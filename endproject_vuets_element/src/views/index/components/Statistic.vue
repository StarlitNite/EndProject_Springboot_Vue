<template>
  <el-row :gutter="16">
    <el-col :span="8">
      <div class="statistic-card">
        <el-statistic :value="9532747">
          <template #title>
            <div style="display: inline-flex; align-items: center">
              全国总计确诊数
              <el-tooltip
                  effect="dark"
                  content="截止当日8时各省市地区卫健委提供"
                  placement="top"
              >
                <el-icon style="margin-left: 4px" :size="12">
                  <Warning />
                </el-icon>
              </el-tooltip>
            </div>
          </template>
        </el-statistic>
        <div class="statistic-footer">
          <div class="footer-item">
            <span>较昨日相比</span>
            <span class="red">
              <el-icon>
                <CaretTop />
              </el-icon>
            </span>
          </div>
        </div>
      </div>
    </el-col>
    <el-col :span="8">
      <div class="statistic-card">
        <el-statistic :value="471394">
          <template #title>
            <div style="display: inline-flex; align-items: center">
              全国总计治愈数
              <el-tooltip
                  effect="dark"
                  content="截止当日8时各省市地区卫健委提供"
                  placement="top"
              >
                <el-icon style="margin-left: 4px" :size="12">
                  <Warning />
                </el-icon>
              </el-tooltip>
            </div>
          </template>
        </el-statistic>
        <div class="statistic-footer">
          <div class="footer-item">
            <span>较昨日相比</span>
            <span class="red">
              <el-icon>
                <CaretTop />
              </el-icon>
            </span>
          </div>
        </div>
      </div>
    </el-col>
    <el-col :span="8">
      <div class="statistic-card">
        <el-statistic :value="31585" title="New transactions today">
          <template #title>
            <div style="display: inline-flex; align-items: center">
              全国总计死亡数
              <el-tooltip
                  effect="dark"
                  content="截止当日8时各省市地区卫健委提供"
                  placement="top"
              >
                <el-icon style="margin-left: 4px" :size="12">
                  <Warning />
                </el-icon>
              </el-tooltip>
            </div>
          </template>
        </el-statistic>
        <div class="statistic-footer">
          <div class="footer-item">
            <span>较昨日相比</span>
            <span class="red">
              <el-icon>
                <CaretTop />
              </el-icon>
            </span>
          </div>
        </div>
      </div>
    </el-col>
  </el-row>
</template>

<script lang="ts" setup>
import {reactive, toRefs, ref, watch} from 'vue'
import { CaretTop, CaretBottom, Warning } from "@element-plus/icons-vue";
const props = defineProps<{
  data:{}[]
}>();
const state = reactive <{
  newForm:{}
}> ({
  newForm:[]
})

const {newForm} = toRefs(state)

const confirmeddata = newForm.id
console.log(confirmeddata)

watch(()=> props.data,()=>{
  /*console.log("props.form")
  console.log(props.data)*/
  newForm.value = props.data
  /*console.log("newForm.value")
  console.log(newForm.value)*/

})

</script>

<style lang="less" scoped>
:global(h2#card-usage .example .example-showcase) {
  background-color: var(--el-fill-color) !important;
}

.el-statistic {
  --el-statistic-content-font-size: 28px;
}

.statistic-card {
  height: 45%;
  text-align: center;
  padding: 20px;
  border-radius: 4px;
  background-color: var(--el-bg-color-overlay);
}

.statistic-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  flex-wrap: wrap;
  font-size: 12px;
  color: var(--el-text-color-regular);
  margin-top: 16px;
}

.statistic-footer .footer-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.statistic-footer .footer-item span:last-child {
  display: inline-flex;
  align-items: center;
  margin-left: 4px;
}
.green {
  color: var(--el-color-success);
}
.red {
  color: var(--el-color-error);
}
</style>