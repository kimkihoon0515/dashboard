package com.humintecTest.dashboard.dao;

import com.humintecTest.dashboard.vo.HupaxVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HupaxDao {
    List<HupaxVo> selectHupax (HupaxVo vo);
}
