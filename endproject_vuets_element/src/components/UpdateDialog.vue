<template>
  <el-dialog :model-value="visible" title="Shipping address">
    <el-form :model="form" :label-width="formLabelWidth" :before-close="close">
      <el-form-item label="Promotion name" :label-width="formLabelWidth">
        <el-input v-model="form.name" autocomplete="off" />
      </el-form-item>
      <el-form-item label="Zones" >
        <el-select v-model="form.name" placeholder="Please select a zone">
          <el-option label="Zone No.1" value="shanghai" />
          <el-option label="Zone No.2" value="beijing" />
        </el-select>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button  @click="close">Cancel</el-button>
        <el-button type="primary" @click="close">
          Confirm
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import {reactive, toRefs, ref, defineProps, watch, defineEmits} from 'vue'
const props = defineProps<{
  visible:boolean;
  form:{name:string}
}>()
const state = reactive({
  formLabelWidth:'120px'
})
const { formLabelWidth } = toRefs(state)

/*watch(
    ()=>props.visible,(newVal,oldVal)=>{
      console.log('new',newVal)
      console.log('old',oldVal)
    }
)*/
const emit = defineEmits<{
  (event:'close'):void
}>();
//点击关闭
const close = () =>{
  //传到父组件
  emit('close');
}
</script>

<style lang="less" scoped>

</style>