<template>
  <header>
    <div class="container" style="margin-bottom: 20px">
      <div class="nav-scroller py-1 mb-2" style="margin-top:100px;">
        <ul class="nav d-flex justify-content-between">
          <div class="search">
            <input class="input" placeholder="请输入你想要查找的博客" v-model="keyString">
            <el-icon color="" :size="20" type="button" @click="searchBtn()"><Search/></el-icon>
          </div>
          <li class="nav-item">
            <a class="nav-link" aria-current="page" @click="controlBtn('java')" style="cursor:pointer">Java</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" aria-current="page" @click="controlBtn('vue')" style="cursor:pointer">Vue</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" aria-current="page" @click="controlBtn('springboot')" style="cursor:pointer">SpringBoot</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" aria-current="page" @click="controlBtn('bootstrap')" style="cursor:pointer">Bootstrap</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" aria-current="page" @click="controlBtn('java')" style="cursor:pointer">Animate</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" aria-current="page" @click="controlBtn('mysql')" style="cursor:pointer">MySQL</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" aria-current="page" @click="controlBtn('elementui')" style="cursor:pointer">ElementUI</a>
          </li>
        </ul>
      </div>
    </div>
  </header>
  <main class="container main1">
    <div class="p-4 p-md-5 mb-4 rounded text-bg-dark div1">
      <div class="col-md-2 px-0">
        <h1 class="display-4 fst-italic" style="color:black;font-size:30px;font-weight: bold">行太不面对博客列表</h1>
        <p class="lead my-3" style="color:black">几行文字表达了重复，以便快速有效地告知新读者有关本文内容最有趣的事情。</p>
        <p class="lead mb-0"><a href="#" class="text-white fw-bold" style="color:black">继续阅读</a></p>
      </div>
    </div>
    <div class="row div2">
      <div class="col-md-8">
        <h3 class="pb-4 mb-4 fst-italic border-bottom">
          博客展示
        </h3>
        <article class="blog-post" v-for="item in articleList" :key="item.id">
          <h5 class="blog-post-title mb-1"><a class = "nav-item" @click="gotoArticleDetail(item)" style="cursor:pointer">{{item.title}}</a></h5>
          <p class="blog-post-meta" >发布时间：{{item.creat}}</p>
          <p class="blog-post-meta">博客分类：{{item.category}}</p>
          <hr>
          <blockquote>
            <p>{{item.content}}</p>
          </blockquote>
        </article>
        <nav class="blog-pagination" aria-label="Pagination">
          <a class="btn btn-outline-primary rounded-pill"  @click="subCount()">上一页</a>
          <a class="btn btn-outline-primary rounded-pill" @click="addCount()">下一页</a>
        </nav>
      </div>

      <div class="col-md-4">
        <div class="position-sticky" style="top: 2rem;">
          <div class="p-4 mb-3 bg-light rounded">
            <h4 class="fst-italic">周围</h4>
            <p class="mb-0">好的程序员即使在过单行道时也总是会环顾两边。<br>不要担心它能否正常工作。如果一切正常，那么你就会失去工作。<br>
              程序员的烦恼是，你永远无法知道一个程序员在做什么，直到为时已晚。<br>程序员的美德：懒惰，没有耐心以及老子天下第一。<br>
              写代码的时候总是想象维护你代码的家伙是一个知道你住在哪里的暴力精神病患者。<br>一个人写的烂软件将会给另一个人带来一份全职工作。<br>
              如果建筑工人用程序员写程序的方式建造建筑物，那么来的第一只啄木鸟（找bug）就将摧毁文明。<br>
              大多数专家认为，世界被破坏的最有可能的方式是，是因为偶然。那就是我们所要进入的领域：我们是计算机专家。我们制造偶然。<br>
              这是我们这个行业的一件咄咄怪事：我们不仅不从错误中学习，我们也不从成功中学习。<br>一旦一种新技术开始滚动碾压道路，如果你不能成为压路机的一部分，那么你就只能成为道路的一部分。 </p>
          </div>

          <div class="p-4">
            <h4 class="fst-italic">我的博客</h4>
            <ol class="list-unstyled mb-0">
              <li><a @click="gotoblogArticleAdd()" class="nav-item" style="cursor:pointer">发布博客</a></li>
              <li><a @click="router.push('/articleManagement')" class="nav-item" style="cursor:pointer">管理博客</a></li>
            </ol>
          </div>

          <div class="p-4">
            <h4 class="fst-italic">链接</h4>
            <ol class="list-unstyled">
              <li><a href="#">GitHub</a></li>
              <li><a href="#">Gitee</a></li>
            </ol>
          </div>
        </div>
      </div>
    </div>

  </main>
</template>
<script setup>
import router from "@/router";
import {useStore} from "@/stores";
import {get} from "@/net";
import {ElMessage} from "element-plus";
import {Plus, Search} from "@element-plus/icons-vue";
const store = useStore()

