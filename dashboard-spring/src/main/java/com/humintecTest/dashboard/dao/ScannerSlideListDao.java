package com.humintecTest.dashboard.dao;

import com.humintecTest.dashboard.vo.ScannerSlideVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Scanner;


@Mapper
public interface ScannerSlideListDao {
    public void insertScanner(ScannerSlideVo vo);
    public List<ScannerSlideVo> selectScanner(ScannerSlideVo vo);
}
