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
    public int deleteStorageDate() {
        try{
            storageDateDao.deleteStorageDate();
            return 0;
        }
        catch (Exception e) {
            return 1;
        }
    }

	@Override
	public int updateStorageDate() {
		if(this.deleteStorageDate()== 0){
            StorageDateVo vo = new StorageDateVo();
            List<StorageDateVo> vList = this.selectStorageDate(vo);

            for(StorageDateVo target : vList) {
                if(this.insertStorageDate(target) == 0) {

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