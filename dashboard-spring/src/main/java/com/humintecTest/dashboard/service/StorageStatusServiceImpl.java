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
    public List<StorageStatusVo> selectStorageStatus(StorageStatusVo vo) { //slide_list, basefolder_list로부터 storage_type에 넣을 데이터를 불러오기 위한 메소드.
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

    @Override
    public int updateStorageStatus() { // delete 후에 insert 하는 방식으로 storage_type을 update한다.
        StorageStatusVo vo = new StorageStatusVo();
        if (this.deleteStorageStatus() == 0) {
            List<StorageStatusVo> vList =  this.selectStorageStatus(vo);

            for (StorageStatusVo target : vList) {
                if (this.insertStorageStatus(target) == 0) {
                } else {
                    return -1;
                }
            }
        } else {
            return -1;
        }
        return 0;
    }
}
