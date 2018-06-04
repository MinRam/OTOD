import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/pages/Index'
import Login from '@/pages/Login'
<<<<<<< HEAD
import ForumTopic from '@/pages/ForumTopic'
import ForumReply from '@/pages/ForumReply'
import store from '../store'
=======
import Home from '@/pages/Home'
// import store from '../store'
>>>>>>> c83f4c02856ee65c270322180d10b79cf4214912

Vue.use(Router)

const routes = [
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
    component: Index,
    children: [
      {
        path: '/home',
        name: 'Home',
        meta: {
          requireAuth: true
        },
        component: Home
      }
    ]
  }, {
    path:'/forumtopic',
    name:'ForumTopic',
    meta:{

    },
    component:ForumTopic
  }, {
    path:'/forumreply',
    name:'ForumReply',
    meta:{

    },
    component:ForumReply
  }
]

const routerObj = new Router({
  routes,
  mode: 'history'
})

// 跳转检测是否需要验证
routerObj.beforeEach((to, from, next) => {
  // 从store获取token
  var token = '123'
  console.log('router')
  // 当需要验证且无token时候
  if (to.meta.requireAuth && (token === null)) {
    alert('Auth:' + to.requireAuth + ',token:' + token)

    next('/login')
  } else {
    // 无需验证
    next()
  }
})

export default routerObj
