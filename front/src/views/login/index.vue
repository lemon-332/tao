<template>
  <div class="login">
    <div class="formLogin">
      <h2 class="title">VUE-ADMIN-MS</h2>
      <el-form label-width="100px" :model="login" style="max-width: 500px">
        <el-form-item label="用户名：">
          <el-input v-model="login.userName" @keyup.enter="handleLogin" />
        </el-form-item>
        <el-form-item label="密码：">
          <el-input
            v-model="login.password"
            type="password"
            @keyup.enter="handleLogin"
          />
        </el-form-item>
      </el-form>
      <el-button class="submit" type="primary" @click="handleLogin">
        登录
      </el-button>
    </div>
    <div class="message">
      <div>账号 / 密码：admin / 123456</div>
      <div>账号 / 密码：jerry / 123456</div>
    </div>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from 'vue-facing-decorator'
import { login } from '@/api/user'
import { useCookies } from 'vue3-cookies'
import { ElMessage } from 'element-plus'

@Component
export default class Login extends Vue {
  private dialogFormVisible = false

  private login = {
    userName: 'admin',
    password: '123456',
    role: 1
  }

  private async handleLogin() {
    const { userName, password, role } = this.login
    const res = await login({ userName, password, role: Number(role) })
    if (res.code !== 200) {
      ElMessage({
        message: res.info,
        type: 'error'
      })
    }
    if (res.code === 200) {
      const { cookies } = useCookies()
      cookies.set('userInfo', res.data, '1d')
      this.$router.push('/')
    }
  }
}
</script>
<style lang="scss" scoped>
.login {
  width: 100%;
  height: 100%;
  background: rgb(32, 112, 165);
}
.login .formLogin {
  width: 30%;
  min-width: 600px;
  height: 30%;
  min-height: 300px;
  margin: 0 auto;

  background: #fff;
  border-radius: 6px;
  position: relative;
  top: 30%;
}

.login .formLogin .title {
  padding: 24px 0;
}

.login .formLogin .submit {
  width: 50%;
  margin: 24px auto;
}

.message {
  width: 31%;
  color: #f3f3f3;
  margin: 0 auto;
  text-align: left;
  position: relative;
  top: 32%;
}

.message div {
  height: 25px;
  line-height: 25px;
}
</style>
