<template>
	<div class="container"  id="app">
	     <div class="jumbotron">
	     	 <h1>博客论坛系统</h1>
		 </div>
		 <div class="row" style="margin:40px 70px;">
			
			 <div class="col-md-2 panel panel-warning" style="margin:3px;max-width:200px" v-for="x in list">
			 <div class="panel-body">
			 	<div class="pull-left col-md-4">
			 		<span class="glyphicon glyphicon-subscript"></span>
			 	</div>
			 	<div class="pull-right col-md-8">
			 	<span>影视</span>
			 	</div>
			 </div>
			 </div>
		 </div>
		 <div class="row" style="margin: 10px 0px;">
	 		<div class="col-md-4 pull-right input-group">
	 			<span class="input-group-addon">标题：</span>
		 		<input type="text" class="form-control" style="width: auto;">
		 		<button class="btn btn-success">搜索</button>
		 	</div>
		 </div>
		 <div class="row">
			 <table class="table table-striped">
			 <tbody>
			 <tr v-for="x in forumTopicList">
			 	<td class="col-md-1">{{ x.click_num }}</td>
			 	<td class="col-md-9"><a v-on:click="toReply(x.id)">{{ x.title }}</a></td>
			 	<td  class="col-md-1">
			 		<a href="#">id：{{ x.user_id }}</a>
			 		<div class="clear-both"></div>
			 		<span style="color:#828282;">{{ x.date }}</span>
	            </td>
			 	<td  class="col-md-1">
			 		<span style="color:#828282;">{{ x.last_time }}</span>
			 		<div class="clear-both"></div>
			 		<a href="#">黑色的毒龙</a>
	     		</td>
			 	<td></td>
			 </tr>
			 </tbody>
			 </table>
		 </div>
		 <div class="row">
		    <ul class="pagination">
			<li><a href="#">&laquo;</a></li>
			<li><a v-on:click="changepage(0)">1</a></li>
			<li><a v-on:click="changepage(1)">2</a></li>
			<li><a v-on:click="changepage(2)">3</a></li>
			<li><a v-on:click="changepage(3)">4</a></li>
			<li><a v-on:click="changepage(4)">5</a></li>
			<li><a href="#">&raquo;</a></li>
		    </ul>
		 </div>
		 <div class="row">
		 	<form class="bs-example bs-example-form">
		 	<div class="input-group" style="margin:8px 0px;">
				<span class="input-group-addon">标题</span>
				<input type="text" class="form-control" placeholder="请输入标题" v-model="forumTopicPO.title">
			</div>
			<div class="form-group">
				<textarea class="form-control" rows="10" name="textarea" placeholder="请输入内容" v-model="forumTopicPO.content"></textarea>
			</div>
			<input type="button" value="确定" class="btn btn-success" v-on:click="changepage(1)">
		 	</form>

		 	<input type="button" value="确定" class="btn btn-success" v-on:click="queryByPage">
		 </div>
 </div>
</template>

<script>
export default {
  name: 'Login',
  data () {
    return {
    	list:[
    	{id:1},
    	{id:2},
    	{id:3},
    	{id:4},
    	{id:5},
    	{id:6},
    	{id:7},
    	{id:8}
      ],
      forumTopicLength:0,
      page:0,
      rows:10,
      forumTopicList:[
      	{}
      ],
      forumTopicPO:{
      	id:'',
      	click_num:'',
      	reply_num:'',
      	date:'',
      	last_reply_id:'',
      	last_reply_date:'',
      	section_id:'',
      	user_id:'',
      	type:'',
      	title:'',
  		 content:''
      }   
    }
  },
  mounted:function(){
    // 键盘监听注册
    this.queryByPage();

  },
  watch:{
  	page:{
  		handler:function(val,oldval)
  		{
  			this.queryByPage();
  		}
  	}
  },
  methods: {
    // 发表帖子
     postData () {
        this.$axios({
	        method: 'post',
	        url: 'http://localhost:8081/forumtopic/save',
	        
	        dataType:'json',
	        data: this.forumTopicPO
        }).then(function (response) {
				console.log(response)
				alert("发表成功")
				})
				.catch(function (error) {
				console.log(error)
				})
     },
     alee(){
     	alert("wwww")
     },

     test(){
     	this.$axios.get('http://localhost:8081/forumtopic/findbyid?id=1').then(function (response) {
				console.log(response)
			})
			.catch(function (error) {
				console.log(error)
			})
	},

	// 分页查询，axios，post
	queryByPage(){
		var t=this
		this.$axios({
      method: 'get',
      url: this.$url + 'forumtopic/findbypage',
      
      dataType:'json',
      params:{
      	page:t.page,
      	row:t.rows
      }
    }).then(function (response) {
		console.log(response)
  	// this.$set("forumTopicList",response.data.content)
    t.forumTopicList=response.data.content
    t.forumTopicLength = response.data.totalElements

    for(var i in t.forumTopicList)
    {
    	t.forumTopicList[i]['last_time'] = t.getLastTime(t.forumTopicList[i].date)
    	var date = new Date(t.forumTopicList[i].date)
    	if(date.getFullYear()<(new Date().getFullYear()))
    		t.forumTopicList[i].date = date.getFullYear()+"年"+date.getMonth()+"月"+date.getDate()+"日"
        else
        	t.forumTopicList[i].date = date.getMonth()+"月"+date.getDate()+"日"
    }

    var a=new Date(t.forumTopicList[0].date).getTime()
  		console.log(a)
  		console.log(t.forumTopicList)
  	})
  	.catch(function (error) {
  	console.log(error)
  	})
	},

    // 获得最后回复距今时间
	getLastTime(date){
		var now = new Date().getTime()
		var oldtime = new Date(date).getTime()
		var difference = now-oldtime
		var result = ''
		var minute = 1000*60
		var hour = minute*60
		var day = hour*24
		var halfamonth = day*15
		var month = day*30
		var year = month*12

		var _year = difference/year
		var _month = difference/month
	    var _week = difference/(7*day)
     	var _day = difference/day
		var _hour = difference/hour
		var _minute = difference/minute

		if(_year>=1) {result=""+~~(_year)+"年前"}
			else if(_month>=1) {result=""+~~(_month)+"个月前"}
			else if(_week>=1) {result=""+~~(_week)+"周前"}
			else if(_day>=1) {result=""+~~(_day)+"天前"}
			else if(_hour>=1) {result=""+~~(_hour)+"个小时前"}
			else if(_minute>=1) {result=""+~~(_minute)+"分钟前"}
		else result="刚刚"
		return result
	},

	// 改变页面
	changepage(page){
		this.page = page
		//this.queryByPage()
	},

    // 回复贴跳转
	toReply(id){
		this.$router.push({path:"forumreply",query:{id:id}})
	}


  }
}
</script>

<style>
  @import '../assets/bootstrap/css/bootstrap.min.css'
  a {
	  cursor:pointer;
	  color: black;
	} 
	a:hover{
	  text-decoration:none;
  }
</style>
