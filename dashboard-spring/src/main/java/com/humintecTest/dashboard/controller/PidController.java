package com.humintecTest.dashboard.controller;

import com.humintecTest.dashboard.response.pidResponseFormat;
import com.humintecTest.dashboard.service.PidService;
import com.humintecTest.dashboard.vo.PidVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @PutMapping("insertPid")
    public String insertPid(PidVo vo) {
        List<PidVo> vList = pidService.selectPid(vo);

        for (PidVo target : vList){
            if(pidService.insertPid(target) == 0){

            }
            else
                return "false";
        }
        return "ok";
    }

    @PutMapping("deletePid")
    public String deletePid(PidVo vo) {
        if(pidService.deletePid(vo) == 0) {
            return "false";
        }
        else
            return "ok";
    }
}
