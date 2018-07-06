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
    <div style="width:960px; height:30px;"></div>
     <div id="box">

                    <div class="bookCover_area">
                        <!-- 左侧图 -->
                        <img src=".../assets/images/wrsh.jpg" width="100%" height="100%"/>
                     </div>
                    <div class="newbook_centera">

                    <!-- 图书描述 -->
                    <h1 class="bookname_box"><span class="title_words" title="无人生还">{{obj.bookName}}</span>
                    </h1>


                     </br>


                    <div class="price_box">
                        <div class="clearfix">
                            <div class="cost_box">

                                <p>售&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价：<span class="normal_price">¥<i>{{obj.monly}}</i></span></p>
                                </br>


                            </div>
                        </div>
                    </div>

                          <div class="explain_box">
                        <p  dd_name="作者" id="author">作&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;者：<span>（英）阿加莎·克里斯蒂</span></p>
                        </br>
                        <p  dd_name="出版社" id="publisher">出&nbsp;&nbsp;版&nbsp;&nbsp;社：<span>新星出版</span></p>
                        </br>



                         </div>
                         <div class="newbtn_" >
			                      		<a  class="green_btn_1 btn" @click="jiarugouwuche">加入购物车</a>
					                     <a  class="green_btn_2 btn" >立即购买</a>
				                    	<a class="green_btn_3 btn">下载</a>
                         </div>

                         </div>
                </div>




<div class="edit_title">
  <div id="biaoti"><span class="new_title" id="new_title" style="background:#fd7e0b;">读书简介</span></div>

  <div class="txt" >
十个相互陌生、身份各异的人受邀前往德文郡海岸边一座孤岛上的豪宅。客人到齐后，主人却没有出现。当晚，一个神秘的声音发出指控，分别说出每个人心中罪恶的秘密。接着，一位客人离奇死亡。暴风雨让小岛与世隔绝，《十个小士兵》——这首古老的童谣成了死亡咒语。如同歌谣中所预言的，客人一个接一个死去……杀人游戏结束后，竟无一人生还！
  </div>
  </div>





  </div>
</template>

<script>
  import wrsh from ".../assets/images/wrsh.jpg"
  export default {
    name: 'Dianzishu',
    data(){
      return {
        obj:{}
      }
    },
    mounted:function(){
      this.obj = this.$router.currentRoute.query;
    },
    methods:{
      jiarugouwuche(){
        this.$store.state.gouwucheList.push(this.obj);
        alert('加入购物车成功');
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
    border: solid 1px  #999;
    width: 960px;
  }

  #menu_white, .goodsheet {
    border: solid 1px  #999;
    width: 960px;
    margin: 0px auto 0px auto;
    clear: both;
    float: none;
  }
  #box{
    width: 960px;
    margin: 0px auto 0px auto;
    clear: both;
    float: none;
    height:350px;

  }

.edit_title{
  width: 960px;
  margin: 0px auto 0px auto;
  clear: both;
   float: none;
}
.bookCover_area {
    border: 1px solid #999;
    width: 260px;
    height: 330px;
    float:left;
}
  .search {
    margin-left: 10px;
    margin-top: 5px;
    margin-bottom: 5px;
    margin-right: 10px;

  }
  .newbook_centera {
    width: 550px;
    height: 300px;
    float:left;
}
.green_btn_1,.green_btn_2,.green_btn_3 {
    border: 1px solid #999;
    color: #fff;
    background-color: rgb(84, 92, 100); 
    width: 100px;
height: 36px;
display: block;
margin-right: 40px;
text-align: center;
line-height: 36px;
float: left;
cursor: pointer;
font-size: 16px;
}
.newbtn_{
  margin-top: 25px;
  margin-left: 50px;
}
 .new_title {
    width: 86px;
    line-height: 30px;
    color: #fff;
    font-size: 16px;
}
.new_title{

    display: inline-block;
    background: #999;
    border-radius: 0 5px 0 0;
    text-align: center;
    float:left;
}
#biaoti{
   border-bottom: 1px solid #999;
    width: 960px;

    float:left;
}

</style>
