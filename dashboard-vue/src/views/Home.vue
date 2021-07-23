<template>
  <div id="container">
    <div id="static-chart" class="grid">
      <pie-type-com id="storage" :query="storage"></pie-type-com>
      <line-type-com id="storage-full" :query="storage_full"></line-type-com>
    </div>
    <div id="dynamic-chart" class="grid">
      <!-- <menubar></menubar> -->
      <bar-type-com id="slide-date"  :query="slide_date" :needCheck=true></bar-type-com>
      <bar-type-com id="size" :query="size" :needCheck=true></bar-type-com>
      <bar-type-com id="scanner" :query="scanner"></bar-type-com>
      <bar-type-com id="pathID" :query="pathID"></bar-type-com>
      <!--<pie-type-com id="storage" ></pie-type-com>
      <line-type-com id="storage-full" ></line-type-com>-->
    </div>
  </div>
</template>

<script>
import BarTypeCom from '../components/BarTypeCom.vue'
import LineTypeCom from '../components/LineTypeCom.vue'
// import Menubar from '../components/Menubar.vue'
import PieTypeCom from '../components/PieTypeCom.vue'


export default {
  components: { BarTypeCom, PieTypeCom, LineTypeCom },
  name: 'home',
  data() {
    return {
      slide_date:{
        name:"slide-date",
        url:"/showSlideDate",
        start_date: this.start,
        end_date: this.end,
        YMD: 3,
        xKey: 0,
        yKey: [1]
      },
      scanner:{
        name:"scanner",
        url:"/selectScanner",
        start_date: this.start,
        end_date: this.end,
        YMD: 3,
        xKey:0,
        yKey:[1]
      },
      pathID:{
        name:"pathID",
        url:"/showPid",
        start_date: this.start,
        end_date: this.end,
        YMD: 3,
        xKey: 0,
        yKey: [1]
      },
      size:{
        name:"size",
        url:"/selectStorageUse",
        start_date: this.start,
        end_date: this.end,
        YMD: 3,
        xKey: 0,
        yKey: [1]
      },
      storage:{
        name:"storgae",
        url:"/storageList",
        start_date: this.start,
        end_date: this.end,
        YMD: 3,
        xKey: null,
        yKey: null
      },
      storage_full:{
        name:"storage-full",
        url:"/showStorageFree",
        start_date: this.start,
        end_date: this.end,
        YMD: 3,
        xKey: 0,
        yKey: [2]
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
    }
  }
}
</script>

<style>
  div {
    width: 100%;
    height: 100%;
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
  width: 350px;
  height: 300px;
  display: inline-block;
  margin: 0 30px 50px 30px;
  overflow: hidden;
  border: 1px solid rgb(53, 196, 231);
  border-radius: 10px;
  /* float: left; */
  }

  #static-chart .chartbox {
  width: 360px;
  height: 300px;
  display: inline-block;
  margin: 0 15px 50px 15px;
  overflow: hidden;
  border: 1px solid rgb(53, 196, 231);
  border-radius: 10px;
  /* float: left; */
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
</style>