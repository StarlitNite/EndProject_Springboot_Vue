<template>

  <Edit style="width: 1em; height: 1em; margin-right: 8px" />
  <el-row >
    <el-col :span="10"><div class="div1"><Bar :data="state.Bar"/></div></el-col>
    <el-col :span="10"><div class="div2"> <MyMap :data="state.Map"></MyMap></div></el-col>
  </el-row>

<!--  <MyPie :data="state.Pie"></MyPie>-->
</template>

<script lang="ts" setup>
import {reactive, toRefs, ref} from 'vue'
import {covidData} from '../../request/api'
import Bar from './components/Bar.vue'
import MyMap from './components/MyMap.vue'
import Line from './components/Line.vue'
import MyPie from "./components/MyPie.vue";


const state = reactive<{
  salePie:{}[]
  Bar:{}[]
  Map:{}[]
  Statistic:{}
  Line:{}[]
}>({
  salePie:[],
  Bar:[],
  Map:[],
  Statistic:[],
  Line:[]
})

covidData().then(res=>{
  if (res.code === 200){
    state.Bar = res.result.Bar
    state.Map = res.result.Map
    state.Statistic = res.result.Statistic
    state.Line = res.result.Line

  }
})


</script>

<style scoped>

</style>