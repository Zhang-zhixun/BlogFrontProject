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
    }

  ]
})

//路由守卫
router.beforeEach((to, from, next) => {
  const store = useStore()
  next()

})

export default router
