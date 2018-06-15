<template>
  <div class="main" id="comment">
      <div class="container">
        <div class="col-md-9 rightBox" id="comment">
          <Content1 v-bind:comments="comments" v-on:change="changeCommenter">
          </Content1>
          <commentarea v-bind:type="type" v-bind:replyid="replyid" v-on:submit="addComment" v-on:canel="canelCommit"></commentarea>
        </div>
      </div>
    </div>
</template>

<script>
import Content1 from './content.vue'
import commentarea from './commentarea.vue'
export default {
  name: 'comment',
  components: {
    Content1,
    commentarea
  },
  props: ['id', 'useid'],
  data () {
    return {
      type: 0,
      comments: [],
      replyid: ''
    }
  },
  created () {
    var url = 'http://127.0.0.1:8081/vrss/Comment/filecomment'
    var params = new URLSearchParams()
    params.append('id', this.id)
    this.$http.post(url, params).then((response) => {
      var data = response.data
      var replyid = 0
      for (var i = 0; i < data.length; i++) {
        if (data.reply != null) {
          replyid = data[i].reply.user_id
        } else {
          replyid = 0
        }
        this.comments.push({
          id: data[i].id,
          comment: data[i].comment,
          create_time: data[i].create_time,
          user_id: data[i].user.user_id,
          reply_id: replyid
        })
      }
    }).catch((error) => {
      console.log(error)
    })
  },
  methods: {
    addComment (data) {
      var params = new URLSearchParams()
      params.append('comment', data)
      params.append('user', 1)
      if (this.type === 0) {
        params.append('reply_id', 0)
      } else {
        params.append('reply_id', this.replyid)
      }
      params.append('file_id', this.id)
      params.append('filelist_id', 0)
      params.append('score', 1)
      var url = 'http://127.0.0.1:8082/vrss/Comment/add'
      this.$http.post(url, params).then((response) => {
        console.log(response.data)
        this.comments.push({
          id: response.data.id,
          comment: response.data.comment,
          create_time: response.data.create_time,
          user_id: response.data.user.user_id,
          reply_id: response.data.reply == null ? 0 : response.data.reply.user_id
        })
      }).catch((error) => {
        console.log(error)
      })
    },
    changeCommenter (replyId, userId) {
      this.replyid = userId
      this.type = 1
    },
    canelCommit () {
      this.type = 0
    }
  }
}
</script>
<style scoped>
  @import '../../../assets/css/bootstrap.css'
  .main {background-color:#f8f8f8; padding-top:20px;}
  .rightBox {background:#fff; padding-bottom:2em; padding-top:5px;}
  .commentBox {margin:20px;}
  .commentBox h3 {color: #634322; background: #e9e5df; padding: 8px 15px; text-align: left; font-size: 16px;}
  .noComment {display:none;}
  .comment {margin-bottom:15px;}
  .comment + .comment, .reply + .reply{border-top:1px solid #ccc;}
  .comment b{color:#01553D; font-size:16px; display:block; margin:5px 0;}
  .comment b span {color:#333; font-size:14px; margin-left:20px;}
  .comment p {font-size:16px; color:#333;}
  .commentBox textarea {overflow: auto; width: 100%; height: 95px; outline: none; resize: none;}
  .commentBox button {float:right; margin-left:20px; padding:5px 30px; background:#01553D; border-radius:5px; color:#fff; font-size:16px;}
  .commentBox button:hover {color:#fff; background:#047857}
  .reply {margin:15px 0 15px 50px;}
  .btn-search {border:0; position:absolute; width:19px; height:19px; top:21%; right:5%; padding:0; outline:none; box-shadow:none;}
  .btn-search:hover {background-color: #fff;}
  .form-control:focus {box-shadow:#01553D !important; border-color:#01553D !important;}
  .btn-envelope {background:#01553D; display:block; margin:0.5em auto 0; color:#fff;}
  .btn-envelope:hover {background:#047857; color:#fff;}
  .btn-envelope span {margin-right:0.3em;}
</style>
