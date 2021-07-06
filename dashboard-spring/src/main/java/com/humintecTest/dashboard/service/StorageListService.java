package com.humintecTest.dashboard.service;

import java.util.List;

import com.humintecTest.dashboard.vo.StorageListVo;

public interface StorageListService {
	List<StorageListVo> selectStorageList(StorageListVo vo);
}
