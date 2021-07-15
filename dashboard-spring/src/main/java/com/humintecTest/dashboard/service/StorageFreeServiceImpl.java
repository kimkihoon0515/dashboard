package com.humintecTest.dashboard.service;

import com.humintecTest.dashboard.dao.StorageFreeDao;
import com.humintecTest.dashboard.vo.StorageFreeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageFreeServiceImpl implements StorageFreeService{
    @Autowired
    StorageFreeDao storageFreeDao;
    @Override
    public List<StorageFreeVo> selectStorageFree(StorageFreeVo vo) {
        return storageFreeDao.selectStorageFree(vo);
    }

    @Override
    public int insertStorageFree(StorageFreeVo vo) {
        try {
            storageFreeDao.insertStorageFree(vo);
            return 0;
        }catch (Exception e){
            return -1;
        }
    }
}
