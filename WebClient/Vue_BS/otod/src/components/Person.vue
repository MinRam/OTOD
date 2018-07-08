<template>
    <div class="main">
            <div class="publish-Bar">
                <ul class="publish-navs">
                    <li class="user-icon">
                        <a href="/">
                            <img :src="$imageUrl + $store.state.headImage"/>
                         </a>
                     </li>
                    <li class="publish-link" v-for="(publish,index) in publishPosts" :class="publish.title" :key="index">
                        <a @click="goRouter(index)">{{publish.title}}</a>
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
             </div>
            <div v-for="(update,index) in updatings" :key="index" class="update-item">
                <div class="update-userHead">
                    <a>
                        <img :src="$imageUrl + update.author.headImage"/>
                    </a>
                </div>
                <div class="update-content">
                    <div class="bg-triangle">
                        <a class="update-page" title="详情">打开新页</a>
                     </div>
                    <div class="update-message">
                        <div class="message-who">
                            <a class="message-user">{{update.author.nickname}}</a>
                         </div>
                        <div class="message-content">
                            <div class="content-img" v-if="update.images !== null">
                                <a>
                                  <img :src="$imageUrl + update.images[0]"/>
                                </a>
                            </div>
                            <div class="content-txt">
                                <p>{{update.content}}</p>
                            </div>
                         </div>
                        <div class="message-footer">
                            <div class="message-tags">
                                <span v-for="(tag,index) in update.updateTags" :key="index">{{tag.name}}</span>
                             </div>
                            <div class="message-options">
                                <span class="opt-share">推荐</span>
                                <span class="opt-love">
                                    <a class="love-icon" :class="{'active':update.updateOption.recommened}" hideFocus="true" title="喜欢" @click="recommendUpdate(update.updateId,index)">喜欢</a>
                                </span>
                             </div>
                         </div>
                     </div>
                    <div class="update-bottom"/>
                </div>
             </div>
            <div class="load-bar" v-if="loading">
                <div class="loading">玩命加载中</div>
            </div>
            <div class="bottom-bar" v-if="bottom">
                <div class="bottom">已经到了最底部了</div>
            </div>
         </div>
</template>

<script>
export default{
  name: 'Person',
  data () {
    return {
      // 发布按钮
      publishPosts: [{
        title: 'blog',
        link: 'Blog'
      }, {
        title: 'market',
        link: 'Sell'
      }, {
        title: 'file',
        link: 'FileUpload'
      }, {
        title: 'service',
        link: 'PublishOrder'
      }, {
        title: 'book',
        link: ''
      }],

      // 通知信息
      noticeList: [],

      // 动态信息列表
      page: 0,
      updatings: [],

      // 加载显示
      loading: true,
      bottom: false
    }
  },
  created () {
    window.onscroll = this.scrollControll
  },
  mounted () {
    this.initialData()
  },
  methods: {
    // home initial
    initialData () {
      // get notice list
      this.$axios({
        method: 'get',
        url: this.$url + '/user/Notice',
        params: {
          access_token: this.$getCookie('otod_access_token')
        }
      }).then(function (response) {
        this.noticeList = response.data
      }.bind(this))

      // get Update List
      this.$axios({
        method: 'get',
        url: this.$url + '/user/getUpdateList',
        params: {
          access_token: this.$getCookie('otod_access_token'),
          page: this.page
        }
      }).then(function (response) {
        this.loading = false
        this.updatings = response.data
      }.bind(this))

      // console.log('finished')
    },

    // route link
    goRouter (index) {
      this.$router.push({ name: this.publishPosts[index].link, params: { page: this.publishPosts[index].title } })
    },

    // 通知栏关闭
    noticeClose () {
      var noticeIdList = []

      for (var i = 0; i < this.noticeList.length; ++i) {
        noticeIdList.push(this.noticeList[i].noticeId)
      }

      this.$axios({
        method: 'post',
        url: this.$url + '/user/readNotice',
        head: {
          'Authorization': 'Bearer ' + this.$getCookie('otod_access_token')
        },
        data: {
          'idList': noticeIdList
        },
        dataType: 'json'
      })

      this.noticeList = []
    },

    scrollControll () {
      // 变量scrollTop是滚动条滚动时，距离顶部的距离
      var scrollTop = document.documentElement.scrollTop || document.body.scrollTop
      // 变量windowHeight是可视区的高度
      var windowHeight = document.documentElement.clientHeight || document.body.clientHeight
      // 变量scrollHeight是滚动条的总高度
      var scrollHeight = document.documentElement.scrollHeight || document.body.scrollHeight

      // 动态加载
      if (!this.bottom && !this.loading && scrollTop + windowHeight > scrollHeight - 100) {
        // 写后台加载数据的函数
        this.loading = true
        // get Update List
        this.$axios({
          method: 'get',
          url: this.$url + '/user/getUpdateList',
          params: {
            access_token: this.$getCookie('otod_access_token'),
            page: this.page + 1
          }
        }).then(function (response) {
          if (response.data.length > 0) {
            this.page += 1
            for (var i = 0; i < response.data.length; ++i) {
              this.updatings.push(response.data[i])
            }
            this.loading = false
          } else {
            this.loading = false
            this.bottom = true
          }
        }.bind(this))
      }
    },

    recommendUpdate (id, index) {
      if (this.updatings[index].updateOption.recommened) {
        this.$axios({
          method: 'get',
          url: this.$url + '/user/noFavorUpdate',
          params: {
            access_token: this.$getCookie('otod_access_token'),
            update_id: id
          }
        }).then(function (response) {
          if (response.data === 'success') {
            this.updatings[index].updateOption.recommened = false
          }
        }.bind(this))
      } else {
        this.$axios({
          method: 'get',
          url: this.$url + '/user/favorUpdate',
          params: {
            access_token: this.$getCookie('otod_access_token'),
            update_id: id
          }
        }).then(function (response) {
          if (response.data === 'success') {
            this.updatings[index].updateOption.recommened = true
          }
        }.bind(this))
      }
    }
  }
}

</script>

<style>

</style>
