<template>
  <el-dialog :model-value="visible" title="每日填报" :before-close="close" width="30%">
    <el-form :model="newForm" :label-width="formLabelWidth"   :rules="rules">
      <el-form-item label="当前所在地" prop="local" :label-width="formLabelWidth">
        <el-input v-model="newForm.local" autocomplete="off" />
      </el-form-item>
      <el-form-item label="今日体温" prop="today_temp" :label-width="formLabelWidth">
        <el-input v-model="newForm.today_temp"  autocomplete="off" />
      </el-form-item>
      <el-form-item label="近期居住地" prop="recent_home" :label-width="formLabelWidth">
        <el-input v-model="newForm.recent_home"  autocomplete="off" />
      </el-form-item>
      <el-form-item label="是否确诊" prop="confirmed" :label-width="formLabelWidth">
        <el-radio-group v-model="newForm.confirmed" class="ml-4">
          <el-radio label="1">是</el-radio>
          <el-radio label="0">否</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="是否为无症状" prop="asymptomatic" :label-width="formLabelWidth">
        <el-radio-group v-model="newForm.asymptomatic" class="ml-4">
          <el-radio label="1">是</el-radio>
          <el-radio label="0">否</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="是否不适" prop="fever_and_cough" :label-width="formLabelWidth">
        <el-radio-group v-model="newForm.fever_and_cough" class="ml-4">
          <el-radio label="1">是</el-radio>
          <el-radio label="0">否</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="详细描述" placeholder=如有不适请填写详细描述  :label-width="formLabelWidth">
        <el-input v-model="newForm.cough"  autocomplete="off" />
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
import {reactive, toRefs, ref, defineProps, defineEmits, watch} from 'vue'
import {addhealth} from '../request/api'
import Cookie from "js-cookie";

const props = defineProps<{
  visible:boolean;
  form:health
}>()
const state = reactive <{
  formLabelWidth:string;
  newForm:health
}> ({
  formLabelWidth:'120px',
  newForm: {}
})
const { formLabelWidth,newForm } = toRefs(state)

watch(()=> props.form,()=>{
  newForm.value = {...props.form}
  console.log(newForm.value)
})

const emit = defineEmits<{
  (event:'close',r?: 'reload'):void
}>();
//点击关闭
const close = (r?: 'reload') =>{
  //传到父组件
  newForm.value = {}
  emit('close',r);
}

//校验规则
const rules = reactive({
  local:[{required: true, message: '*必填项',trigger: 'blur' }],
  today_temp:[{required: true, message: '*必填项', trigger: 'blur' }],
  recent_home:[{required: true, message: '*必填项', trigger: 'blur' }],
  confirmed:[{required: true, message: '*必填项', trigger: 'blur' }],
  asymptomatic:[{required: true, message: '*必填项', trigger: 'blur' }],
  fever_and_cough:[{required: true, message: '*必填项', trigger: 'blur' }],
})


const modify = () =>{
  newForm.value.snum=Cookie.get("snum")
  newForm.value.health_status=1
  newForm.value.create_time=Date.now()
  addhealth(newForm.value).then(res=>{
    if (res.code===200){
      ElMessage({
        showClose: true,
        message: res.message,
        type: 'success'
      })
    }else {
      ElMessage({
        showClose: true,
        message: res.message,
        type: 'error'
      })
    }
    close('reload')
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