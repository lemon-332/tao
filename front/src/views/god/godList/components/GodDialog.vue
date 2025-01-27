<template>
  <el-dialog
    :title="type === 'add' ? '添加商品' : '编辑商品'"
    width="500px"
    :model-value="visible"
    @update:model-value="$emit('update:visible', $event)"
    @close="handleClose"
  >
    <el-form ref="formRef" :model="form" label-width="120px" :rules="rules">
      <el-form-item label="商品图片" prop="godImage">
        <el-input v-model="form.godImage" placeholder="请输入商品图片URL" />
      </el-form-item>
      <el-form-item label="商品名称" prop="godName">
        <el-input v-model="form.godName" placeholder="请输入商品名称" />
      </el-form-item>
      <el-form-item label="商品描述" prop="godDesc">
        <el-input
          v-model="form.godDesc"
          type="textarea"
          :rows="3"
          placeholder="请输入商品描述"
        />
      </el-form-item>
      <el-form-item label="商品数量" prop="godCount">
        <el-input-number
          v-model="form.godCount"
          :min="0"
          :precision="0"
          placeholder="请输入商品数量"
        />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="$emit('update:visible', false)">取消</el-button>
      <el-button type="primary" @click="handleSubmit">确定</el-button>
    </template>
  </el-dialog>
</template>

<script lang="ts">
import { Component, Vue, Prop, Ref, Watch } from 'vue-facing-decorator'
// import { addGod, godUpdate } from '@/api/god'
import { ElMessage } from 'element-plus'

const defaultForm = {
  godImage: '',
  godName: '',
  godDesc: '',
  godCount: 0
}

@Component({
  name: 'GodDialog',
  components: {},
  emits: ['update:visible', 'refresh']
})
export default class GodDialog extends Vue {
  @Prop({
    type: Boolean,
    default: false
  })
  private visible!: boolean

  @Prop({
    type: String,
    default: 'add'
  })
  private type!: string

  @Prop({
    type: Object,
    default: () => ({ ...defaultForm })
  })
  private editGod!: any

  private form = { ...defaultForm }

  @Ref()
  private formRef

  @Watch('editGod', { deep: true })
  onEditGodChange(newVal: any) {
    this.form = { ...newVal }
  }

  private handleClose() {
    this.$emit('update:visible', false)
    this.form = { ...defaultForm }
  }

  private rules = {
    godImage: [
      { required: true, message: '请输入商品图片URL', trigger: 'blur' }
    ],
    godName: [
      { required: true, message: '请输入商品名称', trigger: 'blur' }
    ],
    godDesc: [
      { required: true, message: '请输入商品描述', trigger: 'blur' }
    ],
    godCount: [
      { required: true, message: '请输入商品数量', trigger: 'blur' }
    ]
  }

  private async handleSubmit() {
    if (!this.formRef) return

    await this.formRef.validate(async (valid, fields) => {
      if (valid) {
        // const res = await (this.type === 'add'
        //   ? addGod(this.form)
        //   : godUpdate(this.form))
        // if (res.code === 200) {
        //   this.$emit('update:visible', false)
        //   ElMessage({
        //     message: this.type === 'add' ? '添加成功' : '编辑成功',
        //     type: 'success'
        //   })
        //   this.$emit('refresh')
        //   this.form = { ...defaultForm }
        // }
      }
    })
  }

  created() {
    this.form = { ...this.editGod }
  }
}
</script>

<style lang="scss" scoped></style>
