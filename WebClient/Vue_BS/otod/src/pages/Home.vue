<template>
  <div class="otod-home">
    <div class="otod-content">
        <router-view/>
        <div class="reside">
            <div class="user-box">
                <div class="user-menu">
                    <ul>
                        <li class="first large">
                            <a href="/" class="menu-item username">
                                <span class="txt">
                                    <span class="lg1">{{$store.state.nickname}}</span>
                                    <span class="lg2">{{$store.state.telephone}}</span>
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
                        <li class="user-setting" @click="setting()">
                            <a class="menu-item">
                              <span class="icon setting"></span>
                              <span class="txt">个人信息设置</span>
                             </a>
                         </li>
                     </ul>
                 </div>
             </div>
         </div>
     </div>
    <a title="回到顶部" class="w-top" href="javascript:scrollTo(0,0);" id="gtotop" hidefocus="true" style="visibility: visible; opacity: 1;">回到顶部</a>
  </div>
</template>

<script>
export default {
  name: 'Home',
  data () {
    return {
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
      }
    }
  },
  mounted () {
    // this.$router.push('/home/person')
    this.initialData()
  },
  methods: {
    initialData () {
      // simple user inoformation : headphoto,username,telephone
      this.$axios({
        method: 'get',
        url: this.$url + '/user/getSimpleInfo',
        params: {
          access_token: this.$getCookie('otod_access_token')
        }
      }).then(function (response) {
        this.$store.commit('initialName', response.data.nickname)
        this.$store.commit('initialHead', response.data.headImage)
        this.$store.commit('initialTel', response.data.telephone)
        this.$router.push('/home/person')
      }.bind(this))

      // get followList
      this.$axios({
        method: 'get',
        url: this.$url + '/user/getfollowInfo',
        params: {
          access_token: this.$getCookie('otod_access_token')
        }
      }).then(function (response) {
        this.followInfo.followList = response.data.userFollow
        this.followInfo.followedList = response.data.userFollowed

        this.menuList[3].number = this.followInfo.followList.length
        this.menuList[2].number = this.followInfo.followedList.length
      }.bind(this))
    },

    // information setting
    setting () {
      this.$router.push('/user/setting')
    }
  }
}
</script>

<style>
    @import '../assets/css/homePage.css'
</style>
