<template>
  <div class="god-container">
    <div v-if="$route.path === '/god'">222</div>
    <RouterView v-else></RouterView>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-facing-decorator'
@Component({
  components: {}
})
export default class ActivityLog extends Vue {}
</script>
<style lang="scss" scoped></style>
