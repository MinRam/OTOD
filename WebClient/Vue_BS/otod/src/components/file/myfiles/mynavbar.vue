<template>
  <div id="mynavbar">
    <nav class="navbar navbar-toggleable-md navbar-light bg-faded">
      <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse div-float" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item active">
           <router-link class="navbar-brand" to='/file/myfiles/mycollection'>我的收藏</router-link>
          </li>
          <li class="nav-item active">
           <router-link class="navbar-brand" to='/file/myfiles/myupload'>我的上传</router-link>
          </li>
          <li class="nav-item active">
           <router-link class="navbar-brand" to="/file/myfiles/mydownload">我的下载</router-link>
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
    return {}
  },
  created () {
  }
}
</script>

<style scoped>
  @import '../../../assets/bootstrap/css/bootstrap.css'
</style>
