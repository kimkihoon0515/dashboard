package com.humintecTest.dashboard.dao;


import com.humintecTest.dashboard.vo.PidVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PidDao {
    public List<PidVo> insertPid (PidVo vo);
}
