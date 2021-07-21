<template>
  <div id="app">
    <div id="nav">
      <form> 
        <label class="check"><input type="checkbox">Y</label>
        <label class="check"><input type="checkbox">M</label>
        <label class="check"><input type="checkbox">D</label>
        <input type="date" id='start-date' value=''/>
        <input type="date" id='end-date' value=''/>
        <button @click="setDate">submit</button>
      </form>
    </div>
    <div class="margindiv"></div>
    <home :start="startDate" :end="startDate"></home>
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
      startDate: "2021-07-18",
      endDate: "2021-07-21"
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

.check {
  margin-right: 10px;
}

.margindiv {
  height: 60px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
  text-decoration: underline;
}

#nav a.router-link-exact-active {
  color: #42b983;
  text-decoration: none;
}

h1 {
  font-size: 1.75em;
}

h2 {
  line-height: 2.5em;
  font-size: 1.25em;
}
</style>