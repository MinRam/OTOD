<template>
    <el-col :xs="12" :sm="12" :md="12" :xl="12" :offset="4">
        <ul>
            <li v-for="m in message" :key="m.id">
                <el-row class="message-bottom" type="flex" justify="center">
                    <el-col :span="24">
                        <el-card shadow="hover" class="center-container-card">
                            <div v-if="m.urgency == '4'" style="height: 3px; clear: both; width: 100%" class="bg-danger"></div>
                            <div v-if="m.urgency == '3'" style="height: 3px; clear: both; width: 100%" class="bg-warning"></div>
                            <div v-if="m.urgency == '2'" style="height: 3px; clear: both; width: 100%" class="bg-success"></div>
                            <div v-if="m.urgency == '1'" style="height: 3px; clear: both; width: 100%" class="bg-info"></div>
                            <el-container class="bg-purple">
                                <el-aside class="aside-container" width="200px">
                                    <div>
                                        <div class="user-img">
                                            <i class="el-icon-menu" style="font-size: 60px;color: #409EFF"></i>
                                        </div>
                                        <div class="user-info">
                                            <p>{{ m.user_name }}</p>
                                            <!-- <p>Nothing</p> -->
                                        </div>
                                        <div style="clear: both"></div>
                                    </div>
                                    <div class="button-group">
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
        <el-row type="flex" justify="center">
            <el-col :span="14">
                <div>
                    <el-pagination
                      @current-change="getServicePage"
                      background
                      layout="prev, pager, next"
                      :current-page="currentPage"
                      :page-size="size"
                      :total="totalPages">
                    </el-pagination>
                </div>
            </el-col>
        </el-row>
    </el-col>
</template>

<script>
export default {
  name: 'Service',
  data () {
    return {
      isCollapse: false,
      sstatic: false,
      show: false,
      sstatic1: false,
      show1: false,
      message: '',
      totalPages: 0,
      currentPage: 0,
      size: 5
    }
  },
  mounted () {
    this.getAllServices()
  },
  methods: {
    getAllServices () {
      var t = this
      t.$axios.get(this.$url + '/listServices?currentPage=' + t.currentPage + '&size=' + t.size)
        .then(function (response) {
          console.log(response)
          t.message = response.data.content
          t.totalPages = response.data.totalPages * t.size
          console.log(t.totalPages)
        })
        .catch(function (error) {
          console.log(error.message)
        })
    },
    getServicePage (currentPage) {
      var t = this
      t.$axios.get(t.$url + '/listServices?currentPage=' + (currentPage - 1) + '&size=' + t.size)
        .then(function (response) {
          console.log(t.$url + '/listServices?currentPage=' + (currentPage - 1) + '&size=' + t.size)
          t.message = response.data.content
          t.totalPages = response.data.totalPages * t.size
          console.log(t.totalPages)
        })
        .catch(function (error) {
          console.log(error.message)
        })
    }
  }
}
</script>

<style>
    @import '../assets/css/OrderList.css';
</style>
