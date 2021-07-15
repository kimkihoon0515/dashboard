package com.humintecTest.dashboard.service;

import com.humintecTest.dashboard.dao.SlideListDao;
import com.humintecTest.dashboard.vo.SlideListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlideListServiceImpl implements SlideListService{
    @Autowired
    SlideListDao slideListDao;

    @Override
    public List<SlideListVo> selectSlideList(SlideListVo vo) {
        return slideListDao.selectSlideList(vo);
    }
}
