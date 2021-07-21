<template>
  <div class='grid'>
    <bar-type-com id="slide-date"  :query="slide_date"></bar-type-com>
    <!--<bar-type-com id="scanner" ></bar-type-com>
    <bar-type-com id="pathID" ></bar-type-com>
    <bar-type-com id="size" ></bar-type-com>
    <pie-type-com id="storage" ></pie-type-com>
    <line-type-com id="storage-full" ></line-type-com>-->
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
        url:"/selectSlideDate",
        start_date: this.start,
        end_date: this.end,
        YMD: 3
      },
      scanner:{
        url:"/selectScanner",
        start_date: this.start,
        end_date: this.end,
        YMD: 3
      },
      pathID:{
        url:"/selectPid",
        start_date: this.start,
        end_date: this.end,
        YMD: 3
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