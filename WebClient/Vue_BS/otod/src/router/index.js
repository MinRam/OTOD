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
// import Service from '@/pages/Service'
import Service from '@/pages/Service'
import Book from '@/pages/Book'
import File from '@/pages/File'
// market pages
import Market from '@/pages/MarketList'
// import store from '../store'
// server pages
import OrderList from '@/components/OrderList'
import PublishOrder from '@/components/PublishOrder'


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
        path: '/blogreply',
        name: 'BlogReply',
        meta: {
          requireAuth: false
        },
        component: BlogReply
      }, {
        path: '/market',
        name: 'Market',
        meta: {
          requireAuth: false
        },
        component: Market,
        children: [
          {
            path: '/market/product',
            name: 'Product',
            meta: {
              requireAuth: false
            }
          }
        ]
      }, {
        path: '/service',
        name: 'Service',
>>>>>>> 7eaf43da13703632a35218c5867fad7d4afa5581
        meta: {
          requireAuth: false
        },
        component: Service,
        children: [
          {
            path: '/service/orderlist',
            name: 'OrderList',
            meta: {
              requireAuth: false
            },
            component: OrderList
          },
          {
            path: '/service/publishorder',
            name: 'PublishOrder',
            meta: {
              requireAuth: false
            },
            component: PublishOrder
          }
        ]
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
      requireAuth: false
    },
    component: ForumTopic
  }, {
    path: '/forumreply',
    name: 'ForumReply',
    meta: {
      requireAuth: false
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
