package com.humintecTest.dashboard.service;

import com.humintecTest.dashboard.request.StorageTableRequestFormat;
import com.humintecTest.dashboard.vo.StorageTableVo;

import java.util.List;

public interface StorageTableService {
    public List<StorageTableVo> selectStorageTable(StorageTableVo vo);
    public int insertStorageTable(StorageTableVo vo);
    public int deleteStorageTable();
    public int updateStorageTable();
    public List<StorageTableVo> selectStorageTableById(StorageTableRequestFormat req);
}