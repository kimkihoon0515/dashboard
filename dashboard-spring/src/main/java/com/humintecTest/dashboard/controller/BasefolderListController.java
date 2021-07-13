package com.humintecTest.dashboard.controller;

import com.humintecTest.dashboard.service.BasefolderListService;
import com.humintecTest.dashboard.vo.BasefolderListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BasefolderListController {

    @Autowired
    BasefolderListService basefolderListService;

    @RequestMapping("/basefolderList")
    public @ResponseBody List<BasefolderListVo> selectBasefolderList(@ModelAttribute("searchVo") BasefolderListVo vo, ModelMap modelMap) throws Exception {
        List<BasefolderListVo> vList = basefolderListService.selectBasefolderList(vo);
        return vList;
    }
}
