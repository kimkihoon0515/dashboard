<template>
  <div class='grid'>
    <bar-type-com id="slide-date"></bar-type-com>
    <bar-type-com id="scanner"></bar-type-com>
    <bar-type-com id="pathID"></bar-type-com>
    <bar-type-com id="size"></bar-type-com>
    <pie-type-com id="storage"></pie-type-com>
    <line-type-com id="storage-full"></line-type-com>
  </div>
</template>

<script>
import BarTypeCom from '../components/BarTypeCom.vue'
import LineTypeCom from '../components/LineTypeCom.vue'
import PieTypeCom from '../components/PieTypeCom.vue'

export default {
  components: { BarTypeCom, PieTypeCom, LineTypeCom },
  name: 'home',
  data() {
    return {
      
    }
  },
  props: {
    start: '',
    end: ''
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