package com.humintecTest.dashboard.controller;

import com.humintecTest.dashboard.response.storageDateResponseFormat;
import com.humintecTest.dashboard.service.StorageDateService;
import com.humintecTest.dashboard.vo.SlideDateVo;
import com.humintecTest.dashboard.vo.StorageDateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class StorageDateController {
    @Autowired
    StorageDateService storageDateService;
    
    @PutMapping("/updateStoragePerDate")
    @Transactional(readOnly = false)
	@CrossOrigin(origins = "*")
    public String updateStoragePerDate() {
    	if(storageDateService.deleteStorageDate()== 0){
    		StorageDateVo vo = new StorageDateVo();
    		List<StorageDateVo> vList = storageDateService.selectStorageDate(vo);
    		
    		for(StorageDateVo target : vList) {
    			if(storageDateService.insertStorageDate(target) == 0) {
    				
    			}
    			else {
    				return "false";
    			}
    		}
    	}
    	
    	return "ok";
    }

}
