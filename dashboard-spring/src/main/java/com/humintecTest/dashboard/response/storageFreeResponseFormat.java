package com.humintecTest.dashboard.response;

import com.humintecTest.dashboard.vo.StorageFreeVo;
import lombok.Data;

import java.sql.Date;

@Data
public class storageFreeResponseFormat {
    private Date date;
    private String storage_name;
    private long daily_used;
    private double free;
    private long total;
    public storageFreeResponseFormat(StorageFreeVo vo){
        date = vo.getDate();
        storage_name = vo.getStorage_name();
        daily_used = vo.getDaily_used();
        free = vo.getFree();
        total = vo.getTotal();
    }
}
