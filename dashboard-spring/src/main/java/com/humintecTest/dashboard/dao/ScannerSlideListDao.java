package com.humintecTest.dashboard.dao;

import com.humintecTest.dashboard.vo.ScannerSlideVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Scanner;


@Mapper
public interface ScannerSlideListDao {
    void insertScanner(ScannerSlideVo vo);
    List<ScannerSlideVo> selectScanner(ScannerSlideVo vo);
    int deleteScanner(ScannerSlideVo vo);
    List<ScannerSlideVo> showScanner(ScannerSlideVo vo);
}
