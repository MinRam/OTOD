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
  }
}
