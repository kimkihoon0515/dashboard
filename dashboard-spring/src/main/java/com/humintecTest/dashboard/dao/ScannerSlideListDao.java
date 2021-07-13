package com.humintecTest.dashboard.dao;

import org.apache.ibatis.session.SqlSession;

public class ScannerSlideListDao {
    public int insertScannerSlideList(SqlSession session) {
        int result  = session.insert("com.humintecTest.dashboard.dao.ScannerSlideListDao.insertScannerSlideList");
        return result;
    }
}
