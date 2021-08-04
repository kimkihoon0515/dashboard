<template>
  <div class="chartbox">
    <line-chart :datacollection="datacollection" :options="chartoptions" :change="change" @rerendered="reset"></line-chart>
  </div>
</template>

<script>
import LineChart from './LineChart.vue'

var gradientStroke
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
      change:0,
     dataform:{
        label: null,
        data: null,
        backgroundColor: null,
        pointRadius : 0,
        //pointBackgroundColor: 'white',
        borderWidth: 1,
        //pointBorderColor: '#249EBF'

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
    },
    parseLineData(res){
      var x= this.query.xKey;
      var y= this.query.yKey;
      var keys= Object.keys(res.data[0]);
      this.datacollection.labels=res.data.map(function(elem){return elem[keys[x]]});
      for(let i=0; i<y.length ; i++){
        let tmp= _.cloneDeep(this.dataform);
        tmp.label=keys[y[i]];
        tmp.data=res.data.map(function(elem){return elem[keys[y[i]]]});
        tmp.backgroundColor=this.colorset[i];
        this.datacollection.datasets.pop();
        this.datacollection.datasets.push(tmp);
      }
      this.change=1;
    }
  },
  watch:{
    storageName: function(){
      this.$axios.post(this.query.url, {'storageName':this.storageName})
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
    this.$axios.post(this.query.url, {'storageName':this.storageName})
    .then((res)=>{
      this.parseLineData(res);
    })
    .then((err)=>{
      console.log(err);
    })
  }
}
</script>

<style>

</style>