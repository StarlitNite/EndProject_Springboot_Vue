<template>
  <div class="m-btn">
    导入表格
    <input type="file" accept=".xls,.xlsx" class="upload-file" @change="changeExcel($event)" />
  </div>
  <el-table :data="tableData" border style="width:100%" v-if="tableData.nstu!=1">
    <el-table-column prop="id" label="编号" width="64" />
    <el-table-column prop="snum" label="学号" width="129" />
    <el-table-column prop="username" label="姓名" width="94" />
    <el-table-column prop="password" label="密码" width="162" />
    <el-table-column prop="dorm_address" label="宿舍号" width="85" />
    <el-table-column prop="tel" label="联系方式" width="178" />
    <el-table-column prop="gender" label="性别" width="82" >
      <template v-slot:default="scope">
        <el-tag v-if="scope.row.gender==1">男</el-tag>
        <el-tag v-if="scope.row.gender==0">女</el-tag>
      </template>
    </el-table-column>
<!--    <el-table-column prop="salt" hidden label="盐" />-->
    <el-table-column prop="role_name" label="权限" width="103" />
    <el-table-column prop="classe_name" label="班级" width="125" />
    <el-table-column prop="major_name" label="专业" width="174" />
    <el-table-column prop="department_name" label="院系" width="129" />
    <el-table-column prop="counselor_name" label="导员" width="86" />
    <el-table-column prop="status" label="状态" width="95" >
      <template v-slot:default="scope">
          <el-tag v-if="scope.row.status==1">在校</el-tag>
          <el-tag v-if="scope.row.status==0">离校</el-tag>
      </template>
    </el-table-column>
    <el-table-column  label="操作" width="284" >
      <template #default="{row}" >
          <el-button text @click="(row)"> 修改 </el-button>
          <el-button text @click="DeleteRole"> 删除 </el-button>
          <el-button text @click="SaveRole"> 提醒填报 </el-button>
      </template>
    </el-table-column>
  </el-table>
  <editUserDialog :visible="visible" @close="closeDialog" :form="rowData"/>
  <div class="demo-pagination-block">
    <el-pagination
        v-model:current-page="currentPage3"
        v-model:page-size="pageSize3"
        layout="prev, pager, next, jumper"
        :total="pagination"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
    />
  </div>
</template>

<script lang="ts" setup>
import {reactive, toRefs, ref, watch} from 'vue'
import {getRole, getUser} from '../../request/api'
import editUserDialog from '../../components/editUserDialog.vue'

const state = reactive<{
  tableData:{}[],
  pagination:[]//分页
  visible:boolean,
  rowData:user
}>({
      tableData:[],
      pagination:[],
      visible:false,
      rowData: {}
}
)
const {tableData,visible,rowData,pagination} = toRefs(state)

getUser({
  snum:'',
  Page:1,
  limit:10
}).then(res=>{
  if (res.code===200){
    tableData.value = res.result.records

    pagination.value = res.result.pages;


  }
})

/*
const tableData = ref([]);//表格数据
const changeExcel = (e) =>{
  const files = e.target.files;
  if (files.length <= 0) {
    return false
  } else if (!/\.(xls|xlsx)$/.test(files[0].name.toLowerCase())) {
    return false
  }
  // 读取表格数据
  const fileReader = new FileReader();
  fileReader.onload = ev => {
    const workbook = XLSX.read(ev.target.result, {
      type: "binary"
    })
    const wsname = workbook.SheetNames[0];
    const ws = XLSX.utils.sheet_to_json(workbook.Sheets[wsname]);
    dealExcel(ws);//转换数据格式
    tableData.value = ws;//赋值
  }
  fileReader.readAsBinaryString(files[0])
}
*/
//点击编辑按钮
const UpdateRole = (row:{}) =>{
  visible.value = true;
  rowData.value = row;
}
//隐藏Dialog
const closeDialog = (r?:'reload')=>{
  visible.value = false;
  rowData.value = {};//清空编辑框
  if (r==='reload'){
    gerRole({
      name:'',
      Page:5,
      limit:5
    }).then(res=>{
      if (res.code===200){
        tableData.value = res.result.records
      }
    })
  }
}
</script>

<style scoped>

</style>