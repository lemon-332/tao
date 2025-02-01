<template>
  <div class="register-container">
    <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
      <el-tab-pane label="注册" name="first">
        <div class="register-content">
          <div v-if="!showSuccess" class="register-form">
            <el-form :model="form" label-width="80px">
              <el-form-item label="商家名称">
                <el-input v-model="form.sellerName" />
              </el-form-item>
              <el-form-item label="商家地址">
                <el-cascader
                  v-model="sellerCodes"
                  size="large"
                  :options="options"
                  @change="handleAddressChange"
                ></el-cascader>
              </el-form-item>
              <el-form-item label="商家星级">
                <el-rate v-model="form.sellerStar" />
              </el-form-item>
            </el-form>
            <div class="register-btn">
              <el-button type="primary" size="default" @click="register">
                注册
              </el-button>
            </div>
          </div>
          <el-result
            v-else
            icon="success"
            title="注册成功"
            sub-title="请等待审核"
          />
        </div>
      </el-tab-pane>
      <el-tab-pane label="审核" name="second">
        <div class="table-container">
          <el-table :data="tableData" stripe border class="audit-table">
            <el-table-column prop="sellerName" label="商家名称" />
            <el-table-column prop="sellerAdress" label="地址" />
            <el-table-column prop="sellerStar" label="商家星级">
              <template #default="{ row }">
                <el-rate v-model="row.sellerStar" disabled />
              </template>
            </el-table-column>
            <el-table-column prop="sellerStatus" label="审核状态">
              <template #default="{ row }">
                <el-tag v-if="row.sellerStatus === 0" type="success">
                  通过
                </el-tag>
                <el-tag v-else type="warning">正在审核</el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="startTime" label="创建时间" width="180" />
          </el-table>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<script lang="ts">
import { Component, Vue } from 'vue-facing-decorator'
import { regionData, codeToText } from 'element-china-area-data'
import { SellerAdd, sellerRegisterList } from '@/api/seller'

@Component({
  name: 'SellerRegister'
})
export default class SellerRegister extends Vue {
  private form = {
    sellerName: '',
    sellerAdress: '',
    sellerStar: 0
  }
  private options = regionData

  private activeName = 'first'

  private sellerCodes = []

  private showSuccess = false

  private tableData = []

  private handleAddressChange(value: string[]) {
    this.form.sellerAdress = value.map((code) => codeToText[code]).join('')
  }

  private register() {
    SellerAdd(this.form).then((res) => {
      if (res.code === 200) {
        this.showSuccess = true
        this.getList()
      }
    })
  }

  private handleClick(tab, event) {
    // console.log(tab, event)
  }

  private async getList() {
    const res = await sellerRegisterList()
    if (res.code === 200) {
      this.tableData = res.data
    }
  }

  created() {
    this.getList()
  }
}
</script>

<style lang="scss" scoped>
.register-container {
  padding: 30px;
  padding-top: 10px;
  .register-content {
    width: 500px;
  }
  .el-result {
    padding-left: 0px;
  }
  .table-container {
    width: 100%;
  }
}
</style>
