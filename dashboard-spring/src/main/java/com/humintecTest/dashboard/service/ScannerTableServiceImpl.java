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

	public List<ScannerTableVo> selectScannerTable(ScannerTableVo vo){ //slide_list로부터 scanner_table에 넣을 데이터를 불러오기위한 메소드.
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
	
	public int updateScannerTable() { // delete 후에 insert 하는 방식으로 scanner_table을 update해준다.
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
