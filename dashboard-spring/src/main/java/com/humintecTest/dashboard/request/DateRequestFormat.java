package com.humintecTest.dashboard.request;

import java.sql.Date;

import lombok.Data;

@Data
public class DateRequestFormat {
	private Date startDate;
	private Date finishDate;
	private int type;   //1: 연 2: 월 3: 일
}
