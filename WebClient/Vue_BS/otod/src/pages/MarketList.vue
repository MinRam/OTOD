<template>
  <div>
    <el-container>
      <el-main>
        <!--搜索栏-->
          <div class="search">
            <el-row>
              <el-col :span=10 :offset="14">
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
              <el-col :span=8 :push="4">
                <p>您当前的位置是：二手市场</p>
              </el-col>
            </el-row>
          </div>
<br><br>
          <el-row>
            <el-col :span=13 :offset="4">
              <el-tabs @tab-click="changetab">
                <el-tab-pane label="商品列表">
                  <!--商品表-->
                  <div id="goods_table">
                    <el-row>
                      <el-col>
                        <el-table :data="show_list"  v-loading="loading">
                          <el-table-column label="商品编码">
                            <template slot-scope="scope" >
                              <span>{{scope.row.product_encoding}}</span>
                            </template>
                          </el-table-column>
                          <el-table-column label="商品名称">
                            <template slot-scope="scope" >
                              <el-button type="text" @click="changepage('/market/product?product_id='+scope.row.product_id)"><img :src="scope.row.product_img_url" width="46px" height="46px" alt="">{{scope.row.product_name}}</el-button>
                            </template>
                          </el-table-column>
                          <el-table-column label="商品价格">
                            <template slot-scope="scope" >
                              <span>{{scope.row.product_price}}</span>
                            </template>
                          </el-table-column>
                          <el-table-column label="商品库存">
                            <template slot-scope="scope" >
                              <span>{{scope.row.product_stock}}</span>
                            </template>
                          </el-table-column>
                        </el-table>
                      </el-col>
                    </el-row>
                  </div>

      <br>
                  <!--分页-->
                  <div>
                    <el-row>
                      <el-col :push="10">
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
                </el-tab-pane>

                <el-tab-pane label="我的商品" v-if="islogin">
                  <el-table :data="update_form.sell_list" style="width: 100%" v-loading="my_loading">
                    <el-table-column type="expand">
                      <template slot-scope="scope">
                        <el-form  label-position="left"  class="demo-table-expand" :model="update_form" ref="update_form">
                          <el-row>
                            <el-col :span=11>
                              <el-form-item label="商品名称">
                                <el-input v-model="scope.row.product_name"></el-input>
                              </el-form-item>
                            </el-col>
                          </el-row>
                          <el-row>
                            <el-col :span=11>
                              <el-form-item label="商品价格">
                                <el-input v-model="scope.row.product_price"></el-input>
                              </el-form-item>
                            </el-col>
                            <el-col :span=11>
                              <el-form-item label="商品库存">
                                <el-input v-model="scope.row.product_stock"></el-input>
                              </el-form-item>
                            </el-col>
                          </el-row>
                          <el-row>
                            <el-form-item label="商品详情">
                              <el-input type="textarea" v-model="scope.row.product_description"></el-input>
                            </el-form-item>
                          </el-row>
                          <el-row>
                            <el-col :span=12>
                              <el-form-item label="商品更新时间">
                                <span>{{formatDate(scope.row.product_createtime)}}</span>
                              </el-form-item>
                            </el-col>
                            <el-col :span=12>
                              <el-form-item label="商品有效期">
                                <el-select v-model="scope.row.product_life">
                                  <el-option label="1天" value=1></el-option>
                                  <el-option label="2天" value=2></el-option>
                                  <el-option label="3天" value=3></el-option>
                                  <el-option label="4天" value=4></el-option>
                                  <el-option label="5天" value=5></el-option>
                                  <el-option label="6天" value=6></el-option>
                                  <el-option label="7天" value=7></el-option>
                                  <el-option label="8天" value=8></el-option>
                                  <el-option label="9天" value=9></el-option>
                                  <el-option label="10天" value=10></el-option>
                                  <el-option label="11天" value=11></el-option>
                                  <el-option label="12天" value=12></el-option>
                                  <el-option label="13天" value=13></el-option>
                                  <el-option label="14天" value=14></el-option>
                                  <el-option label="15天" value=15></el-option>
                                </el-select>
                              </el-form-item>
                            </el-col>
                          </el-row>
                          <el-row>
                            <el-col :span=8>
                              <el-form-item label="方便交易时间">
                                <el-select v-model="scope.row.product_day_from">
                                  <el-option label="00:00" value=0></el-option>
                                  <el-option label="01:00" value=1></el-option>
                                  <el-option label="02:00" value=2></el-option>
                                  <el-option label="03:00" value=3></el-option>
                                  <el-option label="04:00" value=4></el-option>
                                  <el-option label="05:00" value=5></el-option>
                                  <el-option label="06:00" value=6></el-option>
                                  <el-option label="07:00" value=7></el-option>
                                  <el-option label="08:00" value=8></el-option>
                                  <el-option label="09:00" value=9></el-option>
                                  <el-option label="10:00" value=10></el-option>
                                  <el-option label="11:00" value=11></el-option>
                                  <el-option label="12:00" value=12></el-option>
                                  <el-option label="13:00" value=13></el-option>
                                  <el-option label="14:00" value=14></el-option>
                                  <el-option label="15:00" value=15></el-option>
                                  <el-option label="16:00" value=16></el-option>
                                  <el-option label="17:00" value=17></el-option>
                                  <el-option label="18:00" value=18></el-option>
                                  <el-option label="19:00" value=19></el-option>
                                  <el-option label="20:00" value=20></el-option>
                                  <el-option label="21:00" value=21></el-option>
                                  <el-option label="22:00" value=22></el-option>
                                  <el-option label="23:00" value=23></el-option>
                                  <el-option label="24:00" value=24></el-option>
                                </el-select>
                              </el-form-item>
                            </el-col>
                            <el-col :span=6>
                              <el-form-item label="到">
                                <el-select v-model="scope.row.product_day_to">
                                  <el-option label="00:00" value=0></el-option>
                                  <el-option label="01:00" value=1></el-option>
                                  <el-option label="02:00" value=2></el-option>
                                  <el-option label="03:00" value=3></el-option>
                                  <el-option label="04:00" value=4></el-option>
                                  <el-option label="05:00" value=5></el-option>
                                  <el-option label="06:00" value=6></el-option>
                                  <el-option label="07:00" value=7></el-option>
                                  <el-option label="08:00" value=8></el-option>
                                  <el-option label="09:00" value=9></el-option>
                                  <el-option label="10:00" value=10></el-option>
                                  <el-option label="11:00" value=11></el-option>
                                  <el-option label="12:00" value=12></el-option>
                                  <el-option label="13:00" value=13></el-option>
                                  <el-option label="14:00" value=14></el-option>
                                  <el-option label="15:00" value=15></el-option>
                                  <el-option label="16:00" value=16></el-option>
                                  <el-option label="17:00" value=17></el-option>
                                  <el-option label="18:00" value=18></el-option>
                                  <el-option label="19:00" value=19></el-option>
                                  <el-option label="20:00" value=20></el-option>
                                  <el-option label="21:00" value=21></el-option>
                                  <el-option label="22:00" value=22></el-option>
                                  <el-option label="23:00" value=23></el-option>
                                  <el-option label="24:00" value=24></el-option>
                                </el-select>
                              </el-form-item>
                            </el-col>
                            <el-col :span=9>
                              <el-form-item label="商品状态">
                                <el-select v-model="scope.row.product_status">
                                  <el-option label="上架" value=1></el-option>
                                  <el-option label="下架" value=2></el-option>
                                </el-select>
                              </el-form-item>
                            </el-col>
                          </el-row>
                          <el-row>
                            <el-form-item label="商品图片">
                              <el-col :span=5 v-for="(o,index) in scope.row.product_imgs" :key="index" :value="o">
                                <el-card :body-style="{ padding: '0px' }">
                                  <el-button @click="handlePictureCardPreview"><img :src="scope.row.product_imgs[index].img_url" alt="" width="150px" height="150px"></el-button>
                                  <el-dialog :visible.sync="dialogVisible">
                                    <img width="100%" :src="scope.row.product_imgs[index].img_url" alt="">
                                  </el-dialog>
                                </el-card>
                              </el-col>
                            </el-form-item>
                          </el-row>
                          <el-row>
                            <el-col :span=4 :push="10">
                              <el-button type="success" @click="UpdateProduct(scope.row.product_id)">确认保存</el-button>
                            </el-col>
                          </el-row>
                          <el-dialog
                            title="提示"
                            :visible.sync="update_ok"
                            width="30%">
                            <span style="text-align: center;">修改成功</span>
                            <span slot="footer" class="dialog-footer">
                              <el-button type="primary" @click="update_ok = false">确 定</el-button>
                            </span>
                          </el-dialog>
                        </el-form>
                      </template>
                    </el-table-column>
                    <el-table-column label="商品编码" prop="product_encoding"></el-table-column>
                    <el-table-column label="商品名称">
                      <template slot-scope="scope">
                        <el-button type="text" @click="changepage('/market/product?product_id='+scope.row.product_id)"><img :src="scope.row.product_img_url" width="46px" height="46px" alt="">{{scope.row.product_name}}</el-button>
                      </template>
                    </el-table-column>
                    <el-table-column label="商品价格" prop="product_price"></el-table-column>
                    <el-table-column label="操作">
                      <template slot-scope="scope">
                        <el-button type="info" icon="el-icon-delete" @click="DeleteProduct(scope.row.product_id)" circle></el-button>
                        <el-dialog
                          title="提示"
                          :visible.sync="delete_product_ok"
                          width="30%">
                          <span style="text-align: center;">删除成功</span>
                          <span slot="footer" class="dialog-footer">
                            <el-button type="primary" @click="delete_product_ok = false , changepage('/market?product_key='+product_key),search()">确 定</el-button>
                          </span>
                        </el-dialog>
                        <el-dialog
                          title="提示"
                          :visible.sync="delete_product_wrong"
                          width="30%">
                          <span style="text-align: center;">删除失败，该商品还有未完成订单</span>
                          <span slot="footer" class="dialog-footer">
                            <el-button type="primary" @click="delete_product_wrong = false">确 定</el-button>
                          </span>
                        </el-dialog>
                      </template>
                    </el-table-column>
                  </el-table>
                  <!--分页-->
                  <div>
                    <el-row>
                      <el-col :push="10">
                        <el-pagination
                          :page-size="1"
                          :pager-count="11"
                          layout="prev, pager, next"
                          :total="total_page_num"
                          :current-page.sync="page_num"
                          @current-change="myproduct()"
                          >
                        </el-pagination>
                      </el-col>
                    </el-row>
                  </div>
                </el-tab-pane>

                <el-tab-pane label="我买的" v-if="islogin">
                  <el-table :data="pay_list" style="width: 100%" v-loading="pay_loading">
                    <el-table-column type="expand">
                      <template slot-scope="scope">
                        <el-form  label-position="left"  class="demo-table-expand">
                          <el-row>
                            <el-col :span=12>
                              <el-form-item label="商品编码">
                                <span>{{scope.row.product_encoding}}</span>
                              </el-form-item>
                            </el-col>
                            <el-col :span=12>
                              <el-form-item label="商品名称">
                                <span>{{scope.row.product_name}}</span>
                              </el-form-item>
                            </el-col>
                          </el-row>
                          <el-row>
                            <el-col :span=12>
                              <el-form-item label="商品购买数量">
                                <span>{{scope.row.product_num}}</span>
                              </el-form-item>
                            </el-col>
                            <el-col :span=12>
                              <el-form-item label="商品单价">
                                <span>{{scope.row.product_price}}</span>
                              </el-form-item>
                            </el-col>
                          </el-row>
                          <el-row>
                            <el-col :span=12>
                              <el-form-item label="创建时间">
                                <span>{{formatDate(scope.row.createtime)}}</span>
                              </el-form-item>
                            </el-col>
                          </el-row>
                        </el-form>
                      </template>
                    </el-table-column>
                    <el-table-column  label="订单编码" prop="order_encoding"></el-table-column>
                    <el-table-column  label="商品信息">
                      <template slot-scope="scope">
                        <el-button type="text" @click="changepage('/market/product?product_id='+scope.row.product_id)"><img :src="scope.row.product_img_url" width="46px" height="46px" alt="">{{scope.row.product_name}}</el-button>
                      </template>
                    </el-table-column>
                    <el-table-column  label="订单状态">
                      <template slot-scope="scope">
                        <span v-if="scope.row.orderstatus === 1">未发货</span>
                        <span v-if="scope.row.orderstatus === 2">已发货</span>
                        <span v-if="scope.row.orderstatus === 3">已收货</span>
                        <span v-if="scope.row.orderstatus === 4">申请订单取消中</span>
                        <span v-if="scope.row.orderstatus === 5">订单取消</span>
                        <span v-if="scope.row.orderstatus === 6">申请退货</span>
                        <span v-if="scope.row.orderstatus === 7">退货中</span>
                        <span v-if="scope.row.orderstatus === 8">退货成功</span>
                      </template>
                    </el-table-column>
                    <el-table-column  label="操作">
                      <template slot-scope="scope" >
                        <el-button type="info" v-if="scope.row.orderstatus === 1" @click="buyerordermanager(scope.row.order_id,10)">申请取消订单</el-button>
                        <el-button type="success" v-if="scope.row.orderstatus === 2" @click="buyerordermanager(scope.row.order_id,9)">确认收货</el-button>
                        <el-button type="info" v-if="scope.row.orderstatus === 2" @click="buyerordermanager(scope.row.order_id,7)">申请退货</el-button>
                        <span v-if="scope.row.orderstatus === 3">交易完成</span>
                        <el-button type="info" v-if="scope.row.orderstatus === 4" @click="buyerordermanager(scope.row.order_id,12)">取消申请取消订单</el-button>
                        <span v-if="scope.row.orderstatus === 5">订单已取消</span>
                        <el-button type="info" v-if="scope.row.orderstatus === 6" @click="buyerordermanager(scope.row.order_id,13)">取消申请退货</el-button>
                        <span v-if="scope.row.orderstatus === 7">订单退货中</span>
                        <span v-if="scope.row.orderstatus === 8">退货成功</span>
                      </template>
                    </el-table-column>
                  </el-table>
                </el-tab-pane>
                <el-tab-pane label="我卖的" v-if="islogin">
                  <el-table :data="sell_orders" style="width: 100%" v-loading="sell_loading">
                    <el-table-column type="expand">
                      <template slot-scope="scope">
                        <el-form  label-position="left"  class="demo-table-expand">
                          <el-row>
                            <el-col :span=12>
                              <el-form-item label="商品编码">
                                <span>{{scope.row.product_encoding}}</span>
                              </el-form-item>
                            </el-col>
                            <el-col :span=12>
                              <el-form-item label="商品名称">
                                <span>{{scope.row.product_name}}</span>
                              </el-form-item>
                            </el-col>
                          </el-row>
                          <el-row>
                            <el-col :span=12>
                              <el-form-item label="商品购买数量">
                                <span>{{scope.row.product_num}}</span>
                              </el-form-item>
                            </el-col>
                            <el-col :span=12>
                              <el-form-item label="商品单价">
                                <span>{{scope.row.product_price}}</span>
                              </el-form-item>
                            </el-col>
                          </el-row>
                          <el-row>
                            <el-col :span=12>
                              <el-form-item label="创建时间">
                                <span>{{formatDate(scope.row.createtime)}}</span>
                              </el-form-item>
                            </el-col>
                          </el-row>
                        </el-form>
                      </template>
                    </el-table-column>
                    <el-table-column  label="订单编码" prop="order_encoding"></el-table-column>
                    <el-table-column  label="商品信息">
                      <template slot-scope="scope">
                        <el-button type="text" @click="changepage('/market/product?product_id='+scope.row.product_id)"><img :src="scope.row.product_img_url" width="46px" height="46px" alt="">{{scope.row.product_name}}</el-button>
                      </template>
                    </el-table-column>
                    <el-table-column  label="订单状态">
                      <template slot-scope="scope">
                        <span v-if="scope.row.orderstatus === 1">未发货</span>
                        <span v-if="scope.row.orderstatus === 2">已发货</span>
                        <span v-if="scope.row.orderstatus === 3">已收货</span>
                        <span v-if="scope.row.orderstatus === 4">申请订单取消中</span>
                        <span v-if="scope.row.orderstatus === 5">订单取消</span>
                        <span v-if="scope.row.orderstatus === 6">申请退货</span>
                        <span v-if="scope.row.orderstatus === 7">退货中</span>
                        <span v-if="scope.row.orderstatus === 8">退货成功</span>
                      </template>
                    </el-table-column>
                    <el-table-column  label="操作">
                      <template slot-scope="scope" >
                        <el-button type="success" v-if="scope.row.orderstatus === 1" @click="sellerordermanager(scope.row.order_id,5)">已完成发货</el-button>
                        <span v-if="scope.row.orderstatus === 2">待收货</span>
                        <span v-if="scope.row.orderstatus === 3">交易完成</span>
                        <el-button type="info" v-if="scope.row.orderstatus === 4" @click="sellerordermanager(scope.row.order_id,11)">同意取消订单</el-button>
                        <span v-if="scope.row.orderstatus === 5">订单已取消</span>
                        <el-button type="info" v-if="scope.row.orderstatus === 6" @click="sellerordermanager(scope.row.order_id,8)">同意退货</el-button>
                        <el-button type="info" v-if="scope.row.orderstatus === 7" @click="sellerordermanager(scope.row.order_id,9)">确认退货</el-button>
                        <span v-if="scope.row.orderstatus === 8">退货成功</span>
                      </template>
                    </el-table-column>
                  </el-table>
                </el-tab-pane>
              </el-tabs>
            </el-col>

            <el-col :span=4 :offset="1">
              <el-tabs>
                <el-button type="primary" v-if="muid !== 0" @click="changepage('/market/sell')" round>我要出售</el-button>
              </el-tabs>
            </el-col>
          </el-row>
      </el-main>
    </el-container>
  </div>
