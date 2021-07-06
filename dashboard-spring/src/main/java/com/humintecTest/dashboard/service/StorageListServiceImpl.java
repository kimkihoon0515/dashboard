package com.humintecTest.dashboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humintecTest.dashboard.vo.StorageListVo;
import com.humintecTest.dashboard.dao.StorageListDao;

@Service
public class StorageListServiceImpl implements StorageListService{
	@Autowired
	StorageListDao storageListDao;
	
	@Override
	public List<StorageListVo> selectStorageList(StorageListVo vo){
		return storageListDao.selectStorageList(vo);
	}
}
