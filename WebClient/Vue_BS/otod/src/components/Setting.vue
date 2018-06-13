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
                        <p class="txt">为您的账号绑定一个手机号，绑定后可开放更多功能。</p>
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
                        <p class="txt">绑定社交帐号，绑定后可以使用以下帐号直接登录OTOD。</p>
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

            <div class="setting-item setting-interesting">
                <div class="item-title">
                    <h3 class="title">兴趣领域</h3>
                 </div>
                <div class="item-content">
                    <div class="interesting-content">
                        <p class="txt">根据兴趣向您推荐达人和内容。</p>
                        <a class="link">修改兴趣</a>
                     </div>
                 </div>
             </div>

            <div class="setting-item setting-info">
                <div class="item-title">
                    <h3 class="title">个人信息</h3>
                 </div>
                <div class="item-content">
                    <div class="interesting-content">
                        <p class="txt">完善您的信息，我们可以更准确地认识您。</p>
                    </div>
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
                    <div class="info-content">
                        <ul>
                            <li><h6>Nickname:</h6><input type="text" name="nickname" v-model="userInfo.nickname"/></li>
                            <li><h6>Position:</h6><input type="text" name="position" v-model="userInfo.position"/></li>
                            <li><h6>Sex:</h6><div style="padding:15px 0 30px;" class="sex-logo-box">
                                    <span class="sex-logo logo-checked">男</span>
                                    <span class="sex-logo">女</span>
                                    <span class="sex-logo">保密</span>
                                </div></li>
                            <li><h6>Age:</h6><input type="number" name="age" v-model="userInfo.age" /></li>
                            <li><h6>Address</h6><input type="text" name="address" v-model="userInfo.address"/></li>
                            <li><h6>email</h6><input type="email" name="email" v-model="userInfo.email"/></li>
                        </ul>
                    </div>
                 </div>
             </div>

            <div class="setting-save">
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
      imageUrl: '',
      userInfo: {
      }
    }
  },
  mounted () {
    // GET user infomation
    console.log(this.$getCookie('otod_access_token'))
    this.initialInfo()
  },
  methods: {
    initialInfo () {
      this.$axios({
        method: 'get',
        url: this.$url + '/user/getAllInfo',
        params: {
          access_token: this.$getCookie('otod_access_token')
        }
      }).then(function (response) {
        this.userInfo = response.data
      }.bind(this))
    },
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
