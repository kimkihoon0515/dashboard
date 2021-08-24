package com.humintecTest.dashboard.response;

import com.humintecTest.dashboard.vo.StorageListVo;
import lombok.Data;

@Data
public class storageListResponseFormat {
    private long id;
    private String storageName;
    private Long total;
    private Long used;
    private Long free;
    private String server_ip;

    public storageListResponseFormat(StorageListVo vo){
        id = vo.getId();
        storageName = vo.getStorageName();
        total = vo.getTotal();
        used = vo.getUsed();
        free = vo.getFree();
        server_ip = vo.getServerIp();
    }
}