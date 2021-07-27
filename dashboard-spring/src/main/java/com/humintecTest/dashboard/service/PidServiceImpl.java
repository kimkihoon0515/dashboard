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
    public List<PidVo> selectPid(PidVo vo) {
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
    public List<pidchResponseFormat> searchPidByDate(DateRequestFormat req) {
        try {
            return pidDao.searchPidByDate(req);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public List<PidVo> showPid(PidVo vo) {
        return pidDao.showPid(vo);
    }

}