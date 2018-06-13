<template>
  <div id="home">
    <Header1></Header1>
    <router-view></router-view>
  </div>
</template>

<script>
import Header1 from '../components/file/Header1.vue'
export default {
  name: 'Home',
  components: {
    Header1
  },
  data () {
    return {
    }
  }
}
</script>

<style scoped>
</style>
