<template>
  <el-row>
    <el-col :span="24">
      <div class="search-form">
        <el-form :inline="true" :model="searchForm" class="flex-align-center">
          <el-form-item>
            <el-input
              v-model="searchForm.sellerName"
              placeholder="商家名称"
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
  </el-row>
  <div class="table-container mt-4">
    <el-table :data="tableData" stripe border style="width: 100%">
      <el-table-column prop="sellerName" label="商家名称" />
      <el-table-column prop="sellerAdress" label="地址" />
      <el-table-column prop="godList" label="旗下商品">
        <template #default="{ row }">
          <el-tooltip placement="bottom" effect="light">
            <template #content>
              <div v-if="row.godList && row.godList.length > 0">
                <el-table :data="row.godList" stripe style="width: 100%">
                  <el-table-column prop="godName" label="商品名称" />
                  <el-table-column prop="godCount" label="商品数量" />
                  <el-table-column
                    prop="godStar"
                    width="100"
                    label="商品点赞数"
                  />
                </el-table>
              </div>
              <span v-else>暂无商品数据</span>
            </template>
            <el-button>商品列表 ({{ row.godList?.length || 0 }})</el-button>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column prop="sellerStar" label="商家星级">
        <template #default="{ row }">
          <el-rate v-model="row.sellerStar" />
        </template>
      </el-table-column>
      <el-table-column prop="startTime" label="创建时间" width="180" />
      <el-table-column fixed="right" label="操作" width="80">
        <template #default="{ row }">
          <el-popconfirm
            title="你确定要删除这条数据吗?"
            @confirm="handleDelete(row)"
          >
            <template #reference>
              <el-button link type="danger" size="small">删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-facing-decorator'
import { ElMessage } from 'element-plus'
import { sellerList, sellerDelete } from '@/api/seller'
import { godListByIds } from '@/api/god'

@Component({
  name: 'SellerList'
})
export default class SellerList extends Vue {
  private searchForm = {
    sellerName: '',
    startTime: ''
  }

  private tableData = []

  private formatDate(date) {
    const year = date.getFullYear()
    const month = date.getMonth() + 1
    const day = date.getDate()
    return `${year}-${month}-${day}`
  }

  private async fetchSellerList(params: {
    sellerNameFuzzy: string
    startTimeStart: string
  }) {
    try {
      const res = await sellerList(params)
      if (res.code === 200) {
        this.tableData = res.data.list
        for (const item of this.tableData) {
          if (item.godIds) {
            const godRes = await godListByIds({ godIds: item.godIds })
            if (godRes.code === 200) {
              item.godList = godRes.data
            }
          } else {
            item.godList = []
          }
        }
      } else {
        ElMessage.error(res.info || '获取商家列表失败')
      }
    } catch (error) {
      ElMessage.error('获取商家列表失败')
    }
  }

  private async search() {
    const startTime = this.searchForm.startTime
      ? this.formatDate(new Date(this.searchForm.startTime))
      : ''

    await this.fetchSellerList({
      sellerNameFuzzy: this.searchForm.sellerName,
      startTimeStart: startTime
    })
  }

  private async getList() {
    await this.fetchSellerList({
      sellerNameFuzzy: this.searchForm.sellerName,
      startTimeStart: ''
    })
  }

  private async handleDelete(row) {
    const res = await sellerDelete({ sellerId: row.sellerId })
    if (res.code === 200) {
      ElMessage.success('删除成功')
      this.getList()
    } else {
      ElMessage.error(res.info || '删除失败')
    }
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
