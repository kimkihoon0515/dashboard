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
    public List<StorageStatusVo> selectStorageStatus (StorageStatusVo vo)
    {
        List<StorageStatusVo> vList = storageStatusService.selectStorageStatus(vo);
        return vList;
    }

    @PutMapping("/updateStorageStatus")
    @Transactional(readOnly = false)
    public String updateStorageStatus() {
        StorageStatusVo vo = new StorageStatusVo();
        if (storageStatusService.deleteStorageStatus() == 0) {
            List<StorageStatusVo> vList = storageStatusService.selectStorageStatus(vo);

            for (StorageStatusVo target : vList) {
                if (storageStatusService.insertStorageStatus(target) == 0) {
                } else {
                    return "false";
                }
            }
        } else {
            return "false";
        }
        return "ok";
    }
}
