<template>
  <el-dialog
    :title="type === 'add' ? '添加用户' : '编辑用户'"
    width="500px"
    :model-value="visible"
    @update:model-value="$emit('update:visible', $event)"
    @close="handleClose"
  >
    <el-form ref="formRef" :model="form" label-width="120px" :rules="rules">
      <el-form-item label="用户名" prop="userName">
        <el-input v-model="form.userName" placeholder="请输入用户名" />
      </el-form-item>
      <el-form-item label="昵称" prop="displayName">
        <el-input v-model="form.displayName" placeholder="请输入显示名称" />
      </el-form-item>
      <el-form-item label="手机号码" prop="phone">
        <el-input v-model="form.phone" placeholder="请输入手机号码" />
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input
          v-model="form.password"
          type="password"
          placeholder="请输入密码"
          show-password
        />
      </el-form-item>
      <el-form-item label="角色" prop="role">
        <el-select v-model="form.role" placeholder="请选择角色">
          <el-option :value="1" label="普通用户" />
          <el-option :value="2" label="管理员" />
          <el-option :value="3" label="会员" />
        </el-select>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="form.status" placeholder="请选择状态">
          <el-option :value="1" label="启用" />
          <el-option :value="0" label="禁用" />
        </el-select>
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
import { addUser, userUpdate } from '@/api/user'
import { ElMessage } from 'element-plus'

const defaultForm = {
  userName: '',
  phone: '',
  password: Math.random().toString(36).substr(2, 8),
  displayName: '',
  role: 1,
  status: 1
}

@Component({
  name: 'AddUserDialog',
  components: {},
  emits: ['update:visible', 'refresh']
})
export default class AddUserDialog extends Vue {
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
  private editUser!: any

  private form = { ...defaultForm }

  @Ref()
  private formRef

  @Watch('editUser', { deep: true })
  onEditUserChange(newVal: any) {
    this.form = { ...newVal }
  }

  private handleClose() {
    this.$emit('update:visible', false)
    this.form = { ...defaultForm }
  }

  private rules = {
    userName: [
      { required: true, message: '请输入用户名', trigger: 'blur' },
      { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }
    ],
    phone: [
      { required: true, message: '请输入手机号码', trigger: 'blur' },
      {
        pattern: /^1[3-9]\d{9}$/,
        message: '请输入正确的手机号码',
        trigger: 'blur'
      }
    ],
    password: [
      { required: true, message: '请输入密码', trigger: 'blur' },
      { min: 6, max: 20, message: '长度在 6 到 20 个字符', trigger: 'blur' }
    ],
    displayName: [
      { required: true, message: '请输入显示名称', trigger: 'blur' }
    ],
    role: [{ required: true, message: '请选择角色', trigger: 'change' }],
    status: [{ required: true, message: '请选择状态', trigger: 'change' }]
  }

  private async handleSubmit() {
    if (!this.formRef) return

    await this.formRef.validate(async (valid, fields) => {
      if (valid) {
        const res = await (this.type === 'add'
          ? addUser(this.form)
          : userUpdate(this.form))
        if (res.code === 200) {
          this.$emit('update:visible', false)
          ElMessage({
            message: this.type === 'add' ? '添加成功' : '编辑成功',
            type: 'success'
          })
          this.$emit('refresh')
          this.form = { ...defaultForm }
        }
      }
    })
  }

  created() {
    this.form = { ...this.editUser }
  }
}
</script>

<style lang="scss" scoped></style>
