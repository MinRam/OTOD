<template>
  <div id="mydownload">
    <ul id="list-ul">
      <div v-if="num == 0">
        <p>还没有下载过文件，快去寻找资源吧！</p>
      </div>
      <div v-else>
        <li v-for="file in filelist" :key="file.id">
          <div class="card" style="width: 10rem;">
            <div class="card-block card-float">
              <h4 class="card-title">{{file.id}}</h4>
              <p class="card-text"></p>
              <router-link class="card-link" :to="{path: '/files', query: {filename: JSON.stringify(file)}}" target="_blank">查看</router-link>
            </div>
          </div>
        </li>
      </div>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'mydownload',
  data () {
    return {
      userid: 1,
      filelist: [],
      num: 0
    }
  },
  created () {
    this.filelist = []
    var url = 'http://127.0.0.1:8082/vrss/Download/list'
    var params = new URLSearchParams()
    params.append('user_id', this.userid)
    this.$http.post(url, params).then((response) => {
      var data = response.data
      console.log(data)
      for (var i = 0; i < data.length; i++) {
        this.num++
        this.filelist.push({
          id: data[i].file.id
        })
      }
    })
  },
  methods: {
  }
}
</script>

<style>
  @import '../../../assets/css/bootstrap.css'
  .card-float {
    position: relative;;
    float: left
  }
</style>
