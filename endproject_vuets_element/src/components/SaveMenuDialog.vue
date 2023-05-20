<template>
  <el-dialog :model-value="visible" title="添加菜单" :before-close="close">
    <el-form :label-width="formLabelWidth" >
      <el-form-item label="所属父菜单编号"  :label-width="formLabelWidth">
        <el-input v-model="newForm.pid"  autocomplete="off" />
      </el-form-item>
      <el-form-item label="菜单名称"  :label-width="formLabelWidth">
        <el-input v-model="newForm.title"  autocomplete="off" />
      </el-form-item>
      <el-form-item label="路径"  :label-width="formLabelWidth">
        <el-input v-model="newForm.path"  autocomplete="off" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button  @click="close">取消</el-button>
        <el-button type="primary" @click="modify">提交</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import {reactive, toRefs, ref, defineProps, watch, defineEmits} from 'vue'
import {addMenu} from "../request/api";

const props = defineProps<{
  visible:boolean;
  form:menu
}>()
const state = reactive <{
  formLabelWidth:string;
  newForm:menu
}> ({
  formLabelWidth:'120px',
  newForm: {}
})
const { formLabelWidth,newForm } = toRefs(state)


const emit = defineEmits<{
  (event:'close',r?: 'reload'):void
}>();
//点击关闭
const close = (r?: 'reload') =>{
  //传到父组件
  newForm.value={}
  emit('close',r);
}

const modify = ()=>{
  console.log(newForm.value)
  addMenu(newForm.value).then(res=>{
    if (res.code===200){
      close('reload')
      ElMessage({
        showClose: true,
        message: '添加成功',
        type: 'success'
      })
    }
  })
}
</script>

<style lang="less" scoped>

</style>