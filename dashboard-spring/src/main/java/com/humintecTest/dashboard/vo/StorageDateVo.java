package com.humintecTest.dashboard.vo;

import lombok.Data;

import java.math.BigInteger;
import java.sql.Date;

@Data
public class StorageDateVo {
    private Date date;
    private BigInteger daily_sum;
    private String storage_name;
    private BigInteger total;
}
