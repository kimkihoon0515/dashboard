package com.humintecTest.dashboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.humintecTest.dashboard.service.ScannerTableService;

@CrossOrigin(origins = "*")
@RestController
public class ScannerTableController {
	@Autowired
	ScannerTableService scannerTableService;

	@PutMapping("/updateScannerTable")
	@Transactional(readOnly = false)
	public String updateScannerTable() {
		if(scannerTableService.updateScannerTable() == 0) {
			return "ok";
		}
		else {
			return "false";
		}
	}

}