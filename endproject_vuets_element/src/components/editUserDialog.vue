<template>
  <el-dialog :model-value="visible" title="用户编辑" :before-close="close" width="20%">
    <el-form :model="newForm" :label-width="formLabelWidth" >
      <el-form-item label="姓名"  :label-width="formLabelWidth">
        <el-input v-model="newForm.username" disabled autocomplete="off" />
      </el-form-item>
      <el-form-item label="权限"  :label-width="formLabelWidth">
        <el-select v-model="newForm.role_id" placeholder="please select your role">
          <el-option label="管理员" value="1" />
          <el-option label="辅导员" value="2" />
          <el-option label="学生" value="3" />
        </el-select>
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
import {editUser} from "../request/api";

const props = defineProps<{
  visible:boolean;
  form:user
}>()
const state = reactive <{
  formLabelWidth:string;
  newForm:user
}> ({
  formLabelWidth:'120px',
  newForm: {}
})
const { formLabelWidth,newForm } = toRefs(state)

watch(()=> props.form,()=>{
  console.log("props.form")
  console.log(props.form)
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
  console.log(newForm.value)
  editUser(newForm.value).then(res =>{
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