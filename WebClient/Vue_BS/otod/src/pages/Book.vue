<template>
  <div id="zhuyao">
    <div id="main">
      <table id="bg1">
        <tr>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td>我要买：</td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td><a style="bgcolor:black;">搜索电子书</a></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td><router-link to="../components/book/Gouwuche">我的购物车</router-link></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td><router-link to="../components/book/Shujia">我的书架</router-link></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td><router-link to="../components/book/Zuji">浏览足迹</router-link></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
        </tr>

        <tr>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td>我要卖：</td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td><router-link to="../components/book/Fabu">发布电子书</router-link></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td><router-link to="../components/book/Bookguanli">电子书管理</router-link></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td><router-link to="../components/book/Ddguanli">订单管理</router-link></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
        </tr>
      </table>
    </div>
    <div id="menu_white">
    </div>

    <div id="box">
      <div class="search">
        <input type="text" class="searchBox" @keyup.enter="search" v-model="searchVal">
        <button class="btn" @click="search">搜 索</button>
      </div>
      <table class="goodsheet">
        <tr>
          <th>电子书ID</th>
          <th>电子书名</th>
          <th>单价</th>
          <th>了解更多</th>
        </tr>
        <tr v-if="!listSearch.length">
          <td colspan="4"> 暂时没有数据</td>
        </tr>
        <tr v-for='(item, key) in listSearch' :key="key">
          <td>{{item.id}}</td>
          <td>{{item.bookname}}</td>
          <td>{{item.monly}}</td>
          <td style="cursor: pointer" @click="bookDetail(item)">{{item.more}}</td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
