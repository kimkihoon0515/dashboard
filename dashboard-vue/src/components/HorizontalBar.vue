<script>
  //스택바를 구현할 가로 막대 차트
  import {HorizontalBar} from 'vue-chartjs'
  export default { 
    extends: HorizontalBar,
    props: {
      datacollection: {
        type: Object, 
      },
      options:{
        type: Object, 
      },
      change:{
        default:0
      }
    },
    watch:{
      change: function(){
        this.renderChart(this.datacollection, this.options)
        this.$emit("rerendered")
      }
    },
    mounted () {
      this.renderChart(this.datacollection, this.options)
    }
  }
</script>
