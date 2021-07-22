package com.humintecTest.dashboard.controller;

import com.humintecTest.dashboard.request.DateRequestFormat;
import com.humintecTest.dashboard.response.slideDateResponseFormat;
import com.humintecTest.dashboard.response.slideMonthResponseFormat;
import com.humintecTest.dashboard.response.slideYearResponseFormat;
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

@CrossOrigin(origins = "*")
@RestController
public class SlideDateController {
    @Autowired
    SlideDateService slideDateService;


    @GetMapping("/selectSlidePerDate")
    @Transactional(readOnly = true)
    @CrossOrigin("*")
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
    @CrossOrigin("*")
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

  
    @GetMapping("/showSlideDate")
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

    @GetMapping("/selMonth")
    public List<slideMonthResponseFormat> selMonth(SlideDateVo vo)
    {
        SlideDateVo vo1 = new SlideDateVo();
        List<SlideDateVo> vList = slideDateService.selMonth(vo1);
        ArrayList<slideMonthResponseFormat> res = new ArrayList<slideMonthResponseFormat>();
        for (SlideDateVo target : vList) {
            res.add(new slideMonthResponseFormat(target));
        }
        return res;
    }

    @GetMapping("/selYear")
    public List<slideYearResponseFormat> selYear(SlideDateVo vo)
    {
        SlideDateVo vo1 = new SlideDateVo();
        List<SlideDateVo> vList = slideDateService.selYear(vo1);
        ArrayList<slideYearResponseFormat> res = new ArrayList<slideYearResponseFormat>();
        for (SlideDateVo target : vList) {
            res.add(new slideYearResponseFormat(target));
        }
        return res;
    }
}

