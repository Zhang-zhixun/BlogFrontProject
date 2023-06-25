<template>
  <div>
    <div class="content flex" style="margin-top: 15vh;color: black">
      <h3 class="animate__animated" :class="{animate__rubberBand:true}" ref="text"></h3>
    </div>
    <div class="row mt-lg-2" style="height: 50vh">


      <div class="col-sm-6 mb-3 mb-sm-0">
        <div class="card" style="margin-top: 25vh;border: 0">
          <div class="card-body">
            <p class="card-text" style="margin-left: 10vw;font-weight: bold">通用后端管理系统模板</p>
            <p class="card-text" style="margin-left: 10vw;font-size:25px">
              描述:Vue3 + SpringBoot3 的管理系统可直接运行应用，有助于学习
            </p>
            <div>
              <a v-if="store.auth.user!=null"
                  class="btn btn-outline-primary"
                  type="submit"
                  href="https://github.com/Zhang-zhixun/BlogProject/archive/refs/heads/master.zip"
                  style="margin-left: 24vw;width:100px;margin-top:5vh;height:5vh;line-height: 5vh;"
                  >下载资源</a>

              <button v-if="store.auth.user==null"
                 disabled
                 class="btn btn-outline-primary"
                 type="submit"
                 style="margin-left: 24vw;width:100px;margin-top:5vh;height:5vh;line-height: 5vh;"
              >下载资源</button>
            </div>

          </div>
        </div>
      </div>

      <div class="col-sm-6" style="margin-top: 10vh;border: 0">
        <div class="card" style="border: 0">
          <div class="card-body" data-aos="zoom-in" data-aos-duration="1000">
            <img
                class="shadow p-3 mb-5 bg-white rounded"
                src="@/assets/img/index-bg/img_2.png" style="width: 80%;"/>
          </div>
        </div>
      </div>
    </div>

    <div class="row" style="height: 50vh;margin-top:20vh">

      <div class="col-sm-6" style="margin-top: 10vh;border: 0">
        <div class="card" style="border: 0">
          <div class="card-body" data-aos="fade-right" data-aos-duration="1000">
            <img
                class="shadow p-3 mb-5 bg-white rounded"
                src="@/assets/img/index-bg/img_7.png" style="width: 80%;margin-left: 10vw"/>
          </div>
        </div>
      </div>

      <div class="col-sm-6 mb-3 mb-sm-0">
        <div class="card" style="margin-top: 25vh;border: 0;padding:0 4vw">
          <div class="card-body">
            <p class="card-text" style="font-weight: bold">Vue3前端模板</p>
            <p class="card-text" style="font-size:25px">
              描述:Vue3的前端模板可直接运行应用，有助于学习
            </p>
            <a  v-if="store.auth.user!=null" class="btn btn-outline-primary" type="button"
               style="margin-left:15vw;width:100px;margin-top:5vh;height:5vh;line-height: 5vh">下载资源</a>

            <button  v-if="store.auth.user==null" disabled class="btn btn-outline-primary" type="button"
                style="margin-left:15vw;width:100px;margin-top:5vh;height:5vh;line-height: 5vh">下载资源</button>
          </div>
        </div>
      </div>
    </div>

    <div class="row" style="height: 50vh;margin-top:20vh">

      <div class="col-sm-6 mb-3 mb-sm-0">
        <div class="card" style="margin-top: 25vh;border: 0">
          <div class="card-body">
            <p class="card-text" style="margin-left: 10vw;font-weight: bold">通用后端管理系统模板</p>
            <p class="card-text" style="margin-left: 10vw;font-size:25px">
              描述:Vue3 + SpringBoot3 的管理系统可直接运行应用，有助于学习
            </p>
            <a  v-if="store.auth.user!=null" class="btn btn-outline-primary" type="button"
               href="https://github.com/Zhang-zhixun/BlogProject/archive/refs/heads/master.zip"
               style="margin-left: 24vw;width:100px;margin-top:5vh;height:5vh;line-height: 5vh">下载资源</a>

            <button  v-if="store.auth.user==null" disabled class="btn btn-outline-primary" type="button"
                style="margin-left: 24vw;width:100px;margin-top:5vh;height:5vh;line-height: 5vh">下载资源</button>

          </div>
        </div>
      </div>

      <div class="col-sm-6" style="margin-top: 10vh;border: 0">
        <div class="card" style="border: 0">
          <div class="card-body" data-aos="flip-down" data-aos-duration="1000">
            <img
                class="shadow p-3 mb-5 bg-white rounded"
                src="@/assets/img/index-bg/img_8.png" style="width: 80%;"/>
          </div>
        </div>
      </div>
    </div>

    <div class="row" style="height: 50vh;margin-top:20vh">

      <div class="col-sm-6" style="margin-top: 10vh;border: 0">
        <div class="card" style="border: 0">
          <div class="card-body" data-aos="zoom-out">
            <img
                class="shadow p-3 mb-5 bg-white rounded"
                src="@/assets/img/index-bg/img_5.png" style="width: 80%;margin-left: 10vw"/>
          </div>
        </div>
      </div>

      <div class="col-sm-6 mb-3 mb-sm-0">
        <div class="card" style="margin-top: 25vh;border: 0;padding:0 4vw">
          <div class="card-body">
            <p class="card-text" style="font-weight: bold">Vue3前端模板</p>
            <p class="card-text" style="font-size:25px">
              描述:Vue3的前端模板可直接运行应用，有助于学习
            </p>
            <a v-if="store.auth.user!=null" class="btn btn-outline-primary" type="button"
               style="margin-left:15vw;width:100px;margin-top:5vh;height:5vh;line-height: 5vh">下载资源</a>
            <button v-if="store.auth.user==null" disabled class="btn btn-outline-primary" type="button"
               style="margin-left:15vw;width:100px;margin-top:5vh;height:5vh;line-height: 5vh">下载资源</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>

