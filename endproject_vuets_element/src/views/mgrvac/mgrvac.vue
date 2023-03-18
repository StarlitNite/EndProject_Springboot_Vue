<template>
  <el-table :data="tableData" border style="width: 100%">
    <el-table-column prop="id" label="编号" width="56" />
    <el-table-column prop="snum" label="学号"  />
    <el-table-column prop="reason" label="原因"  />
    <el-table-column prop="address" label="去向" />
    <el-table-column prop="day" label="离校天数"  />
    <el-table-column prop="phone" label="联系方式" width="129" />
    <el-table-column prop="node_status" label="状态" >
      <template v-slot:default="scope">
        <el-tag v-if="scope.row.node_status==0" type="info">未提交</el-tag>
        <el-tag v-if="scope.row.node_status==1" >审核中</el-tag>
        <el-tag v-if="scope.row.node_status==2" type="success">审核通过</el-tag>
        <el-tag v-if="scope.row.node_status==3" type="danger">教师已驳回</el-tag>
        <el-tag v-if="scope.row.node_status==4" type="info">已结束</el-tag>
      </template>
    </el-table-column>
    <el-table-column prop="create_time" label="创建时间" />
    <el-table-column prop="update_time" label="更新时间" />
    <el-table-column  label="操作" width="223">
      <template #default="{row}">
        <!--        <el-switch v-model="scope.row.status" :active-value="1" :inactive-value="0"/>-->
        <el-button :disabled="row.node_status!=1?true:false" text @click="update"> 批准 </el-button>
        <el-button :disabled="row.node_status!=1?true:false" text @click="DeleteRole"> 驳回 </el-button>
        <el-button text @click="SaveRole"> 详情 </el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script lang="ts" setup>
import {reactive, toRefs, ref, watch} from 'vue'
import {getleave,updateleave} from '../../request/api'
import Cookie from "js-cookie";

const state = reactive<{
  tableData:{}[],
  visible:boolean
  rowData:leave
}>({
  tableData:[],
  visible:false,
  rowData:{}
})

const {tableData,rowData,visible} = toRefs(state)
const role_id = Cookie.get("role_id")
const snum = Cookie.get("snum")
getleave({
  role_id:role_id,
  snum:snum,
  Page:1,
  limit:10
}).then(res=>{
  if (Cookie.get(role_id)!=1){
    tableData.value = res.result;
    console.log(tableData.value)
  }else {
    tableData.value = res.result.records
    console.log("--------------"+tableData.value)
  }
})

const update = () =>{
    updateleave(tableData.value)//有问题
}


</script>

<style scoped>

</style>