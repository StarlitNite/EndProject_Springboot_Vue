<template>
  <el-dialog :model-value="visible" title="修改密码" :before-close="close">
    <el-form :model="newForm" ref="ruleFormRef"  :label-width="formLabelWidth" :rules="rules">
      <el-form-item label="密码" prop="password" :label-width="formLabelWidth">
        <el-input
            v-model="newForm.password"
            type="password"
            placeholder="Please input password"
            show-password
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
  newForm:adminLoginData
}> ({
  formLabelWidth:'120px',
  newForm: {}
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

//密码校验
const validatePassWord = (rule:unknown,value:String|undefined,cb:(msg?:string) => void)=>{
  if(!value){
    cb('密码不能为空');
  }else{
    cb();
  }
}

//校验规则
const rules = reactive({
  password:[{
    validator: validatePassWord,trigger:'blur'
  }, { min: 6, max: 12, message: '密码长度为6-12位', trigger: 'blur' },
  ]
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
    console.log('校验不通过')
  })

}
</script>

<style lang="less" scoped>

</style>