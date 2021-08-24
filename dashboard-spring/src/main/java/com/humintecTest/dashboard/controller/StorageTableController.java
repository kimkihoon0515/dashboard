package com.humintecTest.dashboard.controller;


import com.humintecTest.dashboard.request.StorageTableRequestFormat;
import com.humintecTest.dashboard.response.storageTableResponseFormat;
import com.humintecTest.dashboard.service.StorageTableService;
import com.humintecTest.dashboard.vo.StorageTableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class StorageTableController {
    @Autowired
    StorageTableService storageTableService;

    @PutMapping("/deleteStorageTable")
    @Transactional(readOnly = false)
    public String deleteStorageTable(StorageTableVo vo){
        if(storageTableService.deleteStorageTable()==0) {
        }
        else {
            return "false";
        }
        return "ok";
    }


    @PostMapping("/selectStorageTableById") //스토리지 별 일일 사용량 추이 계산
    @Transactional(readOnly = true)
    public List<storageTableResponseFormat> selectStorageTableById (@RequestBody StorageTableRequestFormat req )
    {

        List<StorageTableVo> vList = storageTableService.selectStorageTableById(req);



        ArrayList<storageTableResponseFormat> res = new ArrayList<storageTableResponseFormat>();

        for(StorageTableVo target : vList)
        {
            res.add(new storageTableResponseFormat(target));
        }
        return res;
    }

    @PutMapping("/updateStorageTable")
    @Transactional(readOnly = false)
    public String updateStorageTable() {
        if(storageTableService.updateStorageTable()==0){
            return "ok";
        }
        else {
            return "false";
        }
    }
}