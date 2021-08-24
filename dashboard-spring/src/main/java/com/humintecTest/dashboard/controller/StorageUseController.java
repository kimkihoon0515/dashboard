package com.humintecTest.dashboard.controller;


import com.humintecTest.dashboard.request.DateRequestFormat;
import com.humintecTest.dashboard.response.storageUseResponseFormat;
import com.humintecTest.dashboard.service.StorageUseService;
import com.humintecTest.dashboard.vo.StorageUseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
public class StorageUseController {
    @Autowired
    StorageUseService storageUseService;

    @GetMapping("/showStorageUse") //전체 데이터
    @Transactional(readOnly = true)
    public List<storageUseResponseFormat> showStorageUse () {
        StorageUseVo vo = new StorageUseVo();
        List<StorageUseVo> vList = storageUseService.showStorageUse(vo);
        ArrayList<storageUseResponseFormat> res = new ArrayList<storageUseResponseFormat>();
        for (StorageUseVo target : vList)
        {
            res.add(new storageUseResponseFormat(target));
        }
        return res;
    }


    @PostMapping("/searchStorageUseByDate")
    @Transactional(readOnly = true)
    public List<storageUseResponseFormat> searchStorageUseByDate(@RequestBody DateRequestFormat req){
        List<storageUseResponseFormat> vList;

        if(req.getStartDate() == null || req.getFinishDate() == null) {
            vList = storageUseService.selectStorageUseNP(req);

            return vList;
        }

        if(req.getType() == 1) {
            vList = storageUseService.selectStorageUseByYear(req);
        }
        else if(req.getType() == 2) {
            vList = storageUseService.selectStorageUseByMonth(req);
        }
        else {
            vList = storageUseService.selectStorageUseByDate(req);
        }

        return vList;
    }

    @PutMapping("/updateStorageUse")
    @Transactional(readOnly = false)
    public String updateStorageUse() {
        if(storageUseService.updateStorageUse() == 0) {
            return "ok";
        }
        else {
            return "delete fail";
        }

    }

}