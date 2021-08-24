<script>
  //Importing Line class from the vue-chartjs wrapper
  import {Bar} from 'vue-chartjs'
  export default { 
    extends: Bar,
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
