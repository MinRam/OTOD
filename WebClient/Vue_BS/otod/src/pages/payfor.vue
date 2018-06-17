<template>
  <div id="pay">
    <br><br><br>
        <!--搜索栏-->
          <div class="search">
            <el-row>
              <el-col :span=8 :push="16">
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
              </el-col><!-- /.col-lg-6 -->
            </el-row><!-- /.row -->
          </div>

          <div id="position">
            <el-row>
              <el-col :span=10 :push="4">
                <p>您当前的位置是：二手市场</p>
              </el-col>
            </el-row>
          </div>

          <el-form :model="OrderForm" ref="OrderForm">
            <el-col :span=10 :push="4">
              <el-card class="box-card">
                <div class="text item">
                  <p>  <img :src="component_img.item" alt="" width="15px" height="15px">
                    订单信息</p>
                </div>
                <el-card class="middle-card">
                  <el-card class="small-item">
                    <p>商品编号：<strong>{{product.product_encoding}}</strong></p>
                  </el-card>
                  <el-card class="small-item">
                    <p>商品名称：<strong>{{product.product_name}}</strong></p>
                  </el-card>
                  <el-card class="small-item">
                    <p>商品单价：<strong>{{product.product_price}}元</strong></p>
                  </el-card>

                  <el-form-item  prop="product_num">
                    <el-card class="small-item">
                      <p>购买数量：
                        <el-select v-model="OrderForm.product_num" placeholder="请选择">
                          <el-option v-for="o in product.product_stock" :key="o" :value="o">
                          </el-option>
                        </el-select>
                        (库存{{product.product_stock}}件)</p>
                    </el-card>
                  </el-form-item>

                  <el-card class="small-item">
                    <p>配送费用：<strong>10元</strong></p>
                  </el-card>
                  <el-card class="small-item">
                    <p>总价：<strong>{{OrderForm.product_num*product.product_price+10}}</strong></p>
                  </el-card>
                </el-card>

                <div class="text item">
                  <p>  <img :src="component_img.item" alt="" width="15px" height="15px">
                    配送信息</p>
                </div>
                <el-card class="middle-card">

                  <el-form-item prop="address">
                    <el-card class="small-item">
                      <p>收货地址：<el-input v-model="OrderForm.address"></el-input></p>
                    </el-card>
                  </el-form-item>
                  <el-form-item prop="receriver">
                    <el-card class="small-item">
                      <p>收货人：<el-input v-model="OrderForm.receiver"></el-input></p>
                    </el-card>
                  </el-form-item>
                  <el-form-item prop="phone">
                    <el-card class="small-item">
                      <p>联系电话：<el-input v-model="OrderForm.phone"></el-input></p>
                    </el-card>
                  </el-form-item>

                </el-card>
                <p class="center"><el-checkbox>您阅读并同意《二手交易市场协议》</el-checkbox></p>
                <p class="center"><el-button type="primary" @click="SubmitOrder('OrderForm')">前往支付</el-button></p>
              </el-card>
            </el-col>
          </el-form>
  </div>
</template>

<script>
export default {
  data () {
    return {
      product_key: '',
      product: [],
      component_img: {
        item: 'http://localhost:8081/component/item.ico'
      },
      OrderForm: {
        product_num: 0,
        address: '',
        receiver: '',
        phone: ''
      }
    }
  },
  mounted () {
    this.getproduct()
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
        t.product_list = response.data.products
        t.total_product_num = response.data.totalElements
        t.total_page_num = response.data.totalPages
      })
    },
    changepage (path) {
      this.$router.push(path)
    },
    getproduct () {
      var t = this
      this.$axios({
        method: 'get',
        url: 'http://localhost:8081/market/product',
        dataType: 'json',
        params: {
          product_id: t.$route.query.product_id
        }
      }).then(function (response) {
        t.product = response.data
        console.log(t.product.product_stock)
      })
    },
    SubmitOrder (formName) {
      var t = this
      this.$axios({
        method: 'post',
        url: 'http://localhost:8081/market/addorder',
        dataType: 'json',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + t.$getCookie('otod_access_token')
        },
        data: {
          product_id: t.product.product_id,
          product_num: t.OrderForm.product_num,
          address: t.OrderForm.address,
          receiver: t.OrderForm.receiver,
          phone: t.OrderForm.phone
        }
      })
    }
  }
}
</script>

<style lang="css">
.center{
  text-align: center;
}
.box-card {
  width: 1200px;
  padding: 20px 0 0 0
}
.middle-card {
  margin: 20px 0 20px 70px ;
  width: 1000px
}
.small-item {
  margin: 20px 0 0 225px ;
  width: 500px
}
.el-card__body {
    padding: 0px 20px 10px 20px;
}
</style>
