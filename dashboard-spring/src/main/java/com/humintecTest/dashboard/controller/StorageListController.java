package com.humintecTest.dashboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.humintecTest.dashboard.service.StorageListService;
import com.humintecTest.dashboard.vo.StorageListVo;

@RestController
public class StorageListController {
	@Autowired
	StorageListService storageListService;
	
	@RequestMapping("/storageList")
	public @ResponseBody List<StorageListVo> selectStorageList(@ModelAttribute("searchVo") StorageListVo vo, ModelMap modelMap) throws Exception{
		List<StorageListVo> vList = storageListService.selectStorageList(vo);
		
		return vList;
	}
}
