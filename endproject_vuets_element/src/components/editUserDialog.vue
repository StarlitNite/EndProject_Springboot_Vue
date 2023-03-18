<template>
  <el-dialog :model-value="visible" title="用户编辑" :before-close="close">
    <el-form :model="newForm" :label-width="formLabelWidth" >
      <el-form-item label="姓名" :label-width="formLabelWidth">
        <el-input v-model="newForm.username" autocomplete="off" />
      </el-form-item>
      <el-form-item label="宿舍号"  :label-width="formLabelWidth">
        <el-input v-model="newForm.remark" disabled autocomplete="off" />
      </el-form-item>
      <el-form-item label="权限"  :label-width="formLabelWidth">
        <el-input v-model="newForm.remark" disabled autocomplete="off" />
      </el-form-item>
      <el-form-item label="班级"  :label-width="formLabelWidth">
        <el-input v-model="newForm.remark" disabled autocomplete="off" />
      </el-form-item>
      <el-form-item label="院系"  :label-width="formLabelWidth">
        <el-input v-model="newForm.remark" disabled autocomplete="off" />
      </el-form-item>
      <el-form-item label="导员"  :label-width="formLabelWidth">
        <el-input v-model="newForm.remark" disabled autocomplete="off" />
      </el-form-item>
      <el-form-item label="状态"  :label-width="formLabelWidth">
        <el-input v-model="newForm.remark" disabled autocomplete="off" />
      </el-form-item>

    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button  @click="close">取消</el-button>
        <el-button type="primary" @click="modify">确定</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import {reactive, toRefs, ref} from 'vue'

</script>

<style lang="less" scoped>

</style>