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
      <el-table-column align="center" label="商品详情" width="800">
        <template #default="{ row }">
          <div class="order-info">
            订单号: {{ row.cartId }} &nbsp;&nbsp;&nbsp;&nbsp; 下单时间:
            {{ formatDate(row.startTime) }}
          </div>
          <el-table :data="row.godInfoDtos" class="inner-table">
            <el-table-column prop="godImg" label="商品快照">
              <template #default="{ row: godInfoDto }">
                <img class="god-img" :src="getGodImage(godInfoDto)" />
              </template>
            </el-table-column>
            <el-table-column label="商品名称" prop="godName" />
            <el-table-column label="商品价格" prop="godPrice" />
            <el-table-column label="购买数量" prop="godCount" />
          </el-table>
        </template>
      </el-table-column>
      <el-table-column prop="userName" label="买家" />
      <el-table-column prop="totalPrice" label="订单总价" />
      <el-table-column prop="cartStatus" label="状态">
        <template #default="{ row }">
          <el-tag :type="getStatusType(row.cartStatus)" disable-transitions>
            {{ getStatusText(row.cartStatus) }}
          </el-tag>
        </template>
      </el-table-column>
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

@Component({
  name: 'CartList'
})
export default class CartList extends Vue {
  private searchForm = {
    startTime: ''
  }

  private tableData: any[] = []

  private formatDate(date: Date) {
    if (typeof date === 'string') {
      date = new Date(date)
    }
    const year = date.getFullYear()
    const month = date.getMonth() + 1
    const day = date.getDate()
    return `${year}-${month}-${day}`
  }

  private getGodImage(row) {
    return `/api/god/godDownload/?fileName=${row.godImg.split(',')[0]}`
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

  private getStatusType(status: number): string {
    const statusMap: Record<number, string> = {
      0: 'info', // 待发货
      1: 'warning', // 待付款
      2: 'danger', // 已取消
      3: 'success' // 交易完成
    }
    return statusMap[status] || 'info'
  }

  private getStatusText(status: number): string {
    const statusMap: Record<number, string> = {
      0: '待发货',
      1: '待付款',
      2: '已取消',
      3: '交易完成'
    }
    return statusMap[status] || '未知状态'
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

.order-info {
  padding: 8px 0;
  font-size: 14px;
  color: #606266;
  border-bottom: 1px solid #ebeef5;
  margin-bottom: 8px;
}
</style>
