package com.humintecTest.dashboard.controller;

import com.humintecTest.dashboard.service.BasefolderListService;
import com.humintecTest.dashboard.vo.BasefolderListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/basefolderList")
public class BasefolderListController {

    @Autowired
    BasefolderListService basefolderListService;
    @PutMapping("/insert")
    public List<BasefolderListVo> selectBasefolderList(@ModelAttribute("searchVo") BasefolderListVo vo, ModelMap modelMap) throws Exception {
        List<BasefolderListVo> vList = basefolderListService.selectBasefolderList(vo);
        return vList;
    }
}
