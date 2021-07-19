<template>
  <div class='grid'>
    <dashboard-block></dashboard-block>
    <dashboard-block></dashboard-block>
    <dashboard-block></dashboard-block>
    <dashboard-block></dashboard-block>
    <dashboard-block></dashboard-block>
    <dashboard-block></dashboard-block>
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