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
                <div class="section login-section" :class="{'active': showcaseObjects[0].active,'old-hat': showcaseObjects[0].oldHatActive}" section-title="注册" style="z-index:4">
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
                <div class="section about-section" :class="{'active': showcaseObjects[1].active,'old-hat':showcaseObjects[1].oldHatActive}" section-title="关于" style="z-index:3">
                    <div class="section-wrapper">
                        <svg class="about-graphic" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" viewBox="0 0 284.5 221.8" enable-background="new 0 0 284.5 221.8" xml:space="preserve">
                            <g class="bottom">
                                <path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="M138.5,209.7l9-9v6.5h16.9v-2.4l6.7-6.7v13c0,1.6-1.3,2.9-2.9,2.9h-20.7v6.6l-9-9C138,211,138,210.2,138.5,209.7z"></path>
                                <path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="M139.1,191.8c0-1.6,1.3-2.9,2.9-2.9h20.7v-6.6l9,9c0.5,0.5,0.5,1.3,0,1.8l-9,9v-6.5h-16.9v2.4l-6.7,6.7V191.8z"></path>
                             </g>
                            <g class="top-left">
                                <path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="M81.7,37.9l10.7-10.6v7.7h19.9V32l7.9-7.9v15.3c0,1.9-1.5,3.4-3.4,3.4H92.3v7.7L81.7,39.9C81.1,39.4,81.1,38.4,81.7,37.9z">
                                 </path>
                                <path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="M82.4,16.8c0-1.9,1.5-3.4,3.4-3.4h24.4V5.6l10.7,10.6c0.6,0.6,0.6,1.5,0,2.1l-10.7,10.6v-7.7H90.3v2.9L82.4,32V16.8z">
                                 </path>
                             </g>
                            <g class="top-left">
                                <path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="M45.7,32.1v-0.6h3.1h0h0h0.1v0c3.7,0,6.8,2.4,8,5.7c0.8-2.3,2.6-4.2,4.9-5.1v-0.6h2.3c0.3,0,0.6,0,0.8,0c0.3,0,0.6,0,0.9,0h0v0c4.3,0.4,7.7,4.1,7.7,8.5c0,4.7-3.8,8.6-8.6,8.6H48.9c-4.7,0-8.6-3.8-8.6-8.6C40.3,36.4,42.6,33.3,45.7,32.1z"></path>
                                <path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="M53.1,48.7h18.9c1.1,0,2,0.9,2,2v0.7c0,1.1-0.9,2-2,2h-1.9V65c0,1.1-1.4,2-2.7,2H53.1V48.7z"></path>
                                <path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="M30.1,48.4c0.2-0.1,0.4-0.1,0.6-0.1c0.4,0,0.8,0.2,1.1,0.5c0,0,6,6.1,6.1,6.3v-6.3h30.9v4.8h-3.6V65c0,1.1-1.4,2-2.7,2H40.5c-1.2,0-2.7-0.8-2.7-1.9v-5.7c-0.1,0.2-6.1,6.3-6.1,6.3c-0.3,0.3-0.7,0.5-1.1,0.5c-0.2,0-0.4,0-0.6-0.1c-0.6-0.2-1-0.8-1-1.4V49.8C29.1,49.2,29.5,48.7,30.1,48.4z"></path>
                                <line fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" x1="37.8" y1="55" x2="37.8" y2="59.4"></line>
                                <path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="M45.7,31.5c3.7,0,6.9,2.4,8.1,5.7c1.2-3.3,4.3-5.7,8.1-5.7c4.7,0,8.6,3.8,8.6,8.6s-3.8,8.6-8.6,8.6H45.7c-4.7,0-8.6-3.8-8.6-8.6S41,31.5,45.7,31.5z"></path>
                                <circle fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" cx="61.8" cy="40" r="3.2"></circle>
                                <circle fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" cx="45.7" cy="40" r="3.2"></circle>
                                <line fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" x1="65" y1="31.5" x2="61.8" y2="31.5"></line>
                                <path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="M53.8,34.6c-0.2-0.6-0.8-1.2-1.4-1.4"></path>
                                <path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="M70.8,53.6c-1.1,0-2-0.9-2-2v-0.7c0-1.1,0.9-2,2-2"></path>
                             </g>
                            <g class="top-right">
                                <path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="M258.5,56.8v-0.7H255h0h0H255v0c-4.1,0-7.5,2.7-8.8,6.3c-0.9-2.6-2.9-4.6-5.4-5.6v-0.6h-2.6c-0.3,0-0.6,0-0.9,0c-0.3,0-0.6,0-1,0h0v0c-4.8,0.5-8.5,4.5-8.5,9.4c0,5.2,4.2,9.4,9.4,9.4H255c5.2,0,9.4-4.2,9.4-9.4C264.5,61.6,262,58.2,258.5,56.8z">
                                 </path>
                                <path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="M250.3,75.1h-20.9c-1.2,0-2.2,1-2.2,2.2v0.8c0,1.2,1,2.2,2.2,2.2h2.1v12.7c0,1.2,1.6,2.2,2.9,2.2h15.9V75.1z">
                                 </path>
                                <path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="
                                M275.8,74.8c-0.2-0.1-0.4-0.1-0.6-0.1c-0.4,0-0.9,0.2-1.2,0.5c0,0-6.6,6.7-6.7,6.9v-7h-34.1v5.3h4v12.7c0,1.2,1.6,2.2,2.9,2.2h24.2
                                c1.3,0,2.9-0.8,2.9-2.1v-6.3c0.1,0.2,6.7,6.9,6.7,6.9c0.3,0.3,0.8,0.5,1.2,0.5c0.2,0,0.4,0,0.6-0.1c0.6-0.3,1.1-0.9,1.1-1.6V76.4
                                C276.8,75.7,276.4,75.1,275.8,74.8z">
                                  </path>
                                <line fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" x1="267.2" y1="82.1" x2="267.2" y2="86.9"></line>
                                <path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="
                                M258.5,56.1c-4.1,0-7.6,2.6-8.9,6.3c-1.3-3.7-4.8-6.3-8.9-6.3c-5.2,0-9.4,4.2-9.4,9.4c0,5.2,4.2,9.4,9.4,9.4h17.8
                                c5.2,0,9.4-4.2,9.4-9.4C268,60.4,263.7,56.1,258.5,56.1z">
                                 </path>
                                <circle fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" cx="240.7" cy="65.6" r="3.6"></circle>
                                <circle fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" cx="258.5" cy="65.6" r="3.6"></circle>
                                <line fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" x1="237.2" y1="56.1" x2="240.7" y2="56.1"></line>
                                <path fill="#61E8A8" stroke="#4EB097" stroke-widt   h="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="M249.6,59.6c0.3-0.7,0.9-1.3,1.6-1.5">
                                 </path>
                                <path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round"  stroke-miterlimit="10" d="M230.9,80.5c1.2,0,2.2-1,2.2-2.2v-0.8c0-1.2-1-2.2-2.2-2.2">
                                 </path>
                             </g>
                            <g class="top-right">
                                <path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="
                                M184.5,104.3l-2.3-7.1h-11.5l-2.3,7.1h-9.2L172,68.4h9.2l12.7,35.9H184.5z M179.9,89.8l-3.3-10.3l-3.4,10.3H179.9z"></path><path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="
                                M204.7,105.1c-2.5,0-4.6-0.7-6.2-2.2c-1.7-1.5-2.5-3.5-2.5-6.2c0-3.4,1.4-5.9,4-7.5c1.4-0.8,3.5-1.4,6.2-1.7l2.3-0.3
                                c1.4-0.2,2.1-0.4,2.4-0.5c0.9-0.4,1-0.8,1-1.2c0-1-0.4-1.3-0.7-1.5c-0.6-0.3-1.6-0.5-2.9-0.5c-1.3,0-2.3,0.3-2.8,0.9
                                c-0.4,0.5-0.6,1.1-0.8,2l-0.1,0.6h-7.9l0.1-0.8c0.1-2.4,0.8-4.4,2-5.9c1.9-2.4,5.2-3.7,9.7-3.7c2.9,0,5.5,0.6,7.8,1.7
                                c2.4,1.2,3.7,3.6,3.7,6.9v11.7c0,0.8,0,1.8,0,2.9c0.1,1,0.2,1.3,0.3,1.4c0.1,0.2,0.4,0.4,0.7,0.5l0.4,0.2v2.2h-8.5l-0.2-0.5
                                c-0.2-0.6-0.4-1.1-0.5-1.6c0,0,0,0,0,0c-0.7,0.6-1.4,1.1-2.2,1.6C208.5,104.7,206.7,105.1,204.7,105.1z M211.6,92.6
                                c-0.5,0.2-1.1,0.3-1.9,0.5l-1.5,0.3c-1.7,0.3-2.4,0.6-2.8,0.8c-0.8,0.5-1.2,1.2-1.2,2.2c0,0.9,0.2,1.5,0.7,1.9
                                c0.5,0.4,1.1,0.6,1.9,0.6c1.3,0,2.5-0.4,3.6-1.1c1-0.7,1.5-2,1.6-4v-1.2C211.7,92.5,211.7,92.5,211.6,92.6z"></path>
                             </g>
                            <g class="left">
                                <path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="M50.1,77.3H39.9l-1.9-2.9H21.8L20,77.3H9.8c-4.2,0-7.6,3.4-7.6,7.6v29.2c0,4.2,3.4,7.6,7.6,7.6h40.4c4.2,0,7.6-3.4,7.6-7.6V84.9C57.7,80.8,54.3,77.3,50.1,77.3z"></path><path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="M58.8,77.3H48.6l-1.9-2.9H30.5l-1.9,2.9H18.4c-4.2,0-7.6,3.4-7.6,7.6v29.2c0,4.2,3.4,7.6,7.6,7.6h40.4c4.2,0,7.6-3.4,7.6-7.6V84.9C66.4,80.8,62.9,77.3,58.8,77.3z"></path><path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="M38.7,117.2c-9.1,0-16.5-7.4-16.5-16.5c0-9.1,7.4-16.5,16.5-16.5h4.9v33H38.7z"></path><circle fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" cx="43.7" cy="100.7" r="16.5"></circle><circle fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" cx="43.7" cy="100.7" r="9.4"></circle><g><circle fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" cx="43.7" cy="100.6" r="7.8"></circle><path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="M39.1,100.6c0-2.5,2.1-4.6,4.6-4.6"></path></g><circle fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" cx="59.1" cy="83.7" r="2.5"></circle>
                             </g>
                            <g class="bottom-right">
                                <path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="
                                M223.1,159.8h-9l-1.7-2.6h-14.3l-1.7,2.6h-9c-3.7,0-6.7,3-6.7,6.7v25.8c0,3.7,3,6.7,6.7,6.7h35.6c3.7,0,6.7-3,6.7-6.7v-25.8
                                C229.8,162.8,226.8,159.8,223.1,159.8z"></path><path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="
                                M230.7,159.8h-9l-1.7-2.6h-14.3l-1.7,2.6h-9c-3.7,0-6.7,3-6.7,6.7v25.8c0,3.7,3,6.7,6.7,6.7h35.6c3.7,0,6.7-3,6.7-6.7v-25.8
                                C237.4,162.8,234.4,159.8,230.7,159.8z"></path><path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="
                                M213,194.9c-8.1,0-14.6-6.5-14.6-14.6c0-8.1,6.5-14.6,14.6-14.6h4.4v29.2H213z"></path><circle fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" cx="217.4" cy="180.3" r="14.6"></circle><circle fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" cx="217.4" cy="180.3" r="8.3"></circle><g><circle fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" cx="217.4" cy="180.3" r="6.9"></circle><path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="
                                    M213.3,180.3c0-2.2,1.8-4.1,4.1-4.1"></path></g><circle fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" cx="231" cy="165.4" r="2.2"></circle></g>
                            <g class="left">
                                <g><g><path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="
                                        M18.6,161.8c-5.8,0-9.6-4.3-9.6-10.8c0-8.1,6.8-15.5,13.2-19.6l0.6-0.4l4.2,4.2l-1.1,0.9c-2.6,1.9-5.8,4.3-7,7.3
                                        c5.3,0.5,8.7,4.2,8.7,9.6C27.6,157.9,23.6,161.8,18.6,161.8z"></path></g></g><g><g><path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="
                                            M41.2,161.8c-5.8,0-9.6-4.3-9.6-10.8c0-8.1,6.8-15.5,13.2-19.6l0.6-0.4l4.2,4.2l-1.1,0.9c-2.6,1.9-5.8,4.3-7,7.3
                                            c5.3,0.5,8.7,4.2,8.7,9.6C50.3,157.9,46.3,161.8,41.2,161.8z"></path></g></g>
                             </g>
                            <g class="right">
                                <g><g><path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="
                                M262.9,106.7c5.8,0,9.6,4.3,9.6,10.8c0,8.1-6.8,15.5-13.2,19.6l-0.6,0.4l-4.2-4.2l1.1-0.9c2.6-1.9,5.8-4.3,7-7.3
                                c-5.3-0.5-8.7-4.2-8.7-9.6C253.8,110.6,257.8,106.7,262.9,106.7z"></path></g></g><g><g><path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="
                                    M240.2,106.7c5.8,0,9.6,4.3,9.6,10.8c0,8.1-6.8,15.5-13.2,19.6l-0.6,0.4l-4.2-4.2l1.1-0.9c2.6-1.9,5.8-4.3,7-7.3
                                    c-5.3-0.5-8.7-4.2-8.7-9.6C231.1,110.6,235.1,106.7,240.2,106.7z"></path></g></g>
                             </g>
                            <g class="left">
                                <path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="
                                M108,113.1c-2.6-2.8-6.2-4.4-10.2-4.4c-7.9,0-14.9,6.4-15.5,14.3l-0.6,6.6h-6.6c-7.9,0-14.9,6.4-15.5,14.3c-0.3,4,0.9,7.8,3.5,10.6
                                c2.6,2.8,6.2,4.4,10.2,4.4c7.9,0,14.9-6.4,15.5-14.3l0.6-6.6H96c7.9,0,14.9-6.4,15.5-14.3C111.8,119.7,110.6,116,108,113.1z
                                M80.4,143.9c-0.3,3.6-3.6,6.6-7.2,6.6c-1.6,0-3-0.6-4-1.7c-1-1.1-1.5-2.6-1.3-4.2c0.3-3.6,3.6-6.6,7.2-6.6h5.9L80.4,143.9z
                                M96,129.6h-5.9l0.5-5.9c0.3-3.6,3.6-6.6,7.2-6.6c1.6,0,3,0.6,4,1.7c1,1.1,1.5,2.6,1.3,4.2C102.8,126.6,99.5,129.6,96,129.6z"></path><line fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" x1="81.6" y1="129.6" x2="90" y2="129.6"></line><line fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" x1="81.2" y1="138" x2="90" y2="138"></line>
                             </g>
                            <g class="top-right">
                                <path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="
                                M243.2,19.3c-2.9-3.2-7-5-11.5-5c-8.9,0-16.8,7.2-17.6,16.2l-0.7,7.5H206c-8.9,0-16.8,7.2-17.6,16.1c-0.4,4.5,1,8.8,4,12
                                c2.9,3.2,7,5,11.5,5c8.9,0,16.8-7.2,17.6-16.2l0.7-7.5h7.5c8.9,0,16.8-7.2,17.6-16.2C247.6,26.7,246.1,22.5,243.2,19.3z M212,54
                                c-0.4,4.1-4.1,7.5-8.1,7.5c-1.8,0-3.4-0.7-4.5-1.9c-1.1-1.2-1.7-2.9-1.5-4.7c0.4-4.1,4.1-7.5,8.1-7.5h6.6L212,54z M229.6,37.9H223
                                l0.6-6.6c0.4-4.1,4.1-7.5,8.1-7.5c1.8,0,3.4,0.7,4.5,1.9c1.1,1.2,1.7,2.9,1.5,4.7C237.3,34.5,233.6,37.9,229.6,37.9z"></path><line fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" x1="213.3" y1="37.9" x2="222.8" y2="37.9"></line><line fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" x1="212.8" y1="47.4" x2="222.8" y2="47.4"></line>
                             </g>
                            <g class="bottom-left">
                                <g><path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="
                                    M89.1,172.5H97c0,0-2.2-4.8-10.4-4.5l-0.4,1.3l2.6,3.3"></path><path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="
                                    M97.7,183.2c0-2.7,0-5.5,0-7.8c0-4.3-4.3-7.8-9.4-7.8H65c-5.2,0-9.4,4.3-9.4,9.4v25.2c0,5.2,4.3,9.4,9.4,9.4h23.3
                                    c5.2,0,9.4-3.5,9.4-7.8c0-2.3,0-5.1,0-7.8l6.5-6.4L97.7,183.2z"></path><path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="
                                    M89.8,183.2c0-2.7,0-5.5,0-7.8c0-4.3-4.3-7.8-9.4-7.8H57.1c-5.2,0-9.4,4.3-9.4,9.4v25.2c0,5.2,4.3,9.4,9.4,9.4h23.3
                                    c5.2,0,9.4-3.5,9.4-7.8c0-2.3,0-5.1,0-7.8l6.5-6.4L89.8,183.2z"></path><line fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" x1="104.2" y1="189.6" x2="96.3" y2="189.6"></line><path fill="#4EB097" d="M76.6,183.4c0-1,0.6-1.5,1.6-1.5c1,0,1.6,0.5,1.6,1.5v2.3c0,0.3,0,0.6-0.1,0.9l-0.6,4.7
                                    c-0.1,0.6-0.4,0.7-0.9,0.7c-0.5,0-0.8-0.2-0.9-0.7l-0.6-4.7c0-0.3-0.1-0.6-0.1-0.9V183.4z"></path><path fill="#4EB097" d="M68.4,195.3c0,0.8-0.3,1.5-1.5,1.5c-1.1,0-1.5-0.7-1.5-1.5v-5.1c0-1.2-0.6-2-1.8-2c-1,0-2.2,0.8-2.2,2.3
                                    v4.8c0,0.8-0.3,1.5-1.5,1.5c-1.1,0-1.5-0.7-1.5-1.5v-12c0-1,0.6-1.5,1.5-1.5c0.9,0,1.5,0.5,1.5,1.5v4h0c0.6-0.8,1.6-1.5,3.3-1.5
                                    c1.8,0,3.7,0.9,3.7,3.8V195.3z"></path><path fill="#4EB097" d="M73.7,195.3c0,0.8-0.3,1.5-1.5,1.5c-1.1,0-1.5-0.7-1.5-1.5v-8c0-0.8,0.3-1.5,1.5-1.5
                                    c1.1,0,1.5,0.7,1.5,1.5V195.3z"></path><path fill="#4EB097" d="M72.3,184.8c-0.8,0-1.5-0.7-1.5-1.5c0-0.8,0.7-1.5,1.5-1.5c0.8,0,1.5,0.7,1.5,1.5
                                    C73.8,184.2,73.1,184.8,72.3,184.8z"></path><path fill="#4EB097" d="M78.2,196.7c-0.9,0-1.6-0.7-1.6-1.6c0-0.9,0.7-1.6,1.6-1.6c0.9,0,1.6,0.7,1.6,1.6
                                    C79.9,195.9,79.1,196.7,78.2,196.7z"></path></g><line fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" x1="89.8" y1="183.2" x2="97.7" y2="183.2"></line><line fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" x1="90.6" y1="195.9" x2="97.7" y2="195.9"></line>
                             </g>
                            <g class="top">
                                <g><path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="
                                    M162.9,12.2h6.1c0,0-1.7-3.7-8.1-3.5l-0.3,1l2,2.5"></path><path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="
                                    M169.5,20.5c0-2.1,0-4.3,0-6.1c0-3.4-3.3-6.1-7.4-6.1h-18.1c-4,0-7.4,3.3-7.4,7.4v19.6c0,4,3.3,7.4,7.4,7.4h18.1
                                    c4,0,7.4-2.7,7.4-6.1c0-1.8,0-4,0-6.1l5-5L169.5,20.5z"></path><path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="
                                    M163.4,20.5c0-2.1,0-4.3,0-6.1c0-3.4-3.3-6.1-7.4-6.1h-18.1c-4,0-7.4,3.3-7.4,7.4v19.6c0,4,3.3,7.4,7.4,7.4H156
                                    c4,0,7.4-2.7,7.4-6.1c0-1.8,0-4,0-6.1l5-5L163.4,20.5z"></path><line fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" x1="174.6" y1="25.5" x2="168.4" y2="25.5"></line><path fill="#4EB097" d="M153.1,20.7c0-0.8,0.5-1.2,1.2-1.2c0.8,0,1.2,0.4,1.2,1.2v1.8c0,0.2,0,0.5-0.1,0.7l-0.5,3.6
                                    c-0.1,0.5-0.3,0.6-0.7,0.6c-0.4,0-0.6-0.1-0.7-0.6l-0.5-3.6c0-0.2-0.1-0.5-0.1-0.7V20.7z"></path><path fill="#4EB097" d="M146.7,30c0,0.6-0.2,1.1-1.1,1.1c-0.9,0-1.1-0.5-1.1-1.1v-4c0-0.9-0.5-1.6-1.4-1.6c-0.8,0-1.7,0.7-1.7,1.8
                                    V30c0,0.6-0.2,1.1-1.1,1.1c-0.9,0-1.1-0.5-1.1-1.1v-9.3c0-0.7,0.5-1.1,1.1-1.1c0.7,0,1.1,0.4,1.1,1.1v3.1h0
                                    c0.5-0.6,1.2-1.2,2.5-1.2c1.4,0,2.9,0.7,2.9,3V30z"></path><path fill="#4EB097" d="M150.9,30c0,0.6-0.2,1.1-1.1,1.1c-0.9,0-1.1-0.5-1.1-1.1v-6.3c0-0.6,0.2-1.1,1.1-1.1
                                    c0.9,0,1.1,0.5,1.1,1.1V30z"></path><circle fill="#4EB097" cx="149.8" cy="20.7" r="1.2"></circle><path fill="#4EB097" d="M154.4,31c-0.7,0-1.3-0.6-1.3-1.3c0-0.7,0.6-1.3,1.3-1.3c0.7,0,1.3,0.6,1.3,1.3
                                    C155.6,30.5,155.1,31,154.4,31z"></path></g><line fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" x1="163.4" y1="20.5" x2="169.5" y2="20.5"></line><line fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" x1="164" y1="30.5" x2="169.5" y2="30.5"></line>
                             </g>
                            <g class="right">
                                <g><path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="
                                    M190.5,120.1H185c0,0,1.5-3.4,7.2-3.2l0.3,0.9l-1.8,2.3"></path><path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="
                                    M184.5,127.6c0-1.9,0-3.9,0-5.5c0-3,3-5.5,6.6-5.5h16.2c3.6,0,6.6,3,6.6,6.6v17.5c0,3.6-3,6.6-6.6,6.6h-16.2
                                    c-3.6,0-6.6-2.5-6.6-5.5c0-1.6,0-3.6,0-5.5l-4.5-4.5L184.5,127.6z"></path><path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="
                                    M190,127.6c0-1.9,0-3.9,0-5.5c0-3,3-5.5,6.6-5.5h16.2c3.6,0,6.6,3,6.6,6.6v17.5c0,3.6-3,6.6-6.6,6.6h-16.2c-3.6,0-6.6-2.5-6.6-5.5
                                    c0-1.6,0-3.6,0-5.5l-4.5-4.5L190,127.6z"></path><line fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" x1="180" y1="132.1" x2="185.5" y2="132.1"></line><g><path fill="#4EB097" d="M210.3,127.8c0-0.7,0.4-1.1,1.1-1.1c0.7,0,1.1,0.4,1.1,1.1v1.6c0,0.2,0,0.4-0.1,0.7l-0.4,3.3
                                        c-0.1,0.4-0.2,0.5-0.6,0.5c-0.4,0-0.6-0.1-0.6-0.5l-0.4-3.3c0-0.2-0.1-0.4-0.1-0.7V127.8z"></path><path fill="#4EB097" d="M204.5,136.1c0,0.5-0.2,1-1,1c-0.8,0-1-0.5-1-1v-3.6c0-0.8-0.4-1.4-1.3-1.4c-0.7,0-1.5,0.6-1.5,1.6v3.4
                                        c0,0.5-0.2,1-1,1c-0.8,0-1-0.5-1-1v-8.4c0-0.7,0.4-1,1-1c0.6,0,1,0.3,1,1v2.8h0c0.4-0.5,1.1-1.1,2.3-1.1c1.2,0,2.6,0.6,2.6,2.7
                                        V136.1z"></path><path fill="#4EB097" d="M208.2,136.1c0,0.5-0.2,1-1,1c-0.8,0-1-0.5-1-1v-5.6c0-0.5,0.2-1,1-1c0.8,0,1,0.5,1,1V136.1z"></path><circle fill="#4EB097" cx="207.2" cy="127.7" r="1.1"></circle><path fill="#4EB097" d="M211.4,137c-0.6,0-1.1-0.5-1.1-1.1c0-0.6,0.5-1.1,1.1-1.1c0.6,0,1.1,0.5,1.1,1.1
                                        C212.5,136.5,212,137,211.4,137z"></path></g></g><line fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" x1="184.4" y1="127.6" x2="189.9" y2="127.6"></line><line fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" x1="185" y1="136.5" x2="189.9" y2="136.5"></line>
                             </g>
                            <g class="top-left">
                                <g><path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="
                                M123,78.5c-1-13-10.5-21.3-22.1-21.3c-11.5,0-21.1,8.3-22.1,21.4l4.7-0.1c1-10.3,8.4-16.4,17.3-16.4c9,0,16.4,6.1,17.3,16.4
                                L123,78.5z"></path><path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="
                                M79.3,78.6h6.3v19.5h-6.3c-2.7,0-4.8-2-4.8-4.6V83.2C74.5,80.7,76.6,78.6,79.3,78.6z"></path><path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="
                                M123.3,78.6h-6v19.5h6c2.5,0,4.6-2,4.6-4.6V83.2C127.9,80.7,125.8,78.6,123.3,78.6z"></path><path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="
                                M127.8,82.3c-0.4-2.1-2.3-3.7-4.5-3.7h-6v3.7H127.8z"></path><path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="
                                M79.3,78.6h6.2v19.5h-6.2c-2.7,0-4.8-2-4.8-4.6V83.2C74.5,80.7,76.6,78.6,79.3,78.6z"></path><path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="
                                M123.3,78.6h-6v19.5h6c2.5,0,4.6-2,4.6-4.6V83.2C127.9,80.7,125.8,78.6,123.3,78.6z"></path><path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="
                                M115,76.9c1.7,0,3.1,1.2,3.1,2.7v17.4c0,1.5-1.4,2.7-3.1,2.7c-1.7,0-3.1-1.2-3.1-2.7V79.6C111.9,78.1,113.3,76.9,115,76.9z"></path><path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="
                                M87.6,76.9c-1.5,0-2.7,1.2-2.7,2.7v17.4c0,1.5,1.2,2.7,2.7,2.7c1.5,0,2.7-1.2,2.7-2.7V79.6C90.3,78.1,89.1,76.9,87.6,76.9z"></path></g><g><path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="
                                    M99.9,100.2c-0.1,0-0.2,0-0.3,0c-0.6-0.1-1.1-0.7-1.1-1.3l0-7l-1.5,0.5c-0.1,0-0.3,0.1-0.4,0.1c-0.4,0-0.7-0.2-1-0.4
                                    c-0.4-0.4-0.5-0.9-0.3-1.4l4.8-12.3c0.2-0.5,0.7-0.9,1.2-0.9c0.1,0,0.2,0,0.3,0c0.6,0.1,1.1,0.7,1.1,1.3l0,7l2.4-0.8
                                    c0.1,0,0.3-0.1,0.4-0.1c0.4,0,0.7,0.2,1,0.4c0.4,0.4,0.4,1,0.2,1.5l-5.7,12.6C101,99.9,100.5,100.2,99.9,100.2z"></path></g>
                             </g>
                            <g class="bottom-right">
                                <g><path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="
                                        M279.7,160.9c-0.7-9.4-7.6-15.4-15.9-15.4c-8.3,0-15.2,6-15.9,15.4l3.4,0c0.7-7.4,6.1-11.8,12.5-11.8c6.5,0,11.8,4.4,12.5,11.8
                                        L279.7,160.9z"></path><path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="
                                        M248.1,161h4.6v14.1h-4.6c-1.9,0-3.5-1.5-3.5-3.3v-7.5C244.6,162.5,246.2,161,248.1,161z"></path><path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="
                                        M279.9,161h-4.3v14.1h4.3c1.8,0,3.3-1.5,3.3-3.3v-7.5C283.2,162.5,281.7,161,279.9,161z"></path><path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="
                                        M283.1,163.7c-0.3-1.5-1.6-2.7-3.2-2.7h-4.3v2.7H283.1z"></path><path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="
                                        M248.1,161h4.5v14.1h-4.5c-1.9,0-3.5-1.5-3.5-3.3v-7.5C244.6,162.5,246.2,161,248.1,161z"></path><path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="
                                        M279.9,161h-4.3v14.1h4.3c1.8,0,3.3-1.5,3.3-3.3v-7.5C283.2,162.5,281.7,161,279.9,161z"></path><path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="
                                        M273.9,159.8c1.2,0,2.2,0.9,2.2,2v12.6c0,1.1-1,2-2.2,2c-1.2,0-2.2-0.9-2.2-2v-12.6C271.6,160.6,272.6,159.8,273.9,159.8z"></path><path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="
                                        M254.1,159.8c-1.1,0-2,0.9-2,2v12.6c0,1.1,0.9,2,2,2c1.1,0,2-0.9,2-2v-12.6C256,160.6,255.2,159.8,254.1,159.8z"></path></g><g><path fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="
                                            M263,176.6c-0.1,0-0.1,0-0.2,0c-0.4-0.1-0.8-0.5-0.8-1l0-5l-1.1,0.4c-0.1,0-0.2,0-0.3,0c-0.3,0-0.5-0.1-0.7-0.3
                                            c-0.3-0.3-0.3-0.7-0.2-1l3.5-8.9c0.1-0.4,0.5-0.6,0.9-0.6c0.1,0,0.1,0,0.2,0c0.5,0.1,0.8,0.5,0.8,1l0,5l1.7-0.6c0.1,0,0.2,0,0.3,0
                                            c0.3,0,0.5,0.1,0.7,0.3c0.3,0.3,0.3,0.7,0.2,1.1l-4.1,9.1C263.7,176.4,263.4,176.6,263,176.6z"></path></g>
                             </g>
                            <g class="bottom">
                                <path class="graphic-icon like-c" fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="
                                M122.4,196.5c-0.3,0.2-0.7,0.2-1,0c-7-3.7-11.7-9-11.6-14.7c0.1-5.2,3.6-6.9,6.3-6.8c2.7,0.1,5,1.8,5.8,2.6h0
                                c0.8-0.8,3.1-2.5,5.8-2.6c2.7-0.1,6.2,1.6,6.3,6.8C134.1,187.5,129.4,192.8,122.4,196.5z" ></path>
                             </g>
                            <g class="bottom-left">
                                <path class="graphic-icon like-b" fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="
                                M30.9,185.6c-0.2,0.1-0.5,0.1-0.7,0c-4.8-2.5-8-6.1-7.9-10.1c0.1-3.6,2.5-4.7,4.3-4.7c1.8,0,3.4,1.2,4,1.8h0c0.5-0.6,2.1-1.7,4-1.8
                                c1.8,0,4.2,1.1,4.3,4.7C39,179.5,35.7,183.1,30.9,185.6z" ></path>
                             </g>
                            <g class="top">
                                <path class="graphic-icon like-a" fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="
                                M146.3,80.1c-0.4,0.2-0.8,0.2-1.1,0c-8.1-4.3-13.6-10.4-13.4-17c0.1-6,4.2-8,7.3-7.9c3.1,0.1,5.8,2,6.7,3h0c0.9-1,3.6-3,6.7-3
                                c3.1-0.1,7.2,1.9,7.3,7.9C159.9,69.7,154.5,75.8,146.3,80.1z"></path>
                             </g>
                            <g class="bottom-left">
                                <polygon class="graphic-icon follow-c" fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" points="
                                120.9,158.3 112.9,158.3 112.9,166.3 105,166.3 105,158.3 97,158.3 97,150.3 105,150.3 105,142.3 112.9,142.3 112.9,150.3
                                120.9,150.3 "></polygon>
                             </g>
                            <g class="right">
                                <polygon class="graphic-icon follow-b" fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" points="
                                175.1,144.9 170,144.9 170,150.1 164.8,150.1 164.8,144.9 159.6,144.9 159.6,139.8 164.8,139.8 164.8,134.6 170,134.6 170,139.8
                                175.1,139.8 "></polygon>
                             </g>
                            <g class="top-right">
                                <polygon class="graphic-icon follow-a" fill="#61E8A8" stroke="#4EB097" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" points="
                                197.7,30.7 192.5,30.7 192.5,35.9 187.3,35.9 187.3,30.7 182.1,30.7 182.1,25.6 187.3,25.6 187.3,20.4 192.5,20.4 192.5,25.6
                                197.7,25.6 " ></polygon>
                             </g>
                            <g class="big-t">
                                <path fill="#61E8A8" stroke="#FFFFFF" stroke-width="2.6" stroke-linecap="round" stroke-linejoin="round" stroke-miterlimit="10" d="M165.89,167c-2.68,2.86-8.89,4.8-14.45,4.89h-.61c-18.67,0-22.73-13.73-22.73-21.74V127.9h-7.35a1.54,1.54,0,0,1-1.54-1.54V115.85a2.62,2.62,0,0,1,1.74-2.47c9.59-3.38,12.59-11.74,13-18.1.12-1.7,1-2.52,2.48-2.52h11A1.54,1.54,0,0,1,149,94.3v17.81h12.84a1.54,1.54,0,0,1,1.54,1.54v12.63a1.54,1.54,0,0,1-1.54,1.54h-12.9v20.59c0,5.17,3.41,6.6,5.51,6.6a15.49,15.49,0,0,0,5-1.06,3.32,3.32,0,0,1,2-.35,1.63,1.63,0,0,1,1.14,1.22l3.4,9.94C166.22,165.54,166.46,166.4,165.89,167Z"></path>
                             </g>
                         </svg>
                        <div class="section-content">
                            <h1 class="section-title">
                                OTOD 让一切开始流动
                             </h1>
                            <p>
                                我们让大学的一切流动起来，简单又随心所欲地分享自己的快乐。图书、故事、图片、GIF 动图、电视剧、链接、俏皮话、冷笑话、不冷的笑话、曲目、MP3、视频、时尚、艺术，以及有深度的内容。
                            </p>
                         </div>
                     </div>
                 </div>
                <div class="section blog-section" :class="{'active': showcaseObjects[2].active,'old-hat':showcaseObjects[2].oldHatActive}" section-title="博客" style="z-index:2">
                    <div class="section-wrapper">
                        <div class="section-content">
                            <h1 class="section-title">OTOD有许多精彩多样的博客</h1>
                            <p>"OTOD提供发表和浏览博客的交流平台，可以让您在学习的同时谈一谈其中的感想和收获。我们也会为您寻找到并关注有对您意义的博客，也会帮助他人发现并关注您的博客."
                            </p>
                        </div>
                    </div>
                </div>
                <div class="section welcome-section" :class="{'active': showcaseObjects[3].active,'old-hat':showcaseObjects[3].oldHatActive}" section-title="好吧，这个不难解释。" style="z-index:1">
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
        dataSection: 'blog'
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
        title: 'OTOD有许多精彩多样的博客。'
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
