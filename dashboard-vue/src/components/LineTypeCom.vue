<template>
  <div class="chartbox">
    <div id="filter">
    <input id="param" type="Number" min=1 v-model="maN"/>
    <span id="predict">용량 초과 예정일: {{predictDate}}</span>
    </div>
    <div id="chart">
    <line-chart :datacollection="datacollection" :options="chartoptions" :change="change" @rerendered="reset"></line-chart>
    </div>
  </div>
</template>

<script>
import LineChart from './LineChart.vue'

import moment from 'moment'

export default {
  name : "LineTypeCom",
  components: { LineChart },
  props: {
    storageName: {
      type: String,
      default: null
    },
    query: {
       type: Object,
       default: null
    }
  },
  data () {
    return {
      predictDate:null,
      maN:7,
      change:0,
     dataform:{
        label: null,
        data: null,


        //backgroundColor: null,
        fill:false,
        interaction:{
            intersect:true
          },
        pointRadius:0.7,
        pointBackgroundColor: 'white',
        borderWidth: 3,
        borderColor: []
        },
      colorset:['#f87979','#ffd950', '#02bc77', '#28c3d7', '#FF6384'],
      datacollection: {
        labels: null,
        datasets: [{
        }]
      },
      chartoptions:{
        elements:{
          point: {
            backgroundColor: 'white'
          }
        },
        title: {
          display: true,
          text: this.query.chartName,
          fontSize: 16
        },
        tooltips: {
        callbacks: {
          label: function(tooltipItem, data) {
            var dataset = data.datasets[tooltipItem.datasetIndex];
            var currentValue = dataset.data[tooltipItem.index];
            return currentValue +'%';
          }, 
        }
      },

          scales: {
              yAxes: [{
                  ticks: {
                      beginAtZero: true,
                      max: 100
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
              display: false
          },
          responsive: true,
          maintainAspectRatio: false
      }
    }
  },
  methods: {
    reset() {
      this.change=0;
    },
    parseLineData(res){
      var x= this.query.xKey;
      var y= this.query.yKey;
      var keys= Object.keys(res.data[0]);
      this.datacollection.labels=res.data.map(function(elem){return elem[keys[x]]});
      this.predictDate=this.datacollection.labels[this.datacollection.labels.length-1]
      let currentIndex=this.getIndex(this.datacollection.labels,moment().format('YYYY-MM-DD'))
      console.log(res.data)
      console.log(currentIndex)
      for(let i=0; i<y.length ; i++){
        let tmp= _.cloneDeep(this.dataform);
        tmp.label=keys[y[i]];
        tmp.data=res.data.map(function(elem){return elem[keys[y[i]]]});
        for (let j=0; j<tmp.data.length;j++){
          if(j<=currentIndex){
            tmp.borderColor.push('#f87979')
          }
          else{
            tmp.borderColor.push('#2ECC70')
          }
        }
        this.datacollection.datasets.pop();
        this.datacollection.datasets.push(tmp);
      }
      console.log(this.datacollection.datasets)
      this.change=1;
    },
    getIndex(labels,date){
      for(let i=0 ; i<labels.length; i++){
        if(labels[i]==date){
          return i
        }
      }
    }
  },
  watch:{
    maN: function(){
      this.$axios.post(this.query.url, {'storageName':this.storageName, 'n':this.maN})
      .then((res)=>{
      console.log(res)
      this.parseLineData(res);
      })
      .then((err)=>{
        console.log(err);
      })
    },
    storageName: function(){
      this.$axios.post(this.query.url, {'storageName':this.storageName, 'n':this.maN})
      .then((res)=>{
      console.log(res)
      this.parseLineData(res);
      })
      .then((err)=>{
        console.log(err);
      })
    }
  },
  mounted() {
    this.$axios.post(this.query.url, {'storageName':this.storageName, 'n':this.maN})
    .then((res)=>{
      console.log(res)
      this.parseLineData(res);
    })
    .then((err)=>{
      console.log(err);
    })
  }
}
</script>

<style>
  #filter {
    font-size: 10pt;
     height:10%
  }
  #chart{
    height:50%
  }
  #param{
    float: left;
    width: 20%;
  }
  #predictDate{
    float: right;
    font-size: 18pt;
  }
</style>