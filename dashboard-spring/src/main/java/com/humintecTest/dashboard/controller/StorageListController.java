package com.humintecTest.dashboard.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.humintecTest.dashboard.request.storageListRequestFormat;
import com.humintecTest.dashboard.response.storageListResponseFormat;
import com.humintecTest.dashboard.service.StorageListService;
import com.humintecTest.dashboard.vo.StorageListVo;

@RestController
public class StorageListController {
	@Autowired
	StorageListService storageListService;
	
	@GetMapping("/storageList")
	@Transactional(readOnly = true)
	public List<storageListResponseFormat> selectStorageList(@RequestHeader("Authorization") @RequestBody storageListRequestFormat req){
		StorageListVo vo = new StorageListVo();
		List<StorageListVo> vList = storageListService.selectStorageList(vo);
		
		ArrayList<storageListResponseFormat> resList = new ArrayList<storageListResponseFormat>();
		
		for(StorageListVo selectVo : vList) {
			resList.add(new storageListResponseFormat(selectVo));
		}
		
		return resList;
	}
	
	@GetMapping("/requestCheck")
	@Transactional(readOnly = true)
	public int requestCheck(@RequestHeader("Authorization") @RequestBody storageListRequestFormat req){
		
		return req.getCheck();
	}
}
