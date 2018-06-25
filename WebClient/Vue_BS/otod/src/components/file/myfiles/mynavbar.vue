<template>
  <div id="mynavbar">
    <nav class="navbar navbar-toggleable-md navbar-light bg-faded">
      <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item active" v-for="brand in brands" :key="brand.index">
             <router-link :class="{'navbar-brand-focus': index == brand.index}" class="navbar-brand" :to="brand.path" @click.native="changeindex(brand.index)">{{brand.name}}</router-link>
          </li>
        </ul>
      </div>
    </nav>
  </div>
</template>

<script>
export default {
  name: 'mynavbar',
  data () {
    return {
      index: 0,
      brands: [{
        path: '/file/myfiles/mycollection',
        name: '我的收藏',
        index: 1
      }, {
        path: '/file/myfiles/myupload',
        name: '我的上传',
        index: 2
      }, {
        path: '/file/myfiles/mydownload',
        name: '我的下载',
        index: 3
      }]
    }
  },
  methods: {
    changeindex (index) {
      this.index = index
    }
  }
}
</script>

<style scoped>
  @import '../../../assets/css/bootstrap.css'
  .navbar-brand-focus{
    text-decoration: none;
    border-bottom:3px solid blue; /* #ccc换成链接的颜色 */
    padding-bottom:2px;  /*这里设置你要空的距离*/
  }
</style>
