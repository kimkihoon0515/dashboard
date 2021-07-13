package com.humintecTest.dashboard.service;

import com.humintecTest.dashboard.dao.ScannerSlideListDao;
import org.apache.ibatis.session.SqlSession;

import static com.humintecTest.dashboard.common.SqlSessionTemplate.getSession;

public class ScannerSlideListService {
    private ScannerSlideListDao scannerSlideListDao = new ScannerSlideListDao();

    public int insertScanner() {
        SqlSession session = getSession();
        int result =  scannerSlideListDao.insertScannerSlideList(session);

        if (result >0 ){
            session.commit();
        }else {
            session.rollback();
        }

        session.close();
        return result;
    }
}
