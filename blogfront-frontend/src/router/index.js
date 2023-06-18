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
              name: 'blogIndex-main',
              component: () => import('@/components/blogIndexCom/blogMain.vue'),
              meta: {
                  title : '官网首页'
              }
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
