<template>
  <div id="Map" style="width: 50%; height: 400px;"></div>
</template>

<script lang="ts" setup>
import {reactive, toRefs, ref, watch} from 'vue'
import * as echarts from 'echarts'
import china from '../../../assets/china.json'
echarts.registerMap('china',china as any);



const props = defineProps<{
  data:{}[]
}>();
let Map:echarts.ECharts;
watch(()=>props.data,()=>{
  const data = props.data.map((item:{[key:string]:string | number})=>({ name:item.child_statistic,value:item.total_confirmed}))
  if (!Map){
    Map = echarts.init(document.getElementById('Map') as HTMLElement)

    const options = {
      tooltip:{
        show:true,
        formatter:'{b0} <br/> 累计确诊:{c0}',
        backgroundColor : 'rgba(50,50,50,0.7)',
        textStyle:{
          color:'#fff'
        }
      },
      series:{
        type:'map',
        map:'china',
        emphasis:{
          label:{
            show:false
          },
          itemStyle:{
            areaColor:'red'
          }
        },
        itemStyle:{
          borderColor:'#fff'
        },
        data
      },
      visualMap:{
        type:'continuous',
        min:0,
        max:50000,
        calculable: true,
        /*inRange: {
          color: ['#121122', 'rgba(3,4,5,0.4)', 'red'],
        }*/
      }
    };

    Map.setOption(options);
  }
})
</script>

<style lang="less" scoped>

</style>