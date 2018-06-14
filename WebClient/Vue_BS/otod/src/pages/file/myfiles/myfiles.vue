<template>
  <div id="myfiles">
    <mynavbar></mynavbar>
    <myinfo></myinfo>
    <router-view></router-view>
  </div>
</template>

<script>
import myinfo from '../../../components/file/myfiles/myinfo.vue'
import mynavbar from '../../../components/file/myfiles/mynavbar.vue'

export default {
  name: 'myfiles',
  components: {
    myinfo,
    mynavbar
  },
  data () {
    return {}
  },
  created () {
  }
}
</script>

<style scoped>
  @import '../../../assets/bootstrap/css/bootstrap.css'
</style>
