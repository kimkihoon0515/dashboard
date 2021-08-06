<template>
  <div id="container">
    <div id="static-chart" class="grid">
      <button v-for="(name, index) in storage_name_list" :key="index" v-on:click="nameChange(name)">{{name}}</button>
      <pie-type-com id="storage" :query="storage" :storageName="storageName"></pie-type-com>
      <line-type-com id="storage-full" :query="storage_full" :storageName="storageName"></line-type-com>
    </div>
    <div id="dynamic-chart" class="grid">
      <div id="daily-chart">
        <bar-type-com id="slide-date" :start_date="start" :end_date="end" :query="slide_date" :needCheck=true></bar-type-com>
        <bar-type-com id="size" :start_date="start" :end_date="end" :query="size" :needCheck=true></bar-type-com>
      </div>
      <div class="form__field">
        <div class="form__label">
          <strong @click="viewPalette">Color Palette</strong>
          <v-swatches v-if="palette==true" v-model="color" inline></v-swatches>
        </div>
      </div>
      <div id="type-chart">
      <bar-type-com id="scanner" :start_date="start" :end_date="end" :query="scanner" :needCheck=false :color="color"></bar-type-com>
      <bar-type-com id="pathID" :start_date="start" :end_date="end" :query="pathID" :needCheck=false :color="color"></bar-type-com>
      </div>
    </div>
  </div>
</template>

<script>
import BarTypeCom from '../components/BarTypeCom.vue'
import LineTypeCom from '../components/LineTypeCom.vue'
import PieTypeCom from '../components/PieTypeCom.vue'
import VSwatches from 'vue-swatches'
import 'vue-swatches/dist/vue-swatches.css'

export default {
  components: { BarTypeCom, PieTypeCom, LineTypeCom, VSwatches },
  name: 'home',
  data() {
    return {
      palette: false,
      color: '#f87979',
      slide_date:{
        name:"slide-date",
        url:"/selectSlidePerDate",
        chartName: "날짜 별 스캔 횟수",
        xKey: 0,
        yKey: [1]
        
      },
      scanner:{
        name:"scanner",
        url:"/searchScannerListByDate",
        chartName: "스캐너 별 스캔 횟수",
        xKey:0,
        yKey:[1]
      },
      pathID:{
        name:"pathID",
        url:"/searchPidByDate",
        chartName: "진단종류 별 스캔 횟수",
        xKey: 0,
        yKey: [1]
      },
      size:{
        name:"size",
        url:"/searchStorageUseByDate",
        chartName: "일별 사용량",
        xKey: 0,
        yKey: [1]
      },
      storage:{
        name:"storage",
        url:"/storageList",
        chartName: "스토리지별 총/잔여 용량",
        xKey: null,
        yKey: null
      },
      storageName:null,
      storage_name_list: [],
      storage_full:{
        name:"storage-full",
        url:"/selectStorageTableById",
        chartName:"스토리지별 사용량 추이",
        xKey: 0,
        yKey: [3]
      },
    }
  },
  props: {
    start: {
      type: String, 
      default: null
    },
    end: {
      type: String, 
      default: null
    },
    submit: {
      default:0
    }
  },
  watch:{
    color:{
      handler(){
        console.log(this.color)
      }
    }
  },
  methods:{
    nameChange(name){
      this.storageName=name;
    },
    viewPalette(){
      this.palette = !this.palette
    }
  },
  mounted() {
    this.$axios.get(this.storage.url)
    .then((res)=>{
      this.storage_name_list=res.data.map(function(elem){ return elem.storageName})
      this.storageName=this.storage_name_list[0];
    })
    .then((err)=>{
      console.log(err);
    })
  }
}
</script>

<style>
  div {
    width: 100%;
    height: 90%;
  }

  strong {
    cursor: pointer;
  }

  .grid {
    display: block;
    float: left;
    padding: 20px;
    text-align: center;
  }

  #static-chart {
    width: 28%;
    background-color: rgb(230, 249, 252);
  }
  #dynamic-chart {
    width: 72%;
  }
  
  #dynamic-chart .chartbox {
    width: 460px;
    height: 460px;
    display: inline-block;
    margin: 0 30px 30px 30px;
    overflow: hidden;
    border: 1px solid rgb(53, 196, 231);
    border-radius: 10px;
  }

  #static-chart .chartbox {
    width: 360px;
    height: 280px;
    display: inline-block;
    margin: 5px 15px 45px 15px;
    overflow: hidden; 
    border: 1px solid rgb(53, 196, 231);
    border-radius: 10px;
  }

  #static-chart .for-size {
    width: 300px;
    height: 280px;
  }
  button {
  border: 1px solid skyblue;
  border-radius: 5px;
  background-color: white;
  color: skyblue;
  padding: 5px;
  }

  button:hover {
    color: white;
    background-color: skyblue;
  }

  @media screen and (max-width: 1530px) {
    #dynamic-chart .chartbox {
      width: 410px;
      height: 250px;
    }
    #static-chart .chartbox {
      width: 310px;
      height: 260px
    }
  }
</style>