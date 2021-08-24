<template>
  <v-app>
  <div class="root">
    <div class="container_A grid">
      <div id="slide-date">
        <bar-type-com id="slide-date" :start_date="start" :end_date="end" :query="tabs? size:slide_date" :needCheck=true @tabChange="tabChange"></bar-type-com>
      </div>
      <div id="under">
        <storage-table class="chartbox" @rowclick="rowclick"></storage-table>
        <stack-bar-type-com class="chartbox" :query="storage"></stack-bar-type-com>

        <line-type-com id="storage-full" :query="storage_full" :storageName="storageName" @show="show"></line-type-com>
      </div>
    </div>
    <div class="form__field grid">
      <div class="form__label">
        <strong  @click="viewPalette">
        <v-btn icon elevation="5" large style="margin: 0 8px 1px 0;">
        <v-icon x-large>mdi-palette</v-icon>
        </v-btn>
        <v-swatches style="margin-top:10px;" v-if="palette==true" v-model="color" shapes="circles" :swatches="swatches" row-length="10" inline></v-swatches>
        </strong>
      </div>
    </div>
    <div class="container_B grid">
      <div id="right">
        <bar-type-com id="scanner" :start_date="start" :end_date="end" :query="scanner" :needCheck=false :color="color+'4D'"></bar-type-com>
        <bar-type-com id="pathID" :start_date="start" :end_date="end" :query="pathID" :needCheck=false :color="color+'4D'"></bar-type-com>
      </div>
    </div>
    <Modal v-if="showModal" :query="storage_full" :storageName="storageName" @close="showModal = false">
      <transition name="modal">
    <div class="modal-mask">
      <div class="modal-wrapper" @click="showModal=false">
        <div class="modal-container">
          <div class="modal-header">
            <slot name="header">   
            </slot>
          </div>
          <div class="modal-body" @click.stop="">
            <slot name="body">
              <line-type-com id="storage-full" :query="storage_full" :storageName="storageName"></line-type-com>
            </slot>
          </div>

          <div class="modal-footer">
            <slot name="footer">
              
  
            </slot>
          </div>
        </div>
      </div>
    </div>
  </transition>
    </Modal>
  </div>
  </v-app>
</template>

<script>
import BarTypeCom from '../components/BarTypeCom.vue'
import LineTypeCom from '../components/LineTypeCom.vue'
import StackBarTypeCom from '../components/StackBarTypeCom.vue'
import VSwatches from 'vue-swatches'
import 'vue-swatches/dist/vue-swatches.css'
import StorageTable from '../components/StorageTable.vue'
export default {
  components: { BarTypeCom, LineTypeCom, VSwatches, StorageTable,StackBarTypeCom },
  name: 'home',
  data() {
    return {
      tabs:0,
      showModal:false,
      palette: false,
      color: '#1FBC9C4D',
      slide_date:{
        name:"slide-date",
        url:"/selectSlidePerDate",
        chartName: "스캔 완료 횟수",
        xKey: 0,
        yKey: [1]
      },
      scanner:{
        name:"scanner",
        url:"/searchScannerListByDate",
        chartName: "스캐너 별 스캔 횟수",
        xKey:0,
        yKey:[1]
      },
      pathID:{
        name:"pathID",
        url:"/searchPidByDate",
        chartName: "진단종류 별 스캔 횟수",
        xKey: 0,
        yKey: [1]
      },
      size:{
        name:"size",
        url:"/searchStorageUseByDate",
        chartName: "스토리지 사용량",
        xKey: 0,
        yKey: [1]
      },
      storage:{
        name:"storage",
        url:"/storageList",
        chartName: "스토리지별 총/잔여 용량",
        xKey: null,
        yKey: null
      },
      storage_full:{
        name:"storage-full",
        url:"/selectHupax",
        chartName:"스토리지 사용량 추이",
        xKey: 0,
        yKey: [2]
      },
      swatches: [ ,'#1FBC9C', '#2ECC70', '#3398DB', '#2980B9', '#A463BF', '#8E43AD', '#3D556E','#F2C511','#F39C19','#E84B3C']
    }
  },
  props: {
    start: {
      type: String, 
      default: null
    },
    end: {
      type: String, 
      default: null
    },
    submit: {
      default:0
    }
  },
  methods:{
    show(){
      this.showModal=true;
    },
    nameChange(name){
      console.log(name)
      this.storageName=name;
    },
    rowclick(selectStorage){
      this.nameChange(selectStorage)
    },
    viewPalette(){
      this.palette = !this.palette
    },
    tabChange(tab){
      this.tabs=tab
      console.log(this.tabs)
    }

  },
  mounted() {
    this.$axios.get(this.storage.url)
    .then((res)=>{
      this.storage_name_list=res.data.map(function(elem){ return elem.storageName})
      this.storageName=this.storage_name_list[0];
    })
    .then((err)=>{
      console.log(err);
    })
  }
}
</script>

