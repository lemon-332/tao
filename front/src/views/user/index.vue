<template>
  <div class="user-container">
    <div v-if="$route.path === '/user'">111</div>
    <RouterView v-else></RouterView>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-facing-decorator'
@Component({
  components: {}
})
export default class User extends Vue {}
</script>

<style lang="scss" scoped>
.user-container {
  padding: 8px 0px;
}
</style>
