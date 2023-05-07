<template>
  <el-table :data="tableData" border style="width: 100%">
    <el-table-column prop="id" label="编号"  />
    <el-table-column prop="name" label="角色"  />
    <el-table-column prop="remark" label="权限" />
    <el-table-column  label="操作" >
      <template #default="{row}">
        <el-button text @click="UpdateRole(row)"> 修改 </el-button>
        <el-button text @click="DeleteRole"> 删除 </el-button>
        <el-button text @click="SaveRole(row)"> 分配权限 </el-button>
      </template>
    </el-table-column>
  </el-table>
  <UpdateDialog :visible="visible" @close="closeDialog" :form="rowData"/>
  <SaveDialog :visible="Savevisible" @close="closeSaveDialog" :tree="TreeData"/>
</template>

<script lang="ts" setup>
/*引入*/
import {reactive, toRefs, ref, watch} from 'vue'
import {getRole} from '../../request/api'
import UpdateDialog from '../../components/UpdateDialog.vue'
import SaveDialog from '../../components/SaveDialog.vue'


const state = reactive<{
  tableData:{}[],//{}指的是对象类型
  visible:boolean,
  rowData:Role,
  TreeData:Role,
  Savevisible:boolean,
}>({
  tableData:[],
  visible:false,
  rowData:{},
  TreeData:{},
  Savevisible:false
})
const {tableData, visible ,rowData,Savevisible,TreeData} = toRefs(state)
getRole({
  name:'',
  Page:5,
  limit:5
}).then(res=>{
  if (res.code===200){
    tableData.value = res.result.records
  }
})


//点击编辑按钮
const UpdateRole = (row:{}) =>{
  visible.value = true;
  rowData.value = row;
}

//分配权限按钮
const SaveRole = (row:{}) =>{
  Savevisible.value = true;
  TreeData.value = row;
  console.log("TreeData.value")
  console.log(TreeData.value)
}



//隐藏Dialog
const closeDialog = (r?:'reload')=>{//问号是可选属性
  visible.value = false;
  rowData.value = {};//清空编辑框
  if (r==='reload'){
    getRole({
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

//隐藏权限分配Dialog
const closeSaveDialog = (r?:'reload')=>{//问号是可选属性
  Savevisible.value = false;
  /*if (r==='reload'){
    getRole({
      name:'',
      Page:5,
      limit:5
    }).then(res=>{
      if (res.code===200){
        tableData.value = res.result.records
      }
    })
  }*/
}
/*watch(
    ()=>visible.value,(newVal,oldVal)=>{
      console.log('new',newVal)
      console.log('old',oldVal)
    }
)*/
</script>

<style scoped>

</style>