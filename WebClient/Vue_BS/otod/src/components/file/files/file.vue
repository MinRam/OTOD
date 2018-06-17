<template>
  <div id='file'>
    <div class="info">
      <ul>
        <li>
          <div class="card-block">
            <p class="card-text">
              文件名：<input type="text" v-model="file.name" :readonly="read"><br>
              描  述：<input type="text" v-model="file.description" :readonly="read"><br>
              标  签：<div>
                <ul>
                  <li v-for="tag in tags" :key="tag.id"><label class="form-check-label">
                    <input class="form-check-input" type="checkbox" @change="getstate(tag)" :checked="tag.right" :readonly="read"> {{ tag.content }}
                  </label>
                </li>
              </ul>
              </div>
              <div v-show="upload === true">
                <button @click="changeread">修改文件信息</button>
                <div v-show="!read">
                  <button>确定</button><button>取消</button>
                </div>
              </div>
              <a v-bind:href="encodeURI('http://127.0.0.1:8081/vrss/Download/download?' + '&user_id=' + userid + '&file_id=' + file.id)" v-bind:download="file.name">下载</a>
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
  props: ['id', 'userid', 'upload'],
  data () {
    return {
      file: {
        type: Object
      },
      isClose: true, // 假设默认未收藏
      optionlist: [],
      myvalue: 0, // 默认是0
      islike: false, // 得到原始状态
      isLikes: false,
      read: true, // 只读
      tags: []
    }
  },
  created () {
    this.getinfo()
    this.getlist()
    this.gettags()
  },
  updated () {
    if (this.islike !== this.isLikes) {
      var url = 'http://127.0.0.1:8081/vrss/FileInfo/love'
      var params = new URLSearchParams()
      params.append('user_id', this.userid)
      params.append('file_id', this.id)
      this.$http.post(url, params).then(() => {
        this.isLikes = !this.isLikes
      }).catch((error) => {
        console.log(error)
      })
    }
    if (this.myvalue !== 0 && this.islike === true) {
      var url1 = 'http://127.0.0.1:8081/vrss/FileList/addfile'
      var params1 = new URLSearchParams()
      params1.append('filelist_id', this.myvalue)
      params1.append('file_id', this.id)
      this.$http.post(url1, params1).then(() => {
      }).catch((error) => {
        console.log(error)
      })
    }
  },
  methods: {
    gettag () {
      var url = 'http://127.0.0.1:8081/vrss/FileInfo/filetag'
      var params = new URLSearchParams()
      params.append('file_id', this.id)
      this.$http.post(url, params).then((response) => {
        var data = response.data
        for (var i = 0; i < data.length; i++) {
          for (var j = i; j < this.tags.length; j++) {
            if (data[i].id === this.tags[j].id) {
              this.tags[j].right = true
              break
            }
          }
        }
      }).catch((error) => {
        console.log(error)
      })
    },
    getstate (tag) {
      tag.right = !tag.right
    },
    gettags () {
      var url = 'http://127.0.0.1:8081/vrss/Tag/listtag'
      var params = new URLSearchParams()
      this.$http.post(url, params).then((response) => {
        var data = response.data
        this.tags = data
        for (var i = 0; i < this.tags.length; i++) {
          this.$set(this.tags[i], 'right', false)
        }
      }).catch((error) => {
        console.log(error)
      }).then(this.gettag())
    },
    changeread () {
      this.read = !this.read
    },
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
          this.isLikes = true
          this.islike = true
        }
      }).catch((error) => {
        console.log(error)
      })
    },
    getinfo () {
      var url = 'http://127.0.0.1:8081/vrss/FileInfo/get'
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
      var url1 = 'http://127.0.0.1:8081/vrss/FileList/listfilelist'
      var params1 = new URLSearchParams()
      params1.append('user_id', this.userid)
      params1.append('tag_id', 0)
      params1.append('key', null)
      params1.append('type', 7)
      this.$http.post(url1, params1).then((response) => {
        var data = response.data
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
      var url = 'http://127.0.0.1:8081/vrss/Download/add'
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
      this.islike = !this.islike
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
