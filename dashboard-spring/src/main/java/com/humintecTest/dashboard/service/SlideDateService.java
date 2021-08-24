package com.humintecTest.dashboard.service;

import com.humintecTest.dashboard.request.DateRequestFormat;
import com.humintecTest.dashboard.response.slideTypeResponseFormat;
import com.humintecTest.dashboard.vo.SlideDateVo;

import java.util.List;

public interface SlideDateService {
    public List<SlideDateVo> selectSlideDate(SlideDateVo vo);
    public int insertSlideDate(SlideDateVo vo);
    public int deleteSlideDate();
    public int updateSlideDate();
    public List<slideTypeResponseFormat> selectSlidePerDateNP(DateRequestFormat req);
    public List<slideTypeResponseFormat> selectSlidePerDateByDate(DateRequestFormat req);
    public List<slideTypeResponseFormat> selectSlidePerDateByMonth(DateRequestFormat req);
    public List<slideTypeResponseFormat> selectSlidePerDateByYear(DateRequestFormat req);
}
