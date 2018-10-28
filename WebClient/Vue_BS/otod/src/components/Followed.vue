<template>
    <div class="followed-box">
        <div class="box-head">
            <h2 class="head-title">粉丝({{followedList.length}})</h2>
        </div>
        <div class="followed-content">
          <div class="content">
            <ul  style="background-color: transparent;">
                <li v-for="(followed,index) in followedList" :key="index">
                    <div class="user-head">
                      <a><img class="img" :src="$imageUrl + followed.headImage"/></a>
                    </div>
                    <div class="user-cnt">
                      <h4>
                        <em>
                          <a>{{followed.nickname}}</a>
                        </em>
                      </h4>
                    </div>
                    <a href="#" v-if="followed.active" class="follow" title="添加关注" style="display: block;" @click="follow(followed.nickname)">添加关注</a>
                </li>
             </ul>
          </div>
        </div>
    </div>
</template>

<script>
export default{
  name: 'followed',
  data () {
    return {
      followedList: []
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
        for (var followed in response.data.userFollowed) {
          this.followedList.push(response.data.userFollowed[followed])
          this.followedList[followed].active = true
        }
      }.bind(this))
    },

    follow (nickname) {
      this.$axios({
        methods: 'get',
        url: this.$url + '/user/followUser',
        params: {
          access_token: this.$getCookie('otod_access_token'),
          nickname: nickname
        }
      }).then(function (response) {
        // if (response.data === 'success'){
        for (var i = 0; i < this.followedList.length; ++i) {
          if (this.followedList[i].nickname === nickname) {
            this.followedList[i].active = false
            break
          }
        }
        // } else {
        //   this.$message.error('该用户已经关注')
        // }
      }.bind(this))
    }

  }
}
</script>

<style>
    @import '../assets/css/FollowedComp.css'
</style>
