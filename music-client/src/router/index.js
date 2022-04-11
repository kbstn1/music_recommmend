import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '*',
    redirect: '/404'
  },
  {
    path: '/',
    name: 'default',
    redirect: '/home'
  },
  {
    path: '/home',
    name: 'home',
    component: () => import('views/Home')
  },
  {
    path: '/404',
    component: () => import('views/404.vue')
  },
  {
    path: '/login-in',
    name: 'login-in',
    component: () => import('views/Login'),
  },
  {
    path: '/sign-up',
    name: 'sign-up',
    component: () => import('views/SignUp')
  },
  {
    path: '/hot-list',
    name: 'hot-list',
    component: () => import('views/HotList')
  },
  {
    path: '/my-music',
    name: 'my-music',
    component: () => import('views/MyMusic')
  },
  {
    path: '/rank-list',
    name: 'rank-list',
    component: () => import('views/RankList')
  },
  {
    path: '/setting',
    name: 'setting',
    component: () => import('views/Setting')
  },
  {
    path: '/search',
    name: 'search',
    component: () => import('views/Search')
  },
  {
    path: '/detail/:id',
    name: 'detail',
    component: () => import('views/Detail')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
