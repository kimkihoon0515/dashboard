package com.humintecTest.dashboard.service;

import com.humintecTest.dashboard.dao.StorageUseDao;
import com.humintecTest.dashboard.request.DateRequestFormat;
import com.humintecTest.dashboard.response.storageUseMoveAvgResponseFormat;
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
    public List<StorageUseVo> selectStorageUse(StorageUseVo vo) {
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
	public int updateStorageUse() {
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
    public List<StorageUseVo> showStorageUse(StorageUseVo vo) {
        return storageUseDao.showStorageUse(vo);
    }

    @Override
    public List<StorageUseVo> searchStorageUse(DateRequestFormat req) {
        return storageUseDao.searchStorageUse(req);
    }

    @Override
    public List<storageUseMoveAvgResponseFormat> moveAvgStorageUse(StorageUseVo vo) {

        return null;
    }

    @Override
    public List<storageUseResponseFormat> selectStorageUseNP(DateRequestFormat req) {
        if(req.getType() == 1) {
            return storageUseDao.selectStorageUseByYearNP();
        }
        else if(req.getType() == 2) {
            return storageUseDao.selectStorageUseByMonthNP();
        }
        else {
            return storageUseDao.selectStorageUseByDateNP();
        }
    }

    @Override
    public List<storageUseResponseFormat> selectStorageUseByDate(DateRequestFormat req) {
        return storageUseDao.selectStorageUseByDate(req);
    }

    @Override
    public List<storageUseResponseFormat> selectStorageUseByMonth(DateRequestFormat req) {
        return storageUseDao.selectStorageUseByMonth(req);
    }

    @Override
    public List<storageUseResponseFormat> selectStorageUseByYear(DateRequestFormat req) {
        return storageUseDao.selectStorageUseByYear(req);
    }
}