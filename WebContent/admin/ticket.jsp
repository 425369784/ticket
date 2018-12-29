<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%     
	String path = request.getContextPath();     
	String basePath = request.getScheme()+"://" +request.getServerName()+":" +request.getServerPort()+path+"/" ;
%>
<!DOCTYPE>
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<!-- 主动适应不同设备的显示方式 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>门票信息</title>

<!-- 在页面中引用样式和JS文件时, 一般, 先引入样式, 再引入JS -->
<!-- 引入Bootstrap的样式表 -->
<link href="bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">


<script type="text/javascript" src="js/jquery-2.2.4.min.js"></script>
<script type="text/javascript" src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script type="text/javascript" src="page/pagetool.js"></script>

<script type="text/javascript">
	
	var pageNum = 1;
	
	$(function(){
		// 获取方向信息
		getView();
		search(pageNum);
	});
	
	function search(pageNum){
		// 获取用户的输入值
 		var key = $("select[name='vid']").val();
		//alert(key);
		$.ajax({
			url : "user/ticketServlet",	// 请求地址
			type : "POST",	// 请求类型
			async : "true",	// 是否异步执行.
			data : {"action" : "select", "id": key, "pageNum": pageNum},	// 给请求地址传递的参数a.json?action=topNew
			dataType : "json",	// 返回数据的数据类型.
			success : function(data){	// 当请求成功后, 响应方法, 请求回来的数据会保存到data变量中.
						
				var html = "<tr><td>门票ID</td><td>门票名称</td><td>门票信息</td><td>门票价格</td></tr>";
				$.each(data.list, function(index, item){
					html += 
						"<tr>" +
						"	<td>" + item.id + "</td>" + 
						"	<td>" + item.name+ "</td>" +
						"	<td>" + item.infor + "</td>" +
						"	<td>" + item.price + "</td>" +
						"	<td><button class='btn btn-success' onclick='delet("                                                +item.id+")'>删除</button> " +
						"       <button class='btn btn-success' data-toggle='modal' data-target='#upModal' "+" onclick='modify("+item.id+")'>修改</button></td>" +
						"</tr>";
				});
				
				$("table").html(html);
				
				// 设置页码
				setPage(data.pageNum, data.pageCount, "search");								
			}
		});
		
		return false;
	}
	function modify(vid){
		$("input[name='mvid']").val(vid);
		$("input[name='vname_updata']").val("333");
		$("input[name='vinfor_updata']").val("444");
	}
	function add(){
		
		var tname = $("input[name='tname']").val();
		var tinfo = $("input[name='tinfo']").val();
		var price = $("input[name='price']").val();
		var vid = $("select[name='vid']").val();
		alert(tname+tinfo);
		$.ajax({
			url : "user/ticketServlet",
			type : "POST",
			data : {"action" : "addTicket", "name" : tname, "infor" : tinfo ,"vpid" : vid ,"price" : price},
			dataType : "json",
			success : function(data){
				
				if (data.res > 0){
					alert("添加成功!");
				}
				else{
					alert("添加失败");
				}
			}
		});
		search(1);
		return false;
	}
	
	function delet(tid){
		$.ajax({
			url : "user/ticketServlet",
			type : "POST",	// 请求类型
			async : "true",	// 是否异步执行.
			data : {"action" : "delete", "id" : tid},	// 给请求地址传递的参数a.json?action=topNew
			dataType : "json",	// 返回数据的数据类型.
			success : function(data){	
				if (data.res == 1){
					alert("删除成功");
				}
				else {
					alert("删除失败");
				}	
				// 刷新数据列表
				search(1);
			}
		});
	}
	
	function update(){
		// 获取用户的输入值
		var tinfo = $("input[name='tinfo_update']").val();
		var tname = $("input[name='tname_update']").val();
		var tid = $("input[name='mvid']").val();
		var price = $("input[name='price_update']").val();
		//alert(tinfo+tname+tid+price);
		$.ajax({
			url : "user/ticketServlet",	// 请求地址 
			type : "POST",	// 请求类型
			async : "true",	// 是否异步执行.
			data : {"action" : "update", "tid":tid,"tname" : tname,"tinfo" : tinfo, "price" : price},	// 给请求地址传递的参数a.json?action=topNew
			dataType : "json",	// 返回数据的数据类型.
			success : function(data){
				if (data.res == 1){
					alert("修改成功");
					
				}
				else {
					alert("修改失败");
				}
				
				// 刷新数据列表
				
			}
		});
		search(1);
		return false;
	}
	
	function getView(){
		$.ajax({
			url : "user/ticketServlet",
			type : "POST",
			data : {"action" : "getView"},
			dataType : "json",
			success : function(data){
				
				var html = "<option value='-1'>请选择景点</option>";
				$.each(data, function(index, item){
					html += "<option value='"+item.id+"'>"+item.name+"</option>";
				});
				
				$("select[name='vid']").html(html);
			}
		});
	}
