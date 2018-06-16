<template>
  <div id='file'>
    <div class="info">
      <ul>
        <li>
          <div class="card-block">
            <p class="card-text">
              {{ file.id }}<br>
              {{ file.name }}<br>
              {{ file.description }}<br>
              <a v-bind:href="'http://127.0.0.1:8082/vrss/Download/download?file_url=' + file.file_url + '&user_id=' + userid" v-bind:download="file.name">下载</a>
              <span class="switcher" v-bind:class="{'left': isClose, 'right': !isClose}" @click="switcher()">
                <p v-if="isClose == true">未收藏（点击收藏）</p>
                <p v-else>
                  已收藏
                </p>
              </span>
              <select v-model="myvalue">
                <option v-for="item in optionlist" :key="item.id" :value="item.id">{{item.name}}</option>
              </select>
            </p>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: 'file',
  props: ['id', 'userid'],
  data () {
    return {
      file: {
        type: Object
      },
      isClose: true, // 假设默认未收藏
      optionlist: [],
      myvalue: 0 // 默认是0
    }
  },
  created () {
    this.getinfo()
    this.getlist()
  },
  destroyed () {

  },
  methods: {
    isLike () {
      var url = 'http://127.0.0.1:8081/vrss/FileInfo/lovestate'
      var params = new URLSearchParams()
      params.append('user_id', this.userid)
      params.append('file_id', this.id)
      this.$http.post(url, params).then((response) => {
        var data = response.data
        console.log(data)
        if (data === true) {
          this.isClose = false
        }
      }).catch((error) => {
        console.log(error)
      })
    },
    getinfo () {
      var url = 'http://127.0.0.1:8082/vrss/FileInfo/get'
      var params = new URLSearchParams()
      params.append('file_id', this.id)
      this.$http.post(url, params).then((response) => {
        var data = response.data
        console.log(data)
        this.file = data
      }).catch((error) => {
        console.log(error)
      })
    },
    getlist () {
      var url1 = 'http://127.0.0.1:8082/vrss/FileList/listfilelist'
      var params1 = new URLSearchParams()
      params1.append('user_id', this.userid)
      params1.append('tag_id', 0)
      params1.append('key', null)
      params1.append('type', 7)
      this.$http.post(url1, params1).then((response) => {
        var data = response.data
        console.log(data)
        if (data != null) {
          for (var i = 0; i < data.length; i++) {
            this.optionlist.push({
              id: data[i].id,
              name: data[i].name
            })
          }
        }
      }).catch((error) => {
        console.log(error)
      }).then(() => {
        this.optionlist.unshift({
          id: 0,
          name: '默认收藏夹'
        })
      })
    },
    adddownload () {
      var url = 'http://127.0.0.1:8082/vrss/Download/add'
      var params = new URLSearchParams()
      params.append('user_id', this.userid)
      params.append('file_id', this.id)
      this.$http.post(url, params).then((response) => {
        console.log(response)
      }).catch((error) => {
        console.log(error)
      })
    },
    switcher: function () {
      // s实现开关切换
      this.isClose = !this.isClose
    }
  }
}
</script>

<style scoped>
  @import '../../../assets/css/bootstrap.css'
  .info {
  }
  .comment {
  }
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
