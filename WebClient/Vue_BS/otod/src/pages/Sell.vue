<template>
  <div>
        <section>
          <div class="container">

<br/><br/>
<br/>
          <!--搜索栏-->
            <div class="search">
              <el-row>
                <el-col :span="8" :push="14">
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
                <el-col :span="10" :push="6">
                  <p>您当前的位置是：二手市场</p>
                </el-col>
              </el-row>
            </div>

            <el-form :model="ruleForm" :rules="rules" ref="ruleForm">
              <el-row>
                <el-col :span="12" :push="4">
                  <el-form-item label="商品分类" prop="product_catalog">
                    <el-select v-model="ruleForm.product_catalog" placeholder="请选择商品分类">
                      <el-option label="书本" value="1"></el-option>
                      <el-option label="服饰" value="2"></el-option>
                      <el-option label="鞋" value="3"></el-option>
                      <el-option label="其他" value="4"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="10" :push="4">
                  <el-form-item label="商品名称" prop="product_name">
                    <el-input v-model="ruleForm.product_name"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="6" :push="4">
                  <el-form-item label="商品单价" prop="product_price">
                    <el-input v-model="ruleForm.product_price"></el-input>
                  </el-form-item>
                </el-col>

                <el-col :span="6" :push="4" :offset="2">
                  <el-form-item label="商品发布数量" prop="product_stock">
                    <el-input v-model="ruleForm.product_stock"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="14" :push="4">
                  <el-form-item label="商品图片" >
                    <el-upload
                      ref="upload"
                      action="http://localhost:8081/market/saveimg"
                      list-type="picture-card"
                      :file-list="img"
                      :on-success="filllist"
                      :on-preview="handlePictureCardPreview"
                      :on-remove="handleRemove">
                      <i class="el-icon-plus"></i>
                    </el-upload>
                    <el-dialog :visible.sync="dialogVisible">
                      <img width="100%" :src="ruleForm.product_img" alt="">
                    </el-dialog>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :push="4" :span="8">
                  <el-form-item label="商品详情" prop="product_description">
                    <el-input type="textarea" v-model="ruleForm.product_description" :autosize="{ minRows: 2, maxRows: 4}"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :push="4" :span="4">
                  <el-form-item label="联系手机" prop="seller_phone">
                    <el-input v-model="ruleForm.seller_phone"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :push="6" :span="4">
                  <el-form-item label="请选择商品有效时长" prop="product_life">
                    <el-select v-model="ruleForm.product_life" placeholder="请选择商品有效时长">
                      <el-option label="1天" value="1"></el-option>
                      <el-option label="2天" value="2"></el-option>
                      <el-option label="3天" value="3"></el-option>
                      <el-option label="4天" value="4"></el-option>
                      <el-option label="5天" value="5"></el-option>
                      <el-option label="6天" value="6"></el-option>
                      <el-option label="7天" value="7"></el-option>
                      <el-option label="8天" value="8"></el-option>
                      <el-option label="9天" value="9"></el-option>
                      <el-option label="10天" value="10"></el-option>
                      <el-option label="11天" value="11"></el-option>
                      <el-option label="12天" value="12"></el-option>
                      <el-option label="13天" value="13"></el-option>
                      <el-option label="14天" value="14"></el-option>
                      <el-option label="15天" value="15"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :push="4" :span="6">
                  <el-form-item label="请选择商品适合交易时间" prop="product_day_from">
                    <el-select v-model="ruleForm.product_day_from">
                      <el-option label="00:00" value="0"></el-option>
                      <el-option label="01:00" value="1"></el-option>
                      <el-option label="02:00" value="2"></el-option>
                      <el-option label="03:00" value="3"></el-option>
                      <el-option label="04:00" value="4"></el-option>
                      <el-option label="05:00" value="5"></el-option>
                      <el-option label="06:00" value="6"></el-option>
                      <el-option label="07:00" value="7"></el-option>
                      <el-option label="08:00" value="8"></el-option>
                      <el-option label="09:00" value="9"></el-option>
                      <el-option label="10:00" value="10"></el-option>
                      <el-option label="11:00" value="11"></el-option>
                      <el-option label="12:00" value="12"></el-option>
                      <el-option label="13:00" value="13"></el-option>
                      <el-option label="14:00" value="14"></el-option>
                      <el-option label="15:00" value="15"></el-option>
                      <el-option label="16:00" value="16"></el-option>
                      <el-option label="17:00" value="17"></el-option>
                      <el-option label="18:00" value="18"></el-option>
                      <el-option label="19:00" value="19"></el-option>
                      <el-option label="20:00" value="20"></el-option>
                      <el-option label="21:00" value="21"></el-option>
                      <el-option label="22:00" value="22"></el-option>
                      <el-option label="23:00" value="23"></el-option>
                      <el-option label="24:00" value="24"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>

                <el-col :push="4" :span="8">
                  <el-form-item label="到" prop="product_day_to">
                    <el-select v-model="ruleForm.product_day_to">
                      <el-option label="00:00" value="0"></el-option>
                      <el-option label="01:00" value="1"></el-option>
                      <el-option label="02:00" value="2"></el-option>
                      <el-option label="03:00" value="3"></el-option>
                      <el-option label="04:00" value="4"></el-option>
                      <el-option label="05:00" value="5"></el-option>
                      <el-option label="06:00" value="6"></el-option>
                      <el-option label="07:00" value="7"></el-option>
                      <el-option label="08:00" value="8"></el-option>
                      <el-option label="09:00" value="9"></el-option>
                      <el-option label="10:00" value="10"></el-option>
                      <el-option label="11:00" value="11"></el-option>
                      <el-option label="12:00" value="12"></el-option>
                      <el-option label="13:00" value="13"></el-option>
                      <el-option label="14:00" value="14"></el-option>
                      <el-option label="15:00" value="15"></el-option>
                      <el-option label="16:00" value="16"></el-option>
                      <el-option label="17:00" value="17"></el-option>
                      <el-option label="18:00" value="18"></el-option>
                      <el-option label="19:00" value="19"></el-option>
                      <el-option label="20:00" value="20"></el-option>
                      <el-option label="21:00" value="21"></el-option>
                      <el-option label="22:00" value="22"></el-option>
                      <el-option label="23:00" value="23"></el-option>
                      <el-option label="24:00" value="24"></el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="8" :push="10">
                  <el-form-item label="" prop="agreement">
                    <el-checkbox-group v-model="ruleForm.agreement">
                      <el-checkbox label="您同意二手市场交易原则" name="agreement"></el-checkbox>
                    </el-checkbox-group>
                  </el-form-item>
                </el-col>
              </el-row>

              <el-row>
                <el-col :span="8" :push="10">
                  <el-form-item>
                    <el-button type="primary" @click="submitForm('ruleForm')">发布商品</el-button>
                    <el-button @click="resetForm('ruleForm')">重置</el-button>
                  </el-form-item>
                </el-col>
              </el-row>

            </el-form>
          </div>
        </section>
  </div>
