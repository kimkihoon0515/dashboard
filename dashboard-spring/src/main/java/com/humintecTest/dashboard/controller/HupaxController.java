package com.humintecTest.dashboard.controller;

import com.humintecTest.dashboard.request.HupaxRequestFormat;
import com.humintecTest.dashboard.response.hupaxResponseFormat;
import com.humintecTest.dashboard.service.HupaxService;
import com.humintecTest.dashboard.vo.HupaxVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class HupaxController {
    @Autowired
    HupaxService hupaxService;

    @PostMapping("/selectHupax")
    @Transactional(readOnly = true)
    public List<hupaxResponseFormat> selectHupax(@RequestBody HupaxRequestFormat req){

        List<HupaxVo> vList = hupaxService.selectHupax(req);
        List<HupaxVo> voList= new ArrayList<HupaxVo>();
        for (HupaxVo target: vList){
            voList.add(target);
        }
        long sum=0,avg,all;

        int size = vList.size()-1;
        int n=0;
        LocalDate today = LocalDate.now(); //오늘날짜를 localDate 형식으로 선언함

        Date date = Date.valueOf(today); //비교를 위해 today를 sqlDate형식으로 변환함
        Date vDate;


        if(vList.size()<1|| req.getN()<1){
            return null;
        }

        while(true){
            HupaxVo vo1 = new HupaxVo();
            for (int i=1;i< req.getN();i++){
                if(date.compareTo(vList.get(size).getDate())==0){
                    sum +=vList.get(size).getDaily_used();
                    size--;
                }
                else {
                    sum+=0;
                }
                date = Date.valueOf(date.toLocalDate().minusDays(1));
               //date = Date.valueOf(today.minusDays(i));

            }
            n++;
            size = vList.size()-1;
            avg = sum/ req.getN();

            vDate = Date.valueOf(today.plusDays(n));
            vo1.setDate(vDate);
            vo1.setDaily_used(avg);
            all = vList.get(size).getDaily_sum()+avg;
            vo1.setDaily_sum(all);
            vo1.setTotal(vList.get(size).getTotal());
            vList.add(vo1);
            date = vDate;

            if(all>vList.get(size).getTotal()){
                break;
            }

            else if (avg == 0){
                vList=voList;
                break;
            }

            else {
                sum = 0;
            }
        }

        ArrayList<hupaxResponseFormat> res = new ArrayList<hupaxResponseFormat>();
        for (HupaxVo target : vList){
            res.add(new hupaxResponseFormat(target));
        }
        return res;
    }
}
