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
        <el-upload
          ref="uploadRef"
          v-model:file-list="fileList"
          name="files"
          action="/api/god/godUpload"
          list-type="picture-card"
          :auto-upload="false"
          :on-preview="handlePictureCardPreview"
        ></el-upload>
      </el-form-item>
      <el-form-item label="商品名称" prop="godName">
        <el-input v-model="form.godName" placeholder="请输入商品名称" />
      </el-form-item>
      <el-form-item label="商家名称" prop="sellerId">
        <el-autocomplete
          v-model="sellerName"
          :fetch-suggestions="querySearch"
          placeholder="请选择商家"
          @select="handleSelect"
        />
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
    <el-dialog v-model="dialogVisible" append-to-body>
      <img
        class="full-image"
        w-full
        :src="dialogImageUrl"
        alt="Preview Image"
      />
    </el-dialog>
  </el-dialog>
</template>

<script lang="ts">
import { Component, Vue, Prop, Ref, Watch } from 'vue-facing-decorator'
import { godAdd, godUpdate } from '@/api/god'
import type { UploadUserFile } from 'element-plus'

import { sellerList } from '@/api/seller'

import { ElMessage } from 'element-plus'

const defaultForm = {
  godImage: '',
  godName: '',
  godDesc: '',
  godCount: 0,
  sellerId: ''
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

  @Ref()
  private formRef

  @Ref()
  private uploadRef

  @Watch('editGod', { deep: true })
  onEditGodChange(newVal: any) {
    this.form = { ...newVal }
    if (this.type === 'edit' && newVal.sellerName && newVal.godImage) {
      this.sellerName = newVal.sellerName
      this.fileList = newVal.godImage.split(',').map((imageName) => ({
        name: imageName,
        url: `/api/god/godDownload/?fileName=${imageName}`,
        status: 'success'
      }))
    }
  }

  @Watch('visible')
  onVisibleChange(newVal: boolean) {
    if (newVal) {
      this.$nextTick(() => {
        sellerList({ sellerNameFuzzy: '', startTimeStart: '' }).then((res) => {
          this.sellerList = res.data.list.map((item) => {
            return {
              value: item.sellerName,
              id: item.sellerId
            }
          })
        })
      })
    }
    this.form.sellerId = ''
    this.fileList = []
  }

  private form: {
    godImage: string
    godName: string
    godDesc: string
    godCount: number
    sellerId: string
  } = { ...defaultForm }

  private fileList: UploadUserFile[] = []

  private dialogImageUrl = ''
  private dialogVisible = false

  private sellerName = ''

  private sellerList: Array<{ value: string; id: string }> = []

  private handlePictureCardPreview(uploadFile) {
    this.dialogImageUrl = uploadFile.url!
    this.dialogVisible = true
  }

  private handleSelect(item: Record<string, any>) {
    this.form.sellerId = item.id
    this.sellerName = item.value
  }

  private querySearch(queryString: string, cb) {
    const results = queryString
      ? this.sellerList.filter(this.createFilter(queryString))
      : this.sellerList
    cb(results)
  }

  private createFilter(queryString: string) {
    return (restaurant) => {
      return (
        restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0
      )
    }
  }

  private handleClose() {
    this.$emit('update:visible', false)
    this.form = { ...defaultForm }
    this.sellerName = ''
  }

  private rules = {
    godName: [{ required: true, message: '请输入商品名称', trigger: 'blur' }],
    godDesc: [{ required: true, message: '请输入商品描述', trigger: 'blur' }],
    godCount: [{ required: true, message: '请输入商品数量', trigger: 'blur' }],
    sellerId: [{ required: true, message: '请选择商家', trigger: 'blur' }]
  }

  private async handleSubmit() {
    if (!this.formRef) return

    try {
      const valid = await this.formRef.validate()
      if (valid) {
        this.form.godImage = this.fileList.map((file) => file.name).join(',')

        const res = await (this.type === 'add'
          ? godAdd(this.form)
          : godUpdate(this.form))

        if (this.fileList.length > 0) {
          await this.uploadRef?.submit()
        }

        if (res.code === 200) {
          this.$emit('update:visible', false)
          ElMessage({
            message: `${this.type === 'add' ? '添加' : '编辑'}成功`,
            type: 'success'
          })
          this.$emit('refresh')
          this.form = { ...defaultForm }
        }
      }
    } catch (error) {
      console.error(error)
    }
  }

  created() {
    this.form = this.type === 'add' ? { ...defaultForm } : { ...this.editGod }
    if (this.type !== 'add' && this.editGod.sellerName) {
      this.sellerName = this.editGod.sellerName
    }
  }
}
</script>

<style lang="scss" scoped>
.full-image {
  max-width: 100%; /* 最大宽度为父元素宽度 */
  height: auto; /* 高度自动，保持比例 */
  display: block; /* 消除底部空白 */
  margin: 0 auto;
}
</style>
