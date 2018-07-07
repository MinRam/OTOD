<template>
<div>
  <el-container style="width:100%;margin:auto;">
    <el-header>
      <!--导航栏-->
      <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/home/person' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item :to="{ path: '/Blog' }">博客</el-breadcrumb-item>
          <el-breadcrumb-item>{{ section_name }}</el-breadcrumb-item>
      </el-breadcrumb>
      <!--版块修改对话框-->
      <el-row type="flex" class="row-bg" justify="center">
        <el-button v-if="user_type > 0" @click="querySectionById(condition.section_id),dialogFormVisible = true">修改当前版块</el-button>
      </el-row>
      <el-dialog title="修改版块" :visible.sync="dialogFormVisible">
        <el-form>
          <el-form-item label="版块名称：">
            <el-input v-model="currentSection.name"></el-input>
          </el-form-item>
          <el-form-item label="版块描述：">
            <el-input v-model="currentSection.statement" auto-complete="off"></el-input>
          </el-form-item>
        </el-form>
        <el-table :data="currentSection.sectionAdminPOs">
          <el-table-column property="userInfo.nickname" label="管理员账号" width="300"></el-table-column>
          <el-table-column
            v-if="user_type == 1"
            label="操作"
            width="120">
            <template slot-scope="scope">
              <el-popover
                placement="top"
                width="160"
                v-model="scope.row.deletevisible">
                <p>删除不可复原，确定删除吗？</p>
                <div style="text-align: right; margin: 0">
                  <el-button size="mini" type="text">取消</el-button>
                  <el-button type="primary" size="mini">确定</el-button>
                </div>
                <el-button slot="reference" type="danger" icon="el-icon-delete" circle size="mini"></el-button>
              </el-popover>
            </template>
          </el-table-column>
        </el-table>
        <div slot="footer" class="dialog-footer">
          <el-button v-if="user_type == 1">新增管理员</el-button>
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogFormVisible = false,updateSection()">确 定</el-button>
        </div>
      </el-dialog>
    </el-header>
    <el-main >
      <!--版块列表-->
      <el-row type="flex" class="row-bg" justify="center">
        <div class="section-div" v-for="(x,index) in sectionList" :key="index">
          <el-popover
            placement="top"
            :title="x.name"
            width="200"
            trigger="hover">
            <p>{{ x.statement }}</p>
            <p v-if="x.sectionAdminPOs != null">管理员：{{ x.sectionAdminPOs[0].userInfo.nickname }}</p>
            <span>主题贴数:{{x.post_num}}</span>
            <el-button type="warning" plain class="section-button" slot="reference" @click="changeSection(x.id)">
              <i class="el-icon-caret-bottom"></i>{{ x.name }}
            </el-button>
          </el-popover>
          </el-popover>
        </div>
      </el-row>
      <!--标题查询-->
      <el-row type="flex" class="row-bg" justify="end">
        <div style="margin: 15px 100px;">
          <el-input placeholder="请输入内容" v-model="condition.title" class="input-with-select">
            <el-button slot="append" icon="el-icon-search" @click="queryByCondition()"></el-button>
          </el-input>
        </div>
      </el-row>
      <!--主题帖列表-->
      <el-row type="flex" class="row-bg" justify="center">
        <div class="block">
          <el-table
            v-loading="loading"
            :data="forumTopicList"
            stripe
            style="width: 100%">
            <el-table-column
              label="回复数"
              align="center"
              width="100">
              <template slot-scope="scope">
                <el-row type="flex" class="row-bg" justify="center">
                  <el-card :body-style="{ padding: '0px 20px'  }">
                    <span>
                      {{ scope.row.reply_num }}
                    </span>
                  </el-card>
                </el-row>
              </template>
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
              align="center"
              width="150">
              <template slot-scope="scope" v-if="scope.row.userInfo != null">
                <img class="head-img" v-if="scope.row.userInfo.headImage != null" :src="$url + '/images/' + scope.row.userInfo.headImage"/>
                <el-button type="text">{{ scope.row.userInfo.nickname }}</el-button>
            <!--    <a>id : {{ scope.row.user_id }}</a> -->
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
                      <el-button type="text" v-if="scope.row.lastUserInfo != null">{{ scope.row.lastUserInfo.nickname }}</el-button>
                    </el-row>
                  </el-col>
                  <el-col>
                  </el-col>
                </el-row>
              </template>
            </el-table-column>
            <el-table-column
              v-if="user_type > 0"
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
      <!--分页显示插件-->
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
      <!--主题贴标题和内容-->
      <el-input
        placeholder="请输入内容"
        suffix-icon="el-icon-date"
        width="180"
        v-model="forumTopicPO.title"
        clearable>
        <template slot="prepend">标题</template>
      </el-input>
      <QuillEditor ref="quillEditor"></QuillEditor>
      <el-button style="margin:5px 0px;" type="success" plain @click="postData()">发表</el-button>
    </el-footer>
  </el-container>
