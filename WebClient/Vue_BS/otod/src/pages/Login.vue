<template>
    <div id="login" >
        <div class="about-Index-showcase ready show-login" prima-component="text">
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
                <div class="form_content">
                    <div id="signup_forms" class="signup_forms clearfix">
                        <button class="signup_forms_submit" id="signup_forms_submit">
                            <span>开始吧</span>
                         </button>
                        <button class="signup_forms_submit" id="signup_login_button">
                            <span>登录</span>
                         </button>
                     </div>
                 </div>
             </div>
            <div class="showcase">
                <div class="section login-section active" data-section="login" section-title="注册" style="z-index:3">
                    <div id="fullscreen_post_bg" class="fullscreen_post_bg" style= "background-image:url(./static/backgrounds/tumblr_register_1280.jpg)" ></div>
                    <div class="about-Index-btn">What is OTOD?</div>
                 </div>
                <div class="section about-section" data-section="about" section-title="关于" style="z-index:2"></div>
                <div class="section welcome-section" data-section="welcome" section-title="好吧，这个不难解释。" style="z-index:1">
                    <div class="section-wrapper">
                        <div class="fullscreen_post_bg" style="background-image:url(./static/backgrounds/tumblr_welcome_1280.gif)"></div>
                     </div>
                 </div>
             </div>
            <div class="showcase-pagination">
                <div class="dot active" title="注册"></div>
                <div class="dot" title="用起来真是简单到难以解释。"></div>
                <div class="dot" title="Tumblr 就是众多博客。"></div>
             </div>
         </div>
        <div class="icon-header-container" prima-component="header">

         </div>
    </div>
</template>

<script>
export default {
  name: 'Login'
}
</script>

<style>
    /*initial all style*/
*{
    margin:0px;
    padding:0px;
}

a:hover{
    text-decoration: none;
}

.about-Index-showcase{
    height:100%;
    width:100%;
    overflow:hidden;
    position:absolute;
    z-index: 10;
    background-color:#444;
    animation-fill-mode: backwards;
}
.about-Index-showcase .form_container{
    left:50%;
    top:50%;
    width:800px;
    margin-left:-400px;
    position: absolute;
    box-sizing: border-box;
    margin-top:-150px;
    z-index: 5;
    display:none;
}
.about-Index-showcase.show-login .form_container,.about-Index-showcase.show-welcome .form_container{
    display: block;
    animation: signupFormIntro .3s ease-out .7s;
    animation-fill-mode: backwards;
}

.about-Index-showcase .form_container .form-header-welcome{
    display:none;
}

.about-Index-showcase.show-welcome .form_container .form_header{
    display:none;
}

.about-Index-showcase.show-welcome .form_container .form-header-welcome{
    display:block;
}

.signup_forms{
    margin-left:-155px;
    left:50%;
    opacity: 1;
    width: 300px;
    position:relative;
}

#signup_forms_submit{
    background:#529ecc;
    color:#fff;
    border:none;
}

#signup_login_button{
    background-color: #fff;
    color:#444;
    border:none;
}

 .signup_forms_submit {
    width:100%;
    height:45px;
    margin-top:12px;
    position:relative;
    font-size: 16px;
    text-align: center;
    border-radius: 2px;
    font-weight: bold;
}

.logo-word.large{
    float:none;
}

.about-Index-showcase .section-title{
    text-align:center;
    margin-bottom:45px;
    color:#557573;
    font-weight:700;
}

.logo-word{
    overflow: hidden;
    margin: auto;
    width:232px;
    height: 50px;
    padding:0px;
    font-size: 20px;
}

.subheading{
    position:relative;
    width: 517px;
    left: 50%;
    margin:10px 0px 20px -258px;
    line-height: 22px;
    text-align: center;
    font-size: 16px;
    color:#F6F8FC;
}

.about-Index-showcase .section.login-section{
    padding-top:0px;
    color:inherit;
}

.about-Index-showcase .section.about-section{
    background:#61E8A8;
}

.about-Index-showcase .section.welcome-section{
    padding-top:0px;
    color:inherit;
}

.about-Index-showcase .showcase{
    height:100%;
    width:100%;
    position: relative;
    z-index: 1;
}

.about-Index-showcase .section{
    color:#fff;
    height:100%;
    width:100%;
    left:0;
    top:0;
    overflow: hidden;
    position: absolute;
    text-align: center;
    transform:  translateY(0);
    transition: transform .7s cubic-bezier(.825,0,.5,1);
}
.about-Index-showcase .section.old-hat {
    transform: translateY(-100%)
}

.showcase .login-section .about-Index-btn{
    cursor:pointer;
    width:100%;
    height:45px;
    color:#fff;
    background:#61E8A8;
    position:absolute;
    bottom:0px;
    line-height: 45px;
    font-size:17px;
    font-weight:700;
    box-shadow: inset 0 3px 0 rgba(0,0,0,.1);
    z-index: 2;
}

.showcase .welcome-section .section-wrapper {
    display: table;
    height: 100%;
    position: relative;
    width: 100%;
    z-index: 2;
    animation: none;
}

/* the fullscreen background */
.fullscreen_post_bg{
    position:fixed;
    top:0;
    right:0;
    bottom:0;
    left:0;
    background-position: 50% 50%;
}

.about-Index-showcase .section .fullscreen_post_bg{
    background-size:0 0;
}

.about-Index-showcase .section.welcome-section .fullscreen_post_bg,.about-Index-showcase .section.login-section .fullscreen_post_bg{
    background-size:cover;
}

.about-Index-showcase .section.login-section .fullscreen_post_bg{
    bottom:45px;
}


/* showcase-pagination */
.showcase-pagination{
    left: 40px;
    position: absolute;
    top: 50%;
    z-index: 2147483643;
    transform: translateY(-50%);
 }

.showcase-pagination .dot {
    border-radius: 50%;
    box-shadow: inset 0 0 0 3px #fff;
    cursor: pointer;
    height: 16px;
    margin-bottom: 10px;
    opacity: .4;
    position: relative;
    width: 16px;
    box-sizing: border-box
 }

.showcase-pagination .dot:hover {
    opacity: 1;
 }

.showcase-pagination .dot.active {
    background: #fff;
    box-shadow: none;
    opacity: 1
 }


@keyframes signupFormIntro {
    0% {
        opacity: 0;
        transform: translateY(25px)
    }

    to {
        opacity: 1;
        transform: translateY(0)
    }
 }


</style>
