<template>

  <nav class="navbar fixed-top nav-Header shadow-sm">

    <div class="container-fluid">
      <ul class="nav justify-content-end">
        <li class="nav-item">
          <img src="public/logo.png" style="width: 75px">
        </li>
        <li class="nav-item" style="margin-top: 15px">
          <a class="navbar-brand nav-link" href="#" style="font-weight: bold"><span style="color: blue;font-size:26px">行太</span>不面对</a>
        </li>
      </ul>

      <ul class="nav justify-content-end">
        <li class="nav-item">
          <a class="nav-link" aria-current="page" href="#">首页</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" aria-current="page" href="#">资源</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">视频</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">文档</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">博客</a>
        </li>
        <li class="nav-item">
          <el-avatar style="margin-top:-5px;margin-right:5px" v-if="store.auth.user!=null"
              src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"
          />
        </li>
        <li>
          <el-button  v-if="store.auth.user==null" @click="router.push('/login')" style="width:80px;height:35px;margin-right: 15px">登录</el-button>
          <el-button  v-if="store.auth.user!=null" @click="router.push('/login')" style="width:80px;height:35px;margin-right: 15px">切换账号</el-button>
        </li>
        <li>
          <button class="button-Menu navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasNavbar" aria-controls="offcanvasNavbar">
            <el-icon><Setting /></el-icon>
          </button>
        </li>
      </ul>



      <!--侧边隐藏导航栏-->
      <div class="offcanvas offcanvas-end" tabindex="-1" id="offcanvasNavbar" aria-labelledby="offcanvasNavbarLabel">
        <div class="offcanvas-header">
          <h5 class="offcanvas-title" id="offcanvasNavbarLabel">Offcanvas</h5>
          <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
        </div>
        <div class="offcanvas-body">
          <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="#">Home</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="#">Link</a>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                Dropdown
              </a>
              <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="#">Action</a></li>
                <li><a class="dropdown-item" href="#">Another action</a></li>
                <li>
                  <hr class="dropdown-divider">
                </li>
                <li><a class="dropdown-item" href="#">Something else here</a></li>
              </ul>
            </li>
          </ul>
          <form class="d-flex mt-3" role="search">
            <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success" type="submit">Search</button>
          </form>
        </div>
      </div>
    </div>
  </nav>

</template>

<script  setup>
import router from "@/router";
import {useStore} from "@/stores";
const store = useStore()
</script>



<style scoped>

.p-2{
  color: #181818;
  text-decoration: none;
}


.button-Menu{
  border: 0;
  width: 80px;
  opacity:0.8;
  text-align:center;
  text-decoration:none;
  text-transform:uppercase;
  font-size:24px;
  color:#fff;
  background:linear-gradient(to right,#03a9f4,#f441a5,#ffeb3b,#09a8f4);
  background-size: 400%;
  z-index:-1;

}

.button-Menu::before{
  content:'';
  top:-5px;
  left:-5px;
  bottom:-5px;
  right:-5px;
  z-index:-1;
  filter:blur(20px);
}

.button-Menu:hover{
  animation:streamer 8s infinite
}

.button-Menu:hover::before{
  animation:streamer 8s infinite
}

@keyframes streamer {
  100%{
    background-position: -400% 0;
  }
}



.nav-Header{
  height: 80px;
  background: white;
}
</style>