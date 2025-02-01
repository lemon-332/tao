<template>
  <div class="header-wrapper">
    <el-card>
      <div class="header-content">
        <el-breadcrumb separator="/">
          <el-breadcrumb-item
            v-for="item in breadcrumbItems"
            :key="item.name"
            :to="{ path: item.path }"
          >
            {{ item.name }}
          </el-breadcrumb-item>
        </el-breadcrumb>

        <el-dropdown @command="handleCommand">
          <el-avatar :size="32" :src="userAvatar" />
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item command="userInfo">用户信息</el-dropdown-item>
              <el-dropdown-item v-if="userRole === 2" command="registerSeller">
                <el-badge
                  :value="tableData.length"
                  @click="dialogTableVisible = true"
                >
                  审核注册商家
                </el-badge>
              </el-dropdown-item>
              <el-dropdown-item command="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
    </el-card>
    <el-dialog v-model="dialogTableVisible" title="审核注册商家" width="800">
      <el-table :data="tableData">
        <el-table-column prop="sellerName" label="商家名称" width="150" />
        <el-table-column prop="sellerAdress" label="商家地址" width="300" />
        <el-table-column prop="sellerStar" label="商家星级">
          <template #default="{ row }">
            <el-rate v-model="row.sellerStar" disabled />
          </template>
        </el-table-column>
        <el-table-column align="center" label="操作" min-width="120">
          <template #default="{ row }">
            <el-popconfirm
              title="确定通过该商家的注册申请吗?"
              @confirm="handleAgree(row)"
            >
              <template #reference>
                <el-button link type="primary" size="small">同意</el-button>
              </template>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>
  </div>
</template>
<script lang="ts">
import { Component, Vue, Watch } from 'vue-facing-decorator'
import { useRoute } from 'vue-router'
import { useCookies } from 'vue3-cookies'
import { ElMessage, ElMessageBox } from 'element-plus'
import { h } from 'vue'

import { sellerAgree, sellerNeedAgree } from '@/api/seller'

@Component
export default class Header extends Vue {
  private router = useRoute()

  private userRole = 1

  private dialogTableVisible = false

  private tableData = []

  private breadcrumbItems: any[] = []
  private userAvatar =
    'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png' // 默认头像

  @Watch('router', {
    immediate: true,
    deep: true
  })
  private routerChange() {
    this.breadcrumbItems = []
    if (this.router.path === '/') {
      this.breadcrumbItems.push({
        name: 'home',
        path: '/'
      })
      return
    }

    const pathSegments = this.router.path.split('/').filter(Boolean)
    let currentPath = ''

    this.breadcrumbItems.push({
      name: 'home',
      path: '/'
    })

    pathSegments.forEach((segment) => {
      currentPath += `/${segment}`
      this.breadcrumbItems.push({
        name: segment,
        path: currentPath
      })
    })
  }

  created() {
    const { cookies } = useCookies()
    this.userRole = (cookies.get('userInfo') as any).role
    if (this.userRole === 2) {
      sellerNeedAgree().then((res) => {
        this.tableData = res.data
      })
    }
  }

  private handleAgree(row) {
    sellerAgree({ sellerId: row.sellerId }).then((res) => {
      ElMessage.success('通过审核')
      sellerNeedAgree().then((res) => {
        this.tableData = res.data
      })
    })
  }

  private handleCommand(command: string) {
    const { cookies } = useCookies()
    if (command === 'logout') {
      cookies.remove('userInfo')
      this.$router.push('/login')
    } else if (command === 'userInfo') {
      const userInfo = cookies.get('userInfo') as any
      ElMessageBox({
        title: '个人信息',
        message: h('div', null, [
          h('h3', null, '用户名：' + userInfo.userName),
          h('h3', { style: 'color: teal' }, '电话：' + userInfo.phone),
          h(
            'h3',
            { style: 'color: green' },
            '角色：' + this.getRoleName(userInfo.role)
          )
        ]),
        confirmButtonText: '确认'
      }).catch(() => {})
    }
  }

  private getRoleName(role: number): string {
    switch (role) {
      case 1:
        return '普通用户'
      case 2:
        return '管理员'
      default:
        return '会员'
    }
  }
}
</script>

<style lang="scss" scoped>
.header-wrapper {
  height: 50px;

  .header-content {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
}
</style>
