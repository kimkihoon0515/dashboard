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

    @GetMapping("/showScanner") //전체 값 불러오기
    @Transactional(readOnly = true)
    @CrossOrigin("*")
    public List<scannerResponseFormat> showScanner()
    {
        ScannerSlideVo vo = new ScannerSlideVo();
        List<ScannerSlideVo> vList = scannerSlideListService.showScanner(vo);
        ArrayList<scannerResponseFormat> res = new ArrayList<scannerResponseFormat>();
        for (ScannerSlideVo target : vList){
            res.add(new scannerResponseFormat(target));
        }
        return res;
    }
    
    @PutMapping("/updateScanner")
    @Transactional(readOnly = false)
    @CrossOrigin(origins = "*")
    public String updateScanner() {    	
    	if(scannerSlideListService.deleteScanner() == 0) {
    		ScannerSlideVo vo = new ScannerSlideVo();
    		List<ScannerSlideVo> vList = scannerSlideListService.selectScanner(vo);

            for(ScannerSlideVo target : vList) {
                if(scannerSlideListService.insertScanner(target) == 0){

                }
                else
                    return "false";
            }
    	}
    	return "ok";
    }
    
    @PostMapping("/searchScannerListByDate")
    @Transactional(readOnly = true)
    @CrossOrigin(origins = "*")
    public List<scannerResponseFormat> searchScannerListByDate(@RequestBody DateRequestFormat req){
    	if(req.getStartDate() == null || req.getFinishDate() == null) {
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

