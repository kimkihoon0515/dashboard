package com.humintecTest.dashboard.service;

import com.humintecTest.dashboard.dao.SlideDateDao;
import com.humintecTest.dashboard.request.DateRequestFormat;
import com.humintecTest.dashboard.response.slideTypeResponseFormat;
import com.humintecTest.dashboard.vo.SlideDateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
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
    public int deleteSlideDate() {
        try {
            slideDateDao.deleteSlideDate();
            return 0;
        }catch (Exception e){
            return -1;
        }
    }
    
    @Override
	public List<slideTypeResponseFormat> selectSlidePerDateNP(DateRequestFormat req) {
		if(req.getType() == 1) {
			return slideDateDao.selectSlidePerDateByYearNP();
		}
		else if(req.getType() == 2) {
			return slideDateDao.selectSlidePerDateByMonthNP();
		}
		else {
			return slideDateDao.selectSlidePerDateByDateNP();
		}
	}

	@Override
	public List<slideTypeResponseFormat> selectSlidePerDateByDate(DateRequestFormat req) {
		return slideDateDao.selectSlidePerDateByDate(req);
	}
	
	@Override
	public List<slideTypeResponseFormat> selectSlidePerDateByMonth(DateRequestFormat req) {
		return slideDateDao.selectSlidePerDateByMonth(req);
	}
	
	@Override
	public List<slideTypeResponseFormat> selectSlidePerDateByYear(DateRequestFormat req) {
		return slideDateDao.selectSlidePerDateByYear(req);
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
