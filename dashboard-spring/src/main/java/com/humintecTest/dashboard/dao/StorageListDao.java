package com.humintecTest.dashboard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.humintecTest.dashboard.vo.StorageListVo;

@Mapper
public interface StorageListDao {
    public List<StorageListVo> selectStorageList(StorageListVo vo);
}