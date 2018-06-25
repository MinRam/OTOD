<template>
  <div class="commentBox" id="commentarea">
    <h3>发表评论</h3>
    <b v-if="type">你回复&nbsp;{{replyid}}</b>
    <textarea name="" value="请填写评论内容" v-model="commentText">
    </textarea>
    <button class="btn" @click="addComment">发表</button>
    <button class="btn" @click="canelComment">取消</button>
  </div>
</template>
<script>
export default {
  name: 'commentarea',
  props: ['type', 'replyid'],
  data () {
    return {commentText: ''}
  },
  methods: {
    addComment () {
      this.$emit('submit', this.commentText)
      this.commentText = ''
    },
    canelComment () {
      this.$emit('canel')
      this.commentText = ''
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
