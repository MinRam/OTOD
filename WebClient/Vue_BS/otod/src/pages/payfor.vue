<template>
  <div>
    <br><br><br>
    <el-container>
      <el-col :span="8" :push="14">
      <!--搜索栏-->
        <div class="search">
          <div class="row">
            <div class="col-lg-6 pull-right">
              <div class="search_form">
                <el-form :inline="true"  class="demo-form-inline">
                  <el-form-item label="商品查询">
                    <el-input v-model="product_key" placeholder="请输入商品关键字" clearable></el-input>
                  </el-form-item>
                  <el-form-item>
                    <el-button type="primary" @click="changepage('/market?product_key='+product_key),search()">查询</el-button>
                  </el-form-item>
                </el-form>
              </div>
            </div><!-- /.col-lg-6 -->
          </div><!-- /.row -->
        </div>
      </el-col>


    </el-container>
  </div>
</template>

<script>
export default {
  data () {
    return {
      product_key: ''
    }
  },
  methods: {
    search () {
      var t = this
      this.$axios({
        method: 'get',
        url: 'http://localhost:8081/market/search',
        dataType: 'json',
        params: {
          product_key: t.product_key,
          page_num: t.page_num
        }
      }).then(function (response) {
        t.product_list = response.data.content
        t.total_product_num = response.data.totalElements
        t.total_page_num = response.data.totalPages
      })
    },
    changepage (path) {
      this.$router.push(path)
    }
  }
}
</script>

<style lang="css">
</style>
