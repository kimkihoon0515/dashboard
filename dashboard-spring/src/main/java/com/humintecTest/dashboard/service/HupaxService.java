package com.humintecTest.dashboard.service;

import com.humintecTest.dashboard.request.HupaxRequestFormat;
import com.humintecTest.dashboard.vo.HupaxVo;

import java.util.List;

public interface HupaxService {
    List<HupaxVo> selectHupax(HupaxRequestFormat req);
}
