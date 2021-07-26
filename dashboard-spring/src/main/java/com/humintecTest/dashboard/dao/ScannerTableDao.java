package com.humintecTest.dashboard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.humintecTest.dashboard.vo.ScannerTableVo;

@Mapper
public interface ScannerTableDao {
	public List<ScannerTableVo> selectScannerTable(ScannerTableVo vo);
	public void insertScannerTable(ScannerTableVo vo);
	public void deleteScannerTable();
}
