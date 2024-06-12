<template>
  <div class="login-container">
    <el-card class="login-card">
      <h2 class="login-title">商品后台管理系统</h2>
      <el-form :model="loginForm" @submit.native.prevent="handleLogin">
        <el-form-item label="用户名">
          <el-input v-model="loginForm.username" class="login-input"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input style="margin-left: 14px;" type="password" v-model="loginForm.password"
            class="login-input"></el-input>
        </el-form-item>
        <el-form-item label="验证码" class="captcha-container">
          <el-input v-model="loginForm.captcha" class="login-input captcha-input"></el-input>
          <img :src="captchaUrl" @click="refreshCaptcha" alt="验证码" class="captcha-image" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleLogin" class="login-button">登录</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import axios from 'axios';
import { ElMessage } from 'element-plus';

export default {
  data() {
    return {
      loginForm: {
        username: '',
        password: '',
        captcha: ''
      },
      captchaUrl: ''
    };
  },
  methods: {
    async handleLogin() {
      if (!this.loginForm.username || !this.loginForm.password || !this.loginForm.captcha) {
        ElMessage.error('请输入用户名、密码和验证码');
        return;
      }
      try {
        const response = await axios.post('http://localhost:8089/api/login', this.loginForm, { withCredentials: true });
        const token = response.data;
        localStorage.setItem('token', token);
        ElMessage.success('登录成功');
        this.$router.push('/main');
      } catch (error) {
        ElMessage.error('登录失败: ' + error.response.data);
      }
    },
    async refreshCaptcha() {
      try {
        const response = await axios.get('http://localhost:8089/api/captcha', { withCredentials: true });
        this.captchaUrl = 'data:image/png;base64,' + response.data;
      } catch (error) {
        ElMessage.error('获取验证码失败');
      }
    }
  },
  mounted() {
    this.refreshCaptcha();
  }
};
</script>

<style lang="less" scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f5f5f5;
  .login-card {
    width: 400px;
    padding: 20px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
    .login-title {
      text-align: center;
      margin-bottom: 50px;
      font-size: 24px;
      color: #333;
    }
    .login-input {
      width: 100%;
    }
    .captcha-container {
      display: flex;
      align-items: center;

      .captcha-image {
        cursor: pointer;
        height: 40px;
      }
      .captcha-input {
        flex: 1;
        margin-right: 10px;
      }
    }
    .login-button {
      width: 100%;
    }
  }
}
</style>
