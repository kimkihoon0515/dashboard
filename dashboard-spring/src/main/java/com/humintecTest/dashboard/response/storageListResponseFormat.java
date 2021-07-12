package com.humintecTest.dashboard.response;

import com.humintecTest.dashboard.vo.StorageListVo;

import lombok.Data;

@Data
public class storageListResponseFormat {
	private int id;
	private String storageName;
	private Long total;
	private Long used;
	private Long free;
	private String server_ip;
	
	public storageListResponseFormat(StorageListVo vo){
		id = vo.getId();
		storageName = vo.getStorageName();
		total = vo.getTotal();
		used = vo.getUsed();
		free = vo.getFree();
		server_ip = vo.getServerIp();
	}
	
	public int getId() {
		return id;
	}
	public String getStorageName() {
		return storageName;
	}
	public Long getTotal() {
		return total;
	}
	public Long getUsed() {
		return used;
	}
	public Long getFree() {
		return free;
	}
	public String getServer_ip() {
		return server_ip;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setStorageName(String storageName) {
		this.storageName = storageName;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public void setUsed(Long used) {
		this.used = used;
	}
	public void setFree(Long free) {
		this.free = free;
	}
	public void setServer_ip(String server_ip) {
		this.server_ip = server_ip;
	}
}
