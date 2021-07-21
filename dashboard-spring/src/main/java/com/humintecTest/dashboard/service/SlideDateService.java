package com.humintecTest.dashboard.service;

import com.humintecTest.dashboard.vo.SlideDateVo;

import java.util.List;

public interface SlideDateService {
    public List<SlideDateVo> selectSlideDate(SlideDateVo vo);
    public int insertSlideDate(SlideDateVo vo);
    public int deleteSlideDate(SlideDateVo vo);
    public List<SlideDateVo> showSlideDate(SlideDateVo vo);
}