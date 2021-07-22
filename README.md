===========================================================================

# Storage 테이블 구조정리  
## StorageTable -> StorageDate -> StorageFree (최종 그래프)
## *표시 되어있는 것들이 그래프로 시각화 되는 테이블

===========================================================================

#데이터 갱신 과정  

### 기존 테이블 데이터 삭제 (delete 호출) -> 새로운 데이터 가져오기(select 호출) -> 가져온 데이터 삽입(insert 호출)

===========================================================================

# URL 정리  

===========================================================================  

#데이터 조회 관련     
##* Pid 기준  
### /showPid   
##### 일별 pid 전체 가져오기    
### /searchPid    
##### 날짜 선택해서 pid 가져오기  
### /selPidMonth  
##### 월별 pid 종류별 합계 가져오기  
### /selPidYear  
###### 연도별 pid 종류별 합계 가져오기    

##* Slide 기준
### /showSlideDate
##### 일별 slide 전체 가져오기
### /searchSlideDate(구현예정)
###### 날짜 선택해서 slide 가져오기
### /selSlideMonth
##### 월별 Slide 종류별 합계 가져오기
### /selSlideYear
###### 연도별 Slide 종류별 합계 가져오기  


##* Storage 기준
### /storageList
##### 모든 storage 용량과 사용량 남은용량 데이터 가져오기
### /searchStorageFree
##### 날짜 선택해서 storage 사용량 가져오기 (딱히 안쓸듯)
### /showStorageFree
##### storage별 사용량 추이(현재는 93E에 대한 데이터밖에없음, 추후 storage별로 묶는것 고려예정)  


##* Scanner 기준
### /showScanner    
##### 모든 스캐너별 스캔한 개수 조회하기  

===========================================================================  

#데이터 삭제 관련  
## Pid 테이블 삭제  
### /deletePid  
###### pid 관련 테이블 데이터 전부 삭제  

## Scanner 테이블 삭제  
### /deleteScanner  
###### scanner 관련 테이블 데이터 전부 삭제  

## SlideDate 테이블 삭제
### /deleteSlideDate    
###### slideDate 관련 테이블 데이터 전부 삭제  

## storage 관련 테이블 삭제  
### /deleteStorageFree  
##### storageFree 테이블 삭제  
### /deleteStorageDate  
##### storageDate 테이블 삭제  
### /deleteStorageTable  
##### storageTable 테이블 삭제  

===========================================================================  

#데이터 등록 관련
##Pid 테이블에 등록할 데이터 가져오기  
### /selectPid  
##### slide_list 테이블에서부터 pid관련 데이터 가져와서 저장  
### /insertPid
##### select로 가져온 데이터를 pid 테이블에 insert  

##Slide 테이블에 등록할 데이터 가져오기  
### /selectSlideDate  
##### slide_date 테이블에서부터 slide관련 데이터 가져와서 저장  
### /insertSlideDate  
##### select로 가져온 데이터를 slide 테이블에 insert  

##Scanner 테이블에 등록할 데이터 가져오기  
### /selectScanner  
##### slid_date 테이블에서부터 scanner관련 데이터 가져와서 저장  
### /insertScanner  
##### select로 가져온 데이터를 scanner 테이블에 insert  

##Storage 테이블에 등록할 데이터 가져오기  
### /selectStorageTable  
##### slide_list,storage_list 테이블로부터 그날에 각각 사용한 총 용량 계산하는 데이터 가져와서 저장  
### /insertStorageTable  
##### select로 가져온 데이터를 storage_table에 insert  
### /selectStorageDate  
##### storage_table에서 일별 사용한 데이터의 합을 계산해서 누적합계를 구하고 저장공간의 total값을 storage_list로부터 가져와서 저장  
### /insertStorageDate  
##### select로 가져온 데이터를 storage_date에 insert  
### /selectStorageFree  
##### storage_date 테이블에서 일별 사용량 누적합을 가져오고 그것이 어떤 스토리지인지 storage_name과 남은 사용량과 전체 사용량 데이터를 가져와서 저장  
### /insertStorageFree  
##### select로 가져온 데이터를 storage_free에 insert  

===========================================================================  




