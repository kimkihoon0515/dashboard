package com.humintecTest.dashboard.vo;

import lombok.Data;

@Data
public class StorageListVo {
	private int id;
	private String storageName;
	private Long total;
	private Long used;
	private Long free;
	private String serverIp;
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
	public String getServerIp() {
		return serverIp;
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
	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}
	
	
}
