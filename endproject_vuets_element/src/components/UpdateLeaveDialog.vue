<template>
  <el-dialog :model-value="visible" title="审批" :before-close="close">
    <el-descriptions  border>
      <el-descriptions-item label="学号">{{newForm.snum}}</el-descriptions-item>
      <el-descriptions-item label="请假原因">{{ newForm.reason }}</el-descriptions-item>
      <el-descriptions-item label="去向">{{newForm.address}}</el-descriptions-item>
      <el-descriptions-item label="离校天数">{{newForm.day}}</el-descriptions-item>
      <el-descriptions-item label="创建时间">
        <template v-slot:default="scope">{{ formateDate(newForm.create_time) }}</template>
      </el-descriptions-item>
      <el-descriptions-item label="更新时间">
        <template v-slot:default="scope">{{ formateDate(newForm.create_time) }}</template>
      </el-descriptions-item>
    </el-descriptions>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="success" @click="modify1">批准</el-button>
        <el-button type="danger" @click="modify2">驳回</el-button>
        <el-button type="primary" @click="close">取消</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import {reactive, toRefs, ref, defineProps, watch, defineEmits} from 'vue'
import {updateleave} from "../request/api";
const props = defineProps<{
  visible:boolean;
  form:leave
}>()
const state = reactive <{formLabelWidth:string; newForm:leave}> ({
  formLabelWidth:'120px',
  newForm: {}
})
const { formLabelWidth,newForm } = toRefs(state)
watch(()=> props.form,()=>{
  newForm.value = {...props.form}
  console.log("watch")
  console.log(newForm.value.id)
})
const emit = defineEmits<{
  (event:'close',r?: 'reload'):void
}>();
//点击关闭
const close = (r?: 'reload') =>{
  //传到父组件
  emit('close',r);
}
//点击确定
const modify1 = ()=>{
  newForm.value.node_status = 2;
  newForm.value.update_time=Date.now();
  updateleave(newForm.value).then(res=>{
    if (res.code===200){
      close('reload')
    }
  })


}
const modify2 = ()=>{
  newForm.value.node_status = 3;
  newForm.value.update_time=Date.now();
  updateleave(newForm.value).then(res=>{
    if (res.code===200){
      close('reload')
    }
  })
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