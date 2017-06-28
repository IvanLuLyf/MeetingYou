<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="meetingmanager.vo.*"%>
			<nav class="navbar navbar-default" role="navigation">
				<div class="navbar-header">
					 
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
						 <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
					</button> <a class="navbar-brand" href="#">Meeting You</a>
				</div>
				
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">个人中心<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li><a href="notifications.html">最新通知</a>
								</li>
								<li><a href="mybookings.html">我的预定</a>
								</li>
								<li><a href="mymeetings.html">我的会议</a>
								</li>
							</ul>
						</li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">人员管理<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li><a href="ViewAllDepartmentsServlet?code=viewalldepartments">部门管理</a>
								</li>
								<li><a href="ViewAllUsersServlet?code=approve">注册审批</a>
								</li>
								<li><a href="searchemployees.html">搜索员工</a>
								</li>
							</ul>
						</li>
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">会议预定<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li><a href="addmeetingroom.html">添加会议室</a>
								</li>
								<li><a href="meetingrooms.html">查看会议室</a>
								</li>
								<li><a href="bookmeeting.html">预定会议</a>
								</li>
							</ul>
						</li>							
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown">
							<%
							String nameString = (String)session.getAttribute("name");							
							%>
							<a href="#" class="dropdown-toggle" data-toggle="dropdown">欢迎您，<%=nameString%><strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li>
									<a href="changepassword.html">修改密码</a>
								</li>
								<li class="divider">
								</li>
								<li>
									<a href="#">退出</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</nav>