<template>
    <div>
        <div style="margin-top: 150px;"></div>
        <el-row>
            <el-col :span="12" :offset="4">
                <el-ul>
                    <el-li v-for="m in message" :key="m.id">
                        <el-row class="message-bottom" type="flex" justify="center">
                            <el-col :span="24">
                                <el-card shadow="hover">
                                    <div v-if="m.emergency === '1'" style="height: 3px; clear: both; width: 100%" class="bg-danger"></div>
                                    <div v-if="m.emergency === '2'" style="height: 3px; clear: both; width: 100%" class="bg-warming"></div>
                                    <div v-if="m.emergency === '3'" style="height: 3px; clear: both; width: 100%" class="bg-success"></div>
                                    <div v-if="m.emergency === '4'" style="height: 3px; clear: both; width: 100%" class="bg-info"></div>
                                    <el-container class="bg-purple">
                                        <el-aside class="" width="200px">
                                            <div>
                                                <div class="user-img">
                                                    <i class="el-icon-menu" style="font-size: 60px;color: #409EFF"></i>
                                                </div>
                                                <div class="user-info">
                                                    <p>zhangyizhuo</p>
                                                    <!-- <p>Nothing</p> -->
                                                </div>
                                                <div style="clear: both"></div>
                                            </div>
                                            <div class="button-group">
                                                <div style="margin-bottom: 10px;">
                                                    <el-button type="success" icon="el-icon-check" circle @click="sstatic = !sstatic" @mouseover.native="show = !show" @mouseout.native="show = !show"></el-button>
                                                    <transition name="el-zoom-in-left">
                                                        <div v-show="show || sstatic" class="commit-button-box"></div>
                                                    </transition>
                                                    <div style="clear: both"></div>
                                                </div>
                                                <div style="margin-bottom: 10px;">
                                                    <el-button type="success" icon="el-icon-check" circle @click="sstatic1 = !sstatic1" @mouseover.native="show1 = !show1" @mouseout.native="show1 = !show1"></el-button>
                                                    <transition name="el-zoom-in-left">
                                                        <div v-show="show1 || sstatic1" class="commit-button-box"></div>
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
            </el-col>
        </el-row>
    </div>
</template>

<script>
export default {
  name: 'Index',
  data () {
    return {
      sstatic: false,
      show: false,
      sstatic1: false,
      show1: false,
      message: [{
        title: '标题一',
        content: 'Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !',
        emergency: '1'
      }, {
        title: '帮忙购买炒酸奶',
        content: 'Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !',
        emergency: '2'
      }, {
        title: '帮忙拿快递，快递号131313131',
        content: 'Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !',
        emergency: '3'
      }, {
        title: '我也不知道该写些什么就这样吧',
        content: 'Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !Content !',
        emergency: '4'
      }]
    }
  }
}
</script>
<style>
    @import '../assets/css/servicePage.css';
</style>
