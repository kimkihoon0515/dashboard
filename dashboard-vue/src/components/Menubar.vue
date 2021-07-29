<template>
  <div>
    <div class='left'>
      
    </div>
    <div id='left_btn' @click='toggle'></div>
  </div>
</template>
<script>
import $ from 'jquery';

export default {
  name: 'Menu',
  data () {
    return {
      toggle: this.enter
    }
  },
  methods: {
    enter: function (el, done) {
      this.toggle = this.leave
      var left = (500 - (parseInt($('#left_btn').css('width')) / 2)) + 'px'
      $('.left').stop().animate({left: '0px'}, 800)
      $('#left_btn').stop().animate({left: left}, 800)
      $('#left_btn').css({transform: 'rotate(180deg)'})
    },
    leave: function (el, done) {
      this.toggle = this.enter
      var left = (0 - (parseInt($('#left_btn').css('width')) / 2)) + 'px'
      $('.left').stop().animate({left: '-500px'}, 800)
      $('#left_btn').stop().animate({left: left}, 800)
      $('#left_btn').css({transform: 'rotate(0deg)'})
    },
    resize: function () {
      $(window).resize(function () {
        var height = (window.innerHeight / 2) + 'px'
        $('#left_btn').css({'top': height})
      })
    }
  },
  created () {
    this.resize()
  }
}
</script>
<style>
  a{color:black}
  a:hover{color:rgb(255,98,124);}
  .left{
    position:fixed; 
    top:0px; 
    left:-500px; 
    width:500px; 
    height:1000px; 
    background:rgba(28, 220, 245, 0.6); 
    padding:40px 0; 
    overflow: hidden
  }
  .left ul {padding: 0 30px}
  .left ul li { font-size:25px;  height:75px;} 
  .left ul li#logo {font-family: 'Passion One', cursive; font-size:50px; height:170px;}
  .left ul li#logo div {line-height: 0.8}
  .left ul li#company {font-size:16px; margin-top:150px; }
  #left_btn {position:fixed; top:0px; left:-30px; cursor:pointer; width: 0;
      height: 0;
      border-left: 30px solid transparent;
      border-right: 30px solid transparent;
      border-bottom: 60px solid rgb(4, 104, 129)}
</style>