<template>
  <div class="chartbox">
    <div></div>
    <pie-chart></pie-chart>
  </div>
</template>

<script>
import PieChart from './PieChart.vue'
export default {
  components: { PieChart },
  data () {
    return {
      lables: [],
      chartData: [],
      query: []
    }
  },
  methods: {
    getList(query) {
      var vm = this;
			this.$axios.get(query)
			.then((res)=>{
				console.log(res);
			})
			.then((err)=>{
				console.log(err);
			})
		}
  }
}
</script>

<style>

</style>