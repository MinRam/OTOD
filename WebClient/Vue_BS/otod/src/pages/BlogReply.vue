<template>
  <div>
    <el-container style="width:80%;margin:auto;">
      <el-header>
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>博客</el-breadcrumb-item>
          <el-breadcrumb-item>{{ section_name }}</el-breadcrumb-item>
          <el-breadcrumb-item>{{ topic_title }}</el-breadcrumb-item>
        </el-breadcrumb>
      </el-header>
      <el-main>
        <el-row type="flex" class="row-bg" justify="center">
          <div class="block">
            <el-table
              :data="pagelist"
              v-loading="loading"
              stripe
              border
              style="width: 100%">
              <el-table-column
                label="用户"
                width="200">
                <template slot-scope="scope">
                  <el-button type="text">{{ scope.row.userInfo.nickname }}</el-button>
                  <br>
                  <el-button type="text">id :{{ scope.row.user_id }}</el-button>
                </template>
              </el-table-column>
              <el-table-column
                label="内容"
                width="1000">
                <template slot-scope="scope">
                  <el-row :gutter="1">
                    <el-col :span="1" :offset="19">
                      <span>{{ scope.row.insideNum }} 楼  </span>
                    </el-col>
                    <el-col :span="4">
                      <span>{{ scope.row.date }}</span>
                    </el-col>
                  </el-row>
                  <el-row type="flex" class="row-bg" justify="start">
                    <quillEditor v-model="scope.row.content" :options="showEditorOption"></quillEditor>
                  </el-row>
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
              :total="forumReplyLength">
            </el-pagination>
          </div>
        </el-row>
      </el-main>
      <el-footer>
        <QuillEditor ref="quillEditor"></QuillEditor>
        <el-button type="success" plain @click="save()">发表</el-button>
      </el-footer>
    </el-container>
  </div>
</template>

<script>
import { quillEditor } from 'vue-quill-editor'
import QuillEditor from '@/components/quillEditor'
export default {
  name: 'BlogReply',
  data () {
    return {
      id: this.$route.query.id,
      page: 1,
      rows: 10,
      forumReplyLength: 0,
      loading: true,
      section_name: '',
      topic_title: '',
      forumReplyPO: {
        topic_id: this.$route.query.id,
        date: '',
        user_id: '',
        content: ''
      },
      pagelist: [
        {}
      ],
      showEditorOption: {
        modules: {
          toolbar: false
        },
        theme: 'bubble',
        readOnly: true
      }
    }
  },
  mounted: function () {
    // 键盘监听注册
    this.queryByConditions()
  },
  watch: {
    page: {
      handler: function (val, oldval) {
        this.queryByConditions()
      }
    },
    rows: {
      handler: function (val, oldval) {
        this.queryByConditions()
      }
    }
  },
  components: {
    QuillEditor,
    quillEditor
  },
  methods: {
    save () {
      var t = this
      if (this.$refs.quillEditor.content === '') {
        this.errorMessageSave()
      } else {
        this.$axios({
          method: 'post',
          url: 'http://localhost:8081/forumreply/save',
          dataType: 'json',
          headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + t.$getCookie('otod_access_token')
          },
          data: {
            user_id: this.forumReplyPO.user_id,
            content: this.$refs.quillEditor.content,
            topic_id: this.$route.query.id
          }
        }).then(function (response) {
          console.log(response)
          t.successMessageSave()
          // 提交完刷新一次数据
          t.queryByConditions()
          // 内容清空
          t.$refs.quillEditor.content = ''
          t.$refs.quillEditor.content = ''
        }).catch(function (error) {
          console.log(error)
        })
      }
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
          page: this.page - 1,
          row: this.rows,
          topic_id: this.id
        }
      }).then(function (response) {
        console.log(response)
        t.pagelist = response.data.content
        t.forumReplyLength = response.data.totalElements
        for (var i in t.pagelist) {
          var date = new Date(t.pagelist[i].date)
          if (date.getFullYear() < (new Date().getFullYear())) {
            t.pagelist[i].date = date.getFullYear() + '年' + (date.getMonth() + 1) + '月' + date.getDate() + '日'
          } else {
            t.pagelist[i].date = (date.getMonth() + 1) + '月' + date.getDate() + '日 ' + date.getHours()
            if (date.getMinutes() < 10) {
              t.pagelist[i].date = t.pagelist[i].date + ':0' + date.getMinutes()
            } else {
              t.pagelist[i].date = t.pagelist[i].date + ':' + date.getMinutes()
            }
          }
        }
        // 为标题赋值
        if (t.section_name === '') {
          t.section_name = t.pagelist[0].forumTopicPO.sectionInfoPO.name
        }
        if (t.topic_title === '') {
          t.topic_title = t.pagelist[0].forumTopicPO.title
        }
        t.loading = false
        t.topic_title = t.pagelist[0].forumTopicPO.title
      }).catch(function (error) {
        console.log(error)
      })
    },

    // 改变页面
    changepage (val) {
      this.page = val
    },

    // 改变页面大小
    changerows (val) {
      this.rows = val
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
        message: '发表失败，请填写标题',
        type: 'error'
      })
    }
  }
}
</script>
<style>
</style>
