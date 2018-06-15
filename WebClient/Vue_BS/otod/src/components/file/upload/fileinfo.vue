<template>
  <div id="fileinfo">
    <form>
       <div class="form-group">
        <label for="exampleInputFile">上传资源：</label>
        <input type="file" class="form-control-file" id="exampleInputFile" aria-describedby="fileHelp" @change="getFile ($event)">
        <small id="fileHelp" class="form-text text-muted">从本地文件中选择需要上传的文件</small>
      </div>
      <div class="form-group" id="filename">
        <label for="exampleInputEmail1">资源名称：</label>
        <input v-model="filename" type="textarea" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="请输入资源名称">
      </div>
      <div class="form-group">
        <label for="exampleTextarea">资源描述：</label>
        <textarea v-model="description" class="form-control" id="exampleTextarea" rows="3" placeholder="请尽可能详细填写文件描述，过于模糊的描述可能无法通过审核。"></textarea>
      </div>
      <div class="form-group row">
        <label class="col-sm-2">请选择标签</label>
        <div class="col-sm-10">
          <div class="form-check">
            <ul>
              <li v-for="tag in tags" v-bind:key="tag.id">
                <label class="form-check-label">
                  <input class="form-check-input" type="checkbox" @change="getstate(tag)"> {{ tag.content }}
                </label>
              </li>
            </ul>
          </div>
        </div>
      </div>
      <button type="submit" class="btn btn-primary" @click="submitForm($event)">上传文件</button>
    </form>
  </div>
</template>

<script>
import Vue from 'vue'
import axios from 'axios'
Vue.prototype.$http = axios
export default{
  name: 'fileinfo',
  // el: '#fileinfo',
  data: function () {
    return {
      filename: '',
      description: '',
      file: '',
      tags: [],
      userid: 1,
      fileid: 0
    }
  },
  created () {
    var url = 'http://127.0.0.1:8081/vrss/Tag/listtag'
    var params = new URLSearchParams()
    this.$http.post(url, params).then((response) => {
      var data = response.data
      this.tags = data
      for (var i = 0; i < this.tags.length; i++) {
        this.$set(this.tags[i], 'right', 0)
      }
    }).catch((error) => {
      console.log(error)
    })
  },
  methods: {
    getFile (event) {
      this.file = event.target.files[0]
      this.filename = this.file.name
      console.log(this.file)
    },
    submitForm (event) {
      event.preventDefault()
      let formData = new FormData()
      formData.append('name', this.filename)
      formData.append('uploader_id', this.userid)
      formData.append('size', this.file.size)
      formData.append('type', this.file.type)
      formData.append('description', this.description)
      formData.append('file', this.file)
      let config = {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      }
      this.$http.post('http://127.0.0.1:8082/vrss/FileInfo/upload', formData, config).then((response) => {
        /* 这里做处理 */
        this.fileid = response.data
        console.log('id' + this.fileid)
        alert('上传成功')
      }).then((response) => {
        // 添加标签
        this.addtag()
      })
    },
    getstate (tag) {
      if (tag.right === 0) {
        tag.right = 1
      } else {
        tag.right = 0
      }
    },
    addtag () {
      var tags = []
      for (var i = 0; i < this.tags.length; i++) {
        if (this.tags[i].right === 1) {
          tags.push(this.tags[i].id)
        }
      }
      var url = 'http://127.0.0.1:8082/vrss/Tag/addfiletag'
      var params = new URLSearchParams()
      params.append('file_id', this.fileid)
      params.append('tag_id', tags)
      console.log(params + ' 参数')
      this.$http.post(url, params).then((response) => {
        console.log('sattus' + response.status)
      })
    }
  }
}
</script>

<style scoped>
  @import '../../../assets/css/bootstrap.css'
</style>
