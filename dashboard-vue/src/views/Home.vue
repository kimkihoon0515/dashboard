<template>
  <div class='grid'>
    <dashboard-block id="countSlide"></dashboard-block>
    <dashboard-block id="countScaner"></dashboard-block>
    <dashboard-block id="countPathId"></dashboard-block>
    <dashboard-block id="size"></dashboard-block>
    <dashboard-block id="storage"></dashboard-block>
    <dashboard-block id="storageFull"></dashboard-block>
  </div>
</template>

<script>
import DashboardBlock from '../components/DashboardBlock.vue'

export default {
  components: { DashboardBlock },
  name: 'home'
}
</script>

<style>
  .grid {
  display: block;
  width: 1100px;
  margin: 20px auto;
  }
</style>