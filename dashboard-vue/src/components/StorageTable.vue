<template>
  <v-card style="width: 300px; display: inline-block;">
    <v-simple-table dense fixed-header
      height="300px" @click:row="handleClick" v-model="selectedRow">
      <template v-slot:default>
        <thead>
          <tr >
            <th class="text-left">Name</th>
            <th class="text-left">Usage (Total / used)</th>
            <th class="text-left">MA predict</th>
          </tr>
        </thead>
        <tbody>
          <tr  v-for="item in dataset" :class="selectedRow==item.id?'cyan':'yellow'" :key="item.name" @click="handleClick(item)">
            <td>{{ item.storageName }}</td>
            <td>{{sizeConvert(item.total)}} / {{ sizeConvert(item.used) }}</td>
            <td>{{ item.predictDate }}</td>
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
        console.log(value.storageName)
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
    this.$axios.get("/storageList")
    .then((res)=>{
      console.log("hello")
      let tmp=res.data;
      console.log(tmp)
      for(let item in tmp){
        this.$axios.post("/selectStorageTableById", {'storageName':tmp[item].storageName, 'n':7})
        .then((res2)=>{
          console.log(res2.data)
          tmp[item].predictDate=res2.data.map(function(elem){return elem.date})[res2.data.length-1]
          this.dataset=tmp
        })
        .then((err)=>{
          console.log(err);
        })
      }
    })
    .then((err)=>{
      console.log(err);
    })
    }
  }
</script>
<style >
.cyan {
    background-color: #dcdee3;
  }
</style>