</script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<div class="col-sm-6"><h1 id="title">门票管理</h1></div>
			
		</div>
		<div class="row">
			<label class="col-sm-2 control-label">景点:</label>
			<div class="col-sm-4">
				<select class="form-control" name="vid"></select>
			</div>
			<div class="col-sm-2">
				<button class='btn btn-success' onclick="search(1)">搜索</button>
				<button class="btn btn-success pull-right" data-toggle="modal" data-target="#addModal" style="margin-right:10px">添加</button>
			</div>
			
		</div>
		<div class="row">
			
			<div class="col-sm-6 "> 
				
			</div>
		</div>
			
		<table class="table" >
			
			
		</table>
		<div id="page">
			
		</div>
		<div class="row">
			<div class="col-sm-12">
				<jsp:include page="../page/pagetool.jsp"></jsp:include>
			</div>
		</div>
	</div>
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="myModalLabel">添加门票</h4>
	            </div>
	            <div class="modal-body">
					<form class="form-horizontal">
<!-- 						<div class="form-group"> -->
<!-- 							<label class="col-sm-2 control-label">方向：</label> -->
<!-- 							<div class="col-sm-8"> -->
<!-- 								<select class="form-control" name="spid"> -->
<!-- 								</select> -->
<!-- 							</div> -->
<!-- 						</div> -->
						<div class="form-group">
							<label  class="col-sm-3 control-label">门票名称：</label>
							<div class="col-sm-9">
								<input class="form-control" type="text" name="tname"/>
							</div>
						</div>
						<div class="form-group">
							<label  class="col-sm-3 control-label">门票信息：</label>
							<div class="col-sm-9">
								<input class="form-control" type="text" name="tinfo"/>
							</div>
						</div>
						<div class="form-group">
							<label  class="col-sm-3 control-label">门票价格：</label>
							<div class="col-sm-9">
								<input class="form-control" type="text" name="price"/>
							</div>
						</div>
					</form>
				</div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary"  data-dismiss="modal" onclick="add()">提交</button>
	            </div>
	        </div><!-- /.modal-content -->
	    </div><!-- /.modal -->
	</div>
	<div class="modal fade" id="upModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="myModalLabel">修改门票</h4>
	            </div>
	            <div class="modal-body">
					<form class="form-horizontal">
						<div class="form-group">
							<label class="col-sm-2 control-label">门票id：</label>
							<div class="col-sm-8">
								<input class="form-control" type="text" name="mvid" readonly="readonly"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">门票名称：</label>
							<div class="col-sm-8">
								<input class="form-control" type="text" name="tname_update" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">门票信息：</label>
							<div class="col-sm-8">
								<input class="form-control" type="text" name="tinfo_update" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">门票价格：</label>
							<div class="col-sm-8">
								<input class="form-control" type="text" name="price_update" />
							</div>
						</div>
					</form>
				</div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary"  data-dismiss="modal" onclick="return update()">提交</button>
	            </div>
	        </div><!-- /.modal-content -->
	    </div><!-- /.modal -->
	</div>
</body>
</html>