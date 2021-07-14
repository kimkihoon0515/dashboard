package com.humintecTest.dashboard.controller;

import com.humintecTest.dashboard.service.ScannerSlideListService;
import com.humintecTest.dashboard.vo.ScannerSlideVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ScannerSlideListController {

    @Autowired
    ScannerSlideListService scannerSlideListService;

    @RequestMapping("/insert")
    public @ResponseBody List<ScannerSlideVo> insertScanner(@ModelAttribute("searchVo") ScannerSlideVo vo)throws Exception{
        List<ScannerSlideVo> vlist = scannerSlideListService.insertScanner(vo);
        return vlist;
    }
}
