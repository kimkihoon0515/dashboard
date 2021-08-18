package com.humintecTest.dashboard.vo;

import lombok.Data;

@Data
public class StorageListVo {
    private long id;
    private String storageName;
    private long total;
    private long used;
    private long free;
    private String serverIp;
}