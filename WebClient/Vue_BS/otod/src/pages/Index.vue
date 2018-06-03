<template>
    <div id="index">
     <div class="otod-header">
        <h1 class="otod-logo">OTOD</h1>
        <div class="menu-nav">
            <ul class="nav-tab">
                <li v-for="(nav,index) in navObjects">
                    <a :href="{{nav.href}}">{{nav.title}}</a>
                 </li>
            </ul>
            <div class="nav-search">
                <div class="ssch">
                    <input type="text" placeholder="搜索用户、标签"/>
                 </div>
             </div>
         </div>
      </div>
     </div>
</template>

<script>
export default {
  name: 'Index',
  data () {
    return {
      navObjects: [{
        href: 'www.baidu.com',
        title: '首页'
      }, {
        href: 'www.baidu.com',
        title: '博客'
      }, {
        href: 'www.baidu.com',
        title: '二手市场'
      }, {
        href: 'www.baidu.com',
        title: '个性化服务'
      }, {
        href: 'www.baidu.com',
        title: '图书馆'
      }, {
        href: 'www.baidu.com',
        title: '文档库'
      }]
    }
  }
}
</script>

<style>
    @import '../assets/css/indexPage.css';
</style>

