package com.humintecTest.dashboard.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.humintecTest.dashboard.request.DateRequestFormat;
import com.humintecTest.dashboard.request.ScannerTableRequestFormat;
import com.humintecTest.dashboard.response.ScannerTableResponseFormat;
import com.humintecTest.dashboard.service.ScannerTableService;
import com.humintecTest.dashboard.vo.ScannerTableVo;

@RestController
public class ScannerTableController {
	@Autowired
	ScannerTableService scannerTableService;

	@GetMapping("/selectScannerTable") //사용안함
	@Transactional(readOnly = true)
	@CrossOrigin(origins = "*")
	public List<ScannerTableResponseFormat> selectScannerTable(){
		ScannerTableVo vo = new ScannerTableVo();
		List<ScannerTableVo> vList = scannerTableService.selectScannerTable(vo);

		ArrayList<ScannerTableResponseFormat> res = new ArrayList<ScannerTableResponseFormat>();
		for(ScannerTableVo target : vList) {
			res.add(new ScannerTableResponseFormat(target));
		}

		return res;
	}

	@PutMapping("/updateScannerTable")
	@Transactional(readOnly = false)
	@CrossOrigin(origins = "*")
	public String updateScannerTable() {
		if(scannerTableService.deleteScannerTable() == 0) {
			ScannerTableVo vo = new ScannerTableVo();
			List<ScannerTableVo> vList = scannerTableService.selectScannerTable(vo);

			for(ScannerTableVo target : vList) {
				if(scannerTableService.insertScannerTable(target) == 0) {

				}
				else {
					return "false";
				}
			}
		}
		else {
			return "false";
		}

		return "ok";
	}

	@GetMapping("/searchScannerTable") //전체 데이터
	@Transactional(readOnly = true)
	@CrossOrigin(origins = "*")
	public List<ScannerTableResponseFormat> searchScannerTable(){
		ScannerTableVo vo = new ScannerTableVo();
		List<ScannerTableVo> vList = scannerTableService.searchScannerTable();

		ArrayList<ScannerTableResponseFormat> res = new ArrayList<ScannerTableResponseFormat>();
		for(ScannerTableVo target : vList) {
			res.add(new ScannerTableResponseFormat(target));
		}

		return res;
	}

	@PostMapping("/searchScannerTableByDate")
	@Transactional(readOnly = false)
	@CrossOrigin(origins = "*")
	public List<ScannerTableResponseFormat> searchScannerTableByDate(@RequestBody DateRequestFormat req){
		if(req.getStartDate() == null || req.getFinishDate() == null) {
			List<ScannerTableVo> vList = scannerTableService.searchScannerTable();

			ArrayList<ScannerTableResponseFormat> res = new ArrayList<ScannerTableResponseFormat>();
			for(ScannerTableVo target : vList) {
				res.add(new ScannerTableResponseFormat(target));
			}

			return res;
		}

		List<ScannerTableVo> vList = scannerTableService.searchScannerTableBydate(req);

		ArrayList<ScannerTableResponseFormat> res = new ArrayList<ScannerTableResponseFormat>();
		for(ScannerTableVo target : vList) {
			res.add(new ScannerTableResponseFormat(target));
		}

		return res;
	}

	@PostMapping("/searchScannerTableById")
	@Transactional(readOnly = false)
	@CrossOrigin(origins = "*")
	public List<ScannerTableResponseFormat> searchScannerTableById(@RequestBody ScannerTableRequestFormat req){
		List<ScannerTableVo> vList = scannerTableService.searchScannerTableById(req);

		ArrayList<ScannerTableResponseFormat> res = new ArrayList<ScannerTableResponseFormat>();
		for(ScannerTableVo target : vList) {
			res.add(new ScannerTableResponseFormat(target));
		}

		return res;
	}
}