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
                                            <el-button type="success" icon="el-icon-check" circle @click="sstatic = !sstatic;finishOrder(m.id)" @mouseover.native="show = !show" @mouseout.native="show = !show"></el-button>
                                            <transition name="el-zoom-in-left">
                                                <div v-show="show || sstatic" class="commit-button-box bg-success">完成订单！</div>
                                            </transition>
                                            <div style="clear: both"></div>
                                        </div>
                                        <div style="margin-bottom: 10px;">
                                            <el-button type="danger" icon="el-icon-close" circle @click="sstatic1 = !sstatic1;open(m.id)" @mouseover.native="show1 = !show1" @mouseout.native="show1 = !show1"></el-button>
                                            <transition name="el-zoom-in-left">
                                                <div v-show="show1 || sstatic1" class="commit-button-box bg-danger">
                                                    <span>取消订单</span>
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
      message: '',
      totalPages: 0,
      currentPage: 0,
      loadingOrder: 'true',
      size: 5,
      ssId: ''
    }
  },
  mounted () {
    this.getRecivedOrders()
  },
  methods: {
    getRecivedOrders () {
      var t = this
      t.loadingOrder = true
      this.$axios({
        url: t.$url + '/rRecivedOrders',
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
    finishOrder (id) {
      var t = this
      t.$axios({
        method: 'get',
        url: t.$url + '/rFinishOrder',
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
          } else if (response.data === 'not the running order') {
            t.$message({
              showClose: true,
              message: '该订单状态不为已接受',
              type: 'danger'
            })
          } else if (response.data === 'order finished!') {
            t.$message({
              showClose: true,
              message: '完成订单！',
              type: 'success'
            })
            document.getElementById('rwaitingcommentorders').click()
          } else if (response.data === 'false') {
            t.$message({
              showClose: true,
              message: '未知错误',
              type: 'danger'
            })
          }
          t.getRecivedOrders()
        })
        .catch(function (error) {
          console.log(error)
        })
    },
    open (id) {
      var t = this
      t.ssId = id
      this.$confirm('此操作将取消订单并作记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        roundButton: true,
        center: true
      }).then(function () {
        console.log(t.ssId)
        t.cancelOrder(t.ssId)
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    },
    cancelOrder (id) {
      var t = this
      t.$axios({
        method: 'get',
        url: t.$url + '/rCancelOrder',
        params: {
          OrderId: id,
          access_token: this.$getCookie('otod_access_token')
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
          } else if (response.data === 'not the running order') {
            t.$message({
              showClose: true,
              message: '该订单状态不为已接受',
              type: 'danger'
            })
          } else if (response.data === 'order canceled!') {
            t.$message({
              showClose: true,
              message: '订单已取消！',
              type: 'success'
            })
            document.getElementById('rfailedorders').click()
          } else if (response.data === 'false') {
            t.$message({
              showClose: true,
              message: '未知错误',
              type: 'danger'
            })
          } else if (response.data === 'no such userinfo') {
            t.$message({
              showClose: true,
              message: '用户不符！',
              type: 'danger'
            })
          }
          t.getRecivedOrders()
        })
        .catch(function (error) {
          console.log(error)
        })
    }
  }
}
</script>

<style>
    @import '../../assets/css/OrderList.css'
</style>
