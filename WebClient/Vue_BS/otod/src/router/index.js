import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/pages/Login'
import ForumTopic from '@/pages/ForumTopic'
import ForumReply from '@/pages/ForumReply'
// index pages
import Index from '@/pages/Index'
import Home from '@/pages/Home'
import Person from '@/components/Person'
import Notice from '@/components/Notice'
import Setting from '@/components/Setting'

import Blog from '@/pages/Blog'
import BlogReply from '@/pages/BlogReply'
import Service from '@/pages/Service'
import Book from '@/pages/Book'
// file pages
import File from '@/pages/File'
import download from '@/pages/file/download/download.vue'
import FileUpload from '@/pages/file/upload/upload.vue'
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
import OrderList from '@/components/service/OrderList'
import PublishOrder from '@/components/service/PublishOrder'
import AllOrder from '@/components/service/AllOrder'
import RunningS from '@/components/service/Runnings'
import WaitingRequest from '@/components/service/WaitingRequest'
import RMyAllOrders from '@/components/service/RMyAllOrders'
import RRecivedOrders from '@/components/service/RRecivedOrders'
import RWaitingCommentOrders from '@/components/service/RWaitingCommentOrders'
import RDoneOrders from '@/components/service/RDoneOrders'
import RFailedOrders from '@/components/service/RFailedOrders'
import WaitingEvaluateS from '@/components/service/WaitingEvaluateS'
import FailedOrderS from '@/components/service/FailedOrderS'
import FinishedOrderS from '@/components/service/FinishedOrderS'
import Gouwuche from '@/components/book/Gouwuche'
import Bookguanli from '@/components/book/Bookguanli'

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
        children: [{
          path: '/home/person',
          name: 'Person',
          meta: {
            requireAuth: true
          },
          component: Person
        }, {
          path: '/home/notice',
          name: 'Notice',
          meta: {
            requireAuth: true
          },
          component: Notice
        }]
      }, {
        path: '/blog',
        name: 'Blog',
        meta: {
          requireAuth: true
        },
        component: Blog
      }, {
        path: '/blogreply',
        name: 'BlogReply',
        meta: {
          requireAuth: true
        },
        component: BlogReply
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
          },
          {
            path: '/service/waitingevaluates',
            name: 'WaitingEvaluateS',
            meta: {
              requireAuth: false
            },
            component: WaitingEvaluateS
          },
          {
            path: '/service/finishedorders',
            name: 'FinishedOrderS',
            meta: {
              requireAuth: false
            },
            component: FinishedOrderS
          },
          {
            path: '/service/failedorders',
            name: 'FailedOrderS',
            meta: {
              requireAuth: false
            },
            component: FailedOrderS
          },
          {
            path: '/service/RMyAllOrders',
            name: 'RMyAllOrders',
            meta: {
              requireAuth: false
            },
            component: RMyAllOrders
          },
          {
            path: '/service/RRecivedOrders',
            name: 'RRecivedOrders',
            meta: {
              requireAuth: false
            },
            component: RRecivedOrders
          },
          {
            path: '/service/RWaitingCommentOrders',
            name: 'RWaitingCommentOrders',
            meta: {
              requireAuth: false
            },
            component: RWaitingCommentOrders
          },
          {
            path: '/service/RDoneOrders',
            name: 'RDoneOrders',
            meta: {
              requireAuth: false
            },
            component: RDoneOrders
          },
          {
            path: '/service/rfailedorders',
            name: 'RFailedOrders',
            meta: {
              requireAuth: false
            },
            component: RFailedOrders
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
        path: '/service/Gouwuche',
        name: 'Gouwuche',
        meta: {
          requireAuth: false
        },
        component: Gouwuche
      }, {
        path: '/service/bookguanli',
        name: 'Bookguanli',
        meta: {
          requireAuth: false
        },
        component: Bookguanli
      }, {
        path: '/file',
        name: 'File',
        meta: {
          requireAuth: false
        },
        redirect: '/file/recommend',
        component: File,
        children: [{
          path: '/file/recommend',
          component: recommend
        },
        {
          path: '/file/upload',
          name: 'FileUpload',
          component: FileUpload
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
