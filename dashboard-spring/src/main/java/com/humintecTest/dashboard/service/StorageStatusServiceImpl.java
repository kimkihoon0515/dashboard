package com.humintecTest.dashboard.service;

import com.humintecTest.dashboard.dao.StorageStatusDao;
import com.humintecTest.dashboard.vo.StorageStatusVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageStatusServiceImpl  implements StorageStatusService{
    @Autowired
    StorageStatusDao storageStatusDao;
    @Override
    public List<StorageStatusVo> selectStorageStatus(StorageStatusVo vo) {
        return storageStatusDao.selectStorageStatus(vo);
    }

    @Override
    public int insertStorageStatus(StorageStatusVo vo) {
        try {
            storageStatusDao.insertStorageStatus(vo);
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public int deleteStorageStatus() {
        try {
            storageStatusDao.deleteStorageStatus();
            return 0;
        } catch (Exception e)
        {
            return -1;
        }
    }
}
