package com.humintecTest.dashboard.controller;

import com.humintecTest.dashboard.service.SlideListService;
import com.humintecTest.dashboard.vo.SlideListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SlideListController {
    @Autowired
    SlideListService slideListService;

    @RequestMapping("/slideList")
    public @ResponseBody List<SlideListVo> selectSlideList(@ModelAttribute("searchVo") SlideListVo vo, ModelMap modelMap) throws Exception {
        List<SlideListVo> vList = slideListService.selectSlideList(vo);
        return vList;
    }
}