import {ref, onMounted} from "vue";
import TypeIt from 'typeit';
import AOS from 'aos';
import 'aos/dist/aos.css';
import {useStore} from "@/stores";
import {
  Check,
  Delete,
  Edit,
  Message,
  Search,
  Star,
} from '@element-plus/icons-vue'
const text = ref(null)
const imgURL = ref('@/assets/img/index-bg/img_1.png')
const store = useStore()
let isDisabled = ref(false)
if (store.auth.user != null) {
  isDisabled = true;
}

onMounted(() => {
      new (TypeIt)(text.value, {
        strings: ["欢迎进入资源首页！", "您可以选择自己喜欢的模板进行下载", "但请记得登录哦！"],
        cursorChar: "<span class='cursorChar'>|<span>",//用于光标的字符。HTML也可以
        speed: 120,
        lifeLike: true,// 使打字速度不规则
        cursor: true,//在字符串末尾显示闪烁的光标
        breakLines: false,// 控制是将多个字符串打印在彼此之上，还是删除这些字符串并相互替换
        loop: false,//是否循环
      }).go()
    },
)

</script>

<style scoped>
* {
  padding: 0;
  margin: 0;
}

h1 {
  font-weight: 300;
  letter-spacing: 2px;
  font-size: 48px;
}

p {
  font-family: 'Lato', sans-serif;
  letter-spacing: 1px;
  font-size: 30px;
  color: #333333;
}

.header {
  position: relative;
  text-align: center;
  background: linear-gradient(60deg, white 0%, white 100%);
  color: white;
}

.inner-header {
  height: 45vh;
  width: 100%;
  margin: 0;
  padding: 0;
}

.flex {
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
}

.waves {
  position: relative;
  width: 100%;
  height: 15vh;
  margin-bottom: -7px;
  min-height: 100px;
  max-height: 150px;
}

.content {
  position: relative;
  margin-top: -45vh;
  text-align: center;
  font-weight: bold;
  color: white;
}

.parallax > use {
  /* 使use元素执行move-forever动画 */
  animation: move-forever 25s cubic-bezier(.55, .5, .45, .5) infinite;
}

.parallax > use:nth-child(1) {
  /* 延迟2秒启动动画  */
  animation-delay: -6s;
  /* 设置动画持续时间为7秒 */
  animation-duration: 4s;
}

.parallax > use:nth-child(2) {
  animation-delay: -6s;
  animation-duration: 4s;
}

.parallax > use:nth-child(3) {
  animation-delay: -6s;
  animation-duration: 4s;
}

.parallax > use:nth-child(4) {
  animation-delay: -3s;
  animation-duration: 4s;
}

@keyframes move-forever {
  0% {
    transform: translate3d(-90px, 0, 0);
  }

  100% {
    transform: translate3d(85px, 0, 0);
  }
}

@media (max-width: 768px) {
  .waves {
    height: 40px;
    min-height: 40px;
  }

  .content {
    height: 30vh;
  }

  h1 {
    font-size: 24px;
  }
}

</style>