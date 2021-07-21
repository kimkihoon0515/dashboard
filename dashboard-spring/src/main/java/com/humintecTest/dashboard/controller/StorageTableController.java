package com.humintecTest.dashboard.controller;

import com.humintecTest.dashboard.dao.StorageTableDao;
import com.humintecTest.dashboard.response.storageTableResponseFormat;
import com.humintecTest.dashboard.service.StorageTableService;
import com.humintecTest.dashboard.vo.StorageTableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class StorageTableController {
    @Autowired
    StorageTableService storageTableService;

    @GetMapping("/selectStorageTable")
    @CrossOrigin(origins = "*")
    public List<storageTableResponseFormat> selectStorageTable(){
        StorageTableVo vo1 = new StorageTableVo();
        List<StorageTableVo> vList = storageTableService.selectStorageTable(vo1);
        ArrayList<storageTableResponseFormat> res = new ArrayList<storageTableResponseFormat>();
        for(StorageTableVo target : vList){
            res.add(new storageTableResponseFormat(target));
        }
        return res;
    }

    @PutMapping("/insertStorageTable")
    @CrossOrigin(origins = "*")
    public String insertStorageTable(StorageTableVo vo){
        List<StorageTableVo> vList = storageTableService.selectStorageTable(vo);

        for(StorageTableVo target : vList){
            if(storageTableService.insertStorageTable(target)== 0){
            }
            else
                return "false";
        }
        return "ok";
    }

    @PutMapping("/deleteStorageTable")
    @CrossOrigin(origins = "*")
    public String deleteStorageTable(StorageTableVo vo){
        if(storageTableService.deleteStorageTable(vo)==1){

        }
        else {
            return "false";
        }
        return "ok";
    }
}
