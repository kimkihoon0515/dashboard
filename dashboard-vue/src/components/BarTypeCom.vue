<template>
  <div class="chartbox">
    <bar-chart :datacollection="datacollection" :options="chartoptions"></bar-chart>
  </div>
</template>

<script>
import BarChart from './BarChart.vue'

export default {
  components: { BarChart },
  props: {
    name: {
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
    getList(query) {
			this.$axios.get(query.url)
			.then((res)=>{
        var keys= Object.keys(res);
				this.datacollection.labels=res.data.map(function(elem){return elem.keys[0]});
        this.datacollection.datasets[0].label=this.name;
        this.datacollection.datasets[0].data==res.data.map(function(elem){return elem.keys[1]});
			})
			.then((err)=>{
				console.log(err);
			})
		}
  },
  mounted() {
    this.$axios.get(query.url)
    .then((res)=>{
      var keys= Object.keys(res);
      this.datacollection.labels=res.data.map(function(elem){return elem.keys[0]});
      this.datacollection.datasets[0].label=this.name;
      this.datacollection.datasets[0].data==res.data.map(function(elem){return elem.keys[1]});
    })
    .then((err)=>{
      console.log(err);
    })
  }
}
</script>

<style>
  
</style>