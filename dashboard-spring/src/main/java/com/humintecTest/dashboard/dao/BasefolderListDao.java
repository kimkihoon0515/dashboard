package com.humintecTest.dashboard.dao;

import com.humintecTest.dashboard.vo.BasefolderListVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BasefolderListDao {
    public List<BasefolderListVo> selectBasefolderList(BasefolderListVo vo);
}
