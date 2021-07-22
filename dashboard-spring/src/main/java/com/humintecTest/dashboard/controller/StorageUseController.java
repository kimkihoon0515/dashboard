package com.humintecTest.dashboard.controller;

import com.humintecTest.dashboard.response.storageUseResponseFormat;
import com.humintecTest.dashboard.service.StorageUseService;
import com.humintecTest.dashboard.vo.StorageUseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<StorageUseVo> showStorageUse (StorageUseVo vo) {
        List<StorageUseVo> vList = storageUseService.showStorageUse(vo);
        return vList;
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

   /* @GetMapping("searchStorageUse")
    public List<storageUseResponseFormat> searchStorageUse (StorageUseVo vo)
    {

    }*/
}
