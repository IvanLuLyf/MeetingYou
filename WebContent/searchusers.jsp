<%@ page language="java"
	import="java.util.*,meetingmanager.vo.*" pageEncoding="utf-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>Meeting You</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script src="js/jquery.min.js"></script>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript">
	function goToOnePage(name,username,status) {
		var pageNum=document.getElementById("pageNum").value;
		if(pageNum==""){
			window.location.href="#";    
		}else{
			window.location.href="SearchUsersServlet?name="+name+"&username="+username+"&verified="+verified+"&pageNum="+pageNum;        
		}
	}
        
	$(function(){
		$('#opencloseModal').on('show.bs.modal', function (event) {
		console.log("test");
		var button = $(event.relatedTarget);
		var uid = button.data('uid');
		var act = $('#btnDoActionIt[data-uid="' + uid + '"]').attr("data-act");
		
		console.log(act);
		
		$("#btnDoAction").click(function(){
			if(act=="close"){
				txt=$.ajax({url:"./ApproveServlet?code=verify&uid=" + uid +"&action=close",async:false});
				if(txt.responseText=="ok"){
					btnDoActionIt = $('#btnDoActionIt[data-uid="' + uid + '"]');
					btnDoActionIt.attr("class","btn btn-success");
					btnDoActionIt.attr("data-act","enable");
					btnDoActionIt.text("启用账号");
					$('#opencloseModal').modal('hide');
				}
			}else if(act=="enable"){
				txt=$.ajax({url:"./ApproveServlet?code=verify&uid=" + uid +"&action=yes",async:false});
				if(txt.responseText=="ok"){
					btnDoActionIt = $('#btnDoActionIt[data-uid="' + uid + '"]');
					btnDoActionIt.attr("class","btn btn-danger");
					btnDoActionIt.attr("data-act","close");
					btnDoActionIt.text("关闭账号");
					$('#opencloseModal').modal('hide');
				}
			}
		});
		
		var modal = $(this);
		if(act=="close"){
			modal.find('.modal-title').text("关闭账号");
			modal.find('.modal-body').text("是否关闭账号");
			$("#btnDoAction").attr("class","btn btn-danger");
		}else{
			modal.find('.modal-title').text("启用账号");
			modal.find('.modal-body').text("是否启用账号");
			$("#btnDoAction").attr("class","btn btn-success");
		}
		});
	});
	
	$(document).ready(function(){

		});
	</script>
