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

        List<HupaxVo> vList = hupaxService.selectHupax(req); //이동평균법을 계산해서 하나씩 추가해줄 리스트
        List<HupaxVo> voList= new ArrayList<HupaxVo>(); //아무런 조치도 하지 않은 초기의 DB 리스트
        for (HupaxVo target: vList){
            voList.add(target);
        }
        long sum=0,avg,all; // sum은 avg를 구하기위해 avg는 익일의 사용량, all은 새로 추가되고 계산되는 누적값

        int size = vList.size()-1;
        int n=0; // vList에 추가될 날짜 계산을 위한 변수
        LocalDate today = LocalDate.now(); //오늘날짜를 localDate 형식으로 선언함

        Date date = Date.valueOf(today); //비교를 위해 today를 sqlDate형식으로 변환함
        Date vDate;


        if(vList.size()<1|| req.getN()<1){ //예외사항 처리를 위한 구문
            return null;
        }

        while(true){
            HupaxVo vo1 = new HupaxVo(); //vList에 새롭게 add 하기 위한 vo
            for (int i=1;i< req.getN();i++){
                if(date.compareTo(vList.get(size).getDate())==0){ //오늘의 날짜와 DB에 있는 마지막 날짜가 같으면
                    sum +=vList.get(size).getDaily_used(); //당일의 사용량은 sum 이라는 변수에 합하여 저장한다.
                    size--; // DB의 마지막에서 두 번째 데이터로 비교대상을 이동
                }
                else {
                    sum+=0; // 오늘의 날짜와 DB에 마지막 날짜가 다르면 sum을 계산하지 않는다.
                }
                date = Date.valueOf(date.toLocalDate().minusDays(1)); // 오늘의 날짜에서 하나를 빼서 어제의 날짜로 만든다.
               //date = Date.valueOf(today.minusDays(i));

            }
            n++; //내일, 모레 등 앞으로 하나씩 추가하기위해서 today에 add 하기위한 변수
            size = vList.size()-1; // for 문이 끝나고 다시 계산할 때에는 size를 원상태로 되돌려야한다.
            avg = sum/ req.getN(); // for 문을 돌고 나와서 계산된 sum 값의 평균을 구한다.

            vDate = Date.valueOf(today.plusDays(n)); //vList의 마지막에 내일날짜로부터 하루씩 추가하기 위한 변수
            vo1.setDate(vDate); // vo에 새롭게 내일 날짜를 등록
            vo1.setDaily_used(avg); // 구해진 평균이 내일 당일의 사용량이 된다.
            all = vList.get(size).getDaily_sum()+avg; // DB의 마지막 데이터까지의 누적 사용량에 새롭게 구해진 평균을 더해서 총 누적 사용량을 구함.
            vo1.setDaily_sum(all); // vo에 새롭게 계산된 누적값을 등록한다.
            vo1.setTotal(vList.get(size).getTotal()); // 총 용량은 변하지 않으므로 임의로 vList의 마지막 데이터값을 넣어주었다.
            vList.add(vo1); // date, daily_used, daily_sum이 모두 set 되었으므로 이제 add해서 새로운 vList를 만들어준다.
            date = vDate; // 그 다음날의 데이터를 추가하기 위해 for문을 돌려줄 날짜를 내일날짜로 맞춘다.

            if(all>vList.get(size).getTotal()){ //누적 사용량이 총 용량을 넘어설 경우 while문을 멈춘다.
                break;
            }

            else if (avg == 0){ //n값이 너무 작아서 이동평균법으로 계산이 불가능할 경우
                vList=voList; // vList를 아무런 조치도 하지 않은 초기의 상태로 되돌린다.
                break; // 반복문을 탈출한다.
            }

            else {
                sum = 0; // sum을 0으로 초기화해서 새롭게 for문을 돌린다. 초기화를 해야 제대로 된 avg와 sum 값을 구할 수 있다.
            }
        }

        ArrayList<hupaxResponseFormat> res = new ArrayList<hupaxResponseFormat>();
        for (HupaxVo target : vList){
            res.add(new hupaxResponseFormat(target));
        }
        return res;
    }
}
