<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png" />
    <el-calendar v-model="value" />
    <div>{{ haha }}</div>
    <HelloWorld msg="Welcome to Your Vue.js + TypeScript App" />
  </div>
</template>

<script lang="ts">
import { Options, Vue } from 'vue-class-component'
import HelloWorld from '@/components/HelloWorld.vue' // @ is an alias to /src
import { UserModule } from '@/store/modules/user'

@Options({
  components: {
    HelloWorld
  }
})
export default class HomeView extends Vue {
  private value = new Date()
  private haha = UserModule.currentCount
}
</script>
