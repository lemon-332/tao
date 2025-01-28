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
      <el-table-column prop="startTime" label="上架时间" width="180" />
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
import { godList, godDelete, godAdd, godUpload } from '@/api/god'
import { sellerInfo } from '@/api/seller'

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

  private async fetchSellerName(sellerId: number): Promise<string> {
    try {
      const sellerRes = await sellerInfo({ sellerId })
      return sellerRes.code === 200 ? sellerRes.data.sellerName : '未知'
    } catch (error) {
      console.error('获取卖家信息失败:', error)
      return '未知'
    }
  }

  private async processGodList(list: any[]): Promise<any[]> {
    try {
      return await Promise.all(
        list.map(async (item) => ({
          ...item,
          sellerName: await this.fetchSellerName(item.sellerId)
        }))
      )
    } catch (error) {
      console.error('处理商品列表失败:', error)
      return list.map((item) => ({ ...item, sellerName: '未知' }))
    }
  }

  private async fetchList(params: {
    godNameFuzzy: string
    startTimeStart: string
  }) {
    try {
      const res = await godList(params)
      if (res.code === 200) {
        this.tableData = await this.processGodList(res.data.list)
      } else {
        ElMessage.error(res.info || '获取列表失败')
      }
    } catch (error) {
      console.error('获取列表失败:', error)
      ElMessage.error('获取列表失败')
    }
  }

  private async search() {
    let startTimeStart = ''
    if (this.searchForm.startTime) {
      const date = new Date(this.searchForm.startTime)
      startTimeStart = this.formatDate(date)
    }

    await this.fetchList({
      godNameFuzzy: this.searchForm.godName,
      startTimeStart
    })
  }

  private async getList() {
    await this.fetchList({
      godNameFuzzy: this.searchForm.godName,
      startTimeStart: ''
    })
  }

  private async handleDelete(row) {
    const res = await godDelete({ godId: row.godId })
    if (res.code === 200) {
      ElMessage.success('删除成功')
      this.getList()
    }
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
