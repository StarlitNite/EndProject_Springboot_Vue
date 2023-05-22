<template>
  <el-dialog :model-value="visible" title="添加新闻" :before-close="close" width="30%">
    <el-form :model="newForm" :label-width="formLabelWidth" :rules="rules" >
      <el-form-item label="新闻标题" prop="title"  :label-width="formLabelWidth">
        <el-input v-model="newForm.title"  autocomplete="off" />
      </el-form-item>
      <el-form-item label="正文内容" prop="content"  :label-width="formLabelWidth">
        <el-input type="textarea" v-model="newForm.content"  autocomplete="off" />
      </el-form-item>
      <el-form-item label="发布者" prop="publishby"  :label-width="formLabelWidth">
        <el-input v-model="newForm.publishby"  autocomplete="off" />
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
import {reactive, toRefs, ref, defineProps, defineEmits} from 'vue'
import {addCovidNews} from "../request/api";

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


const emit = defineEmits<{
  (event:'close',r?: 'reload'):void
}>();
//点击关闭
const close = (r?: 'reload') =>{
  //传到父组件
  newForm.value={}
  emit('close',r);
}

//校验规则
const rules = reactive({
  title:[{required: true, message: '*必填项',trigger: 'blur' }],
  content:[{required: true, message: '*必填项', trigger: 'blur' }],
  publishby:[{required: true, message: '*必填项', trigger: 'blur' }],
})

const modify = ()=>{
  newForm.value.create_time = Date.now();
  console.log(newForm.value)
  addCovidNews(newForm.value).then(res=>{
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