<template>
  <div class="chartbox">
    <line-chart></line-chart>
  </div>
</template>

<script>
import LineChart from './LineChart.vue'
export default {
  components: { LineChart },
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