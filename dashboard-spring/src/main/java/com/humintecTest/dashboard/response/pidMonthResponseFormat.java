package com.humintecTest.dashboard.response;

import com.humintecTest.dashboard.vo.PidVo;
import lombok.Data;

@Data
public class pidMonthResponseFormat {
    private String month;
    private String pathID_ch;
    private int amount;

    public pidMonthResponseFormat(PidVo vo)
    {
        month = vo.getMonth();
        pathID_ch = vo.getPathID_ch();
        amount = vo.getAmount();
    }
}
