import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Login from '@/pages/Login'
import store from '../store'

Vue.use(Router)

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/',
    name: 'HelloWorld',
    meta: {
      requireAuth: true
    },
    component: HelloWorld
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
  if (to.requireAuth && (token === null)) {
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