</head>
<body>
    <div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12">
			<%@include file="header.jsp"%>
			<div class="row">
				<div class="modal fade" id="opencloseModal" tabindex="-1" role="dialog" aria-labelledby="opencloseModalLabel">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
								<h4 class="modal-title" id="opencloseModalLabel">关闭账号</h4>
							</div>
							<div class="modal-body">
								是否关闭该账号
							</div>
							<div class="modal-footer">
								<button type="button" id="btnDoAction" class="btn btn-default">确定</button>
								<button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
							</div>
						</div>
					</div>
				</div>			
				<div class="hidden-xs col-sm-4 col-md-2">
				<%@include file="sidebar.jsp" %>
				</div>
				<div class="col-xs-12 col-sm-8 col-md-10">
					<ul class="breadcrumb">
						<li>
							<a href="#">Meeting You</a> <span class="divider"></span>
						</li>
						<li>
							<a href="#">人员管理</a> <span class="divider"></span>
						</li>
						<li class="active">
							<a href="#">搜索员工</a>
						</li>
					</ul>
					<div class="panel panel-info">
						<div class="panel-heading">
							<h3 class="panel-title">
								搜索员工
							</h3>
						</div>
						<div class="panel-body">
							<form method="post" action="SearchUsersServlet" role="form" class="form-inline">
								<div class="form-group">
									<label for="name">姓名:</label>
									<input type="text" id="name" name="name" value="${param.name}" maxlength="20" class="form-control"/>
									<label for="name">账号名:</label>
									<input type="text" id="username" name="username" value="${param.username}" maxlength="20" class="form-control"/>
									<label for="verified">状态:</label>
									
									<c:if test="${param.verified eq null or param.verified eq -1 or param.verified eq 3}">
									<input type="radio" id="verified" name="verified" value="1" class="checkbox-inline"/>
									<label>已批准</label>
									<input type="radio" id="verified" name="verified" value="0" class="checkbox-inline"/>
									<label>待审批</label>
									<input type="radio" id="verified" name="verified" value="2" class="checkbox-inline"/>
									<label>已关闭</label>
									<input type="radio" id="verified" name="verified" value="3"	class="checkbox-inline" checked>
									<label>所有</label>
									</c:if>
									
									<c:if test="${param.verified eq 1}">
									<input type="radio" id="verified" name="verified" value="1" class="checkbox-inline" checked />
									<label>已批准</label>
									<input type="radio" id="verified" name="verified" class="checkbox-inline" value="0" />
									<label>待审批</label>
									<input type="radio" id="verified" name="verified" class="checkbox-inline" value="2" />
									<label>已关闭</label>
									<input type="radio" id="verified" name="verified" class="checkbox-inline" value="3" />
									<label>所有</label>
									</c:if>
									
									<c:if test="${param.verified eq 0}">
									<input type="radio" id="verified" name="verified" value="1" class="checkbox-inline" />
									<label>已批准</label>
									<input type="radio" id="verified" name="verified" value="0" class="checkbox-inline" checked />
									<label>待审批</label>
									<input type="radio" id="verified" name="verified" value="2" class="checkbox-inline" />
									<label>已关闭</label>
									<input type="radio" id="verified" name="verified" value="3" class="checkbox-inline" />
									<label>所有</label>
									</c:if>
									
									<c:if test="${param.verified eq 2}">
									<input type="radio" id="verified" name="verified" value="1" class="checkbox-inline" />
									<label>已批准</label>
									<input type="radio" id="verified" name="verified" value="0" class="checkbox-inline" />
									<label>待审批</label>
									<input type="radio" id="verified" name="verified" value="2" class="checkbox-inline" checked />
									<label>已关闭</label>
									<input type="radio" id="verified" name="verified" value="3" class="checkbox-inline" />
									<label>所有</label>
									</c:if>
									
									<input type="submit" value="查询" class="btn btn-sm btn-info"/>
									<input type="reset" value="重置" class="btn btn-sm btn-default"/>
								</div>
							</form>
						</div>
					</div>
					
					<c:if test="${requestScope.search eq 1 }">
					<div class="panel panel-info">
						<div class="panel-heading">
							<h3 class="panel-title">
								查询结果
							</h3>
						</div>
						<div class="panel-body">
							<div>
								共<span class="label label-info">${requestScope.countOfUsers}</span>条结果，
								分成<span class="label label-info">${requestScope.countOfPages}</span>页显示，
								当前第<span class="label label-info">${requestScope.pageNum}</span>页
							</div>
							<!-- 
							<div class="header-nav">
								<input type="button" class="clickbutton" value="首页"
									onclick="window.location.href='SearchUsersServlet?name=${param.name}&username=${param.username}&verified=${param.verified}&pageNum=1'" />
								<c:if test="${requestScope.pageNum ne '1'}">
									<input type="button" class="clickbutton" value="上页"
										onclick="window.location.href='SearchUsersServlet?name=${param.name}&username=${param.username}&verified=${param.verified}&pageNum=${requestScope.pageNum-1}'" />
								</c:if>
								<c:if test="${requestScope.pageNum ne requestScope.countOfPages}">
									<input type="button" class="clickbutton" value="下页"
										onclick="window.location.href='SearchUsersServlet?name=${param.name}&username=${param.username}&verified=${param.verified}&pageNum=${requestScope.pageNum+1}'" />
								</c:if>
								<input type="button" class="clickbutton" value="末页"
									onclick="window.location.href='SearchUsersServlet?name=${param.name}&username=${param.username}&verified=${param.verified}&pageNum=${requestScope.countOfPages}'" />
								跳到第<input type="text" id="pageNum" name="pageNum"
									class="nav-number"  value=${param.pageNum}>页 <input type="button" class="clickbutton"
									value="跳转"
									onclick="goToOnePage('${param.name}','${param.username}','${param.verified}')" />
							</div>
							-->
						</div>
						<div class="table-responsive">
						<table class="table table-hover table-striped">
							<thead>
								<tr>
									<th>姓名</th>
									<th>账号名</th>
									<th class="hidden-xs">联系电话</th>
									<th class="hidden-xs">电子邮件</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach var="item" items="${requestScope.usersList}">
								<tr>
									<td>${item.name}</td>
									<td>${item.username}</td>
									<td class="hidden-xs">${item.phone}</td>
									<td class="hidden-xs">${item.email}</td>
									<c:if test="${item.verified eq 2 }">
										<td><button type="button" id="btnDoActionIt" class="btn btn-success" data-toggle="modal" data-target="#opencloseModal" data-uid="${item.uid}" data-act="enable">启用账号</button>
										</td>
									</c:if>
									<c:if test="${item.verified ne 2 }">
										<td><button type="button" id="btnDoActionIt" class="btn btn-danger" data-toggle="modal" data-target="#opencloseModal" data-uid="${item.uid}" data-act="close">关闭账号</button>
										</td>
									</c:if>
								</tr>
							</c:forEach>
							</tbody>
						</table>
						</div>
						<div class="text-center">
							<ul class="pagination pagination-centered">
								<li>
									<a href="SearchUsersServlet?name=${param.name}&username=${param.username}&verified=${param.verified}&pageNum=1">首页</a>
								</li>
								<c:if test="${requestScope.pageNum ne '1'}">
								<li>
									<a href="SearchUsersServlet?name=${param.name}&username=${param.username}&verified=${param.verified}&pageNum=${requestScope.pageNum-1}">上页</a>
								</li>
								</c:if>
								<c:if test="${requestScope.pageNum eq '1'}">
								<li class="disabled">
									<a>上页</a>
								</li>
								</c:if>								
								<%
								int tmpCp = Integer.parseInt(request.getAttribute("countOfPages").toString());
								int tmpCur = Integer.parseInt(request.getAttribute("pageNum").toString());
								for(int i=1;i<=tmpCp;i++){
									if(i==tmpCur){
								%>
								<li class="active">
									<a><%=i %></a>
								</li>
								<%
									}else{
								%>
								<li class="hidden-xs">
									<a href="SearchUsersServlet?name=${param.name}&username=${param.username}&verified=${param.verified}&pageNum=<%=i %>"><%=i %></a>
								</li>
								<%		
									}			
								}
								%>
								
								<c:if test="${requestScope.pageNum ne requestScope.countOfPages}">								
								<li>
									<a href="SearchUsersServlet?name=${param.name}&username=${param.username}&verified=${param.verified}&pageNum=${requestScope.pageNum+1}">下页</a>
								</li>
								</c:if>
								<c:if test="${requestScope.pageNum eq requestScope.countOfPages}">								
								<li class="disabled">
									<a>下页</a>
								</li>
								</c:if>
								
								<li>
									<a href="SearchUsersServlet?name=${param.name}&username=${param.username}&verified=${param.verified}&pageNum=${requestScope.countOfPages}">末页</a>
								</li>								
							</ul>
						</div>
					</div>
					</c:if>
				</div>
			</div>
			<%@include file="footer.jsp"%>
			</div>
		</div>
	</div>
</body>
</html>