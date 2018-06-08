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
            <div class="notice-tips" v-if="noticeList.length > 0">
                <div class="notice-list">
                    <div class="notice-head">
                        <h3>通知</h3>
                        <a class="close" @click="noticeClose()">关闭</a>
                     </div>
                    <div class="notice-content">
                        <ul>
                            <li v-for="(notice,index) in noticeList" :key="index">
                                <div class="notice-headImage">
                                    <a href="http://minram.lofter.com" target="_blank" :title="notice.userOut.nickname + '1分钟前'">
                                        <img class="itag" :src="$url + '/images/' + notice.userOut.headImage"/>
                                    </a>
                                    <span class="w-icn3 w-icn3-2">&nbsp;</span>
                                 </div>
                                <div class="notice-title">
                                    <a href="http://www.baidu.com" target="_blank">{{notice.userOut.nickname}}</a>
                                    &nbsp;&nbsp;{{notice.title}}&nbsp;&nbsp;
                                    <a href="http://www.baidu.com" target="_blank">{{notice.object}}</a>
                                 </div>
                            </li>
                         </ul>
                    </div>
                 </div>
             </div>
            <div v-for="(update,index) in updatings" :key="index" class="update-item">
                <div class="update-userHead"></div>
                <div class="update-content">

                </div>
            </div>
            <div class="load-bar" v-if="loading">
                <div class="loading">玩命加载中</div>
            </div>
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
                                <span class="icon" :class="menu.icon"></span>
                                <span class="txt">{{menu.title}}</span>
                                <span v-if="menu.number > 0" class="inf">{{menu.number}}</span>
                             </a>
                         </li>
                        <li>
                            <span>个人信息设置</span>
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
        title: 'Market'
      }, {
        title: 'Service'
      }, {
        title: 'Book'
      }, {
        title: 'File'
      }],

      // menu
      menuList: [{
        icon: 'icon-1',
        title: '文章',
        number: 1
      }, {
        icon: 'icon-2',
        title: '喜欢',
        number: 16
      }, {
        icon: 'icon-3',
        title: '粉丝',
        number: 0
      }, {
        icon: 'icon-4',
        title: '关注',
        number: 0
      }, {
        icon: 'icon-5',
        title: '通知',
        number: 0
      }, {
        icon: 'icon-6',
        title: '私信',
        number: 0
      }],

      // 通知信息
      noticeList: [],

      // 用户基本信息
      userInfo: {
        headPhoto: '',
        username: '',
        telephone: ''
      },

      // 关注列表与被关注列表
      followInfo: {
        followList: [],
        followedList: []
      },

      // 动态信息列表
      updatings: [{
        userOut: {
          headImage: '1'
        }
      }],

      // 加载显示
      loading: 'true'
    }
  },
  mounted () {
    this.initialData()
  },
  methods: {
    // 发布点击
    publishNavsclick () {

    },

    initialData () {
      // simple user inoformation : headphoto,username,telephone
      this.$axios({
        method: 'get',
        url: this.$url + '/getSimpleInfo',
        params: {
          access_token: this.$getCookie('otod_access_token')
        }
      }).then(function (response) {
        this.userInfo.headPhoto = this.$url + '/images/' + response.data.headImage
        this.userInfo.username = response.data.nickname
        this.userInfo.telephone = 'Tel:' + response.data.telephone
      }.bind(this))


      // get followList
      this.$axios({
        method: 'get',
        url: this.$url + '/getfollowInfo',
        params: {
          access_token: this.$getCookie('otod_access_token')
        }
      }).then(function (response) {
        this.followInfo.followList = response.data.userFollow
        this.followInfo.followedList = response.data.userFollowed

        this.menuList[3].number = this.followInfo.followList.length
        this.menuList[2].number = this.followInfo.followedList.length
      }.bind(this))

      // get notice list
      this.$axios({
        method: 'get',
        url: this.$url + '/Notice',
        params: {
          access_token: this.$getCookie('otod_access_token')
        }
      }).then(function (response) {
        this.noticeList = response.data
      }.bind(this))

      // get upadtes
      // this.$axios()
      this.loading = false
    },

    // 通知栏关闭
    noticeClose () {
      this.noticeList = []
    }
  }
}
</script>

<style>
    @import '../assets/css/homePage.css'
</style>
