<template>
  <el-dialog :model-value="visible" title="提交申请" :before-close="close" width="30%">
    <el-form :model="newForm" :label-width="formLabelWidth"  :rules="rules">
      <el-form-item label="原因" prop="reason" :label-width="formLabelWidth">
        <el-input v-model="newForm.reason" autocomplete="off" />
      </el-form-item>
      <el-form-item label="去向" prop="address" :label-width="formLabelWidth">
        <el-input v-model="newForm.address"  autocomplete="off" />
      </el-form-item>
      <el-form-item label="时长" prop="day" :label-width="formLabelWidth">
        <el-input v-model="newForm.day"  autocomplete="off" />
      </el-form-item>
      <el-form-item label="联系方式" prop="phone" :label-width="formLabelWidth">
        <el-input v-model="newForm.phone"  autocomplete="off" />
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
import {addleave} from "../request/api";
import Cookie from "js-cookie";

const props = defineProps<{
  visible:boolean;
  form:leave
}>()
const state = reactive <{
  formLabelWidth:string;
  newForm:leave
}> ({
  formLabelWidth:'120px',
  newForm: {}
})

const { formLabelWidth,newForm } = toRefs(state)
/*watch(()=> props.form,()=>{
  newForm.value = {...props.form}
})*/
const emit = defineEmits<{
  (event:'close',r?: 'reload'):void
}>();
//点击关闭
const close = (r?: 'reload') =>{
  //传到父组件
  newForm.value = {};
  emit('close',r);
}

//校验规则
const rules = reactive({
  reason:[{required: true, message: '*必填项',trigger: 'blur' }],
  address:[{required: true, message: '*必填项', trigger: 'blur' }],
  day:[{required: true, message: '*必填项', trigger: 'blur' }],
  phone:[{required: true, message: '*必填项', trigger: 'blur' }],
})

//点击确定
const modify = ()=>{
  console.log(Date)
  newForm.value.snum=Cookie.get("snum")
  newForm.value.create_time=Date.now();
  newForm.value.update_time=Date.now();
  newForm.value.node_status=1
  addleave(newForm.value).then(res=>{
    if (res.code===200){
      close('reload')
    }
  })
}
</script>

<style lang="less" scoped>

</style>