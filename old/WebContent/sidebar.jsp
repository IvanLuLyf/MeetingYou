<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
int roleid = -1;
if(session.getAttribute("roleid")!=null){
	roleid = (Integer)session.getAttribute("roleid");
}
%>
					<div class="panel panel-info">
						<div class="panel-heading">
							<h3 class="panel-title">
								个人中心
							</h3>
						</div>
						<div class="panel-body">
							<ul class="nav nav-list">
								<li><a href="NotificationServlet">最新通知</a>
								</li>
								<li><a href="MyBookingServlet">我的预定</a>
								</li>
								<li><a href="MyMeetingServlet">我的会议</a>
								</li>
							</ul>
						</div>
					</div>
	<%
	if(roleid==1){
	%>
					<div class="panel panel-info">
						<div class="panel-heading">
							<h3 class="panel-title">
								人员管理
							</h3>
						</div>
						<div class="panel-body">
							<ul class="nav nav-list">
								<li><a href="ViewAllDepartmentsServlet?code=viewalldepartments">部门管理</a>
								</li>
								<li><a href="ViewAllUsersServlet?code=approve">注册审批</a>
								</li>
								<li><a href="searchusers.jsp">搜索员工</a>
								</li>
							</ul>
						</div>
					</div>
	<%} %>
						<div class="panel panel-info">
						<div class="panel-heading">
							<h3 class="panel-title">
								会议预定
							</h3>
						</div>
						<div class="panel-body">
							<ul class="nav nav-list">
								<%
								if(roleid==1){
								%>
								<li><a href="addmeetingroom.jsp">添加会议室</a>
								</li>
								<%} %>
								<li><a href="ViewAllRoomsServlet?code=viewall">查看会议室</a>
								</li>
								<li><a href="BookMeetingServlet?code=prepare">预定会议</a>
								</li>
							</ul>
						</div>
					</div>	