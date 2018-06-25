<template>
  <div id="type">
    <nav class="navbar navbar-toggleable-md navbar-light bg-faded">
      <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav div-flow">
          <b>资源类型：</b>
          <a v-for="tag in tags" v-bind:key=tag.name class="nav-item nav-link" href="javascript: void ( 0 );" @click="taglist(tag.id)">
          {{tag.content}}
          </a>
        </div>
      </div>
    </nav>
  </div>
</template>

<script>
export default {
  name: 'type',
  data () {
    return {
      tags: []
    }
  },
  created () {
    var url = 'http://127.0.0.1:8081/vrss/Tag/listtag'
    var params = new URLSearchParams()
    this.$http.post(url, params).then((response) => {
      var data = response.data
      console.log(data)
      this.tags = data
      this.tags.unshift({id: 0, content: '全部'})
    }).catch((error) => {
      console.log(error)
    })
  },
  methods: {
    taglist (id) {
      this.$emit('getlist', id)
    }
  }
}
</script>

<style scoped>
  @import '../../../assets/css/bootstrap.css'
  .div-flow{
    margin-bottom: 1rem;
  }
</style>
