package com.humintecTest.dashboard.response;

import com.humintecTest.dashboard.vo.StorageDateVo;
import lombok.Data;

import java.math.BigInteger;
import java.sql.Date;

@Data
public class storageDateResponseFormat {
    private Date date;
    private BigInteger daily_sum;
    private String storage_name;
    private BigInteger total;

    public storageDateResponseFormat(StorageDateVo vo){
        date = vo.getDate();
        daily_sum = vo.getDaily_sum();
        storage_name = vo.getStorage_name();
        total = vo.getTotal();
    }
}
