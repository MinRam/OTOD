<template>
<<<<<<< HEAD
    <div id="book">
=======
  <div id="zhuyao">
>>>>>>> a09985c9669671d9268d3c6583b5ec325dd88c3d
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
          <td><a>搜索电子书</a></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td>我的购物车</td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td>我的收藏夹</td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td>浏览足迹</td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td>历史订单</td>
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
          <td>发布电子书</td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td>电子书管理</td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td>订单管理</td>
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
<<<<<<< HEAD
        <tr v-for='(item, key) in listSearch'>
=======
        <tr v-for='(item, key) in listSearch' :key = "key">
>>>>>>> a09985c9669671d9268d3c6583b5ec325dd88c3d
          <td>{{item.id}}</td>
          <td>{{item.bookName}}</td>
          <td>{{item.monly}}</td>
          <td>{{item.more}}</td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
<<<<<<< HEAD
export default{
  name: 'Book',
   data () {
      return {
        list: [{id: '000001', bookName: '赢在下班后', monly: 17.99, more: '进入'},
          {id: '000002', bookName: '赢在下班后', monly: 16.55, more: '进入'},
          {id: '000003', bookName: '赢在下班后', monly: 18.50, more: '进入'},
          {id: '000004', bookName: '社交尴尬症', monly: 9.99, more: '进入'},
          {id: '000005', bookName: '社交尴尬症', monly: 10.50, more: '进入'},
          {id: '000006', bookName: '社交尴尬症', monly: 9.99, more: '进入'},
          {id: '000007', bookName: '半小时漫画中国史2', monly: 19.99, more: '进入'},
          {id: '000008', bookName: '流量池', monly: 29.99, more: '进入'},
          {id: '000009', bookName: '终身学习', monly: 28.80, more: '进入'},
          {id: '000010', bookName: '情商', monly: 128.80, more: '进入'},
          {id: '000011', bookName: '终身学习', monly: 27.99, more: '进入'},
          {id: '000012', bookName: '终身学习', monly: 26.55, more: '进入'},
          {id: '000013', bookName: '如何优雅地挽回前任', monly: 13.50, more: '进入'},
          {id: '000015', bookName: '以眨眼干杯', monly: 10.50, more: '进入'},
          {id: '000016', bookName: '侏罗纪公园', monly: 19.99, more: '进入'},
          {id: '000017', bookName: '时间的玫瑰', monly: 59.99, more: '进入'},
          {id: '000018', bookName: '哈佛谈判心理学', monly: 29.99, more: '进入'},
          {id: '000019', bookName: '失踪的总统', monly: 8.80, more: '进入'},
          {id: '000020', bookName: '时间的驳论', monly: 12.80, more: '进入'},
          {id: '000021', bookName: '终有一天你会懂', monly: 21.05, more: '进入'},
          {id: '000022', bookName: '此刻不要回头', monly: 15.55, more: '进入'},
          {id: '000023', bookName: '此刻不要回头', monly: 14.50, more: '进入'},
          {id: '000024', bookName: '社交尴尬症', monly: 10.99, more: '进入'},
          {id: '000025', bookName: '社交尴尬症', monly: 10.50, more: '进入'},
          {id: '000026', bookName: '此刻不要回头', monly: 19.99, more: '进入'},
          {id: '000027', bookName: '10秒沟通', monly: 12.99, more: '进入'},
          {id: '000028', bookName: '10秒沟通', monly: 29.99, more: '进入'},
          {id: '000029', bookName: '国家是怎样炼成的', monly: 27.80, more: '进入'},
          {id: '000030', bookName: '国家宝藏', monly: 28.80, more: '进入'},
          {id: '000031', bookName: '国家宝藏', monly: 27.99, more: '进入'},
          {id: '000032', bookName: '国家宝藏', monly: 26.55, more: '进入'},
          {id: '000033', bookName: '你那么懂事,一定很辛苦吧', monly: 13.50, more: '进入'},
          {id: '000034', bookName: '终有一天你会懂', monly: 9.99, more: '进入'},
          {id: '000035', bookName: '此刻不要回头', monly: 10.50, more: '进入'},
          {id: '000036', bookName: '此刻不要回头', monly: 9.99, more: '进入'},
          {id: '000037', bookName: '时间的玫瑰(全新升级版)', monly: 59.99, more: '进入'},
          {id: '000038', bookName: '哈佛谈判心理学', monly: 29.99, more: '进入'},
          {id: '000039', bookName: '红楼梦', monly: 8.80, more: '进入'},
          {id: '000040', bookName: '三国演义', monly: 12.80, more: '进入'},
          {id: '000041', bookName: '水浒传', monly: 17.99, more: '进入'},
          {id: '000042', bookName: '西游记', monly: 16.55, more: '进入'},
          {id: '000043', bookName: '经济管理', monly: 18.50, more: '进入'},
          {id: '000044', bookName: '资本论', monly: 9.99, more: '进入'},
          {id: '000045', bookName: '美的历程', monly: 40.50, more: '进入'},
          {id: '000046', bookName: '活出生命的意义', monly: 33.99, more: '进入'},
          {id: '000047', bookName: '理想国', monly: 26.00, more: '进入'},
          {id: '000048', bookName: '基督要义', monly: 129.99, more: '进入'},
          {id: '000049', bookName: '论语', monly: 28.80, more: '进入'},
          {id: '000050', bookName: '明天会更好', monly: 128.80, more: '进入'},
          {id: '000051', bookName: '明天的你会感谢今天努力的自己', monly: 27.99, more: '进入'},
          {id: '000052', bookName: '占星学入门', monly: 26.55, more: '进入'},
          {id: '000053', bookName: '存在与时间', monly: 12.50, more: '进入'},
          {id: '000054', bookName: '王安石全集', monly: 7.99, more: '进入'},
          {id: '000055', bookName: '微表情心理学', monly: 27.50, more: '进入'},
          {id: '000056', bookName: '自卑与超越', monly: 19.99, more: '进入'},
          {id: '000057', bookName: '天生变态狂', monly: 29.99, more: '进入'},
          {id: '000058', bookName: '懂你', monly: 29.99, more: '进入'},
          {id: '000059', bookName: '如何从这个世界消失', monly: 8.80, more: '进入'},
          {id: '000060', bookName: '梦的解析', monly: 12.80, more: '进入'}],
        searchVal: '',
        listSearch: []
      }
    },
    methods: {
      search: function () {
        let self = this;
        let listNew = self.list;
        self.listSearch = [];
        for (var i = 0; i < listNew.length; i++) {
          if (listNew[i].bookName.indexOf(self.searchVal) >= 0) {
            self.listSearch.push(listNew[i]);
          }
        }
        self.listSearch.sort(self.sortMonly("monly"));
      },
      sortMonly: function (prop) {
        return function (obj1, obj2) {
          var val1 = obj1[prop];
          var val2 = obj2[prop];
          if (val1 < val2) {
            return -1;
          } else if (val1 > val2) {
            return 1;
          } else {
            return 0;
          }
=======
export default {
  name: 'Book',
  data () {
    return {
      list: [{id: '000001', bookName: '赢在下班后', monly: 17.99, more: '进入'},
        {id: '000002', bookName: '赢在下班后', monly: 16.55, more: '进入'},
        {id: '000003', bookName: '赢在下班后', monly: 18.50, more: '进入'},
        {id: '000004', bookName: '社交尴尬症', monly: 9.99, more: '进入'},
        {id: '000005', bookName: '社交尴尬症', monly: 10.50, more: '进入'},
        {id: '000006', bookName: '社交尴尬症', monly: 9.99, more: '进入'},
        {id: '000007', bookName: '半小时漫画中国史2', monly: 19.99, more: '进入'},
        {id: '000008', bookName: '流量池', monly: 29.99, more: '进入'},
        {id: '000009', bookName: '终身学习', monly: 28.80, more: '进入'},
        {id: '000010', bookName: '情商', monly: 128.80, more: '进入'},
        {id: '000011', bookName: '终身学习', monly: 27.99, more: '进入'},
        {id: '000012', bookName: '终身学习', monly: 26.55, more: '进入'},
        {id: '000013', bookName: '如何优雅地挽回前任', monly: 13.50, more: '进入'},
        {id: '000015', bookName: '以眨眼干杯', monly: 10.50, more: '进入'},
        {id: '000016', bookName: '侏罗纪公园', monly: 19.99, more: '进入'},
        {id: '000017', bookName: '时间的玫瑰', monly: 59.99, more: '进入'},
        {id: '000018', bookName: '哈佛谈判心理学', monly: 29.99, more: '进入'},
        {id: '000019', bookName: '失踪的总统', monly: 8.80, more: '进入'},
        {id: '000020', bookName: '时间的驳论', monly: 12.80, more: '进入'},
        {id: '000021', bookName: '终有一天你会懂', monly: 21.05, more: '进入'},
        {id: '000022', bookName: '此刻不要回头', monly: 15.55, more: '进入'},
        {id: '000023', bookName: '此刻不要回头', monly: 14.50, more: '进入'},
        {id: '000024', bookName: '社交尴尬症', monly: 10.99, more: '进入'},
        {id: '000025', bookName: '社交尴尬症', monly: 10.50, more: '进入'},
        {id: '000026', bookName: '此刻不要回头', monly: 19.99, more: '进入'},
        {id: '000027', bookName: '10秒沟通', monly: 12.99, more: '进入'},
        {id: '000028', bookName: '10秒沟通', monly: 29.99, more: '进入'},
        {id: '000029', bookName: '国家是怎样炼成的', monly: 27.80, more: '进入'},
        {id: '000030', bookName: '国家宝藏', monly: 28.80, more: '进入'},
        {id: '000031', bookName: '国家宝藏', monly: 27.99, more: '进入'},
        {id: '000032', bookName: '国家宝藏', monly: 26.55, more: '进入'},
        {id: '000033', bookName: '你那么懂事,一定很辛苦吧', monly: 13.50, more: '进入'},
        {id: '000034', bookName: '终有一天你会懂', monly: 9.99, more: '进入'},
        {id: '000035', bookName: '此刻不要回头', monly: 10.50, more: '进入'},
        {id: '000036', bookName: '此刻不要回头', monly: 9.99, more: '进入'},
        {id: '000037', bookName: '时间的玫瑰(全新升级版)', monly: 59.99, more: '进入'},
        {id: '000038', bookName: '哈佛谈判心理学', monly: 29.99, more: '进入'},
        {id: '000039', bookName: '红楼梦', monly: 8.80, more: '进入'},
        {id: '000040', bookName: '三国演义', monly: 12.80, more: '进入'},
        {id: '000041', bookName: '水浒传', monly: 17.99, more: '进入'},
        {id: '000042', bookName: '西游记', monly: 16.55, more: '进入'},
        {id: '000043', bookName: '经济管理', monly: 18.50, more: '进入'},
        {id: '000044', bookName: '资本论', monly: 9.99, more: '进入'},
        {id: '000045', bookName: '美的历程', monly: 40.50, more: '进入'},
        {id: '000046', bookName: '活出生命的意义', monly: 33.99, more: '进入'},
        {id: '000047', bookName: '理想国', monly: 26.00, more: '进入'},
        {id: '000048', bookName: '基督要义', monly: 129.99, more: '进入'},
        {id: '000049', bookName: '论语', monly: 28.80, more: '进入'},
        {id: '000050', bookName: '明天会更好', monly: 128.80, more: '进入'},
        {id: '000051', bookName: '明天的你会感谢今天努力的自己', monly: 27.99, more: '进入'},
        {id: '000052', bookName: '占星学入门', monly: 26.55, more: '进入'},
        {id: '000053', bookName: '存在与时间', monly: 12.50, more: '进入'},
        {id: '000054', bookName: '王安石全集', monly: 7.99, more: '进入'},
        {id: '000055', bookName: '微表情心理学', monly: 27.50, more: '进入'},
        {id: '000056', bookName: '自卑与超越', monly: 19.99, more: '进入'},
        {id: '000057', bookName: '天生变态狂', monly: 29.99, more: '进入'},
        {id: '000058', bookName: '懂你', monly: 29.99, more: '进入'},
        {id: '000059', bookName: '如何从这个世界消失', monly: 8.80, more: '进入'},
        {id: '000060', bookName: '梦的解析', monly: 12.80, more: '进入'}],
      searchVal: '',
      listSearch: []
    }
  },
  methods: {
    search: function () {
      let self = this
      let listNew = self.list
      self.listSearch = []
      for (var i = 0; i < listNew.length; i++) {
        if (listNew[i].bookName.indexOf(self.searchVal) >= 0) {
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
>>>>>>> a09985c9669671d9268d3c6583b5ec325dd88c3d
        }
      }
    }
  }
<<<<<<< HEAD
=======
}
>>>>>>> a09985c9669671d9268d3c6583b5ec325dd88c3d

</script>

<style>
<<<<<<< HEAD
 #book {
=======
  #zhuyao {
>>>>>>> a09985c9669671d9268d3c6583b5ec325dd88c3d
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
</style>
