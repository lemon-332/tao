<template>
  <el-row>
    <el-col :span="20">
      <div class="search-form">
        <el-form :inline="true" :model="searchForm" class="flex-align-center">
          <el-form-item>
            <el-input
              v-model="searchForm.userName"
              placeholder="用户名"
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
      <el-button type="primary" size="default" @click="addUser">
        添加用户
      </el-button>
    </el-col>
  </el-row>
  <div class="table-container mt-4">
    <el-table :data="tableData" stripe border style="width: 100%">
      <el-table-column prop="userName" label="账号" />
      <el-table-column prop="phone" label="电话" />
      <el-table-column prop="displayName" label="昵称" />
      <el-table-column prop="password" label="密码" />
      <el-table-column prop="status" label="状态">
        <template #default="scope">
          <el-tag
            :type="scope.row.status === 1 ? 'primary' : 'warning'"
            disable-transitions
          >
            {{ scope.row.status === 1 ? '启用' : '禁用' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="role" label="角色">
        <template #default="scope">
          <el-tag :type="getUserRoleTag(scope.row.role)" disable-transitions>
            {{ getUserRole(scope.row.role) }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="startTime" label="创建时间" width="180" />
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
  <AddUserDialog
    v-model:visible="visible"
    :type="userDiaType"
    :edit-user="editUser"
    @refresh="getList"
  />
</template>

<script lang="ts">
import { Component, Vue } from 'vue-facing-decorator'
import AddUserDialog from './components/AddUserDialog.vue'

import { ElMessage } from 'element-plus'

import { userList, userDelete } from '@/api/user'
@Component({
  components: {
    AddUserDialog
  }
})
export default class UserList extends Vue {
  private searchForm = {
    userName: '',
    startTime: ''
  }
  private visible = false

  private editUser = {}

  private userDiaType = 'add'

  private tableData = []

  private getUserRole(role: number) {
    if (role === 1) return '用户'
    else if (role === 2) return '管理员'
    else return '会员'
  }

  private getUserRoleTag(role: number) {
    if (role === 1) return 'primary'
    else if (role === 2) return 'success'
    else return 'warning'
  }

  private addUser() {
    this.userDiaType = 'add'
    this.visible = true
  }

  private formatDate(date) {
    const year = date.getFullYear()
    const month = date.getMonth() + 1 // 月份从 0 开始
    const day = date.getDate()

    return `${year}-${month}-${day}`
  }

  private async search() {
    if (this.searchForm.startTime !== '') {
      const date = new Date(this.searchForm.startTime)
      this.searchForm.startTime = this.formatDate(date)
    }

    const res = await userList({
      userNameFuzzy: this.searchForm.userName,
      startTimeStart: this.searchForm.startTime
    })
    if (res.code === 200) {
      this.tableData = res.data.list
    }
  }

  private async getList() {
    console.log('22')

    const res = await userList({
      userNameFuzzy: this.searchForm.userName,
      startTimeStart: ''
    })
    if (res.code === 200) {
      this.tableData = res.data.list
    }
  }

  private async handleDelete(row) {
    const res = await userDelete({ userId: row.userId })
    if (res.code === 200) {
      ElMessage.success('删除成功')
      this.getList()
    }
  }

  private handleEdit(row) {
    this.editUser = row
    this.userDiaType = 'edit'
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
