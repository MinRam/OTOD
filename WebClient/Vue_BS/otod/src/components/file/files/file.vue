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
              <a v-bind:href="'http://127.0.0.1:8082/vrss/Download/download?file_url=' + file.file_url" v-bind:download="file.name">下载</a>
              <span class="switcher" v-bind:class="{'left': isClose, 'right': isOpen}" @click="switcher()">
                <p v-if="isClose == true">未收藏（点击收藏）</p>
                <p v-else>
                  已收藏至
                </p>
              </span>
              <select v-show="isOpen">
                <option value ="0">默认收藏夹</option>
                <option value ="1">收藏1</option>
                <option value="2">收藏2</option>
                <option value="3">收藏3</option>
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
      isClose: true,
      isOpen: false// 假设默认关闭
    }
  },
  created () {
    console.log(this.id)
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
  methods: {
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
      this.isOpen = !this.isOpen
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
