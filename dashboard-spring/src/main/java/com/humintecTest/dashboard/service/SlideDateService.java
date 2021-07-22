package com.humintecTest.dashboard.service;

import com.humintecTest.dashboard.request.DateRequestFormat;
import com.humintecTest.dashboard.vo.SlideDateVo;

import java.util.Date;
import java.util.List;

public interface SlideDateService {
    public List<SlideDateVo> selectSlideDate(SlideDateVo vo);
    public int insertSlideDate(SlideDateVo vo);
    public int deleteSlideDate();
    
    public List<SlideDateVo> selectDateToDate(DateRequestFormat req);
    public List<SlideDateVo> showSlideDate(SlideDateVo vo);
    public List<SlideDateVo> selMonth(SlideDateVo vo);
    public List<SlideDateVo> selYear(SlideDateVo vo);
}


