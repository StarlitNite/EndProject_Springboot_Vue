<template>
  <div class="common-layout">
    <el-container>
      <el-aside width="200px" height="100%">
        <el-col :span="12">
        <h5 class="mb-2">疫情管理系统</h5>
        <el-menu
            active-text-color="#ffd04b"
            background-color="#545c64"
            class="el-menu-vertical-demo"
            default-active="2"
            text-color="#fff"
            :unique-opened="true"
            :router="true"
        >
          <template  v-for="menu in Menus ">
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
      <el-container>
        <el-header>

          <el-button type="info"  @click="logout">退出</el-button>
        </el-header>
        <el-main>
          <router-view/>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script lang="ts" setup>
import {useStore} from 'vuex'

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
const Menus: menus= store.state.menus;
const logout = () =>{//退出登录
  window.sessionStorage.clear()
  router.push('/login')
}

</script>

<style scoped>

</style>