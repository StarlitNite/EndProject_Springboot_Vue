<template>
  <el-form
      ref="ruleFormRef"
      :model="ruleForm"
      :rules="rules"
      class="demo-ruleForm"
  >
    <el-form-item label="学(工)号" prop="snum">
      <el-input v-model="ruleForm.snum" type="text" autocomplete="off" />
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input v-model="ruleForm.password" type="password" autocomplete="off"/>
    </el-form-item>
    <el-form-item label="身份" prop="role">
      <el-select v-model="ruleForm.role_id" placeholder="please select your role">
        <el-option label="管理员" value="1" />
        <el-option label="教师" value="2" />
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
    snum:'19027355608',//admin1
    password:'888',
    role_id:'2'
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
  userName:[
    {required: true,message:'用户名不能为空',trigger:'blur'}
  ],
  password:[{
    validator: validatePassWord,trigger:'blur'
  }]
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
      }
    })
  }).catch(()=>{
    console.log('校验不通过')
  })
}


</script>

<style lang='less' scoped>
</style>