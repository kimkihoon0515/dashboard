package com.humintecTest.dashboard.controller;

import com.humintecTest.dashboard.response.storageUseMonthResponseFormat;
import com.humintecTest.dashboard.response.storageUseResponseFormat;
import com.humintecTest.dashboard.response.storageUseYearResponseFormat;
import com.humintecTest.dashboard.service.StorageUseService;
import com.humintecTest.dashboard.vo.StorageUseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
public class StorageUseController {
    @Autowired
    StorageUseService storageUseService;

    @GetMapping("/selectStorageUse")
    public List<storageUseResponseFormat> selectStorageUse (StorageUseVo vo){
        StorageUseVo vo1 = new StorageUseVo();
        List<StorageUseVo> vList = storageUseService.selectStorageUse(vo1);
        ArrayList<storageUseResponseFormat> res = new ArrayList<storageUseResponseFormat>();
        for (StorageUseVo target : vList)
        {
            res.add(new storageUseResponseFormat(target));
        }
        return res;
    }

    @GetMapping("/showStorageUse")
    public List<storageUseResponseFormat> showStorageUse (StorageUseVo vo) {
        StorageUseVo vo1 = new StorageUseVo();
        List<StorageUseVo> vList = storageUseService.showStorageUse(vo1);
        ArrayList<storageUseResponseFormat> res = new ArrayList<storageUseResponseFormat>();
        for (StorageUseVo target : vList)
        {
            res.add(new storageUseResponseFormat(target));
        }
        return res;
    }

    @PutMapping("/insertStorageUse")
    public String insertStorageUse (StorageUseVo vo)
    {
        List<StorageUseVo> vList = storageUseService.selectStorageUse(vo);

        for(StorageUseVo target : vList)
        {
            if(storageUseService.insertStorageUse(target)==0)
            {

            }
            else
            {
                return "false";
            }
        }
        return "ok";
    }

    @PutMapping("deleteStorageUse")
    public String deleteStorageUse(StorageUseVo vo)
    {
        if(storageUseService.deleteStorageUse(vo)==1)
        {

        }
        else {
            return "false";
        }
        return "ok";
    }

    @GetMapping("/selStorageMonth")
    public List<storageUseMonthResponseFormat> selStorageMonth (StorageUseVo vo)
    {
        StorageUseVo vo1 = new StorageUseVo();
        List<StorageUseVo> vList = storageUseService.selStorageMonth(vo1);
        ArrayList<storageUseMonthResponseFormat> res = new ArrayList<storageUseMonthResponseFormat>();

        for(StorageUseVo target : vList)
        {
            res.add(new storageUseMonthResponseFormat(target));
        }
        return res;
    }

    @GetMapping("/selStorageYear")
    public List<storageUseYearResponseFormat> selStorageYear(StorageUseVo vo)
    {
        StorageUseVo vo1 = new StorageUseVo();
        List<StorageUseVo> vList = storageUseService.selStorageYear(vo1);
        ArrayList<storageUseYearResponseFormat> res = new ArrayList<storageUseYearResponseFormat>();
        for (StorageUseVo target : vList){
            res.add(new storageUseYearResponseFormat(target));
        }
        return res;
    }

   /* @GetMapping("searchStorageUse")
    public List<storageUseResponseFormat> searchStorageUse (StorageUseVo vo)
    {

    }*/
}