import {onBeforeMount, onMounted, reactive, ref} from "vue";
import axios from "axios";

//博客文章分页功能实现
const getData = () =>{
  axios.request({
    method:'POST',
    url:'http://localhost:2223/api/article/articlePage',
    params:{
      startNum: startNum,
      recordNum: recordNum,
    },
  }).then(response => {
    articleList.value = response.data
    console.log(response)
  })
  return articleList
}

const addCount = () =>{
  if(selectNum === 0){
    count++;
    startNum = 5 * (count-1);
    articleList = getData(startNum,recordNum)
  }else if(selectNum === 1){
    count++;
    startNum = 5 * (count-1);
    articleList = getCategoryData(startNum,recordNum,category)
  }else if(selectNum === 2){
    count++;
    startNum = 5 * (count-1);
    articleList = getSearchData(startNum,recordNum,keyString)
  }
}
const subCount = () =>{
  if(selectNum === 0){
    if(count>=2){
      count--;
      startNum = 5 * (count-1);
      articleList = getData(startNum,recordNum)
    }else{
      alert("已经在第一页了")
    }
  }else if(selectNum === 1){
    if(count >= 2){
      count--;
      startNum = 5 * (count-1);
      articleList = getCategoryData(startNum,recordNum,category)
    }
  }else if(selectNum === 2){
    if(count >= 2){
      count--;
      startNum = 5 * (count-1);
      articleList = getSearchData(startNum,recordNum,keyString)
    }
  }

}

//数据初始化
let count = 1;
let recordNum = 5;
let startNum = 5 * (count-1);
let articleList = ref([])

let category = ""
let selectNum = 0

let keyString = ""

articleList = getData(startNum,recordNum)


onBeforeMount(() => {
})

//博客文章分类功能实现
const getCategoryData = () =>{
  axios.request({
    method:'POST',
    url:'http://localhost:2223/api/article/articleCategory',
    params:{
      startNum: startNum,
      recordNum: recordNum,
      category: category,
    },
  }).then(response => {
    articleList.value = response.data
    console.log(response)
  })
  return articleList
}

const controlBtn = (categoryString) => {
  count = 1
  recordNum = 5
  startNum = 5 * (count - 1)
  category = categoryString
  articleList = getCategoryData()
  selectNum = 1
}

//文章搜索功能实现
const getSearchData = () =>{
  axios.request({
    method:'POST',
    url:'http://localhost:2223/api/article/articleSearch',
    params:{
      startNum: startNum,
      recordNum: recordNum,
      keyString: keyString,
    },
  }).then(response => {
    articleList.value = response.data
    console.log(response)
  })
  return articleList
}

const searchBtn = () => {
  count = 1
  recordNum = 5
  startNum = 5 * (count-1)
  articleList = getSearchData()
  selectNum = 2
}

//跳转到文章详情页
const gotoArticleDetail = (item) => {
  router.push({
    //指定跳转目标页面
    path:'/articleDetail',
    //传递的参数
    query:{
      data:JSON.stringify(item)
    }
  })
}

const gotoblogArticleAdd = () => {
  router.push({
    path:'/articleAdd'
  })
}


</script>

<style scoped>
.bd-placeholder-img {
  font-size: 1.125rem;
  text-anchor: middle;
  -webkit-user-select: none;
  -moz-user-select: none;
  user-select: none;
}

@media (min-width: 768px) {
  .bd-placeholder-img-lg {
    font-size: 3.5rem;
  }
}

.b-example-divider {
  height: 3rem;
  background-color: rgba(0, 0, 0, .1);
  border: solid rgba(0, 0, 0, .15);
  border-width: 1px 0;
  box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
}

.b-example-vr {
  flex-shrink: 0;
  width: 1.5rem;
  height: 100vh;
}

.bi {
  vertical-align: -.125em;
  fill: currentColor;
}

.nav-scroller {
  position: relative;
  z-index: 2;
  height: 2.75rem;
  overflow-y: hidden;
}

.nav-scroller .nav {
  display: flex;
  flex-wrap: nowrap;
  padding-bottom: 1rem;
  overflow-x: auto;
  text-align: center;
  white-space: nowrap;
  -webkit-overflow-scrolling: touch;
}

.div1{
  margin-top: -50px;
  background: url("@/assets/img/articleimg/article01.png");
}
.div2{
  margin-top: -50px;
}
.main1{
  margin-bottom: -80px;
}
.search{
  /*padding:0 10px;*/
  display: flex;
  align-items: center;
  width:400px;
  height:40px;
  background: #F0F2F4;
  border-radius:5px;
}
.search .input{
  width:360px;
  height:40px;
  border:none;
  background: transparent;
  outline: none;
}
</style>