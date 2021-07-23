<template>
  <div class='grid'>
    <bar-type-com id="slide-date"  :query="slide_date"></bar-type-com>
    <bar-type-com id="scanner" :query="scanner"></bar-type-com>
    <bar-type-com id="pathID" :query="pathID"></bar-type-com>
    <bar-type-com id="size" :query="size"></bar-type-com>
    <pie-type-com id="storage" :query="storage"></pie-type-com>
    <line-type-com id="storage-full" :query="storage_full"></line-type-com>
  </div>
</template>

<script>
import BarTypeCom from '../components/BarTypeCom.vue'
// import DashboardBlock from '../components/DashboardBlock.vue'
import LineTypeCom from '../components/LineTypeCom.vue'
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
  .grid {
  display: block;
  width: 1100px;
  margin: 20px auto;
  }
  .chartbox {
  width: 500px;
  height: 400px;
  display: block;
  margin: 0 15px 50px 15px;
  overflow: hidden;
  border: 1px solid rgb(53, 196, 231);
  border-radius: 10px;
  float: left;
}
</style>