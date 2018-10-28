<template>
  <div style="float: left; width: 672px;">
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
  </div>
</template>

<script>
export default{
  name: 'follow',
  data () {
    return {
      // 动态信息列表
      page: 0,
      updatings: [],
      // 加载显示
      loading: true,
      bottom: false
    }
  },
  mounted () {
    this.initialData()
  },
  methods: {
    initialData () {
      // get Update List
      this.$axios({
        method: 'get',
        url: this.$url + '/user/getUserUpdateList',
        params: {
          access_token: this.$getCookie('otod_access_token'),
          page: this.page
        }
      }).then(function (response) {
        this.loading = false
        this.updatings = response.data
      }.bind(this))
    }
  }
}
</script>

<style>
    @import '../assets/css/DynamicComp.css'
</style>
