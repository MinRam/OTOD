<template>
  <div id="filelists">
    列表名：<input type="text" v-model="name" :readonly="read"><br>
    描  述：<input type="text" v-model="description" :readonly="read"><br>
    <div v-show="id !== 0">标  签：
                <ul>
                  <li v-for="tag in tags" :key="tag.id"><label class="form-check-label">
                    <input class="form-check-input" type="checkbox" @change="changestate(tag)" :checked="tag.right" :readonly="read"> {{ tag.content }}
                  </label>
                </li>
              </ul>
              </div>
    <div v-show="create === true">
      <button @click="changeread" v-show="id !== 0">修改列表信息</button>
      <div v-show="!read">
        <button @click="confirm">确定</button><button @click="cancle">取消</button>
      </div>
    </div>
    <span v-show="id !== 0" class="switcher" v-bind:class="{'left': isClose, 'right': !isClose}" @click="switcher()">
      <p v-if="isClose == true">未收藏（点击收藏）</p>
      <p v-else>
        已收藏
      </p>
    </span>
    <p><b>文件列表</b></p>
    <ul>
      <div v-if="filelist.length !== 0">
        <li v-for="file in filelist" :key="file.id">
          <div class="card" style="width: 10rem;">
            <div class="card-block card-float">
              <h4 class="card-title">{{file.name}}</h4>
              <router-link :to="{path: '/file/files', query: {filename: JSON.stringify(file)}}" target="_blank">查看</router-link>
              <button v-show="id === 0" @click="changecopy(file.id)">复制到</button>
              <div v-show="copystate && index === file.id">
                <li v-for="create in createdlist" v-bind:key="create.id">
                  <label class="form-check-label">
                    <input class="form-check-input" type="checkbox" :checked="create.right" @click="f(create)"> {{ create.name }}
                  </label>
                </li>
                <button @click="confirm1(file.id)">确定</button> <button @click="cancle1">取消</button>
              </div>
              <button v-show="create" @click="delfile(file.id)">删除</button>
            </div>
          </div>
        </li>
      </div>
      <div v-else>
        还没有文件
      </div>
    </ul>
    <listcom v-bind:id="id" v-bind:userid="userid" v-show="id !== 0"></listcom>
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
      index: 0,
      filelist: [],
      listinfo: {
        type: Object
      },
      type: 0,
      id: 0,
      userid: 1,
      isClose: true, // 假设默认未收藏
      islike: false, // 得到原始状态
      isLikes: false,
      create: false,
      read: true,
      tags: [],
      name: '',
      description: '',
      tagstate: [],
      copystate: false,
      createdlist: []
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
    if (this.$route.query.create === 'create') {
      this.create = true
    }
    let str = this.$route.query.listname
    this.listinfo = JSON.parse(str)
    this.id = this.listinfo.id
    this.name = this.listinfo.name
    this.description = this.listinfo.description
    if (this.id !== 0) {
      this.gettags()
    }
    this.getstate()
    // console.log(this.listinfo)
    if (this.listinfo.id !== 0) {
      var url = 'http://127.0.0.1:8081/vrss/FileList/listfile'
      var params = new URLSearchParams()
      params.append('filelist_id', this.listinfo.id)
      this.$http.post(url, params).then((response) => {
        var data = response.data
        console.log(response.data)
        this.filelist = data
      })
    } else {
      var url1 = 'http://127.0.0.1:8081/vrss/FileInfo/list'
      var params1 = new URLSearchParams()
      params1.append('user_id', this.userid)
      params1.append('type', 10)
      this.$http.post(url1, params1).then((response) => {
        var data = response.data
        console.log(response.data)
        this.filelist = data
      })
    }
    this.getcreate()
  },
  methods: {
    f (create) {
      create.right = !create.right
    },
    lovefile (fid, lid) {
      var url = 'http://127.0.0.1:8081/vrss/FileList/addfile'
      var params = new URLSearchParams()
      params.append('filelist_id', lid)
      params.append('file_id', fid)
      this.$http.post(url, params).catch(error => {
        console.log(error)
      })
    },
    confirm1 (id) {
      for (var i = 0; i < this.createdlist.length; i++) {
        if (this.createdlist[i].right === true) {
          this.lovefile(id, this.createdlist[i].id)
        }
      }
      this.cancle1()
    },
    cancle1 () {
      for (var i = 0; i < this.createdlist.length; i++) {
        this.createdlist[i].right = false
      }
      this.copystate = false
    },
    delfile (id) {
      var url = 'http://127.0.0.1:8081/vrss/FileList/deletefile'
      var params = new URLSearchParams()
      params.append('filelist_id', this.id)
      params.append('file_id', id)
      this.$http.post(url, params)
    },
    getcreate () {
      var url = 'http://127.0.0.1:8081/vrss/FileList/listfilelist'
      var params = new URLSearchParams()
      params.append('user_id', 1)
      params.append('type', 7)
      this.$http.post(url, params).then((response) => {
        var data = response.data
        console.log(data)
        this.createdlist = data
        for (var i = 0; i < this.createdlist.length; i++) {
          this.$set(this.createdlist[i], 'right', false)
        }
      }).catch((error) => {
        console.log(error)
      })
    },
    changecopy (id) {
      this.copystate = !this.copystate
      this.index = id
    },
    chtag () {
      var tag1 = []
      var tag2 = []
      for (var i = 0; i < this.tags.length; i++) {
        if (this.tags[i].right !== this.tagstate[i].right) {
          if (this.tagstate[i].right === true) {
            tag1.push(this.tagstate[i].id)
          } else {
            tag2.push(this.tagstate[i].id)
          }
        }
      }
      this.deltag(tag1)
      this.addtag(tag2)
    },
    deltag (tag) {
      var url = 'http://127.0.0.1:8081/vrss/Tag/deletefilelisttag'
      var params = new URLSearchParams()
      params.append('filelist_id', this.id)
      params.append('tag_id', tag)
      this.$http.post(url, params).then((response) => {
        console.log(response.status)
      })
    },
    addtag (tag) {
      var url = 'http://127.0.0.1:8081/vrss/Tag/addfilelisttag'
      var params = new URLSearchParams()
      params.append('filelist_id', this.id)
      params.append('tag_id', tag)
      this.$http.post(url, params).then((response) => {
        console.log(response.status)
      })
    },
    chfile () {
      this.listinfo.name = this.name
      this.listinfo.description = this.description
      var url = 'http://127.0.0.1:8081/vrss/FileList/update'
      var params = new URLSearchParams()
      params.append('name', this.name)
      params.append('description', this.description)
      params.append('filelist_id', this.id)
      this.$http.post(url, params).catch((error) => {
        console.log(error)
      })
      this.read = true
    },
    confirm () {
      this.chfile()
      this.chtag()
    },
    cancle () {
      this.name = this.listinfo.name
      this.description = this.listinfo.description
      for (var i = 0; i < this.tags.length; i++) {
        this.tags[i].right = false
        for (var j = 0; j < this.tagstate.length; j++) {
          if (this.tagstate[j].id === this.tags[i].id) {
            this.tags[i].right = true
            break
          }
        }
      }
      this.read = true
    },
    gettag () {
      var url = 'http://127.0.0.1:8081/vrss/FileList/filelisttag'
      var params = new URLSearchParams()
      params.append('filelist_id', this.id)
      this.$http.post(url, params).then((response) => {
        var data = response.data
        console.log(data)
        for (var i = 0; i < data.length; i++) {
          for (var j = 0; j < this.tags.length; j++) {
            if (data[i].id === this.tags[j].id) {
              this.tags[j].right = true
              this.tagstate[j].right = true
              break
            }
          }
        }
      }).catch((error) => {
        console.log(error)
      })
    },
    changestate (tag) {
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
          this.tagstate.push({'id': this.tags[i].id, 'right': false})
        }
      }).catch((error) => {
        console.log(error)
      }).then(this.gettag())
    },
    changeread () {
      this.read = !this.read
    },
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
