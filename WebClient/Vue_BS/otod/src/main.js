// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Vuex from 'vuex'
import App from './App'
import router from './router'
import cookieOpe from './utils/cookieOp'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import store from './store/index.js'
import VueQuillEditor from 'vue-quill-editor'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'

// 采用axios代替ajax
import axios from 'axios'

Vue.use(Vuex)
Vue.use(ElementUI)
Vue.use(cookieOpe)
Vue.use(VueQuillEditor)

Vue.prototype.$imageUrl = 'http://localhost:8081/images/'
Vue.prototype.$url = 'http://localhost:8081'
Vue.prototype.$axios = axios
Vue.config.productionTip = false


// // http request 拦截器
// axios.interceptors.request.use(
//   config => {
//     // 判断是否存在token，如果存在的话，则每个http header都加上token
//     if (store.state.token) {
//       config.headers.Authorization = `token ${store.state.token}`
//     }
//     return config
//   },
//   err => {
//     return Promise.reject(err)
//   }
// )

// // http response 拦截器
// axios.interceptors.response.use(
//   response => {
//     return response
//   },
//   error => {
//     if (error.response) {
//       switch (error.response.status) {
//         case 401:
//         // 返回 401 清除token信息并跳转到登录页面
//           store.commit(types.LOGOUT)
//           router.replace({
//             path: 'login',
//             query: {redirect: router.currentRoute.fullPath}
//           })
//       }
//     }
//     // 返回接口返回的错误信息
//     return Promise.reject(error.response.data)
//   }
// )

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
