<template>
  <div class="chartbox">
    <div id="filter">
      <strong style="margin-left:2px;"> MA:
        <input id="param" type="Number" min=1 v-model="maN"/>
      </strong>
      <span id="predict">용량 초과 예정일: {{predictDate}}</span>
    </div>
    <div id="chart">
      <line-chart class="line" :datacollection="datacollection" :options="chartoptions" :change="change" @rerendered="reset"></line-chart>
    </div>
  </div>
</template>

<script>
import LineChart from './LineChart.vue'
import moment from 'moment'

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
      gradient:null,
      predictDate:null,
      maN:120,
      change:0,
      dataform:{
        label: null,
        data: null,
        interaction:{
            intersect:true
          },
        pointRadius:0,
        pointBackgroundColor: "transparent",
        backgroundColor:null,
        borderWidth: 2,
        borderColor: '#1FBC9C',
        borderDash:null
        },
      colorset:['#f87979','#ffd950', '#02bc77', '#28c3d7', '#FF6384','#fdfff5'],
      datacollection: {
        labels: null,
        datasets: [{
        }]
      },
      chartoptions:{
        //modal기능을 위한 사용자 클릭 속성
        onClick : this.chartClick,
        animation:{
          duration:2000
        },
        elements:{
          point: {
            backgroundColor: 'white'
          }
        },
        interaction:{
          mode:'dataset'
        }
        ,
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
        xAxes: [{
          gridLines: {
            display: false
          },
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
  methods: {
    chartClick(){
      //상위 컴포넌트(Home)에 이벤트 전달
      //modal기능
      this.$emit("show");
    },
    reset() {
      //재렌더링을 위한 리셋 함수
      this.change=0;
    },
    parseLineData(res){ //호출한 데이터를 차트에 적용하는 함수
      var ctx= this.$children[0]._data._chart.canvas.getContext("2d");
      console.log(ctx)
      var gradientFill = ctx.createLinearGradient(0, 0, 0, 450);
      gradientFill.addColorStop(0.1, "rgba(31, 188,156,0.6 )");
      gradientFill.addColorStop(0.2, "rgba(31, 188,156, 0.5)");
      gradientFill.addColorStop(0.4, "rgba(31, 188,156, 0.2)");
      gradientFill.addColorStop(0.6, "rgba(31, 188,156, 0.1)");
      gradientFill.addColorStop(0.8, "rgba(31, 188,156, 0.1)");
      gradientFill.addColorStop(1, "rgba(31, 188,156, 0)");
      var x= this.query.xKey;
      var y= this.query.yKey;
      let total=res.data[0].total
      console.log(total)
      var keys= Object.keys(res.data[0]);
      this.datacollection.labels=res.data.map(function(elem){return elem[keys[x]]});
      if(moment().isAfter(moment(this.datacollection.labels[res.data.length-1]))){
        this.predictDate="∞"
      }
      else{
        this.predictDate=this.datacollection.labels[this.datacollection.labels.length-1]
      }
      let currentIndex=this.getIndex(this.datacollection.labels,moment().add(1,'days').format('YYYY-MM-DD'))
      console.log(res.data)
      console.log("current: "+currentIndex)
      for(let i=0; i<y.length ; i++){
        let tmp= _.cloneDeep(this.dataform);
        tmp.label=keys[y[i]];
        tmp.data=res.data.map(function(elem){return ((elem[keys[y[i]]])/ total *100).toFixed(1)});
        let tmpPredict= _.cloneDeep(tmp);
        tmpPredict.label=keys[y[i]]+"예측값";
        tmpPredict.borderDash=[5,5]
        for (let j=currentIndex+1; j<tmpPredict.data.length;j++){
          tmp.data.pop();
        }
        for (let k=0; k<=currentIndex;k++){
          tmpPredict.data[k]=null;
          if(k==currentIndex){
            tmpPredict.data[k]=""
          }
        }
        this.datacollection.datasets=[];
        tmp.backgroundColor=gradientFill;
        tmpPredict.backgroundColor=gradientFill;
        this.datacollection.datasets.push(tmp);
        this.datacollection.datasets.push(tmpPredict);
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
    maN: function(){ //이동평균 파라미터 수정시 예측 데이터 다시 호출
      this.$axios.post(this.query.url, {'n':this.maN})
      .then((res)=>{
      console.log(res)
      this.parseLineData(res);
      })
      .then((err)=>{
        console.log(err);
      })
    },
  },
  mounted() {//인스턴스 마운트 된 후 데이터 호출
    this.$axios.post(this.query.url, {'n':this.maN})
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
  #filter {
    font-size: 10pt;
    height:10%
  }
  #MA{
    float: left;
    width: 30%;
  }
  #param{
    width: 17%;
    margin: 5px 5px 0 5px;
    border: 1px solid #eee !important;
    box-shadow: 0 1px 3px 0 rgba(0,0,0,0.50);
  }
  #predict{
    margin-top:7px;
    float: right;
    width: 66%;
    font-size: 12px;
  }
  .line{
    height: 87%;
    margin: 0 0 20px 0;
  }
  .modal-container{
    position: relative;
    height: 100%;
    
  }
  .modal-header{
    height:25%;
  }
  .modal-body{
    border-radius: 10px;
    background-color: rgb(255, 255, 255);
    height: 50%;
  }
  .modal-footer{
    height:50%
  }
</style>