package com.humintecTest.dashboard.controller;

import com.humintecTest.dashboard.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.humintecTest.dashboard.service.PidService;
import com.humintecTest.dashboard.service.ScannerSlideListService;
import com.humintecTest.dashboard.service.ScannerTableService;
import com.humintecTest.dashboard.service.SlideDateService;
import com.humintecTest.dashboard.service.StorageDateService;
import com.humintecTest.dashboard.service.StorageFreeService;
import com.humintecTest.dashboard.service.StorageTableService;
import com.humintecTest.dashboard.service.StorageUseService;

@CrossOrigin(origins = "*")
@RestController
public class ScheduleController {
	@Autowired
	PidService pidService;
	@Autowired
	ScannerSlideListService scannerSlideListService;
	@Autowired
	SlideDateService slideDateService;
	@Autowired
	StorageDateService storageDateService;
	@Autowired
	StorageFreeService storageFreeService;
	@Autowired
	StorageTableService storageTableService;
	@Autowired
	StorageUseService storageUseService;
	@Autowired
	ScannerTableService scannerTableService;
	@Autowired
	StorageStatusService storageStatusService;
	
	@PutMapping("/updateAll")
	@Transactional(readOnly = false)
//	@Scheduled(fixedRate = 86400000)
	public String uadateAll() {
		int check = 0;
		
		check += storageTableService.updateStorageTable();
		check += scannerTableService.updateScannerTable();
		check += scannerSlideListService.updateScanner();
		check += slideDateService.updateSlideDate();
		check += pidService.updatePid();
		check += storageDateService.updateStorageDate();
		check += storageFreeService.updateStorageFree();
		check += storageUseService.updateStorageUse();
		
		if(check != 0) {
			return "update all false";
		}
		return "ok";
	}
}
