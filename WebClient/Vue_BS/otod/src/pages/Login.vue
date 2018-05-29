<template>
    <div id="login" >
        <div class="about-Index-showcase ready" :class="indexShow" prima-component="text">
            <div class="form_container" >
                <div class="form_header">
                    <h1 class="logo-word large" id="logo">
                        <a class = "logo_anchor" href="/" >OTOD</a>
                     </h1>
                    <h2 class="subheading">
                        为你的所爱而来,<br>为你的发现停留。
                     </h2>
                 </div>
                <div class="form-header-welcome">
                    <h2 class="section-title">解释完毕~</h2>
                    <p class="subheading">好吧，我也不知道写什么。</p>
                 </div>
                <div class="form_content" :class="{'show-forms': showForms}">
                    <div id="signup_forms" class="signup_forms clearfix">
                        <div class="signup_forms_container clearfix" id="signup_forms_container">
                            <div id="signup_forms_panel" class="signup_forms_panel clearfix" :class="formType">
                                    <div id="signup_account" class="signup_view" :class="{'active':formsActive}">
                                        <div class="form_row form_row_username">
                                            <input id="signup_username" type="text" placeholder="用户名" v-model="username"/>
                                         </div>
                                        <div class="form_row form_row_password">
                                            <input id="signup_password" type="password" placeholder="用户密码" v-model="password"/>
                                         </div>
                                        <div class="form_row form_row_phone">
                                            <input id="signup_phone" type="text" onkeypress="return event.keyCode >= 48 && event.keyCode <= 57" ng-pattern="/[^a-zA-Z]/" placeholder="手机号" v-model="telephone"/>
                                         </div>
                                    </div>
                             </div>
                         </div>
                        <!-- 错误显示点 -->
                        <ul class="signup_forms_errors" :class="{'has-errors':hasErrors}" id="signup_forms_errors">
                            <li v-for="(error,index) in errorList" :key="index">{{error.name}}</li>
                        </ul>
                        <button class="signup_forms_submit" :class="{'active':signupBtnActive}" id="signup_forms_submit" @click="signupBtnClick()">
                            <span>注册</span>
                         </button>
                        <button class="signup_forms_submit" :class="{'active':signinBtnActive}" id="signup_login_button" @click="signinBtnClick()">
                            <span>登录</span>
                         </button>
                        <div class="forgot-password" :class="{'active':forgotPassword}">
                            <a href="www.baidu.com" class="forgot-password-link">忘记密码?</a>
                         </div>
                     </div>
                 </div>
             </div>
            <div class="showcase">
                <div class="section login-section" :class="{'active': showcaseObjects[0].active,'old-hat': showcaseObjects[0].oldHatActive}" section-title="注册" style="z-index:3">
                    <div id="fullscreen_post_bg" class="fullscreen_post_bg" style= "background-image:url(./static/backgrounds/tumblr_register_1280.jpg)" ></div>
                    <div class="fullscreen_post_footer">
                        <div class="fullscreen_post_footer_inner">
                            <div class="footer_legal_links">
                                <a v-for="(link, index) in footerLinks" :href="link.href" :key="index" target="_blank">{{link.title}}</a>
                             </div>
                            <div class="fullscreen_post_posted_by_show"></div>
                        </div>
                     </div>
                    <div class="about-Index-btn" @click="nextShowcase()" >What is OTOD?</div>
                 </div>
                <div class="section about-section" :class="{'active': showcaseObjects[1].active,'old-hat':showcaseObjects[1].oldHatActive}" section-title="关于" style="z-index:2">
                    <div class="section-wrapper">
                        <svg class="about-graphic" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" viewBox="0 0 284.5 221.8" enable-background="new 0 0 284.5 221.8" xml:space="preserve">
                            <g class="graphic-icon reblog-b">
                                <path fill="#56BC8A" stroke="#47B27F" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="M138.5,209.7l9-9v6.5h16.9v-2.4l6.7-6.7v13c0,1.6-1.3,2.9-2.9,2.9h-20.7v6.6l-9-9C138,211,138,210.2,138.5,209.7z"></path><path fill="#56BC8A" stroke="#47B27F" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="M139.1,191.8c0-1.6,1.3-2.9,2.9-2.9h20.7v-6.6l9,9c0.5,0.5,0.5,1.3,0,1.8l-9,9v-6.5h-16.9v2.4l-6.7,6.7V191.8z"></path>
                            </g>
                         </svg>
                        <div class="section-content">
                            <h1 class="section-title">
                                OTOD 让一切开始流动
                             </h1>
                            <p>
                                我们让大学的一切流动起来，简单又随心所欲地分享自己的快乐。故事、图片、GIF 动图、电视剧、链接、俏皮话、冷笑话、不冷的笑话、Spotify 的曲目、MP3、视频、时尚、艺术，以及有深度的内容。
                            </p>
                         </div>
                     </div>
                 </div>
                <div class="section welcome-section" :class="{'active': showcaseObjects[2].active,'old-hat':showcaseObjects[2].oldHatActive}" section-title="好吧，这个不难解释。" style="z-index:1">
                    <div class="section-wrapper">
                        <div class="fullscreen_post_bg" style="background-image:url(./static/backgrounds/tumblr_welcome_1280.gif)"></div>
                     </div>
                 </div>
             </div>
            <div class="showcase-pagination">
                <div class="dot" v-for="(item,index) in dotObjects" :class="{'active': item.dotActive}" :key="index"
                 :title="item.title" @click="setShowcase(index)" />
             </div>
         </div>
        <!-- <div class="icon-header-container" prima-component="header">
            <div  class="icon-header hide-logo">
                <h1 class="logo"></h1>
                <div class="right"></div>
                <div class="ui_search" id="search_field"></div>
             </div>
         </div> -->
    </div>
