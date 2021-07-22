package com.humintecTest.dashboard.service;

import com.humintecTest.dashboard.dao.StorageUseDao;
import com.humintecTest.dashboard.request.DateRequestFormat;
import com.humintecTest.dashboard.vo.StorageFreeVo;
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
    public int deleteStorageUse(StorageUseVo vo) {
        try {
            storageUseDao.deleteStorageUse(vo);
            return 1;
        }catch (Exception e){
            return 0;
        }
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
    public List<StorageUseVo> selStorageMonth(StorageUseVo vo) {
        return storageUseDao.selStorageMonth(vo);
    }

    @Override
    public List<StorageUseVo> selStorageYear(StorageUseVo vo) {
        return storageUseDao.selStorageYear(vo);
    }
}
