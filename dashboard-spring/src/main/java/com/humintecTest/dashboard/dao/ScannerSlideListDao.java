package com.humintecTest.dashboard.dao;

import com.humintecTest.dashboard.vo.ScannerSlideVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScannerSlideListDao {
    public int insertScanner(ScannerSlideVo vo);
}
