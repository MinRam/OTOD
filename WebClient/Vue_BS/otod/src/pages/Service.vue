<template>
    <div>
        <el-row :gutter="40" style="min-width: 1000px;">
            <router-view/>
            <div class="right-container-menu">
                <el-menu
                    default-active="2"
                    class="right-side-menu"
                    background-color="#545c64"
                    text-color="#fff"
                    active-text-color="#ffd04b"
                    :collapse="isCollapse">
                    <el-menu-item @click="navClick('/service/publishorder')" index="1">
                        <i class="el-icon-menu"></i>
                        <span slot="title">发布订单</span>
                    </el-menu-item>
                    <el-menu-item id="order-list" @click="navClick('/service/orderlist')" index="2">
                        <i class="el-icon-menu"></i>
                        <span slot="title">订单列表</span>
                    </el-menu-item>
                    <el-submenu index="3">
                        <template slot="title">
                            <i class="el-icon-location"></i>
                            <span>我的求助</span>
                        </template>
                        <el-menu-item @click="navClick('/service/allorder')" index="3-1">所有求助</el-menu-item>
                        <el-menu-item index="3-2">待支付</el-menu-item>
                        <el-menu-item @click="navClick('/service/waitingrequest')" index="3-3">待接单</el-menu-item>
                        <el-menu-item @click="navClick('/service/runnings')" index="3-4">进行中</el-menu-item>
                        <el-menu-item @click="navClick('/service/waitingevaluates')" index="3-5">待评价</el-menu-item>
                        <el-menu-item @click="navClick('/service/finished')" index="3-6">已完成</el-menu-item>
                        <el-menu-item index="3-7">失败求助</el-menu-item>
                    </el-submenu>
                    <el-submenu index="4">
                        <template slot="title">
                            <i class="el-icon-document"></i>
                            <span slot="title">我的订单</span>
                        </template>
                        <el-menu-item @click="navClick('/service/rmyallorders')" index="4-1">所有订单</el-menu-item>
                        <el-menu-item @click="navClick('/service/rrecivedorders')" index="4-2">已接受</el-menu-item>
                        <el-menu-item @click="navClick('/service/rwaitingcommentorders')" index="4-3">待评价</el-menu-item>
                        <el-menu-item @click="navClick('/service/rdoneorders')" index="4-4">已完成</el-menu-item>
                        <el-menu-item @click="navClick('/service/rfailedorders')" index="4-5">失败订单</el-menu-item>
                    </el-submenu>
                </el-menu>
            </div>
        </el-row>
    </div>
</template>

<script>
export default {
  name: 'Index',
  data () {
    return {
      isCollapse: false,
      sstatic: false,
      show: false,
      sstatic1: false,
      show1: false,
      message: '',
      page: [{
        max: 100
      }]
    }
  },
  mounted () {
  },
  methods: {
    getAllServices () {
      var t = this
      t.$axios.get(this.$url + '/allServices')
        .then(function (response) {
          t.message = response.data.commenOrders
          console.log(this.message)
        })
        .catch(function (error) {
          console.log(error.message)
        })
    },
    navClick (path) {
      this.$router.push(path)
    }
  }
}
</script>
<style>
    @import '../assets/css/servicePage.css';
</style>
