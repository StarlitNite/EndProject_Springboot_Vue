<template>
  <el-row :gutter="20">
    <el-col :span="8">
      <el-input placeholder="请输入学号" v-model="info.snum" clearable @clear="searchUser">
        <template #append>
          <el-button @click="searchUser"><el-icon><Search /></el-icon></el-button>
        </template>
      </el-input>
    </el-col>
    <el-col :span="10">
      <input type="file" accept=".xls,.xlsx" class="upload-file" @change="changeExcel($event)" />
    </el-col>
  </el-row>
  <el-table :data="tableData" border style="width:100%" v-if="tableData.nstu!=1">
<el-table-column prop="id" label="编号" /><!-- width="64"    -->
    <el-table-column prop="snum" label="学号"  /><!-- width="129"   -->
    <el-table-column prop="username" label="姓名"  /><!--  width="94"   -->
    <!-- <el-table-column prop="password" label="密码" width="162" />   -->
    <el-table-column prop="dorm_address" label="宿舍号" width="85" /><!--    -->
    <el-table-column prop="tel" label="联系方式"  /><!--width="178"    -->
    <el-table-column prop="gender" label="性别"  ><!--width="82"    -->
      <template v-slot:default="scope">
        <el-tag v-if="scope.row.gender==1">男</el-tag>
        <el-tag v-if="scope.row.gender==0">女</el-tag>
      </template>
    </el-table-column>
<!--    <el-table-column prop="salt" hidden label="盐" />-->
    <el-table-column prop="role_name" label="权限"  /><!--   width="103" -->
    <el-table-column prop="classe_name" label="班级"  /><!--width="125"    -->
    <el-table-column prop="major_name" label="专业" width="174"/><!--     -->
    <el-table-column prop="department_name" label="院系"  /><!-- width="129"   -->
    <el-table-column prop="counselor_name" label="导员"  /><!-- width="86"   -->
    <el-table-column prop="status" label="状态" width="95" ><!--     -->
      <template v-slot:default="scope">
          <el-tag v-if="scope.row.status==1">在校</el-tag>
          <el-tag v-if="scope.row.status==0" type="danger">离校</el-tag>
      </template>
    </el-table-column>
    <el-table-column prop="health_status" label="填报状态"  ><!--width="95"    -->
      <template v-slot:default="scope">
        <el-tag v-if="scope.row.health_status==1">已填报</el-tag>
        <el-tag type="warning" v-if="scope.row.health_status==0">未填报</el-tag>
      </template>
    </el-table-column>
    <el-table-column  label="操作" width="204" ><!--    -->
      <template #default="{row}" >
          <el-button text @click="UpdateRole(row)"> 修改 </el-button>
        <el-popconfirm
            confirm-button-text="Yes"
            cancel-button-text="No"
            title="确认操作？"
            @cancel="cancelEvent"
            @confirm="confirmEvent(row.id)"
        >
          <template #reference>
            <el-button text> 删除 </el-button>
          </template>
        </el-popconfirm>
      </template>
    </el-table-column>
  </el-table>
  <editUserDialog :visible="visible" @close="closeDialog" :form="rowData"/>

  <div >
    <el-pagination
        :current-page=info.Page
        :page-size=info.limit
        :page-sizes="[5,10, 50, 100]"
        layout="total, sizes, prev, pager, next, jumper"
        :total="pagination"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
    />
  </div>
</template>

<script lang="ts" setup>
import {reactive, toRefs, ref, watch} from 'vue'
import {delMenu, delUser, getAllMenus, getUser} from '../../request/api'
import editUserDialog from '../../components/editUserDialog.vue'
import { Search } from "@element-plus/icons-vue";
const state = reactive<{
  tableData:{}[],
  pagination:[]//分页
  visible:boolean,
  rowData:user,
  pageSize:number
}>({
      tableData:[],
      pagination:[],
      visible:false,
      rowData: {},
      pageSize:''
}
)
const {tableData,visible,rowData,pagination,pageSize} = toRefs(state)
let info=reactive({
  snum:"",
  Page:1,
  limit:10
})
getUser({
  snum:info.snum,
  Page:info.Page,
  limit:info.limit
}).then(res=>{
  if (res.code===200){
    tableData.value = res.result.records
    pagination.value = res.result.total;
    pageSize.value = res.result.size;
    console.log(pageSize.value)
  }
})
const searchUser = ()=>{
  console.log(info.snum);
  getUser({
    snum:info.snum,
    Page:info.Page,
    limit:info.limit
  }).then(res=>{
    if (res.code===200){
      tableData.value = res.result.records
      pagination.value = res.result.total;
      pageSize.value = res.result.size;
    }
  })
}

const handleSizeChange = (limit:number)=>{
  info.limit = limit
  console.log(info.limit);
  getUser({
    snum:info.snum,
    Page:info.Page,
    limit:info.limit
  }).then(res=>{
    if (res.code===200){
      tableData.value = res.result.records
      pagination.value = res.result.total;
      pageSize.value = res.result.size;
      console.log("pageSize.value")
      console.log(pageSize.value)
    }
  })
  console.log("handleSizeChange")
}
const handleCurrentChange = (Page:number)=>{
  info.Page = Page
  console.log(Page);
  getUser({
    snum:info.snum,
    Page:info.Page,
    limit:info.limit
  }).then(res=>{
    if (res.code===200){
      tableData.value = res.result.records
      pagination.value = res.result.total;
      pageSize.value = res.result.size;
      console.log(pageSize.value)
    }
  })
  console.log("handleCurrentChange")
}

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
    getUser({
      snum:info.snum,
      Page:info.Page,
      limit:info.limit
    }).then(res=>{
      if (res.code===200){
        tableData.value = res.result.records
        pagination.value = res.result.total;
        pageSize.value = res.result.size;
        console.log("pageSize.value")
        console.log(pageSize.value)
      }
    })
  }
}

//删除学生
const confirmEvent = id => {
  console.log(id)
  delUser(id).then(res =>{
    if (res.code===200){
      ElMessage({
        showClose: true,
        message: '操作成功',
        type: 'success'
      })
      getUser({
        snum:info.snum,
        Page:info.Page,
        limit:info.limit
      }).then(res=>{
        if (res.code===200){
          tableData.value = res.result.records
          pagination.value = res.result.total;
          pageSize.value = res.result.size;
          console.log("pageSize.value")
          console.log(pageSize.value)
        }
      })
    }
  })
}
const cancelEvent = () =>{
  ElMessage({
    showClose: true,
    message: '操作取消',
    type: 'warning'
  })
}
</script>

<style scoped>

</style>