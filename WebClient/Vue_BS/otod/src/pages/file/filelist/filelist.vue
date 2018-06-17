<template>
  <div id="filelists">
    <span class="switcher" v-bind:class="{'left': isClose, 'right': !isClose}" @click="switcher()">
      <p v-if="isClose == true">未收藏（点击收藏）</p>
      <p v-else>
        已收藏
      </p>
    </span>
    <ul>
      <div v-if="filelist.length !== 0">
        <li v-for="file in filelist" :key="file.id">
          <router-link :to="{path: '/file/files', query: {filename: JSON.stringify(file)}}" target="_blank">{{ file.id }}</router-link>
        </li>
      </div>
      <div v-else>
        还没有文件
      </div>
    </ul>
    <listcom v-bind:id="id" v-bind:userid="userid"></listcom>
  </div>
</template>

<script>
import listcom from '../../../components/file/listcom/comment.vue'
export default {
  components: {
    listcom
  },
  name: 'filelists',
  data () {
    return {
      filelist: [],
      listinfo: {
        type: Object
      },
      type: 0,
      id: 0,
      userid: 1,
      isClose: true, // 假设默认未收藏
      islike: false, // 得到原始状态
      isLikes: false
    }
  },
  watch: {
    islike () {
      var url = 'http://127.0.0.1:8081/vrss/FileList/love'
      var params = new URLSearchParams()
      params.append('filelist_id', this.listinfo.id)
      params.append('user_id', this.userid)
      this.$http.post(url, params).then(() => {
        this.isLikes = !this.isLikes
      })
    }
  },
  created () {
    this.getstate()
    let str = this.$route.query.listname
    this.listinfo = JSON.parse(str)
    this.id = this.listinfo.id
    // console.log(this.listinfo)
    if (this.listinfo.id !== 0) {
      var url = 'http://127.0.0.1:8081/vrss/FileList/listfile'
      var params = new URLSearchParams()
      params.append('filelist_id', this.listinfo.id)
      this.$http.post(url, params).then((response) => {
        var data = response.data
        console.log(response.data)
        for (var i = 0; i < data.length; i++) {
          this.filelist.push({
            id: data[i].id
          })
        }
      })
    }
  },
  methods: {
    getstate () {
      var url = 'http://127.0.0.1:8081/vrss/FileList/lovestate'
      var params = new URLSearchParams()
      params.append('filelist_id', this.listinfo.id)
      params.append('user_id', this.userid)
      this.$http.post(url, params).then((response) => {
        var data = response.data
        console.log(response.data)
        if (data === true) {
          this.isClose = false
          this.isLikes = true
          this.islike = true
        }
      })
    },
    switcher: function () {
      // s实现开关切换
      this.isClose = !this.isClose
      this.islike = !this.islike
    }
  }
}
</script>

<style scoped>
  @import '../../../assets/css/bootstrap.css'
  .switcher {
    transition: left 0.8s;
  }
  .left {
    color: green;
  }
  .right {
    color: red;
  }
</style>
