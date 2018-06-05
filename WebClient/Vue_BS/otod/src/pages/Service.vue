<template>
    <div>
        <div style="margin-top: 150px;"></div>
        <el-row :gutter="20" style="min-width: 1000px;">
            <el-col :xs="12" :sm="12" :md="12" :xl="12" :offset="4">
                <el-ul>
                    <el-li v-for="m in message" :key="m.id">
                        <el-row class="message-bottom" type="flex" justify="center">
                            <el-col :span="24">
                                <el-card shadow="hover" class="center-container-card">
                                    <div v-if="m.emergency === '1'" style="height: 3px; clear: both; width: 100%" class="bg-danger"></div>
                                    <div v-if="m.emergency === '2'" style="height: 3px; clear: both; width: 100%" class="bg-warning"></div>
                                    <div v-if="m.emergency === '3'" style="height: 3px; clear: both; width: 100%" class="bg-success"></div>
                                    <div v-if="m.emergency === '4'" style="height: 3px; clear: both; width: 100%" class="bg-info"></div>
                                    <el-container class="bg-purple">
                                        <el-aside class="" width="200px">
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
                                            <el-header>{{ m.title }}</el-header>
                                            <el-main>{{ m.content }}</el-main>
                                        </el-container>
                                    </el-container>
                                </el-card>
                            </el-col>
                        </el-row>
                    </el-li>
                </el-ul>
                <el-row type="flex" justify="center">
                    <el-col :span="14">
                        <div>
                            <el-pagination
                              background
                              layout="prev, pager, next"
                              :total=page[0].max>
                            </el-pagination>
                        </div>
                    </el-col>
                </el-row>
            </el-col>
            <div class="right-container-menu">
                <el-menu
                    default-active="1"
                    class="right-side-menu"
                    background-color="#545c64"
                    text-color="#fff"
                    active-text-color="#ffd04b"
                    :collapse="isCollapse"
                    @open="handleOpen"
                    @close="handleClose">
                    <el-menu-item index="1">
                        <i class="el-icon-menu"></i>
                        <span slot="title">订单列表</span>
                    </el-menu-item>
                    <el-submenu index="2">
                        <template slot="title">
                            <i class="el-icon-location"></i>
                            <span>我的订单</span>
                        </template>
                        <el-menu-item index="2-1">所有订单</el-menu-item>
                        <el-menu-item index="2-2">进行中</el-menu-item>
                        <el-menu-item index="2-3">已完成</el-menu-item>
                    </el-submenu>
                    <el-submenu index="3">
                        <template slot="title">
                            <i class="el-icon-document"></i>
                            <span slot="title">我的求助</span>
                        </template>
                        <el-menu-item index="3-1">所有求助</el-menu-item>
                        <el-menu-item index="3-2">进行中</el-menu-item>
                        <el-menu-item index="3-3">已完成</el-menu-item>
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
      message: [{
        title: '标题一',
        user_name: 'zhangyz',
        content: 'Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !',
        contributers: '4',
        emergency: '1'
      }, {
        title: '帮忙购买炒酸奶',
        user_name: 'zhangyh',
        content: 'Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !',
        contributers: '2',
        emergency: '2'
      }, {
        title: '帮忙拿快递，快递号131313131',
        user_name: 'zhangyh',
        content: 'Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !',
        contributers: '1',
        emergency: '3'
      }, {
        title: '我也不知道该写些什么就这样吧',
        user_name: 'zhangyizhuo',
        content: 'Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !',
        contributers: '5',
        emergency: '4'
      }, {
        title: '我也不知道该写些什么就这样吧',
        user_name: 'DogeZhang',
        content: 'Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !',
        contributers: '6',
        emergency: '4'
      }],
      page: [{
        max: 100
      }]
    }
  }
}
</script>
<style>
    @import '../assets/css/servicePage.css';
</style>
