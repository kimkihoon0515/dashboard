<template>
  <v-app>
  <div id="app">
    <div id="nav"> <!--네비게이션 바 시작-->
      <div class="title">
        <div class="main">
        <strong class="tit">
          <img src = "./data/humintec.jpg" alt="">
        </strong>
        </div>
        <div class="sub">
          <h2>
            DashBoard Service
          </h2>
        </div>
      </div>
      <div class="datePicker">
        <div class="recent"><!--최근데이터 불러오기 버튼 "v-" 붙은 태그는 vuetify 프레임워크의 ui 컴포넌트이며 v-btn의 elevation은 버튼의 그림자 정도에 대한 속성-->
          <div >
            <v-btn elevation="3" small @click="aYearAgo" style="margin: 0 12px 0 0;">
            일 년
            </v-btn>
            <v-btn elevation="3" small @click="aMonthAgo" style="margin: 0 12px 0 0;">
            한 달
            </v-btn>
            <v-btn elevation="3" small @click="setToday">
            오늘
            </v-btn>
          </div>
        </div>
        <div class="range"><!-- 날짜 입력 -->
            <input type="date" id='start-date' value=startDate/ style="margin-top: 1px ;">
            <strong>~</strong>
            <input type="date" id='end-date' value=endDate/ style="margin: 1px 8px 0 5px ;">
            <v-btn icon elevation="5" small @click="setDate" style="margin: 0 8px 1px 0;">
            <v-icon>mdi-magnify</v-icon>
            </v-btn>
        </div>
      </div>
    </div> <!--네비게이션 바 끝-->
    <div class="margindiv"></div> <!-- 여백 생성-->
    <home :start="startDate" :end="endDate"></home>
  </div>
  </v-app>
</template>

<script>
import home from "./views/Home.vue"
import moment from 'moment' //날짜 계산 라이브러리
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
    setDate() { //돋보기 아이콘 버튼 클릭시 실행되며 startDate, endDate를 입력받은 값으로 변경
      this.startDate = document.querySelector("#start-date").value;
      this.endDate = document.querySelector("#end-date").value;
    },
    aYearAgo() {// 최근 1년으로 날짜 설정 
      this.endDate = moment().format('YYYY-MM-DD')
      this.startDate = moment().subtract(1,'years').format('YYYY-MM-DD')
    },
    aMonthAgo(){// 최근 한달로 날짜 설정
      this.endDate = moment().format('YYYY-MM-DD')
      this.startDate = moment().subtract(1,'months').format('YYYY-MM-DD')
    },
    setToday(){// 오늘로 날짜 설정
      this.startDate = moment().format('YYYY-MM-DD')
      this.endDate = moment().format('YYYY-MM-DD')
    }
  },
  mounted(){ // 인스턴스 마운트시 실행되는 영역 
    setTimeout(this.setToday,100); // 초기 시작일과 끝일은 null로 설정 되어있기에 마운트시 오늘로 설정해주는 함수 실행
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

img {
  position: relative;
  bottom: 12px;
  height: auto;
  
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
  position: relative;
  bottom: 12px;
  left : 200px;
  font-size:xx-large;
  float: left;
}
.datePicker{
  width: 650px;
  float: right;
}
.tit{
  float: left;
  font-weight:bold;
  font-size: xx-large;
  margin-right:10px ;
}
#start-date{
  border: 1px solid #eee !important;
  box-shadow: 0 1px 3px 0 rgba(0,0,0,0.50);
  margin-right: 4px;
  float: left;
}
#end-date{
  border: 1px solid #eee !important;
  box-shadow: 0 1px 3px 0 rgba(0,0,0,0.50);
}
.recent{
  float: left;
  width: 35%;
}
.range{
  float: right;
  width: 60%;
}
</style>