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
                            <div class="content-txt"></div>
                         </div>
                        <div class="message-footer">
                            <div class="message-tags">
                                <span v-for="(tag,index) in update.updateTags" :key="index">{{tag.name}}</span>
                             </div>
                            <div class="message-options">
                                <span class="opt-share">推荐</span>
                                <span class="opt-love">
                                    <a class="love-icon" :class="{'active':update.updateOpt.recommened}" hideFocus="true" title="喜欢">喜欢</a>
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
        link: 'QuillEditor'
      }, {
        title: 'market',
        link: 'Sell'
      }, {
        title: 'file',
        link: ''
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
        updateTags: [{
          name: '皮皮虾'
        }, {
          name: '揍你'
        }],
        images: [{
          url: 'Images/1.jpg',
          title: '早上好'
        }],
        Content: '怕上火爆王老菊',
        updateOpt: {
          recommened: false
        }
      }, {
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
        Content: '怕上火爆王老菊',
        updateOpt: {
          recommened: false
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

      this.loading = false
      // console.log('finished')
    },

    // route link
    goRouter (index) {
      this.$router.push({ name: this.publishPosts[index].link, params: { page: this.publishPosts[index].title } })
    },

    // 通知栏关闭
    noticeClose () {
      this.noticeList = []
    }
  }
}

</script>

<style>

</style>
