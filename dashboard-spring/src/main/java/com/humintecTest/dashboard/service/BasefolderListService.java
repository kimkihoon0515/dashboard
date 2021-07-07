package com.humintecTest.dashboard.service;

import com.humintecTest.dashboard.vo.BasefolderListVo;

import java.util.List;

public interface BasefolderListService {
    List<BasefolderListVo> selectBasefolderList(BasefolderListVo vo);
}
