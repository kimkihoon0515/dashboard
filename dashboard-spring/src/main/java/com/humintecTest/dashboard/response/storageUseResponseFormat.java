package com.humintecTest.dashboard.response;

import com.humintecTest.dashboard.vo.StorageUseVo;
import lombok.Data;

import java.math.BigInteger;
import java.sql.Date;

@Data
public class storageUseResponseFormat {
    private String date;
    private long daily_sum;

    public storageUseResponseFormat (StorageUseVo vo)
    {
        date = vo.getDate();
        daily_sum = vo.getDaily_sum();
    }
}