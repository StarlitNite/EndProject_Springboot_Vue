<template>
  <el-form
      ref="ruleFormRef"
      :model="ruleForm"
      :rules="rules"
      class="login-box"
  >
    <h3 class="login-title">欢迎登录校园疫情管理系统</h3>
    <el-form-item label="账号" prop="snum">
      <el-input v-model="ruleForm.snum" placeholder="请输入学(工)号" type="text" autocomplete="off" />
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input v-model="ruleForm.password" placeholder="请输入密码" type="password" autocomplete="off"/>
    </el-form-item>
    <el-form-item label="身份" prop="role">
      <el-select v-model="ruleForm.role_id" placeholder="请选择身份">
        <el-option label="管理员" value="1" />
        <el-option label="辅导员" value="2" />
        <el-option label="学生" value="3" />
      </el-select>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="Login()">登录</el-button>
    </el-form-item>
  </el-form>
</template>

<script lang='ts' setup>
import { reactive,toRefs,ref ,toRef} from 'vue'
import {adminLogin, getMenu} from "../request/api";
import Cookie from "js-cookie";
import {useRouter} from "vue-router"
import {useStore} from "vuex";

const state = reactive({
  ruleForm:{
    snum:'',//admin1
    password:'',
    role_id:''
  },

})
//获取ruleForm
let { ruleForm } = toRefs(state);
//获取el-form组件对象
let ruleFormRef = ref();
//获取路由对象
let router = useRouter();
//获取项目vuex对象
let store = useStore();

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
  snum:[
    {message:'用户名不能为空',trigger:'blur'}//required: true,
  ],
  password:[
    {validator: validatePassWord,trigger:'blur'},//required: true,
  ],
})


//登录
const Login=() => {
  ruleFormRef.value.validate().then( () =>{
    adminLogin({
      "password": ruleForm.value.password,
      "snum": ruleForm.value.snum,
      "role_id": ruleForm.value.role_id,
    }).then(res =>{
      if (res.code == 200){
        //存储token
        Cookie.set('token',res.result.token)
        Cookie.set('userName',res.result.userName)
        Cookie.set('role_id',res.result.role_id)
        Cookie.set('snum',res.result.snum)
        //获取菜单
        store.dispatch('getMenu').then(res=>{
          router.push('/home')
        })
        /*getMenu().then(res =>{
          if (res.code==200){
            console.log(res)
            store.commit("Menus",res.result)
            //跳转Home页面
            router.push('/Home')
          }
        })*/
      }else {
        ElMessage({
          showClose: true,
          message: res.message,
          type: 'error'
        })
      }
    })
  }).catch(()=>{
    console.log('校验不通过')
  })
}


</script>

<style lang='less' scoped>
.login-box {
  border: 1px solid #DCDFE6;
  width: 350px;
  margin: 180px auto;
  padding: 35px 35px 15px 35px;
  border-radius: 5px;
  -webkit-border-radius: 5px;
  -moz-border-radius: 5px;
  box-shadow: 0 0 25px #909399;
}
.login-title {
  text-align: center;
  margin: 0 auto 40px auto;
  color: #303133;
}
</style>