</template>

<script>
export default {
  name: 'Login',
  data () {
    return {
      // showcase 状态flags
      indexShow: 'show-login', // 'show-' + dataSection
      indexAnimate: false, // 变换flag ，为假时候才可变换
      hasErrors: false, // 存在错误flag

      // 表单类型 'signup_view' / 'signin_view'
      showForms: false, // 展示表单状态
      formsActive: false, // 表单激活
      formType: '', // 表单类型 signup/signin
      signupBtnActive: true, // 注册按钮
      signinBtnActive: true, // 登录按钮
      forgotPassword: false, // 忘记密码样式

      // 外链连接表单
      footerLinks: [{
        title: '使用条款',
        href: 'http://www.baidu.com'
      }, {
        title: '客户隐私',
        href: 'http://www.baidu.com'
      }, {
        title: '客户反馈',
        href: 'http://www.baidu.com'
      }, {
        title: '相关证书',
        href: 'http://www.baidu.com'
      }],

      // showcase 响应控制对象
      showcaseObjects: [{
        active: true,
        oldHatActive: false,
        dataSection: 'login'
      }, {
        active: false,
        oldHatActive: false,
        dataSection: 'about'
      }, {
        active: false,
        oldHatActive: false,
        dataSection: 'welcome'
      }],

      // 当前激活属性<showcase>
      currentShowcase: 0,

      // dots 响应对象信息
      dotObjects: [{
        dotActive: true,
        title: '注册'
      }, {
        dotActive: false,
        title: '用起来真是简单到难以解释。'
      }, {
        dotActive: false,
        title: 'OTOD就是众多博客。'
      }],

      // userData form
      username: '',
      password: '',
      telephone: '',

      // 错误集合
      errorList: []
    }
  },
  mounted () {
    // 键盘监听注册
    document.onkeydown = this.keyDownEvent

    // 滚轮监听注册
    window.onmousewheel = document.onmousewheel = this.handleScroll
    window.addEventListener('mouseWheel', this.handleScroll, false)
    window.addEventListener('DOMMouseScroll', this.handleScroll, false)
  },
  methods: {
    // 注册按钮
    signupBtnClick () {
      if (this.showForms) {
        if (this.username !== '' && this.password !== '' && this.telephone !== '') {
          console.log('name:' + this.username + ',pass:' + this.password + ',tel:' + this.telephone)
        } else {
          this._showErrors('信息不全,请补全信息！')
        }
      } else {
        this.formType = 'signup'
        this.formsActive = true
        this.signinBtnActive = false
        this.showForms = true
      }
    },

    // 登录按钮
    signinBtnClick () {
      if (this.showForms) {
        if (this.username !== '' && this.password !== '') {
          console.log('name:' + this.username + ',pass:' + this.password)
        } else {
          this._showErrors('信息不全，请补全信息！')
        }
      } else {
        this.formType = 'signin'
        this.formsActive = true
        this.signupBtnActive = false
        this.showForms = true
        setTimeout(() => {
          this.forgotPassword = true
        }, 40)
      }
    },

    // 滚轮实现轮播
    handleScroll: function (event) {
      event = event || window.event
      if (event.wheelDelta && !this.indexAnimate) {
        event.wheelDelta > 0 && this.preShowcase()
        event.wheelDelta < 0 && this.nextShowcase()
      }
    },

    // 键盘实现轮播
    keyDownEvent: function (event) {
      event = event || window.event
      switch (event.keyCode) {
        case 40:
        case 74:
          this.nextShowcase()
          break
        case 38:
        case 75:
          this.preShowcase()
          break
      }
    },

    // 下一页
    nextShowcase () {
      this.currentShowcase < this.showcaseObjects.length - 1 && this.setShowcase(this.currentShowcase + 1)
    },

    // 上一页
    preShowcase () {
      this.currentShowcase > 0 && this.setShowcase(this.currentShowcase - 1)
    },

    // 设定第index页的shwocase
    setShowcase (index) {
      this.indexAnimate = true
      // console.log('[scrollEvent]' + this.indexAnimate)
      this.indexShow = 'show'

      for (var showcaseIndex = 0; showcaseIndex < this.showcaseObjects.length; ++showcaseIndex) {
        if (showcaseIndex < index) {
          this.showcaseObjects[showcaseIndex].oldHatActive = true
        } else {
          this.showcaseObjects[showcaseIndex].oldHatActive = false
        }
      }

      setTimeout(() => {
        this._resetAnimating()
        this._setActiveShowcase(index)
      }, 200)
    },

    // 重置animating属性
    _resetAnimating () {
      setTimeout(this.indexAnimate = false, 300)
      // console.log('[scrollEvent]' + this.indexAnimate)
    },

    // 设置激活showcase
    _setActiveShowcase (index) {
      this.showcaseObjects[this.currentShowcase].active = false
      this.showcaseObjects[index].active = true
      this.dotObjects[this.currentShowcase].dotActive = false
      this.dotObjects[index].dotActive = true
      this.currentShowcase = index
      this.indexShow = 'show-' + this.showcaseObjects[index].dataSection
    },

    // 显示错误提示
    _showErrors (errorname) {
      if (this.errorList.length === 1) {
        return false
      }
      if (this.hasErrors === false) {
        this.hasErrors = true
      }
      errorname !== '' && this.errorList.push({ name: errorname })
    }
  }
}
</script>

<style>
    @import '../assets/css/loginPage.css';
</style>
