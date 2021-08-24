package com.humintecTest.dashboard.response;

import java.sql.Date;

import com.humintecTest.dashboard.vo.ScannerTableVo;

import lombok.Data;

@Data
public class ScannerTableResponseFormat {
	private Date date;
	private String scannerType;
	private long amount;

	public ScannerTableResponseFormat(ScannerTableVo vo) {
		this.date = vo.getDate();
		this.scannerType = vo.getScannerType();
		this.amount = vo.getAmount();
	}
}