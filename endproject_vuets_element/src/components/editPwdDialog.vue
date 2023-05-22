<template>
  <el-dialog :model-value="visible" title="修改密码" :before-close="close" width="20%">
    <el-form :model="newForm" ref="ruleFormRef"  :label-width="formLabelWidth" :rules="rules">
      <el-form-item label="密码" prop="password" :label-width="formLabelWidth">
        <el-input
            v-model="newForm.password"
            type="password"
            placeholder="请输入密码"
            show-password
        />
      </el-form-item>
      <el-form-item label="确认密码" prop="checkPass">
        <el-input
            v-model="newForm.checkPass"
            type="password"
            placeholder="请再次输入密码"
            autocomplete="off"
        />
      </el-form-item>
<!--      <el-form-item label="确认密码"  :label-width="formLabelWidth">
        <el-input
            v-model="password"
            type="password"
            placeholder="Please input password"
            show-password
        />
      </el-form-item>-->
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
import {editpwd} from "../request/api";
import Cookie from "js-cookie";
const props = defineProps<{
  visible:boolean;
}>()


const state = reactive <{
  formLabelWidth:string;
  newForm:editpassword
}> ({
  formLabelWidth:'120px',
  newForm: {
    password: '',
    checkPass: '',
  }
})
const { formLabelWidth,newForm } = toRefs(state)
//获取el-form组件对象
let ruleFormRef = ref();

const emit = defineEmits<{
  (event:'close',r?: 'reload'):void
}>();
//点击关闭
const close = (r?: 'reload') =>{
  //传到父组件
  newForm.value={}
  emit('close',r);
}

/*//密码校验
const validatePassWord = (rule:unknown,value:String|undefined,cb:(msg?:string) => void)=>{
  if(!value){
    cb('密码不能为空');
  }else{
    cb();
  }
}*/
const validatePass = (rule: any, value: any, callback: any) => {
  if (value === '') {
    callback(new Error('密码不能为空!'))
  } else {
    if (newForm.value.checkPass !== '') {
      if (!ruleFormRef.value) return
      ruleFormRef.value.validateField('checkPass', () => null)
    }
    callback()
  }
}

const validatePass2 = (rule: any, value: any, callback: any) => {
  if (value === '') {
    callback(new Error('请再次输入密码'))
  } else if (value !== newForm.value.password) {
    callback(new Error("两次输入的密码不一致!"))
  } else {
    callback()
  }
}

//校验规则
const rules = reactive({
  password:[
    {validator: validatePass,trigger:'blur'},
    {required: true,min: 6, max: 12, message: '密码长度为6-12位', trigger: 'blur' },
  ],
  checkPass:[{required: true,validator: validatePass2, trigger: 'blur' }]
})


const modify = ()=>{
  newForm.value.snum = Cookie.get('snum')
  ruleFormRef.value.validate().then(()=>{
    editpwd(newForm.value).then(res=>{
      if (res.code===200){
        close('reload')
        ElMessage({
          showClose: true,
          message: '修改成功,请重新登录',
          type: 'success'
        })
      }
    })
  }).catch(()=>{
    ElMessage({
      showClose: true,
      message: '校验失败，请重试',
      type: 'warning'
    })
  })

}
</script>

<style lang="less" scoped>

</style>