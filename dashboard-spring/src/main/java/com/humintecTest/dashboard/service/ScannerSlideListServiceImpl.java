package com.humintecTest.dashboard.service;

import com.humintecTest.dashboard.dao.ScannerSlideListDao;
import com.humintecTest.dashboard.vo.ScannerSlideVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScannerSlideListServiceImpl implements ScannerSlideListService{
    @Autowired
    ScannerSlideListDao scannerSlideListDao;

    @Override
    public List<ScannerSlideVo> insertScanner(ScannerSlideVo vo) {
        return scannerSlideListDao.insertScanner(vo);
    }
}
