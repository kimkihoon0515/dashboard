package com.humintecTest.dashboard.controller;

import com.humintecTest.dashboard.service.ScannerSlideListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScannerSlideListController {
    @Autowired
    ScannerSlideListService scannerSlideListService;
}
