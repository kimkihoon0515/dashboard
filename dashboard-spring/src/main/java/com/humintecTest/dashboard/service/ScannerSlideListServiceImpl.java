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
    public List<ScannerSlideVo> selectScanner(ScannerSlideVo vo) { // slide_list table로부터 pathid_table에 넣을 데이터를 불러오는 메소드.
        return scannerSlideListDao.selectScanner(vo);
    }

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
    public int deleteScanner() {
        try{
            scannerSlideListDao.deleteScanner();
            return 0;
        } catch (Exception e){
            return -1;
        }
    }
    
	@Override
	public int updateScanner() { // delete 후에 insert 하는 방식으로 진행.
		if(this.deleteScanner() == 0) {
            ScannerSlideVo vo = new ScannerSlideVo();
            List<ScannerSlideVo> vList = this.selectScanner(vo);

            for(ScannerSlideVo target : vList) {
                if(this.insertScanner(target) == 0){

                }
                else
                    return -1;
            }
        }
		else {
			return -1;
		}
		return 0;
	}

    @Override
    public List<ScannerSlideVo> searchScannerList() { // 전체 scanner_per_slide 데이터를 쿼리해오는 메소드.
        return scannerSlideListDao.searchScannerList();
    }

    @Override
    public List<ScannerSlideVo> searchScannerListByDate(DateRequestFormat req) { // 조건에 맞는 scanner_per_slide 의 정보를 불러오는 메소드.
        return scannerSlideListDao.searchScannerListByDate(req);
    }
}