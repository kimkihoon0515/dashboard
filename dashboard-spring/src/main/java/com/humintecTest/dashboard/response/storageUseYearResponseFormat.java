package com.humintecTest.dashboard.response;

import com.humintecTest.dashboard.vo.StorageUseVo;
import lombok.Data;

import java.math.BigInteger;

@Data
public class storageUseYearResponseFormat {
    private String year;
    private BigInteger daily_sum;

    public storageUseYearResponseFormat (StorageUseVo vo)
    {
        year = vo.getYear();
        daily_sum = vo.getDaily_sum();
    }
}
