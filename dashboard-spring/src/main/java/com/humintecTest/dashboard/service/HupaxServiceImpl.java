package com.humintecTest.dashboard.service;

import com.humintecTest.dashboard.dao.HupaxDao;
import com.humintecTest.dashboard.request.HupaxRequestFormat;
import com.humintecTest.dashboard.vo.HupaxVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HupaxServiceImpl implements HupaxService{
    @Autowired
    HupaxDao hupaxDao;

    @Override
    public List<HupaxVo> selectHupax(HupaxRequestFormat req) {
        return hupaxDao.selectHupax(req);
    }
}
