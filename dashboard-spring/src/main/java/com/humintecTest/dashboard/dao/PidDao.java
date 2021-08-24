package com.humintecTest.dashboard.dao;


import com.humintecTest.dashboard.request.DateRequestFormat;
import com.humintecTest.dashboard.response.pidchResponseFormat;
import com.humintecTest.dashboard.vo.PidVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PidDao {
    public List<PidVo> selectPid (PidVo vo);
    public int insertPid(PidVo vo);
    public int deletePid();
    public List<pidchResponseFormat> searchPid ();
    public List<pidchResponseFormat> searchPidByDate (DateRequestFormat req);
}