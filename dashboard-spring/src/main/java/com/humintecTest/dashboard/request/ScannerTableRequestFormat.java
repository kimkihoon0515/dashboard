package com.humintecTest.dashboard.request;

import java.sql.Date;

import lombok.Data;

@Data
public class ScannerTableRequestFormat {
	private Date startDate;
	private Date finishDate;
	private String scannerType;
}