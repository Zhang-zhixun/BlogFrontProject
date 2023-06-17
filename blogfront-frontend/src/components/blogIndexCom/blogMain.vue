<template>
  <div style="height: 100vh;">
    <div style="background-color: rgba(30, 144, 255);height: 100vh;">
      <div class="header">
        <div class="inner-header flex"></div>
        <div>
          <!--
              xmlns:SVG命名看见URI
              viewBox:定义当前视口（viewbox）中绘制区域的位置大小
              preserveeAspectRatio：定义了绘制区域在视口中的对齐方式
              shape-rendering：定义了形状的渲染方式（如何呈现头像的锯齿效果）
           -->
          <svg class="waves" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"
               viewBox="0 24 150 28" preserveAspectRatio="none" shape-rendering="auto">
            <!-- 定义一个defs元素，用于存储各种元素的定义，可以被其他元素引用。
                在defs元素中定义了一个名为“gentle-wave”的路径元素，用于描述波浪形状
                改路径秒苏联一系列二次贝塞尔曲线的控制点坐标，从而实现了波浪形状 -->
            <defs>
              <path id="gentle-wave"
                    d="M-160 44c30 0 58-18 88-18s 58 18 88 18 58-18 88-18 58 18 88 18 v44h-352z"/>
            </defs>
            <!-- 定义一个g元素，用于讲多个图形组合在一期，并应用一些样式
                在g元素中使用use元素多次引用了赚钱定义的名为gentle-wave的路径元素
                通过设置不同的x，y坐标和填充颜色，实现了波浪形状和渐变效果 -->
            <g class="parallax">
              <use xlink:href="#gentle-wave" x="48" y="0" fill="rgba(30, 144, 255,0.7)"/>
              <use xlink:href="#gentle-wave" x="48" y="3" fill="rgba(30, 144, 255,0.5)"/>
              <use xlink:href="#gentle-wave" x="48" y="5" fill="rgba(30, 144, 255,0.3)"/>
              <use xlink:href="#gentle-wave" x="48" y="7" fill="rgba(30, 144, 255,0.8)"/>
            </g>

          </svg>
        </div>
      </div>
      <div class="content flex">
        <h1 style="font-weight: bold;
          font-size: 64px;
          background-image:-webkit-linear-gradient(left,black,black);
          -webkit-background-clip:text;
          -webkit-text-fill-color:transparent;"
            class="animate__animated" :class="{animate__rubberBand:true}">行太不灭队博客官网</h1>

      </div>
      <div class="content flex" style="margin-top: 10vh;color: black">
        <h3 class="animate__animated" :class="{animate__rubberBand:true}" ref="text"></h3>
      </div>
    </div>
  </div>
  <div>
    <div class="row mt-lg-2" style="height: 50vh">

      <div class="col-sm-6 mb-3 mb-sm-0">
        <div class="card" style="margin-top: 25vh;border: 0">
          <div class="card-body">
            <p class="card-text" style="margin-left: 10vw">资源</p>
            <p class="card-text" style="margin-left: 10vw">可以直接运行的小应用，便于学习</p>
          </div>
        </div>
      </div>

      <div class="col-sm-6" style="margin-top: 10vh;border: 0">
        <div class="card" style="border: 0">
          <div class="card-body" data-aos="fade-up" data-aos-duration="1000">
            <img
                class="shadow p-3 mb-5 bg-white rounded"
                src="@/assets/img/index-bg/img_2.png" style="width: 680px;height: 400px;"/>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>

import {ref, onMounted} from "vue";
import TypeIt from 'typeit'

const text = ref(null)
const imgURL = ref('@/assets/img/index-bg/img_1.png')
import AOS from 'aos';
import 'aos/dist/aos.css';

onMounted(() => {
      new (TypeIt)(text.value, {
        strings: ["您好！ 欢迎来到行太不灭队博客官网", "我是团队成员@ZhiXun", "请滚动滑轮往下浏览博客内容"],
        cursorChar: "<span class='cursorChar'>|<span>",//用于光标的字符。HTML也可以
        speed: 200,
        lifeLike: true,// 使打字速度不规则
        cursor: true,//在字符串末尾显示闪烁的光标
        breakLines: false,// 控制是将多个字符串打印在彼此之上，还是删除这些字符串并相互替换
        loop: false,//是否循环
      }).go()
    }
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