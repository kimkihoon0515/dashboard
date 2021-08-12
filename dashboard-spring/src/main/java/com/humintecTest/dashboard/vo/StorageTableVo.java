package com.humintecTest.dashboard.vo;

import lombok.Data;

import java.math.BigInteger;
import java.sql.Date;

@Data
public class StorageTableVo {
    private Date date;
    private double used;
    private String storage_name;
    private double daily_used;
    private long total;
}

