import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router'

import { useCookies } from 'vue3-cookies'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'home',
    component: () => import('../views/home/index.vue'),
    children: [
      {
        path: '/user',
        name: 'user',
        meta: {
          title: '用户管理'
        },
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import('../views/user/index.vue'),
        children: [
          {
            path: '/user/userList',
            name: 'userList',
            meta: {
              title: '用户列表',
              index: '1-1'
            },
            component: () => import('../views/user/userList/index.vue')
          },
          {
            path: '/user/activityLog',
            name: 'activityLog',
            meta: {
              title: '活动日志',
              index: '1-2'
            },
            component: () => import('../views/user/activityLog/index.vue')
          }
        ]
      },
      {
        path: '/god',
        name: 'god',
        meta: {
          title: '商品管理'
        },
        component: () => import('../views/god/index.vue'),
        children: [
          {
            path: '/god/godList',
            name: 'godList',
            meta: {
              title: '商品列表',
              index: '2-1'
            },
            component: () => import('../views/god/godList/index.vue')
          },
          {
            path: '/god/godReport',
            name: 'godReport',
            meta: {
              title: '报告与分析',
              index: '2-2'
            },
            component: () => import('../views/god/report/index.vue')
          }
        ]
      },
      {
        path: '/seller',
        name: 'seller',
        meta: {
          title: '商家管理'
        },
        component: () => import('../views/seller/index.vue'),
        children: [
          {
            path: '/seller/sellerList',
            name: 'sellerList',
            meta: {
              title: '商家列表',
              index: '3-1'
            },
            component: () => import('../views/seller/sellerList/index.vue')
          },

          {
            path: '/seller/sellerRegister',
            name: 'sellerRegister',
            meta: {
              title: '注册与审核',
              index: '3-2'
            },
            component: () => import('../views/seller/sellerRegister/index.vue')
          }
        ]
      },
      {
        path: '/cart',
        name: 'cart',
        meta: {
          title: '订单管理'
        },
        component: () => import('../views/cart/index.vue'),
        children: [
          {
            path: '/cart/cartList',
            name: 'cartList',
            meta: {
              title: '购物车列表',
              index: '4-1'
            },
            component: () => import('../views/cart/cartList/index.vue')
          }
        ]
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
