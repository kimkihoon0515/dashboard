package com.humintecTest.dashboard.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class ScannerTableVo {
	private Date date;
	private String scannerType;
	private int amount;
}
