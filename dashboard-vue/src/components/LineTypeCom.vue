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
      }
    }
  },
  methods: {
    reset() {
      this.change=0;
      //console.log(this.change);
    }
  },
  mounted() {
    //console.log(this.query.start_date);
    //console.log(this.query.end_date);
    this.$axios.post(this.query.url, {'startDate':this.query.start_date,'finishDate':this.query.end_date,'type':3})
    //this.$axios.get(this.query.url)
    .then((res)=>{
      //console.log(res.data);
      var x= this.query.xKey;
      var y= this.query.yKey;
      var keys= Object.keys(res.data[0]);
      this.datacollection.labels=res.data.map(function(elem){return elem[keys[x]]});
      this.datacollection.datasets[0].label=this.query.name;
      this.datacollection.datasets[0].data=res.data.map(function(elem){return elem[keys[y]]});
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