<template>
  <el-table :data="tableData" border style="width: 100%">
    <el-table-column prop="id" label="编号"  />
    <el-table-column prop="name" label="角色"  />
    <el-table-column prop="remark" label="权限" />
    <el-table-column  label="操作" >
<!--      <template v-slot:default="scope">
        <el-switch v-model="scope.row.status" :active-value="1" :inactive-value="0"/>
      </template>-->
    </el-table-column>

  </el-table>
</template>

<script lang="ts" setup>
/*引入*/
import {reactive,toRefs,ref} from 'vue'
import {gerRole} from '../../request/api'

const state = reactive<{
  tableData:{}[]
}>({
  tableData:[]
})
const {tableData} = toRefs(state)
gerRole({
  name:'',
  Page:5,
  limit:5
}).then(res=>{
  if (res.code){
    tableData.value = res.result.records
  }
})
</script>

<style scoped>

</style>