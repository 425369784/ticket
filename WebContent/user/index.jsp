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
<title>方向管理</title>

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
		search(pageNum);
	});
	
	function showUpdate(spid, spname){
		$("input[name='s_spid']").val(spid);
		$("input[name='s_spname']").val(spname);
	}
	
	function update(){
		
		// 获取用户的输入值
		var spname = $("input[name='s_spname']").val();
		var spid = $("input[name='s_spid']").val();
		
		$.ajax({
			url : "admin/specialtyServlet",	// 请求地址 
			type : "POST",	// 请求类型
			async : "true",	// 是否异步执行.
			data : {"action" : "update", "spid":spid, "spname" : spname},	// 给请求地址传递的参数a.json?action=topNew
			dataType : "json",	// 返回数据的数据类型.
			success : function(data){
				
				if (data.res == 1){
					alert("修改成功");
					$("input[name='s_spname']").val("");
				}
				else {
					alert("修改失败");
				}
				
				// 刷新数据列表
				search(1);
			}
		});
		
		return false;
	}
	function delet(bid){
		var uid = ${sessionScope.student.uid};
// 		alert(uid);
		// 使用异步进行删除操作
		$.ajax({
			url : "student/borrowServlet",	// 请求地址 
			type : "POST",	// 请求类型
			async : "true",	// 是否异步执行.
			data : {"action" : "delete", "bid" : bid,"uid" : uid},	// 给请求地址传递的参数a.json?action=topNew
			dataType : "json",	// 返回数据的数据类型.
			success : function(data){
				
				if (data.res == 1){
					alert("还书成功");
				}
				else {
					alert("还书失败");
				}
				
				// 刷新数据列表
				search(1);
			}
		});
	}
	
	//借书
	function add(bid){
		
// 		// 获取用户的输入值
// 		var spname = $("input[name='spname']").val();
	    var uid = ${sessionScope.student.uid};
// 		alert(uid);
		$.ajax({
			url : "student/borrowServlet",	// 请求地址 
			type : "POST",	// 请求类型
			async : "true",	// 是否异步执行.
			data : {"action" : "add", "bid" : bid,"uid":uid},	// 给请求地址传递的参数a.json?action=topNew
			dataType : "json",	// 返回数据的数据类型.
			success : function(data){
				
				if (data.res == 1){
					alert("借书成功");
					$("input[name='spname']").val("");
				}
				else {
					alert("借书失败");
				}
				
				// 刷新数据列表
				search(1);
			}
		});
		
		return false;
	}
	
	function search(pageNum){
		// 获取用户的输入值
		var key = $("input[name='key']").val();
		$.ajax({
			url : "student/bookServlet",	// 请求地址 
			type : "POST",	// 请求类型
			async : "true",	// 是否异步执行.
			data : {"action" : "select", "key": key, "pageNum": pageNum},	// 给请求地址传递的参数a.json?action=topNew
			dataType : "json",	// 返回数据的数据类型.
			success : function(data){	// 当请求成功后, 响应方法, 请求回来的数据会保存到data变量中.
						
				var html = "<tr><td>ID</td><td>书名</td><td>作者</td><td>操作</td></tr>";
				// 解析data数据, 并将其显示到页面中.  类似于java for( 类型  变量 : 集合){  }
				$.each(data.list, function(index, item){
					html += 
						"<tr>" +
						"	<td>" + item.bid + "</td>" + 
						"	<td>" + item.btitle + "</td>" +
						"	<td>" + item.bauthor + "</td>" +
						"	<td><button class='btn btn-success' onclick='add("+item.bid+")'>借书</button> " +
						"	<button class='btn btn-success' onclick='delet("+item.bid+")'>还书</button> </td>  " +
						"</tr>";
				});
				
				$("table").html(html);
				
				// 设置页码
				setPage(data.pageNum, data.pageCount, "search");								
			}
		});
		
		return false;
	}
	
</script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<div class="col-sm-12"><h1 id="title">借阅</h1></div>
		</div>
		<div class="row">
			
			<div class="col-sm-6 "> 
				<form class="form-inline" role="form">
					<div class="form-group">
						<label class="sr-only" for="key">关键字</label> 
						<input type="text" class="form-control"  name="key"  placeholder="请输入书名"/>
					</div>
					<input type="submit" class="btn btn-default" onclick="return search(1);" value="搜索"></input>
				</form>
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
	<div class="modal fade" id="update" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	                <h4 class="modal-title" id="myModalLabel">修改方向</h4>
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
							<label  class="col-sm-2 control-label">方向名：</label>
							<div class="col-sm-10">
								<input type="hidden" name="s_spid" />
								<input class="form-control" type="text" name="s_spname"/>
							</div>
						</div>
					</form>
				</div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary"  data-dismiss="modal" onclick="update()">提交</button>
	            </div>
	        </div><!-- /.modal-content -->
	    </div><!-- /.modal -->
	</div>
</body>
</html>