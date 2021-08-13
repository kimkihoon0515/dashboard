package com.humintecTest.dashboard.controller;

import java.util.List;

import com.humintecTest.dashboard.service.*;
import com.humintecTest.dashboard.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

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

	
	@PutMapping("/updateAll")
	@Transactional(readOnly = false)
	@CrossOrigin(origins = "*")
//	@Scheduled(fixedRate = 86400000)
	public String uadateAll() {
    	if(storageTableService.deleteStorageTable()==0){
    		StorageTableVo vo = new StorageTableVo();
    		List<StorageTableVo> vList = storageTableService.selectStorageTable(vo);

            for(StorageTableVo target : vList){
                if(storageTableService.insertStorageTable(target)== 0){
                }
                else {
                    return "false";
                }
            }
    	}
        else {
            return "false";
        }
    	
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
    	
		if(scannerSlideListService.deleteScanner() == 0) {
    		ScannerSlideVo vo = new ScannerSlideVo();
    		List<ScannerSlideVo> vList = scannerSlideListService.selectScanner(vo);

            for(ScannerSlideVo target : vList) {
                if(scannerSlideListService.insertScanner(target) == 0){

                }
                else
                    return "false";
            }
    	}
		
		if(slideDateService.deleteSlideDate()== 0){
    		SlideDateVo vo = new SlideDateVo();
    		List<SlideDateVo> vList = slideDateService.selectSlideDate(vo);
    		
    		for(SlideDateVo target : vList) {
    			if(slideDateService.insertSlideDate(target) == 0) {
    				
    			}
    			else {
    				return "false";
    			}
    		}

    	}
    	else {
    		return "false";

    	}
		
		if(pidService.deletePid() == 0) {
   		 PidVo vo = new PidVo();
   		 List<PidVo> vList = pidService.selectPid(vo);

   	        for (PidVo target : vList){
   	            if(pidService.insertPid(target) == 0){

   	            }
   	            else
   	                return "false";
   	        }
        }
        else {
       	 return "false";
        }
		
		if(storageDateService.deleteStorageDate()== 0){
    		StorageDateVo vo = new StorageDateVo();
    		List<StorageDateVo> vList = storageDateService.selectStorageDate(vo);
    		
    		for(StorageDateVo target : vList) {
    			if(storageDateService.insertStorageDate(target) == 0) {
    				
    			}
    			else {
    				return "false";
    			}
    		}
    	}
		
		if (storageFreeService.deleteStorageFree() == 0) {
    		StorageFreeVo vo = new StorageFreeVo();
    		List<StorageFreeVo> vList = storageFreeService.selectStorageFree(vo);
    		
    		for (StorageFreeVo target : vList) {
                if (storageFreeService.insertStorageFree(target) == 0) {

                } else
                    return "false";
            }
    	}
    	else{
    		return "false";
    	}
		
		if(storageUseService.deleteStorageUse() == 0) {
    		StorageUseVo vo = new StorageUseVo();
    		List<StorageUseVo> vList = storageUseService.selectStorageUse(vo);
    		
    		for(StorageUseVo target : vList) {
                if(storageUseService.insertStorageUse(target)==0) {

                }
                else {
                    return "insert fail";
                }
            }
    	}
    	else {
    		return "delete fail";
    	}
		
		return "ok";
	}
}
