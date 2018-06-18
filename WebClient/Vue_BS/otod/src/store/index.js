import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)
export default new Vuex.Store({
  state: {
    isLogin: false,
    nickname: '',
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
    },
    loginOut (state) {
      state.isLogin = false
      state.nickname = ''
      state.headImage = ''
      state.telephone = ''
    }
  }
})
