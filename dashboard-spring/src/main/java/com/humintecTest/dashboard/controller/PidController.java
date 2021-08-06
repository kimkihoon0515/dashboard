package com.humintecTest.dashboard.controller;

import com.humintecTest.dashboard.request.DateRequestFormat;
import com.humintecTest.dashboard.response.pidchResponseFormat;
import com.humintecTest.dashboard.service.PidService;
import com.humintecTest.dashboard.vo.PidVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
public class PidController {
    @Autowired
    PidService pidService;

    @PostMapping("/searchPidByDate")
    @Transactional(readOnly = true)
    @CrossOrigin(origins = "*")
    public List<pidchResponseFormat> searchPid (@RequestBody DateRequestFormat req){
        if(req.getFinishDate() == null || req.getStartDate() == null) {
            List<pidchResponseFormat> vList = pidService.searchPid();

            return vList;
        }

        List<pidchResponseFormat> vList = pidService.searchPidByDate(req);

        return vList;
    }

    @PutMapping("/updatePid")
    @Transactional(readOnly = false)
    @CrossOrigin("*")
    public String updatePid() {
        int check;
        check = pidService.updatePid();
        
        if(check == 0) {
        	return "ok";
        }
        else {
        	return "false";
        }

    }
}