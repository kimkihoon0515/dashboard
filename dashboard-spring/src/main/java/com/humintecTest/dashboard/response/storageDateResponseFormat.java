package com.humintecTest.dashboard.response;

import com.humintecTest.dashboard.vo.StorageDateVo;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class storageDateResponseFormat {
    private Date last_scan_date;
    private BigInteger daily_sum;
    private String storage_name;
    private BigInteger total;

    public storageDateResponseFormat(StorageDateVo vo){
        last_scan_date = vo.getLast_scan_date();
        daily_sum = vo.getDaily_sum();
        storage_name = vo.getStorage_name();
        total = vo.getTotal();
    }
}
