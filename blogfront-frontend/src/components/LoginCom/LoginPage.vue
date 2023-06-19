<template>
  <div style="text-align: center;margin: 0 20px">
    <el-button @click="router.push('/')" size="large" style="width: 100px;margin-left: 85%" >博客首页</el-button>
    <div style="margin-top: 120px">
      <div style="font-size: 25px"><h3>登录</h3></div>
      <div style="font-size: 15px">进入系统前，请先输入用户名和密码</div>
    </div>

    <div style="margin-top: 50px">
      <el-input v-model="form.username" size="large" type="text" placeholder="用户名/邮箱">
        <template #prefix>
          <el-icon>
            <User/>
          </el-icon>
        </template>
      </el-input>

      <el-input v-model="form.password" size="large" type="password" style="margin-top: 20px" placeholder="密码">
        <template #prefix>
          <el-icon>
            <Lock/>
          </el-icon>
        </template>
      </el-input>
    </div>


    <div style="margin-top: 10px">
      <el-row>

        <el-col :span="12" style="text-align: left">
          <el-checkbox v-model="form.remember" label="记住我" size="large"/>
        </el-col>


        <el-col :span="12" style="text-align: right">
          <el-link @click="router.push('/login/forget')">忘记密码?</el-link>
        </el-col>


      </el-row>
    </div>

    <div style="margin-top: 30px">
      <el-button @click="login()" size="large" style="width: 270px" type="success" plain>登录</el-button>
    </div>

    <el-divider>
      <span style="color: grey">没有账号</span>
    </el-divider>

    <div>
      <el-button @click="router.push('/login/register')" size="large" style="width: 270px" type="warning" plain>
        注册账号
      </el-button>
    </div>

  </div>
</template>

<script setup>
import {Lock, User} from "@element-plus/icons-vue";
import {reactive} from 'vue'
import {ElMessage} from "element-plus";
import router from "@/router";
import {get, post} from "@/net";
import {useStore} from "@/stores";

const form = reactive({
  username: '',
  password: '',
  remember: false
})

const store = useStore()

const login = () => {
  if (!form.username || !form.password) {
    ElMessage.warning('请填写用户名和密码')
  } else {
    //后端服务器
    post('/api/user/login ', {
      username: form.username,
      password: form.password,
      remember: form.remember
    }, (message) => {
      ElMessage.success(message)
      get('/api/role/me', (message) => {
        store.auth.user = message
        console.log(message + 'user')
        router.push('/')
      })
    })
  }
}


</script>

<style scoped>

</style>