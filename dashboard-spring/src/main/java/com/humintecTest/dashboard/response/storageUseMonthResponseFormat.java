package com.humintecTest.dashboard.response;

import com.humintecTest.dashboard.vo.StorageUseVo;
import lombok.Data;

import java.math.BigInteger;

@Data
public class storageUseMonthResponseFormat {
    private String month;
    private BigInteger daily_sum;

    public storageUseMonthResponseFormat (StorageUseVo vo)
    {
        month = vo.getMonth();
        daily_sum = vo.getDaily_sum();
    }
}
