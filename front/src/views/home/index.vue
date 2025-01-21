<template>
  <el-container>
    <el-aside width="200px">
      <router-link to="/about">about</router-link>
      <el-button type="primary" size="default" @click="haha">haha</el-button>
    </el-aside>
    <el-container>
      <el-header>Header</el-header>
      <el-main>
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'

@Options({
  components: {}
})
export default class HomeView extends Vue {
  private value = new Date()

  private haha() {
    this.$router.push('/about')
  }
}
</script>
