<template>
  <div class="header-wrapper">
    <el-card>
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/' }">homepage</el-breadcrumb-item>
        <el-breadcrumb-item>
          <a href="/">promotion management</a>
        </el-breadcrumb-item>
        <el-breadcrumb-item>promotion list</el-breadcrumb-item>
        <el-breadcrumb-item>promotion detail</el-breadcrumb-item>
      </el-breadcrumb>
    </el-card>
  </div>
</template>
<script lang="ts">
import { Options, Vue } from 'vue-class-component'
import { ElNotification as notify } from 'element-plus'
import { useRoute } from 'vue-router'

@Options({
  components: {}
})
export default class Header extends Vue {
  router = useRoute()
  onBack = () => {
    notify('Back')
  }
}
</script>

<style lang="scss" scoped>
.header-wrapper {
  height: 50px;
}
</style>
