package com.humintecTest.dashboard.service;

import com.humintecTest.dashboard.dao.BasefolderListDao;

import com.humintecTest.dashboard.vo.BasefolderListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasefolderListServiceImpl implements BasefolderListService{
    @Autowired
    BasefolderListDao basefolderListDao;

    @Override
    public List<BasefolderListVo> selectBasefolderList(BasefolderListVo vo){
        return basefolderListDao.selectBasefolderList(vo);
    }
}
