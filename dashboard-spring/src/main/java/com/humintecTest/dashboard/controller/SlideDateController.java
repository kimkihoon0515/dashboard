package com.humintecTest.dashboard.controller;

import com.humintecTest.dashboard.response.slideDateResponseFormat;
import com.humintecTest.dashboard.service.SlideDateService;
import com.humintecTest.dashboard.vo.SlideDateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class SlideDateController {
    @Autowired
    SlideDateService slideDateService;

    @GetMapping("/selectSlideDate")
    public List<slideDateResponseFormat> selectSlideDate(SlideDateVo vo){
        SlideDateVo vo1 = new SlideDateVo();
        List<SlideDateVo> vList = slideDateService.selectSlideDate(vo1);
        ArrayList<slideDateResponseFormat> res = new ArrayList<slideDateResponseFormat>();

        for(SlideDateVo target : vList){
            res.add(new slideDateResponseFormat(target));
        }
        return res;
    }

    @PutMapping("/insertSlideDate")
    public String insertSlideDate(SlideDateVo vo) {
        List<SlideDateVo> vList = slideDateService.selectSlideDate(vo);
        for (SlideDateVo target : vList) {
            if (slideDateService.insertSlideDate(target) == 0) {

            } else
                return "false";
        }
        return "ok";
    }

    @PutMapping("/deleteSlideDate")
    public String deleteSlideDate(SlideDateVo vo){
        if(slideDateService.deleteSlideDate(vo)== 1){

        }
        else{
            return "false";
        }
        return "ok";
    }
    @GetMapping("/showSlideDate")
    public List<SlideDateVo> showSlideDate(SlideDateVo vo)
    {
        List<SlideDateVo> vList = slideDateService.showSlideDate(vo);
        return vList;
    }
}
