<template>
  <div class="otod-home">
    <div class="otod-content">
        <div class="main">
            <div class="publish-Bar">
                <ul class="publish-navs">
                    <li class="user-icon">
                        <a href="/">
                            <img :src="userInfo.headPhoto"/>
                         </a>
                     </li>
                    <li class="publish-link" v-for="(publish,index) in publishPosts" :key="index">
                        <a href="">{{publish.title}}</a>
                     </li>
                 </ul>
             </div>
            <div class="notice-tips"></div>
            <div class="m-mlist"></div>
         </div>
        <div class="reside">
            <div class="user-box">
                <div class="user-menu">
                    <ul>
                        <li class="first large">
                            <a href="/" class="menu-item username">
                                <span class="txt">
                                    <span class="lg1">{{userInfo.username}}</span>
                                    <span class="lg2">{{userInfo.telephone}}</span>
                                </span>
                             </a>
                         </li>
                        <li v-for="(menu,index) in menuList" :key="index" class="small">
                            <a class="menu-item">
                                <span class="txt">{{menu.title}}</span>
                                <span class="inf">{{menu.number}}</span>
                             </a>
                         </li>
                        <li>
                            <span>个人主页设置</span>
                         </li>
                     </ul>
                 </div>
             </div>
         </div>
     </div>
  </div>
</template>

<script>
export default {
  name: 'Home',
  data () {
    return {
      // 发布按钮
      publishPosts: [{
        title: 'Blog'
      }, {
        title: 'Shop'
      }, {
        title: 'Service'
      }, {
        title: 'Book'
      }, {
        title: 'File'
      }],

      // menu
      menuList: [{
        title: '文章',
        number: 1
      }, {
        title: '喜欢',
        number: 16
      }, {
        title: '粉丝',
        number: 9
      }, {
        title: '关注',
        number: 9
      }, {
        title: '通知',
        number: 0
      }, {
        title: '私信',
        number: 0
      }],

      userInfo: {
        headPhoto: 'http://localhost:8081/images/6630576284002729390.jpg',
        username: '祎隋',
        telephone: 'Tel:15005933722'
      }
    }
  },
  mounted () {
    // 键盘监听注册
    document.onkeydown = this.keyDownEvent

    // 滚轮监听注册
    window.onmousewheel = document.onmousewheel = this.handleScroll
    window.addEventListener('mouseWheel', this.handleScroll, false)
    window.addEventListener('DOMMouseScroll', this.handleScroll, false)
  },
  methods: {
    // 发布点击
    publishNavsclick () {

    }
  }
}
</script>

<style>
    @import '../assets/css/homePage.css'
</style>

