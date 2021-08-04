<template>
  <div id="app">
    <div id="nav">
      <form>
        <button @click="aYearAgo">일 년</button>
        <button @click="aMonthAgo">한 달</button>
        <button @click="setToday">오늘</button>
        <input type="date" id='start-date' value=''/>
        <input type="date" id='end-date' value=''/>
        <button @click="setDate">submit</button>
      </form>
    </div>
    <div class="margindiv"></div>
    <home :start="startDate" :end="endDate"></home>
    <!--<router-view :start="startDate" :end="endDate"></router-view>-->
  </div>
</template>

<script>
import home from "./views/Home.vue"
import moment from 'moment'
export default {
  components: {
    home
  },
  data () {
    return {
      startDate: null,
      endDate: null,
    }
  },
  methods: {
    setDate() {
      this.startDate = document.querySelector("#start-date").value;
      this.endDate = document.querySelector("#end-date").value;
    },
    aYearAgo() {
      this.endDate = moment().format('YYYY-MM-DD')
      this.startDate = moment().subtract(1,'years').format('YYYY-MM-DD')

    },
    aMonthAgo(){
      this.endDate = moment().format('YYYY-MM-DD')
      this.startDate = moment().subtract(1,'months').format('YYYY-MM-DD')
    },
    setToday(){
      this.startDate = moment().format('YYYY-MM-DD')
      this.endDate = moment().format('YYYY-MM-DD')
    }
  },
  mounted(){
    setTimeout(this.setToday,30);

  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: right;
  color: #2c3e50;
}

#nav {
  position: fixed;
  padding: 30px;
  margin-bottom: 5px;
  width: 100%;
  height: 60px;
  background-color: rgb(53, 196, 231);
  box-shadow: 0 1px 3px 0 rgba(0,0,0,0.50);
  border-bottom: 1px solid #eee;
  overflow: hidden;
}

/* .check {
  margin-right: 10px;
} */

.margindiv {
  height: 60px;
}
</style>