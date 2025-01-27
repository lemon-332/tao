<template>
  <el-row>
    <el-col :span="20">
      <div class="search-form">
        <el-form :inline="true" :model="searchForm" class="flex-align-center">
          <el-form-item>
            <el-input
              v-model="searchForm.godName"
              placeholder="商品名称"
              clearable
            />
          </el-form-item>
          <el-form-item>
            <el-date-picker
              v-model="searchForm.startTime"
              type="date"
              placeholder="请选择开始时间"
              clearable
            />
          </el-form-item>
        </el-form>
        <el-button type="primary" size="default" @click="search">
          查询
        </el-button>
      </div>
    </el-col>
    <el-col :span="4" class="flex-end-center">
      <el-button type="primary" size="default" @click="addGod">
        添加商品
      </el-button>
    </el-col>
  </el-row>
  <div class="table-container mt-4">
    <el-table :data="tableData" stripe border style="width: 100%">
      <el-table-column prop="godImage" label="商品快照" />
      <el-table-column prop="sellerName" label="卖家名称" />
      <el-table-column prop="godName" label="商品名称" />
      <el-table-column prop="godDesc" label="描述" />
      <el-table-column prop="godCount" label="商品数量" />
      <el-table-column prop="godBoughtCount" label="商品已卖出数量" />
      <el-table-column prop="godStar" label="商品点赞数" />
      <el-table-column fixed="right" label="操作" min-width="120">
        <template #default="{ row }">
          <el-popconfirm
            title="你确定要删除这条数据吗?"
            @confirm="handleDelete(row)"
          >
            <template #reference>
              <el-button link type="danger" size="small">删除</el-button>
            </template>
          </el-popconfirm>
          <el-button link type="primary" size="small" @click="handleEdit(row)">
            编辑
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
  <GodDialog
    v-model:visible="visible"
    :type="godDiaType"
    :edit-god="editGod"
    @refresh="getList"
  />
</template>

<script lang="ts">
import { Component, Vue } from 'vue-facing-decorator'
import GodDialog from './components/GodDialog.vue'
import { ElMessage } from 'element-plus'
// import { godList, godDelete } from '@/api/god'

@Component({
  components: {
    GodDialog
  }
})
export default class GodList extends Vue {
  private searchForm = {
    godName: '',
    startTime: ''
  }
  private visible = false
  private editGod = {}
  private godDiaType = 'add'
  private tableData = []

  private addGod() {
    this.godDiaType = 'add'
    this.visible = true
  }

  private formatDate(date) {
    const year = date.getFullYear()
    const month = date.getMonth() + 1
    const day = date.getDate()
    return `${year}-${month}-${day}`
  }

  private async search() {
    if (this.searchForm.startTime !== '') {
      const date = new Date(this.searchForm.startTime)
      this.searchForm.startTime = this.formatDate(date)
    }

    // const res = await godList({
    //   godNameFuzzy: this.searchForm.godName,
    //   startTimeStart: this.searchForm.startTime
    // })
    // if (res.code === 200) {
    //   this.tableData = res.data.list
    // }
  }

  private async getList() {
    // const res = await godList({
    //   godNameFuzzy: this.searchForm.godName,
    //   startTimeStart: ''
    // })
    // if (res.code === 200) {
    //   this.tableData = res.data.list
    // }
  }

  private async handleDelete(row) {
    // const res = await godDelete({ godId: row.godId })
    // if (res.code === 200) {
    //   ElMessage.success('删除成功')
    //   this.getList()
    // }
  }

  private handleEdit(row) {
    this.editGod = row
    this.godDiaType = 'edit'
    this.visible = true
  }

  created() {
    this.getList()
  }
}
</script>

<style lang="scss" scoped>
.search-form {
  display: flex;
  align-items: center;

  .el-form-item {
    margin-bottom: 0px;
  }
}
</style>
