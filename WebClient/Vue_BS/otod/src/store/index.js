import Vue from 'vue'
import Vuex from 'vuex'
import createLogger from 'vuex/dist/logger'

// mutation types
export const LOGIN = 'LOGIN'
export const LOGOUT = 'LOGOUT'

// actions
export const actions = {}

// module
const state = {
  token: null,
  isLogin: false
}


const user = {
  state
}

const getters = {
  user: ({ user }) => user,
  token: ({ user }) => user.token
}

Vue.use(Vuex)
export default new Vuex.Store({
  modules: {
    user
  },
  getters,
  actions,
  plugins: [createLogger()]
})
