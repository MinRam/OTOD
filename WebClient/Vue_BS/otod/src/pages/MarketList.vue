<template>
  <div>
        <section>
          <div class="container">

<br/><br/>
<br/>
          <!--搜索栏-->
            <div class="search">
              <el-row>
                <el-col :span=8 :push="14">
                  <div class="search_form">
                    <el-form :inline="true"  class="demo-form-inline">
                      <el-form-item label="商品查询">
                        <el-input v-model="product_key" placeholder="请输入商品关键字" clearable></el-input>
                      </el-form-item>
                      <el-form-item>
                        <el-button type="primary" @click="search()">查询</el-button>
                      </el-form-item>
                    </el-form>
                  </div>
                </el-col><!-- /.col-lg-6 -->
              </el-row><!-- /.row -->
            </div>

            <div id="position">
              <el-row>
                <el-col :span=10 :push="6">
                  <p>您当前的位置是：二手市场</p>
                </el-col>
              </el-row>
            </div>

            <el-row>
              <el-col :span=11 :push="4">
                <el-tabs>
                  <el-tab-pane label="商品列表"></el-tab-pane>
                </el-tabs>
              </el-col>

              <el-col :span=2 :offset="6">
                <el-tabs>
                  <el-button type="primary" @click="changepage('/market/sell')" round>我要出售</el-button>
                </el-tabs>
              </el-col>
            </el-row>
<br><br>
            <!--商品表-->
            <div id="goods_table">
              <el-row>
                <el-col :push="4" span=14>
                  <el-table :data="product_list" style="width: 100%" v-loading="loading">
                    <el-table-column prop="product_encoding" label="商品编码" width="260">
                    </el-table-column>
                    <el-table-column label="商品名称" width="520">
                      <template slot-scope="scope">
                        <el-button type="text" @click="changepage('/market/product?product_id='+scope.row.product_id)"><img :src="scope.row.product_img_url" width="46px" height="46px" alt="">{{scope.row.product_name}}</el-button>
                      </template>
                    </el-table-column>
                    <el-table-column prop="product_price" label="商品价格" width="160">
                    </el-table-column>
                    <el-table-column prop="product_stock" label="商品库存" width="160">
                    </el-table-column>
                  </el-table>
                </el-col>
              </el-row>
            </div>

<br>
            <!--分页-->
            <div id="page" class="text-center">
              <el-row>
                <el-col :span=6 :push="9">
                  <el-pagination
                    :page-size="1"
                    :pager-count="11"
                    layout="prev, pager, next"
                    :total="total_page_num"
                    :current-page.sync="page_num"
                    @current-change="search()"
                    >
                  </el-pagination>
                </el-col>
              </el-row>
            </div>

          </div>
        </section>
  </div>
</template>
<script>
export default {
  mounted () {
    this.search()
  },
  data () {
    return {
      loading: true,
      page_num: 1,
      product_list: [],
      product_key: this.$route.query.product_key,
      total_page_num: 0,
      total_product_num: ''
    }
  },
  methods: {
    search () {
      var t = this
      console.log(t)
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
        t.loading = false
      })
    },
    changepage (path) {
      this.$router.push(path)
    }
  }
}
</script>
<style>
</style>
