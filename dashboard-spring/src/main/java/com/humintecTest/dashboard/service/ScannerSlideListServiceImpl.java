package com.humintecTest.dashboard.service;

import com.humintecTest.dashboard.dao.ScannerSlideListDao;
import com.humintecTest.dashboard.request.DateRequestFormat;
import com.humintecTest.dashboard.vo.ScannerSlideVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ScannerSlideListServiceImpl implements ScannerSlideListService{
    @Autowired
    ScannerSlideListDao scannerSlideListDao;

    @Override
    public int insertScanner(ScannerSlideVo vo) {
        try{
            scannerSlideListDao.insertScanner(vo);
            return 0;
        }
        catch (Exception e){
            return -1;
        }
    }

    @Override
    public List<ScannerSlideVo> selectScanner(ScannerSlideVo vo) {
        return scannerSlideListDao.selectScanner(vo);
    }

    @Override
    public int deleteScanner() {
        try{
            scannerSlideListDao.deleteScanner();
            return 0;
        } catch (Exception e){
            return -1;
        }
    }

    @Override
    public List<ScannerSlideVo> showScanner(ScannerSlideVo vo) {
        return scannerSlideListDao.showScanner(vo);
    }
    
    @Override
	public List<ScannerSlideVo> searchScannerList() {
		return scannerSlideListDao.searchScannerList();
	}
    
	@Override
	public List<ScannerSlideVo> searchScannerListByDate(DateRequestFormat req) {
		return scannerSlideListDao.searchScannerListByDate(req);
	}
}

