<template>
  <div class="chartbox">
    <div id="filter">
      <v-expansion-panels class="mb-6">
        <v-expansion-panel>
          <v-expansion-panel-header expand-icon="mdi-menu-down">
          Filter
          </v-expansion-panel-header>
            <v-expansion-panel-content style="height:auto;">
              <label  style="display: inline-flexbox;"><input id="selectall" type="checkbox" v-model="checked">전체</label> 
              <br>
              <label class="checkbox" v-for="(name, index) in this.labelList" :key="index"><input :id="name" :value="name" type="checkbox" v-model="checkBind" style="float:left; margin-top:10px;">{{name}} </label>
            </v-expansion-panel-content>
        </v-expansion-panel>
      </v-expansion-panels>
    </div>
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
      checked:true, //체크박스에 양방향 바인딩 되는 데이터
      origin: {},
      checkBind: [], // 체크박스에 따라 실제로 차트에 그려질 그래프의 라벨리스트
      labelList: [], // 호출한 데이터 전체에 대한 라벨리스트
      change:0,
      datacollection: {
        labels: null,
        datasets: [//차트에 들어갈 데이터셋
          {//사용중인 용량
            label: "Used",
            data: [],
            backgroundColor: '#f8797980',
            borderWidth: 1,
            pointBorderColor: '#249EBF',
            barPercentage:0.7
          },
          {//사용가능한 빈 용량
            label: "Free",
            data: [],
            backgroundColor: '#3398DB80',
            borderWidth: 1,
            pointBorderColor: '#249EBF',
            barPercentage:0.7
          }
        ]
      },
      chartoptions:{
        animation:{
          duration:0
        },
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
        tooltips: {
          callbacks: {
            label: function(tooltipItem, data) {
              var dataset = data.datasets[tooltipItem.datasetIndex];
              var currentValue = dataset.data[tooltipItem.index];
              return currentValue +'%';
            },
          },
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
    parseBarData(res){ //호출한 데이터를 차트에 적용하는 함수
      if(res.data.length==0){
        this.datacollection.datasets.pop();
        this.change=1;
        this.labelList=null
        this.checkBind=null
        return
      }
      this.origin=res.data
      this.checkBind=res.data.map(function(elem){return elem.storageName});
      this.labelList=res.data.map(function(elem){return elem.storageName});
      this.datacollection.labels=res.data.map(function(elem){return elem.storageName})
      this.datacollection.datasets[0].data=res.data.map(function(elem){return ((elem.used)/(elem.total)*100).toFixed(1)})
      this.datacollection.datasets[1].data=res.data.map(function(elem){return ((elem.free)/(elem.total)*100).toFixed(1)})
      this.change=1;
      console.log(this.datacollection.datasets)
    }
  },
  mounted() {//인스턴스 마운트 된 후 데이터 호출
    this.$axios.get(this.query.url)
    .then((res)=>{
      this.parseBarData(res);
    })
    .then((err)=>{
      console.log(err);
    })
  },
  watch:{
    checked:{ // 체크박스의 변화를 감지하고 차트를 그리는 라벨리스트 수정
      handler(){
        if(this.checked==true){
          this.checkBind=_.cloneDeep(this.labelList);
        }
        else{
          this.checkBind=[] 
        }
      }
    },
    checkBind:{ // 체크박스가 변화하면 호출되며, 체크박스에 맞게 차트 재렌더링
      handler(){
        this.datacollection.datasets[0].data=[]
        this.datacollection.datasets[1].data=[]
        this.datacollection.labels=[];
        for(let i=0; i<this.labelList.length;i++){
          if(this.checkBind.includes(this.labelList[i])){
            this.datacollection.labels.push(this.labelList[i]);
            this.datacollection.datasets[0].data.push(((this.origin[i].used)/(this.origin[i].total)*100).toFixed(1));
            this.datacollection.datasets[1].data.push(((this.origin[i].free)/(this.origin[i].total)*100).toFixed(1));
          }
        }
        this.change=1
      }
    }
  }
}

</script>

<style>
  #filter {
    font-size: 10pt;
    height:10%;
  }
  #chart{
    height:100%;
    width: 98%;
    margin:0px 5px;
    float: left;
  }
</style>