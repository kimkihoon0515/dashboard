package com.humintecTest.dashboard.controller;

import com.humintecTest.dashboard.request.DateRequestFormat;
import com.humintecTest.dashboard.response.pidResponseFormat;
import com.humintecTest.dashboard.response.pidShowResponseFormat;
import com.humintecTest.dashboard.response.pidchResponseFormat;
import com.humintecTest.dashboard.service.PidService;
import com.humintecTest.dashboard.vo.PidVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
public class PidController {
    @Autowired
    PidService pidService;

    @GetMapping("/selectPid")
    public List<pidResponseFormat> selectPid(PidVo vo) {

        PidVo vo1 = new PidVo();
        List<PidVo> vList = pidService.selectPid(vo1);
        ArrayList<pidResponseFormat> res = new ArrayList<pidResponseFormat>();
        for (PidVo target : vList){
            res.add(new pidResponseFormat(target));
        }

        return res;
    }

    
    @PostMapping("/searchPidByDate")
    @Transactional(readOnly = true)
    @CrossOrigin(origins = "*")
    public List<pidchResponseFormat> searchPid (@RequestBody DateRequestFormat req){
        List<pidchResponseFormat> vList = pidService.searchPidByDate(req);
        
        return vList;
    }
    
    @PutMapping("/updatePid")
    @Transactional(readOnly = false)
    @CrossOrigin("*")
    public String updatePid() {
    	 if(pidService.deletePid() == 0) {
    		 PidVo vo = new PidVo();
    		 List<PidVo> vList = pidService.selectPid(vo);

    	        for (PidVo target : vList){
    	            if(pidService.insertPid(target) == 0){

    	            }
    	            else
    	                return "false";
    	        }
         }
         else {
        	 return "false";
         }
    	 
    	 return "ok";

    }
}

