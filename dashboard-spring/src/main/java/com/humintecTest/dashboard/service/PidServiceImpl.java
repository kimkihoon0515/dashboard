package com.humintecTest.dashboard.service;

import com.humintecTest.dashboard.dao.PidDao;
import com.humintecTest.dashboard.request.DateRequestFormat;
import com.humintecTest.dashboard.response.pidchResponseFormat;
import com.humintecTest.dashboard.vo.PidVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PidServiceImpl implements PidService {
    @Autowired
    PidDao pidDao;

    @Override
    public List<PidVo> selectPid(PidVo vo) { // slide_list table로부터 pathid_table에 넣을 데이터를 불러오는 메소드.
        return pidDao.selectPid(vo);
    }

    @Override
    public int insertPid(PidVo vo) {
        try {
            pidDao.insertPid(vo);
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public int deletePid() {
        try {
            pidDao.deletePid();
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }
    
	@Override
	public int updatePid(){ // delete 후에 insert 하는 방식으로 진행.
		if(this.deletePid() == 0) {
            PidVo vo = new PidVo();
            List<PidVo> vList = this.selectPid(vo);

            for (PidVo target : vList){
                if(this.insertPid(target) == 0){

                }
                else
                    return -1;
            }
        }
        else {
            return -1;
        }
		return 0;
	}
	
    @Override
    public List<pidchResponseFormat> searchPid() { // pid table의 모든 정보를 불러오는 메소드.
        return pidDao.searchPid();
    }

    @Override
    public List<pidchResponseFormat> searchPidByDate(DateRequestFormat req) { // 조건에 맞는 pid table의 정보를 불러오는 메소드.
        try {
            return pidDao.searchPidByDate(req);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}