<style>
  .root{
    background: rgb(248, 249, 253);
  }
  div {
    width: 100%;
    height: 90%;
  }

  strong {
    cursor: pointer;
  }

  .grid {
    display: block;
    float: left;
    padding: 10px;
    text-align: center;
  }
  
  .container_A {
    width: 68%;
  }

  .form__field {
    width: 4%;
  }
  .v-btn__content{
    width: 4% !important;
  }
  .container_B {
    width: 28%;
  }

  #chart{
    height:100%
  }
  
  .container_A #slide-date .chartbox {
    width: 97%;
    height: 320px;
    display: inline-block;
    margin: 0 5px 10px 5px;
    overflow: hidden;
    border-radius: 10px;
    box-shadow: 0 2px 4px 0 rgba(0,0,0,0.50);
    background-color: white;
  }

  .container_A #under .chartbox {
    width: 32%;
    height: 300px;
    display: inline-block;
    margin: 0 5px 10px 5px;
    overflow: hidden;
    border-radius: 10px;
    box-shadow: 0 2px 4px 0 rgba(0,0,0,0.50);
    background-color: white;
  }

  .container_B .chartbox {
    width: 380px;
    height: 310px;
    display: inline-block;
    margin: 0 10px 10px 10px;
    overflow: visible;
    border-radius: 10px;
    box-shadow: 0 2px 4px 0 rgba(0,0,0,0.50);
    background-color: white;
  }

.modal-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, .5);
  display: table;
  transition: opacity .3s ease;
}

.modal-wrapper {
  display: table-cell;
  vertical-align: middle;
}

.modal-container {
  position:absolute;
  width: 900px;
  margin: 0px auto;
  padding: 0px 0px;
  border-radius: 4px;
  font-family: Helvetica, Arial, sans-serif;
  
}

.modal-body {
  margin: 0px 0;
}

.modal-default-button {
  float: right;
}

/*
 * The following styles are auto-applied to elements with
 * transition="modal" when their visibility is toggled
 * by Vue.js.
 *
 * You can easily play with the modal transition by editing
 * these styles.
 */

.modal-enter {
  opacity: 0;
}

.modal-leave-active {
  opacity: 0;
}

.modal-enter .modal-container,
.modal-leave-active .modal-container {
  -webkit-transform: scale(1.1);
  transform: scale(1.1);
}


  input {
    background-color: #fff;
  }

  button {

  border-radius: 5px;
  background-color: white;
  color: rgb(32, 28, 231);
  padding: 5px;
  }

  button:hover {
    color: white;
    background-color: skyblue;
  }

  @media screen and (max-width: 1450px) {
    .container_A #slide-date .chartbox {
      width: 860px;
      height: 320px;
      display: inline-block;
      margin: 0 5px 10px 5px;
      overflow: hidden;
      border-radius: 10px;
      box-shadow: 0 2px 4px 0 rgba(0,0,0,0.50);
    }

    .container_A #under .chartbox {
      width: 265px;
      height: 300px;
      display: inline-block;
      margin: 0 5px 10px 5px;
      overflow: hidden;
      border-radius: 10px;
      box-shadow: 0 2px 4px 0 rgba(0,0,0,0.50);
    }

    .container_B .chartbox {
      width: 330px;
      height: 310px;
      display: inline-block;
      margin: 0 10px 10px 10px;
      overflow: hidden;
      border-radius: 10px;
      box-shadow: 0 2px 4px 0 rgba(0,0,0,0.50);
    }
  }

  @media screen and (max-width: 1324px) {
    .container_A {
      width: 68%;
    }

    .form__field {
      width: 4%;
    }

    .container_B {
      width: 28%;
    }
    .container_A #slide-date .chartbox {
      width: 820px;
      height: 320px;
      display: inline-block;
      margin: 0 0px 10px 0px;
      overflow: hidden;
      border-radius: 10px;
      box-shadow: 0 2px 4px 0 rgba(0,0,0,0.50);
    }

    .container_A #under .chartbox {
      width: 245px;
      height: 300px;
      display: inline-block;
      margin: 0 5px 10px 5px;
      overflow: hidden;
      border-radius: 10px;
      box-shadow: 0 2px 4px 0 rgba(0,0,0,0.50);
    }

    .container_B .chartbox {
      width: 320px;
      height: 310px;
      display: inline-block;
      margin: 0 10px 10px 10px;
      overflow: hidden;
      border-radius: 10px;
      box-shadow: 0 2px 4px 0 rgba(0,0,0,0.50);
    }
    .vue-swatches .swatches{
      margin-top: 20px !important;
    }
  }
</style>