<template>
<div>
  <el-container style="width:80%;margin:auto;">
    <el-header>
    </el-header>
    <el-main>
      <el-row type="flex" class="row-bg" justify="center">
        <div style="margin:3px;max-width:200px" v-for="(x,index) in sectionList" :key="index">
          <el-popover
            placement="top"
            :title="x.name"
            width="200"
            trigger="hover">
            <p>{{ x.statement }}</p>
            <span>主题贴数:{{x.post_num}}</span>
            <el-button type="warning" plain style="height:60px;width:150px;" slot="reference" @click="changepage(1),changesection_id(x.id),queryByCondition()">
              <i class="el-icon-caret-bottom"></i>{{ x.name }}
            </el-button>
          </el-popover>
          </el-popover>
        </div>
      </el-row>
      <el-row type="flex" class="row-bg" justify="end">
        <div style="margin: 15px 100px;">
          <el-input placeholder="请输入内容" v-model="condition.title" class="input-with-select">
            <el-select v-model="conditionSelect" slot="prepend" placeholder="请选择">
              <el-option label="餐厅名" value="1"></el-option>
              <el-option label="订单号" value="2"></el-option>
              <el-option label="用户电话" value="3"></el-option>
            </el-select>
            <el-button slot="append" icon="el-icon-search" @click="queryByCondition()"></el-button>
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
              width="700">
              <template slot-scope="scope">
                <el-button type="text" @click="toReply(scope.row.id)">{{ scope.row.title }}</el-button>
              </template>
            </el-table-column>
            <el-table-column
              label="作者"
              width="150">
              <template slot-scope="scope">
                <el-button type="text">用户昵称</el-button>
                <a>id : {{ scope.row.user_id }}</a>
                <p>{{ scope.row.date }}</p>
              </template>
            </el-table-column>
            <el-table-column
              label="最后回复"
              width="150">
              <template slot-scope="scope">
                <el-row>
                  <el-col>
                    <p>{{ scope.row.last_time }}</p>
                    <el-row>
                      <i class="el-icon-service"></i>
                      <el-button type="text">回复者昵称</el-button>
                    </el-row>
                  </el-col>
                  <el-col>
                  </el-col>
                </el-row>
              </template>
            </el-table-column>
            <el-table-column
              label="操作"
              width="70">
              <template slot-scope="scope">
                <el-popover
                  placement="top"
                  width="160"
                  v-model="scope.row.deletevisible">
                  <p>删除不可复原，确定删除吗？</p>
                  <div style="text-align: right; margin: 0">
                    <el-button size="mini" type="text" @click="this.$set('scope.row.deletevisible', false)">取消</el-button>
                    <el-button type="primary" size="mini" @click="scope.row.deletevisible = false,deletebyid(scope.row.id)">确定</el-button>
                  </div>
                  <el-button slot="reference" type="danger" icon="el-icon-delete" circle size="mini"></el-button>
                </el-popover>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-row>
      <el-row type="flex" class="row-bg" justify="center" style="margin: 15px;">
        <div class="block">
          <el-pagination
            background
            @size-change="changerows"
            @current-change="changepage"
            :current-page="page"
            :page-sizes="[10, 15, 20, 30]"
            :page-size="rows"
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
      <el-button type="success" plain @click="alee()">发表</el-button>
      <router-view/>
    </el-footer>
  </el-container>
</div>
</template>

<script>
// import QuillEditor from '@/components/quillEditor'
export default {
  name: 'Blog',
  data () {
    return {
      conditionSelect: '',
      forumTopicLength: 0,
      page: 1,
      rows: 10,
      deletevisible: false,
      condition: {
        title: '',
        section_id: 1,
        user_id: 1
      },
      forumTopicList: [
        {
          deletevisible: false
        }
      ],
      sectionList: [
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
    this.querySectionList()
    this.$router.push('/forumtopic/quilleditor')
  },
  watch: {
    page: {
      handler: function (val, oldval) {
        this.queryByCondition()
      }
    },
    rows: {
      handler: function (val, oldval) {
        this.queryByCondition()
      }
    }
  },
  methods: {
    // 发表帖子
    postData () {
      var t = this
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
        t.successMessageSave()
        // 提交完刷新一次数据
        t.queryByCondition()
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
        // 为主题帖赋值，为总数度赋值
        t.forumTopicList = response.data.content
        t.forumTopicLength = response.data.totalElements
        // 切换日期显示
        for (var i in t.forumTopicList) {
          // 获得距今时间
          t.forumTopicList[i]['last_time'] = t.getLastTime(t.forumTopicList[i].lastReplyDate)
          var date = new Date(t.forumTopicList[i].date)
          if (date.getFullYear() < (new Date().getFullYear())) {
            t.forumTopicList[i].date = date.getFullYear() + '年' + (date.getMonth() + 1) + '月' + date.getDate() + '日'
          } else {
            t.forumTopicList[i].date = (date.getMonth() + 1) + '月' + date.getDate() + '日 ' + date.getHours() + ':' + date.getMinutes()
          }
          // 添加删除弹框可视变量
          t.forumTopicList[i].deletevisible = false
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
      alert(this.$refs.myTextEditor.content)
      console.log(this.mycontent)
    },

    querySectionList () {
      var t = this
      this.$axios({
        method: 'get',
        url: 'http://localhost:8081/sectioninfo/findbypage',
        dataType: 'jsonp',
        params: {
          page: t.page - 1,
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

    // 改变版块
    changsection (val) {
      this.condition.section_id = val
    },

    // 改变页面大小
    changerows (val) {
      this.rows = val
    },

    // 改变当前版块
    changesection_id (val) {
      this.condition.section_id = val
    },

    // 删除主题帖
    deletebyid (id) {
      var t = this
      this.$axios.get('http://localhost:8081/forumtopic/deletebyid?id=' + id).then(function (response) {
        console.log(response)
        t.successMessageDelete()
        t.queryByCondition()
      }).catch(function (error) {
        console.log(error)
      })
    },

    // 回复贴跳转
    toReply (id) {
      this.$router.push({path: 'blogreply', query: {id: id}})
    },

    // 发表成功信息顶部弹出
    successMessageSave () {
      this.$message({
        showClose: true,
        message: '发表成功',
        type: 'success'
      })
    },

    // 删除成功信息弹出
    successMessageDelete () {
      this.$message({
        showClose: true,
        message: '删除成功',
        type: 'error'
      })
    },

    handlePreview (file) {
      console.log(file)
    }
  }
}
</script>
