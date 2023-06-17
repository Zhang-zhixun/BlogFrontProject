import { createApp } from 'vue'
import { createPinia } from 'pinia'
import 'element-plus/dist/index.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import App from './App.vue'
import router from './router'
import axios from "axios";
import $ from 'jquery';
import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.min.js";
import 'animate.css/animate.min.css';
import AOS from 'aos'
import 'aos/dist/aos.css'


const app = createApp(App)
//您需要从 @element-plus/icons-vue 中导入所有图标并进行全局注册。
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}

app.AOS = new AOS.init({ disable: "phone" });


//后端默认服务器地址
axios.defaults.baseURL = 'http://localhost:8080'
app.use(createPinia())
app.use(router)
app.use($)
app.use(AOS)
app.mount('#app')
