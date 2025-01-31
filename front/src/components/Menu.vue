<template>
  <div class="menu-wrapper">
    <el-menu
      default-active="1"
      class="el-menu-vertical"
      active-text-color="#ffd04b"
      background-color="#545c64"
      text-color="#fff"
      @open="handleOpen"
      @close="handleClose"
    >
      <el-sub-menu
        v-for="(route, index) in routes"
        :key="route.name"
        :index="String(index + 1)"
      >
        <template #title>
          <component
            :is="iconComponent(String(route.name))"
            class="icon"
          ></component>
          <span>{{ route.meta.title }}</span>
        </template>
        <el-menu-item
          v-for="(item, itemIndex) in route.children"
          :key="itemIndex"
          :index="item.meta.index"
        >
          <el-icon>
            <component
              :is="iconComponent(String(item.name))"
              class="icon"
            ></component>
          </el-icon>
          <RouterLink :to="item.path">{{ item.meta.title }}</RouterLink>
        </el-menu-item>
      </el-sub-menu>
    </el-menu>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-facing-decorator'
import { RouterLink, useRouter, RouteRecordRaw } from 'vue-router'
import * as ElementIcons from '@element-plus/icons-vue'

// 定义图标映射接口
interface IconMapping {
  [key: string]: (typeof ElementIcons)[keyof typeof ElementIcons]
}

@Component({
  components: {
    ...ElementIcons,
    RouterLink
  }
})
export default class Menu extends Vue {
  private routes: RouteRecordRaw[] = []

  private handleOpen(key: string, keyPath: string[]): void {
    console.debug('Menu opened:', key, keyPath)
  }

  private handleClose(key: string, keyPath: string[]): void {
    console.debug('Menu closed:', key, keyPath)
  }

  private iconComponent(
    name: string
  ): (typeof ElementIcons)[keyof typeof ElementIcons] {
    const icons: IconMapping = {
      user: ElementIcons.UserFilled,
      god: ElementIcons.Goods,
      seller: ElementIcons.ShoppingCartFull,
      userList: ElementIcons.Stamp,
      activityLog: ElementIcons.BellFilled,
      godList: ElementIcons.Sell,
      godReport: ElementIcons.PieChart,
      sellerList: ElementIcons.List,
      cartList: ElementIcons.SuitcaseLine,
      sellerRegister: ElementIcons.OfficeBuilding
    }

    return icons[name] || ElementIcons.Document // 提供一个默认图标
  }

  created(): void {
    const router = useRouter()
    // 确保路由配置存在
    if (router.options.routes?.[0]?.children) {
      this.routes = router.options.routes[0].children
    }
  }
}
</script>
<style lang="scss" scoped>
.menu-wrapper {
  height: 100%;

  .el-menu-vertical {
    height: 100%;
    a {
      color: #fff;
    }
  }
}
.icon {
  width: 25px;
  height: 25px;
}
</style>
