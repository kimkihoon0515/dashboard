package com.humintecTest.dashboard.vo;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class StorageFreeVo {
    private Date last_scan_date;
    private String storage_name;
    private BigInteger daily_used;
    private BigInteger free;
    private BigInteger total;
}
