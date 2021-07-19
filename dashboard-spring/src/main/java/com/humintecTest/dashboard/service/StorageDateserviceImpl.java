package com.humintecTest.dashboard.service;

import com.humintecTest.dashboard.dao.StorageDateDao;
import com.humintecTest.dashboard.vo.StorageDateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageDateserviceImpl implements StorageDateService{
    @Autowired
    StorageDateDao storageDateDao;
    @Override
    public List<StorageDateVo> selectStorageDate(StorageDateVo vo) {
        return storageDateDao.selectStorageDate(vo);
    }

    @Override
    public int insertStorageDate(StorageDateVo vo) {
        try {
            storageDateDao.insertStorageDate(vo);
            return 0;
        }
        catch(Exception e){
            return -1;
        }
    }

    @Override
    public int deleteStorageDate(StorageDateVo vo) {
        try{
            storageDateDao.deleteStorageDate(vo);
            return 1;
        }
        catch (Exception e) {
            return 0;
        }
    }
}
