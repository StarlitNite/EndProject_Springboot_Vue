<template>
  <el-button @click="SaveMenu">新增菜单</el-button>
  <el-table :data="tableData" border style="width: 100%">
    <el-table-column prop="id" label="菜单编号" /><!--   width="64"-->
    <el-table-column prop="pid" label="所属父菜单编号"  /><!--   width="164"-->
    <el-table-column prop="title" label="菜单名称"  /><!--  width="234"-->
    <el-table-column prop="path" label="路径" /><!--   width="276"-->
    <el-table-column  label="操作"  ><!--   width="674"-->
      <template  #default="{row}">
        <el-button text @click="updateMenu(row)"> 修改 </el-button>
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
  <SaveMenuDialog :visible="SaveMenuVisible" @close="closeDialog"/>
  <UpdateMenuDialog :visible="updateMenuVisible" @close="closeSaveDialog" :form="rowData"/>
</template>

<script lang="ts" setup>
import {reactive, toRefs, ref, watch} from 'vue'
import {delMenu, getAllMenus} from '../../request/api'
import Cookie from "js-cookie";

const state = reactive<{
  tableData:{}[],//{}指的是对象类型
  SaveMenuVisible:boolean,
  updateMenuVisible:boolean,
  rowData:menu,
}>({
  tableData:[],
  SaveMenuVisible:false,
  updateMenuVisible:false,
  rowData:{},
})
const {tableData, SaveMenuVisible,updateMenuVisible ,rowData} = toRefs(state)

getAllMenus().then(res=>{
  if (res.code===200){
    console.log(res.result)
    tableData.value = res.result
  }
})

//添加菜单
const SaveMenu = ()=>{
  SaveMenuVisible.value = true;

}


//修改菜单
const updateMenu=(row:{})=>{
  updateMenuVisible.value = true;
  rowData.value = row;
}



//隐藏Dialog
const closeDialog = (r?:'reload')=>{//问号是可选属性
  SaveMenuVisible.value = false;
  rowData.value = {};//清空编辑框
  if (r==='reload'){
    getAllMenus().then(res=>{
      if (res.code===200){
        console.log(res.result)
        tableData.value = res.result
      }
    })
  }
}
//隐藏修改菜单Dialog
const closeSaveDialog = (r?:'reload')=>{
  updateMenuVisible.value = false;
  rowData.value = {};
  if (r==='reload'){
    getAllMenus().then(res=>{
      if (res.code===200){
        console.log(res.result)
        tableData.value = res.result
      }
    })
  }
}

//删除菜单
const confirmEvent = id => {
  console.log(id)
  delMenu(id).then(res =>{
    if (res.code===200){
      ElMessage({
        showClose: true,
        message: '操作成功',
        type: 'success'
      })
      getAllMenus().then(res=>{
        if (res.code===200){
          console.log(res.result)
          tableData.value = res.result
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