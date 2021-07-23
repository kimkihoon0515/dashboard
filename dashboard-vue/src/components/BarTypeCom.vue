<template>
  <div class="chartbox">
    <bar-chart></bar-chart>
  </div>
</template>

<script>
import BarChart from './BarChart.vue'

export default {
  components: { BarChart },
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