<template>
    <div class="followed-box">
        <div class="box-head">
            <h2 class="head-title">关注({{followList.length}})</h2>
        </div>
        <div class="followed-content">
          <div class="content">
            <ul  style="background-color: transparent;">
                <li v-for="(follow,index) in followList" :key="index">
                    <div class="user-head">
                      <a><img class="img" :src="$imageUrl + follow.headImage"/></a>
                    </div>
                    <div class="user-cnt">
                      <h4>
                        <em>
                          <a>{{follow.nickname}}</a>
                        </em>
                      </h4>
                    </div>
                    <a href="#" class="no-follow" title="取消关注" style="display: block;" @click="noFollow(follow.nickname)">添加关注</a>
                </li>
             </ul>
          </div>
        </div>
    </div>
</template>

<script>
export default{
  name: 'follow',
  data () {
    return {
      followList: []
    }
  },
  mounted () {
    this.initialData()
  },
  methods: {
    initialData () {
      this.$axios({
        methods: 'get',
        url: this.$url + '/user/getfollowInfo',
        params: {
          access_token: this.$getCookie('otod_access_token')
        }
      }).then(function (response) {
        for (var follow in response.data.userFollow) {
          this.followList.push(response.data.userFollow[follow])
        }
      }.bind(this))
    },

    noFollow (nickname) {
      this.$axios({
        methods: 'get',
        url: this.$url + '/user/followUser',
        params: {
          access_token: this.$getCookie('otod_access_token'),
          nickname: nickname
        }
      }).then(function (response) {
        if (response.data === 'true') {
          for (var i = 0; i < this.followedList.length; ++i) {
            if (this.followedList.nickname === nickname) {
              this.followedList.splice(i, 1)
              break
            }
          }
        }
      }.bind(this))
    }

  }
}
</script>

<style>
    @import '../assets/css/FollowedComp.css'
</style>
