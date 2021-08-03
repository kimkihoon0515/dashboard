package com.humintecTest.dashboard.response;

import com.humintecTest.dashboard.vo.PidVo;
import lombok.Data;

import java.sql.Date;

@Data
public class pidResponseFormat {
    private Date last_scan_date;
    private String pathID_ch;
    private long amount;

    public pidResponseFormat(PidVo vo) {
        last_scan_date = vo.getLast_scan_date();
        pathID_ch = vo.getPathID_ch();
        amount = vo.getAmount();
    }
}
