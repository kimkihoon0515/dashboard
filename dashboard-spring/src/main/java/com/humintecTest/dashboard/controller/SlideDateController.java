package com.humintecTest.dashboard.controller;

import com.humintecTest.dashboard.request.DateRequestFormat;
import com.humintecTest.dashboard.response.slideDateResponseFormat;
import com.humintecTest.dashboard.response.slideTypeResponseFormat;
import com.humintecTest.dashboard.response.slideYearResponseFormat;
import com.humintecTest.dashboard.service.SlideDateService;
import com.humintecTest.dashboard.vo.SlideDateVo;

import ch.qos.logback.core.net.SyslogOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class SlideDateController {
    @Autowired
    SlideDateService slideDateService;

    @PostMapping("/selectSlidePerDate")
    @Transactional(readOnly = true)
    @CrossOrigin("*")
    public List<slideTypeResponseFormat> selectSlidePerDate(@RequestBody DateRequestFormat req) {
    	List<slideTypeResponseFormat> vList;
    	//연
    	if(req.getType() == 1) {
    		vList = slideDateService.selectSlidePerDateByYear(req);    	

    	}
    	//월
    	else if(req.getType() == 2) {
    		vList = slideDateService.selectSlidePerDateByMonth(req);
        	
    	}
    	//일
    	else {
    		vList = slideDateService.selectSlidePerDateByDate(req);
        	
    	}
    		
    	return vList;
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
    	else {
    		return "false";

    	}
    	
    	return "ok";
    }
  
    @GetMapping("/showSlideDate") //전체 데이터
    public List<slideDateResponseFormat> showSlideDate(SlideDateVo vo)
    {
       SlideDateVo vo1 = new SlideDateVo();
       List<SlideDateVo> vList = slideDateService.showSlideDate(vo1);
       ArrayList<slideDateResponseFormat> res = new ArrayList<slideDateResponseFormat>();
       for (SlideDateVo target : vList){
           res.add(new slideDateResponseFormat(target));
       }
       return res;
    }


}

