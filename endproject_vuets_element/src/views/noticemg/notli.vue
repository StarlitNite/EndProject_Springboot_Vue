<template>
  <el-button @click="addNews">创建新闻</el-button>
  <el-table :data="tableData" border style="width:100%" v-if="tableData.nstu!=1">
    <el-table-column prop="id" label="编号" /><!-- width="64"    -->
    <el-table-column prop="title" label="标题"  /><!-- width="129"   -->
    <el-table-column prop="content" label="正文内容" /><!--    -->
    <el-table-column label="创建日期"  ><!--width="82"    -->
      <template v-slot:default="scope">{{ formateDate(scope.row.create_time) }}</template>
    </el-table-column>
    <el-table-column prop="publishby" label="发布者"  /><!--   width="103" -->
    <el-table-column  label="操作"  ><!--    -->
      <template #default="{row}" >
        <el-button text @click="editNews(row)"> 修改 </el-button>
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
  <editNewsDialog :visible="editVisible" @close="closeDialog" :form="rowData" />
  <saveNewsDialog :visible="saveVisible" @close="saveCloseDialog"/>
</template>

<script lang="ts" setup>
import {reactive, toRefs, ref} from 'vue'
import {getAllNews, delCovidNews} from "../../request/api";
import Cookie from "js-cookie";

const state = reactive<{
  tableData:{}[],
  rowData:news,
  editVisible:boolean,
  saveVisible:boolean
}>({
  tableData:[],
  rowData:{},
  editVisible:false,
  saveVisible:false
})

const {tableData,rowData,editVisible,saveVisible } = toRefs(state)

getAllNews().then(res=>{
  if (res.code === 200){
    tableData.value = res.result
  }
})


const editNews =(row:{})=>{
  editVisible.value = true
  rowData.value = row;
}

const addNews =()=>{
  saveVisible.value = true
}



//隐藏Dialog
const closeDialog = (r?:'reload')=>{
  editVisible.value = false;
  rowData.value = {};//清空编辑框
  if (r==='reload'){
    getAllNews().then(res=>{
      if (res.code === 200){
        tableData.value = res.result
      }
    })
  }
}

//隐藏Dialog
const saveCloseDialog = (r?:'reload')=>{
  saveVisible.value = false;
  if (r==='reload'){
    getAllNews().then(res=>{
      if (res.code === 200){
        tableData.value = res.result
      }
    })
  }
}




//删除新闻
const confirmEvent = id => {
  console.log(id)
  delCovidNews(id).then(res =>{
    if (res.code===200){
      ElMessage({
        showClose: true,
        message: '操作成功',
        type: 'success'
      })
      getAllNews().then(res=>{
        if (res.code === 200){
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



const formateDate = (time: string|undefined) =>{
  if (!time) return '';
  const date = new Date(time);

  let month = addZero(date.getMonth()+1);
  let day = addZero(date.getDate());


  return `${month}-${day} `
}

const addZero = (num:number) =>{
  return num > 9 ? num:'0'+num
}

</script>

<style lang="less" scoped>

</style>