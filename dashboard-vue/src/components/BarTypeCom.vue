<template>
  <div class="chartbox">
    <span v-if="needCheck==true" id="check-box-group">
      <input :name=query.name type="radio" value="1" v-model="YMD"><label>Y</label>
      <input :name=query.name type="radio" value="2" v-model="YMD"><label>M</label>
      <input :name=query.name type="radio" value="3" v-model="YMD" checked="checked"><label>D</label>
    </span>
    <span v-if="needCheck==false" id="filter">
      <label v-for="(name, index) in Object.keys(this.origin)" :key="index"><input :id="name" :value="name" type="checkbox" v-model="checkBind">{{name}}</label>
    </span>
    <bar-chart :datacollection="datacollection" :options="chartoptions" :change="change" @rerendered="reset"></bar-chart>
  </div>
</template>

<script>
import BarChart from './BarChart.vue'
import _ from 'lodash'
export default {
  name : "BarTypeCom",
  components: { BarChart },
  props: {
    needCheck: false,
    query: {
       type: Object,
       default: null
    },
    start_date: '',
    end_date: ''
  },

  data () {
    return {
      origin: {},
      checkBind: [],
      labelList: [],
      YMD: 3,
      dataform:{
        label: null,
        data: null,
        backgroundColor: null,
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
          scales: {
              yAxes: [{
                  ticks: {
                      beginAtZero: true
                  },
                  gridLines: {
                      display: true
                  },
                  stacked: false
              }],
              xAxes: [ {
              
                  gridLines: {
                      display: false
                  },
                  stacked: false
              }]
          },
          legend: {
              display: true
          },
          responsive: true,
          maintainAspectRatio: false
      }
    }
  },
  methods: {
    reset() {
      this.change=0;
      //console.log(this.change);
    },
    parseBarData(res){
      var x= this.query.xKey;
      var y= this.query.yKey;
      //console.log(this.query.yKey);
      var keys= Object.keys(res.data[0]);
      this.datacollection.labels=res.data.map(function(elem){return elem[keys[x]]});
      var originLabel=res.data.map(function(elem){return elem[keys[x]]});
      for(let i=0; i<y.length ; i++){
        let tmp= _.cloneDeep(this.dataform);
        tmp.label=keys[y[i]];
        tmp.data=res.data.map(function(elem){return elem[keys[y[i]]]});
        tmp.backgroundColor=this.colorset[i];
        console.log(tmp);
        this.datacollection.datasets.pop();
        this.datacollection.datasets.push(tmp);
        for(let i=0; i<originLabel.length; i++){
        this.origin[originLabel[i]]=tmp.data[i];
        this.checkBind.push(originLabel[i]);
        this.labelList.push(originLabel[i]);
        }
      }
      this.change=1;
    },
    parseBarData_check(){
      let tmp= _.cloneDeep(this.dataform);
      console.log(tmp);
      for (var i=0; i<this.labelList.length; i++){
        if(this.labelList[i] in this.checkBind){
          tmp.data.push(this.origin[this.labelList[i]]);
          this.datacollection.labels.push(this.labelList[i]);
        }
      }
      tmp.backgroundColor=this.colorset[0];
      tmp.label=this.query.name;
      this.datacollection.datasets.pop();
      this.datacollection.datasets.push(tmp);
      this.change=1;
    }
  },
  mounted() {
    this.$axios.post(this.query.url, {'startDate':this.start_date,'finishDate':this.end_date, 'type':this.YMD})
    .then((res)=>{
      this.parseBarData(res);
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
    changeDate:{
      handler(){
        this.$axios.post(this.query.url, {'startDate':this.start_date,'finishDate':this.end_date, 'type':this.YMD})
        .then((res)=>{
          console.log(res.data)
          this.parseBarData(res);
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
    }
  }
}

</script>

<style>
  #filter {
    font-size: 10pt;
  }

  div{
    width: 100%;
    height:90%
  }
</style>