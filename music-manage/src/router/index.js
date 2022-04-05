import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/home',
    component: () => import('components/Home.vue'),
    children: [
      {
        path: 'consumer',
        component: () => import('views/Consumer.vue')
      },
      {
        path: 'song',
        component: () => import('views/Song.vue')
      },
      {
        path: 'collect',
        component: () => import('views/Collect.vue')
      },
      {
        path: 'comment',
        component: () => import('views/Comment.vue')
      }
    ]
  },
  {
    path: '/',
    component: () => import('views/Login.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
