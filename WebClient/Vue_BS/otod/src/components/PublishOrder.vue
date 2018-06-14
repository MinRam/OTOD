<template>
    <el-col :xs="12" :sm="12" :md="12" :xl="12" :offset="4">
        <el-card>
            <el-container>
                <el-header class="po-header-container">发布！</el-header>
                <el-main>
                    <el-form :model="form" :label-position="labelPosition">
                        <el-form-item>
                            <el-input v-model="form.title" require>
                                <template slot="prepend">标题：</template>
                            </el-input>
                        </el-form-item>
                        <el-form-item label="内容" label-width="80px">
                            <el-input type="textarea" :autosize="{ minRows: 4}" v-model="form.content"></el-input>
                        </el-form-item>
                        <el-form-item label="时间" label-width="80px">
                            <el-date-picker type="date" placeholder="选择结束日期" v-model="form.e_date"></el-date-picker>
                        </el-form-item>
                        <el-form-item label="人数需求" label-width="80px">
                            <el-select v-model="form.contributer" clearable placeholder="需要多少人？">
                                <el-option
                                    v-for="item in form.contributers_op"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="紧急程度" label-width="80px">
                            <el-select v-model="form.urgency" clearable placeholder="有多紧急？">
                                <el-option
                                    v-for="item in form.urgency_op"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                                    <span v-if="item.value == 1" style="float: left; color: #909399">{{ item.label }}</span>
                                    <span v-if="item.value == 2" style="float: left; color: #67C23A">{{ item.label }}</span>
                                    <span v-if="item.value == 3" style="float: left; color: #E6A23C">{{ item.label }}</span>
                                    <span v-if="item.value == 4" style="float: left; color: #F56C6C">{{ item.label }}</span>
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-form>
                </el-main>
                <el-footer class="po-footer-container">
                    <el-col :span="24">
                        <el-button type="primary" style="width: 100%;" @click="submit()">发布按钮</el-button>
                    </el-col>
                </el-footer>
            </el-container>
        </el-card>
    </el-col>
</template>

<script>
export default {
  name: 'PublishOrder',
  data () {
    return {
      form: {
        title: '',
        content: '',
        contributers_op: [{
          value: '1',
          label: '一个人'
        }, {
          value: '2',
          label: '两个人'
        }, {
          value: '3',
          label: '三个人'
        }, {
          value: '4',
          label: '四个人'
        }, {
          value: '5',
          label: '五个人'
        }, {
          value: '6',
          label: '六个人'
        }, {
          value: '7',
          label: '七个人'
        }, {
          value: '8',
          label: '八个人'
        }, {
          value: '9',
          label: '九个人'
        }, {
          value: '10',
          label: '十个人'
        }],
        contributer: '',
        urgency_op: [{
          value: '1',
          label: '闲置'
        }, {
          value: '2',
          label: '正常'
        }, {
          value: '3',
          label: '紧急'
        }, {
          value: '4',
          label: '严重'
        }],
        urgency: '',
        s_date: '',
        e_date: ''
      },
      labelPosition: 'left'
    }
  },
  methods: {
    submit () {
      console.log(this.$getCookie('otod_access_token'))
      if (this.form.title === '') {
        this.$message({
          showClose: true,
          message: '警告哦，你的标题没有写',
          type: 'warning'
        })
      } else if (this.form.content === '') {
        this.$message({
          showClose: true,
          message: '警告哦，你的内容没有写',
          type: 'warning'
        })
      } else if (this.form.e_date === '') {
        this.$message({
          showClose: true,
          message: '警告哦，你的结束日期没有写',
          type: 'warning'
        })
      } else if (this.form.urgency === '') {
        this.$message({
          showClose: true,
          message: '警告哦，你的紧急程度没有写',
          type: 'warning'
        })
      } else if (this.form.contributers === '') {
        this.$message({
          showClose: true,
          message: '警告哦，你的所需人数没有写',
          type: 'warning'
        })
      } else {
        // var orderPOJO = [{
        //   title: 'this.form.title',
        //   content: 'this.form.content',
        //   type: '',
        //   sDate: '',
        //   deadline: 'this.form.e_date',
        //   orderState: '',
        //   receiveTime: '',
        //   user_s: '',
        //   user_r: '',
        //   completeTime: '',
        //   urgency: 'this.form.urgency',
        //   profits: '',
        //   reason: '',
        //   contributers: 'this.form.contributers',
        //   orderEval: ''
        // }]
        var t = this
        this.$axios({
          method: 'post',
          url: t.$url + '/saveOrder',
          headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + t.$getCookie('otod_access_token')
          },
          data: {
            title: t.form.title,
            content: t.form.content,
            deadline: t.form.e_date,
            urgency: t.form.urgency,
            contributers: t.form.contributer
          }
        })
          .then(function (response) {
            t.form = ''
            t.$message({
              showClose: true,
              message: '发布成功！~',
              type: 'success'
            })
            // t.$router.push('/service/orderlist')
            document.getElementById('order-list').click()
            console.log(response)
          })
          .catch(function (error) {
            console.log(error)
          })
        // console.log(orderPOJO)
      }
    }
  }
}
</script>
<style>
    @import '../assets/css/PublishOrderPage.css';
</style>
