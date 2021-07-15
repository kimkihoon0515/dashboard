package com.humintecTest.dashboard.dao;

import com.humintecTest.dashboard.vo.StorageFreeVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StorageFreeDao {
    public List<StorageFreeVo> selectStorageFree(StorageFreeVo vo);
    public int insertStorageFree(StorageFreeVo vo);
}
