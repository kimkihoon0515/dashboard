package com.humintecTest.dashboard.service;

import com.humintecTest.dashboard.vo.StorageStatusVo;

import java.util.List;

public interface StorageStatusService {
    List<StorageStatusVo> selectStorageStatus (StorageStatusVo vo);
    int insertStorageStatus(StorageStatusVo vo);
    int deleteStorageStatus();
}
