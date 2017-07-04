<%@ page language="java"
	import="java.util.*,meetingmanager.vo.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
</head>
<body>
    <div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12">
			<%@include file="header.jsp"%>
			<div class="row">
				<div class="hidden-xs col-sm-4 col-md-2">
				<%@include file="sidebar.jsp" %>
				</div>
				<div class="col-xs-12 col-sm-8 col-md-10">
					<ul class="breadcrumb">
						<li>
							<a href="#">Meeting You</a> <span class="divider"></span>
						</li>
						<li>
							<a href="#">会议预定</a> <span class="divider"></span>
						</li>
						<li class="active">
							<a href="#">修改会议室信息</a>
						</li>
					</ul>
					<div class="panel panel-info">
						<div class="panel-heading">
							<h3 class="panel-title">
								会议室信息
							</h3>
						</div>
						<div class="panel-body">
							<c:if test="${requestScope.msg!=null}">
							<div id="myAlert" class="alert alert-danger">
								<a href="#" class="close" data-dismiss="alert">&times;</a>
								<strong>提示信息:</strong>${requestScope.msg}
							</div>
							</c:if>
							<c:if test="${requestScope.room!=null}">
							<form class="form-horizontal" role="form" action="UpdateMeetingRoomServlet" method="post">
								<input type="hidden" name="roomid" value='${requestScope.room.roomid}'>
								<div class="form-group">
									<label class="control-label col-lg-2" for="roomnumber">门牌号</label>
									<div class="col-lg-4">
									<input id="roomnumber" name="roomnumber" type="text" placeholder="例如：201" maxlength="10" class="form-control" value="${requestScope.room.number}" required/>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-lg-2" for="roomname">会议室名称</label>
									<div class="col-lg-4">
									<input id="roomname" name="roomname" type="text" placeholder="例如：第一会议室" maxlength="20" class="form-control" value="${requestScope.room.name}" required/>
									</div>
								</div>
										
								<div class="form-group">
									<label class="control-label col-lg-2" for="roomcapacity">最多容纳人数</label>
									<div class="col-lg-4">
									<input id="roomcapacity" name="roomcapacity" type="text" placeholder="填写一个正整数" class="form-control" value="${requestScope.room.capacity}" required/>
									</div>
								</div>
								<c:if test="${requestScope.room.status==1}">
								<div class="form-group">
									<label class="control-label col-lg-2" for="roomname">当前状态</label>
									<div class="col-lg-4">
										<label class="checkbox-inline">
										<input type="radio" id="status" name="status" checked="checked" value="1">启用
										</label>
										<label class="checkbox-inline">
										<input type="radio" id="status" name="status" value="0">停用
										</label>
										<label class="checkbox-inline">
										<input type="radio" id="status" name="status" value="-1">删除
										</label>
									</div>
								</div>
								</c:if>
								
								<c:if test="${requestScope.room.status==0}">
								<div class="form-group">
									<label class="control-label col-lg-2" for="roomname">当前状态</label>
									<div class="col-lg-4">
										<label class="checkbox-inline">
										<input type="radio" id="status" name="status" value="1">启用
										</label>
										<label class="checkbox-inline">
										<input type="radio" id="status" name="status" checked="checked" value="0">停用
										</label>
										<label class="checkbox-inline">
										<input type="radio" id="status" name="status" value="-1">删除
										</label>
									</div>
								</div>
								</c:if>

								<c:if test="${requestScope.room.status==-1}">
								<div class="form-group">
									<label class="control-label col-lg-2" for="roomname">当前状态</label>
									<div class="col-lg-4">
										<label class="checkbox-inline">
										<input type="radio" id="status" name="status" value="1">启用
										</label>
										<label class="checkbox-inline">
										<input type="radio" id="status" name="status" value="0">停用
										</label>
										<label class="checkbox-inline">
										<input type="radio" id="status" name="status" checked="checked" value="-1">删除
										</label>
									</div>
								</div>
								</c:if>
																
								<div class="form-group">
									<label class="control-label col-lg-2" for="description">备注</label>
									<div class="col-lg-4">
									<textarea class="form-control" id="description" name="description" maxlength="200" rows="5" cols="60" placeholder="200字以内的文字描述">${requestScope.room.description}</textarea>
									</div>
								</div>
								
								<div class="form-group">
									<label class="control-label col-lg-2"></label>
									<div class="col-lg-2">
									<button class="btn btn-success btn-block" type="submit">修改</button>
									</div>
									<div class="col-lg-2">
									<button class="btn btn-default btn-block" type="reset">重置</button>
									</div>
								</div>
							</form>
							</c:if>
						</div>
					</div>
				</div>
			</div>
			<%@include file="footer.jsp"%>
			</div>
		</div>
	</div>
</body>
</html>