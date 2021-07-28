package com.humintecTest.dashboard.vo;

import lombok.Data;

import java.sql.Date;

@Data
public class StorageFreeVo {
    private Date date;
    private String storage_name;
    private long daily_used;
    private double free;
    private long total;
    private String start_date;
    private String end_date;
}
