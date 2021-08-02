package com.humintecTest.dashboard.vo;

import lombok.Data;

@Data
public class StorageListVo {
    private long id;
    private String storageName;
    private Long total;
    private Long used;
    private Long free;
    private String serverIp;
}