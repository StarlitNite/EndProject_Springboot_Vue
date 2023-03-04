<template>
  <el-table :data="tableData" border style="width: 100%">
    <el-table-column prop="id" label="编号"  />
    <el-table-column prop="name" label="角色"  />
    <el-table-column prop="remark" label="权限" />
    <el-table-column  label="操作" >
      <template #default="{row}">
<!--        <el-switch v-model="scope.row.status" :active-value="1" :inactive-value="0"/>-->
        <el-button text @click="UpdateRole(row)"> 修改 </el-button>
        <el-button text @click="DeleteRole"> 删除 </el-button>
        <el-button text @click="SaveRole"> 分配权限 </el-button>
      </template>
    </el-table-column>
  </el-table>
  <UpdateDialog :visible="visible" @click="closeDialog" :form="rowData"/>
</template>

<script lang="ts" setup>
/*引入*/
import {reactive, toRefs, ref, watch} from 'vue'
import {gerRole} from '../../request/api'
import UpdateDialog from '../../components/UpdateDialog.vue'


const state = reactive<{
  tableData:{}[],
  visible:boolean,
  rowData:{}
}>({
  tableData:[],
  visible:false,
  rowData:{}
})
const {tableData, visible ,rowData} = toRefs(state)
gerRole({
  name:'',
  Page:5,
  limit:5
}).then(res=>{
  if (res.code){
    tableData.value = res.result.records
  }
})

//点击编辑按钮
const UpdateRole = (row:{}) =>{
  console.log(visible.value)
  visible.value = true;
  rowData.value = row;
}



//隐藏Dialog
const closeDialog = ()=>{
  visible.value = false;
}

</script>

<style scoped>

</style>