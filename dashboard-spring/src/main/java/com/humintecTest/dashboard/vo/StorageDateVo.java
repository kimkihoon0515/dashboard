package com.humintecTest.dashboard.vo;

import lombok.Data;

import java.sql.Date;

@Data
public class StorageDateVo {
    private Date date;
    private double daily_sum;
    private String storage_name;
    private long total;
}