package com.humintecTest.dashboard.dao;

import com.humintecTest.dashboard.request.DateRequestFormat;
import com.humintecTest.dashboard.vo.ScannerSlideVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface ScannerSlideListDao {
    void insertScanner(ScannerSlideVo vo);
    List<ScannerSlideVo> selectScanner(ScannerSlideVo vo);
    int deleteScanner();
    List<ScannerSlideVo> searchScannerList();
    List<ScannerSlideVo> searchScannerListByDate(DateRequestFormat req);
}
