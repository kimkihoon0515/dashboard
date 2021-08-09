package com.humintecTest.dashboard.controller;

import com.humintecTest.dashboard.service.StorageDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*")
@RestController
public class StorageDateController {
    @Autowired
    StorageDateService storageDateService;

    @PutMapping("/updateStoragePerDate")
    @Transactional(readOnly = false)
    @CrossOrigin(origins = "*")
    public String updateStoragePerDate() {
        if(storageDateService.updateStorageDate()== 0){
            return "ok";
        }

        return "false";
    }

}