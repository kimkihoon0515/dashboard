package com.humintecTest.dashboard.controller;

import com.humintecTest.dashboard.response.scannerResponseFormat;
import com.humintecTest.dashboard.service.ScannerSlideListService;
import com.humintecTest.dashboard.vo.ScannerSlideVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ScannerSlideListController {

    @Autowired
    ScannerSlideListService scannerSlideListService;

    @PutMapping("/insertScanner")
    public String insertScanner(ScannerSlideVo vo) {
        List<ScannerSlideVo> vList = scannerSlideListService.selectScanner(vo);

        for(ScannerSlideVo target : vList) {
            if(scannerSlideListService.insertScanner(target) == 0){

            }
            else
                return "false";
        }
        return "ok";
    }

    @GetMapping("/selectScanner")
    public List<scannerResponseFormat> selectScanner(ScannerSlideVo vo) {
        ScannerSlideVo vo1 = new ScannerSlideVo();
        List<ScannerSlideVo> vList = scannerSlideListService.selectScanner(vo1);
        ArrayList<scannerResponseFormat> res = new ArrayList<scannerResponseFormat>();

        for(ScannerSlideVo target : vList){
            res.add(new scannerResponseFormat(target));
        }
        return res;
    }

    @PutMapping("/deleteScanner")
    public String deleteScanner(ScannerSlideVo vo){
        if(scannerSlideListService.deleteScanner(vo) == 1){
        }
        else {
            return "false";
        }
        return "ok";
    }

    @GetMapping("/showScanner")
    public List<ScannerSlideVo> showScanner(ScannerSlideVo vo)
    {
        List<ScannerSlideVo> vList = scannerSlideListService.showScanner(vo);
        return vList;
    }
}
