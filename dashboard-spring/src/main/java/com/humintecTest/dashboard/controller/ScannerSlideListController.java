package com.humintecTest.dashboard.controller;

import com.humintecTest.dashboard.service.ScannerSlideListService;
import com.humintecTest.dashboard.vo.ScannerSlideVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class ScannerSlideListController {

    @Autowired
    ScannerSlideListService scannerSlideListService;

    @PostMapping("/insert")
    public @ResponseBody String insertScanner(@ModelAttribute("searchVo") ScannerSlideVo vo)throws Exception{
        if(scannerSlideListService.insertScanner(vo) == 0){
         return "ok";
        }
        else
            return "false";
    }
}
