package com.humintecTest.dashboard.controller;

import com.humintecTest.dashboard.dao.StorageTableDao;
import com.humintecTest.dashboard.response.storageTableResponseFormat;
import com.humintecTest.dashboard.service.StorageTableService;
import com.humintecTest.dashboard.vo.StorageTableVo;
import com.humintecTest.dashboard.vo.StorageUseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class StorageTableController {
    @Autowired
    StorageTableService storageTableService;

    @GetMapping("/selectStorageTable") //사용안함
    @CrossOrigin(origins = "*")
    public List<StorageTableVo> selectStorageTable(StorageTableVo vo){
        List<StorageTableVo> vList = storageTableService.selectStorageTable(vo);
        return vList;
    }

    @PutMapping("/insertStorageTable")  //사용안함
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
        if(storageTableService.deleteStorageTable()==0) {
        }
        else {
            return "false";
        }
        return "ok";
    }

    @PutMapping("/updateStorageTable")
    @Transactional(readOnly = false)
    @CrossOrigin(origins = "*")
    public String updateStorageTable() {
        StorageTableVo vo = new StorageTableVo();
        if(storageTableService.deleteStorageTable()==0){
            List<StorageTableVo> vList = storageTableService.selectStorageTable(vo);

            for(StorageTableVo target : vList){
                if(storageTableService.insertStorageTable(target)== 0){
                }
                else {
                    return "false";
                }
            }
        }
        else {
            return "false";
        }
        return "ok";
    }
}