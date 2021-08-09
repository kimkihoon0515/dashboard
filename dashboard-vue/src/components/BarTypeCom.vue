<template>
    <div class="chartbox">
      <span v-if="needCheck==true" id="check-box-group">
        <input :name=query.name type="radio" value="1" v-model="YMD"><label>Y</label>
        <input :name=query.name type="radio" value="2" v-model="YMD"><label>M</label>
        <input :name=query.name type="radio" value="3" v-model="YMD" checked="checked"><label>D</label>
      </span>
      <div v-if="needCheck==false" id="filter">
        <label><input id="selectall" type="checkbox" v-model="checked">전체</label>
        <label v-for="(name, index) in Object.keys(this.origin)" :key="index"><input :id="name" :value="name" type="checkbox" v-model="checkBind">{{name}}</label>
      </div>
      <div id="chart">
      <bar-chart :datacollection="datacollection" :options="chartoptions" :change="change" @rerendered="reset"></bar-chart>
      </div>
    </div>
</template>

<script>
import BarChart from './BarChart.vue'
import _ from 'lodash'
import moment from 'moment'
export default {
  name : "BarTypeCom",
  components: { BarChart },
  props: {
    color:{
      type: String,
      default:'#f87979'
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
    }
  },
  data () {
    return {
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
        pointBackgroundColor: 'white',
        borderWidth: 1,
        pointBorderColor: '#249EBF'
        },
      change:0,
      colorset:['#f87979','#ffd950', '#02bc77', '#28c3d7', '#FF6384'],
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
        responsive: true,
        maintainAspectRatio: false
      }
    }
  },
  methods: {
    handleChartClick(evt, elements) {
      if(this.needCheck==false){
        var chart = this.$children[0]._data._chart;
        const chartIndex = chart.getElementAtEvent(evt);
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
    this.$axios.post(this.query.url, {'startDate':null,'finishDate': null, 'type':null})
    .then((res)=>{
      this.parseBarData(res, 0);
    })
    .then((err)=>{
      console.log(err);
    })
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
    }
  }
}

</script>

<style>
  #filter {
    font-size: 10pt;
    height:10%
  }
</style>