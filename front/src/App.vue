<template>
  <el-config-provider :locale="locale">
    <router-view></router-view>
  </el-config-provider>
</template>
<script lang="ts">
import { Component, Vue } from 'vue-facing-decorator'
import zhCn from 'element-plus/es/locale/lang/zh-cn'
import { debounce } from 'lodash'

/**
 * ResizeObserver loop completed with undelivered notifications.
 * 解决ResizeObserver loop completed with undelivered notifications.
 */
const _ = (window as any).ResizeObserver
;(window as any).ResizeObserver = class ResizeObserver extends _ {
  constructor(callback: (...args: any[]) => void) {
    callback = debounce(callback, 100)
    super(callback)
  }
}

@Component({
  name: 'App'
})
export default class App extends Vue {
  private locale = zhCn
}
</script>

<style>
#app {
  width: 100%;
  height: 100%;
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  user-select: none;
}
* {
  margin: 0;
  padding: 0;
}
body,
html {
  height: 100%;
}

a {
  text-decoration: none;
  color: #000;
}

.el-form-item {
  margin-right: 8px !important;
  /* margin-bottom: 0px; */
}
</style>
