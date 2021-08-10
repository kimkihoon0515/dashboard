<template>
    <div class="chartbox">
      <!-- 필터
      <div id="filter">
        <label><input id="selectall" type="checkbox" v-model="checked">전체</label>
        <label v-for="(name, index) in Object.keys(this.origin)" :key="index"><input :id="name" :value="name" type="checkbox" v-model="checkBind">{{name}}</label>
      </div>
      -->
      <div id="chart">
      <horizontal-bar :datacollection="datacollection" :options="chartoptions" :change="change" @rerendered="reset"></horizontal-bar>
      </div>
    </div>
</template>

<script>
import HorizontalBar from './HorizontalBar.vue'
import _ from 'lodash'

export default{
    name: "StackBarTypeCom",
    components: {HorizontalBar},
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
        return{
            change:0,
            datacollection: {
                labels: null,
                datasets: [
                    {
                        label: "Used",
                        data: [],
                        backgroundColor: '#f87979',
                        borderWidth: 1,
                        pointBorderColor: '#249EBF',
                        barPercentage:0.7
                    },
                    {
                        label: "Free",
                        data: [],
                        backgroundColor: '#3398DB',
                        borderWidth: 1,
                        pointBorderColor: '#249EBF',
                        barPercentage:0.7

                    },

                ]
            },
            chartoptions:{
                title: {
                    display: true,
                    text: this.query.chartName,
                    fontSize: 16
                },
                scales:{
                    yAxes:[{
                        gridLines: {
                            lineWidth:3,
                            offsetGridLines: true,
                            display: true,
                            drawBorder:false,
                            color:'#dedbd9'
                            
                        },
                        stacked: true
                        
                    }],
                     xAxes: [{
                        ticks:{
                            max:100,
                            beginAtZero: true, 
                        },
                        gridLines: {
                            display:true
                        },
                        stacked: true
                    }]
                },
                legend: {
                    display: false
                },
                responsive: true,
                maintainAspectRatio: false                
            }
        }
    },
    methods:{
        reset() {
            this.change=0;
        },
        parseBarData(res){
            if(res.data.length==0){
                this.datacollection.datasets.pop();
                this.change=1;
                return
            }
            this.datacollection.labels=res.data.map(function(elem){return elem.storageName})
            this.datacollection.datasets[0].data=res.data.map(function(elem){return (elem.used)/(elem.total)*100})
            this.datacollection.datasets[1].data=res.data.map(function(elem){return (elem.free)/(elem.total)*100})
            this.change=1;
        }
    },
    mounted() {

        this.$axios.get(this.query.url)
        .then((res)=>{
        this.parseBarData(res);
        })
        .then((err)=>{
        console.log(err);
        })
    },

}

</script>

<style>
  #filter {
    font-size: 10pt;
     height:10%
  }
  #chart{
    height:100%
  }
</style>