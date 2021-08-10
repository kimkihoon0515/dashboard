package com.humintecTest.dashboard.vo;

import lombok.Data;

import java.sql.Date;

@Data
public class HupaxVo {
    private Date date;
    private long daily_used;
    private long daily_sum;
}