</template>
<script>
export default {
  mounted () {
    this.initial()
  },
  data () {
    return {
      update_ok: false,
      delete_product_ok: false,
      delete_product_wrong: false,
      dialogVisible: false,
      loading: true,
      my_loading: true,
      sell_loading: true,
      pay_loading: true,
      page_num: 1,
      product_list: [],
      product_key: this.$route.query.product_key,
      total_page_num: 0,
      total_product_num: '',
      islogin: false,
      muid: 0,
      show_list: [],
      update_form: {
        sell_list: []
      },
      pay_list: [],
      sell_orders: []
    }
  },
  methods: {
    search () {
      var t = this
      t.show_list = []
      t.loading = true
      this.$axios({
        method: 'get',
        url: 'http://localhost:8081/market/search',
        dataType: 'json',
        params: {
          product_key: t.product_key,
          page_num: t.page_num
        }
      }).then(function (response) {
        console.log(response)
        t.product_list = response.data.products
        t.show_list = t.product_list
        t.total_product_num = response.data.totalElements
        t.total_page_num = response.data.totalPages
        t.loading = false
      })
    },
    changepage (path) {
      this.$router.push(path)
    },
    initial () {
      this.search()
      var t = this
      if (this.$getCookie('otod_access_token')) {
        t.islogin = true
        t.$axios({
          method: 'post',
          url: 'http://localhost:8081/market/getmuser',
          dataType: 'json',
          headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + t.$getCookie('otod_access_token')
          }
        }).then(function (response) {
          t.muid = response.data
        })
      }
    },
    changetab (tab, event) {
      var t = this
      if (tab.label === '我的商品') {
        t.page_num = 1
        t.myproduct()
      } else if (tab.label === '我买的') {
        t.buyorder()
      } else if (tab.label === '我卖的') {
        t.sellorder()
      } else if (tab.label === '商品列表') {
        t.page_num = 1
        t.search()
      }
    },
    myproduct () {
      var t = this
      t.my_loading = true
      t.$axios({
        method: 'post',
        url: 'http://localhost:8081/market/getPbyMU',
        dataType: 'json',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + t.$getCookie('otod_access_token')
        },
        params: {
          page_num: t.page_num
        }
      }).then(function (response) {
        t.update_form.sell_list = response.data.content
        t.my_loading = false
        t.total_page_num = response.data.totalPages
        console.log(t.update_form.sell_list)
      })
    },
    buyorder () {
      var t = this
      t.pay_loading = true
      t.$axios({
        method: 'post',
        url: 'http://localhost:8081/market/getObyMU',
        dataType: 'json',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + t.$getCookie('otod_access_token')
        },
        params: {
          page_num: t.page_num
        }
      }).then(function (response) {
        t.pay_list = response.data
        t.pay_loading = false
        console.log(t.pay_list)
      })
    },
    sellorder () {
      var t = this
      t.sell_loading = true
      t.$axios({
        method: 'post',
        url: 'http://localhost:8081/market/getObySeller',
        dataType: 'json',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + t.$getCookie('otod_access_token')
        },
        params: {
          page_num: t.page_num
        }
      }).then(function (response) {
        console.log(response)
        t.sell_orders = response.data
        t.sell_loading = false
      })
    },
    handlePictureCardPreview () {
      this.dialogVisible = true
    },
    buyerordermanager (oid, operation) {
      var t = this
      this.$axios({
        method: 'post',
        url: 'http://localhost:8081/market/BuyerOrderManage',
        dataType: 'json',
        params: {
          order_id: oid,
          operation: operation
        }
      }).then(function (response) {
        t.buyorder()
      })
    },
    sellerordermanager (oid, operation) {
      var t = this
      this.$axios({
        method: 'post',
        url: 'http://localhost:8081/market/SellerOrderManage',
        dataType: 'json',
        params: {
          order_id: oid,
          operation: operation
        }
      }).then(function (response) {
        t.sellorder()
      })
    },
    UpdateProduct (pid) {
      var t = this
      var obj
      for (let i = 0; i < t.update_form.sell_list.length; i++) {
        if (t.update_form.sell_list[i].product_id === pid) {
          obj = t.update_form.sell_list[i]
        }
      }
      Object.keys(obj).forEach(function (key) {
        if (typeof obj[key] === 'number') {
          obj[key] = obj[key].toString()
        }
      })
      this.$axios({
        method: 'post',
        url: 'http://localhost:8081/market/UpdateProduct',
        dataType: 'json',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + t.$getCookie('otod_access_token')
        },
        data: obj
      }).then(function (response) {
        t.update_ok = true
        t.myproduct()
      })
    },
    DeleteProduct (pid) {
      var t = this
      this.$axios({
        method: 'post',
        url: 'http://localhost:8081/market/DeleteProduct',
        dataType: 'json',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': 'Bearer ' + t.$getCookie('otod_access_token')
        },
        params: {
          product_id: pid
        }
      }).then(function (response) {
        if (response.data === 'success') {
          t.delete_product_ok = true
          t.myproduct()
        } else if (response.data === 'failure') {
          t.delete_product_wrong = true
        }
      })
    },
    formatDate (time) {
      var date = new Date(time)
      var year = date.getFullYear()
      var month = date.getMonth() + 1
      var day = date.getDate()
      var hour = date.getHours()
      var min = date.getMinutes()
      var sec = date.getSeconds()
      var newTime = year + '-' + month + '-' + day + ' ' + hour + ':' + min + ':' + sec
      return newTime
    }
  }
}
</script>

<style>
<style>
.form .form-left {
width:50%; padding-left:1rem; padding-right:1rem; padding-top:1rem;
}
.form .form-right {
width:50%; padding-left:1rem; padding-right:1rem; padding-top:1rem;
}
</style>
