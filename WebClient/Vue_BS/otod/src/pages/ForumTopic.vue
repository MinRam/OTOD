<template>
<div>
<div>
  <quill-editor
    ref="myTextEditor"
    v-model="forumTopicPO.content"
    :config="editorOption"
    @blur="alee()"
    @focos="alee()"
    @ready="alee()">
  </quill-editor>
  <el-button @click="postData">确定q</el-button>
  <router-view/>
</div>
</div>
</template>

<script>
export default {
  name: 'Login',
  data () {
    return {
      mycontent: '',
      currentPage: 0,
      sectionList: [
        {}
      ],
      forumTopicLength: 0,
      page: 1,
      rows: 10,
      condition: {
        title: '',
        section_id: 1,
        user_id: 1
      },
      forumTopicList: [
        {}
      ],
      forumTopicPO: {
        id: '',
        clickNum: '',
        replyNum: '',
        date: '',
        lastReplyId: '',
        lastReplyDate: '',
        sectionId: '1',
        userId: '1',
        type: '',
        title: '',
        content: ''
      }
    }
  },
  mounted: function () {
    // 键盘监听注册
    // this.queryByCondition()
    this.$router.push('/forumtopic/quilleditor')
  },
  watch: {
    page: {
      handler: function (val, oldval) {
        this.queryByCondition()
      }
    }
  },
  methods: {
    // 发表帖子
    postData () {
      this.$axios({
        method: 'post',
        url: 'http://localhost:8081/forumtopic/save',
        dataType: 'json',
        data: {
          section_id: this.condition.section_id,
          user_id: this.condition.user_id,
          content: this.forumTopicPO.content,
          title: this.forumTopicPO.title
        }
      }).then(function (response) {
        console.log(response)
        alert('发表成功')
        // 提交完刷新一次数据
      //  this.queryByCondition()
      }).catch(function (error) {
        console.log(error)
      })
    },

    queryByCondition () {
      var t = this
      this.$axios({
        method: 'post',
        url: 'http://localhost:8081/forumtopic/findbycondition',
        dataType: 'json',
        data: {
          section_id: this.condition.section_id,
          title: this.condition.title,
          page: this.page - 1,
          row: this.rows
        }
      }).then(function (response) {
        console.log(response)
        t.forumTopicList = response.data.content
        t.forumTopicLength = response.data.totalElements

        for (var i in t.forumTopicList) {
          t.forumTopicList[i]['last_time'] = t.getLastTime(t.forumTopicList[i].lastReplyDate)
          var date = new Date(t.forumTopicList[i].date)
          if (date.getFullYear() < (new Date().getFullYear())) {
            t.forumTopicList[i].date = date.getFullYear() + '年' + date.getMonth() + '月' + date.getDate() + '日'
          } else {
            t.forumTopicList[i].date = date.getMonth() + '月' + date.getDate() + '日'
          }
        }
      }).catch(function (error) {
        console.log(error)
      })
    },

    test () {
      console.log(this.content)
    },

    // 分页查询，axios，post
    queryByPage () {
      var t = this
      this.$axios({
        method: 'get',
        url: 'http://localhost:8081/forumtopic/findbypage',
        dataType: 'jsonp',
        params: {
          page: t.page,
          row: t.rows
        }
      }).then(function (response) {
        console.log(response)
        t.forumTopicList = response.data.content
        t.forumTopicLength = response.data.totalElements

        for (var i in t.forumTopicList) {
          t.forumTopicList[i]['last_time'] = t.getLastTime(t.forumTopicList[i].date)
          var date = new Date(t.forumTopicList[i].date)
          if (date.getFullYear() < (new Date().getFullYear())) {
            t.forumTopicList[i].date = date.getFullYear() + '年' + date.getMonth() + '月' + date.getDate() + '日'
          } else {
            t.forumTopicList[i].date = date.getMonth() + '月' + date.getDate() + '日'
          }
        }

        var a = new Date(t.forumTopicList[0].date).getTime()
        console.log(a)
        console.log(t.forumTopicList)
      }).catch(function (error) {
        console.log(error)
      })
    },
    alee () {
      alert(this.page)
    },

    querySectionList () {
      var t = this
      this.$axios({
        method: 'get',
        url: 'http://localhost:8081/sectioninfo/findbypage',
        dataType: 'jsonp',
        params: {
          page: t.page,
          row: t.rows
        }
      }).then(function (response) {
        console.log(response)
        t.sectionList = response.data.content
      }).catch(function (error) {
        console.log(error)
      })
    },
    // 获得最后回复距今时间
    getLastTime (date) {
      var now = new Date().getTime()
      var oldtime = new Date(date).getTime()
      var oldtimedate = new Date(date)
      var difference = now - oldtime
      var result = ''
      var minute = 1000 * 60
      var hour = minute * 60
      var day = hour * 24
      var month = day * 30
      var year = month * 12

      var _year = difference / year
      var _month = difference / month
      var _week = difference / (7 * day)
      var _day = difference / day
      var _hour = difference / hour
      var _minute = difference / minute

      if (_year >= 1) {
        result = oldtimedate.getFullYear() + '年' + oldtimedate.getMonth() + '月' + oldtimedate.getDate() + '日'
      } else if (_month >= 1) {
        result = oldtimedate.getMonth() + '月' + oldtimedate.getDate() + '日'
      } else if (_week >= 1) {
        result = oldtimedate.getMonth() + '月' + oldtimedate.getDate() + '日'
      } else if (_day >= 1) {
        result = '' + ~~(_day) + '天前'
      } else if (_hour >= 1) {
        result = '' + ~~(_hour) + '个小时前'
      } else if (_minute >= 1) {
        result = '' + ~~(_minute) + '分钟前'
      } else result = '刚刚'
      return result
    },

    // 改变页面
    changepage (val) {
      this.page = val
    },

    // 回复贴跳转
    toReply (id) {
      this.$router.push({path: 'forumreply', query: {id: id}})
    }
  }
}
</script>

<style>

</style>
