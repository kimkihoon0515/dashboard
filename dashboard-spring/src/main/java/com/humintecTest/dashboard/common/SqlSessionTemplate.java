package com.humintecTest.dashboard.common;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionTemplate {
    public static SqlSession getSession() {
        String resource = "/application.properties";
        SqlSession session = null;

        try {
            InputStream is = Resources.getResourceAsStream(resource);
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = builder.build(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return session;
    }
}
