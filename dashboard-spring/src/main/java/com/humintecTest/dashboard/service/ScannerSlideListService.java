package com.humintecTest.dashboard.service;

import com.humintecTest.dashboard.vo.ScannerSlideVo;

import java.util.List;
import java.util.Scanner;

public interface ScannerSlideListService {
    public int insertScanner(ScannerSlideVo vo);
    public List<ScannerSlideVo> selectScanner(ScannerSlideVo vo);
    public int deleteScanner(ScannerSlideVo vo);
    public List<ScannerSlideVo> showScanner(ScannerSlideVo vo);
}