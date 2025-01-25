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
  <AddUserDialog v-model:visible="visible" />
</template>

<script lang="ts">
import { Component, Vue } from 'vue-facing-decorator'
import AddUserDialog from './components/AddUserDialog.vue'
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

  private addUser() {
    this.visible = true
  }

  private search() {
    console.log(this.searchForm.startTime)
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
