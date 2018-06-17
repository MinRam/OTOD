<template>
  <div id="myupload">
    <ul id="list-ul">
      <li v-for="file in filelist" :key="file.id">
        <div class="card" style="width: 10rem;">
          <div class="card-block card-float">
            <h4 class="card-title">{{file.id}}</h4>
            <p class="card-text">{{file.name}}</p>
            <router-link class="card-link" :to="{path: '/file/files', query: {filename: JSON.stringify(file)}}" target="_blank">查看</router-link>
          </div>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'myupload',
  data () {
    return {
      filelist: [],
      userid: 1
    }
  },
  created () {
    this.filelist = []
    var url = 'http://127.0.0.1:8081/vrss/FileInfo/list'
    var params = new URLSearchParams()
    params.append('user_id', this.userid)
    params.append('tag_id', 0)
    params.append('key', null)
    params.append('type', 9)
    this.$http.post(url, params).then((response) => {
      var data = response.data
      console.log(data)
      this.filelists = []
      for (var i = 0; i < data.length; i++) {
        this.filelist.push({
          id: data[i].id,
          name: data[i].name,
          create_time: data[i].create_time,
          description: data[i].description
        })
      }
    }).catch((error) => {
      console.log(error)
    })
  },
  methods: {
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
