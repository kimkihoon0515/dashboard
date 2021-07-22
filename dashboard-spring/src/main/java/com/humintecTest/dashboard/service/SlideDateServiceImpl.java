package com.humintecTest.dashboard.service;

import com.humintecTest.dashboard.dao.SlideDateDao;
import com.humintecTest.dashboard.vo.SlideDateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlideDateServiceImpl implements SlideDateService{
    @Autowired
    SlideDateDao slideDateDao;

    @Override
    public List<SlideDateVo> selectSlideDate(SlideDateVo vo) {
        return slideDateDao.selectSlideDate(vo);
    }

    @Override
    public int insertSlideDate(SlideDateVo vo) {
        try {
            slideDateDao.insertSlideDate(vo);
            return 0;
        }catch (Exception e){
            return -1;
        }
    }

    @Override
    public int deleteSlideDate(SlideDateVo vo) {
        try {
            slideDateDao.deleteSlideDate(vo);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public List<SlideDateVo> showSlideDate(SlideDateVo vo) {
        return slideDateDao.showSlideDate(vo);
    }

    @Override
    public List<SlideDateVo> selMonth(SlideDateVo vo) {
        return slideDateDao.selMonth(vo);
    }

    @Override
    public List<SlideDateVo> selYear(SlideDateVo vo) {
        return slideDateDao.selYear(vo);
    }
}