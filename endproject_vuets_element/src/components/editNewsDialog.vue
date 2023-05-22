<template>
  <el-dialog :model-value="visible" title="新闻编辑" :before-close="close" width="30%">
    <el-form :model="newForm" :label-width="formLabelWidth" >
      <el-form-item label="标题"  :label-width="formLabelWidth">
        <el-input v-model="newForm.title"  autocomplete="off" />
      </el-form-item>
      <el-form-item label="正文"  :label-width="formLabelWidth">
        <el-input v-model="newForm.content" type="textarea"  autocomplete="off" />
      </el-form-item>
      <el-form-item label="发布者"  :label-width="formLabelWidth">
        <el-input v-model="newForm.publishby" disabled autocomplete="off" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button  @click="close">取消</el-button>
        <el-button type="primary" @click="modify">确定</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import {reactive, toRefs, ref, defineProps, watch, defineEmits} from 'vue'
import {editCovidNews} from "../request/api";


const props = defineProps<{
  visible:boolean;
  form:news
}>()
const state = reactive <{
  formLabelWidth:string;
  newForm:news
}> ({
  formLabelWidth:'120px',
  newForm: {}
})
const { formLabelWidth,newForm } = toRefs(state)

watch(()=> props.form,()=>{
  console.log("props.form")
  console.log(props.form)
  newForm.value = {...props.form}
  console.log("newForm.value")
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
//点击确定
const modify = ()=>{
  console.log(newForm.value)
  editCovidNews(newForm.value).then(res =>{
    if (res.code === 200){
      close('reload');
      ElMessage({
        showClose: true,
        message: '修改成功',
        type: 'success'
      })
    }
  })

}
</script>

<style lang="less" scoped>

</style>