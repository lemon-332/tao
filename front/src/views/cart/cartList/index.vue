<template>
  <el-row>
    <el-col :span="20">
      <div class="search-form">
        <el-form :inline="true" :model="searchForm" class="flex-align-center">
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
      <!-- <el-table-column prop="godImage" label="商品快照">
        <template #default="{ row }">
          <img class="god-img" :src="getGodImage(row)" alt="" srcset="" />
        </template>
      </el-table-column> -->
      <el-table-column prop="sellerName" label="卖家名称" />
      <el-table-column prop="godName" label="商品名称" />
      <el-table-column prop="cartCount" label="购买数量">
        <template #default="{ row }">
          <el-input-number
            v-model="row.cartCount"
            :min="1"
            :max="row.godCount"
            @change="handleCountChange(row)"
          />
        </template>
      </el-table-column>
      <el-table-column prop="godPrice" label="商品价格">
        <template #default="{ row }">¥{{ row.godPrice }}</template>
      </el-table-column>
      <el-table-column prop="status" label="状态">
        <template #default="scope">
          <el-tag
            :type="scope.row.status === 1 ? 'success' : 'warning'"
            disable-transitions
          >
            {{ scope.row.status === 1 ? '有货' : '缺货' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="createTime" label="加入时间" width="180" />
      <el-table-column fixed="right" label="操作" min-width="120">
        <template #default="{ row }">
          <el-popconfirm
            title="你确定要删除这个商品吗?"
            @confirm="handleDelete(row)"
          >
            <template #reference>
              <el-button link type="danger" size="small">删除</el-button>
            </template>
          </el-popconfirm>
          <el-button link type="primary" size="small" @click="handleBuy(row)">
            购买
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-facing-decorator'
import { ElMessage } from 'element-plus'
import { cartList, cartDelete, cartUpdate } from '@/api/cart'
import { sellerInfo } from '@/api/seller'

@Component({
  name: 'CartList'
})
export default class CartList extends Vue {
  private searchForm = {
    startTime: ''
  }

  private tableData: any[] = []

  private getGodImage(row: any) {
    return `/api/god/godDownload/?fileName=${row.godImg.split(',')[0]}`
  }

  private formatDate(date: Date) {
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

  private async fetchList(params: { startTimeStart: string }) {
    try {
      const res = await cartList(params)
      if (res.code === 200) {
        this.tableData = res.data
      } else {
        ElMessage.error(res.info || '获取购物车列表失败')
      }
    } catch (error) {
      ElMessage.error('获取购物车列表失败')
    }
  }

  private async search() {
    const startTime = this.searchForm.startTime
      ? this.formatDate(new Date(this.searchForm.startTime))
      : ''

    await this.fetchList({
      startTimeStart: startTime
    })
  }

  private async handleCountChange(row: any) {
    try {
      const res = await cartUpdate({
        cartId: row.cartId,
        cartCount: row.cartCount
      })
      if (res.code !== 200) {
        ElMessage.error(res.info || '更新数量失败')
      }
    } catch (error) {
      ElMessage.error('更新数量失败')
    }
  }

  private async handleDelete(row: any) {
    try {
      const res = await cartDelete({ cartId: row.cartId })
      if (res.code === 200) {
        ElMessage.success('删除成功')
        this.search()
      } else {
        ElMessage.error(res.info || '删除失败')
      }
    } catch (error) {
      ElMessage.error('删除失败')
    }
  }

  private handleBuy(row: any) {
    // TODO: 实现购买功能
    ElMessage.info('购买功能开发中')
  }

  created() {
    this.search()
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

.god-img {
  width: 100px;
  height: auto;
}
</style>
