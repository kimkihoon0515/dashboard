<template>
	<div>
		<h2>API 연결 확인</h2>
		<a href="javascript:;" @click="getList">GET 방식 데이터 접근</a>
    <button @click="com">데이터 생성</button>
    <p>{{ per }}</p>
    <!-- <bar-chart class="scannertype" :datacollection="storageDataset" :option="chartoptions"></bar-chart> -->
    <h3>Reactivity - Live update upon change in datasets</h3>
    <reactive :chart-data="datacollection"></reactive>
    <button class="button is-primary" @click="fillData()">Randomize</button>
	</div>
</template>

<script>
// import BarChart from '@/components/BarChart'
import Reactive from '@/components/Reactive'

export default {
  name: 'Sample',
  components: { Reactive },
  data () {
    return {
      storageList: [],
      storageName: [],
      total: [],
      used: [],
      chartoptions:{
        scales: {
          yAxes: [{
            ticks: {
              beginAtZero: true
            },
            gridLines: {
              display: true
            }
          }],
          xAxes: [{
            gridLines: {
              display: false
            }
          }]
        },
        legend: {
          display: true
        },
        responsive: true,
        maintainAspectRatio: false
      },
      storageDataset:{
        labels: this.storageName,
        datasets: [{
          label: 'Data One',
          data: this.per,
          backgroundColor: '#f87979',
          pointBackgroundColor: 'white',
          borderWidth: 1,
          pointBorderColor: '#249EBF'
        }],
      }      
    }
  },
  computed: {
    per: function() {
      let percent = []
      for (let i=0; i<this.storageList.length; i++) {
        percent[i] = this.used[i] / this.total[i];
      }
      return percent;
    }
  },
	methods:{
		getList() {
      var vm = this;
			this.$axios.get("/storageList")
			.then((res)=>{
				console.log(res);
        this.storageList = res.data;
			})
			.then((err)=>{
				console.log(err);
			})
		},
    com() {
      for (let i=0; i<this.storageList.length; i++) {
        this.total[i] = this.storageList[i].total;
        this.used[i] = this.storageList[i].used;
        this.storageName[i] = this.storageList[i].storageName;
      }
    }
	}
}
</script>

<style scoped>

</style>