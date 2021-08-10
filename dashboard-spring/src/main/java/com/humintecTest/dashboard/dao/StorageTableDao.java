package com.humintecTest.dashboard.dao;

import com.humintecTest.dashboard.request.StorageTableRequestFormat;
import com.humintecTest.dashboard.vo.StorageTableVo;
import com.humintecTest.dashboard.vo.StorageUseVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StorageTableDao {
    public List<StorageTableVo> selectStorageTable(StorageTableVo vo);
    public int insertStorageTable(StorageTableVo vo);
    public int deleteStorageTable();
    public List<StorageTableVo> selectStorageTableById(StorageTableRequestFormat req);
}