<template>
    <el-col :xs="12" :sm="12" :md="12" :xl="12" :offset="4">
        <ul v-loading="loadingOrder">
          <!-- 这个是element的特有写法 v-for 就是一个循环 循环输出<li>里面的html message是一个数组，里面存着order 相当于for(m in message){ <li>里面的代码</li>} -->
            <li v-show="message.length != 0" v-for="m in message" :key="m.id">
                <el-row class="message-bottom" type="flex" justify="center">
                    <el-col :span="24">
                        <el-card shadow="hover" class="center-container-card">
                          <!-- v-if 也是element里面特有的写法 如果里面的判断为真就显示 为假就不显示 m.urgency是message里面的一个数据 就和c里面的strut一样 如果里面还有其他的话就一直。。。下去 -->
                            <div v-if="m.urgency == '4'" style="height: 3px; clear: both; width: 100%" class="bg-danger"></div>
                            <div v-if="m.urgency == '3'" style="height: 3px; clear: both; width: 100%" class="bg-warning"></div>
                            <div v-if="m.urgency == '2'" style="height: 3px; clear: both; width: 100%" class="bg-success"></div>
                            <div v-if="m.urgency == '1'" style="height: 3px; clear: both; width: 100%" class="bg-info"></div>
                            <!-- el-container是一个element封装好的写法 你可以到 http://element-cn.eleme.io/#/zh-CN 里面看它的实例，想用的话之间复制他的代码自己改 -->
                            <el-container class="bg-purple">
                                <el-aside class="aside-container" width="200px">
                                    <div>
                                        <div class="user-img">
                                            <i v-if="m.userinfoS == null" class="el-icon-menu" style="font-size: 60px;color: #409EFF"></i>
                                            <img v-if="m.userinfoS != null" class="user-img" :src="$url + '/images/' + m.userinfoS.headImage"/>
                                        </div>
                                        <div class="user-info">
                                          <!-- {{ }} 是一种输出，就像printf一样，把里面的内容输出来 -->
                                            <p v-if="m.userinfoS != null">{{ m.userinfoS.nickname }}</p>
                                            <!-- <p>Nothing</p> -->
                                        </div>
                                        <div style="clear: both"></div>
                                    </div>
                                    <div class="button-group">
                                        <div style="margin-bottom: 10px;">
                                            <el-button type="primary" icon="el-icon-edit" circle @click="openDialog(m.id)" @mouseover.native="show1 = !show1" @mouseout.native="show1 = !show1"></el-button>
                                            <transition name="el-zoom-in-left">
                                                <div v-show="show1 || sstatic1" class="commit-button-box bg-primary">
                                                    <span>编辑订单</span>
                                                </div>
                                            </transition>
                                            <div style="clear: both"></div>
                                        </div>
                                        <div style="margin-bottom: 10px;">
                                            <el-button type="danger" icon="el-icon-delete" circle @click="open6(m.id)" @mouseover.native="show2 = !show2" @mouseout.native="show2 = !show2"></el-button>
                                            <transition name="el-zoom-in-left">
                                                <div v-show="show2 || sstatic2" class="commit-button-box bg-danger">
                                                    <span>删除求助！</span>
                                                </div>
                                            </transition>
                                            <div style="clear: both"></div>
                                        </div>
                                    </div>
                                </el-aside>
                                <el-container>
                                    <el-header class="header-container"><strong>{{ m.title }}</strong></el-header>
                                    <el-main><p class="order-content">{{ m.content }}</p></el-main>
                                </el-container>
                            </el-container>
                        </el-card>
                    </el-col>
                </el-row>
            </li>
        </ul>
          <el-card v-show="message.length == 0" shadow="hover" class="center-container-card">
            <p>没有任何信息哦</p>
          </el-card>
        <el-dialog :visible.sync="showFlag">
            <el-container>
                <el-header class="po-header-container">编辑订单！</el-header>
                <el-main>
                    <el-form :model="form" :label-position="labelPosition">
                        <el-form-item>
                            <el-input v-model="form.title" require>
                                <template slot="prepend">标题：</template>
                            </el-input>
                        </el-form-item>
                        <el-form-item label="内容" label-width="80px">
                            <el-input type="textarea" :autosize="{ minRows: 4}" v-model="form.content"></el-input>
                        </el-form-item>
                        <el-form-item label="时间" label-width="80px">
                            <el-date-picker type="date" placeholder="选择结束日期" v-model="form.deadline" :pickerOptions="pickerOptions0"></el-date-picker>
                        </el-form-item>
                        <el-form-item label="人数需求" label-width="80px">
                            <el-select v-model="form.contributer" clearable placeholder="需要多少人？">
                                <el-option
                                    v-for="item in form.contributers_op"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="紧急程度" label-width="80px">
                            <el-select v-model="form.urgency" clearable placeholder="有多紧急？">
                                <el-option
                                    v-for="item in form.urgency_op"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                                    <span v-if="item.value == 1" style="float: left; color: #909399">{{ item.label }}</span>
                                    <span v-if="item.value == 2" style="float: left; color: #67C23A">{{ item.label }}</span>
                                    <span v-if="item.value == 3" style="float: left; color: #E6A23C">{{ item.label }}</span>
                                    <span v-if="item.value == 4" style="float: left; color: #F56C6C">{{ item.label }}</span>
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-form>
                </el-main>
                <el-footer class="po-footer-container">
                    <el-col :span="24">
                        <el-button type="primary" style="width: 100%;" @click="submit()">发布按钮</el-button>
                    </el-col>
                </el-footer>
            </el-container>
        </el-dialog>
    </el-col>
</template>

