<%@ page language="java"
	import="java.util.*,meetingmanager.vo.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
			<div class="col-xs-12">
			<%@include file="header.jsp"%>
			<div class="row">
				<div class="modal fade" id="cancelModal" tabindex="-1" role="dialog" aria-labelledby="cancelModalLabel">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<form action="BookMeetingServlet?code=cancel" method="POST">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
								<h4 class="modal-title" id="cancelModalLabel">是否取消会议</h4>
							</div>
							<div class="modal-body">
								<input type="hidden" name="mid" value="${requestScope.meeting.meetingid}"/>
								<div class="form-group">
									<label for="message-text" class="control-label">取消原因:</label>
									<textarea class="form-control" id="reason" name="reason"></textarea>
								</div>
							</div>
							<div class="modal-footer">
								<input type="submit" value="取消会议" class="btn btn-danger" ></input>
								<button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
							</div>
							</form>
						</div>
					</div>
				</div>
				<div class="modal fade" id="enableModal" tabindex="-1" role="dialog" aria-labelledby="enableModalLabel">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<form action="BookMeetingServlet?code=enable" method="POST">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
								<h4 class="modal-title" id="cancelModalLabel">恢复会议</h4>
							</div>
							<div class="modal-body">
								<input type="hidden" name="mid" value="${requestScope.meeting.meetingid}"/>
								是否恢复该会议
							</div>
							<div class="modal-footer">
								<input type="submit" value="恢复会议" class="btn btn-success" ></input>
								<button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
							</div>
							</form>
						</div>
					</div>
				</div>
				<div class="col-xs-2">
				<%@include file="sidebar.jsp" %>
				</div>
				<div class="col-xs-10">
					<ul class="breadcrumb">
						<li>
							<a href="#">Meeting You</a> <span class="divider"></span>
						</li>
						<li>
							<a href="#">会议预定</a> <span class="divider"></span>
						</li>
						<li class="active">
							<a href="#">会议信息</a>
						</li>
					</ul>
					<c:if test="${requestScope.msg!=null}">
					<div class="alert alert-success alert-dismissible" role="alert">
						<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<strong>提示</strong> ${requestScope.msg}
					</div>
					</c:if>
					<div class="panel panel-info">
						<div class="panel-heading">
							<h3 class="panel-title">
								会议信息
							</h3>
						</div>
						<div class="panel-body">
							<table class="table table-responsive table-hover table-striped">
								<tr>
									<td>会议名称</td>
									<td>${requestScope.meeting.name}</td>
								</tr>
								<tr>
									<td>预计参加人数</td>
									<td>${requestScope.meeting.capacity}</td>
								</tr>
								<tr>
									<td>预计开始时间</td>
									<td><fmt:formatDate pattern="yyyy-MM-dd hh:mm" value="${requestScope.meeting.begintime}" /></td>
								</tr>
								<tr>
									<td>预计结束时间</td>
									<td><fmt:formatDate pattern="yyyy-MM-dd hh:mm" value="${requestScope.meeting.endtime}" /></td>
								</tr>
								<tr>
									<td>会议说明</td>
									<td>${requestScope.meeting.description}</td>
								</tr>
							</table>
							<c:if test="${requestScope.isMine!=null && requestScope.meeting.status==0}">
							<button class="btn btn-danger"  data-toggle="modal" data-target="#cancelModal">取消会议</button>
							</c:if>
							<c:if test="${requestScope.isMine!=null && requestScope.meeting.status==-1}">
							<button class="btn btn-success"  data-toggle="modal" data-target="#enableModal">恢复会议</button>
							</c:if>
							<button class="btn btn-info" onclick="window.history.back();">返回</button>
						</div>
					</div>
					
					<div class="panel panel-info">
						<div class="panel-heading">
							<h3 class="panel-title">
								参会人员
							</h3>
						</div>
						<div class="panel-body">
							<table class="table table-responsive table-hover table-striped">
								<thead>
									<tr>
										<th>姓名</th>
										<th>联系电话</th>
										<td>电子邮件</td>
									</tr>
								</thead>
								
								<tbody>
									<c:forEach var="memberid" items="${requestScope.members}">
									<tr>
										<td>${requestScope.users[memberid].name}</td>
										<td>${requestScope.users[memberid].phone}</td>
										<td>${requestScope.users[memberid].email}</td>
									</tr>
									</c:forEach>
								</tbody>
							</table>
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