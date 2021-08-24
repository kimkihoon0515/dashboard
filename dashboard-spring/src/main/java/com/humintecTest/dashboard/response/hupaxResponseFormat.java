package com.humintecTest.dashboard.response;

import com.humintecTest.dashboard.vo.HupaxVo;
import lombok.Data;

import java.sql.Date;

@Data
public class hupaxResponseFormat { // 결과값을 FE에 전달하는 객체
    private Date date;
    private long daily_used;
    private long daily_sum;
    private long total;
    public hupaxResponseFormat (HupaxVo vo)
    {
        date = vo.getDate();
        daily_used = vo.getDaily_used();
        daily_sum = vo.getDaily_sum();
        total = vo.getTotal();
    }
}
