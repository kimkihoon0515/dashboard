package com.humintecTest.dashboard.response;

import com.humintecTest.dashboard.vo.ScannerSlideVo;
import lombok.Data;

@Data
public class scannerResponseFormat {
    private String scanner_type;
    private long slide_sum;

    public scannerResponseFormat(ScannerSlideVo vo){
        scanner_type = vo.getScanner_type();
        slide_sum = vo.getSlide_sum();
    }
}
