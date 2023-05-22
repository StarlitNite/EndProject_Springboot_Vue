<template>

  <el-timeline>
    <el-timeline-item
        v-for="(activity, index) in data"
        :key="index"
        :timestamp="activity.content"
    >
      {{ activity.title }}

    </el-timeline-item>
  </el-timeline>


</template>

<script lang="ts" setup>
import {reactive, toRefs, ref, watch} from 'vue'

const props = defineProps<{
  data:{}[]
}>();

watch(()=>props.data,()=> {
  console.log("props.data")
  console.log(props.data)
  var data = props.data
})

</script>

<style lang="less" scoped>
:deep(.el-timeline-item__wrapper ){
  display: flex;
  flex-direction: column;
  align-items: start;
}

</style>