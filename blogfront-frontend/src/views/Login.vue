<template>
  <div style="width: 100vw;height: 100vh;overflow: hidden;display: flex">
    <div style="flex: 1;background-color: dodgerblue">
      <img style="width: 100%;height: 100%" src="@/assets/img/index-bg/img_6.png"/>
    </div>
    <div class="login-title">
      <div style="font-size: 30px;font-weight: bold;">欢迎进入个人博客后端管理系统</div>
      <div style="margin-top: 15px">在这里你可以自主修改博客权限。</div>
    </div>
    <div style="width: 500px;">
      <router-view v-slot="{ Component }">
        <transition  name="el-fade-in-linear">
          <component :is="Component" style="height:100%" />
        </transition>
      </router-view>

    </div>
  </div>
</template>

<script setup>

</script>

<style scoped>
.login-title{
  position:absolute;
  bottom: 60px;
  left: 30px;
  color: white;
  text-shadow: 0 0 10px black;
  font-size: 20px;
}
</style>