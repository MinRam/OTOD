<template>
    <div>
        <div class="setting-main">
            <div class="setting-item setting-title">
                <h2>
                    <a>返回</a>
                    账号设置
                </h2>
             </div>

            <div class="setting-item setting-phone">
                <div class="item-title">
                    <h3 class="title">手机绑定</h3>
                </div>
                <div class="item-content">
                    <div class="telephone-content">
                        <p class="txt">为你的账号绑定一个手机号，绑定后可开放更多功能</p>
                        <a class="link">绑定手机号</a>
                     </div>
                 </div>
             </div>

            <div class="setting-item setting-oauth">
                <div class="item-title">
                    <h3 class="title">社交账号</h3>
                 </div>
                <div class="item-content">
                    <div class="oauth-content">
                        <p class="txt">绑定社交帐号，绑定后可以使用以下帐号直接登录OTOD</p>
                        <p class="connection">
                            <span class="icon icon-xina">
                                <span class="connection-name txt">新浪微博</span>
                                <a>(绑定)</a>
                             </span>
                            <span class="icon icon-qq">
                                <span class="connection-name txt">腾讯QQ</span>
                                <a>(绑定)</a>
                             </span>
                            <span class="icon icon-weixin">
                                <span class="connection-name txt">微信</span>
                                <a>(绑定)</a>
                             </span>
                         </p>
                     </div>
                 </div>
             </div>

            <div class="setting-item setting-info">
                <div class="item-title">
                    <h3 class="title">个人信息</h3>
                 </div>
                <div class="item-content">
                    <div class="info-head">
                        <div class="user-head">
                            <img :src="$imageUrl + $store.state.headImage"/>
                         </div>
                        <div class="user-uploader">
                            <el-upload
                              class="img-uploader"
                              action="https://jsonplaceholder.typicode.com/posts/"
                              :show-file-list="false"
                              :on-success="handleAvatarSuccess"
                              :before-upload="beforeAvatarUpload">
                                <img v-if="imageUrl" :src="imageUrl" class="avatar">
                                <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                            </el-upload>
                         </div>
                    </div>
                 </div>
             </div>

            <div class="setting-item setting-save">
                <div class="m-set">
                    <div class="btns">
                        <a href="#" class="w-btn save">保&nbsp;&nbsp;存</a>
                     </div>
                </div>
             </div>
        </div>
 <!--        <div class="setting-footer"></div> -->
    </div>
</template>

<script >
export default {
  name: 'Setting',
  data () {
    return {
      userInfo: {

      }
    }
  },
  mounted () {
    // get user all info
    this.$axios({
      method: 'get',
      url: this.$url + '/user/getAllInfo'
    }).then(function (response) {
      if (response.data !== null) {
        this.userInfo = response.data
      }
    }.bind(this))
  },
  methods: {
    handleAvatarSuccess (res, file) {
      this.imageUrl = URL.createObjectURL(file.raw)
    },
    beforeAvatarUpload (file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
    }
  }
}
</script>

<style>
    @import '../assets/css/SettingPage.css'
</style>
