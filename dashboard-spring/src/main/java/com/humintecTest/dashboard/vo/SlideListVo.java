package com.humintecTest.dashboard.vo;

import lombok.Data;

import java.util.Date;

@Data
public class SlideListVo {
    private int sid;
    private String filename;
    private String pathology_id;
    private String pathology_id_ref;
    private String block;
    private String stain;
    private String pathID_ch;
    private String create_date;
    private String modify_date;
    private Date first_scan_date;
    private Date last_scan_date;
    private String image_type;
    private String scanner_type;
    private String hupax_basefolder_name;
    private String sectra_basefolder_name;
    private String file_path;
    private long file_size;
    private int download_count;
}
