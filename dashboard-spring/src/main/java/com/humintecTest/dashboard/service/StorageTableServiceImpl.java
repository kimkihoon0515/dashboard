package com.humintecTest.dashboard.service;

import com.humintecTest.dashboard.dao.StorageTableDao;
import com.humintecTest.dashboard.request.StorageTableRequestFormat;
import com.humintecTest.dashboard.vo.StorageTableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageTableServiceImpl implements StorageTableService{
    @Autowired
    StorageTableDao storageTableDao;

    @Override
    public List<StorageTableVo> selectStorageTable(StorageTableVo vo) {
        return storageTableDao.selectStorageTable(vo);
    }

    @Override
    public int insertStorageTable(StorageTableVo vo) {
        try {
            storageTableDao.insertStorageTable(vo);
            return 0;
        }
        catch (Exception e){
            return -1;
        }
    }

    @Override
    public int deleteStorageTable() {
        try{
            storageTableDao.deleteStorageTable();
            return 0;
        }catch (Exception e){
            return -1;
        }
    }

    @Override
    public List<StorageTableVo> selectStorageTableById(StorageTableRequestFormat req) {
        return storageTableDao.selectStorageTableById(req);
    }

	@Override
	public int updateStorageTable() {
		StorageTableVo vo = new StorageTableVo();
        if(this.deleteStorageTable()==0){
            List<StorageTableVo> vList = this.selectStorageTable(vo);

            for(StorageTableVo target : vList){
                if(this.insertStorageTable(target)== 0){
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
}