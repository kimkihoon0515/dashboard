<template>
  <div class="chartbox">
    <bar-chart :datacollection="datacollection" :options="chartoptions"></bar-chart>
  </div>
</template>

<script>
import BarChart from './BarChart.vue'
import _ from "lodash"

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
    getList() {
			this.$axios.get(this.query.url)
			.then((res)=>{
        console.log(res.data);
			})
			.then((err)=>{
				console.log(err);
			})
		}
  },
  mounted() {
    this.$axios.get(this.query.url)
    .then((res)=>{
      //console.log(this.query.name);
      //console.log(this.query.url);
      var keys= Object.keys(res.data[0]);
      //console.log(res.data[0]);
      //console.log(keys[0]);
      var tmp= _.cloneDeep(this.datacollection);
      tmp.labels=res.data.map(function(elem){return elem[keys[0]]});
      //console.log(this.datacollection.labels);
      tmp.datasets[0].label=this.query.name;
      tmp.datasets[0].data=res.data.map(function(elem){return elem[keys[1]]});
      //console.log(this.datacollection.datasets[0].data);
      //console.log(this.datacollection);
      this.datacollection= _.cloneDeep(tmp);

    })
    .then((err)=>{
      console.log(err);
    })
  }
}
</script>

<style>
  
</style>