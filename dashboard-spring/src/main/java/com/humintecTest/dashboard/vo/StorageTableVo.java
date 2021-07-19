package com.humintecTest.dashboard.vo;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class StorageTableVo {
    private Date date;
    private BigInteger used;
    private String storage_name;
}
