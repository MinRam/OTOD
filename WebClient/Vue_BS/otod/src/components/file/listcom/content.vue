<template>
  <div class="commentBox" id="Content1">
    <h3>评论</h3>
    <p v-if="comments.length==0">暂无评论，我来发表第一篇评论！</p>
    <div v-else>
      <div class="comment" v-for="item in comments" v-bind:key="item.id" >
        <div v-if="item.reply_id!=0">
          <b>from&nbsp;{{item.user_id}}&nbsp;to&nbsp;{{item.reply_id}}
            <span>
              {{item.create_time}}
            </span>
          </b>
        </div>
        <div v-else>
          <b>from&nbsp;{{item.user_id}}
            <span>
              {{item.create_time}}
            </span>
          </b>
        </div>
        <p @click="changeCommenter(item.reply_id, item.user_id)">
        {{item.comment}}
        </p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Content1',
  props: ['comments'],
  data () {
    return {
    }
  },
  created () {
  },
  methods: {
    changeCommenter (replyId, userId) {
      this.$emit('change', replyId, userId)
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
