<template>
  <header class="header">

  </header>
  <div class="row" style="margin-bottom: -80px;">
    <div class="col"></div>
    <div class="col-10">
      <h3 style="text-align: center;">博客管理</h3>
      <table border="1" class="table table-striped">
        <thead>
        <tr>
          <th>博客id</th>
          <th>博客标题</th>
          <th>博客内容</th>
          <th>博客分类</th>
          <th>博客创建时间</th>
          <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="item in articleList" :key="item.id">
          <td>{{item.id}}</td>
          <td>{{item.title}}</td>
          <td>{{item.content}}</td>
          <td>{{item.category}}</td>
          <td>{{item.creat}}</td>
          <td>
            <div class="form-group" style="float:left;display:flex;width:100%">
              <a href="#" @click="gotoArticleUpdate(item)"><input class="form-control"  type="button" value="更新"/></a>
              <a href="#" @click="deleteBtn(item)"><input class="form-control"  type="button" value="删除"/></a>
              <a href="#" @click="gotoArticleDetail(item)"><input class="form-control"  type="button" value="查看"/></a>
            </div>
          </td>
        </tr>
        </tbody>
      </table>

      <nav class="blog-pagination" aria-label="Pagination">
        <a class="btn btn-outline-primary rounded-pill"  @click="subCount()">上一页</a>
        <a class="btn btn-outline-primary rounded-pill" @click="addCount()">下一页</a>
        <a class="btn btn-outline-primary rounded-pill"  @click="router.push('/article')">返回</a>
      </nav>
    </div>
    <div class="col"></div>
  </div>
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

//向上翻页
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

//向下翻页
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
//跳转到文章修改页面
const gotoArticleUpdate = (item) => {
  router.push({
    //指定跳转目标页面
    path:'/articleUpdate',
    //传递的参数
    query:{
      data:JSON.stringify(item)
    }
  })
}
//实现博客删除功能
let flag = 0
let aidNum = 0
const getArticleByDeleteData = () =>{
  axios.request({
    method:'POST',
    url:'http://localhost:2223/api/article/articleDelete',
    params:{
      aidNum:aidNum,
    },
  }).then(response =>{
    flag = response.data
    if(flag){
      alert("删除成功")
      articleList = getData(startNum,recordNum)
    }else{
      alert("删除失败")
    }
  })
}
const deleteBtn = (item) => {
  aidNum = item.id
  console.log(item.id)
  getArticleByDeleteData()
}

</script>

<style scoped>
.header{
  margin-top: 100px;
}
.table {
  table-layout:fixed;
  word-break:break-all;
}
.table td:hover{
  overflow: visible;
  white-space: normal;
}
.table td {
  word-wrap: break-word;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
</style>