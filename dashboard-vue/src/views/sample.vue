<template>
	<div>
		<h2>API 연결 확인</h2>
		<a href="javascript:;" @click="getList">GET 방식 데이터 접근</a>
	</div>
</template>

<script>
export default {
	methods:{
		getList() {
			this.$axios.get("/selectStorageDate",{
				headers: {
					
				}
			})
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

<style scoped>
</style>