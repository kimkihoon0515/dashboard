<template>
  <div class="chartbox">
    <button v-for="(name, index) in storage_name_list" :key="index" v-on:click="nameset(name)">{{name}}</button>
    <div id="for-size">
      <pie-chart :datacollection="datacollection" :options="chartoptions" :change="change" @rerendered="reset"></pie-chart>
    </div>
  </div>
</template>

<script>
import PieChart from './PieChart.vue'
export default {
  name : "PieTypeCom",
  components: { PieChart },
    props: {
    
    query: {
       type: Object,
       default: null
    }
  },
  data () {
    return {
      storage_list:null,
      storage_name_list: null,
      change:0,
      datacollection: {
        labels: ["Free","Used"],
        datasets: [{
                    label: null,
                    data: null,
                    backgroundColor: ['#ffd950', '#02bc77', '#28c3d7', '#FF6384','#f87979'],
                    pointBackgroundColor: 'white',
                    borderWidth: 1,
                    pointBorderColor: '#249EBF'
        }
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
    nameset: function (name) {
      this.datacollection.datasets[0].label=name;
      for(let i=0; i<this.storage_list.length; i++){
        if(this.storage_list[i].storageName==name){
          this.datacollection.datasets[0].data=[this.storage_list[i].free,this.storage_list[i].used];
        }
      }
      this.change=1;
    }    
  },
  mounted() {
    this.$axios(this.query.url)
    .then((res)=>{
      this.storage_list = res.data
      //console.log(this.storage_list);
      this.storage_name_list=res.data.map(function(elem){ return elem.storageName})
      this.datacollection.datasets[0].data=[res.data[0].free,res.data[0].used];
      this.datacollection.datasets[0].label= this.storage_list[0];
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