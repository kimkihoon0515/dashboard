package com.humintecTest.dashboard.controller;


import com.humintecTest.dashboard.request.StorageTableRequestFormat;
import com.humintecTest.dashboard.response.storageTableResponseFormat;
import com.humintecTest.dashboard.service.StorageTableService;
import com.humintecTest.dashboard.vo.StorageTableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @PutMapping("/deleteStorageTable")
    @Transactional(readOnly = false)
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
    public List<storageTableResponseFormat> selectStorageTableById (@RequestBody StorageTableRequestFormat req )
    {

        List<StorageTableVo> vList = storageTableService.selectStorageTableById(req);


        String today,yesterday;
        double sum=0,avg,total;
        int j = vList.size()-1;
        System.out.println(j);

        SimpleDateFormat sdformat = new SimpleDateFormat("YYYY-MM-dd");

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = null;
        Date parse = null;



        Date date = new Date(); // 오늘날짜
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        yesterday = sdformat.format(calendar.getTime());
        try {
            parse = format.parse(yesterday);
        } catch (ParseException e) {
            e.printStackTrace();
        } //parse가 어제날짜 date형식으로바꾼것
        java.util.Date yesDate = new java.util.Date(parse.getTime());

        if (vList.get(vList.size()-1).getDate()!=date){
            date = yesDate;
        } //DB 마지막 데이터 날짜와 오늘 날짜를 비교해서 같지않으면 예측 데이터를 오늘부터 나오게끔 설정하는 조건문

        if (vList.size() < 1 || req.getN()<1){
            return null;
        }

        while(true) {
            StorageTableVo vo1 = new StorageTableVo();
            for (int i = 1; i < req.getN()+1; i++) { // ex) n = 4인경우 4번 loop 돌아감.
                // 오늘날짜인 date가 vlist의 마지막 데이터의 날짜와 같지않으면 sum을 계산하지않고 넘어감
                // date -1을 하고 새롭게 나온 date를 date로 넣어줌.
                // 그렇게 n번 loop 돌리면서 sum 값을 계산하고 그것을 used로 넣어줌.
                // add를 통해서 vlist를 update한 후 date를 하나증가시킴. 그렇게되면 현재 date는 내일이 되고 vlist가 하나늘어남
                // 이런식으로 while문을 돌아서 total > 총 용량이 되면 while문 종료
                sum += vList.get(vList.size() - i).getUsed(); // 당일 사용량의 합
            }
            avg = Math.round((sum / req.getN()) * 100) /100.0;

            cal.setTime(date);
            cal.add(Calendar.DATE, 1);
            today = sdformat.format(cal.getTime());

            try {
                parsed = format.parse(today);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());
            vo1.setDate(sqlDate);
            date = sqlDate; //date를 오늘날짜에서 하루 증가된 날로 교체

            vo1.setUsed(avg);
            vo1.setStorage_name(req.getStorageName());
            total = Math.round((vList.get(vList.size()-1).getDaily_used()+avg)*100)/100.0;

            vo1.setDaily_used(total);
            vList.add(vo1); //새롭게 만들어진 vo1을 vlist의 마지막에 add


            if (total > 100) {

                break;
            }
            else {
                sum = 0;
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
    public String updateStorageTable() {
        if(storageTableService.updateStorageTable()==0){
            return "ok";
        }
        else {
            return "false";
        }
    }
}