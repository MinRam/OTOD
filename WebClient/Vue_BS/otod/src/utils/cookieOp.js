export default{
  install (Vue, options) {
    Vue.prototype.$setCookie = function (name, value) {
      document.cookie = name + '=' + value + '; Path=/;'
    }
    Vue.prototype.$getCookie = function (name) {
      var value = '; ' + document.cookie
      var parts = value.split('; ' + name + '=')
      if (parts.length === 2) return parts.pop().split(';').shift()
    }
    Vue.prototype.$UserRouter = function (nickname) {
      console.log(nickname)
    }
    Vue.prototype.$clearCookie = function clearCookie (name) {
      var exp = new Date()
      exp.setTime(exp.getTime() - 1)
      var cval = this.$getCookie(name)
      if (cval != null) {
        document.cookie = name + '=' + cval + ';expires=' + exp.toGMTString() + ';Path=/;'
      }
    }
  }
}
