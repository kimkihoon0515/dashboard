package com.humintecTest.dashboard.vo;

import lombok.Data;

import java.math.BigInteger;
import java.sql.Date;

@Data
public class StorageUseVo {
    private String date;
    private long daily_sum;
}
