import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)
export default new Vuex.Store({
  state: {
    isLogin: false,
    nickname: 'name',
    headImage: '',
    telephone: ''
  },
  mutations: {
    userSignIn (state) {
      state.isLogin = true
    },
    userSignOut (state) {
      state.isLogin = false
    },
    initialName (state, name) {
      state.nickname = name
    },
    initialHead (state, head) {
      state.headImage = head
    },
    initialTel (state, tel) {
      state.telephone = tel
    }
  }
})
