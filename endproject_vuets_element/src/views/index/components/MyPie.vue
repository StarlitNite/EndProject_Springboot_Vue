<template>
  <el-radio-group v-model="selectLabel"  @change="dayChange">
    <el-radio-button label="" >确诊</el-radio-button>
    <el-radio-button label="治愈" >治愈</el-radio-button>
    <el-radio-button label="死亡" >死亡</el-radio-button>
  </el-radio-group>
  <el-select v-model="selectDay" placeholder="Select" @change="dayChange" >
    <el-option
        v-for="item in options"
        :key="item.value"
        :label="item.label"
        :value="item.value"
    />
  </el-select>
  <div id="pie" style="width: 100% ; height: 350px" ></div>
</template>

<script lang="ts" setup>
import {reactive, toRefs, ref, onMounted, watch} from 'vue'
//
import * as echarts from 'echarts'
const props = defineProps<{
  data:{}[]
}>()

const state = reactive({
  selectLabel:'',
  selectDay:'',
  options:[
    {name:'确诊',value:'confirmed'},
    {name:'治愈',value:'cured'},
    {name:'死亡',value:'death'},
  ]
})

const {selectLabel,selectDay,options} = toRefs(state);

const dayChange = () =>{
  /*if (selectLabel.value === '_money'){
    selectDay.value + selectLabel.value
  }else {
    selectLabel.value + selectDay.value
  }*/
  initPie()
}
let myChart : echarts.ECharts;
const initPie =()=>{
  let key =''
  if (selectLabel.value === '_money'){
    selectDay.value + selectLabel.value
  }else {
    selectLabel.value + selectDay.value
  }
  //图形数据
  const arr = props.data.map((item:{[key:string]:string})=>({value:item[key],name:item.name}))
  console.log(props.data,arr)
  //初始化实例
  if (!myChart){
    myChart = echarts.init(document.getElementById('pie') as HTMLElement);
  }

  const option = {
    tooltip: {
      trigger: 'none'
    },
    legend: {
      top: 20,
      right: 10,
      width:300,
      height:100,
      orient:'vertical'
    },
    series: [
      {
        name: 'Access From',
        type: 'pie',
        radius: ['40%', '70%'],
        center:['35%','45%'],
        avoidLabelOverlap: false,
        label: {
          show: true,
          // position: 'center'
        },
        labelLine: {
          show: true
        },
        data: [
          arr
        ]
      }
    ]
  };
// 使用刚指定的配置项和数据显示图表。
  myChart.setOption(option);
}


watch(()=> props.data,()=>{
  initPie()
 /* const arr = props.data(item=>({value:item.today,name:item.name}))
  //初始化实例
  var myChart = echarts.init(document.getElementById('pie') as HTMLElement);

  const option = {
    tooltip: {
      trigger: 'none'
    },
    legend: {
      top: 20,
      right: 10,
      width:300,
      height:100,
      orient:'vertical'
    },
    series: [
      {
        name: 'Access From',
        type: 'pie',
        radius: ['40%', '70%'],
        center:['35%','45%'],
        avoidLabelOverlap: false,
        label: {
          show: true,
          // position: 'center'
        },
        labelLine: {
          show: true
        },
        data: [
          arr
        ]
      }
    ]
  };
// 使用刚指定的配置项和数据显示图表。
  myChart.setOption(option);*/
})


/*onMounted(()=>{})*/



</script>

<style lang="less" scoped>

</style>