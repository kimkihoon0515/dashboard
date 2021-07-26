package com.humintecTest.dashboard.response;

import lombok.Data;


@Data
public class storageUseTypeResponseFormat {
    private String type;
    private Long daily_sum;

}
