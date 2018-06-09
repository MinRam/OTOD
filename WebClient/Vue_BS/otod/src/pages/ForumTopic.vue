<template>
<div>
<div class="container"  id="app">
  <div class="jumbotron">
      <h1>博客论坛系统</h1>
  </div>
<div class="row" style="margin:40px 70px;">
   <div class="col-md-2 panel panel-warning" style="margin:3px;max-width:200px" v-for="(x,index) in sectionList" :key="index">
   <div class="panel-body row">
       <div class="col-md-12">
       <span>{{ x.name }}</span>
       </div>
   </div>
   </div>
</div>
<div class="row" style="margin: 10px 0px;">
   <div class="col-md-4 pull-right input-group">
       <span class="input-group-addon">标题：</span>
       <input type="text" class="form-control" style="width: auto;" v-model="condition.title">
       <button class="btn btn-success" @click="changepage(0),queryByCondition()">搜索</button>
   </div>
</div>
<div class="row">
  <table class="table table-striped">
    <tbody>
      <tr v-for="(x,index) in forumTopicList" :key="index">
       <td class="col-md-1">{{ x.reply_num }}</td>
       <td class="col-md-9"><a v-on:click="toReply(x.id)">{{ x.title }}</a></td>
       <td  class="col-md-1">
        <a href="#">id：{{ x.user_id }}</a>
        <div class="clear-both"></div>
        <span style="color:#828282;">{{ x.date }}</span>
      </td>
      <td  class="col-md-1">
        <span style="color:#828282;">{{ x.last_time }}</span>
        <div class="clear-both"></div>
        <a href="#">黑色的毒龙</a>
      </td>
      <td></td>
    </tr>
  </tbody>
</table>
</div>
<div class="row">
  <ul class="pagination">
   <li><a href="#">&laquo;</a></li>
   <li><a v-on:click="changepage(0)">1</a></li>
   <li><a v-on:click="changepage(1)">2</a></li>
   <li><a v-on:click="changepage(2)">3</a></li>
   <li><a v-on:click="changepage(3)">4</a></li>
   <li><a v-on:click="changepage(4)">5</a></li>
   <li><a href="#">&raquo;</a></li>
 </ul>
</div>
<div class="row">
  <form class="bs-example bs-example-form">
    <div class="input-group" style="margin:8px 0px;">
      <span class="input-group-addon">标题</span>
      <input type="text" class="form-control" placeholder="请输入标题" v-model="forumTopicPO.title">
    </div>
    <div class="form-group">
      <textarea class="form-control" rows="10" name="textarea" placeholder="请输入内容" v-model="forumTopicPO.content"></textarea>
    </div>
  </form>
  <input type="button" value="确定" class="btn btn-success" @click="postData()">
  <input type="button" value="确定" class="btn btn-success" @click="querySectionList()">
  <input type="button" value="确定" class="btn btn-success" @click="alee()">
</div>
</div>

<div>
  <el-container>
    <el-header>
    </el-header>
    <el-main>
      <el-row type="flex" class="row-bg" justify="center">
        <el-popover
          placement="top"
          width="160"
          v-model="visible2">
          <p>这是一段内容这是一段内容确定删除吗？</p>
          <div style="text-align: right; margin: 0">
            <el-button size="mini" type="text" @click="visible2 = false">取消</el-button>
            <el-button type="primary" size="mini" @click="visible2 = false">确定</el-button>
          </div>
          <el-button slot="reference" style="height:100px;">删除</el-button>
        </el-popover>
      </el-row>
      <el-row type="flex" class="row-bg" justify="end">
        <div style="margin-top: 15px;">
          <el-input placeholder="请输入内容" v-model="input5" class="input-with-select">
            <el-select v-model="select" slot="prepend" placeholder="请选择">
              <el-option label="餐厅名" value="1"></el-option>
              <el-option label="订单号" value="2"></el-option>
              <el-option label="用户电话" value="3"></el-option>
            </el-select>
            <el-button slot="append" icon="el-icon-search"></el-button>
          </el-input>
        </div>
      </el-row>
      <el-row type="flex" class="row-bg" justify="center">
        <div class="block">
          <el-table
            :data="forumTopicList"
            stripe
            style="width: 100%">
            <el-table-column
              prop="reply_num"
              label="回复数"
              width="100">
            </el-table-column>
            <el-table-column
              label="标题"
              width="600">
              <template slot-scope="scope">
                <el-button type="text" @click="toReply(scope.row.id)">{{ scope.row.title }}</el-button>
              </template>
            </el-table-column>
            <el-table-column
              label="作者"
              width="200">
              <template slot-scope="scope">
                <p>用户昵称</p>
                <a>id : {{ scope.row.user_id }}</a>
                <p>{{ scope.row.date }}</p>
              </template>
            </el-table-column>
            <el-table-column
              label="最后回复"
              width="200">
              <template slot-scope="scope">
                <p>{{ scope.row.last_time }}</p>
                <p>回复者昵称</p>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-row>
      <el-row type="flex" class="row-bg" justify="center" style="margin: 15px;">
        <div class="block">
          <el-pagination
            background
            @size-change="handleSizeChange"
            @current-change="changepage"
            :current-page="page"
            :page-sizes="[10, 15, 20, 30]"
            :page-size="10"
            layout="sizes, prev, pager, next,total, jumper"
            :total="forumTopicLength">
          </el-pagination>
        </div>
      </el-row>
    </el-main>
    <el-footer>
      <el-input
        placeholder="请输入内容"
        suffix-icon="el-icon-date"
        width="180"
        v-model="forumTopicPO.title"
        clearable>
        <template slot="prepend">标题</template>
      </el-input>
      <el-input
        type="textarea"
        :rows="8"
        placeholder="请输入内容"
        v-model="forumTopicPO.content">
      </el-input>
      <el-button type="success" plain @click="postData()">发表</el-button>
    </el-footer>
  </el-container>
</div>


</div>
</template>

<script>
export default {
  name: 'Login',
  data () {
    return {
      currentPage: 0,
      sectionList: [
        {}
      ],
      forumTopicLength: 0,
      page: 1,
      rows: 10,
      condition: {
        title: '',
        section_id: 1
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
        sectionId: '',
        userId: '',
        type: '',
        title: '',
        content: ''
      }
    }
  },
  mounted: function () {
    // 键盘监听注册
    this.queryByCondition()
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
        data: this.forumTopicPO
      }).then(function (response) {
        console.log(response)
        alert('发表成功')
        // 提交完刷新一次数据
        this.queryByCondition()
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
      this.$axios.get('http://localhost:8081/forumtopic/findbyid?id=1').then(function (response) {
        console.log(response)
      }).catch(function (error) {
        console.log(error)
      })
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
