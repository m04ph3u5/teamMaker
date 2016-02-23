<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MAD - TeamMaker</title>
<link href="/assets/css/bootstrap.min.css" rel="stylesheet">
<link href="/assets/css/font-awesome.min.css" rel="stylesheet">
<link href="/assets/css/custom.css" rel="stylesheet">

</head>
<body>
	<header style="height: 80px;"
		class="navbar navbar-inverse navbar-fixed-top wet-asphalt"
		role="banner">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand hidden-xs visible-sm visible-md visible-lg"
					href="/"><img style="float: left;"
					src="assets/images/poli_logo_poli.png" height="50px" alt="logo" />
					<span class="navbar-title">Mobile Application Development</span></a> <a
					class="navbar-brand visible-xs hidden-sm hidden-md hidden-lg"
					href="/"><img style="float: left;"
					src="assets/images/poli_logo_poli.png" height="35px" alt="logo" />
					<span class="navbar-title">MAD</span></a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav navbar-right">
					<li class="active"><a href="/">Teams</a></li>
					<li><a href="/addTeam">Add team</a></li>
				</ul>
			</div>
		</div>
	</header>
	<!--/header-->
	<section>
		<div class="container">
			<div class="row">
				<h2>Teams</h2>
				last update
				<%=new java.util.Date()%>
				<hr>
				<c:if test="${groups.size()==0}">
					<h4>No team has been created yet.</h4>
				</c:if>
				<h5>
					<a href="/addTeam">Add new team <img src="assets/images/plus.png" style="width:100px;"></a>
				</h5>
				<c:if test="${groups.size()>0}">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>Team number</th>
								<th>Team name</th>
								<th>Attendees</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${groups}" var="g">
								<tr>
									<td>${g.number}</td>
									<c:if test="${!g.groupName.isEmpty()}">
										<td>${g.groupName}</td>
									</c:if>
									<c:if test="${g.groupName.isEmpty()}">
										<td><b>-</b></td>
									</c:if>
									<td><c:forEach items="${g.members}" var="m">
											<span>${m.studentId} - ${m.lastname} ${m.firstname}</span>
											<br>
										</c:forEach></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
			</div>
		</div>
	</section>
	<script src="/assets/js/jquery.js"></script>
	<script src="/assets/js/bootstrap.min.js"></script>
</body>
</html>