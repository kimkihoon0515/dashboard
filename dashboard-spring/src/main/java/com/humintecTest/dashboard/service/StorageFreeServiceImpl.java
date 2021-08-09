package com.humintecTest.dashboard.service;

import com.humintecTest.dashboard.dao.StorageFreeDao;
import com.humintecTest.dashboard.request.DateRequestFormat;
import com.humintecTest.dashboard.request.StorageTableRequestFormat;
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
            System.out.println(e);
            return -1;
        }
    }

    @Override
    public int deleteStorageFree() {
        try{
            storageFreeDao.deleteStorageFree();
            return 0;
        }catch (Exception e){
            return -1;
        }
    }

    @Override
    public List<StorageFreeVo> searchStorageFreeByDate(DateRequestFormat req) {
        return storageFreeDao.selectStorageFreeByDate(req);
    }

    @Override
    public List<StorageFreeVo> searchStorageFreeById(StorageTableRequestFormat req) {
        return storageFreeDao.selectStorageFreeById(req);
    }

    @Override
    public List<StorageFreeVo> showStorageFree(StorageFreeVo vo) {
        return storageFreeDao.showStorageFree(vo);
    }

	@Override
	public int updateStorageFree() {
		if (this.deleteStorageFree() == 0) {
            StorageFreeVo vo = new StorageFreeVo();
            List<StorageFreeVo> vList = this.selectStorageFree(vo);

            for (StorageFreeVo target : vList) {
                if (this.insertStorageFree(target) == 0) {

                } else
                    return -1;
            }
        }
        else{
            return -1;
        }

        return 0;
	}
}