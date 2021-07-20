<template>
  <section class="container">
    <form> 
      <input type="date" name='start_Date' value=''/>
      <input type="date" name='end_Date' value=''/>
      <input type="submit"/>
    </form>
    <div class="columns">
      <div class="column">
        <h3>Slide per Date Chart</h3>
        <line-chart class="slidedate" :datacollection="slideDateDataset" :options="chartoptions"></line-chart>
      </div>
      <div class="column">
        <h3>Scanner Type Chart</h3>
        <bar-chart class="scannertype" :datacollection="scannerDataset" :options="chartoptions"></bar-chart>
      </div>
      <div class="column">
        <h3>pathID Type Chart</h3>
        <bar-chart class="pathIDtype" :datacollection="pathIDdataset" :options="chartoptions"></bar-chart>
      </div>
    </div>
  
    <div class="columns">
      <div class="column">
        <h3>Storage Status Chart</h3>
        <bar-chart class="storageStatus" :datacollection="storagestatusdataset" :options="stackchartoptions"></bar-chart>
      </div>
      <div class="column">
        <h3>storage detail</h3>
      
        <h2>storage piechart</h2>
        <pie-chart class="storagePie" :datacollection="storagedetaildataset" :options="stackchartoptions"></pie-chart>
      </div>
      <div>
        <h2>storage trendline</h2>
        <line-chart class="storageTrend" :datacollection="storageTrenddataset" :options="chartoptions"></line-chart>

      </div>
    
    </div>
  </section>
</template>
 
<script>
  import LineChart from '@/components/LineChart'
  import BarChart from '@/components/BarChart'
  import PieChart from '@/components/PieChart'
  import Reactive from '@/components/Reactive'
  import pathID from '@/data/pathID_ch.json'
  import scannType from '@/data/scannertype.json'
  import slideDate from '@/data/slideDate.json'
  import storageList from '@/data/storagelist.json'
  import storageTrend from '@/data/storage.json'
  import _ from 'lodash'

  function makePieData(name,data){
    var storage_name_list=data.map(function(elem){ return elem.storage_name}) ;
    var storage_total_list=data.map(function(elem){ return elem.total}) ;
    var storage_used_list=data.map(function(elem){ return elem.used}) ;
    for (var i in storage_name_list){
      if (storage_name_list[i] == name){
        return [storage_used_list[i],storage_total_list[i]-storage_used_list[i]]
      }
    }
  }

  export default {  
    name: 'VueChartJS',
    components: {
      LineChart,
      BarChart,
      PieChart,
      Reactive
    },
    data() {
        return{
            storage_name:"129D",
            storage_name_list: storageList.map(function(elem){ return elem.storage_name}),
            chartoptions:{
                    scales: {
                        yAxes: [{
                            ticks: {
                                beginAtZero: true
                            },
                            gridLines: {
                                display: true
                            },
                        }],
                        xAxes: [ {
                        
                            gridLines: {
                                display: false
                            },
                        }]
                    },
                    legend: {
                        display: true
                    },
                    responsive: true,
                    maintainAspectRatio: false
                },
            slideDateDataset:{
                labels: slideDate.map(function(elem){
                            return elem.last_scan_date
                            }
                ),
                datasets: [{
                    label: 'Data One',
                    data: slideDate.map(function(elem){
                        return elem.slide_sum
                    }),
                    backgroundColor: '#f87979',
                    pointBackgroundColor: 'white',
                    borderWidth: 1,
                    pointBorderColor: '#249EBF'
                }]
          
            },
            scannerDataset:{
                labels: scannType.map(function(elem){
                    return elem.scanner_type
                }),

                datasets:[{
                    label: "scanner type",
                    data: scannType.map(function(elem){
                        return elem.slide_sum
                        }),
                    backgroundColor: '#f87979',
                    pointBackgroundColor: 'white',
                    borderWidth: 1,
                    pointBorderColor: '#249EBF'
                }]             
            },
            pathIDdataset:{
              labels: pathID.map(function(elem){
                    return elem.pathID_ch
                }),
              datasets:[{
                  label: "pathID type",
                  data: pathID.map(function(elem){
                      return elem.amount
                      }),
                  backgroundColor: '#f87979',
                  pointBackgroundColor: 'white',
                  borderWidth: 1,
                  pointBorderColor: '#249EBF'
              }]    
            },
            storagestatusdataset:{
              labels: storageList.map(function(elem){
                    return elem.storage_name
                }),
              datasets:[
                {
                  label: 'storageused',
                  backgroundColor: '#f87979',
                  pointBackgroundColor: 'white',
                  borderWidth: 1,
                  pointBorderColor: '#249EBF',
                  //Data to be represented on y-axis
                  data: storageList.map(function(elem){
                          return  elem.used
                        })
                },
                {
                  label: 'storagefree',
                  backgroundColor: 'blue',
                  pointBackgroundColor: 'white',
                  borderWidth: 1,
                  pointBorderColor: '#249EBF',
                  //Data to be represented on y-axis
                  data: storageList.map(function(elem){
                          return  elem.total-elem.used
                        })
                }
              ]    
            },
            stackchartoptions: {
              scales: {
                yAxes: [{
                  ticks: {
                    beginAtZero: true
                  },
                  gridLines: {
                    display: true
                  },
                  stacked: true
                }],
                xAxes: [ {
                  gridLines: {
                    display: false
                  },
                  stacked: true
                }]
              },
              legend: {
                display: true
              },
              responsive: true,
              maintainAspectRatio: false
            },
            storagedetaildataset:{
              labels: ['Used', 'Free'],
              datasets:[{
                  label: "storage percent",
                  data: makePieData("129D",storageList),
                  backgroundColor: ['#ffd950', '#02bc77', '#28c3d7', '#FF6384','#f87979'],
                  pointBackgroundColor: 'white',
                  borderWidth: 1,
                  pointBorderColor: '#249EBF'
              }]    
            },
            storageTrenddataset:{
              labels: storageTrend.map(function(elem){
                            return elem.last_scan_date
                            }
                ),
                datasets: [{
                    label: 'Data One',
                    data: storageTrend.map(function(elem){
                        return 100-elem.free
                    }),
                    backgroundColor: '#f87979',
                    pointBackgroundColor: 'white',
                    borderWidth: 1,
                    pointBorderColor: '#249EBF'
                }]  
            }
        }
    },
    methods: {
      nameset: function (name){
        console.log(this.storagedetaildataset);
        let tmp = _.cloneDeep(this.storagedetaildataset);
        console.log(tmp);
        tmp.datasets[0].data= makePieData(name,storageList);
        this.storagedetaildataset=tmp;
        console.log(this.storagedetaildataset);
      }
    }
  }
</script>
 
<style scoped>
  ul {
    list-style-type: none;
    padding: 0;
  }
 
  li {
    display: inline-block;
    margin: 0 10px;
  }
 
  a {
    color: #42b983;
  }
</style>