<template>
  <div id="list">
    <ul>
       <li v-for="file in filelist" :key="file.id">
        <div class="card" style="width: 10rem;">
          <div class="card-block card-float">
            <h4 class="card-title">{{file.name}}</h4>
            <p class="card-text">浏览数：{{file.views}}</p>
            <p class="card-text">评分：{{file.score}}</p>
            <router-link :to="{path: '/file/files', query: {filename: JSON.stringify(file)}}" target="_blank">查看</router-link>
          </div>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios'
import Vue from 'vue'
Vue.prototype.$http = axios

export default {
  name: 'downloadlist',
  props: ['str', 'tag_id'],
  data () {
    return {
      filelist: []
    }
  },
  created () {
    this.getlist()
  },
  watch: {
    str () {
      this.getlist()
    },
    tag_id () {
      if (this.tag_id !== 0) {
        var url = 'http://127.0.0.1:8081/vrss/FileInfo/list'
        var params = new URLSearchParams()
        params.append('user_id', 0)
        params.append('tag_id', this.tag_id)
        params.append('key', null)
        params.append('type', 7)
        this.$http.post(url, params).then((response) => {
          var data = response.data
          console.log(data)
          this.filelist = data
        }).catch((error) => {
          console.log(error)
        })
      } else {
        this.getdownload()
      }
    }
  },
  methods: {
    getdownload () {
      this.filelist = []
      var url = 'http://127.0.0.1:8081/vrss/FileInfo/list'
      var params = new URLSearchParams()
      params.append('tag_id', 0)
      params.append('key', null)
      params.append('type', 1)
      this.$http.post(url, params).then((response) => {
        var data = response.data
        console.log(response.data)
        for (var i = 0; i < data.length; i++) {
          this.filelist.push({
            id: data[i].id,
            name: data[i].name,
            score: data[i].score,
            views: data[i].views
          })
        }
      })
    },
    getsearch () {
      this.filelist = []
      var url = 'http://127.0.0.1:8081/vrss/FileInfo/list'
      var params = new URLSearchParams()
      params.append('tag_id', 0)
      params.append('key', this.str)
      params.append('type', 8)
      this.$http.post(url, params).then((response) => {
        var data = response.data
        console.log(response.data)
        for (var i = 0; i < data.length; i++) {
          this.filelist.push({
            id: data[i].id
          })
        }
      })
    },
    getlist () {
      if (this.str === '' || this.str == null) {
        this.getdownload()
      } else {
        this.getsearch()
      }
    }
  }
}
</script>
