package com.humintecTest.dashboard.controller;

import com.humintecTest.dashboard.request.DateRequestFormat;
import com.humintecTest.dashboard.response.slideDateResponseFormat;
import com.humintecTest.dashboard.service.SlideDateService;
import com.humintecTest.dashboard.vo.SlideDateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SlideDateController {
    @Autowired
    SlideDateService slideDateService;

    @GetMapping("/selectSlidePerDate")
    @Transactional(readOnly = true)
	@CrossOrigin(origins = "*")
    public List<slideDateResponseFormat> selectSlidePerDate(@RequestBody DateRequestFormat req) {
    	List<SlideDateVo> vList = slideDateService.selectDateToDate(req);
    	
    	ArrayList<slideDateResponseFormat> res = new ArrayList<slideDateResponseFormat>();
    	
    	for(SlideDateVo target : vList) {
    		res.add(new slideDateResponseFormat(target));
    	}
    	
    	return res;
    }
    
    @PutMapping("/updateSlidePerDate")
    @Transactional(readOnly = false)
    @CrossOrigin(origins = "*")
    public String updateSlidePerDate() {
    	if(slideDateService.deleteSlideDate()== 0){
    		SlideDateVo vo = new SlideDateVo();
    		List<SlideDateVo> vList = slideDateService.selectSlideDate(vo);
    		
    		for(SlideDateVo target : vList) {
    			if(slideDateService.insertSlideDate(target) == 0) {
    				
    			}
    			else {
    				return "false";
    			}
    		}
    		
    	}
    	
    	return "ok";
    }
}
