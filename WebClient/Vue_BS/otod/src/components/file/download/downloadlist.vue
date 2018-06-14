<template>
  <div id="list">
    <ul>
       <li v-for="file in filelist" :key="file.id">
        <router-link :to="{path: '/file/files', query: {filename: JSON.stringify(file)}}" target="_blank">{{ file.id }}</router-link>
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
        var url = 'http://127.0.0.1:8082/vrss/FileInfo/list'
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
        this.getlist()
      }
    }
  },
  methods: {
    getdownload () {
      this.filelist = []
      var url = 'http://127.0.0.1:8082/vrss/FileInfo/list'
      var params = new URLSearchParams()
      params.append('tag_id', 0)
      params.append('key', null)
      params.append('type', 1)
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
    getsearch () {
      this.filelist = []
      var url = 'http://127.0.0.1:8082/vrss/FileInfo/list'
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
      if (this.str === '') {
        this.getdownload()
      } else {
        this.getsearch()
      }
    }
  }
}
</script>