<script>
export default {
  name: 'Service',
  data () {
    return {
      navSelected: 2,
      isCollapse: false,
      sstatic: false,
      show: false,
      sstatic1: false,
      show1: false,
      sstatic2: false,
      show2: false,
      message: '',
      totalPages: 0,
      currentPage: 0,
      loadingOrder: true,
      size: 5,
      showFlag: false,
      form: {
        title: '',
        content: '',
        contributers_op: [{
          value: '1',
          label: '一个人'
        }, {
          value: '2',
          label: '两个人'
        }, {
          value: '3',
          label: '三个人'
        }, {
          value: '4',
          label: '四个人'
        }, {
          value: '5',
          label: '五个人'
        }, {
          value: '6',
          label: '六个人'
        }, {
          value: '7',
          label: '七个人'
        }, {
          value: '8',
          label: '八个人'
        }, {
          value: '9',
          label: '九个人'
        }, {
          value: '10',
          label: '十个人'
        }],
        contributer: '',
        urgency_op: [{
          value: '1',
          label: '闲置'
        }, {
          value: '2',
          label: '正常'
        }, {
          value: '3',
          label: '紧急'
        }, {
          value: '4',
          label: '严重'
        }],
        urgency: '',
        s_date: '',
        deadline: ''
      },
      labelPosition: 'left',
      pickerOptions0: {
        disabledDate (time) {
          return time.getTime() < Date.now() - 8.64e7
        }
      },
      ssId: ''
    }
  },
  mounted () {
    this.getWaitingRequest()
  },
  methods: {
    getWaitingRequest () {
      var t = this
      t.loadingOrder = true
      this.$axios({
        url: t.$url + '/waitingRequests',
        method: 'get',
        params: {
          access_token: this.$getCookie('otod_access_token')
        }
      })
        .then(function (response) {
          console.log(response)
          t.message = response.data
          t.loadingOrder = false
          console.log(t.totalPages)
        })
        .catch(function (error) {
          console.log(error.message)
        })
    },
    // getServicePage (currentPage) {
    //   var t = this
    //   t.loadingOrder = true
    //   t.$axios.get(t.$url + '/listServices?currentPage=' + (currentPage - 1) + '&size=' + t.size)
    //     .then(function (response) {
    //       console.log(t.$url + '/listServices?currentPage=' + (currentPage - 1) + '&size=' + t.size)
    //       t.message = response.data.content
    //       t.totalPages = response.data.totalPages * t.size
    //       t.loadingOrder = false
    //       console.log(t.totalPages)
    //     })
    //     .catch(function (error) {
    //       console.log(error.message)
    //     })
    // }
    deleteOrder (id) {
      var t = this
      t.$axios({
        method: 'get',
        url: t.$url + '/sDeleteOrder',
        params: {
          OrderId: id
        }
      })
        .then(function (response) {
          console.log(response)
          if (response.data === 'null order') {
            t.$message({
              showClose: true,
              message: '没有此订单',
              type: 'danger'
            })
          } else if (response.data === 'delete!') {
            t.$message({
              showClose: true,
              message: '删除成功！',
              type: 'success'
            })
          } else if (response.data === 'state failed!') {
            t.$message({
              showClose: true,
              message: '求助已被接收！',
              type: 'danger'
            })
          } else if (response.data === 'wrong state!') {
            t.$message({
              showClose: true,
              message: '求助已被接收！',
              type: 'danger'
            })
          }
          t.getWaitingRequest()
        })
        .catch(function (error) {
          console.log(error)
        })
    },
    open6 (id) {
      var t = this
      t.ssId = id
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        roundButton: true,
        center: true
      }).then(function () {
        console.log(t.ssId)
        t.deleteOrder(t.ssId)
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    openDialog (id) {
      this.showFlag = true
      this.ssId = id
      var t = this
      t.$axios({
        method: 'get',
        url: t.$url + '/getCommenOrder',
        params: {
          OrderId: id
        }
      })
        .then(function (response) {
          console.log(response)
          t.form.title = response.data.title
          t.form.content = response.data.content
          t.form.deadline = response.data.deadline
          t.form.contributer = response.data.contributers
          t.form.urgency = response.data.urgency
        })
        .catch(function (error) {
          console.log(error)
        })
    },
    submit () {
      console.log(this.$getCookie('otod_access_token'))
      if (this.form.title === '') {
        this.$message({
          showClose: true,
          message: '警告哦，你的标题没有写',
          type: 'warning'
        })
      } else if (this.form.content === '') {
        this.$message({
          showClose: true,
          message: '警告哦，你的内容没有写',
          type: 'warning'
        })
      } else if (this.form.deadline === '') {
        this.$message({
          showClose: true,
          message: '警告哦，你的结束日期没有写',
          type: 'warning'
        })
      } else if (this.form.urgency === '') {
        this.$message({
          showClose: true,
          message: '警告哦，你的紧急程度没有写',
          type: 'warning'
        })
      } else if (this.form.contributers === '') {
        this.$message({
          showClose: true,
          message: '警告哦，你的所需人数没有写',
          type: 'warning'
        })
      } else {
        var t = this
        this.$axios({
          method: 'post',
          url: t.$url + '/saveOrder',
          headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + t.$getCookie('otod_access_token')
          },
          data: {
            id: t.ssId,
            title: t.form.title,
            content: t.form.content,
            deadline: t.form.deadline,
            urgency: t.form.urgency,
            contributers: t.form.contributer
          }
        })
          .then(function (response) {
            t.form = ''
            t.$message({
              showClose: true,
              message: '修改成功！~',
              type: 'success'
            })
            t.showFlag = false
            t.getWaitingRequest()
            // document.getElementById('order-list').click()
            console.log(response)
          })
          .catch(function (error) {
            console.log(error)
          })
        // console.log(orderPOJO)
      }
    }
  }
}
</script>

<style>
    @import '../../assets/css/OrderList.css'
</style>
