package com.humintecTest.dashboard.dao;

import com.humintecTest.dashboard.request.DateRequestFormat;
import com.humintecTest.dashboard.vo.SlideDateVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface SlideDateDao {
    public List<SlideDateVo> selectSlideDate(SlideDateVo vo);
    public int insertSlideDate(SlideDateVo vo);
    public int deleteSlideDate();
    
    public List<SlideDateVo> selectSlidePerDateList(DateRequestFormat req);
}
