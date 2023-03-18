<template>
  <el-dialog :model-value="visible" title="角色编辑" :before-close="close">
    <el-form :model="newForm" :label-width="formLabelWidth" >
      <el-form-item label="角色名称" :label-width="formLabelWidth">
        <el-input v-model="newForm.name" autocomplete="off" />
      </el-form-item>
      <el-form-item label="权限"  :label-width="formLabelWidth">
        <el-input v-model="newForm.remark" disabled autocomplete="off" />
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
import {UpdateRole} from '../request/api'

const props = defineProps<{
  visible:boolean;
  form:Role
}>()
const state = reactive <{formLabelWidth:string; newForm:Role}> ({
  formLabelWidth:'120px',
  newForm: {}
})
const { formLabelWidth,newForm } = toRefs(state)

watch(()=> props.form,()=>{
  newForm.value = {...props.form}
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

  UpdateRole(newForm.value).then(res =>{
    if (res.code === 200){
      console.log("--------------")
      close('reload');
    }
  })

}
</script>

<style lang="less" scoped>

</style>