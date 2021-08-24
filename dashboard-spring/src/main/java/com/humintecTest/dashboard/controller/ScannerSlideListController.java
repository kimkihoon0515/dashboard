package com.humintecTest.dashboard.controller;

import com.humintecTest.dashboard.request.DateRequestFormat;
import com.humintecTest.dashboard.response.scannerResponseFormat;
import com.humintecTest.dashboard.service.ScannerSlideListService;
import com.humintecTest.dashboard.vo.ScannerSlideVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ScannerSlideListController {

    @Autowired
    ScannerSlideListService scannerSlideListService;

    @PutMapping("/updateScanner")
    @Transactional(readOnly = false)
    public String updateScanner() {
        if(scannerSlideListService.updateScanner() == 0) {
           return "false"; 
        }
        return "ok";
    }

    @PostMapping("/searchScannerListByDate")
    @Transactional(readOnly = true)
    public List<scannerResponseFormat> searchScannerListByDate(@RequestBody DateRequestFormat req){ //날짜를 parameter로 받아서 해당 기간 내 Scanner_list 데이터를 쿼리해오는 api
        if(req.getStartDate() == null || req.getFinishDate() == null) { //날짜가 입력안되었을 경우 전체 데이터를 보냄.
            List<ScannerSlideVo> vList = scannerSlideListService.searchScannerList();

            ArrayList<scannerResponseFormat> res = new ArrayList<scannerResponseFormat>();

            for(ScannerSlideVo target : vList) {
                res.add(new scannerResponseFormat(target));
            }

            return res;
        }

        List<ScannerSlideVo> vList = scannerSlideListService.searchScannerListByDate(req);

        ArrayList<scannerResponseFormat> res = new ArrayList<scannerResponseFormat>();

        for(ScannerSlideVo target : vList) {
            res.add(new scannerResponseFormat(target));
        }

        return res;
    }
}