package com.humintecTest.dashboard.dao;

import com.humintecTest.dashboard.request.DateRequestFormat;
import com.humintecTest.dashboard.response.slideTypeResponseFormat;
import com.humintecTest.dashboard.vo.SlideDateVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface SlideDateDao {
    public List<SlideDateVo> selectSlideDate(SlideDateVo vo);
    public int insertSlideDate(SlideDateVo vo);
    public int deleteSlideDate();
    public List<slideTypeResponseFormat> selectSlidePerDateByDateNP();
    public List<slideTypeResponseFormat> selectSlidePerDateByMonthNP();
    public List<slideTypeResponseFormat> selectSlidePerDateByYearNP();
    public List<slideTypeResponseFormat> selectSlidePerDateByDate(DateRequestFormat req);
    public List<slideTypeResponseFormat> selectSlidePerDateByMonth(DateRequestFormat req);
    public List<slideTypeResponseFormat> selectSlidePerDateByYear(DateRequestFormat req);

    public List<SlideDateVo> showSlideDate(SlideDateVo vo);
    public List<SlideDateVo> selMonth (SlideDateVo vo);
    public List<SlideDateVo> selYear (SlideDateVo vo);
}
