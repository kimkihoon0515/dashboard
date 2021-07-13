package com.humintecTest.dashboard.vo;

import lombok.Data;

import java.util.Date;

@Data
public class SlideDateListVo {
    private Date last_scan_date;
    private int slide_count;
}
