package com.humintecTest.dashboard.vo;

import lombok.Data;

@Data
public class ScannerListVo {
    private String scanner_type;
    private int slide_sum;

    @Override
    public String toString(){
        return "ScannerListVo [scanner_type=" + scanner_type + "]";
    }
}
