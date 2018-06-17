<template>
  <div id="file" class="container-fluid">
    <Header1></Header1>
    <router-view></router-view>
  </div>
</template>

<script>
import Header1 from '../components/file/Header1.vue'
export default {
  name: 'File',
  components: {
    Header1
  },
  data () {
    return {
    }
  }
}
</script>

<style>
  @import '../assets/css/bootstrap.css';
</style>
