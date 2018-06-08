<template>
    <el-col :xs="12" :sm="12" :md="12" :xl="12" :offset="4">
<<<<<<< HEAD
        <el-card>
            <el-container>
                <el-header>publish order</el-header>
                <el-main>
                    <el-form :model="form" label-width="60px">
                        <el-form-item label="标题：">
                            <el-input v-model="form.title"></el-input>
                        </el-form-item>
                        <el-form-item label="内容：">
                            <el-input type="textarea" v-model="form.content"></el-input>
                        </el-form-item>
                        <el-form-item label="时间：">
                            <el-col :span="10">
                                <el-date-picker type="date" placeholder="选择开始日期" v-model="form.s_data" style="width: 100%;"></el-date-picker>
                            </el-col>
                            <el-col :span="1">-</el-col>
                            <el-col :span="10">
                                <el-date-picker type="date" placeholder="选择结束日期" v-model="form.e_data" style="width: 100%;"></el-date-picker>
                            </el-col>
                        </el-form-item>
                        <el-form-item label="人数：">
                            <el-select v-model="form.contributer" clearable placeholder="需要多少人？">
                                <el-option
                                    v-for="item in form.contributers_op"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                                </el-option>
                            </el-select>
                        </el-form-item>
                    </el-form>
                </el-main>
            </el-container>
        </el-card>
=======
        <span>publish order</span>
>>>>>>> 92528660ca6c9b7c5a1e064c1fa58bd678c42325
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
        contributers: '',
        s_data: '',
        e_data: ''
      }
    }
  }
}
</script>
