<template>
  <div id="mycollection">
    <div>
      创建新专辑
      <div>
        名称：<input type="text" v-model="newname" placeholder="输入新专辑名称">
        描述：<input type="text" v-model="newdescription" placeholder="输入新专辑描述">
        <ul>
          <div>
            <li v-for="tag in tags" :key="tag.id">
              <label class="form-check-label">
                <input class="form-check-input" type="checkbox" @click="getstate(tag)" :checked="tag.right"> {{ tag.content }}
              </label>
            </li>
          </div>
        </ul>
        <button @click="confirm()">确定</button> <button @click="cancle()">取消</button>
      </div>
    </div>
    <ul id="list-ul">
      <div>
        <li v-bind="listdefault">
          <div class="card" style="width: 10rem;">
            <div class="card-block card-float">
              <h4 class="card-title">{{listdefault.name}}</h4>
              <h4 class="card-title">{{listdefault.description}}</h4>
              <router-link class="card-link" :to="{path: '/file/filelist', query: {listname: JSON.stringify(listdefault)}}" target="_blank">查看</router-link>
            </div>
          </div>
        </li>
      </div>
      <div>
        我创建的专辑
        <div>
        </div>
        <div v-if="createdlist.length !== 0">
          <li v-for="create in createdlist" :key="create.id">
            <div class="card" style="width: 10rem;">
              <div class="card-block card-float">
                <h4 class="card-title">{{create.name}}</h4>
                <p class="card-text">浏览数：{{create.views}}</p>
                <p class="card-text">评分：{{create.score}}</p>
                <router-link class="card-link" :to="{path: '/file/filelist', query: {listname: JSON.stringify(createdlist)}}" target="_blank">查看</router-link>
              </div>
            </div>
          </li>
        </div>
        <div v-else>
          <li>
            还没有创建过的专辑
          </li>
        </div>
      </div>
      <div>
        我收藏的专辑
        <div v-if="collectlist.length !== 0">
          <li v-for="collect in collectlist" :key="collect.id">
            <div class="card" style="width: 10rem;">
              <div class="card-block card-float">
                <h4 class="card-title">{{collect.name}}</h4>
                <p class="card-text">浏览数：{{collect.views}}</p>
                <p class="card-text">评分：{{collect.score}}</p>
                <router-link class="card-link" :to="{path: '/file/filelist', query: {listname: JSON.stringify(collectlist)}}" target="_blank">查看</router-link>
              </div>
            </div>
          </li>
        </div>
        <div v-else>
          <li>
            还没有收藏过的专辑
          </li>
        </div>
      </div>
    </ul>
  </div>
</template>

<script>
import axios from 'axios'
import Vue from 'vue'
Vue.prototype.$http = axios
export default {
  name: 'mycollection',
  props: ['userid'],
  data () {
    return {
      tags: [],
      createdlist: [],
      collectlist: [],
      listdefault: {
        id: 0,
        name: '默认列表',
        description: '所有文件'
      },
      newdescription: '',
      newname: ''
    }
  },
  created () {
    this.getcreate()
    this.getcollect()
    this.gettags()
  },
  methods: {
    confirm () {
      var url = 'http://127.0.0.1:8081/vrss/FileList/add'
      var params = new URLSearchParams()
      params.append('name', this.newname)
      params.append('creator_id', this.userid)
      params.append('description', this.newdescription)
      this.$http.post(url, params).then((response) => {
        var data = response.data
        this.createdlist.unshift(data)
        var tags = []
        for (var i = 0; i < this.tags.length; i++) {
          if (this.tags[i].right === true) {
            tags.push(this.tags[i].id)
          }
        }
        this.cancle()
        var url1 = 'http://127.0.0.1:8081/vrss/Tag/addfilelisttag'
        var params1 = new URLSearchParams()
        params1.append('filelist_id', data.id)
        params1.append('tag_id', tags)
        this.$http.post(url1, params1).then((response) => {
          console.log(response)
        })
      }).catch((error) => {
        console.log(error)
      })
    },
    getstate (tag) {
      tag.right = !tag.right
    },
    cancle () {
      this.newname = ''
      this.newdescription = ''
      for (var i = 0; i < this.tags.length; i++) {
        this.tags[i].right = false
      }
    },
    gettags () {
      var url = 'http://127.0.0.1:8081/vrss/Tag/listtag'
      var params = new URLSearchParams()
      this.$http.post(url, params).then((response) => {
        var data = response.data
        console.log(data)
        this.tags = data
        for (var i = 0; i < this.tags.length; i++) {
          this.$set(this.tags[i], 'right', false)
        }
      }).catch((error) => {
        console.log(error)
      })
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
      }).catch((error) => {
        console.log(error)
      })
    },
    getcollect () {
      var url = 'http://127.0.0.1:8081/vrss/FileList/listfilelist'
      var params = new URLSearchParams()
      params.append('user_id', 1)
      params.append('type', 8)
      this.$http.post(url, params).then((response) => {
        var data = response.data
        console.log(data)
        this.collectlist = data
      }).catch((error) => {
        console.log(error)
      })
    }
  }
}
</script>

<style scoped>
  @import '../../../assets/css/bootstrap.css'
  .card-float {
    position: relative;;
    float: left
  }
  [v-clock] {
                display: none;
            }
</style>
