package com.humintecTest.dashboard.controller;

import com.humintecTest.dashboard.request.DateRequestFormat;
import com.humintecTest.dashboard.response.pidchResponseFormat;
import com.humintecTest.dashboard.service.PidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
public class PidController {
    @Autowired
    PidService pidService;

    @PostMapping("/searchPidByDate") //날짜를 parameter로 받아서 해당 기간 내 Pid 데이터를 쿼리해오는 api
    @Transactional(readOnly = true)
    public List<pidchResponseFormat> searchPid (@RequestBody DateRequestFormat req){
        if(req.getFinishDate() == null || req.getStartDate() == null) { //날짜가 입력안되었을 경우 전체 데이터를 보냄.
            List<pidchResponseFormat> vList = pidService.searchPid();

            return vList;
        }

        List<pidchResponseFormat> vList = pidService.searchPidByDate(req);

        return vList;
    }

    @PutMapping("/updatePid")
    @Transactional(readOnly = false)
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