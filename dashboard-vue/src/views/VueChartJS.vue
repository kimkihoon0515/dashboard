<template>
  <section class="container">
    <div class="columns">
      <div class="column">
        <h3>Slide per Date Chart</h3>
        <line-chart class="slidedate" :datacollection="slideDateDataset" :option="chartoptions"></line-chart>
      </div>
      <div class="column">
        <h3>Scanner Type Chart</h3>
        <bar-chart class="scannertype" :datacollection="scannerDataset" :option="chartoptions"></bar-chart>
      </div>
      <div class="column">
        <h3>pathID Type Chart</h3>
        <bar-chart class="pathIDtype" :datacollection="pathIDdataset" :option="chartoptions"></bar-chart>
      </div>
    </div>
    <div class="columns">
      <div class="column">
        <h3>Bubble Chart</h3>
        <!--Bubble Chart example-->
      </div>
      <div class="column">
        <h3>Reactivity - Live update upon change in datasets</h3>
        <!--Reactivity Line Chart example-->
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
            chartoptions:{
                    scales: {
                        yAxes: [{
                            ticks: {
                                beginAtZero: true
                            },
                            gridLines: {
                                display: true
                            }
                        }],
                        xAxes: [ {
                        
                            gridLines: {
                                display: false
                            }
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
                }],
          
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