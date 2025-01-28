<template>
  <el-upload
    ref="uploadRef"
    v-model:file-list="fileList"
    name="files"
    action="/api/god/godUpload"
    list-type="picture-card"
    :auto-upload="false"
    :on-preview="handlePictureCardPreview"
    :on-remove="handleRemove"
  ></el-upload>

  <el-dialog v-model="dialogVisible">
    <img w-full :src="dialogImageUrl" alt="Preview Image" />
  </el-dialog>

  <el-button class="ml-3" type="success" @click="submitUpload">
    upload to server
  </el-button>
</template>

<script lang="ts">
import { Component, Ref, Vue } from 'vue-facing-decorator'
import type { UploadProps, UploadUserFile } from 'element-plus'

@Component({
  components: {}
})
export default class ActivityLog extends Vue {
  @Ref()
  private uploadRef

  private fileList: UploadUserFile[] = [
    {
      name: 'food.jpeg',
      url: '/api/god/godDownload?fileName=1.png'
    },
    {
      name: 'plant-1.png',
      url: '/images/plant-1.png'
    },
    {
      name: 'food.jpeg',
      url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
    },
    {
      name: 'plant-2.png',
      url: '/images/plant-2.png'
    },
    {
      name: 'food.jpeg',
      url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
    },
    {
      name: 'figure-1.png',
      url: '/images/figure-1.png'
    },
    {
      name: 'food.jpeg',
      url: 'https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100'
    },
    {
      name: 'figure-2.png',
      url: '/images/figure-2.png'
    }
  ]

  dialogImageUrl = ''
  dialogVisible = false

  handleRemove(uploadFile, uploadFiles) {
    console.log(uploadFile, uploadFiles)
  }

  handlePictureCardPreview(uploadFile) {
    this.dialogImageUrl = uploadFile.url!
    this.dialogVisible = true
  }
  submitUpload() {
    console.log(this.uploadRef)

    this.uploadRef!.submit()
  }
}
</script>
<style lang="scss" scoped></style>
