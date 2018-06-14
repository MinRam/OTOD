import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/pages/Login'
import ForumTopic from '@/pages/ForumTopic'
import ForumReply from '@/pages/ForumReply'
// index pages
import Index from '@/pages/Index'

import Home from '@/pages/Home'
import Person from '@/components/Person'
import Setting from '@/components/Setting'

import Blog from '@/pages/Blog'
// import BlogReply from '@/pages/BlogReply'
import Service from '@/pages/Service'
import Book from '@/pages/Book'
// file pages
import File from '@/pages/File'
import download from '@/pages/file/download/download.vue'
import upload from '@/pages/file/upload/upload.vue'
import recommend from '@/pages/file/recommend/recommend.vue'
import myfiles from '@/pages/file/myfiles/myfiles.vue'
import files from '@/pages/file/files/files.vue'
import filelist from '@/pages/file/filelist/filelist.vue'
import mydownload from '@/components/file/myfiles/mydownload.vue'
import myupload from '@/components/file/myfiles/myupload.vue'
import mycollection from '@/components/file/myfiles/mycollection.vue'
// market pages
import Market from '@/pages/MarketList'
import Product from '@/pages/Product'
import Sell from '@/pages/Sell'
import Payfor from '@/pages/payfor'
// import store from '../store'
// server pages
import OrderList from '@/components/OrderList'
import PublishOrder from '@/components/PublishOrder'
import QuillEditor from '@/components/quillEditor'
import AllOrder from '@/components/AllOrder'
import RunningS from '@/components/Runnings'
import WaitingRequest from '@/components/WaitingRequest'

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
        component: Home,
        children: [
          {
            path: '/home/person',
            name: 'Person',
            meta: {
              requireAuth: true
            },
            component: Person
          }
        ]
      }, {
        path: '/blog',
        name: 'Blog',
        meta: {
          requireAuth: true
        },
        component: Blog,
        children: [
          {
            path: '/forumtopic/quilleditor',
            name: 'QuillEditor',
            meta: {
              requireAuth: false
            },
            component: QuillEditor
          }
        ]
      }, {

        path: '/service',
        name: 'Service',
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
          },
          {
            path: '/service/allorder',
            name: 'AllOrder',
            meta: {
              requireAuth: false
            },
            component: AllOrder
          },
          {
            path: '/service/waitingrequest',
            name: 'WaitingRequest',
            meta: {
              requireAuth: false
            },
            component: WaitingRequest
          },
          {
            path: '/service/runnings',
            name: 'RunningS',
            meta: {
              requireAuth: false
            },
            component: RunningS
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
        component: File,
        children: [{
          path: '',
          redirect: '/file/recommend'
        },
        {
          path: '/file/recommend',
          component: recommend
        },
        {
          path: '/file/upload',
          component: upload
        },
        {
          path: '/file/download',
          component: download
        },
        {
          path: '/file/files',
          component: files
        },
        {
          path: '/file/filelist',
          component: filelist
        },
        {
          path: '/file/myfiles',
          component: myfiles,
          children: [{
            path: '',
            redirect: '/file/myfiles/mycollection'
          },
          {
            path: '/file/myfiles/mydownload',
            component: mydownload
          },
          {
            path: '/file/myfiles/myupload',
            component: myupload
          },
          {
            path: '/file/myfiles/mycollection',
            component: mycollection
          }]
        }]
      }, {
        path: '/market',
        name: 'Market',
        meta: {
          requireAuth: false
        },
        component: Market
      }, {
        path: '/market/product',
        name: 'Product',
        meta: {
          requireAuth: false
        },
        component: Product
      }, {
        path: '/market/sell',
        name: 'Sell',
        meta: {
          requireAuth: false
        },
        component: Sell
      }, {
        path: '/market/product/pay',
        name: 'payfor',
        meta: {
          requireAuth: false
        },
        component: Payfor
      }, {
        path: '/user/setting',
        name: 'Setting',
        meta: {
          requireAuth: true
        },
        component: Setting
      }
    ]
  }, {
    path: '/forumtopic',
    name: 'ForumTopic',
    meta: {
      requireAuth: false
    },
    component: ForumTopic,
    children: [
      {
        path: '/forumtopic/quilleditor',
        name: 'QuillEditor',
        meta: {
          requireAuth: false
        },
        component: QuillEditor
      }
    ]
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
