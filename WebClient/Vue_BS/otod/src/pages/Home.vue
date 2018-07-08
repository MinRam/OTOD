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
        link: '/home/followed',
        number: 0,
        active: false
      }, {
        icon: 'icon-4',
        title: '关注',
        link: '/home/follow',
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
  },
  mounted () {
    // this.$router.push('/home/person')
    this._initialData()
  },
  watch: {
  },
  methods: {
    _initialData () {
      // get all nums
      this.$axios({
        method: 'get',
        url: this.$url + '/user/getUserNum',
        params: {
          access_token: this.$getCookie('otod_access_token')
        }
      }).then(function (response) {
        this.menuList[0].number = response.data.dynamics
        this.menuList[1].number = response.data.favors
        this.menuList[2].number = response.data.followed
        this.menuList[3].number = response.data.follow
        this.menuList[4].number = response.data.notices
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
      this.$router.push(this.menuList[index].link)
      for (var i = 0; i < this.menuList.length; ++i) {
        this.menuList[i].active = i === index
      }
    },

    // information setting-item
    setting () {
      this.$router.push('/user/setting')
    }
  }
}
</script>

<style>
    @import '../assets/css/homePage.css'
</style>
