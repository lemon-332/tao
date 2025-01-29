<template>
  <div class="cart-container">
    <div v-if="$route.path === '/cart'">222</div>
    <RouterView v-else></RouterView>
  </div>
</template>
<script lang="ts">
import { Component, Vue } from 'vue-facing-decorator'

@Component({
  name: 'Cart'
})
export default class Cart extends Vue {}
</script>

<style lang="scss" scoped></style>
