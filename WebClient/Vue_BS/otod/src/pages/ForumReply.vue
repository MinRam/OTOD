<template>
<div class="container"  id="app">
    <div class="jumbotron">
      <h1>博客论坛系统</h1>
    </div>
    <div class="row">
      <span>{{ topic_title }}</span>
      <table class="table table-bordered table-striped">
        <tbody>
          <tr v-for="(x,index) in pagelist" style="height:100px" :key="index">
            <td class="col-md-2">
              <span>用户的昵称</span>
              <div class="clear-both"></div>
              <span><a @click="alee()">id:{{ x.user_id }}</a></span>
            </td>
            <td class="col-md-10">
              <div class="row">
                <div class="pull-right col-md-2">
                  <span>{{ x.date }}</span>
                </div>
              </div>
              <div class="clear-both"></div>
              <div>
                <span>&nbsp;{{ x.content }}</span>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <div class="row">
        <ul class="pagination">
        <li><a href="#">&laquo;</a></li>
        <li><a href="#">1</a></li>
        <li><a href="#">2</a></li>
        <li><a href="#">3</a></li>
        <li><a href="#">4</a></li>
        <li><a href="#">5</a></li>
        <li><a href="#">&raquo;</a></li>
      </ul>
    </div>
    <div class="row">
       <form class="bs-example bs-example-form" role="form">
        <div class="form-group">
          <textarea class="form-control" rows="10" name=textarea v-model="forumReplyPO.content"></textarea>
        </div>
      </form>
      <button class="btn btn-success" @click="save()">回复</button>
      <button class="btn btn-success" @click="queryByConditions()">回复</button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Login',
  data () {
    return {
      id: this.$route.query.id,
      page: 0,
      row: 10,
      topic_title: '',
      forumReplyPO: {
        topic_id: this.$route.query.id,
        date: '',
        user_id: '',
        content: ''
      },
      pagelist: [
        {}
      ]
    }
  },
  mounted: function () {
    // 键盘监听注册
    this.queryByConditions()
  //  this.setTopicTitle()
  //  this.setTopicTitle()
  },
  watch: {
    page: {
      handler: function (val, oldval) {
        this.queryByConditions()
      }
    }
  },
  methods: {
    save () {
      var t = this
      this.$axios({
        method: 'post',
        url: 'http://localhost:8081/forumreply/save',
        dataType: 'json',
        data: {
          user_id: this.forumReplyPO.user_id,
          content: this.forumReplyPO.content,
          topic_id: this.$route.query.id
        }
      }).then(function (response) {
        console.log(response)
        alert('回复成功')
        // 提交完刷新一次数据
        t.queryByConditions()
      }).catch(function (error) {
        console.log(error)
      })
    },
    alee () {
      alert(this.forumReplyPO.topic_id)
    },
    queryByPage () {
      var t = this
      this.$axios({
        method: 'get',
        url: 'http://localhost:8081/forumreply/findbypage',
        dataType: 'json',
        params: {
          page: this.page,
          row: this.row
        }
      }).then(function (response) {
        console.log(response)
        t.pagelist = response.data.content
        console.log(t.pagelist)
      }).catch(function (error) {
        console.log(error)
      })
    },
    queryByConditions () {
      var t = this
      this.$axios({
        method: 'post',
        url: 'http://localhost:8081/forumreply/findbyconditions',
        dataType: 'json',
        data: {
          page: this.page,
          row: this.row,
          topic_id: this.id
        }
      }).then(function (response) {
        console.log(response)
        t.pagelist = response.data.content
        for (var i in t.pagelist) {
          var date = new Date(t.pagelist[i].date)
          if (date.getFullYear() < (new Date().getFullYear())) {
            t.pagelist[i].date = date.getFullYear() + '年' + date.getMonth() + '月' + date.getDate() + '日'
          } else {
            t.pagelist[i].date = date.getMonth() + '月' + date.getDate() + '日 ' + date.toLocaleTimeString()
          }
        }
        if (t.topic_title === '') {
          t.topic_title = t.pagelist[0].forumTopicPO.title
        }
      }).catch(function (error) {
        console.log(error)
      })
    }
  }
}
</script>

<style>
</style>
