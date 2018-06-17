<template>
  <div id="mycollection">
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
        <div v-if="createdlist.length !== 0">
          <li v-for="create in createdlist" :key="create.id">
            <div class="card" style="width: 10rem;">
              <div class="card-block card-float">
                <h4 class="card-title">{{create.name}}</h4>
                <p class="card-text">浏览数：{{create.views}}</p>
                <p class="card-text">评分：{{create.score}}</p>
                <router-link class="card-link" :to="{path: '/file/filelist', query: {listname: JSON.stringify(createlist)}}" target="_blank">查看</router-link>
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
      createdlist: [],
      collectlist: [],
      listdefault: {
        id: 0,
        name: '默认列表',
        description: '所有文件'
      }
    }
  },
  created () {
    this.getcreate()
    this.getcollect()
  },
  methods: {
    getcreate () {
      var url = 'http://127.0.0.1:8081/vrss/FileList/listfilelist'
      var params = new URLSearchParams()
      params.append('user_id', 1)
      params.append('tag_id', 0)
      params.append('key', null)
      params.append('type', 7)
      this.$http.post(url, params).then((response) => {
        var data = response.data
        console.log(data)
        this.createlist = data
      }).catch((error) => {
        console.log(error)
      })
    },
    getcollect () {
      var url = 'http://127.0.0.1:8081/vrss/FileList/listfilelist'
      var params = new URLSearchParams()
      params.append('user_id', 1)
      params.append('tag_id', 0)
      params.append('key', null)
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
</style>