export default {
  name: 'zhuyao',
  data () {
    return {
      list: [{id: '000001', bookname: '赢在下班后', monly: 17.99, more: '进入'},
        {id: '000002', bookname: '赢在下班后', monly: 16.55, more: '进入'},
        {id: '000003', bookname: '赢在下班后', monly: 18.50, more: '进入'},
        {id: '000004', bookname: '社交尴尬症', monly: 9.99, more: '进入'},
        {id: '000005', bookname: '社交尴尬症', monly: 10.50, more: '进入'},
        {id: '000006', bookname: '社交尴尬症', monly: 9.99, more: '进入'},
        {id: '000007', bookname: '半小时漫画中国史2', monly: 19.99, more: '进入'},
        {id: '000008', bookname: '流量池', monly: 29.99, more: '进入'},
        {id: '000009', bookname: '终身学习', monly: 28.80, more: '进入'},
        {id: '000010', bookname: '情商', monly: 128.80, more: '进入'},
        {id: '000011', bookname: '终身学习', monly: 27.99, more: '进入'},
        {id: '000012', bookname: '终身学习', monly: 26.55, more: '进入'},
        {id: '000013', bookname: '如何优雅地挽回前任', monly: 13.50, more: '进入'},
        {id: '000015', bookname: '以眨眼干杯', monly: 10.50, more: '进入'},
        {id: '000016', bookname: '侏罗纪公园', monly: 19.99, more: '进入'},
        {id: '000017', bookname: '时间的玫瑰', monly: 59.99, more: '进入'},
        {id: '000018', bookname: '哈佛谈判心理学', monly: 29.99, more: '进入'},
        {id: '000019', bookname: '失踪的总统', monly: 8.80, more: '进入'},
        {id: '000020', bookname: '时间的驳论', monly: 12.80, more: '进入'},
        {id: '000021', bookname: '终有一天你会懂', monly: 21.05, more: '进入'},
        {id: '000022', bookname: '此刻不要回头', monly: 15.55, more: '进入'},
        {id: '000023', bookname: '此刻不要回头', monly: 14.50, more: '进入'},
        {id: '000024', bookname: '社交尴尬症', monly: 10.99, more: '进入'},
        {id: '000025', bookname: '社交尴尬症', monly: 10.50, more: '进入'},
        {id: '000026', bookname: '此刻不要回头', monly: 19.99, more: '进入'},
        {id: '000027', bookname: '10秒沟通', monly: 12.99, more: '进入'},
        {id: '000028', bookname: '10秒沟通', monly: 29.99, more: '进入'},
        {id: '000029', bookname: '国家是怎样炼成的', monly: 27.80, more: '进入'},
        {id: '000030', bookname: '国家宝藏', monly: 28.80, more: '进入'},
        {id: '000031', bookname: '国家宝藏', monly: 27.99, more: '进入'},
        {id: '000032', bookname: '国家宝藏', monly: 26.55, more: '进入'},
        {id: '000033', bookname: '你那么懂事,一定很辛苦吧', monly: 13.50, more: '进入'},
        {id: '000034', bookname: '终有一天你会懂', monly: 9.99, more: '进入'},
        {id: '000035', bookname: '此刻不要回头', monly: 10.50, more: '进入'},
        {id: '000036', bookname: '此刻不要回头', monly: 9.99, more: '进入'},
        {id: '000037', bookname: '时间的玫瑰(全新升级版)', monly: 59.99, more: '进入'},
        {id: '000038', bookname: '哈佛谈判心理学', monly: 29.99, more: '进入'},
        {id: '000039', bookname: '红楼梦', monly: 8.80, more: '进入'},
        {id: '000040', bookname: '三国演义', monly: 12.80, more: '进入'},
        {id: '000041', bookname: '水浒传', monly: 17.99, more: '进入'},
        {id: '000042', bookname: '西游记', monly: 16.55, more: '进入'},
        {id: '000043', bookname: '经济管理', monly: 18.50, more: '进入'},
        {id: '000044', bookname: '资本论', monly: 9.99, more: '进入'},
        {id: '000045', bookname: '美的历程', monly: 40.50, more: '进入'},
        {id: '000046', bookname: '活出生命的意义', monly: 33.99, more: '进入'},
        {id: '000047', bookname: '理想国', monly: 26.00, more: '进入'},
        {id: '000048', bookname: '基督要义', monly: 129.99, more: '进入'},
        {id: '000049', bookname: '论语', monly: 28.80, more: '进入'},
        {id: '000050', bookname: '明天会更好', monly: 128.80, more: '进入'},
        {id: '000051', bookname: '明天的你会感谢今天努力的自己', monly: 27.99, more: '进入'},
        {id: '000052', bookname: '占星学入门', monly: 26.55, more: '进入'},
        {id: '000053', bookname: '存在与时间', monly: 12.50, more: '进入'},
        {id: '000054', bookname: '王安石全集', monly: 7.99, more: '进入'},
        {id: '000055', bookname: '微表情心理学', monly: 27.50, more: '进入'},
        {id: '000056', bookname: '自卑与超越', monly: 19.99, more: '进入'},
        {id: '000057', bookname: '天生变态狂', monly: 29.99, more: '进入'},
        {id: '000058', bookname: '懂你', monly: 29.99, more: '进入'},
        {id: '000059', bookname: '如何从这个世界消失', monly: 8.80, more: '进入'},
        {id: '000060', bookname: '梦的解析', monly: 12.80, more: '进入'}],
      searchVal: '',
      listSearch: []
    }
  },
  mounted: function () {
    // 初始化判断是否记住密码
  },
  methods: {
    search: function () {
      let self = this
      let listNew = self.list

      self.listSearch = []
      for (var i = 0; i < listNew.length; i++) {
        if (listNew[i].bookname.indexOf(self.searchVal) >= 0) {
          self.listSearch.push(listNew[i])
        }
      }
      self.listSearch.sort(self.sortMonly('monly'))
    },
    sortMonly: function (prop) {
      return function (obj1, obj2) {
        var val1 = obj1[prop]
        var val2 = obj2[prop]
        if (val1 < val2) {
          return -1
        } else if (val1 > val2) {
          return 1
        } else {
          return 0
        }
      }
    },
    bookDetail (obj) {
      var date = new Date()
      var timeNew = date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate()
      obj.time = timeNew
      this.$store.state.hostoryList.push(obj)
      this.$router.push({path: '/book', query: obj})
    }
  }
}
</script>

<style>
  #zhuyao {
    margin: 0px;
    padding: 0px;
    font-size: 12px;
    line-height: 20px;
  }

  #main {

    border: solid 1px #999;
    width: 960px;
    margin: 0px auto 0px auto;
    clear: both;
    float: none;
    background-color: rgb(84, 92, 100);

  }

  #bg1 {
    border: solid 1px #999;
    width: 960px;
  }

  #menu_white, #box, .goodsheet {
    border: solid 1px #999;
    width: 960px;
    margin: 0px auto 0px auto;
    clear: both;
    float: none;
  }

  .search {
    margin-left: 10px;
    margin-top: 5px;
    margin-bottom: 5px;
    margin-right: 10px;

  }
   td
  {
    text-align:center;
  }
  th
  {
    text-align:center;
  }
</style>
