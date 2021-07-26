<template>
  <div class="chartbox">
    <span v-if="needCheck==true" id="check-box-group">
      <input :name=query.name type="radio"><label>Y</label>
      <input :name=query.name type="radio"><label>M</label>
      <input :name=query.name type="radio" checked="checked"><label>D</label>
    </span>
    <span v-if="needCheck==false" id="filter">
      <label v-for="(name, index) in this.datacollection.labels" :key="index"><input type="checkbox" checked="checked">{{name}}</label>
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
      for(let i=0; i<y.length ; i++){
        let tmp= _.cloneDeep(this.dataform);
        tmp.label=keys[y[i]];
        tmp.data=res.data.map(function(elem){return elem[keys[y[i]]]});
        tmp.backgroundColor=this.colorset[i];
        console.log(tmp);
        this.datacollection.datasets.push(tmp);
      }
      this.change=1;
    }
  },
  mounted() {
    this.$axios.post(this.query.url, {'startDate':this.start_date,'finishDate':this.end_date})
    .then((res)=>{
      this.parseBarData(res);
    })
    .then((err)=>{
      console.log(err);
    })
  },
  computed:{
    changeDate() {
      return `${this.start_date}|${this.end_date}`;
    }
  },
  watch: {
    changeDate:{
      handler(){
        this.$axios.post(this.query.url, {'startDate':this.start_date,'finishDate':this.end_date})
        .then((res)=>{
          console.log(res.data)
          this.parseBarData(res);
        })
        .then((err)=>{
          console.log(err);
        })
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