package com.humintecTest.dashboard.vo;

import lombok.Data;

import java.sql.Date;

@Data
public class PidVo {
    private Date last_scan_date;
    private String pathID_ch;
    private long amount;
    private String start_date;
    private String end_date;
}