package com.humintecTest.dashboard.response;

import com.humintecTest.dashboard.vo.SlideDateVo;
import lombok.Data;

import java.sql.Date;

@Data
public class slideYearResponseFormat {
    private String year;
    private int slide_sum;

    public slideYearResponseFormat (SlideDateVo vo){
        year = vo.getYear();
        slide_sum = vo.getSlide_sum();
    }
}
