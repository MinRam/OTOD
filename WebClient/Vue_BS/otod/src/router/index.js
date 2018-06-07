import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/pages/Login'
import ForumTopic from '@/pages/ForumTopic'
import ForumReply from '@/pages/ForumReply'
// index pages
import Index from '@/pages/Index'
import Home from '@/pages/Home'
import Blog from '@/pages/Blog'
import Shop from '@/pages/Shop'
import Service from '@/pages/Service'
import Book from '@/pages/Book'
import File from '@/pages/File'
// import store from '../store'

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
      }, {
        path: '/blog',
        name: 'Blog',
        meta: {
          requireAuth: false
        },
        component: Blog
      }, {
        path: '/shop',
        name: 'Shop',
        meta: {
          requireAuth: false
        },
        component: Shop
      }, {
        path: '/service',
        name: 'Service',
        meta: {
          requireAuth: false
        },
        component: Service
      }, {
        path: '/book',
        name: 'Book',
        meta: {
          requireAuth: false
        },
        component: Book
      }, {
        path: '/file',
        name: 'File',
        meta: {
          requireAuth: false
        },
        component: File
      }
    ]
  }, {
    path: '/forumtopic',
    name: 'ForumTopic',
    meta: {
    },
    component: ForumTopic
  }, {
    path: '/forumreply',
    name: 'ForumReply',
    meta: {
    },
    component: ForumReply
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
