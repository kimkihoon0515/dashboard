package com.humintecTest.dashboard.controller;

import com.humintecTest.dashboard.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class ScheduleController { // table들을 update하기 위한 controller.
	@Autowired
	PidService pidService;
	@Autowired
	ScannerSlideListService scannerSlideListService;
	@Autowired
	SlideDateService slideDateService;

	@Autowired
	StorageStatusService storageStatusService;

	@Autowired
	StorageUseService storageUseService;
	@Autowired
	ScannerTableService scannerTableService;

	
	@PutMapping("/updateAll")
	@Transactional(readOnly = false)
//	@Scheduled(fixedRate = 86400000)
	public String uadateAll() {
		int check = 0;

		

		check += scannerTableService.updateScannerTable();
		check += scannerSlideListService.updateScanner();
		check += slideDateService.updateSlideDate();
		check += pidService.updatePid();
		check += storageStatusService.updateStorageStatus();
		check += storageUseService.updateStorageUse();
		
		if(check != 0) {
			return "update all false";
		}
		return "ok";
	}
}
