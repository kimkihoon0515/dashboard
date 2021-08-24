package com.humintecTest.dashboard.response;

import com.humintecTest.dashboard.vo.StorageFreeVo;
import lombok.Data;

import java.sql.Date;

@Data
public class storageFreeResponseFormat {
    private Date date;
    private String storage_name;
    private double daily_used;
    private long total;
    public storageFreeResponseFormat(StorageFreeVo vo){
        date = vo.getDate();
        storage_name = vo.getStorage_name();
        daily_used = vo.getDaily_used();
        total = vo.getTotal();
    }
}