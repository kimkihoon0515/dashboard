package com.humintecTest.dashboard.dao;

import com.humintecTest.dashboard.request.DateRequestFormat;
import com.humintecTest.dashboard.request.StorageFreeRequestFormat;
import com.humintecTest.dashboard.vo.StorageFreeVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StorageFreeDao {
    public List<StorageFreeVo> selectStorageFree(StorageFreeVo vo);
    public int insertStorageFree(StorageFreeVo vo);
    public int deleteStorageFree();
    public List<StorageFreeVo> searchStorageFree(StorageFreeVo vo);
    public List<StorageFreeVo> selectStorageFreeByDate(DateRequestFormat req);
    public List<StorageFreeVo> selectStorageFreeById(StorageFreeRequestFormat req);
    public List<StorageFreeVo> showStorageFree(StorageFreeVo vo);
}


