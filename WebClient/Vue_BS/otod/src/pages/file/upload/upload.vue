<template>
  <div id="upload">
    <fileinfo></fileinfo>
  </div>
</template>

<script>
import fileinfo from '../../../components/file/upload/fileinfo.vue'
export default {
  name: 'upload',
  components: {
    fileinfo
  },
  data () {
    return {
    }
  }
}
</script>
<style scoped>
  @import '../../../assets/css/bootstrap.css'
</style>
