package com.humintecTest.dashboard.dao;


import com.humintecTest.dashboard.vo.SlideListVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SlideListDao {
    public List<SlideListVo> selectSlideList(SlideListVo vo);
}
