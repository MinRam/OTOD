import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import Login from '@/pages/Login'
import store from '../store'

Vue.use(Router)

const routes = [
  {
    path: '/',
    name: 'HelloWorld',
    meta: {
      requireAuth: true
    },
    component: HelloWorld
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  }

]

const routerObj = new Router({
  routes,
  mode: 'history'
})


routerObj.beforeEach((to, from, next) => {
    //  let token = store.state.token;

})
export default routerObj
