<script>
  //Importing Line class from the vue-chartjs wrapper
  import {Line} from 'vue-chartjs'
  //Exporting this so it can be used in other components
  export default { 
    extends: Line,
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
      //renderChart function renders the chart with the datacollection and options object.
      this.renderChart(this.datacollection, this.options)
    }
  }
</script>