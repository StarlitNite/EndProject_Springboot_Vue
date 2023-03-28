<template>
  <template v-if="role_id==3">
    <el-button @click="addHealth">新增填报</el-button>
  </template>
  <el-table :data="tableData" border style="width: 100%">
    <el-table-column prop="id" label="编号" width="56" />
    <el-table-column prop="snum" label="学号"  />
    <el-table-column prop="local" label="当前所在地"  />
    <el-table-column prop="confirmed" label="是否确诊">
      <template v-slot:default="scope">
      <el-tag type="danger" v-if="scope.row.confirmed==1">是</el-tag>
      <el-tag type="success" v-if="scope.row.confirmed==0">否</el-tag>
      </template>
    </el-table-column>
    <el-table-column prop="asymptomatic" label="是否无症状"  >
      <template v-slot:default="scope">
      <el-tag type="danger" v-if="scope.row.asymptomatic==1">是</el-tag>
      <el-tag type="success" v-if="scope.row.asymptomatic==0">否</el-tag>
      </template>
    </el-table-column>
    <el-table-column prop="today_temp" label="今日体温" width="129" />
    <el-table-column prop="fever_and_cough" label="是否不适">
      <template v-slot:default="scope">
      <el-tag type="danger" v-if="scope.row.fever_and_cough==1">是</el-tag>
      <el-tag type="success" v-if="scope.row.fever_and_cough==0">否</el-tag>
      </template>
    </el-table-column>
    <el-table-column prop="cough" label="不适时的详细描述"/>
    <el-table-column prop="recent_home" label="近期居住地"/>
    <el-table-column label="创建时间" >
      <template v-slot:default="scope">{{ formateDate(scope.row.create_time) }}</template>
    </el-table-column>
    <el-table-column prop="health_status" label="填报状态" width="95" >
      <template v-slot:default="scope">
        <el-tag v-if="scope.row.health_status==1">已填报</el-tag>
        <el-tag type="warning" v-if="scope.row.health_status==0">未填报</el-tag>
      </template>
    </el-table-column>
    <el-table-column  label="操作" >
      <template v-if="role_id!=3" #default="{row}">
<!--        <el-button :disabled="row.status!=0?true:false" text @click="updateleave"> 提醒填报 </el-button>-->
        <el-button  text @click="updateleave(row)" > 查看填报 </el-button>
      </template>
<!--      <template v-else #default="{row}">
            <el-button  :disabled="row.status!=0?true:false" @click="healthDialog(row)"  text> 填报 </el-button>
      </template>-->
    </el-table-column>
  </el-table>
 <AddHealthDialog :visible="visible" @close="closeDialog" />

</template>

<script lang="ts" setup>
import {reactive, toRefs, ref} from 'vue'
import {gethealth, getleave} from '../../request/api'
import Cookie from "js-cookie";
import UpdateHealthDialog  from '../../components/AddHealthDialog.vue'

const state = reactive<{
  tableData:{}[],
  visible:boolean,
  rowData:health,
}>({
  tableData:[],
  visible:false,
  rowData:{},
})

const {tableData,rowData,visible} = toRefs(state)
const role_id = Cookie.get("role_id")
const snum = Cookie.get("snum")
gethealth({
  role_id:role_id,
  snum:snum,
  page:0,
  limit:10
}).then(res=>{
  if (Cookie.get(role_id)!=1){
    tableData.value = res.result.Healths;
    console.log(tableData.value)
  }else {
    tableData.value = res.result.records;
    console.log("--------------"+tableData.value)
  }
})

const addHealth = () =>{
  visible.value = true
}

//隐藏Dialog
const closeDialog = (r?:'reload')=>{//问号是可选属性
  visible.value = false;
  rowData.value = {};//清空编辑框
  if (r==='reload'){
    gethealth({
      role_id:role_id,
      snum:snum,
      page:0,
      limit:10
    }).then(res=>{
      if (Cookie.get(role_id)!=1){
        tableData.value = res.result.Healths;
        console.log(tableData.value)
      }else {
        tableData.value = res.result.records;
        console.log("--------------"+tableData.value)
      }
    })
  }
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

<style lang="less" scoped>

</style>