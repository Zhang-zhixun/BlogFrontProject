<template>
  <header class="header"></header>
  <div class="container div1" style="margin-top: 50px;">
    <form></form>
    <div class="form-group">
      <label for="title">博客标题<br></label>
      <input type="text" class="form-control" id="title" placeholder="Enter Title" v-model="title">
    </div>
    <div class="form-group">
      <label for="title" >博客分类</label>
      <input type="text" class="form-control" id="title" placeholder="Enter Category" v-model="category">
    </div>
    <div class="form-group">
      <label for="content">博客内容</label>
      <textarea class="form-control" id="content" rows="10" placeholder="请输入博客内容......" v-model="content"></textarea>
    </div>
    <button type="button" class="btn btn-primary" @click="submitBtn()">提交</button>
    <button type="button" class="btn btn-primary">清空</button>
    <button type="button" class="btn btn-primary" @click="router.go(-1)">返回</button>
  </div>
</template>

<script setup>
import router from "@/router";
import {ref} from "vue";
import axios from "axios";
let title = ref("")
let category = ref("")
let content = ref("")
let flag = 0

const getArticleByAddData = () =>{
  axios.request({
    method:'POST',
    url:'http://localhost:2223/api/article/articleInsert',
    params:{
      title:title.value,
      category:category.value,
      content:content.value
    },
  }).then(response =>{
    flag = response.data
    if(flag){
      alert("发布成功")
      router.push('/articleAdd')
      title.value = ""
      category.value = ""
      content.value = ""
    }else{
      alert("发布失败")
    }
  })
}
const submitBtn = () => {
  getArticleByAddData()
  console.log(title.value)
}
</script>

<style scoped>
.header{
  height:50px;
}
.div1{
  margin-bottom: -80px;
}
</style>