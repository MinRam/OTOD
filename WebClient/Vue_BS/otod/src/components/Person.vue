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
                        <img :src="$imageUrl + update.author.headPhoto"/>
                    </a>
                </div>
                <div class="update-content">
                    <div class="bg-triangle">
                        <a class="update-page" title="详情">打开新页</a>
                     </div>
                    <div class="update-message">
                        <div class="message-who">
                            <a class="message-user">{{update.author.username}}</a>
                         </div>
                        <div class="message-content">
                            <div class="content-img">
                                <a>
                                  <img :src="$imageUrl + update.images[0].url" :title="update.images[0].title"/>
                                </a>
                            </div>
                            <div class="content-txt">
                                <p v-for="(con,index) in update.content" :key="index">{{con}}</p>
                            </div>
                         </div>
                        <div class="message-footer">
                            <div class="message-tags">
                                <span v-for="(tag,index) in update.updateTags" :key="index">{{tag.name}}</span>
                             </div>
                            <div class="message-options">
                                <span class="opt-share">推荐</span>
                                <span class="opt-love">
                                    <a class="love-icon" :class="{'active':update.updateOpt.recommened}" hideFocus="true" title="喜欢" @click="recommendUpdate(update.updateId,index)">喜欢</a>
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
      updatings: [{
        author: {
          headPhoto: 'hp/6630576284002729390.jpg',
          username: 'TianChengLiu',
          telephone: ''
        },
        updateId: 1,
        updateTags: [{
          name: '皮皮虾'
        }, {
          name: '揍你'
        }],
        images: [{
          url: 'Images/1.jpg',
          title: '早上好'
        }],
        content: ['怕上火爆王老菊'],
        updateOpt: {
          recommened: false
        }
      }, {
        author: {
          headPhoto: 'hp/6630576284002729390.jpg',
          username: 'TianChengLiu',
          telephone: ''
        },
        updateId: 2,
        updateTags: [{
          name: '皮皮虾'
        }, {
          name: '揍你'
        }],
        images: [{
          url: 'Images/1.jpg',
          title: '早上好'
        }],
        content: ['怕上火爆王老菊', '123'],
        updateOpt: {
          recommened: true
        }
      }, {
        author: {
          headPhoto: 'hp/6630576284002729390.jpg',
          username: 'TianChengLiu',
          telephone: ''
        },
        updateId: 3,
        updateTags: [{
          name: '皮皮虾'
        }, {
          name: '揍你'
        }],
        images: [{
          url: 'Images/1.jpg',
          title: '早上好'
        }],
        content: ['怕上火爆王老菊', '123'],
        updateOpt: {
          recommened: true
        }
      }],

      // 加载显示
      loading: true
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
      // this.$axios({
      //   method: 'get',
      //   url: this.$url + '/user/get'
      // })

      this.loading = false
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
      if (!this.loading && scrollTop + windowHeight > scrollHeight - 100) {
        // 写后台加载数据的函数
        this.loading = true

        this.updatings.push({
          author: {
            headPhoto: 'hp/6630576284002729390.jpg',
            username: 'TianChengLiu',
            telephone: ''
          },
          updateTags: [{
            name: '皮皮虾'
          }, {
            name: '揍你'
          }],
          images: [{
            url: 'Images/1.jpg',
            title: '早上好'
          }],
          content: ['怕上火爆王老菊', '123'],
          updateOpt: {
            recommened: true
          }
        })

        this.loading = false
      }
    },

    recommendUpdate (id, index) {
      if (this.updatings[index].updateOpt.recommened) {
        this.$axios({
          method: 'get',
          url: this.$url + '/user/favorUpdate',
          params: {
            access_token: this.$getCookie('otod_access_token'),
            update_id: id
          }
        }).then(function (response) {
          if (response.data === 'success') {
            this.updatings[index].updateOpt.recommened = false
          }
        })
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
            this.updatings[index].updateOpt.recommened = true
          }
        })
      }
    }
  }
}

</script>

<style>

</style>
