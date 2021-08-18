<template>
  <v-card style="width: 300px; display: inline-block;">
    <v-simple-table dense fixed-header
      height="300px" @click:row="handleClick" v-model="selectedRow">
      <template v-slot:default>
        <thead>
          <tr >
            <th class="text-left" >Name</th>
            <th class="text-left" >Usage</th>
            <th class="text-left" >H/S</th>
          </tr>
        </thead>
        <tbody>
          <tr  v-for="item in dataset" :class="selectedRow==item.id?'white':'yellow'" :key="item.name" @click="handleClick(item)">
            <td id="name">{{ item.storage_name }}</td>
            <td id="size">{{sizeConvert(item.total)}} / {{ sizeConvert(item.used) }}</td>
            <td id="name">{{ item.hupax_or_sectra }}</td>
          </tr>
        </tbody>
      </template>
    </v-simple-table>
  </v-card>
</template>

<script>
  export default {
    name:"StorageTable",
    data () {
      return {
        selectedRow:null,
        dataset:[],
        selectStorage:"",
      }
    },
    methods:{
      handleClick(value){
        console.log(value.storagename)
        this.toggleSelection(value.id);
        this.selectStorage=""
        this.selectStorage=value.storageName
        this.$emit("rowclick",this.selectStorage)
      },
      toggleSelection(keyID) {
        console.log(keyID)
        if (this.selectedRow!=keyID) {
          this.selectedRow=keyID
          console.log(this.selectedRow)
        }
      },
      sizeConvert(currentValue){
          if (currentValue >= 1000000000000) {
            return currentValue = (currentValue/ 1000000000000).toFixed(1) + "TB";
          }
          else if (currentValue>=1000000000 && currentValue < 1000000000000) {
            return currentValue = (currentValue / 1000000000).toFixed(1) + "GB";
          }
          else if (currentValue>=1000000 && currentValue < 1000000000) {
            return currentValue = (currentValue / 1000000).toFixed(1) + "MB";
          }
          else {
          return currentValue;
          }
      }
    },
    beforeCreate(){
    this.$axios.get("/selectStorageStatus")
    .then((res)=>{
      this.dataset=res.data;
      console.log(res.data)

    })
    .then((err)=>{
      console.log(err);
    })
    }
  }
</script>
<style >
.cyan {
    background-color: #ededed;
  }
  td #name{
    width: 1%;
  }
  td #size{
    width: 99%;
    font-weight: 900 !important;
  }
</style>
