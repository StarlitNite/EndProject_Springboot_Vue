<template>
  <el-dialog :model-value="visible" title="菜单权限分配" :before-close="close">
    <el-tree
        ref="treeRef"
        :data="menuTree"
        show-checkbox
        default-expand-all
        node-key="id"
        highlight-current
        :props="{
          children:'child',
          label:'title'
        }"
    />
    <template #footer>
      <span class="dialog-footer">
        <el-button  @click="close">取消</el-button>
        <el-button type="primary" @click="modify">确定</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import {reactive, toRefs, ref, defineProps, defineEmits, watch} from 'vue'
import {getAllMenu, SaveRole} from '../request/api'

interface menuObj{
  pid:number
  id:number
  title:string
  child?:menuObj[]
  path:string
}

interface menus{
  [key: number]:menuObj
}

const props = defineProps<{
  visible:boolean;
  tree:Role
}>()

const state = reactive <{
  formLabelWidth:string,
  tree:Role,
  menuTree:menus,
  updateTree:roleMenu
}> ({
  formLabelWidth:'120px',
  tree:{},
  menuTree:{},
  updateTree:{}
})
const treeRef = ref()
const {formLabelWidth,menuTree,updateTree,tree} = toRefs(state)
watch(()=> props.tree,()=>{
  tree.value = {...props.tree}
  console.log("watch")
  console.log(tree.value.id)
})

getAllMenu().then(res=>{

  if (res.code == 200){
    menuTree.value = res.result
    /*console.log("newTree.value")
    console.log(menuTree.value);
    console.log("newTree.value after")*/
  }
})


const emit = defineEmits<{
  (event:'close',r?: 'reload'):void
}>();
//点击关闭
const close = (r?: 'reload') =>{
  //传到父组件
  emit('close',r);
}

//点击确定
const modify = ()=>{

  updateTree.value.rid = tree.value.id
  /*console.log("updateTree.value.rid")
  console.log(updateTree.value.rid)
  console.log("tree.value.rid")
  console.log(tree.value.id)*/
  updateTree.value.mid = treeRef.value!.getCheckedKeys(false)
  /*console.log("updateTree.value.mid")
  console.log(updateTree.value)*/
  SaveRole(updateTree.value).then(res =>{
    if (res.code === 200){
      console.log("--------------")
      close('reload');
    }
  })

}

</script>

<style lang="less" scoped>

</style>