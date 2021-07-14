package com.humintecTest.dashboard.service;

import com.humintecTest.dashboard.dao.PidDao;
import com.humintecTest.dashboard.vo.PidVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PidServiceImpl implements PidService{

    @Autowired
    PidDao pidDao;

    @Override
    public List<PidVo> insertPid(PidVo vo) {
        return pidDao.insertPid(vo);
    }
}
