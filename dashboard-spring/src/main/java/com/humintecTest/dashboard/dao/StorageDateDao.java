package com.humintecTest.dashboard.dao;

import com.humintecTest.dashboard.vo.StorageDateVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StorageDateDao {
    public List<StorageDateVo> selectStorageDate(StorageDateVo vo);
    public int insertStorageDate(StorageDateVo vo);
    public int deleteStorageDate(StorageDateVo vo);
}
