<template>
  <el-dialog :model-value="visible" title="审批" :before-close="close">
    <el-form :model="newForm" :label-width="formLabelWidth" >
      <el-form-item label="角色名称" :label-width="formLabelWidth">
        <el-input v-model="newForm.name" autocomplete="off" />
      </el-form-item>
      <el-form-item label="权限"  :label-width="formLabelWidth">
        <el-input v-model="newForm.remark" disabled autocomplete="off" />
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button type="primary" @click="modify">批准</el-button>
        <el-button type="primary" @click="modify">驳回</el-button>
        <el-button  @click="close">取消</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import {reactive, toRefs, ref} from 'vue'
</script>

<style lang="less" scoped>

</style>