<template>
  <el-dialog :model-value="visible" title="填报详情" :before-close="close">
    <el-descriptions :data="newForm" direction="vertical"  border>
      <el-descriptions-item label="学号">{{newForm.snum}}</el-descriptions-item>
      <el-descriptions-item label="当前所在地">{{ newForm.local }}</el-descriptions-item>
      <el-descriptions-item label="是否确诊">
        <template v-slot:default="scope">
        <el-tag type="danger" v-if="newForm.confirmed==1">是</el-tag>
        <el-tag type="success" v-if="newForm.confirmed==0">否</el-tag>
      </template>
      </el-descriptions-item>
      <el-descriptions-item label="是否无症状">
        <template v-slot:default="scope">
          <el-tag type="danger" v-if="newForm.asymptomatic==1">是</el-tag>
          <el-tag type="success" v-if="newForm.asymptomatic==0">否</el-tag>
        </template>
      </el-descriptions-item>
      <el-descriptions-item label="今日体温">
        <template v-slot:default="scope">
          <el-tag type="danger" v-if="newForm.today_temp>=37.5">{{ newForm.today_temp }}</el-tag>
          <el-tag type="success" v-if="newForm.today_temp<37.5">{{ newForm.today_temp }}</el-tag>
        </template>
      </el-descriptions-item>
      <el-descriptions-item label="是否不适">

        <template v-slot:default="scope">
          <el-tag type="danger" v-if="newForm.fever_and_cough==1">是</el-tag>
          <el-tag type="success" v-if="newForm.fever_and_cough==0">否</el-tag>
        </template>
      </el-descriptions-item>
      <el-descriptions-item label="不适时的详细描述">{{newForm.cough}}</el-descriptions-item>
      <el-descriptions-item label="近期居住地">{{newForm.recent_home}}</el-descriptions-item>
      <el-descriptions-item label="创建时间"> <template v-slot:default="scope">{{ formateDate(newForm.create_time) }}</template> </el-descriptions-item>
      <el-descriptions-item label="填报状态">
        <template v-slot:default="scope">
          <el-tag v-if="newForm.health_status==1">已填报</el-tag>
          <el-tag type="warning" v-if="newForm.health_status==0">未填报</el-tag>
        </template></el-descriptions-item>
    </el-descriptions>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="close">关闭</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import {reactive, toRefs, ref, defineProps, watch, defineEmits} from 'vue'

const props = defineProps<{
  visible:boolean;
  form:{}[]
}>()
const state = reactive <{formLabelWidth:string; newForm:leave}> ({
  formLabelWidth:'120px',
  newForm: []
})
const { formLabelWidth,newForm } = toRefs(state)
watch(()=> props.form,()=>{
  newForm.value = {...props.form}
  console.log("watch")
  console.log(newForm.value)
})
const emit = defineEmits<{
  (event:'close',r?: 'reload'):void
}>();
//点击关闭
const close = (r?: 'reload') =>{
  //传到父组件
  emit('close',r);
}

const formateDate = (time: string|undefined) =>{
  if (!time) return '';
  const date = new Date(time);
  let year = date.getFullYear();
  let month = addZero(date.getMonth()+1);
  let day = addZero(date.getDate());
  let hour = addZero(date.getHours());
  let min = addZero(date.getMinutes());
  let sec = addZero(date.getSeconds());

  return `${year}-${month}-${day} ${hour}:${min}:${sec}`
}

const addZero = (num:number) =>{
  return num > 9 ? num:'0'+num
}
</script>

<style lang="less" scoped>

</style>