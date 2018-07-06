 <template>
  <div id="fabu">
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
    <div id="menu_white">
    </div>
    <div style="width:960px; height:30px;"></div>
     <div id="box">
       <div class="bookCover_area">
                       
                        <textarea style="width:160px;height:100px;"></textarea>
                        <button>添加图片</button>
                     </div>
                   
                    <div class="newbook_centera">
			          书名：<input type="text"></br></br>
                     
                    售价：<input type="text"></br></br>
                             作者：<input type="text"></br></br>
                      
                       出版社：<input type="text"></br></br>
                   
                  
                    <!-- 图书描述 -->
                    
                         </div>
                           读书简介：<textarea style="width:300px;height:100px;"></textarea></br></br>
                 <button type="submit" value="发布商品">发布商品</button>
                </div>


      
    

    
  </div>
</template>

<script>
  
  export default {
    name: 'Fabu'
    
  }

</script>

<style>
  #fabu{
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
  

.bookCover_area {
    
    width: 160px;
    height: 330px;
   float:left;
}


 
.newbook_centera {
    width: 280px;
    height: 300px;
    float:left;
     text-align: right; 
}




</style>
