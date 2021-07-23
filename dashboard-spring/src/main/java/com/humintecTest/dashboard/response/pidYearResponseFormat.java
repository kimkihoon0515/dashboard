package com.humintecTest.dashboard.response;

import com.humintecTest.dashboard.vo.PidVo;
import lombok.Data;

@Data
public class pidYearResponseFormat {
    private String pathID_ch;
    private String year;
    private int amount;

    public pidYearResponseFormat(PidVo vo)
    {
        pathID_ch = vo.getPathID_ch();
        year = vo.getYear();
        amount = vo.getAmount();
    }
}
