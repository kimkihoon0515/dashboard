<template>
  <div class="chartbox">
    <input type="Number" v-model="maN"/>
    <line-chart :datacollection="datacollection" :options="chartoptions" :change="change" @rerendered="reset"></line-chart>
  </div>
</template>

<script>
import LineChart from './LineChart.vue'
import moment from 'moment'
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
      maN:7,
      change:0,
     dataform:{
        label: null,
        data: null,
        //backgroundColor: null,
        fill:false,
        interaction:{
            intersect:true
          },
        pointRadius:0.7,
        pointBackgroundColor: 'white',
        borderWidth: 3,
        borderColor: []
        },
      colorset:['#f87979','#ffd950', '#02bc77', '#28c3d7', '#FF6384'],
      datacollection: {
        labels: null,
        datasets: [{
        }]
      },
      chartoptions:{
          fill:false,
          interaction:{
            intersect:false
          },
          radius:0,
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

      let currentIndex=this.getIndex(this.datacollection.labels,moment().format('YYYY-MM-DD'))
      console.log(res.data)
      console.log(currentIndex)
      for(let i=0; i<y.length ; i++){
        let tmp= _.cloneDeep(this.dataform);
        tmp.label=keys[y[i]];
        tmp.data=res.data.map(function(elem){return elem[keys[y[i]]]});
        for (let j=0; j<tmp.data.length;j++){
          if(j<=currentIndex){
            tmp.borderColor.push('#f87979')
          }
          else{
            tmp.borderColor.push('#ffd950')
          }
        }
        this.datacollection.datasets.pop();
        this.datacollection.datasets.push(tmp);
      }
      console.log(this.datacollection.datasets)
      this.change=1;
    },
    getIndex(labels,date){
      for(let i=0 ; i<labels.length; i++){
        if(labels[i]==date){
          return i
        }
      }
    }
  },
  watch:{
    maN: function(){
      this.$axios.post(this.query.url, {'storageName':this.storageName, 'n':this.maN})
      .then((res)=>{
      console.log(res)
      this.parseLineData(res);
      })
      .then((err)=>{
        console.log(err);
      })
    },
    storageName: function(){
      this.$axios.post(this.query.url, {'storageName':this.storageName, 'n':this.maN})
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
    this.$axios.post(this.query.url, {'storageName':this.storageName, 'n':this.maN})
    .then((res)=>{
      console.log(res)
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