<template>
  <div>
    <div style="margin:30px 20px">
      <el-steps :active="active" finish-status="success" align-center>
        <el-step title="验证电子邮件"/>
        <el-step title="重新设置密码"/>
      </el-steps>
    </div>

    <transition name="el-fade-in-linear" mode="out-in">
      <div style="text-align: center;margin: 0 10px;height: 100%" v-if="active == 0">
        <div style="margin-top: 100px">
          <div style="font-size: 25px"><h3>验证电子邮件</h3></div>
          <div style="font-size: 15px">请输入需要重置密码的电子邮箱</div>
        </div>

        <div style="margin-top: 50px">
          <el-form
              :model="form"
              :rules="rules"
              @validate="onValidate"
              ref="formRef"
          >

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
          <el-button @click="startRest()" size="large" style="width: 270px" type="danger" plain>验证邮箱验证码
          </el-button>
        </div>
        <el-link @click="router.push('/login')">返回登录界面</el-link>
      </div>
    </transition>

    <transition name="el-fade-in-linear" mode="out-in">
      <div style="text-align: center;margin: 0 10px;height: 100%" v-if="active == 1">
        <div style="margin-top: 100px">
          <div style="font-size: 25px"><h3>重新设置密码</h3></div>
          <div style="font-size: 15px">请输入您新的密码,务必牢记,防止丢失</div>
        </div>

        <div style="margin-top: 50px">
          <el-form
              :model="form"
              :rules="rules"
              @validate="onValidate"
              ref="formRef"
          >
            <el-form-item prop="password">
              <el-input v-model="form.password" size="large" type="password" placeholder="新密码">
                <template #prefix>
                  <el-icon>
                    <Lock/>
                  </el-icon>
                </template>
              </el-input>
            </el-form-item>

            <el-form-item prop="password_repeat">
              <el-input size="large" v-model="form.password_repeat" type="password"
                        placeholder="重复新密码">
                <template #prefix>
                  <el-icon>
                    <Lock/>
                  </el-icon>
                </template>
              </el-input>
            </el-form-item>
          </el-form>
        </div>

        <div style="margin-top: 30px">
          <el-button @click="doReset()" size="large" style="width: 270px" type="success" plain>立即重置密码</el-button>
        </div>

        <el-link @click="router.push('/login')">返回登录界面</el-link>

      </div>


    </transition>
  </div>


</template>

<script setup>
import {Lock, Message, User} from "@element-plus/icons-vue";
import {reactive, ref} from 'vue'
import {ElMessage} from "element-plus";
import router from "@/router";
import {post} from "@/net";

const form = reactive({
  email: '',
  code: '',
  password: '',
  password_repeat: ''
})

const active = ref(0)


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
  email: [
    {required: true, message: '请输入邮件地址', trigger: ['blur', 'change']},
    {type: 'email', message: '请输入合法的电子邮件', trigger: ['blur', 'change']},
  ],
  code: [
    {required: true, message: '请输入获取的验证码', trigger: ['blur', 'change']},
  ],
  password: [
    {required: true, min: 6, max: 12, message: '密码长度6-12个字符之间', trigger: ['blur', 'change']},
  ],
  password_repeat: [
    {validator: validatePassword, trigger: ['blur', 'change']},
  ]
}

//创建判断邮件是否通过验证的变量
const isEmailValid = ref(false)
//表单
const formRef = ref()
//验证码时间
const codetime = ref(0)
//为输入邮箱 无法点击发送验证码按钮
const onValidate = (prop, isValid) => {
  if (prop === 'email')
    isEmailValid.value = isValid
}

//验证码时间
const validateEmail = () => {
  codetime.value = 60
  post('/api/user/valid-reset-email', {
    email: form.email
  }, (message) => {
    ElMessage.success(message)
    //验证码时间
    setInterval(() => codetime.value--, 1200)
  }, () => {
    ElMessage.warning('邮箱未存在')
    codetime.value = 0
  })
}
//验证 邮箱和验证码
const startRest = () => {
  formRef.value.validate((isVaild => {
    if (isVaild) {
      post('/api/user/start-reset', {
        email: form.email,
        code: form.code
      }, (message) => {
        active.value++
      })
    } else {
      ElMessage.warning('请填写邮件地址和验证码')
    }
  }))
}
//重置密码
const doReset = () => {
  formRef.value.validate((isVaild => {
    if (isVaild) {
      post('/api/user/do-reset', {
        password: form.password
      }, (message) => {
        router.push('/login')
      })
    } else {
      ElMessage.warning('请填写新的密码')
    }
  }))
}


</script>

<style scoped>

</style>