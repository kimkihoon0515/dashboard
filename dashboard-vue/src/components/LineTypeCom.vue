<template>
  <div class="chartbox">
    <line-chart :datacollection="datacollection" :options="chartoptions" :change="change" @rerendered="reset"></line-chart>
  </div>
</template>

<script>
import LineChart from './LineChart.vue'
export default {
  name : "LineTypeCom",
  components: { LineChart },
  props: {
    
    query: {
       type: Object,
       default: null
    }
  },
  data () {
    return {
      change:0,
      datacollection: {
        labels: null,
        datasets: [{
                    label: null,
                    data: null,
                    backgroundColor: '#f87979',
                    pointBackgroundColor: 'white',
                    borderWidth: 1,
                    pointBorderColor: '#249EBF'
        }]
      },
      chartoptions:{
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
      //console.log(this.change);
    },
    parseLineData(res){
      var x= this.query.xKey;
      var y= this.query.yKey;
      console.log(res);
      var keys= Object.keys(res.data[0]);
      this.datacollection.labels=res.data.map(function(elem){return elem[keys[x]]});
      for(let i=0; i<y.length ; i++){
        let tmp= _.cloneDeep(this.dataform);
        tmp.label=keys[y[i]];
        tmp.data=res.data.map(function(elem){return elem[keys[y[i]]]});
        tmp.backgroundColor=this.colorset[i];
        console.log(tmp);
        this.datacollection.datasets.pop();
        this.datacollection.datasets.push(tmp);
      }
      this.change=1;
    }
  },
  mounted() {
    console.log(this.query.startDate)
    this.$axios.post(this.query.url, {'startDate':this.query.start_date,'finishDate':this.query.end_date})
    .then((res)=>{
      console.log(res.data);
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