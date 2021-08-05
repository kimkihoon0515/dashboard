package com.humintecTest.dashboard.request;

import lombok.Data;

@Data
public class StorageTableRequestFormat {
    private String StorageName;
    private int n; // 데이터의 표본을 몇개로 삼을 것인지 정하는 변수
}
