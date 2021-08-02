package com.humintecTest.dashboard.controller;

import com.humintecTest.dashboard.dao.StorageTableDao;
import com.humintecTest.dashboard.request.StorageTableRequestFormat;
import com.humintecTest.dashboard.response.storageTableResponseFormat;
import com.humintecTest.dashboard.service.StorageTableService;
import com.humintecTest.dashboard.vo.StorageTableVo;
import com.humintecTest.dashboard.vo.StorageUseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class StorageTableController {
    @Autowired
    StorageTableService storageTableService;

    @GetMapping("/selectStorageTable") //사용안함
    @CrossOrigin(origins = "*")
    public List<StorageTableVo> selectStorageTable(StorageTableVo vo){
        List<StorageTableVo> vList = storageTableService.selectStorageTable(vo);
        return vList;
    }

    @PutMapping("/insertStorageTable")  //사용안함
    @CrossOrigin(origins = "*")
    public String insertStorageTable(StorageTableVo vo){
        List<StorageTableVo> vList = storageTableService.selectStorageTable(vo);

        for(StorageTableVo target : vList){
            if(storageTableService.insertStorageTable(target)== 0){
            }
            else
                return "false";
        }
        return "ok";
    }

    @PutMapping("/deleteStorageTable")
    @CrossOrigin(origins = "*")
    public String deleteStorageTable(StorageTableVo vo){
        if(storageTableService.deleteStorageTable()==0) {
        }
        else {
            return "false";
        }
        return "ok";
    }

    @PostMapping("/selectStorageTableById") //스토리지 별 일일 사용량 추이 계산
    @Transactional(readOnly = true)
    @CrossOrigin("*")
    public List<storageTableResponseFormat> selectStorageTableById (@RequestBody StorageTableRequestFormat req )
    {

        List<StorageTableVo> vList = storageTableService.selectStorageTableById(req);
        //System.out.println(vList);

        String today;
        double sum=0,avg,total=0;

        SimpleDateFormat sdformat = new SimpleDateFormat("YYYY-MM-dd");

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = null;

        while(true) {
            StorageTableVo vo1 = new StorageTableVo();
            for (int i = 1; i < 7; i++) {
                sum += vList.get(vList.size() - i).getUsed();
            }
            avg = Math.round((sum / 6) * 100) * 0.01;
            cal.setTime(vList.get(vList.size() - 1).getDate());
            cal.add(Calendar.DATE, 1);
            today = sdformat.format(cal.getTime());
            System.out.println();

            try {
                parsed = format.parse(today);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());
            vo1.setDate(sqlDate);
            vo1.setUsed(avg);
            vo1.setStorage_name(req.getStorageName());
            vList.add(vo1);
            //System.out.println(vList);
            //System.out.println(sqlDate);

            for (int i = 0; i < vList.size(); i++) {
                total += vList.get(i).getUsed();
            }
            if (total > 100) {
                System.out.println(total);
                break;
            }
            else {
                sum = 0;
                total = 0;
            }
        }

        ArrayList<storageTableResponseFormat> res = new ArrayList<storageTableResponseFormat>();

        for(StorageTableVo target : vList)
        {
            res.add(new storageTableResponseFormat(target));
        }
        return res;
    }
    
    @PutMapping("/updateStorageTable")
    @Transactional(readOnly = false)
    @CrossOrigin(origins = "*")
    public String updateStorageTable() {
    	StorageTableVo vo = new StorageTableVo();
    	if(storageTableService.deleteStorageTable()==0){
    		List<StorageTableVo> vList = storageTableService.selectStorageTable(vo);

            for(StorageTableVo target : vList){
                if(storageTableService.insertStorageTable(target)== 0){
                }
                else {
                    return "false";
                }
            }
    	}
        else {
            return "false";
        }
        return "ok";
    }
}