</template>
<script>
export default {
  data () {
    return {
      loading: true,
      product_key: '',
      dialogVisible: false,
      img: [],
      ruleForm: {
        product_catalog: 0,
        product_name: '',
        product_price: 0,
        product_stock: 0,
        product_img: [],
        product_description: '',
        seller_phone: '',
        product_life: '',
        product_day_from: 0,
        product_day_to: 24,
        agreement: ''
      },
      rules: {
        product_catalog: [
          {required: true, message: '请选择商品分类', trigger: 'change'}
        ],
        product_name: [
          {required: true, message: '请输入商品名称', trigger: 'blur'}
        ],
        product_price: [
          {required: true, message: '请输入商品单价', trigger: 'blur'},
          {min: 1, message: '价格大于0', trigger: 'blur'}
        ],
        product_stock: [
          {required: true, message: '请输入发布数量', trigger: 'blur'},
          {min: 1, max: 4, message: '发布数量在1~999之间', trigger: 'blur'}
        ],
        product_img: [

        ],
        product_description: [
          {}
        ],
        seller_phone: [
          {required: true, message: '请输入联系手机', trigger: 'blur'},
          {min: 7, max: 13, message: '请输入正确位数手机', trigger: 'blur'}
        ],
        product_life: [
          {required: true, message: '请选择商品有效期', trigger: 'change'}
        ],
        product_day_from: [
          {required: true, message: '请选择合适交易时间', trigger: 'change'}
        ],
        product_day_to: [
          {required: true, message: '请选择合适交易时间', trigger: 'change'}
        ],
        agreement: [
          {required: true, message: '请先阅读后点击同意', trigger: 'blur'}
        ]
      }
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
    upload (item) {
      this.$axios({
        method: 'post',
        url: 'http://localhost:8081/market/saveimg',
        dataType: 'json',
        data: item.file
      }).then(function (response) {
        console.log(response)
      })
    },
    filllist (response, file) {
      var t = this.ruleForm.product_img
      t.push(response.data.file)
    },
    submitForm (formName) {
      this.$refs.upload.submit()
      var t = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          t.$axios({
            method: 'post',
            url: 'http://localhost:8081/market/addproduct',
            dataType: 'json',
            headers: {
              'Content-Type': 'application/json',
              'Authorization': 'Bearer ' + t.$getCookie('otod_access_token')
            },
            data: t.ruleForm
          })
        } else {
          console.log(t.ruleForm)
          return false
        }
      })
    },
    resetForm (formName) {
      this.$refs[formName].resetFields()
    },
    handleRemove (file, fileList) {
      console.log(file, fileList)
    },
    handlePictureCardPreview (file) {
      this.ruleForm.product_img = file.url
      this.dialogVisible = true
    }
  }
}
</script>
<style>
.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
