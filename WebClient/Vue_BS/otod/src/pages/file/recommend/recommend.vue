<template class="template-float">
  <div id="recommend div-float">
    <type @getlist="getlist"></type>
    <ul id="list-ul">
      <li v-for="filelist in filelists" :key="filelist.id">
        <div class="card" style="width: 10rem;">
          <div class="card-block card-float">
            <h4 class="card-title">{{filelist.name}}</h4>
            <p class="card-text">浏览数：{{filelist.views}}</p>
            <p class="card-text">评数：{{filelist.score}}</p>
            <router-link class="card-link" :to="{path: '/file/filelist', query: {listname: JSON.stringify(filelist)}}" target="_blank">查看</router-link>
          </div>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
import type from '../../../components/file/recommend/type.vue'
import axios from 'axios'
import Vue from 'vue'
Vue.prototype.$http = axios
export default {
  name: 'recommend',
  components: {
    type
  },
  data () {
    return {
      filelists: []
    }
  },
  created () {
    var url = 'http://127.0.0.1:8081/vrss/FileList/listfilelist'
    var params = new URLSearchParams()
    params.append('tag_id', 0)
    params.append('key', null)
    params.append('type', 1)
    this.$http.post(url, params).then((response) => {
      var data = response.data
      console.log(response.data)
      for (var i = 0; i < data.length; i++) {
        this.filelists.push({
          id: data[i].id,
          name: data[i].name,
          create_time: data[i].create_time,
          description: data[i].description,
          views: data[i].views,
          score: data[i].score
        })
      }
    })
  },
  methods: {
    getlist (id) {
      var url = 'http://127.0.0.1:8082/vrss/FileList/listfilelist'
      var params = new URLSearchParams()
      params.append('tag_id', id)
      params.append('key', null)
      params.append('type', 5)
      this.$http.post(url, params).then((response) => {
        var data = response.data
        this.filelists = []
        for (var i = 0; i < data.length; i++) {
          this.filelists.push({
            id: data[i].id,
            name: data[i].name,
            create_time: data[i].create_time,
            description: data[i].description,
            views: data[i].views,
            score: data[i].score
          })
        }
      }).catch((error) => {
        console.log(error)
      })
    }
  }
}
</script>

<style scoped>
  @import '../../../assets/css/bootstrap.css'
  .template-float {
    position: relative;
    top: 103;
  }.
  .card-float {
    position: relative;;
    float: left
  }
  .div-float {
    float: left;
  }
</style>
