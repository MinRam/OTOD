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
          <li class="nav-item active" v-for="brand in brands" :key="brand.index">
             <router-link :class="{'navbar-brand-focus': index == brand.index}" class="navbar-brand" :to="brand.path" @click.native="changeindex(brand.index)">{{brand.name}}</router-link>
          </li>
        </ul>
        <form class="form-inline my-2 my-lg-0 form-float">
          <input class="form-control mr-sm-2" type="text" placeholder="搜索" v-model="searchtext">
          <router-link class="btn btn-outline-success my-2 my-sm-0" :to="{ path: '/file/download', query: { search: this.searchtext } }" @click.native="strnull()">
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
      searchtext: '',
      index: 0,
      brands: [{
        path: '/file/recommend',
        name: '推荐资源',
        index: 1
      }, {
        path: '/file/myfiles',
        name: '我的资源',
        index: 2
      }, {
        path: '/file/download',
        name: '下载资源',
        index: 3
      }, {
        path: '/file/upload',
        name: '上传资源',
        index: 4
      }]
    }
  },
  methods: {
    strnull () {
      this.searchtext = ''
      this.searchindex()
    },
    changeindex (index) {
      this.index = index
    },
    searchindex () {
      this.index = 3
    }
  }
}
</script>

<style scoped>
  @import '../../../assets/css/bootstrap.css'
  .div-float{
    position: relative;
    left:3%;
  }
  .form-float{
    position: relative;;
  }
  .navbar-brand-focus{
    text-decoration: none;
    border-bottom:3px solid blue; /* #ccc换成链接的颜色 */
    padding-bottom:3px;  /*这里设置你要空的距离*/
  }
</style>
