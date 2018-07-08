<template>
    <div class="notice-box">
        <div class="notice-head">
            <h2 class="head-title">通知</h2>
        </div>
        <div class="notice-content">
            <ul>
                <li v-for="(notice,index) in noticeList" :key="index">
                    <div class="notice-headImage">
                        <a href="http://minram.lofter.com" target="_blank" :title="notice.userOut.nickname + '1分钟前'">
                            <img class="itag" :src="$imageUrl + notice.userOut.headImage"/>
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
</template>

<script>
export default{
  name: 'notice',
  data () {
    return {
      noticeList: []
    }
  },
  mounted () {
    this.initialData()
  },
  methods: {
    initialData () {
      this.$axios({
        method: 'get',
        url: this.$url + '/user/getAllNotice',
        params: {
          access_token: this.$getCookie('otod_access_token')
        }
      }).then(function (response) {
        this.noticeList = response.data
      }.bind(this))
    }
  }
}
</script>

<style>
    @import '../assets/css/NoticeComp.css'
</style>
