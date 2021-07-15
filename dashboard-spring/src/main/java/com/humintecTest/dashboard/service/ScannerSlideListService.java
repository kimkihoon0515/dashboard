package com.humintecTest.dashboard.service;

import com.humintecTest.dashboard.vo.ScannerSlideVo;
import com.humintecTest.dashboard.vo.SlideListVo;

import java.util.List;

public interface ScannerSlideListService {
    public int insertScanner(ScannerSlideVo vo);
    public List<ScannerSlideVo> selectScanner(ScannerSlideVo vo);
}
