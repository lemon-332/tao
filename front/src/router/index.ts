import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router'

import { useCookies } from 'vue3-cookies'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'home',
    component: () => import('../views/home/index.vue'),
    children: [
      {
        path: '/about',
        name: 'about',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () =>
          import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
      }
    ]
  },
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/login/index.vue')
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})
router.beforeEach((to, from, next) => {
  const { cookies } = useCookies()
  const userInfo = cookies.get('userInfo')
  if (!userInfo && to.path != '/login') {
    router.push('/login')
  }
  if (userInfo && to.path == '/login') {
    router.push(from.path)
  }
  next()
})
export default router
