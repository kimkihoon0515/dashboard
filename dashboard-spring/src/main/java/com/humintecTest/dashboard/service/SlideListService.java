package com.humintecTest.dashboard.service;

import com.humintecTest.dashboard.vo.SlideListVo;

import java.util.List;

public interface SlideListService {
    List<SlideListVo> selectSlideList(SlideListVo vo);
}
