<template>
  <div>
    <div class="container_A grid">
      <div id="slide-date">
        <bar-type-com id="slide-date" :start_date="start" :end_date="end" :query="tabs? size:slide_date" :needCheck=true @tabChange="tabChange"></bar-type-com>
      </div>
      <div id="under">
        <storage-table class="chartbox" @rowclick="rowclick"></storage-table>
        <stack-bar-type-com class="chartbox" :query="storage"></stack-bar-type-com>
        <line-type-com id="storage-full" :query="storage_full" :storageName="storageName"></line-type-com>
      </div>
    </div>
    <div class="form__field grid">
      <div class="form__label">
        <strong @click="viewPalette">Color Palette</strong>
        <v-swatches v-if="palette==true" v-model="color" :swatches="swatches" row-length="10" inline></v-swatches>
      </div>
    </div>
    <div class="container_B grid">
      <div id="right">
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
import StackBarTypeCom from '../components/StackBarTypeCom.vue'
import VSwatches from 'vue-swatches'
import 'vue-swatches/dist/vue-swatches.css'
import StorageTable from '../components/StorageTable.vue'
export default {
  components: { BarTypeCom, PieTypeCom, LineTypeCom, VSwatches,StorageTable, StorageTable,StackBarTypeCom },
  name: 'home',
  data() {
    return {
      tabs:0,
      palette: false,
      color: '#f87979',
      slide_date:{
        name:"slide-date",
        url:"/selectSlidePerDate",
        chartName: "스캔 완료 횟수",
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
        chartName: "메모리 사용량",
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
      swatches: ['#1FBC9C', '#1CA085', '#2ECC70', '#27AF60', '#3398DB', '#2980B9', '#A463BF', '#8E43AD', '#3D556E', '#222F3D']
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
      console.log(name)
      this.storageName=name;
    },
    rowclick(selectStorage){
      this.nameChange(selectStorage)
    },
    viewPalette(){
      this.palette = !this.palette
    },
    tabChange(tab){
      this.tabs=tab
      console.log(this.tabs)
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
    padding: 10px;
    text-align: center;
  }
  
  .container_A {
    width: 68%;
  }

  .form__field {
    width: 4%;
  }

  .container_B {
    width: 28%;
  }

  #chart{
    height:100%
  }
  
  .container_A #slide-date .chartbox {
    width: 97%;
    height: 320px;
    display: inline-block;
    margin: 0 5px 10px 5px;
    overflow: hidden;
    border-radius: 10px;
    box-shadow: 0 2px 4px 0 rgba(0,0,0,0.50);
  }

  .container_A #under .chartbox {
    width: 32%;
    height: 300px;
    display: inline-block;
    margin: 0 5px 10px 5px;
    overflow: hidden;
    border-radius: 10px;
    box-shadow: 0 2px 4px 0 rgba(0,0,0,0.50);
  }

  .container_B .chartbox {
    width: 380px;
    height: 310px;
    display: inline-block;
    margin: 0 10px 10px 10px;
    overflow: hidden;
    border-radius: 10px;
    box-shadow: 0 2px 4px 0 rgba(0,0,0,0.50);
  }

  input {
    background-color: #fff;
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

  @media screen and (max-width: 1450px) {
    .container_A #slide-date .chartbox {
      width: 860px;
      height: 320px;
      display: inline-block;
      margin: 0 5px 10px 5px;
      overflow: hidden;
      border-radius: 10px;
      box-shadow: 0 2px 4px 0 rgba(0,0,0,0.50);
    }

    .container_A #under .chartbox {
      width: 265px;
      height: 300px;
      display: inline-block;
      margin: 0 5px 10px 5px;
      overflow: hidden;
      border-radius: 10px;
      box-shadow: 0 2px 4px 0 rgba(0,0,0,0.50);
    }

    .container_B .chartbox {
      width: 330px;
      height: 310px;
      display: inline-block;
      margin: 0 10px 10px 10px;
      overflow: hidden;
      border-radius: 10px;
      box-shadow: 0 2px 4px 0 rgba(0,0,0,0.50);
    }
  }

  @media screen and (max-width: 1324px) {
    .container_A {
      width: 68%;
    }

    .form__field {
      width: 4%;
    }

    .container_B {
      width: 28%;
    }
    .container_A #slide-date .chartbox {
      width: 820px;
      height: 320px;
      display: inline-block;
      margin: 0 0px 10px 0px;
      overflow: hidden;
      border-radius: 10px;
      box-shadow: 0 2px 4px 0 rgba(0,0,0,0.50);
    }

    .container_A #under .chartbox {
      width: 245px;
      height: 300px;
      display: inline-block;
      margin: 0 5px 10px 5px;
      overflow: hidden;
      border-radius: 10px;
      box-shadow: 0 2px 4px 0 rgba(0,0,0,0.50);
    }

    .container_B .chartbox {
      width: 320px;
      height: 310px;
      display: inline-block;
      margin: 0 10px 10px 10px;
      overflow: hidden;
      border-radius: 10px;
      box-shadow: 0 2px 4px 0 rgba(0,0,0,0.50);
    }
  }
</style>