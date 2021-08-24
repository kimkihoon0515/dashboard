package com.humintecTest.dashboard.vo;

import lombok.Data;

@Data
public class StorageStatusVo {
    private String storage_name;
    private long total;
    private long used;
    private long free;
    private String hupax_or_sectra;
}
