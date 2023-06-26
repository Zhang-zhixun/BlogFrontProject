<template>
  <div class="header"></div>
  <div class="container articlediv">
    <!-- 文章标题 -->
    <h4 class="post-title">{{itemList.title}}</h4>
    <p class="post-info">
      <!-- 文章发布时间 -->
      <span>发布于：{{itemList.creat}}</span>
      <!-- 文章作者 -->
      <span>作者：{{itemList.user.name}}</span>
      <!-- 文章流量信息 -->
<!--      <span class="glyphicon glyphicon-eye-open">浏览量：</span>-->
<!--      <span class="glyphicon glyphicon-heart">点赞：</span>-->
<!--      <span class="glyphicon glyphicon-comment">评论：</span>-->
    </p>
    <!-- 文章标签 -->
    <p class="post-tag">
<!--      <span class="label label-danger">Python</span>-->
<!--      <span class="label label-warning">OCR</span>-->
<!--      <span class="label label-info">Python模块介绍使用</span>-->
    </p>

    <hr>
    <div class="row">
      <div class="col-md-8">
        <!-- 文章内容 -->
        <div class="content">
          <p>{{itemList.content}}</p>
<!--          <p>安装命令：</p>-->
        </div>
        <hr>
<!--        评论表内容-->
<!--        <ul class="list-group" style="width: 750px;">-->
<!--          <li class="list-group-item">-->
<!--            <h5>User 1：写得不错！</h5>-->
<!--          </li>-->
<!--          <li class="list-group-item">-->
<!--            <h5>User 2：感谢博主！</h5>-->
<!--          </li>-->
<!--          <li class="list-group-item">-->
<!--            <h5>User 3：希望能看到更多像这样的优质文章！</h5>-->
<!--          </li>-->
<!--        </ul>-->
      </div>
      <div class="col-md-4">
        <h4><b>获取更多文章</b></h4>
        <ul class="list-group" v-for="item in articleList" :key="item.id">
          <!-- 最新文章列表 -->
          <li class="list-group-item">
            <a class="nav-item" @click="otherBtn(item)">{{item.title}}</a>
          </li>
        </ul>
      </div>
      <nav class="blog-pagination" aria-label="Pagination">
        <a class="btn btn-outline-primary rounded-pill"  @click="gotoHomeOrManage()">返回</a>
      </nav>
    </div>
<!--    <a class="btn btn-outline-primary rounded-pill"  @click="router.push('/article')">返回</a>-->
  </div>
</template>

<script setup>
//引入生命周期函数
import {onMounted, reactive, ref} from "vue";
//引入路由
import {useRoute} from "vue-router";
import axios from "axios";
import router from "@/router";

//数据初始化
//定义路由
const route = useRoute()
let itemList = ref([])

itemList.value = JSON.parse(route.query.data)
console.log("这是从主页传过来的数据")
console.log(itemList.value)

let uidNum = itemList.value.uid
let articleList = ref([])
let startNum = 0
let recordNum = 5
let aidNum = itemList.value.id
// 根据作者id查询更多文章
const getArticleByAuthorIdData = () =>{
  axios.request({
    method:'POST',
    url:'http://localhost:2223/api/article/articleAuthorId',
    params:{
      startNum: startNum,
      recordNum: recordNum,
      uidNum: uidNum,
    },
  }).then(response => {
    articleList.value = response.data
    // console.log(articleList.value)
  })
  return articleList
}
articleList = getArticleByAuthorIdData()
console.log('根据作者id查询更多文章')
console.log(articleList)
//根据文章id查询文章信息
const getArticleByTitleData = () =>{
  axios.request({
    method:'POST',
    url:'http://localhost:2223/api/article/articleTitle',
    params:{
      aidNum:aidNum,
    },
  }).then(response => {
    itemList.value = response.data[0]
    console.log("根据文章id查询文章信息")
    console.log(itemList.value)
  })
  return itemList
}

const otherBtn = (item) => {
  aidNum = item.id
  itemList = getArticleByTitleData()
}

const gotoHomeOrManage = () => {
  console.log(router.back())
}

</script>

<style scoped>
/* 博客文章信息为灰色 */
.post-info {
  color: gray;
}
.post-info span {
  margin-right: 15px;
}
.header{
  height:100px;
}
.articlediv{
  margin-bottom: -80px;
}
</style>