<template>
  <header class="header"></header>
  <div class="container div1" style="margin-top: 36px;">
    <form>
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
      <button type="button" class="btn btn-primary" @click="submitBtn()">更新</button>
      <button type="reset" class="btn btn-primary">清空</button>
      <button type="button" class="btn btn-primary" @click="router.push('articleManagement')">返回</button>
    </form>
  </div>
</template>

<script setup>
import router from "@/router";
import {ref} from "vue";
import axios from "axios";
import {useRoute} from "vue-router";
//数据初始化
//定义路由
const route = useRoute()
let itemList = ref([])
itemList.value = JSON.parse(route.query.data)
console.log("这是从主页传过来的数据")
console.log(itemList.value)
if(!itemList){
  router.push('/articleManagement')
}

let aidNum = itemList.value.id
let title = ref("")
let category = ref("")
let content = ref("")
let flag = 0

title = itemList.value.title
category = itemList.value.category
content = itemList.value.content

//实现博客修改功能
const getArticleByUpdateData = () =>{
  axios.request({
    method:'POST',
    url:'http://localhost:2223/api/article/articleUpdate',
    params:{
      aidNum:aidNum,
      title:title,
      category:category,
      content:content
    },
  }).then(response =>{
    flag = response.data
    if(flag){
      alert("更新成功")
      router.push('/articleUpdate')
      // title.value = ""
      // category.value = ""
      // content.value = ""
    }else{
      alert("更新失败")
    }
  })
}
const submitBtn = () => {
  if(aidNum && title && category && content){
    getArticleByUpdateData()
  }else{
    alert('请输入标题、分类和内容')
    // if(!aidNum){
    //   alert('')
    // }else if(!title){
    //   alert('请输入标题')
    // }else if(!category){
    //   alert('请输入分类')
    // }else if(!content){
    //   alert('请输入内容')
    // }else if(!title && !category){
    //   alert('请输入标题和分类')
    // }
  }
}
</script>

<style scoped>
.header{
  height:60px;
}
.div1{
  margin-bottom: -110px;
}
</style>