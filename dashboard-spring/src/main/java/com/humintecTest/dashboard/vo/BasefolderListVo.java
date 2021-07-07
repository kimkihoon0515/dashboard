package com.humintecTest.dashboard.vo;

import lombok.Data;

@Data
public class BasefolderListVo {
    private int id;
    private String basefolder_name;
    private String basefolder_path;
    private String current_write_YN;
    private String longterm_YN;
    private String hupax_or_sectra;
    private int slide_count;
    private String storage_name;
}
