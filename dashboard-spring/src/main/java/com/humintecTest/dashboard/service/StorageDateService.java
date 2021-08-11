package com.humintecTest.dashboard.service;

import com.humintecTest.dashboard.vo.StorageDateVo;

import java.util.List;

public interface StorageDateService {
    public List<StorageDateVo> selectStorageDate(StorageDateVo vo);
    public int insertStorageDate(StorageDateVo vo);
    public int deleteStorageDate();
}