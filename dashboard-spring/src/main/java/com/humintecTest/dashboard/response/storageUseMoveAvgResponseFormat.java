package com.humintecTest.dashboard.response;

import com.humintecTest.dashboard.vo.StorageUseVo;
import lombok.Data;

@Data
public class storageUseMoveAvgResponseFormat {
    private String date;
    private long daily_sum;
    private long sum;
    private long avg;

    public storageUseMoveAvgResponseFormat(StorageUseVo vo)
    {
        date = vo.getDate();
        daily_sum = vo.getDaily_sum();
    }
}