<template>
  <div class="chartbox">

  </div>
</template>

<script>
export default {
  data() {
    return {
      chartType: '',
      query: '',
      labels: '',
      chartData: ''
    }
  },
  props: {

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