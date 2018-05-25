<template>
    <div id="login" >
        <div class="about-Index-showcase ready" :class="indexShow" prima-component="text">
            <div class="form_container" >
                <div class="form_header">
                    <h1 class="logo-word large" id="logo">
                        <a class = "logo_anchor" href="/" >OneTeamOneDream</a>
                     </h1>
                    <h2 class="subheading">
                        为你的所爱而来。<br>为你的发现停留。
                     </h2>
                 </div>
                <div class="form-header-welcome">
                    <h2 class="section-title">解释完毕~</h2>
                    <p class="subheading">好吧，我也不知道写什么。</p>
                 </div>
                <div class="form_content" :class="{'show-forms': showForms, 'has-errors':hasErrors}">
                    <div id="signup_forms" class="signup_forms clearfix">
                        <div class="signup_forms_container clearfix" id="signup_forms_container">
                            <div id="signup_forms_panel" class="signup_forms_panel clearfix" :class="forms_type">
                                <form id="sign_forms" method="post" >
                                    <div id="signup_account" class="signup_view account login" :class="{'active':formsActive}">
                                        <div class="form_row form_row_username">
                                            <input id="signup_username" type="text" placeholder="用户名"/>
                                         </div>
                                        <div class="form_row form_row_password">
                                            <input id="signup_password" type="password" placeholder="用户密码"/>
                                         </div>
                                        <div class="form_row form_row_phone">
                                            <input id="signup_phone" type="text" onkeypress="return event.keyCode >= 48 && event.keyCode <= 57" ng-pattern="/[^a-zA-Z]/" placeholder="手机号"/>
                                         </div>
                                    </div>
                                 </form>
                             </div>
                         </div>
                        <ul class="signup_forms_errors" id="signup_forms_errors" style="display:none"></ul>
                        <button class="signup_forms_submit" :class="{'active':signupBtnActive}" id="signup_forms_submit" @click="signupBtnClick()">
                            <span>{{signup_started_btn}}</span>
                         </button>
                        <button class="signup_forms_submit" :class="{'active':signinBtnActive}" id="signup_login_button" @click="signinBtnClick()">
                            <span>登录</span>
                         </button>
                     </div>
                 </div>
             </div>
            <div class="showcase">
                <div class="section login-section" :class="{'active': showcaseObjects[0].active,'old-hat': showcaseObjects[0].oldHatActive}" section-title="注册" style="z-index:3">
                    <div id="fullscreen_post_bg" class="fullscreen_post_bg" style= "background-image:url(./static/backgrounds/tumblr_register_1280.jpg)" ></div>
                    <!-- <div class="fullscreen_post_footer">
                        <div class="fullscreen_post_footer_inner">
                            <div class="footer_legal_links">
                                <a  href="https://www.baidu.com" target="_blank" class="">
                             </div>
                            <div class="fullscreen_post_posted_by_show"></div>
                        </div>
                     </div> -->
                    <div class="about-Index-btn" @click="nextShowcase()" >What is OTOD?</div>
                 </div>
                <div class="section about-section" :class="{'active': showcaseObjects[1].active,'old-hat':showcaseObjects[1].oldHatActive}" section-title="关于" style="z-index:2"></div>
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
        <div class="icon-header-container" prima-component="header">

         </div>
    </div>
</template>

<script>
export default {
  name: 'Login',
  data () {
    return {
      // 按钮文本 '开始吧'/'注册'
      signup_started_btn: '开始吧',

      forms_type: '',

      // showcase 状态flags
      indexShow: 'show-login', // 'show-' + dataSection
      indexAnimate: false, // 变换flag ，为假时候才可变换
      hasErrors: false, // 存在错误flag

      // 表单类型 'signup_view' / 'signin_view'
      showForms: false, // 展示表单状态
      formsActive: false, // 表单激活
      signupBtnActive: true, // 注册按钮
      signinBtnActive: true, // 登录按钮

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
      }]
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

      } else {
        this.formsActive = true
        this.signinBtnActive = false
        this.signup_started_btn = '注册'
        this.showForms = true
      }
    },

    // 登录按钮
    signinBtnClick () {
      this.signupBtnActive = false
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
    }
  }
}
</script>

<style>
    @import '../assets/css/loginPage.css';
</style>
