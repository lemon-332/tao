<template>
  <el-config-provider :locale="locale">
    <router-view></router-view>
  </el-config-provider>
</template>
<script lang="ts">
import { Options, Vue } from 'vue-class-component'
import zhCn from 'element-plus/es/locale/lang/zh-cn'

@Options({
  name: 'App',
  components: {}
})
export default class HomeView extends Vue {
  private locale = zhCn
}
</script>
