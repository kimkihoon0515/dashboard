package com.humintecTest.dashboard.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.humintecTest.dashboard.response.storageUseTypeResponseFormat;
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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
public class StorageUseController {
    @Autowired
    StorageUseService storageUseService;

    @GetMapping("/selectStorageUse") //사용안함
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

    @GetMapping("/showStorageUse") //전체 데이터
    public List<storageUseResponseFormat> showStorageUse (StorageUseVo vo) {
        StorageUseVo vo1 = new StorageUseVo();
        List<StorageUseVo> vList = storageUseService.showStorageUse(vo1);
        long sum=0;
        System.out.println(vList);
        for (int i =0; i < 4;i++){
            //System.out.println(vList.indexOf("daily_sum"));
            //System.out.println(vList.get(i).getDate());
            //System.out.println(vList.get(i).getDaily_sum());
            sum +=vList.get(i).getDaily_sum();
        }
        System.out.println(sum);
        vo1.setDaily_sum(sum);
        vo1.setDate("2021-07-30");
        vList.add(vo1);
        System.out.println(vList);
        ArrayList<storageUseResponseFormat> res = new ArrayList<storageUseResponseFormat>();
        int k= 4;
        for (StorageUseVo target : vList)
        {
            res.add(new storageUseResponseFormat(target));
            //System.out.println(target.getDate());
            //System.out.println(target.getDaily_sum());
            sum = sum + target.getDaily_sum();
            //System.out.println(target);
        }

        //System.out.println(res);
        return res;
    }

    @PutMapping("/insertStorageUse") //사용안함
    public String insertStorageUse (StorageUseVo vo)
    {
        List<StorageUseVo> vList = storageUseService.selectStorageUse(vo); //Json
        System.out.println(vList);


        for(StorageUseVo target : vList)
        {
            if(storageUseService.insertStorageUse(target)==0)
            {
                System.out.println(target);
            }
            else
            {
                return "false";
            }
        }
        return "ok";
    }

    @PutMapping("/deleteStorageUse") //사용안함
    public String deleteStorageUse(StorageUseVo vo)
    {
        if(storageUseService.deleteStorageUse()==1)
        {

        }
        else {
            return "false";
        }
        return "ok";
    }
    
    @PostMapping("/searchStorageUseByDate")
    @Transactional(readOnly = true)
    @CrossOrigin(origins = "*")
    public List<storageUseResponseFormat> searchStorageUseByDate(@RequestBody DateRequestFormat req){
    	List<storageUseResponseFormat> vList;
    	
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
    @CrossOrigin(origins = "*")
    public String updateStorageUse() {
    	if(storageUseService.deleteStorageUse() == 0) {
    		StorageUseVo vo = new StorageUseVo();
    		List<StorageUseVo> vList = storageUseService.selectStorageUse(vo);
    		
    		for(StorageUseVo target : vList) {
                if(storageUseService.insertStorageUse(target)==0) {

                }
                else {
                    return "insert fail";
                }
            }
    	}
    	else {
    		return "delete fail";
    	}
    	
    	return "ok";
    }

}
