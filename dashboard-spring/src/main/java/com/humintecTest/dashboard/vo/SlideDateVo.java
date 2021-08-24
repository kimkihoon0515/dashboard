package com.humintecTest.dashboard.vo;

import lombok.Data;

import java.sql.Date;

@Data
public class SlideDateVo {
    private Date date;
    private long slide_sum;
}