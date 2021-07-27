package com.humintecTest.dashboard.service;

import com.humintecTest.dashboard.vo.StorageTableVo;

import java.util.List;

public interface StorageTableService {
    List<StorageTableVo> selectStorageTable(StorageTableVo vo);
    int insertStorageTable(StorageTableVo vo);
    int deleteStorageTable();
}
