package com.humintecTest.dashboard.controller;

import com.humintecTest.dashboard.service.PidService;
import com.humintecTest.dashboard.vo.PidVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PidController {
    @Autowired
    PidService pidService;

    @RequestMapping("pid")
    public @ResponseBody List<PidVo> insertPid(@ModelAttribute("searchVo") PidVo vo, ModelMap modelMap) throws Exception{
        List<PidVo> vlist = pidService.insertPid(vo);
        return vlist;
    }

}
