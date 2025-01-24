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
import { Options, Vue } from 'vue-class-component'

import Header from '@/components/Header.vue'
import Menu from '@/components/Menu.vue'

@Options({
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
