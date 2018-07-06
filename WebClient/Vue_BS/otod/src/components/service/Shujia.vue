<template>
  <div id="shoucan">
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
     <p>我的书架</p>
      <table class="goodsheet">
        <tr>
          <th>电子书名</th>
          <th>单价</th>
          <th>查看电子书</th>
        </tr>
        <tr v-for="(item,index) in $store.state.historyOrderList">
          <td>{{item.bookname}}</td>
          <td>{{item.monly}}</td>
          <td>查看电子书</td>
        </tr>
        </table>

    </div>


  </div>
</template>

<script>
  export default {
    name: 'Shujia'
  }

</script>

<style>
  #shoucan {
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
#shopping_list_end_1{
  float:right;
}
  .search {
    margin-left: 10px;
    margin-top: 5px;
    margin-bottom: 5px;
    margin-right: 10px;

  }
</style>
