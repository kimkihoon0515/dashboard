package com.humintecTest.dashboard.service;


import com.humintecTest.dashboard.request.DateRequestFormat;
import com.humintecTest.dashboard.response.pidchResponseFormat;
import com.humintecTest.dashboard.vo.PidVo;

import java.util.List;

public interface PidService {
    public List<PidVo> selectPid (PidVo vo);
    public int insertPid (PidVo vo);
    public int deletePid ();
    public List<pidchResponseFormat> searchPidByDate (DateRequestFormat req);
    public List<PidVo> showPid (PidVo vo);
    public List<PidVo> selPidMonth(PidVo vo);
    public List<PidVo> selPidYear(PidVo vo);
}

