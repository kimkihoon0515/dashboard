package com.humintecTest.dashboard.response;

import com.humintecTest.dashboard.vo.PidVo;
import lombok.Data;

@Data
public class pidShowResponseFormat {
    private String pathID_ch;
    private int amount;

    public pidShowResponseFormat (PidVo vo){
        pathID_ch = vo.getPathID_ch();
        amount = vo.getAmount();
    }
}
