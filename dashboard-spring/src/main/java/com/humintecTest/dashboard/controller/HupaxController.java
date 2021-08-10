package com.humintecTest.dashboard.controller;

import com.humintecTest.dashboard.service.HupaxService;
import com.humintecTest.dashboard.vo.HupaxVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class HupaxController {
    @Autowired
    HupaxService hupaxService;
    @GetMapping("/selectHupax")
    public List<HupaxVo> selectHupax(HupaxVo vo){
        List<HupaxVo> vList = hupaxService.selectHupax(vo);
        return vList;
    }
}
