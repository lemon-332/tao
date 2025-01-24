<template>
  <div class="menu-wrapper">
    <el-menu
      default-active="1"
      class="el-menu-vertical"
      @open="handleOpen"
      @close="handleClose"
    >
      <el-sub-menu
        v-for="(route, index) in routes"
        :key="index"
        :index="String(index + 1)"
      >
        <template #title>
          <component :is="iconComponent(route.name)" class="icon"></component>
          <span>{{ route.meta.title }}</span>
        </template>
        <el-menu-item
          v-for="(item, itemIndex) in route.children"
          :key="itemIndex"
          :index="item.meta.index"
        >
          <el-icon>
            <component :is="iconComponent(item.name)" class="icon"></component>
          </el-icon>
          <RouterLink :to="item.path">{{ item.meta.title }}</RouterLink>
        </el-menu-item>
      </el-sub-menu>
    </el-menu>
  </div>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'
import { RouterLink, useRouter } from 'vue-router'
import {
  UserFilled,
  ShoppingCartFull,
  Goods,
  Stamp,
  BellFilled,
  List,
  PieChart,
  OfficeBuilding,
  SuitcaseLine,
  Sell
} from '@element-plus/icons-vue'

@Options({
  components: {
    UserFilled,
    ShoppingCartFull,
    Goods,
    Stamp,
    BellFilled,
    List,
    PieChart,
    OfficeBuilding,
    SuitcaseLine,
    Sell
  }
})
export default class Login extends Vue {
  private routes = []

  private handleOpen = (key: string, keyPath: string[]) => {
    // console.log(key, keyPath)
  }
  private handleClose = (key: string, keyPath: string[]) => {
    // console.log(key, keyPath)
  }

  private iconComponent(name) {
    const icons = {
      user: UserFilled,
      god: Goods,
      seller: ShoppingCartFull,
      userList: Stamp,
      activityLog: BellFilled,
      godList: Sell,
      godReport: PieChart,
      sellerList: List,
      cartList: SuitcaseLine,
      sellerRegister: OfficeBuilding
    }

    return icons[name]
  }

  created() {
    const router = useRouter()
    this.routes = router.options.routes[0].children
  }
}
</script>
<style lang="scss" scoped>
.menu-wrapper {
  height: 100%;
  // width: 100%;
  .el-menu-vertical {
    height: 100%;
    &:not(.el-menu--collapse) {
      width: 200px;
    }
  }
}
.icon {
  width: 25px;
  height: 25px;
}
</style>
