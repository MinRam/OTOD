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
                        <li v-for="(menu,index) in menuList" :key="index" class="small" :class="{'active':menu.active}" @click="menuClick(index)">
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
            <div v-if="activityImage!=''" class="activity-box">
                <div class="main-item">
                    <a class="image-trans">
                        <img :src="activityImage"/>
                    </a>
                 </div>
                <div class="main-btn">
                    <a class="btn-trans" href="www.baidu.com">
                        更多活动
                     </a>
                </div>
             </div>
         </div>
     </div>
    <a title="回到顶部" class="w-top" href="javascript:scrollTo(0,0);" :class="{'active': goTop}" hidefocus="true">回到顶部</a>
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
        title: '动态',
        number: 1,
        active: false
      }, {
        icon: 'icon-2',
        title: '喜欢',
        number: 16,
        active: false
      }, {
        icon: 'icon-3',
        title: '粉丝',
        number: 0,
        active: false
      }, {
        icon: 'icon-4',
        title: '关注',
        number: 0,
        active: false
      }, {
        icon: 'icon-5',
        title: '通知',
        link: '/home/notice',
        number: 0,
        active: false
      }, {
        icon: 'icon-6',
        title: '私信',
        number: 0,
        active: false
      }],

      // 关注列表与被关注列表
      followInfo: {
        followList: [],
        followedList: []
      },

      // go top
      goTop: false,

      activityImage: ''
    }
  },
  created () {
    window.onscroll = this.scrollHandler
  },
  mounted () {
    // this.$router.push('/home/person')
    this._initialData()
  },
  watch: {
    '$route': function (to, from) {
      this.initialInfo()
    }
  },
  methods: {
    _initialData () {
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

      // get activities
      this.$axios({
        method: 'get',
        url: this.$url + '/activity'
      }).then(function (response) {
        this.activityImage = this.$imageUrl + response.data
      }.bind(this))
    },

    menuClick (index) {
      // this.$router.push(this.menuList[index].link)
      for (var i = 0; i < this.menuList.length; ++i) {
        this.menuList[i].active = i === index
      }
    },

    // information setting
    setting () {
      this.$router.push('/user/setting')
    },
    scrollHandler () {
      console.log('test')
      // 变量scrollTop是滚动条滚动时，距离顶部的距离
      var scrollTop = document.documentElement.scrollTop || document.body.scrollTop

      this.goTop = scrollTop > 300

      console.log(scrollTop)
    }
  }
}
</script>

<style>
    @import '../assets/css/homePage.css'
</style>
