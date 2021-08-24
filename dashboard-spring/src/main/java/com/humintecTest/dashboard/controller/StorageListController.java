package com.humintecTest.dashboard.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.humintecTest.dashboard.response.storageListResponseFormat;
import com.humintecTest.dashboard.service.StorageListService;
import com.humintecTest.dashboard.vo.StorageListVo;

@RestController
@CrossOrigin(origins = "*")
public class StorageListController {
    @Autowired
    StorageListService storageListService;

    @GetMapping("/storageList") //전체 데이터
    @Transactional(readOnly = true)
    public List<storageListResponseFormat> selectStorageList(){ // storage_list table을 불러오기 위한 컨트롤러.
        StorageListVo vo = new StorageListVo();
        List<StorageListVo> vList = storageListService.selectStorageList(vo);

        ArrayList<storageListResponseFormat> resList = new ArrayList<storageListResponseFormat>();

        for(StorageListVo selectVo : vList) {
            resList.add(new storageListResponseFormat(selectVo));
        }

        return resList;
    }
}