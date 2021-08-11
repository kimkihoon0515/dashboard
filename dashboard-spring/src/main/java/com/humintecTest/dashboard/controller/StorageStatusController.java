package com.humintecTest.dashboard.controller;

import com.humintecTest.dashboard.service.StorageStatusService;
import com.humintecTest.dashboard.vo.StorageStatusVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class StorageStatusController {
    @Autowired
    StorageStatusService storageStatusService;
    @GetMapping("/selectStorageStatus")
    public List<StorageStatusVo> selectStorageStatus (StorageStatusVo vo)
    {
        List<StorageStatusVo> vList = storageStatusService.selectStorageStatus(vo);
        return vList;
    }
}
