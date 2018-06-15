<template>
  <div id="download">
    <type @getlist="getlist"></type>
    <downloadlist v-bind:str="str" :tag_id="tag_id"></downloadlist>
  </div>
</template>

<script>
import type from '../../../components/file/download/type.vue'
import downloadlist from '../../../components/file/download/downloadlist.vue'

export default {
  name: 'download',
  components: {
    type,
    downloadlist
  },
  data () {
    return {
      str: '',
      tag_id: 0
    }
  },
  created () {
    this.str = ''
    let str = this.$route.query.search
    if (str != null) {
      this.str = str
    }
    console.log(this.str)
  },
  watch: {
    '$route' () {
      if (this.$route.query.search != null) {
        this.str = this.$route.query.search
      } else {
        this.str = ''
      }
    }
  },
  methods: {
    getlist (id) {
      this.tag_id = id
    }
  }
}
</script>

<style scoped></style>
