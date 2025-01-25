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
              <el-dropdown-item command="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
    </el-card>
  </div>
</template>
<script lang="ts">
import { Component, Vue, Watch } from 'vue-facing-decorator'
import { ElNotification as notify } from 'element-plus'
import { useRoute } from 'vue-router'
import { useCookies } from 'vue3-cookies'

@Component
export default class Header extends Vue {
  private router = useRoute()

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
  onBack = () => {
    notify('Back')
  }

  private handleCommand(command: string) {
    if (command === 'logout') {
      const { cookies } = useCookies()
      cookies.remove('userInfo')
      this.$router.push('/login')
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
