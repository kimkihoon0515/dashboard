package com.humintecTest.dashboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humintecTest.dashboard.dao.ScannerTableDao;
import com.humintecTest.dashboard.request.DateRequestFormat;
import com.humintecTest.dashboard.request.ScannerTableRequestFormat;
import com.humintecTest.dashboard.vo.ScannerTableVo;

@Service
public class ScannerTableServiceImpl implements ScannerTableService{
	@Autowired
	ScannerTableDao scannerTableDao;

	public List<ScannerTableVo> selectScannerTable(ScannerTableVo vo){
		return scannerTableDao.selectScannerTable(vo);
	}

	public int insertScannerTable(ScannerTableVo vo) {
		try {
			scannerTableDao.insertScannerTable(vo);

			return 0;
		}
		catch (Exception e) {
			return -1;
		}
	}

	public int deleteScannerTable() {
		try {
			scannerTableDao.deleteScannerTable();

			return 0;
		}
		catch (Exception e) {
			return -1;
		}
	}

	public List<ScannerTableVo> searchScannerTable(){
		return scannerTableDao.searchScannerTable();
	}

	public List<ScannerTableVo> searchScannerTableBydate(DateRequestFormat req){
		return scannerTableDao.searchScannerTableByDate(req);
	}

	public List<ScannerTableVo> searchScannerTableById(ScannerTableRequestFormat req){
		return scannerTableDao.searchScannerTableById(req);
	}
	
	public int updateScannerTable() {
		if(this.deleteScannerTable() == 0) {
			ScannerTableVo vo = new ScannerTableVo();
			List<ScannerTableVo> vList = this.selectScannerTable(vo);

			for(ScannerTableVo target : vList) {
				if(this.insertScannerTable(target) == 0) {

				}
				else {
					return -1;
				}
			}
		}
		else {
			return -1;
		}

		return 0;
	}
}
