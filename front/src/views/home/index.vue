<template>
  <div class="main">
    <div class="menu">
      <Menu />
    </div>
    <div class="content-wrapper">
      <div class="header">
        <Header />
      </div>
      <div class="content">
        <RouterView></RouterView>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-facing-decorator'

import Header from '@/components/Header.vue'
import Menu from '@/components/Menu.vue'

@Component({
  components: {
    Header,
    Menu
  }
})
export default class Home extends Vue {}
</script>

<style lang="scss" scoped>
.main {
  display: flex;
  height: 100%;
  .menu {
    // width: 10%;
    width: 200px;
    height: 100%;
  }
  .content-wrapper {
    width: 100%;
    .content {
    }
    .header {
      margin: 10px 10px;
    }
  }
}
</style>
