<template>
    <div id="index">
        <div class="otod-header">
          <div class="otod-hdc">
            <h1 class="otod-logo">
              <a href="/"></a>
             </h1>
            <div class="menu-nav">
                <ul class="nav-tab">
                  <li v-for="(nav,index) in navObjects" :class="{'active': nav.active}" :key="index" @click="navClick(index)">{{nav.title}}</li>
                  <li>
                    <el-dropdown  trigger="click" @command="moreCommand">
                      <span class="el-dropdown-link more-link">
                        更多<i class="el-icon-arrow-down el-icon--right"></i>
                      </span>
                      <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item command="o">登出</el-dropdown-item>
                        <el-dropdown-item command="c">联系我们</el-dropdown-item>
                      </el-dropdown-menu>
                    </el-dropdown>
                  </li>
                 </ul>
                <div class="nav-search">
                  <el-autocomplete popper-class="el-search" v-model="state3" :fetch-suggestions="querySearch" placeholder="搜索标签" @select="handleSelect">
                    <i class="el-icon-edit el-input__icon" slot="suffix" @click="handleIconClick"></i>
                    <template slot-scope="{ item }">
                        <div class="name">{{ item.Name }}</div>
                     </template>
                   </el-autocomplete>
                 </div>
             </div>
           </div>
         </div>
        <router-view/>
     </div>
</template>

<script>
export default {
  name: 'Index',
  data () {
    return {
      // 菜单
      navObjects: [{
        active: true,
        link: 'Person',
        name: 'home',
        title: '首页'
      }, {
        active: false,
        link: 'Blog',
        name: 'blog',
        title: '博客'
      }, {
        active: false,
        link: 'Market',
        name: 'market',
        title: '二手市场'
      }, {
        active: false,
        link: 'OrderList',
        name: 'service',
        title: '个性化服务'
      }, {
        active: false,
        link: 'File',
        name: 'file',
        title: '文档库'
      }, {
        active: false,
        link: 'Book',
        name: 'book',
        title: '图书馆'
      }],

      // 搜索框
      restaurants: [],
      state3: ''
    }
  },

  mounted () {
    this.loadAll()

    this._changeHead()

    this.initialUserInfo()
  },
  watch: {
    '$route': '_changeHead'
  },
  created () {
    if (this.$getCookie('otod_access_token') !== null) {
      if (this.$route.path === '/') {
        this.$router.replace('/home/person')
      }
    } else {
      this.$router.replace('/login')
    }
  },
  methods: {
    // initial userInfo
    initialUserInfo () {
      // simple user inoformation : headphoto,username,telephone
      if (!this.$store.state.isLogin || this.$store.state.nickname === '') {
        this.$axios({
          method: 'get',
          url: this.$url + '/user/getSimpleInfo',
          params: {
            access_token: this.$getCookie('otod_access_token')
          }
        }).then(function (response) {
          this.$store.commit('initialName', response.data.nickname)
          this.$store.commit('initialHead', response.data.headImage)
          this.$store.commit('initialTel', response.data.telephone)
        }.bind(this))
      }
    },

    _changeHead () {
      if (this.$route.params.page) {
        for (var i = 0; i < this.navObjects.length; ++i) {
          this.navObjects[i].active = (this.navObjects[i].name === this.$route.params.page)
        }
      } else {
        for (i = 0; i < this.navObjects.length; ++i) {
          this.navObjects[i].active = (this.navObjects[i].link === this.$route.name)
        }
      }
    },

    // 菜单样式跳转
    navClick (index) {
      this.$router.push({ name: this.navObjects[index].link, params: {page: this.navObjects[index].name} })
    },

    // 搜索框响应
    querySearch (queryString, cb) {
      var restaurants = this.restaurants
      var results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants
      // 调用 callback 返回建议列表的数据
      cb(results)
    },
    createFilter (queryString) {
      return (restaurant) => {
        return (restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) === 0)
      }
    },

    loadAll () {
      this.$axios({
        method: 'get',
        url: this.$url + '/user/updateTags',
        params: {
          access_token: this.$getCookie('otod_access_token')
        }
      }).then(function (response) {
        this.restaurants = response.data
      }.bind(this))
    },

    handleSelect (item) {
      console.log(item)
    },

    handleIconClick (ev) {
      console.log(ev)
    },

    moreCommand (command) {
      switch (command) {
        case 'o': this.$clearCookie('otod_access_token')
          this.$store.commit('loginOut')
          this.$router.replace('/login')
          break
        case 'c': this.$alert('2017-2018ru软工实践设计作品-OTOD小组开发', 'OTOD共享与交易平台', {
          confirmButtonText: '确定'
        })
          break
      }
    }
  }
}
</script>

<style>
    @import '../assets/css/indexPage.css'
</style>
