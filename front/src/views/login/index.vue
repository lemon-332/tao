<template>
  <div class="login">
    <div class="formLogin">
      <div class="logo">
        <img src="@/assets/rebot.png" alt="logo" />
      </div>
      <h2 class="title">Welcome aboard my friend</h2>
      <p class="subtitle">just a couple of clicks and we start</p>

      <el-form :model="login" class="login-form">
        <el-form-item>
          <el-input
            v-model="login.userName"
            placeholder="Email"
            prefix-icon="el-icon-message"
            @keyup.enter="handleLogin"
          />
        </el-form-item>
        <el-form-item>
          <el-input
            v-model="login.password"
            type="password"
            placeholder="Password"
            prefix-icon="el-icon-lock"
            @keyup.enter="handleLogin"
          />
        </el-form-item>

        <el-form-item>
          <el-radio-group v-model="login.role">
            <el-radio :value="1">普通用户</el-radio>
            <el-radio :value="2">管理员</el-radio>
            <el-radio :value="3">会员</el-radio>
          </el-radio-group>
        </el-form-item>

        <div class="forgot-password">
          <a href="#">Forgot password?</a>
        </div>

        <el-button class="submit" type="primary" @click="handleLogin">
          Log in
        </el-button>

        <div class="divider">
          <span>Or</span>
        </div>

        <div class="third-party-login">
          <el-button class="google-btn">
            <img src="@/assets/google.png" alt="google" />
            Google
          </el-button>
          <el-button class="github-btn">
            <img src="@/assets/gitlab.png" alt="github" />
            GitHub
          </el-button>
        </div>

        <div class="register-link">
          <span>Have no account yet?</span>
          <a href="#">Registration</a>
        </div>
      </el-form>
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
      ElMessage.success('登录成功')
      setTimeout(() => {
        this.$router.push('/')
      }, 1000)
    }
  }
}
</script>
<style lang="scss" scoped>
.login {
  width: 100%;
  height: 100vh;
  background: #4052e6;
  display: flex;
  align-items: center;
  justify-content: center;
}

.formLogin {
  width: 900px;
  background: #fff;
  border-radius: 20px;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 40px;
  box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
}

.logo {
  margin-bottom: 20px;
  img {
    width: 120px;
  }
}

.title {
  font-size: 24px;
  color: #333;
  margin-bottom: 10px;
}

.subtitle {
  color: #666;
  margin-bottom: 30px;
}

.login-form {
  width: 100%;
  max-width: 400px;
}

.submit {
  width: 100%;
  height: 45px;
  border-radius: 8px;
  margin: 20px 0;
}

.forgot-password {
  text-align: right;
  margin: -10px 0 10px;
  a {
    color: #4052e6;
    text-decoration: none;
    font-size: 14px;
  }
}

.divider {
  text-align: center;
  margin: 20px 0;
  position: relative;

  &::before,
  &::after {
    content: '';
    position: absolute;
    top: 50%;
    width: 45%;
    height: 1px;
    background: #ddd;
  }

  &::before {
    left: 0;
  }
  &::after {
    right: 0;
  }

  span {
    background: #fff;
    padding: 0 10px;
    color: #666;
  }
}

.third-party-login {
  display: flex;
  gap: 15px;
  margin-bottom: 20px;

  .google-btn,
  .github-btn {
    flex: 1;
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 8px;
    height: 45px;
    border-radius: 8px;
    border: 1px solid #ddd;

    img {
      width: 20px;
      height: 20px;
    }
  }
}

.register-link {
  text-align: center;
  font-size: 14px;

  span {
    color: #666;
  }

  a {
    color: #4052e6;
    text-decoration: none;
    margin-left: 5px;
  }
}

:deep(.el-input__inner) {
  height: 45px;
  border-radius: 8px;
}
</style>
