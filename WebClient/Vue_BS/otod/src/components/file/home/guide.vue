//功能栏组件
//组件内容未定义
//css未编写完全

<template>
  <div id="guide">
    <nav class="navbar navbar-toggleable-md navbar-light bg-faded">
      <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse div-float" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item active">
             <router-link class="navbar-brand" to="/file/recommend">推荐资源</router-link>
          </li>
          <li class="nav-item active">
             <router-link class="navbar-brand" to="/file/myfiles">我的资源</router-link>
          </li>
          <li class="nav-item active">
             <router-link class="navbar-brand" to="/file/download">下载资源</router-link>
          </li>
          <li class="nav-item active">
             <router-link class="navbar-brand" to="/file/upload">上传资源</router-link>
          </li>
        </ul>
        <form class="form-inline my-2 my-lg-0 form-float">
          <input class="form-control mr-sm-2" type="text" placeholder="搜索" v-model="searchtext">
          <router-link class="btn btn-outline-success my-2 my-sm-0" :to="{ path: '/file/download', query: { search: this.searchtext } }">
            <b>搜索</b>
          </router-link>
        </form>
      </div>
    </nav>
  </div>
</template>

<script>
export default {
  name: 'guide',
  data () {
    return {
      searchtext: ''
    }
  },
  methods: {
  }
}
</script>

<style scoped>
  @import '../../../assets/bootstrap/css/bootstrap.css'
  .div-float{
    position: relative;
    left:3%;
  }
  .form-float{
    position: relative;;
    right:3%;
  }
</style>
