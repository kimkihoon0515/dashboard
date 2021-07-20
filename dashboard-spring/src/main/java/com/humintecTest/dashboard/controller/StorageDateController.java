package com.humintecTest.dashboard.controller;

import com.humintecTest.dashboard.response.storageDateResponseFormat;
import com.humintecTest.dashboard.service.StorageDateService;
import com.humintecTest.dashboard.vo.StorageDateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class StorageDateController {
    @Autowired
    StorageDateService storageDateService;

    @GetMapping("/selectStorageDate")
    public List<storageDateResponseFormat> selectStorageDate(StorageDateVo vo){
        StorageDateVo vo1 = new StorageDateVo();
        List<StorageDateVo> vList = storageDateService.selectStorageDate(vo1);
        ArrayList<storageDateResponseFormat> res = new ArrayList<storageDateResponseFormat>();

        for(StorageDateVo target : vList){
            res.add(new storageDateResponseFormat(target));
        }
        return res;
    }

    @PutMapping("/insertStorageDate")
    public String insertStorageDate(StorageDateVo vo){
        List<StorageDateVo> vList = storageDateService.selectStorageDate(vo);

        for(StorageDateVo target : vList){
            if(storageDateService.insertStorageDate(target)==0){

            }
            else {
                return "false";
            }
        }
        return "ok";
    }

    @PutMapping("/deleteStorageDate")
    public String deleteStorageDate(StorageDateVo vo){
        if(storageDateService.deleteStorageDate(vo)==1){

        }
        else {
            return "false";
        }
        return "ok";
    }
}