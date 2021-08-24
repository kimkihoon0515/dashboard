package com.humintecTest.dashboard.service;

import com.humintecTest.dashboard.dao.StorageUseDao;
import com.humintecTest.dashboard.request.DateRequestFormat;
import com.humintecTest.dashboard.response.storageUseResponseFormat;
import com.humintecTest.dashboard.vo.StorageUseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageUseServiceImpl implements StorageUseService {
    @Autowired
    StorageUseDao storageUseDao;

    @Override
    public List<StorageUseVo> selectStorageUse(StorageUseVo vo) { //storage_use table에 들어갈 데이터를 slide_list로부터 불러오기 위한 메소드.
        return storageUseDao.selectStorageUse(vo);
    }

    @Override
    public int insertStorageUse(StorageUseVo vo) {
        try {
            storageUseDao.insertStorageUse(vo);
            return 0;
        }catch (Exception e){
            return -1;
        }
    }

    @Override
    public int deleteStorageUse() {
        try {
            storageUseDao.deleteStorageUse();
            return 0;
        }catch (Exception e){
            return -1;
        }
    }

	@Override
	public int updateStorageUse() { //delete 후에 insert 하는 방식으로 storage_use table을 update 한다.
		if(this.deleteStorageUse() == 0) {
            StorageUseVo vo = new StorageUseVo();
            List<StorageUseVo> vList = this.selectStorageUse(vo);

            for(StorageUseVo target : vList) {
                if(this.insertStorageUse(target)==0) {

                }
                else {
                    return -1;
                }
            }
        }
        else {
            return -1;
        }

        return 0;
	}

    @Override
    public List<storageUseResponseFormat> selectStorageUseNP(DateRequestFormat req) { //parameter에 기간이 들어오지 않았을 때 전체데이터를 불러오는 메소드.
        if(req.getType() == 1) { //연
            return storageUseDao.selectStorageUseByYearNP();
        }
        else if(req.getType() == 2) { //월
            return storageUseDao.selectStorageUseByMonthNP();
        }
        else { //일
            return storageUseDao.selectStorageUseByDateNP();
        }
    }

    @Override
    public List<storageUseResponseFormat> selectStorageUseByDate(DateRequestFormat req) { //parameter에 기간과 type이 3일 때 일 단위로 데이터를 불러오는 메소드.
        return storageUseDao.selectStorageUseByDate(req);
    }

    @Override
    public List<storageUseResponseFormat> selectStorageUseByMonth(DateRequestFormat req) { //parameter에 기간과 type이 2일 때 월 단위로 데이터를 불러오는 메소드.
        return storageUseDao.selectStorageUseByMonth(req);
    }

    @Override
    public List<storageUseResponseFormat> selectStorageUseByYear(DateRequestFormat req) { //parameter에 기간과 type이 1일 때 일 단위로 데이터를 불러오는 메소드.
        return storageUseDao.selectStorageUseByYear(req);
    }
}