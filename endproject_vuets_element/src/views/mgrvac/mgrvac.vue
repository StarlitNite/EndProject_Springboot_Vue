<template>
  <template v-if="role_id==3">
    <el-button @click="details">请假</el-button>
  </template>
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
    <el-table-column label="创建时间" >
      <template v-slot:default="scope">{{ formateDate(scope.row.create_time) }}</template>
    </el-table-column>
    <el-table-column  label="更新时间" >
      <template v-slot:default="scope">{{ formateDate(scope.row.update_time) }}</template>
    </el-table-column>
    <el-table-column  label="操作" >
      <template v-if="role_id!=3" #default="{row}">
        <el-button :disabled="row.node_status!=1?true:false" text @click="updateleave(row)"> 审批 </el-button>
      </template>
      <template v-else #default="{row}">
        <el-popconfirm
            confirm-button-text="Yes"
            cancel-button-text="No"
            title="确认操作？"
            @cancel="cancelEvent"
            @confirm="confirmEvent(row.id)"
        >
          <template #reference>
            <el-button :disabled="row.node_status!=1?true:false" text> 取消申请 </el-button>
          </template>
        </el-popconfirm>
      </template>

    </el-table-column>
  </el-table>
  <UpdateLeaveDialog :visible="visible" @close="closeDialog" :form="rowData"/>
  <leavedetails :visible="leavevisible" @close="closeleaveDialog" />
</template>

<script lang="ts" setup>
import {reactive, toRefs, ref, watch} from 'vue'
import {getleave,delleave} from '../../request/api'
import Cookie from "js-cookie";
import UpdateLeaveDialog  from "../../components/UpdateLeaveDialog.vue";
import leavedetails from "../../components/leavedetails.vue"


const state = reactive<{
  tableData:{}[],
  visible:boolean,
  rowData:leave,
  leavevisible:boolean,
  // leaveData:leave
}>({
  tableData:[],
  visible:false,
  rowData:{},
  leavevisible:false,
  // leaveData:{}
})

const {tableData,rowData,visible,leavevisible,leaveData} = toRefs(state)
const role_id = Cookie.get("role_id")
const snum = Cookie.get("snum")
getleave({
  role_id:role_id,
  snum:snum,
  page:1,
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

const updateleave = (row:{}) =>{
  visible.value = true;
  rowData.value = row;
}

const details = () =>{
  leavevisible.value = true;
}


//隐藏Dialog
const closeDialog = (r?:'reload')=>{//问号是可选属性
  visible.value = false;
  rowData.value = {};//清空编辑框
  if (r==='reload'){
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
  }
}

//关闭创建请假弹窗
const closeleaveDialog = (r?:'reload')=> {
  leavevisible.value = false;
  if (r === 'reload') {
    getleave({
      role_id: role_id,
      snum: snum,
      Page: 1,
      limit: 10
    }).then(res => {
      if (Cookie.get(role_id) != 1) {
        tableData.value = res.result;
        console.log(tableData.value)
      } else {
        tableData.value = res.result.records
        console.log("--------------" + tableData.value)
      }
    })
  }
}

//取消请假
const confirmEvent = id => {
  console.log(id)
  delleave(id).then(res =>{
    if (res.code===200){
      alert("操作成功")
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
    }
  })
}
const cancelEvent = () =>{
  alert("操作取消")
}

const formateDate = (time: string|undefined) =>{
  if (!time) return '';
  const date = new Date(time);
  let year = date.getFullYear();
  let month = addZero(date.getMonth()+1);
  let day = addZero(date.getDate());
  let hour = addZero(date.getHours());
  let min = addZero(date.getMinutes());
  let sec = addZero(date.getSeconds());

  return `${year}-${month}-${day} ${hour}:${min}:${sec}`
}

const addZero = (num:number) =>{
  return num > 9 ? num:'0'+num
}

</script>

<style scoped>

</style>