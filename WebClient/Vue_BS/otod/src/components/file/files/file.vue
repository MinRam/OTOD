<template>
  <div id='file'>
    <div class="info">
      <ul>
        <li>
          <div class="card-block">
            <p class="card-text">
              {{ file.id }}<br>
              {{ file.name }}<br>
              {{ file.description }}<br>
              <a v-bind:href="'http://127.0.0.1:8082/vrss/Download/download?file_url=' + file.file_url" v-bind:download="file.name">下载</a>
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
  props: ['id', 'userid'],
  data () {
    return {
      file: {
        type: Object
      }
    }
  },
  created () {
    console.log(this.id)
    var url = 'http://127.0.0.1:8082/vrss/FileInfo/get'
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
  methods: {
    adddownload () {
      var url = 'http://127.0.0.1:8082/vrss/Download/add'
      var params = new URLSearchParams()
      params.append('user_id', this.userid)
      params.append('file_id', this.id)
      this.$http.post(url, params).then((response) => {
        console.log(response)
      }).catch((error) => {
        console.log(error)
      })
    }
  }
}
</script>

<style scoped>
  @import '../../../assets/bootstrap/css/bootstrap.css'
  .info {
  }
  .comment {
  }
</style>
