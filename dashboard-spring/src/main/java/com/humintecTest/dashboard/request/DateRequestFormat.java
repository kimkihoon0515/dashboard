package com.humintecTest.dashboard.request;

import java.sql.Date;

import lombok.Data;

@Data
public class DateRequestFormat {
	private Date startDate;
	private Date finishDate;
}
