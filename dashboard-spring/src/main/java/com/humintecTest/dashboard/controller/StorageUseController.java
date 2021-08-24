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

    @PostMapping("/searchStorageUseByDate")
    @Transactional(readOnly = true)
    public List<storageUseResponseFormat> searchStorageUseByDate(@RequestBody DateRequestFormat req){ //날짜를 parameter로 받아서 해당 기간 내 storage_use 데이터를 쿼리해오는 api
        List<storageUseResponseFormat> vList;

        if(req.getStartDate() == null || req.getFinishDate() == null) { //날짜가 입력안되었을 경우 전체 데이터를 보냄.
            vList = storageUseService.selectStorageUseNP(req);

            return vList;
        }

        if(req.getType() == 1) { //연
            vList = storageUseService.selectStorageUseByYear(req);
        }
        else if(req.getType() == 2) { //월
            vList = storageUseService.selectStorageUseByMonth(req);
        }
        else { //일
            vList = storageUseService.selectStorageUseByDate(req);
        }

        return vList;
    }

    @PutMapping("/updateStorageUse")
    @Transactional(readOnly = false)
    public String updateStorageUse() { //storage_use table을 update 하기 위한 컨트롤러 부분.
        if(storageUseService.updateStorageUse() == 0) {
            return "ok";
        }
        else {
            return "delete fail";
        }

    }

}