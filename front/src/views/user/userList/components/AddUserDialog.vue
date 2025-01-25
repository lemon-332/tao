<template>
  <el-dialog
    title="添加用户"
    width="500px"
    :model-value="visible"
    @update:model-value="$emit('update:visible', $event)"
    @close="$emit('update:visible', false)"
  >
    <el-form :model="form" label-width="120px">
      <el-form-item label="用户名">
        <el-input v-model="form.userName" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="$emit('update:visible', false)">取消</el-button>
      <el-button type="primary" @click="addUser">确定</el-button>
    </template>
  </el-dialog>
</template>

<script lang="ts">
import { Component, Vue, Prop } from 'vue-facing-decorator'

@Component({
  name: 'AddUserDialog',
  components: {},
  emits: ['update:visible']
})
export default class AddUserDialog extends Vue {
  @Prop({
    type: Boolean,
    default: false
  })
  private visible!: boolean

  private form = {
    userName: ''
  }

  private addUser() {
    console.log(this.form)
    this.$emit('update:visible', false)
  }
}
</script>
<style lang="scss" scoped></style>
