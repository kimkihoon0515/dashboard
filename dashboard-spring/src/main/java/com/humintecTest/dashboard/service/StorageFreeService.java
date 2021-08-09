package com.humintecTest.dashboard.service;

import com.humintecTest.dashboard.request.DateRequestFormat;
import com.humintecTest.dashboard.request.StorageTableRequestFormat;
import com.humintecTest.dashboard.vo.StorageFreeVo;

import java.util.List;

public interface StorageFreeService {
    public List<StorageFreeVo> selectStorageFree(StorageFreeVo vo);
    public int insertStorageFree(StorageFreeVo vo);
    public int deleteStorageFree();
    public List<StorageFreeVo> searchStorageFreeByDate(DateRequestFormat req);
    public List<StorageFreeVo> searchStorageFreeById(StorageTableRequestFormat req);
    public List<StorageFreeVo> showStorageFree(StorageFreeVo vo);
    public int updateStorageFree();
}