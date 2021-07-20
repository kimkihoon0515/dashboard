package com.humintecTest.dashboard.response;

import com.humintecTest.dashboard.vo.StorageTableVo;
import lombok.Data;

import java.math.BigInteger;
import java.util.Date;

@Data
public class storageTableResponseFormat {
    private Date date;
    private BigInteger used;
    private String storage_name;
    public storageTableResponseFormat(StorageTableVo vo){
        date = vo.getDate();
        used = vo.getUsed();
        storage_name = vo.getStorage_name();
    }
}
