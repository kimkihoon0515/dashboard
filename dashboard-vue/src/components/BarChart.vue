<script>
  //Importing Line class from the vue-chartjs wrapper
  import {Bar} from 'vue-chartjs'
  //Exporting this so it can be used in other components
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
        //this.update();
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
