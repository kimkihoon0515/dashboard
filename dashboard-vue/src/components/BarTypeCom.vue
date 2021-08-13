<template>
    <div class="chartbox">
      <div v-if="needCheck==true" id="header">
        <div class="radio">
          <input :name=query.name type="radio" value="1" v-model="YMD"><label>Y</label>
          <input :name=query.name type="radio" value="2" v-model="YMD"><label>M</label>
          <input :name=query.name type="radio" value="3" v-model="YMD" checked="checked"><label>D</label>
        </div>
        <div class="tab">
          <v-tabs v-model="tab">
            <v-tab >스캔 완료 횟수</v-tab>
            <v-tab>메모리 사용량</v-tab>
          </v-tabs>
        </div>
      </div>
      <div v-if="needCheck==false" id="filter">
        <v-expansion-panels class="mb-6">
          <v-expansion-panel>
            <v-expansion-panel-header expand-icon="mdi-menu-down">
            Filter
            </v-expansion-panel-header>
              <v-expansion-panel-content style="height:auto;">
                  <label class="all"><input id="selectall" type="checkbox" v-model="checked">전체</label>
                  <label class="checkbox" v-for="(name, index) in this.labelList" :key="index"><input :id="name" :value="name" type="checkbox" v-model="checkBind">{{name}} </label>
              </v-expansion-panel-content>
          </v-expansion-panel>
        </v-expansion-panels>
      </div>
      <div id="bar">
        <bar-chart :datacollection="datacollection" :options="chartoptions" :change="change" @rerendered="reset"></bar-chart>
      </div>
    </div>
</template>

<script>
import Multiselect from 'vue-multiselect'
import BarChart from './BarChart.vue'
import _ from 'lodash'
import moment from 'moment'

