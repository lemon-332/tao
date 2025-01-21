import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router'

// import VueCookies from 'vue-cookies'

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
// router.beforeEach((to, from, next) => {
//   const userInfo = VueCookies.VueCookies.get('userInfo')
//   if (!userInfo && to.path != '/login') {
//     router.push('/login')
//   }
//   next()
// })
export default router
