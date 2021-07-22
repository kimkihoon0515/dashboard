package com.humintecTest.dashboard.response;

import com.humintecTest.dashboard.vo.SlideDateVo;
import lombok.Data;

import java.sql.Date;

@Data
public class slideMonthResponseFormat {
    private String month;
    private int slide_sum;

    public slideMonthResponseFormat(SlideDateVo vo){
        month = vo.getMonth();
        slide_sum = vo.getSlide_sum();
    }
}
