import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/pages/Index'
import Login from '@/pages/Login'
import Service from '@/pages/Service'
import store from '../store'

Vue.use(Router)

const routes = [
  {
    path: '/service',
    name: 'Service',
    component: Service
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/',
    name: 'Index',
    meta: {
      requireAuth: true
    },
    component: Index
  }
]

const routerObj = new Router({
  routes,
  mode: 'history'
})

// 跳转检测是否需要验证
routerObj.beforeEach((to, from, next) => {
  // 从store获取token
  let token = store.state.token

  // 当需要验证且无token时候
  if (to.meta.requireAuth && (token === null)) {
    alert('Auth:' + to.requireAuth + ',token:' + token)

    next({
      path: '/login',
      query: {redirect: to.fullPath}
    })
  } else {
    // 无需验证
    next()
  }
})

export default routerObj
