<template>
  <div class="chartbox">
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
    
    query: {
       type: Object,
       default: null
    }
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
      }
    }
  },
  methods: {
    reset() {
      this.change=0;
      console.log(this.change);
    }
  },
  mounted() {
    this.$axios.get(this.query.url)
    .then((res)=>{
      //console.log(this.query.name);
      //console.log(this.query.xKey);
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
    })
    .then((err)=>{
      console.log(err);
    })
  }
}
</script>

<style>
  
</style>