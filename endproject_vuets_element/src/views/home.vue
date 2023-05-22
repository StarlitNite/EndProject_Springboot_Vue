<template>
  <div class="common-layout">
    <el-container class="homecontainer">
      <el-header>
        <div class="head">
          <div class="div1" >校园疫情管理系统</div>
          <div class="div2">
            <el-button type="info"  @click="editpwd2">修改密码</el-button>
            <el-button type="info"  @click="logout">退出</el-button>
          </div>
        </div>
      </el-header>

      <el-container>
        <el-aside >
          <el-col :span="12">
            <el-menu
                active-text-color="#ffd04b"
                background-color="#545c64"
                class="el-menu-vertical-demo"
                default-active="2"
                text-color="#fff"
                :unique-opened="true"
                :router="true"
                width="200px"
            >
              <template  v-for="menu in Menus "  >
                <el-sub-menu v-if="menu.child.length"  :index="menu.id+''" :key="menu.id" >
                  <template #title>
                    {{ menu.title }}
                  </template>
                  <el-menu-item :index="'/'+submenu.path" v-for="submenu in menu.child" :key="submenu.id">{{ submenu.title }}</el-menu-item>
                </el-sub-menu>
                <el-menu-item v-else :index="'/'+menu.path" :key="menu.key">
                  {{ menu.title }}
                </el-menu-item>
              </template>
            </el-menu>
          </el-col></el-aside>
        <el-main >
          <router-view/>
        </el-main>
      </el-container>
    </el-container>
  </div>
  <editPwdDialog :visible="editpwdVisible" @close="closeDialog"/>
</template>

<script lang="ts" setup>
import {useStore} from 'vuex'
import {editpwd} from '../request/api'
import Cookie from "js-cookie";
import {useRouter} from "vue-router";
import {reactive, toRefs} from "vue";

let router = useRouter();


const state = reactive<{
  editpwdVisible:boolean,
}>({
  editpwdVisible:false,
})
const {editpwdVisible } = toRefs(state)


interface menuObj{
  pid:number
  id:number
  title:string
  child?:menuObj[]
  path:string
}
interface menus{
  [key: number]:menuObj
}
const store = useStore();
//清除cookie
const clearcookie =()=> {
  Cookie.remove('token');
  Cookie.remove('userName');
  Cookie.remove('role_id');
  Cookie.remove('snum');
  router.push('/login');
}
const Menus: menus= store.state.menus;
const logout = () => {//退出登录
  clearcookie()
}
const editpwd2 = () =>{
  editpwdVisible.value = true;
}

//隐藏Dialog
const closeDialog = (r?:'reload')=>{//问号是可选属性
  editpwdVisible.value = false;
  if (r==='reload'){
    clearcookie()
  }
}
</script>

<style scoped>
.homecontainer{
  height: 763px;
  width: 100%;

}
.head{
  display: flex ;
  width:100%;
  justify-content: space-between;
  align-items: center;
  height:100%;
}
/*.div1{*/
/*  align-items: center;*/
/*  margin-top: 25px;*/
/*}*/
/*.div2{*/
/*  margin-left: 1270px;*/
/*  margin-top: 30px;*/
/*  float: right;*/
/*  font-size: 12px;*/
/*}*/
.el-header{
  background-color: #1b7cfbbb;
  color: #333;
  text-align: center;
  line-height: 10px;
}

.el-aside {
  background-color: #D3DCE6;
  color: #333;
  height: 100%;
  width: 150px;
  text-align: center;
  line-height: 200px;
}

.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: start;
  line-height: 10px;
}

.el-menu-vertical-demo{
  width: 150px;
  min-height: 150px;
}
</style>