</div>
</template>

<script>
import QuillEditor from '@/components/quillEditor'
export default {
  name: 'Blog',
  data () {
    return {
      user_type: 0,
      section_name: '公共',
      conditionSelect: '',
      forumTopicLength: 0,
      page: 1,
      rows: 10,
      deleteTopicVisible: false,
      dialogFormVisible: false,
      deletevisible: false,
      loading: true,
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
      currentSection: {},
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
  created: function () {
    this.queryByCondition()
    this.querySectionList()
    this.checkusertype()
  },
  watch: {
    // 监听当前页面
    page: {
      handler: function (val, oldval) {
        this.queryByCondition()
      }
    },
    // 监听页面大小
    rows: {
      handler: function (val, oldval) {
        this.queryByCondition()
      }
    }
  },
  components: {
    QuillEditor
  },
  methods: {
    // 发表帖子
    postData () {
      var t = this
      if (this.forumTopicPO.title === '' || this.$refs.quillEditor.content === '') {
        this.errorMessageSave()
      } else {
        this.$axios({
          method: 'post',
          url: t.$url + '/forumtopic/save',
          dataType: 'json',
          headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + t.$getCookie('otod_access_token')
          },
          data: {
            section_id: this.condition.section_id,
            user_id: this.condition.user_id,
            content: this.$refs.quillEditor.content,
            title: this.forumTopicPO.title
          }
        }).then(function (response) {
          // console.log(response)
          t.successMessageSave()
          // 提交完刷新一次数据
          t.queryByCondition()
          // 清空标题和内容
          t.$refs.quillEditor.content = ''
          t.forumTopicPO.title = ''
        }).catch(function (error) {
          console.log(error)
        })
      }
    },

    queryByCondition () {
      var t = this
      this.$axios({
        method: 'post',
        url: this.$url + '/forumtopic/findbycondition',
        dataType: 'json',
        data: {
          section_id: this.condition.section_id,
          title: this.condition.title,
          page: this.page - 1,
          row: this.rows
        }
      }).then(function (response) {
        // console.log(response)
        // 为主题帖赋值，为总数度赋值
        t.forumTopicList = response.data.pageList.content
        t.forumTopicLength = response.data.pageList.totalElements
        // 切换日期显示
        for (var i in t.forumTopicList) {
          // 获得距今时间
          t.forumTopicList[i]['last_time'] = t.getLastTime(t.forumTopicList[i].lastReplyDate)
          var date = new Date(t.forumTopicList[i].date)
          if (date.getFullYear() < (new Date().getFullYear())) {
            t.forumTopicList[i].date = date.getFullYear() + '年' + (date.getMonth() + 1) + '月' + date.getDate() + '日'
          } else {
            t.forumTopicList[i].date = (date.getMonth() + 1) + '月' + date.getDate() + '日 ' + date.getHours()
            if (date.getMinutes() < 10) {
              t.forumTopicList[i].date = t.forumTopicList[i].date + ':0' + date.getMinutes()
            } else {
              t.forumTopicList[i].date = t.forumTopicList[i].date + ':' + date.getMinutes()
            }
          }
          t.section_name = t.forumTopicList[0].sectionInfoPO.name
          // 去除加载图案
          t.loading = false
          // 添加删除弹框可视变量
          t.forumTopicList[i].deletevisible = false
        }
      }).catch(function (error) {
        console.log(error)
      })
    },

    // 根据id查找主题帖
    test () {
      this.$axios.get(this.$url + '/forumtopic/findbyid?id=1').then(function (response) {
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
        url: this.$url + '/forumtopic/findbypage',
        dataType: 'jsonp',
        params: {
          page: t.page,
          row: t.rows
        }
      }).then(function (response) {
        // console.log(response)
        t.forumTopicList = response.data.content
        t.forumTopicLength = response.data.totalElements

        for (var i in t.forumTopicList) {
          t.forumTopicList[i]['last_time'] = t.getLastTime(t.forumTopicList[i].date)
          var date = new Date(t.forumTopicList[i].date)
          if (date.getFullYear() < (new Date().getFullYear())) {
            t.forumTopicList[i].date = date.getFullYear() + '年' + (date.getMonth() + 1) + '月' + date.getDate() + '日'
          } else {
            t.forumTopicList[i].date = (date.getMonth() + 1) + '月' + date.getDate() + '日'
          }
        }
      }).catch(function (error) {
        console.log(error)
      })
    },

    alee () {
      alert(this.user_type)
    },

    // 版块列表查询
    querySectionList () {
      var t = this
      this.$axios({
        method: 'get',
        url: this.$url + '/sectioninfo/findbypage',
        dataType: 'jsonp',
        params: {
          page: t.page - 1,
          row: t.rows
        }
      }).then(function (response) {
        if (t.$route.query.section_id) {
          t.condition.section_id = t.$route.query.section_id
        }
        // console.log(response)
        t.sectionList = response.data.content
        t.querySectionById(1)
      }).catch(function (error) {
        console.log(error)
      })
    },

    // 版块id查询
    querySectionById (id) {
      var t = this
      this.$axios.get(this.$url + '/sectioninfo/findbyid?id=' + id).then(function (response) {
      //  console.log(response)
        t.currentSection = response.data
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
        result = (oldtimedate.getMonth() + 1) + '月' + oldtimedate.getDate() + '日'
      } else if (_week >= 1) {
        result = (oldtimedate.getMonth() + 1) + '月' + oldtimedate.getDate() + '日'
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
    // 改变版块名称

    // 改变当前版块
    changesection_id (val) {
      this.condition.section_id = val
    },

    changeSection (id) {
      this.changepage(1)
      this.changesection_id(id)
      this.checkusertype()
    //  this.queryByCondition()
    },
    // 删除主题帖
    deletebyid (id) {
      var t = this
      this.$axios.get(this.$url + '/forumtopic/deletebyid?id=' + id).then(function (response) {
        // console.log(response)
        t.successMessageDelete()
        t.queryByCondition()
      }).catch(function (error) {
        console.log(error)
      })
    },

    // 检查用户类型
    checkusertype () {
      var t = this
      t.loading = true
      this.$axios({
        method: 'get',
        url: this.$url + '/sectioninfo/checkusertype',
        dataType: 'jsonp',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + t.$getCookie('otod_access_token')
        },
        params: {
          section_id: t.condition.section_id
        }
      }).then(function (response) {
        // console.log(response)
        t.user_type = response.data
        // 因为每次点击版块都要检查用户类型，为了顺序将查询放入
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

    // 标题未写错误信息
    errorMessageSave () {
      this.$message({
        showClose: true,
        message: '发表失败，请填写标题和内容',
        type: 'error'
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

    updateSection () {
      var t = this
      this.$axios({
        method: 'post',
        url: this.$url + '/sectioninfo/update',
        dataType: 'jsonp',
        data: {
          section_id: t.condition.section_id,
          name: t.currentSection.name,
          statement: t.currentSection.statement
        }
      }).then(function (response) {
        console.log(response)
      }).catch(function (error) {
        console.log(error)
      })
    },

    // 版块修改，管理员模块
    removeAdmin (item) {
      var index = this.currentSection.sectionAdminPOs.indexOf(item)
      if (index !== 0) {
        this.currentSection.sectionAdminPOs.splice(index, 1)
      }
    },
    addAdmin () {
      this.currentSection.sectionAdminPOs.push({
        userInfo: '',
        key: Date.now()
      })
    },
    handlePreview (file) {
      console.log(file)
    }
  }
}
</script>

<style>
    @import '../assets/css/Blog.css';
</style>
