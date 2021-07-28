package com.humintecTest.dashboard.vo;

import lombok.Data;

import java.math.BigInteger;
import java.sql.Date;

@Data
public class StorageFreeVo {
    private Date date;
    private String storage_name;
    private BigInteger daily_used;
    private float free;
    private BigInteger total;
    private String start_date;
    private String end_date;
}
