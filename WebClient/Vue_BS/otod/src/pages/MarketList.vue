<template>
  <div>
        <section>
          <div class="container">

<br/><br/>
          <!--搜索栏-->
            <div class="search">
              <div class="row">
                <div class="col-lg-6 pull-right">
                  <div class="search_form">
                    <el-form :inline="true"  class="demo-form-inline">
                      <el-form-item label="商品查询">
                        <el-input v-model="product_key" placeholder="请输入商品关键字"></el-input>
                      </el-form-item>
                      <el-form-item>
                        <el-button type="primary" @click="page_num=1,search()">查询</el-button>
                      </el-form-item>
                    </el-form>
                  </div>
                </div><!-- /.col-lg-6 -->
              </div><!-- /.row -->
            </div>

<br/>
            <div id="position">
              <p>您当前的位置是：二手市场</p>
            </div>
            <div class="row">
              <div class="col-md-8">
                <ul class="nav nav-tabs">
                  <li role="presentation" class="disabled"><a href="#">商品信息</a></li>
                </ul>
              </div>
              <div class="col-md-4">
                <ul class="nav nav-pills nav-justified">
                  <li><a href="market/sell">我要出售</a></li>
                </ul>
              </div>
            </div>

            <!--商品表-->
            <div id="goods_table">
              <el-table :data="product" style="width: 100%">
                <el-table-column prop="product_encoding" label="商品编码" width="240">
                </el-table-column>
                <el-table-column label="商品名称" width="500">
                  <template slot-scope="scope">
                    <a href="#"><img :src="scope.row.product_img_url" width="46px" height="46px" alt="">{{scope.row.product_name}}</a>
                  </template>
                </el-table-column>
                <el-table-column prop="product_price" label="商品价格" width="180">
                </el-table-column>
                <el-table-column prop="product_stock" label="商品库存" width="180">
                </el-table-column>
              </el-table>
            </div>

            <!--分页-->

            <div id="page" class="text-center">
              <el-pagination
                :page-size="1"
                :pager-count="11"
                layout="prev, pager, next"
                :total="total_page_num"
                :current-page.sync="page_num"
                @current-change="search()"
                >
              </el-pagination>
            </div>
          </div>
        </section>
  </div>
</template>
<script>
export default {
  data () {
    return {
      page_num: 1,
      product: [],
      product_key: '',
      total_page_num: 0,
      total_product_num: ''
    }
  },
  mounted () {
    this.search()
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
        console.log(JSON.parse(JSON.stringify(response.data))['content'])
        t.product = response.data.content
        t.total_product_num = response.data.totalElements
        t.total_page_num = response.data.totalPages
      })
    }

    
  }
}
</script>
<style>
  @import '../assets/bootstrap/css/bootstrap.min.css'
</style>
