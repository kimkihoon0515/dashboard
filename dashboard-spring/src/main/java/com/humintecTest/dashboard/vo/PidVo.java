package com.humintecTest.dashboard.vo;

import lombok.Data;

import java.util.Date;

@Data
public class PidVo {
    private Date last_scan_date;
    private  String pathID_ch;
    private int amount;
}
