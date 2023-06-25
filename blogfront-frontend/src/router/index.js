import {createRouter, createWebHistory} from 'vue-router'
import {useStore} from '@/stores';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
   {
      path:'/',
      name:"blogIndex",
      component: () => import('@/views/blogIndex.vue'),
      children: [
          {
              path: '/',
              name: 'blog-index',
              component: () => import('@/components/blogIndexCom/blogMain.vue'),
              meta: {
                  title : '博客首页'
              }
          },
          {
              path: '/resources',
              name: 'blog-Resources',
              component: () => import('@/components/blogIndexCom/blogResources.vue'),
              meta: {
                  title : '资源首页'
              }
          },{
              path: '/article',
              name: 'blog-Article',
              component: () => import('@/components/blogIndexCom/blogArticle.vue'),
              meta: {
                  title : '博客内容'
              },
              props:true
          },{
              path: '/articleDetail',
              name: 'blog-Article-Detail',
              component: () => import('@/components/blogIndexCom/blogArticleDetail.vue'),
              meta: {
                  title : '博客详情页'
              },
              props:true
          },{
              path: '/articleAdd',
              name: 'blog-Article-Add',
              component: () => import('@/components/blogIndexCom/blogArticleAdd.vue'),
              meta: {
                  title : '博客添加'
              },
              props:true
          },
      ]
    },
      {
          path: '/login',
          name: "login",
          component: () => import('@/views/Login.vue'),
          children: [
              {
                  path: '/login',
                  name: 'blog-login',
                  component: () => import('@/components/LoginCom/LoginPage.vue')
              },
              {
                  path: 'register',
                  name: 'blog-register',
                  component: () => import('@/components/LoginCom/RegisterPage.vue')
              },
              {
                  path: 'forget',
                  name: 'blog-forget',
                  component: () => import('@/components/LoginCom/ForgetPage.vue')
              }
          ]
      },

  ]
})

//路由守卫
router.beforeEach((to, from, next) => {
  const store = useStore()
  next()

})

export default router
