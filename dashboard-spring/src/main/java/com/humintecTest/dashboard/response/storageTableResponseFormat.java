package com.humintecTest.dashboard.response;

import com.humintecTest.dashboard.vo.StorageTableVo;
import lombok.Data;

import java.sql.Date;

@Data
public class storageTableResponseFormat {
    private Date date;
    private double used;
    private String storage_name;
    private double daily_used;
    private long total;

    public storageTableResponseFormat(StorageTableVo vo){
        date = vo.getDate();
        used = vo.getUsed();
        storage_name = vo.getStorage_name();
        daily_used = vo.getDaily_used();
        total = vo.getTotal();
    }
}