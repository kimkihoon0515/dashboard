package com.humintecTest.dashboard.request;

import lombok.Data;

@Data
public class HupaxRequestFormat { // 평균이동법 계산을 위해 n값을 프론트로부터 전달받는 객체
    private int n;
}
