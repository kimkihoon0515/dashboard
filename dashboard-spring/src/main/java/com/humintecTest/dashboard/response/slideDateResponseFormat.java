package com.humintecTest.dashboard.response;

import com.humintecTest.dashboard.vo.SlideDateVo;
import lombok.Data;

import java.sql.Date;

@Data
public class slideDateResponseFormat {
    private Date date;
    private long slide_sum;
    public slideDateResponseFormat(SlideDateVo vo){
        date = vo.getDate();
        slide_sum = vo.getSlide_sum();
    }
}