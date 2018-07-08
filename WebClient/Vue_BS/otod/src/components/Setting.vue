<template>
    <div>
        <div class="setting-main">
            <div class="setting-item setting-title">
                <h2>
                    <a @click="backIndex()">返回</a>
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
                        <span class="telephone-Num">{{$store.state.telephone}}</span>
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
                        <div class="user-arrow"></div>
                        <div class="user-uploader">
                            <!-- <div class="uploader-arrow"></div> -->
                            <el-upload
                              class="img-uploader"
                              action="http://localhost:8081/user/saveImg"
                              accept="image/jpeg,image/gif,image/png"
                              :show-file-list="false"
                              :on-success="handleAvatarSuccess"
                              :before-upload="beforeAvatarUpload">
                                <img v-if="imageUrl" :src="$imageUrl + imageUrl" class="avatar">
                                <i v-else class="el-icon-plus"></i>
                            </el-upload>
                         </div>
                     </div>
                    <div class="info-content">
                        <ul>
                            <li class="nes-li"><h6>昵 称:</h6><input type="text" name="nickname" v-model="userInfo.nickname" placeholder="我们如何称呼您？" @blur="nicknameCheck"/></li>
                            <li><h6>职 务:</h6><input type="text" name="position" v-model="userInfo.position" placeholder="您的职务，即是上帝" /></li>
                            <li class="nes-li"><h6>性 别:</h6>
                                <el-radio-group v-model="userInfo.sex">
                                    <el-radio :label="1">男</el-radio>
                                    <el-radio :label="2">女</el-radio>
                                    <el-radio :label="3">保密</el-radio>
                                 </el-radio-group>
                             </li>
                            <li><h6>年 龄:</h6><input type="number" name="age" min="0" step="1" v-model="userInfo.age" /></li>
                            <li><h6>地 址:</h6><input type="text" name="address" v-model="userInfo.address" placeholder="给我们一次相遇的机会" /></li>
                            <li><h6>邮 箱：</h6><input type="email" name="email" v-model="userInfo.email" placeholder="给我们一次表白的机会"  @blur="emailCheck"/></li>
                            <li>
                              <h6>个 人 信 条：</h6>
                              <el-input
                                class="input-area"
                                type="textarea"
                                :rows="2"
                                v-model="credo">
                              </el-input>
                            </li>
                        </ul>
                    </div>
                 </div>
             </div>

            <div class="setting-save">
                <div class="m-set">
                    <div class="btns">
                        <a class="w-btn save" @click="saveSetting">保&nbsp;&nbsp;存</a>
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
      credo: '',
      imageUrl: '',
      newImageUrl: '',
      userInfo: {
      },
      newUserInfo: {},
      error: {
        nickname: true,
        email: true
      }
    }
  },
  mounted () {
    // GET user infomation
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
        if (this.userInfo.sex === '男') {
          this.userInfo.sex = 1
        } else if (this.userInfo.sex === '女') {
          this.userInfo.sex = 2
        } else {
          this.userInfo.sex = 3
        }
      }.bind(this))
    },

    // headImage upload
    handleAvatarSuccess (res, file) {
      if (res === 'Error!') {
        this.$message.error('请检查上传文件为图片类型！')
      } else if (res === 'Empty!') {
        this.$message.error('请选择上传图片文件！')
      } else {
        this.imageUrl = res
      }
    },

    beforeAvatarUpload (file) {
      const isJPG = file.type === 'image/jpeg' || file.type === 'image/png' || file.type === 'image/gif'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG/PNG/GIF 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
    },

    // check info
    nicknameCheck () {
      if (this.userInfo.nickname !== '') {
        this.$axios({
          method: 'get',
          url: this.$url + '/user/getSimpleByNickname',
          params: {
            nickname: this.userInfo.nickname
          }
        }).then(function (response) {
          if (response.data.nickname !== null) {
            this.error.nickname = true
          }
        })
      }
    },
    emailCheck () {
      if (this.userInfo.email !== '') {
        this.error.email = !/^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/.test(this.userInfo.email)
        this.$message.error('请重新检查邮箱')
      }
    },

    backIndex () {
      this.$notify({
        title: '设置',
        message: '放弃保存个人信息',
        position: 'bottom-right'
      })
      this.$router.push({ name: 'Person', params: { page: 'home' } })
    },

    saveSetting () {
      if (this.userInfo.nickname === '') {
        this.$message.error('昵称不为空！')
        return
      }

      if (!this.error.email) {
        this.$message.error('请重新检查邮箱')
        return
      }

      this.newUserInfo = this.userInfo

      // add new headImage url
      if (this.imageUrl !== '') {
        this.newUserInfo.headImage = this.imageUrl
      }

      // change sex value
      switch (this.userInfo.sex) {
        case 1: this.newUserInfo.sex = '男'
          break
        case 2: this.newUserInfo.sex = '女'
          break
        case 3: this.newUserInfo.sex = '保密'
          break
      }

      this.$axios({
        method: 'post',
        url: this.$url + '/user/setAllInfo',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + this.$getCookie('otod_access_token')
        },
        dataType: 'json',
        data: this.newUserInfo
      }).then(function (response) {
        if (response.data === 'success') {
          this.$store.commit('initialName', this.newUserInfo.nickname)
          this.$store.commit('initialHead', this.newUserInfo.headImage)
          this.$notify({
            title: '设置',
            message: '成功保存个人信息',
            position: 'bottom-right'
          })
          this.$router.replace({ name: 'Person', params: { page: 'home' } })
        }
      }.bind(this))
    }
  }
}
</script>

<style>
    @import '../assets/css/SettingPage.css'
</style>
