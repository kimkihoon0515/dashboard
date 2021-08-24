package com.humintecTest.dashboard.controller;

import com.humintecTest.dashboard.service.StorageStatusService;
import com.humintecTest.dashboard.vo.StorageStatusVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class StorageStatusController {
    @Autowired
    StorageStatusService storageStatusService;

    @GetMapping("/selectStorageStatus")
    public List<StorageStatusVo> selectStorageStatus (StorageStatusVo vo) // slide_list, basefolder_list로부터 storage_type table에 넣을 데이터를 불러오기 위한 컨트롤러.
    {
        List<StorageStatusVo> vList = storageStatusService.selectStorageStatus(vo);
        return vList;
    }

    @PutMapping("/updateStorageStatus") // storage_type table을 update하기위한 컨트롤러.
    @Transactional(readOnly = false)
    public String updateStorageStatus() {
        if (storageStatusService.updateStorageStatus() == 0) {
            return "ok";
        } else {
            return "false";
        }
    }
}
