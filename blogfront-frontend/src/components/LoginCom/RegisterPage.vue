<template>
  <div style="text-align: center;margin: 0 20px">
    <div style="margin-top: 80px">
      <div style="font-size: 25px"><h3>注册新用户</h3></div>
      <div style="font-size: 15px">欢迎注册智慧医疗平台，请在下方填写相关信息</div>
    </div>

    <div style="margin-top: 50px">
      <el-form
          :model="form"
          :rules="rules"
          @validate="onValidate"
          ref="formRef"
      >
        <el-form-item prop="username">
          <el-input v-model="form.username" size="large" type="text" placeholder="用户名/邮箱">
            <template #prefix>
              <el-icon>
                <User/>
              </el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="form.password" size="large" type="password" placeholder="密码">
            <template #prefix>
              <el-icon>
                <Lock/>
              </el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="password_repeat">
          <el-input size="large" v-model="form.password_repeat" type="password"
                    placeholder="重复密码">
            <template #prefix>
              <el-icon>
                <Lock/>
              </el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="email">
          <el-input size="large" v-model="form.email" type="email"
                    placeholder="请输入电子邮件">
            <template #prefix>
              <el-icon>
                <Message/>
              </el-icon>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="code">
          <el-row :gutter="15" style="width: 100%">
            <el-col :span="18">

              <el-input v-model="form.code" size="large" type="text" placeholder="请输入电子邮件验证码">
                <template #prefix>
                  <el-icon>
                    <Iphone/>
                  </el-icon>
                </template>
              </el-input>

            </el-col>
            <el-col :span="6">
              <el-button size="large" @click="validateEmail" type="primary"
                         :disabled="!isEmailValid || codetime > 0"
                         style="width: 115px">
                {{ codetime > 0 ? '请稍后' + codetime : '获取验证码' }}
              </el-button>
            </el-col>
          </el-row>
        </el-form-item>
      </el-form>
    </div>


    <div style="margin-top: 30px">
      <el-button @click="register()" size="large" style="width: 270px" type="success" plain>立即注册</el-button>
    </div>

    <el-divider>
      <span style="color: grey">是否已存在账号</span>
    </el-divider>

    <div style="margin-top: 10px">
      <el-link @click="router.push('/login')">已有账号？立即登录</el-link>
    </div>

  </div>
</template>

<script setup>
import {Lock, Message, User} from "@element-plus/icons-vue";
import {reactive, ref} from 'vue'
import {ElMessage} from "element-plus";
import router from "@/router";
import {post} from "@/net";

const form = reactive({
  username: '',
  password: '',
  password_repeat: '',
  email: '',
  code: ''
})

//验证用户名格式
const validateUsername = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('请输入用户名'))
    //判断用户名是否以英文开头并不存在特殊字符
  } else if (!/^[a-zA-Z][a-zA-Z0-9]*$/.test(value)) {
    callback(new Error('用户名只能以英文开头,不能存在特殊字符'))
  } else {
    callback()
  }
}

//验证密码是否一致
const validatePassword = (rule, value, callback) => {
  if (value === '') {
    callback(new Error('请再次输入密码'))
  } else if (value !== form.password) {
    callback(new Error('两次密码不一致请重新输入'))
  } else {
    callback()
  }
}

//校验输入框
const rules = {
  username: [
    {validator: validateUsername, trigger: ['blur', 'change']},
    {min: 3, max: 16, message: '用户名长度3-16个字符之间', trigger: ['blur', 'change']},
  ],
  password: [
    {required: true, min: 6, max: 12, message: '密码长度6-12个字符之间', trigger: ['blur', 'change']},
  ],
  password_repeat: [
    {validator: validatePassword, trigger: ['blur', 'change']},
  ],
  email: [
    {required: true, message: '请输入邮件地址', trigger: ['blur', 'change']},
    {type: 'email', message: '请输入合法的电子邮件', trigger: ['blur', 'change']},
  ],
  code: [
    {required: true, message: '请输入获取的验证码', trigger: ['blur', 'change']},
  ]
}

//创建判断邮件是否通过验证的变量
const isEmailValid = ref(false)
const onValidate = (prop, isValid) => {
  if (prop === 'email')
    isEmailValid.value = isValid
}

const formRef = ref()
const codetime = ref(0)

//validata 对整个表单的内容进行验证。 接收一个回调函数，或返回 Promise。
const register = () => {
  formRef.value.validate((isVaild => {
    if (isVaild) {
      post('/api/user/register', {
        username: form.username,
        password: form.password,
        email: form.email,
        code: form.code
      }, (message) => {
        router.push('/login')
        ElMessage.success(message)
      })
    } else {
      ElMessage.warning('请完整填写注册表单内容')
    }
  }))
}

const validateEmail = () => {
  codetime.value = 60
  post('/api/user/valid-register-email', {
    email: form.email
  }, (message) => {
    ElMessage.success(message)
    //验证码时间
    setInterval(() => codetime.value--, 1200)
  }, () => {
    ElMessage.warning('邮箱已被注册')
    codetime.value = 0
  })
}


</script>

<style scoped>

</style>