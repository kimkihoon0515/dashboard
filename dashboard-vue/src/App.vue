<template>
  <v-app>
  <div id="app">
    <div id="nav">
      <div class="title">
        <div class="main">
        <strong class="tit">DashBoard</strong>
        </div>
        <div class="sub">
          <h2>Scanning Service</h2>
        </div>
      </div>
      <div class="datePicker">
        <form>
          <v-btn elevation="3" small @click="aYearAgo" style="margin: 0 12px 0 0;">
          일 년
          </v-btn>
          <v-btn elevation="3" small @click="aMonthAgo" style="margin: 0 12px 0 0;">
          한 달
          </v-btn>
          <v-btn elevation="3" small @click="setToday" style="margin: 0 8px 0 0;">
          오늘
          </v-btn>
                    <span class="material-icons">
search
</span>
          <input type="date" id='start-date' value=''/>
          <input type="date" id='end-date' value=''/>
          <v-btn icon elevation="3" small @click="setDate" style="margin: 0 8px 0 0;">
          <v-icon>fas fa-search</v-icon>
          </v-btn>
        </form>
      </div>
    </div>
    <div class="margindiv"></div>
    <home :start="startDate" :end="endDate"></home>
  </div>
  </v-app>
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
    setTimeout(this.setToday,100);
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
  background: rgb(248, 249, 253);
}

#nav {
  position: fixed;
  padding: 15px;
  margin-bottom: 5px;
  width: 100%;
  height: 60px;
  background-color: rgb(255, 255, 255);
  box-shadow: 0 1px 3px 0 rgba(0,0,0,0.50);
  border-bottom: 1px solid #eee;
  overflow: hidden;
  z-index: 2;
}

.margindiv {
  height: 60px;
}
.title{
  width: 40%;
  float: left;
  font-weight: bold;
  font-size: xx-large;
}
.main{
  width: auto;
  float: left;
  white-space: nowrap;
}
.sub{
  margin-left:20px ;
  margin-top: 5px;
  width: 70%;
}
h2{
  float: left;
}
.datePicker{
  width: auto;
  float: right;
}
.tit{
  float: left;
  font-weight:bold;
  font-size: xx-large;
  margin-right:10px ;
}
</style>