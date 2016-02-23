<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MAD - TeamMaker</title>
<link href="/assets/css/bootstrap.min.css" rel="stylesheet">
<link href="/assets/css/font-awesome.min.css" rel="stylesheet">
<link href="/assets/css/custom.css" rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.2-rc.1/css/select2.min.css"
	rel="stylesheet" />
<script src="/assets/js/jquery.js"></script>
<script src="/assets/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.2-rc.1/js/select2.min.js"></script>
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
					<li><a href="/">Teams</a></li>
					<li><a href="/addTeam">Add team</a></li>
				</ul>
			</div>
		</div>
	</header>
	<!--/header-->
	<section>
		<div class="container">
			<div class="row">
				<c:choose>
					<c:when test="${groupNumber>'0'}">
						<h2>Your attendance to group was confirmed.</h2>
						<br>
						<h2>Team created!</h2>
						<h4>Your team has been correctly registered in the system. Your team number is ${groupNumber}.</h4>
						<h4>Visit <a href="/">home page</a> to see all registered teams</h4>
					</c:when>
					<c:otherwise>
						<h2>Your attendance to group was confirmed.</h2>
						<c:choose>
							<c:when test="${missingAttendees=='1'}">
								<h4>In order to complete group subscription the last attendee should confirm</h4>
							</c:when>
							<c:otherwise>
								<h4>In order to complete group subscription, ${missingAttendees} more attendees need to confirm</h4>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</section>

</body>
</html>