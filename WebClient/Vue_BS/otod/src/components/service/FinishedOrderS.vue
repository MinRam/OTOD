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
<!--                                     <div class="button-group">
                                        <div style="margin-bottom: 10px;">
                                            <el-button type="success" icon="el-icon-check" circle @click="sstatic = !sstatic" @mouseover.native="show = !show" @mouseout.native="show = !show"></el-button>
                                            <transition name="el-zoom-in-left">
                                                <div v-show="show || sstatic" class="commit-button-box bg-success"></div>
                                            </transition>
                                            <div style="clear: both"></div>
                                        </div>
                                        <div style="margin-bottom: 10px;">
                                            <el-button type="warning" icon="el-icon-arrow-right" circle @click="sstatic1 = !sstatic1" @mouseover.native="show1 = !show1" @mouseout.native="show1 = !show1"></el-button>
                                            <transition name="el-zoom-in-left">
                                                <div v-show="show1 || sstatic1" class="commit-button-box bg-warning">
                                                    <span>我需要{{ m.contributers }}个人</span>
                                                </div>
                                            </transition>
                                            <div style="clear: both"></div>
                                        </div>
                                    </div> -->
                                    <div v-if="m.orderEval !== null">
                                      <div v-show="m.orderEval.rTitle !== null">
                                        <p style="color: #67c23a">接收方评价：</p>
                                        <p><span>标题：</span>{{ m.orderEval.rTitle }}</p>
                                        <p><span>内容：</span>{{ m.orderEval.rContent }}</p>
                                        <p><span>评级：</span>{{ level[m.orderEval.rNum] }}</p>
                                      </div>
                                      <div v-show="m.orderEval.rTitle === null">
                                        <p style="color: #e6a23c">接收方未作出评价</p>
                                      </div>
                                      <div style="height: 1px;width: 100%;background-color: #f5f5f5;margin: 10px 0;"></div>
                                      <div v-show="m.orderEval.sTitle !== null">
                                        <p style="color: #67c23a">求助方评价：</p>
                                        <p><span>标题：</span>{{ m.orderEval.sTitle }}</p>
                                        <p><span>内容：</span>{{ m.orderEval.sContent }}</p>
                                        <p><span>评级：</span>{{ level[m.orderEval.sNum] }}</p>
                                      </div>
                                      <div v-show="m.orderEval.sTitle === null">
                                        <p style="color: #e6a23c">求助方未作出评价</p>
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
      level: [
        '',
        '太差了！',
        '比较差',
        '一般般',
        '还可以',
        '超棒的！'
      ]
    }
  },
  mounted () {
    this.getDoneOrderst()
  },
  methods: {
    getDoneOrderst () {
      var t = this
      t.loadingOrder = true
      this.$axios({
        url: t.$url + '/finishedOrderS',
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
    }
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
  }
}
</script>

<style>
    @import '../../assets/css/OrderList.css'
</style>
