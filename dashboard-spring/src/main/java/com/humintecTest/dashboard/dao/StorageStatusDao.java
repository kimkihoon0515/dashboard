package com.humintecTest.dashboard.dao;

import com.humintecTest.dashboard.vo.StorageStatusVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StorageStatusDao {
    List<StorageStatusVo> selectStorageStatus (StorageStatusVo vo);
}
