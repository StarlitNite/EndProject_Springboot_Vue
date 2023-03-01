<template>
  <div class="common-layout">
    <el-container>
      <el-aside width="200px">
        <el-col :span="12">
        <h5 class="mb-2">Custom colors</h5>
        <el-menu
            active-text-color="#ffd04b"
            background-color="#545c64"
            class="el-menu-vertical-demo"
            default-active="2"
            text-color="#fff"
            :unique-opened="true"
            :router="true"
        >
          <el-sub-menu :index="menu.id+''" v-for="menu in Menus " :key="menu.id">
            <template #title>
              <span>{{ menu.title }}</span>
            </template>
            <el-menu-item :index="'/'+submenu.path" v-for="submenu in menu.child" :key="submenu.id">{{ submenu.title }}</el-menu-item>
          </el-sub-menu>
        </el-menu>
      </el-col></el-aside>
      <el-container>
        <el-header>Header</el-header>
        <el-main><router-view/></el-main>
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
</script>

<style scoped>

</style>