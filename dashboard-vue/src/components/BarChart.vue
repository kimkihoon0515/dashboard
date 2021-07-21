<script>
  //Importing Line class from the vue-chartjs wrapper
  import {Bar} from 'vue-chartjs'
  //Exporting this so it can be used in other components
  export default { 
    extends: Bar,
    props: {
        datacollection: {
          type: Object, 
          default: {}
        },
        options:{
          type: Object, 
          default: {}
        }
    },
    watch:{
      datacollection: function(){
        this.renderChart(this.datacollection, this.options)
      }
    },
    mounted () {
      //renderChart function renders the chart with the datacollection and options object.
      this.renderChart(this.datacollection, this.options)
    }
  }
</script>
