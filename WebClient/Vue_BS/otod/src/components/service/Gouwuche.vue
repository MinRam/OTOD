<template>
  <div id="gouwuche">
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
          <td><a><router-link to=".../pages/Book">搜索电子书</router-link></a></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td><router-link to="/Gouwuche">我的购物车</router-link></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td><router-link to="/Shujia">我的书架</router-link></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td><router-link to="/Zuji">浏览足迹</router-link></td>
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
          <td><router-link to="/Fabu">发布电子书</router-link></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td><router-link to="/Bookguanli">电子书管理</router-link></td>
          <td></td>
          <td></td>
          <td></td>
          <td></td>
          <td><router-link to="/Ddguanli">订单管理</router-link></td>
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
      <p>我的购物车</p>
      <table class="goodsheet">
        <tr>
          <th>电子书ID</th>
          <th>电子书名</th>
          <th>单价</th>
          <th>是否删除</th>
        </tr>
        <tr v-for="(item,index) in $store.state.gouwucheList">
          <td>{{item.bookid}}</td>
          <td>{{item.bookname}}</td>
          <td>￥ {{item.monly}}</td>
          <td style='cursor: pointer' @click="deletelist(index)">删除</td>
        </tr>
      </table>
      <div class="shopping_list_end">

        <ul>
          <li class="shopping_list_end_1">商品金额总计：
            <button style='cursor: pointer' @click="jisuan">结算</button>
          </li>

          </li>
        </ul>

      </div>
    </div>


  </div>
</template>

<script>
  export default {
    name: 'Gouwuche',
    data () {
      return {
        nummber:0,
      }
    },
    methods: {
      deletelist:function(index){
        this.$store.state.gouwucheList.splice(index,1);
      },
      jisuan:function(){
        let self = this;
        self.nummber = 0;
        this.$store.state.gouwucheList.forEach(function(value,index){
          self.nummber+=value.monly;
        });
        alert('确认付款金额为'+self.nummber.toFixed(2)+'元');
        this.$store.state.historyOrderList = this.$store.state.gouwucheList;
        this.$store.state.gouwucheList = [];
      }
    }
  }

</script>

<style>
  #gouwuche {
    margin: 0px;
    padding: 0px;
    font-size: 12px;
    line-height: 20px;

  }

  #main {

    border: solid 1px #fd7e0b;
    width: 960px;
    margin: 0px auto 0px auto;
    clear: both;
    float: none;

  }

  #bg1 {
    border: solid 1px #fd7e0b;
    width: 960px;
  }

  #menu_white, #box, .goodsheet {
    border: solid 1px #fd7e0b;
    width: 960px;
    margin: 0px auto 0px auto;
    clear: both;
    float: none;
  }

  #shopping_list_end_1 {
    float: right;
  }

  .search {
    margin-left: 10px;
    margin-top: 5px;
    margin-bottom: 5px;
    margin-right: 10px;

  }
</style>
