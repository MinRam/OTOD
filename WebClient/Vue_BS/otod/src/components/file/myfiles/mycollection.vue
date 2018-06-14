<template>
  <div id="mycollection">
    <ul id="list-ul">
      <li v-for="file in files" :key="file.id">
        <div class="card" style="width: 10rem;">
          <div class="card-block card-float">
            <h4 class="card-title">{{file.name}}</h4>
            <p class="card-text">{{file.description}}</p>
            <router-link class="card-link" :to="{path: '/files', query: {listname: JSON.stringify(filelist)}}" target="_blank">查看</router-link>
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
  name: 'mycollection',
  data () {
    return {
      files: [],
      file: {
        type: Object
      }
    }
  },
  created () {
    this.getfile()
  },
  methods: {
    getfile () {
      this.$http.get('').then((response) => {
        response = response.body
        console.log(response)
        for (var i = 0; (response.data).lenth; i++) {
          this.file.id = response.data[i].id
          this.file.name = response.data[i].name
          this.file.url = response.data[i].URL
          this.files.push(this.file)
          for (var key in this.file) {
            delete this.file[key]
          }
        }
      })
    }
  }
}
</script>

<style>
  @import '../../../assets/bootstrap/css/bootstrap.css'
  .card-float {
    position: relative;;
    float: left
  }
</style>
