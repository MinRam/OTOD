<template>
  <div>
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
                    <el-button type="primary" @click="changepage('/market?product_key='+product_key),search()">查询</el-button>
                  </el-form-item>
                </el-form>
              </div>
            </el-col><!-- /.col-lg-6 -->
          </el-row><!-- /.row -->
        </div>

        <div id="position">
          <el-row>
            <el-col :span=10 :push="6">
              <p>您当前的位置是：<el-button type="text" @click="changepage('/market')">二手市场</el-button> -> 商品信息</p>
            </el-col>
          </el-row>
        </div>


        <!--商品信息-->
        <div id="product">
          <el-row>
            <el-col :span=10 :push="9"><div class="grid-content text-center"><h3  style="font-family: 'Microsoft YaHei';">{{product.product_name}}</h3></div></el-col>
          </el-row>
          <el-row>
            <el-col :push="4" span=4>
              <!--商品图片-->
              <div id="product_img ">
              <!--当前展示图片-->
                <div id="img-top">
                  <a @click="handlePictureCardPreview(show_picture)">
                    <img class="thumbnail" :src="show_picture" alt="" width="420px" height="400px"/>
                  </a>
                </div>
                <el-dialog :visible.sync="dialogVisible">
                  <img width="100%" :src="dialogImageUrl" alt="">
                </el-dialog>
                <!--待选图片-->
                <hr>

                <div id="img-box">
                  <template v-for="item in img_url">
                    <el-button :key="item" @click="toshow(item.img_url)" circle>
                      <img class="thumbnail" :src="item.img_url" alt="" width="45px" height="45px"/>
                    </el-button>
                  </template>
                </div>

              </div>
            </el-col>

            <!--商品参数-->

            <div id="product_attribute">
              <el-col :push="7" span=5>
                <div class="top">
                  <ul class="list-group">
                    <li class="list-group-item"><span >价格： <strong> {{product.product_price}} 元</strong></span></li>
                    <li class="list-group-item"><span >运费： <strong> 10.00 元</strong></span></li>
                    <li class="list-group-item"><span >商品编号： <strong style="color: blue">{{product.product_encoding}}</strong></span></li>
                  </ul>
                  <hr>
                </div>
                <div class="middle">
                  <ul class="list-group">
                    <li class="list-group-item"><span>商品类型：<strong>其他</strong> </span><span class="pull-right">商品库存:<strong>{{product.product_stock}}件</strong></span></li>
                    <li class="list-group-item"><span>最佳交易时间：<strong>{{product.product_day_from}}:00 ~ {{product.product_day_to}}:00</strong> </span><span class="pull-right">剩余时间:<strong>{{product.product_life}}天</strong></span></li>
                  </ul>
                </div>
                <hr>
                <br>
                <br><br>
                <br>
                <br>
                <div class="buttom">
                  <!--安全保障-->
                  <dl>
                    <dt style="float:left;">
                      诚信保障：
                    </dt>
                    <dd>
                      <a href="" ><img :src="component_img.security" alt="" width="20px" height="20px"></a>
                    </dd>
                    <dd>
                      <span><img :src="component_img.security" alt="" width="20px" height="20px"></span>
                      <span>担保交易，卖家自主发货</span>
                    </dd>
                  </dl>
                  <hr>
<br>
<br>
<br>
<br>

                  <!--购买商品-->
                  <div id="add_cart" class="pull-right">
                    <el-button @click="payforit()">
                      <span><img :src="component_img.add_cart" alt="" width="40px" height="40px"></span>
                      <span>购买商品</span>
                    </el-button>
                    <el-dialog
                      title="提示"
                      :visible.sync="payfor_wrong"
                      width="30%"
                      :before-close="handleClose">
                      <span>无法购买（商品正在交易或者商品已经下架）</span>
                      <span slot="footer" class="dialog-footer">
                        <el-button type="primary" @click="payfor_wrong = false">确 定</el-button>
                      </span>
                    </el-dialog>
                  </div>
                </div>
              </el-col>
            </div>

            <!--卖家信息-->
            <!--  <div id="seller_message">
                <el-col :span=3 :push="8">
                  <ul class="list-group">
                    <li class="list-group-item">
                      <span ><img src="img/user_id.jpg" alt="卖家信息" width="60px" height="60px"></span>
                      <span  style="display:inline-block;float:right;"><h4><strong>卖家信息</strong></h4></span>
                    </li>
                    <li class="list-group-item">
                      <p><span >作为卖家:</span><span class="pull-right">成交数: <strong>36 </strong></span></p>
                      <p><span class="pull-right">好评数: <strong>35</strong></span></p>
                      <div style="clear:both"></div>
                    </li>
                    <li class="list-group-item">
                      <p><span >作为买家:</span><span class="pull-right">成交数: <strong>17 </strong></span></p>
                      <p><span class="pull-right">好评数: <strong>17</strong></span></p>
                      <div style="clear:both"></div>
                    </li>
                  </ul>
                </el-col>
              </div>-->
            </el-row>
          </div>

        <div id="detail">
          <el-row>
            <el-col :push="4" :span=16>
              <el-tabs @tab-click="handleClick">
                <el-tab-pane label="商品详情">
                  <div id="product_description" >
                    <el-col :push="1" span=22>
                      <!--商品详情 卖家填写的详细内容 先由纯文字构成-->
                      <p>{{product.product_description}}</p>
                    </el-col>
                  </div>
                </el-tab-pane>

                <el-tab-pane label="购买流程">
                  <div id="liucheng" >
                    <el-col :push="1" span=22>
                      <img :src="component_img.liucheng" alt="">
                    </el-col>
                  </div>
                </el-tab-pane>
              </el-tabs>
            </el-col>
          </el-row>
        </div>
      </div>
  </div>
</template>

<script>
export default {
  mounted () {
    this.findproduct()
  },
  data () {
    return {
      payfor_wrong: false,
      dialogImageUrl: '',
      dialogVisible: false,
      loading: true,
      product: [],
      show_picture: '',
      page_num: 1,
      product_list: [],
      product_key: '',
      total_page_num: 0,
      total_product_num: '',
      component_img:
      {
        security: 'http://localhost:8081/component/security.ico',
        add_cart: 'http://localhost:8081/component/add_cart.ico',
        liucheng: 'http://localhost:8081/component/liucheng.png'
      },
      img_url: {}
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
        t.product_list = response.data.products
        t.total_product_num = response.data.totalElements
        t.total_page_num = response.data.totalPages
        t.loading = false
      })
    },
    changepage (path) {
      this.$router.push(path)
    },
    findproduct () {
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
        t.show_picture = t.product.product_img_url
        t.img_url = response.data.product_imgs
        console.log(t.product)
      })
    },
    payforit () {
      var t = this
      if (t.product.product_status !== 1) {
        t.payfor_wrong = true
      } else {
        t.changepage('/market/product/pay?product_id=' + t.product.product_id)
      }
    },
    handleClick (tab, event) {},
    toshow (src) {
      this.show_picture = src
    },
    handlePictureCardPreview (src) {
      this.dialogImageUrl = src
      this.dialogVisible = true
    }
  }
}
</script>

<style>
</style>
