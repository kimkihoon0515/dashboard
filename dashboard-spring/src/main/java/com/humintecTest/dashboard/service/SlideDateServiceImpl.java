package com.humintecTest.dashboard.service;

import com.humintecTest.dashboard.dao.SlideDateDao;
import com.humintecTest.dashboard.request.DateRequestFormat;
import com.humintecTest.dashboard.response.slideTypeResponseFormat;
import com.humintecTest.dashboard.vo.SlideDateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlideDateServiceImpl implements SlideDateService{
    @Autowired
    SlideDateDao slideDateDao;

    @Override
    public List<SlideDateVo> selectSlideDate(SlideDateVo vo) { //slide_list로부터 slide_per_date에 넣을 데이터를 불러오는 메소드.
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
    
    public int updateSlideDate() { // delete 후에 insert 하는 방식으로 slide_per_date table을 update한다.
    	if(this.deleteSlideDate()== 0){
			SlideDateVo vo = new SlideDateVo();
			List<SlideDateVo> vList = this.selectSlideDate(vo);

			for(SlideDateVo target : vList) {
				if(this.insertSlideDate(target) == 0) {
				}
				else {
					return -1;
				}
			}
		}
		else {
			return -1;
		}

		return 0;
    }

    @Override
    public List<slideTypeResponseFormat> selectSlidePerDateNP(DateRequestFormat req) { //parameter에 기간이 들어오지 않았을 때 전체데이터를 불러오는 메소드.
        if(req.getType() == 1) { //연
            return slideDateDao.selectSlidePerDateByYearNP();
        }
        else if(req.getType() == 2) { //월
            return slideDateDao.selectSlidePerDateByMonthNP();
        }
        else { //일
            return slideDateDao.selectSlidePerDateByDateNP();
        }
    }

    @Override
    public List<slideTypeResponseFormat> selectSlidePerDateByDate(DateRequestFormat req) { //parameter에 기간과 type이 3일 때 일 단위로 데이터를 불러오는 메소드.
        return slideDateDao.selectSlidePerDateByDate(req);
    }

    @Override
    public List<slideTypeResponseFormat> selectSlidePerDateByMonth(DateRequestFormat req) { //parameter에 기간과 type이 2일 때 월 단위로 데이터를 불러오는 메소드.
        return slideDateDao.selectSlidePerDateByMonth(req);
    }

    @Override
    public List<slideTypeResponseFormat> selectSlidePerDateByYear(DateRequestFormat req) { //parameter에 기간과 type이 1일 때 연 단위로 데이터를 불러오는 메소드.
        return slideDateDao.selectSlidePerDateByYear(req);
    }
}