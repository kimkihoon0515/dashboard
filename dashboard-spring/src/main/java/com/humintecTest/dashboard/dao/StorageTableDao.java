package com.humintecTest.dashboard.dao;

import com.humintecTest.dashboard.vo.StorageTableVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StorageTableDao {
    public List<StorageTableVo> selectStorageTable(StorageTableVo vo);
    public int insertStorageTable(StorageTableVo vo);
}
