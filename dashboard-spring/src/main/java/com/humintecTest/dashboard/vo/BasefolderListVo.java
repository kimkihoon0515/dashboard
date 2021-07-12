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
	public int getId() {
		return id;
	}
	public String getBasefolder_name() {
		return basefolder_name;
	}
	public String getBasefolder_path() {
		return basefolder_path;
	}
	public String getCurrent_write_YN() {
		return current_write_YN;
	}
	public String getLongterm_YN() {
		return longterm_YN;
	}
	public String getHupax_or_sectra() {
		return hupax_or_sectra;
	}
	public int getSlide_count() {
		return slide_count;
	}
	public String getStorage_name() {
		return storage_name;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setBasefolder_name(String basefolder_name) {
		this.basefolder_name = basefolder_name;
	}
	public void setBasefolder_path(String basefolder_path) {
		this.basefolder_path = basefolder_path;
	}
	public void setCurrent_write_YN(String current_write_YN) {
		this.current_write_YN = current_write_YN;
	}
	public void setLongterm_YN(String longterm_YN) {
		this.longterm_YN = longterm_YN;
	}
	public void setHupax_or_sectra(String hupax_or_sectra) {
		this.hupax_or_sectra = hupax_or_sectra;
	}
	public void setSlide_count(int slide_count) {
		this.slide_count = slide_count;
	}
	public void setStorage_name(String storage_name) {
		this.storage_name = storage_name;
	}
    
    
}
