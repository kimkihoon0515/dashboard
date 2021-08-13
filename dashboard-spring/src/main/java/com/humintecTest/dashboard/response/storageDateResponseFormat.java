package com.humintecTest.dashboard.response;

import com.humintecTest.dashboard.vo.StorageDateVo;
import lombok.Data;

import java.sql.Date;

@Data
public class storageDateResponseFormat {
    private Date date;
    private double daily_sum;
    private String storage_name;
    private long total;

    public storageDateResponseFormat(StorageDateVo vo){
        date = vo.getDate();
        daily_sum = vo.getDaily_sum();
        storage_name = vo.getStorage_name();
        total = vo.getTotal();
    }
}