<template>
  <div class="seller-container">
    <div v-if="$route.path === '/seller'">222</div>
    <RouterView v-else></RouterView>
  </div>
</template>
<script lang="ts">
import { Component, Vue } from 'vue-facing-decorator'

@Component({
  name: 'Seller'
})
export default class Seller extends Vue {}
</script>

<style lang="scss" scoped></style>
