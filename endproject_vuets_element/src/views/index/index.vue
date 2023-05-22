<template>

  <el-row>
    <el-col :span="24"><div><Statistic :data="state.Statistic"></Statistic></div></el-col>
  </el-row>

  <el-card class="box-card">
    <el-row >
     <el-col :span="9"><div><Bar :data="state.Bar"/></div></el-col>
     <el-col :span="9"><div><MyMap :data="state.Map"></MyMap></div></el-col>
       <el-col :span="6"><div><TimeLine :data="state.TimeLine"/></div></el-col>
   </el-row>
  </el-card>
<!--  <el-card class="box-card">
    <template #header>
      <div class="card-header">
        <div> <MyMap :data="state.Map"></MyMap></div>
      </div>
    </template>
  </el-card>-->
<!--  <MyPie :data="state.Pie"></MyPie>-->
  <!--  <el-row >
    <el-col :span="8"><div><Bar :data="state.Bar"/></div></el-col>
    <el-col :span="8"><div> <MyMap :data="state.Map"></MyMap></div></el-col>
    <el-col :span="8"> </el-col>
  </el-row>-->
</template>

<script lang="ts" setup>
import {reactive, toRefs, ref} from 'vue'
import {covidData,covidNews} from '../../request/api'
import Bar from './components/Bar.vue'
import MyMap from './components/MyMap.vue'
import Statistic from './components/Statistic.vue'
import MyPie from "./components/MyPie.vue";
import TimeLine  from "./components/TimeLine.vue"

const state = reactive<{
  salePie:{}[]
  Bar:{}[]
  Map:{}[]
  Statistic:{}[]
  Line:{}[]
  TimeLine:{}[]
}>({
  salePie:[],
  Bar:[],
  Map:[],
  Statistic:[],
  Line:[],
  TimeLine:[]
})

covidData().then(res=>{
  if (res.code === 200){
    state.Bar = res.result.Bar
    state.Map = res.result.Map
    state.Statistic = res.result.Statistic
    state.Line = res.result.Line
  }
})

covidNews().then(res=>{
  if (res.code ===200 ){
    state.TimeLine = res.result
    console.log(TimeLine)
  }
})



</script>

<style scoped>

</style>