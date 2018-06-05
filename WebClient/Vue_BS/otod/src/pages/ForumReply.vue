<template>
<div class="container"  id="app">
    <div class="jumbotron">
        <h1>博客论坛系统</h1>
     </div>
    <div class="row">
        <table class="table table-bordered table-striped">
            <tbody>
                <tr v-for="(x,index) in list" style="height:100px" :key="index">
                    <td class="col-md-2">
                        <span>黑色的野马</span>
                        <div class="clear-both"></div>
                        <span><a @click="">id:874445</a></span>
                     </td>
                    <td class="col-md-10">
                        <div class="row">
                            <div class="pull-right col-md-2">
                                <span>2017-8-31 18:00</span>
                             </div>
                         </div>
                        <div class="clear-both"></div>
                        <div>
                            <span>&nbsp;之前RNG有个上单tianci(转RYL打LDL去了)，不然可以上单上他。姿态中单，Y4辅助擦汗总之乐观就对了。之前RNG有个上单tianci(转RYL打LDL去了)，不然可以上单上他。姿态中单，Y4辅助擦汗总之乐观就对了。之前RNG有个上单tianci(转RYL打LDL去了)，不然可以上单上他。姿态中单，Y4辅助擦汗总之乐观就对了。 </span>
                         </div>
                     </td>
                 </tr>
             </tbody>
         </table>
     </div>
    <div class="row">
        <ul class="pagination">
            <li><a href="#">&laquo;</a></li>
            <li><a href="#">1</a></li>
            <li><a href="#">2</a></li>
            <li><a href="#">3</a></li>
            <li><a href="#">4</a></li>
            <li><a href="#">5</a></li>
            <li><a href="#">&raquo;</a></li>
        </ul>
     </div>
    <div class="row">
        <form class="bs-example bs-example-form" role="form">
            <div class="form-group">
                <textarea class="form-control" rows="10" name=textarea></textarea>
             </div>
            <button class="btn btn-success">回复</button>
         </form>
     </div>

     <button class="btn btn-success">{{ id }}</button>
</div>

</template>

<script>
export default {
  name: 'Login',
  data () {
    return {
      id: this.$route.query.id,
      list: [
        {id: 1},
        {id: 2},
        {id: 3},
        {id: 4},
        {id: 5},
        {id: 6},
        {id: 7},
        {id: 8}
      ]
    }
  },
  methods: {

  }
}
</script>

<style>
    @import '../assets/bootstrap/css/bootstrap.min.css';
</style>
