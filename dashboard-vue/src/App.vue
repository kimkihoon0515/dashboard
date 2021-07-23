<template>
  <div id="app">
    <div id="nav">
      <form>
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

export default {
  components: {
    home
  },
  data () {
    return {
      startDate: "2021-03-01",
      endDate: "2021-04-23"
    }
  },
  methods: {
    setDate() {
      this.startDate = document.querySelector("#start-date").value;
      this.endDate = document.querySelector("#end-date").value;
    }
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