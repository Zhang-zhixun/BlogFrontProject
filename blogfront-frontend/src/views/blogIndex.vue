<template>
<BlogHeader/>
  <el-main  class="scrollable-content">
    <router-view v-slot="{ Component }">
      <transition name="el-fade-in-linear">
        <component :is="Component" />
      </transition>
    </router-view>


    <div class="row" style="height: 15vh;margin-top:20vh;text-align: center;padding: 30px;background: #f8f8f8">
      <footer class="blog-footer">
        <div class="copyright_info" >
          <p style="font-size: 13px;color: #999999"><a href="#" target="_blank">京公网安备 11000002000088号</a>
            <span class="copyright_split"> | </span>
            <span><a href="//beian.miit.gov.cn" target="_blank">京ICP备11041704号</a></span>
            <span class="copyright_split"> | </span>
            <a href="#" rel="noopener noreferrer" target="_blank">ICP</a>
            <span class="copyright_split"> | </span>
            <a href="#" target="_blank">经营性网站备案信息</a>
            <span class="copyright_split"> | </span><span>新出发京零 字第大120007号</span></p>
          <p style="font-size: 13px;color: #999999"><span>互联网出版许可证编号新出网证(京)字150号</span>
            <span class="copyright_split"> | </span>
            <a
                href="#"
                target="_blank">出版物经营许可证</a><span class="copyright_split"> | </span> <a
                href="#"
                target="_blank">网络文化经营许可证京网文[2020]6112-1201号</a><span
                class="copyright_split"> | </span>
            <span>违法和不良信息举报电话：4006561155</span>
          </p>
          <p style="font-size: 13px;color: #999999">
              <span class="copyright_text">Copyright © 2004 -
                <em id="copyright_year">2023</em>
                XingTai.com 版权所有
              </span>
            <span class="copyright_split"> | </span><span>消费者维权热线：4006067733</span>
            <a class="copyright_license" href="#" target="_blank">经营证照</a>
            <span class="copyright_split"> | </span>
            <span>(京)网械平台备字(2018)第00003号</span>
            <span class="copyright_split"> | </span>
            <a
                class="mod_business_license" href="#"
                target="_blank">营业执照</a>
            <span class="copyright_split"> | </span>
            <a
                href="#" rel="noopener noreferrer"
                target="_blank">增值电信业务经营许可证</a>
          </p>


        </div>
      </footer>
    </div>


  </el-main>

</template>

<script setup>

import {ref, onMounted} from "vue";
import TypeIt from 'typeit';
import BlogHeader from '@/components/blogIndexCom/blogHeader.vue';

import {
  Check,
  Delete,
  Edit,
  Message,
  Search,
  Star,
} from '@element-plus/icons-vue'

</script>

<style scoped>
.scrollable-content {
  overflow: auto; /* 允许内容滚动 */
  scrollbar-width: none; /* 隐藏滚动条宽度 */
  -ms-overflow-style: none; /* 隐藏 IE/Edge 浏览器的滚动条 */
}
.scrollable-content::-webkit-scrollbar {
  display: none; /* 隐藏 Webkit 内核浏览器的滚动条 */
}
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
  background: linear-gradient(180deg, white 0%, white 100%);
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

.a{
  position:relative;
  margin-left:40%;
  margin-top:10vh;
  opacity:0.8;
  width:400px;
  height:100px;
  line-height:100px;
  text-align:center;
  text-decoration:none;
  text-transform:uppercase;
  font-size:24px;
  color:#fff;
  background:linear-gradient(to right,#03a9f4,#f441a5,#ffeb3b,#09a8f4);
  background-size: 400%;
  border-radius:50px;
  z-index:1;

}

.a::before{
  content:'';
  position: absolute;
  top:-5px;
  left:-5px;
  bottom:-5px;
  right:-5px;
  z-index:-1;
  filter:blur(20px);
}

.a:hover{
  animation:streamer 8s infinite
}

.a:hover::before{
  animation:streamer 8s infinite
}

@keyframes streamer {
  100%{
    background-position: -400% 0;
  }
}

</style>