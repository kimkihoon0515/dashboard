package com.humintecTest.dashboard.controller;

import com.humintecTest.dashboard.request.DateRequestFormat;
import com.humintecTest.dashboard.response.storageFreeResponseFormat;
import com.humintecTest.dashboard.service.StorageFreeService;
import com.humintecTest.dashboard.vo.StorageFreeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StorageFreeController {
    @Autowired
    StorageFreeService storageFreeService;

    @GetMapping("/selectStorageFree")
    @CrossOrigin(origins = "*")
    public List<storageFreeResponseFormat> selectFreeStorage() {
        StorageFreeVo vo1 = new StorageFreeVo();
        List<StorageFreeVo> vList = storageFreeService.selectStorageFree(vo1);
        ArrayList<storageFreeResponseFormat> res = new ArrayList<storageFreeResponseFormat>();

        for (StorageFreeVo target : vList) {
            res.add(new storageFreeResponseFormat(target));
        }
        return res;
    }

    @PutMapping("/insertStorageFree")
    @CrossOrigin(origins = "*")
    public String insertStorageFree(StorageFreeVo vo) {
        List<StorageFreeVo> vList = storageFreeService.selectStorageFree(vo);

        for (StorageFreeVo target : vList) {
            if (storageFreeService.insertStorageFree(target) == 0) {

            } else
                return "false";
        }
        return "ok";
    }

    @PutMapping("/deleteStorageFree")
    @CrossOrigin(origins = "*")
    public String deleteStorageFree(StorageFreeVo vo) {
        if (storageFreeService.deleteStorageFree() == 0) {
        } else {
            return "false";
        }
        return "ok";
    }

    @GetMapping("/searchStorageFreeByDate")
    @Transactional(readOnly = true)
    @CrossOrigin(origins = "*")
    public List<storageFreeResponseFormat> searchStorageFree (@RequestBody DateRequestFormat req) {
        List<StorageFreeVo> vList = storageFreeService.searchStorageFreeByDate(req);
        
        ArrayList<storageFreeResponseFormat> res = new ArrayList<storageFreeResponseFormat>();

        for (StorageFreeVo target : vList) {
            res.add(new storageFreeResponseFormat(target));
        }
        
        return res;
    }
    
    @PutMapping("/updateStorageFree")
    @Transactional(readOnly = false)
    @CrossOrigin(origins = "*")
    public String updateStorageFree() {
    	if (storageFreeService.deleteStorageFree() == 0) {
    		StorageFreeVo vo = new StorageFreeVo();
    		List<StorageFreeVo> vList = storageFreeService.selectStorageFree(vo);
    		
    		for (StorageFreeVo target : vList) {
                if (storageFreeService.insertStorageFree(target) == 0) {

                } else
                    return "false";
            }
    	}
    	else{
    		return "false";
    	}
    	
    	return "ok";
    }
}
