package com.humintecTest.dashboard.controller;

import com.humintecTest.dashboard.response.pidMonthResponseFormat;
import com.humintecTest.dashboard.response.pidResponseFormat;
import com.humintecTest.dashboard.response.pidShowResponseFormat;
import com.humintecTest.dashboard.response.pidYearResponseFormat;
import com.humintecTest.dashboard.service.PidService;
import com.humintecTest.dashboard.vo.PidVo;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PutMapping("/insertPid")
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

    @PutMapping("/deletePid")
    public String deletePid(PidVo vo) {
        if(pidService.deletePid(vo) == 1) {
            return "false";
        }
        else
            return "ok";
    }

    @GetMapping("/searchPid")
    public List<PidVo> searchPid (PidVo vo){
        vo.setStart_date("2014-09-02");
        vo.setEnd_date("2020-12-09");
        List<PidVo> vList = pidService.searchPid(vo);
        return vList;
    }

    @GetMapping("/showPid")
    public List<pidShowResponseFormat> showPid (PidVo vo) {
        PidVo vo1 = new PidVo();
        List<PidVo> vList = pidService.showPid(vo1);
        ArrayList<pidShowResponseFormat> res = new ArrayList<pidShowResponseFormat>();
        for (PidVo target : vList) {
            res.add(new pidShowResponseFormat(target));
        }
        return res;
    }

    @GetMapping("/selPidMonth")
    public List<pidMonthResponseFormat> selMonth (PidVo vo)
    {
        PidVo vo1 = new PidVo();
        List<PidVo> vList = pidService.selPidMonth(vo1);
        ArrayList<pidMonthResponseFormat> res = new ArrayList<pidMonthResponseFormat>();
        for (PidVo target : vList){
            res.add(new pidMonthResponseFormat(target));
        }
        return res;
    }

    @GetMapping("/selPidYear")
    public List<pidYearResponseFormat> selYear (PidVo vo)
    {
        PidVo vo1 = new PidVo();
        List<PidVo> vList = pidService.selPidYear(vo1);
        ArrayList<pidYearResponseFormat> res = new ArrayList<pidYearResponseFormat>();
        for (PidVo target : vList)
        {
            res.add(new pidYearResponseFormat(target));
        }
        return res;
    }
}

