<template>
  <div>
    <el-container style="width:80%;margin:auto;">
      <el-header>
      </el-header>
      <el-main>
        <el-row type="flex" class="row-bg" justify="center">
          <div class="block">
            <el-table
              :data="pagelist"
              stripe
              border
              style="width: 100%">
              <el-table-column
                label="标题"
                width="200">
                <template slot-scope="scope">
                  <el-button type="text">用户昵称</el-button>
                  <br>
                  <el-button type="text">id :{{ scope.row.user_id }}</el-button>
                </template>
              </el-table-column>
              <el-table-column
                label="作者"
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
                    <span>{{ scope.row.content }}</span>
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
          type="textarea"
          :rows="8"
          placeholder="请输入内容"
          v-model="forumReplyPO.content">
        </el-input>
        <el-button type="success" plain @click="save()">发表</el-button>
      </el-footer>
    </el-container>
  </div>
</template>

<script>
export default {
  name: 'Login',
  data () {
    return {
      id: this.$route.query.id,
      page: 1,
      row: 10,
      forumTopicLength: 0,
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
          page: this.page - 1,
          row: this.row,
          topic_id: this.id
        }
      }).then(function (response) {
        console.log(response)
        t.pagelist = response.data.content
        t.forumTopicLength = response.data.totalElements
        for (var i in t.pagelist) {
          var date = new Date(t.pagelist[i].date)
          if (date.getFullYear() < (new Date().getFullYear())) {
            t.pagelist[i].date = date.getFullYear() + '年' + date.getMonth() + '月' + date.getDate() + '日'
          } else {
            t.pagelist[i].date = date.getMonth() + '月' + date.getDate() + '日 ' + date.getHours() + ':' + date.getMinutes()
          }
        }
        if (t.topic_title === '') {
          t.topic_title = t.pagelist[0].forumTopicPO.title
        }
      }).catch(function (error) {
        console.log(error)
      })
    },

    // 改变页面
    changepage (val) {
      this.page = val
    }
  }
}
</script>

<style>
</style>
