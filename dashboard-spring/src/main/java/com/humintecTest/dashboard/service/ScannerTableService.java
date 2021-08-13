package com.humintecTest.dashboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.humintecTest.dashboard.request.DateRequestFormat;
import com.humintecTest.dashboard.request.ScannerTableRequestFormat;
import com.humintecTest.dashboard.vo.ScannerTableVo;

@Service
public interface ScannerTableService {
	public List<ScannerTableVo> selectScannerTable(ScannerTableVo vo);
	public int insertScannerTable(ScannerTableVo vo);
	public int deleteScannerTable();
	public int updateScannerTable();
	public List<ScannerTableVo> searchScannerTable();
	public List<ScannerTableVo> searchScannerTableBydate(DateRequestFormat req);
	public List<ScannerTableVo> searchScannerTableById(ScannerTableRequestFormat req);

}