export default {
  name : "BarTypeCom",
  components: { BarChart, Multiselect},
  props: {
    color:{
      type: String,
      default:'#1FBC9C4D'
    },
    needCheck: {
      type: Boolean,
      default: false
    },
    query: {
       type: Object,
       default: null
    },
    start_date: {
      type: String,
      default : null//moment().format('YYYY-MM-DD').toString,
    },
    end_date: {
      type: String,
      default : null//moment().format('YYYY-MM-DD').toString,
    },
  },
  data () {
    return {
      selectOption:null,
      tab:0,
      checked:true,
      setcolor: 0,
      origin: {},
      checkBind: [],
      labelList: [],
      YMD: 3,
      dataform:{
        label: null,
        data: [],
        backgroundColor: [],
        maxBarThickness:50,
        pointBackgroundColor: 'white',
        borderWidth: 1,
        pointBorderColor: '#249EBF33'
        },
      change:0,
      colorset:['#1FBC9C4D','#ffd95033', '#02bc7733', '#28c3d733', '#FF638433'],
      datacollection: {
        labels: null,
        datasets: [
        ]
      },
      chartoptions:{
        onClick: this.handleChartClick,
        title: {
          display: true,
          text: this.query.chartName,
          fontSize: 16
        },
        plugins: {
          zoom: {
            pan: {
              enabled: true,
              mode: 'xy'
            },
            zoom: {
              enabled: true,
              mode: 'xy'
            }
          }
        },
        tooltips: {
          callbacks: {
            label: function(tooltipItem, data) {
              var dataset = data.datasets[tooltipItem.datasetIndex];
              var currentValue = dataset.data[tooltipItem.index];
              if (currentValue >= 1000000000000) {
                return currentValue = (currentValue/ 1000000000000).toFixed(1) + "TB";
              }
              else if (currentValue>=1000000000 && currentValue < 1000000000000) {
                return currentValue = (currentValue / 1000000000).toFixed(1) + "GB";
              }
              else if (currentValue>=1000000 && currentValue < 1000000000) {
                return currentValue = (currentValue / 1000000).toFixed(1) + "MB";
              }
              else {
              return currentValue;
              }
            }
          }
        }, 
        scales: {
          yAxes: [{
            ticks: {
              beginAtZero: true,
              callback: function(value, index, values){
                if (value >=1000000000000){
                  return value = (value / 1000000000000).toFixed(1) + "TB";
                }
                else if (value>=1000000000 && value <1000000000000){
                  return value = (value / 1000000000).toFixed(1) + "GB";
                }
                else if (value >=1000000 && value < 1000000000) {
                  return value = (value / 1000000).toFixed(1) + "MB";
                }
                else {
                  return value;
                }
              }
            },
            gridLines: {
              display: true
            },
            stacked: false
          }],
          xAxes: [{
            gridLines: {
              display: false
            },
            stacked: false
          }]
        },
        legend:{
          display:false
        },
        responsive: true,
        maintainAspectRatio: false
      }
    }
  },
  methods: {
    handleChartClick(evt, elements) {
      if(this.needCheck==false){
        var chart = this.$children[1]._data._chart;
        console.log(chart);
        const chartIndex = chart.getElementAtEvent(evt);
        console.log(chart);
        if (chartIndex.length !== 0) {
          const datasetIndex = chartIndex[0]._datasetIndex;
          const position = chartIndex[0]._index;
          const info = {
            datasetIndex: datasetIndex,
            valueIndex: position,
            label: chart.tooltip._data.labels[position],
            value: chart.tooltip._data.datasets[datasetIndex].data[position]
          };
          this.origin[info.label][1]=this.color;
          this.setcolor=1;
          console.log(info.label);
        } else {
          console.log("Background clicked");
        }
      }
    },
    reset() {
      this.change=0;
    },
    parseBarData(res, protect_check){
      var x= this.query.xKey;
      var y= this.query.yKey;
      if(res.data.length==0){
        this.datacollection.datasets.pop();
        this.change=1;
        this.labelList=null
        this.checkBind=null
        return
      }
      var keys= Object.keys(res.data[0]);
      if((protect_check==0)&&this.needCheck==false){
        this.selectOption=res.data.map(function(elem){return elem[keys[x]]});
        this.checkBind=res.data.map(function(elem){return elem[keys[x]]});
        this.labelList=res.data.map(function(elem){return elem[keys[x]]});
        return
      }
      else if((protect_check==0)&&this.needCheck==true){
        return
      }
      this.datacollection.labels=res.data.map(function(elem){return elem[keys[x]]});
      var originLabel=res.data.map(function(elem){return elem[keys[x]]});
      for(let i=0; i<y.length ; i++){
        let tmp= _.cloneDeep(this.dataform);
        tmp.label=keys[y[i]];
        tmp.data=res.data.map(function(elem){return elem[keys[y[i]]]});
        tmp.backgroundColor=this.colorset[i];
        this.datacollection.datasets.pop();
        this.datacollection.datasets.push(tmp);
        for(let i=0; i<originLabel.length; i++){
          this.origin[originLabel[i]]=[tmp.data[i],tmp.backgroundColor];
        }
        this.checkBind=_.cloneDeep(originLabel)
        this.labelList=_.cloneDeep(originLabel)
      }
      this.change=1;
      if(this.checkBind.length != 0){
        this.parseBarData_check();
      }
    },
    parseBarData_check(){
      let tmp= _.cloneDeep(this.dataform);
      this.datacollection.labels=[]
      for (var i=0; i<this.labelList.length; i++){
        if(this.checkBind.includes(this.labelList[i])){
          tmp.data.push(this.origin[this.labelList[i]][0]);
          tmp.backgroundColor.push(this.origin[this.labelList[i]][1]);
          this.datacollection.labels.push(this.labelList[i]);
        }
      }
      tmp.label=this.query.name;
      this.datacollection.datasets.pop();
      this.datacollection.datasets.push(tmp);
      this.change=1;
    }
  },
  mounted() {
    if(this.needCheck==false){
      this.$axios.post(this.query.url, {'startDate':null,'finishDate': null, 'type':null})
      .then((res)=>{
        this.parseBarData(res, 0);
      })
      .then((err)=>{
        console.log(err);
      })
    }
  },
  computed:{
    changeDate() {
      return `${this.start_date}|${this.end_date}|${this.YMD}`;
    }
  },
  watch: {
    checked:{
      handler(){
        if(this.checked==true){
          console.log(this.labelList)
          this.checkBind=_.cloneDeep(this.labelList);
        }
        else{
          this.checkBind=[] 
        }
      }
    },
    color:{
      handler(){
        console.log(this.color)
      }
    },
    changeDate:{
      handler(){
        console.log(this.start_date)
        this.$axios.post(this.query.url, {'startDate':this.start_date,'finishDate':this.end_date, 'type':this.YMD})
        .then((res)=>{
          this.parseBarData(res, 1);
        })
        .then((err)=>{
          console.log(err);
        })
      }
    },
    checkBind:{
      handler(){
        this.parseBarData_check();
      }
    },
    setcolor:{
      handler(){
        this.parseBarData_check();
        this.setcolor=0;
      }
    },
    tab:{
      handler(){
        this.$emit("tabChange",this.tab)
        setTimeout( changeQuery =>{
          this.chartoptions.title.text=this.query.chartName
          this.$axios.post(this.query.url, {'startDate':this.start_date,'finishDate': this.end_date, 'type':this.YMD})
          .then((res)=>{
            this.parseBarData(res, 1);
          })
          .then((err)=>{
            console.log(err);
          })
          this.change=1;
        }
        ,1);

      }
    }
  }
}

</script>

<style>
  #select{
    height: 20%;
    overflow: visible !important;
  }
  #filter {
    margin: 5px 0px 0px 0px;
    font-size: 10pt;
    height:80%;
    overflow: visible !important;
  }
  #header {
    height: 10%;
  }
  #bar {
    height: 97%;
    margin: 1% 0 0 0;
  }
  div .tab {
    width: 30%;
    float: left;
    box-sizing: border-box;
  }
  div .radio{
    margin: 10px 10px 0 0px;
    width: 10%;
    float: right;
    box-sizing: border-box;
  }
  .v-expansion-panel-header{
    margin: 2px 2px 2px 2px;
    width: 98% !important;
    height: 20px !important;
    padding: 5px 5px !important;
    min-height: 10px !important;
  }
  .v-expansion-panel-content{
    background-color: rgb(255, 255, 255,0.5) !important;
    border-width: thick  !important;
    border-bottom-color: black !important;
    width: 100% !important;
    box-shadow: 0 2px 4px 0 rgba(0,0,0,0.50) !important;

  }
    .v-expansion-panel-content_wrap{
    width: 70% !important;
    background-color: rgb(255, 255, 255,0.5) !important;
    border-width: thick !important;
  }
  .checkbox{ display:inline-block;
    width:90px;
    line-height:30px; 
  }
  .all{ display:inline-block;
    width:300px;
    line-height:30px; 
  }
  .panel-border{
    width: 99%;
    height: 99%;
    background-color: crimson;
  }
</style>
