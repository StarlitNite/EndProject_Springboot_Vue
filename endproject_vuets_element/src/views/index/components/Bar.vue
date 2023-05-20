<template>
  <span>全国疫情</span>
  <div id="pie" style="width: 50% ; height: 400px" ></div>
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

/*const dayChange = () =>{
  initBar()
}*/
let myChart : echarts.ECharts;

var app:any={};

const initBar = ()=>{

  const xAxisdata = props.data.map(value => value.child_statistic)
  const confirmeddata = props.data.map(value => value.total_confirmed)
  const cureddata = props.data.map(value => value.total_cured)
  const deathdata = props.data.map(value => value.total_death)
 /* const xAxisdata = props.data.map((item:{[key:string]:string})=>({name:item.child_statistic,value:item[key]}))
  const confirmeddata = props.data.map((item:{[key:string]:string})=>({name:item.total_confirmed,value:item[key]}))
  const cureddata = props.data.map((item:{[key:string]:string})=>({name:item.total_cured,value:item[key]}))
  const deathdata = props.data.map((item:{[key:string]:string})=>({name:item.total_death,value:item[key]}))*/
  console.log(xAxisdata)
  console.log(confirmeddata)
  console.log(cureddata)
  console.log(deathdata)
  //初始化实例
  if (!myChart){
    myChart = echarts.init(document.getElementById('pie') as HTMLElement);
  }

  const posList = [
    'left',
    'right',
    'top',
    'bottom',
    'inside',
    'insideTop',
    'insideLeft',
    'insideRight',
    'insideBottom',
    'insideTopLeft',
    'insideTopRight',
    'insideBottomLeft',
    'insideBottomRight'
  ] as const;

   app.configParameters = {
    rotate: {
      min: -90,
      max: 90
    },
    align: {
      options: {
        left: 'left',
        center: 'center',
        right: 'right'
      }
    },
    //
    verticalAlign: {
      options: {
        top: 'top',
        middle: 'middle',
        bottom: 'bottom'
      }
    },
    position: {
      options: posList.reduce(function (map, pos) {
        map[pos] = pos;
        return map;
      }, {} as Record<string, string>)
    },
    distance: {
      min: 0,
      max: 100
    }
  };

  app.config = {
    rotate: 90,
    align: 'left',
    //柱内文字左右位置
    verticalAlign: 'middle',
    //柱内文字上下标签
    position: 'insideBottom',
    distance: 15,
    onChange: function () {
      const labelOption: BarLabelOption = {
        rotate: app.config.rotate as BarLabelOption['rotate'],
        align: app.config.align as BarLabelOption['align'],
        verticalAlign: app.config.verticalAlign as BarLabelOption['verticalAlign'],
        position: app.config.position as BarLabelOption['position'],
        distance: app.config.distance as BarLabelOption['distance']
      };
      myChart.setOption<echarts.EChartsOption>({
        series: [
          {
            label: labelOption
          },
          {
            label: labelOption
          },
          {
            label: labelOption
          }
        ]
      });
    }
  };

  type BarLabelOption = NonNullable<echarts.BarSeriesOption['label']>;

  const labelOption: BarLabelOption = {
    show: true,
    position: app.config.position as BarLabelOption['position'],
    distance: app.config.distance as BarLabelOption['distance'],
    align: app.config.align as BarLabelOption['align'],
    verticalAlign: app.config.verticalAlign as BarLabelOption['verticalAlign'],
    rotate: app.config.rotate as BarLabelOption['rotate'],
    formatter: '{c}  {name|{a}}',
    fontSize: 16,
    rich: {
      name: {}
    }
  };

  const option = {
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'shadow'
      }
    },
    calculable: true,
    //提示框组件
    legend: {
      data: ['确诊', '治愈', '死亡']//'confirmed', 'cured', 'death'
    },
    //工具盒
    toolbox: {
      show: true,
      orient: 'vertical',
      left: 'right',
      top: 'center',
      feature: {
        mark: { show: true },
        dataView: { show: true, readOnly: false },
        magicType: { show: true, type: ['line', 'bar', 'stack'] },
        restore: { show: true },
        saveAsImage: { show: true }
      }
    },
    //横坐标显示
    xAxis: [
      {
        type: 'category',
        axisLabel: {
          interval: 0 // 让横坐标每一项都显示
        },
        axisTick: { show: true },
        data: xAxisdata//
      }
    ],
    //纵坐标显示
    yAxis: [
      {
        type: 'value'
      }
    ],
    dataZoom: [
      {
        show: true,
        start: 94,
        end: 100
      },
      {
        type: 'inside',
        start: 94,
        end: 100
      },
      {
        show: true,
        yAxisIndex: 0,
        filterMode: 'empty',
        width: 30,
        height: '80%',
        showDataShadow: false,
        left: '93%'
      }
    ],
    //柱状显示
    series: [
      {
        name: '确诊',//confirmed
        type: 'bar',
        barGap: 0,
        label: labelOption,
        emphasis: {
          focus: 'series'
        },
        data: confirmeddata//confirmeddata.toString()
      },
      {
        name: '治愈',//cured
        type: 'bar',
        label: labelOption,
        emphasis: {
          focus: 'series'
        },
        data:cureddata//cureddata.toString()
      },
      {
        name: '死亡',//death
        type: 'bar',
        label: labelOption,
        emphasis: {
          focus: 'series'
        },
        data: deathdata
      }
    ]
  };

  // 使用刚指定的配置项和数据显示图表。
  myChart.setOption(option);
}

watch(()=> props.data,()=>{
   initBar()
  // iniBar()
})
/*onMounted(()=>{})*/
</script>

<style lang="less" scoped>



</style>