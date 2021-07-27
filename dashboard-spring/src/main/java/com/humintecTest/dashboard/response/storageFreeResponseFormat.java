package com.humintecTest.dashboard.response;

import com.humintecTest.dashboard.vo.StorageFreeVo;
import lombok.Data;

import java.math.BigInteger;
import java.sql.Date;

@Data
public class storageFreeResponseFormat {
    private Date date;
    private String storage_name;
    private BigInteger daily_used;
    private float free;
    private BigInteger total;
    public storageFreeResponseFormat(StorageFreeVo vo){
        date = vo.getDate();
        storage_name = vo.getStorage_name();
        daily_used = vo.getDaily_used();
        free = vo.getFree();
        total = vo.getTotal();
    }
}
