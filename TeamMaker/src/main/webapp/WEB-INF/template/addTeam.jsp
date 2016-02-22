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
					href="/index"><img style="float: left;"
					src="assets/images/poli_logo_poli.png" height="50px" alt="logo" />
					<span class="navbar-title">Mobile Application Development</span></a> <a
					class="navbar-brand visible-xs hidden-sm hidden-md hidden-lg"
					href="/index"><img style="float: left;"
					src="assets/images/poli_logo_poli.png" height="35px" alt="logo" />
					<span class="navbar-title">MAD</span></a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="/">Teams</a></li>
					<li class="active"><a href="/addTeam">Add team</a></li>
				</ul>
			</div>
		</div>
	</header>
	<!--/header-->
	<section>
		<div class="container">
			<div class="row">
				<h2>Create new team</h2>
				<h4>Insert your studentId and those of yours teammate. Or if
					you prefer looking for them using their lastname.</h4>
				<h5>Remember! Just one component of your team must perform this
					operation. If you or your teammate are already in a team, you'll
					not be able to add it.</h5>
				<hr>
				<div class="center">
					<div class="row" style="padding-bottom: 50px;">
						<select id="firstAttendee" class="js-basic-single"	style="width: 50%;">
							<option value=""></option>
							<c:forEach items="${users}" var="u">
								<option value="${u.id}">${u.studentId} - ${u.lastname} ${u.firstname}</option>
							</c:forEach>
						</select>
					</div>
					<div class="row" style="padding-bottom: 50px;">
						<select id="secondAttendee" class="js-basic-single"	style="width: 50%;">
							<option value=""></option>
							<c:forEach items="${users}" var="u">
								<option value="${u.id}">${u.studentId} - ${u.lastname} ${u.firstname}</option>
							</c:forEach>
						</select>
					</div>
					<div class="row" style="padding-bottom: 50px;">
						<select id="thirdAttendee" class="js-basic-single"	style="width: 50%;">
							<option value=""></option>
							<c:forEach items="${users}" var="u">
								<option value="${u.id}">${u.studentId} - ${u.lastname} ${u.firstname}</option>
							</c:forEach>
						</select>
					</div>
					<div class="row" style="padding-bottom: 50px;">
						<select id="fourthAttendee" class="js-basic-single"	style="width: 50%;">
							<option value=""></option>
							<c:forEach items="${users}" var="u">
								<option value="${u.id}">${u.studentId} - ${u.lastname} ${u.firstname}</option>
							</c:forEach>
						</select>
					</div>
					<div class="row">
						<form id="sendForm" style="display: none; margin-top:20px;"
							action="/createTeam" method="POST">
							<div id="inputContainer" class="row">
								<label>Group name</label>
								<input name="groupName" type="text" placeholder="Insert your group name here (optional)"
									style="width:30%;"/>
								<input id="a1" name="firstAttendee" type="hidden"/>
								<input id="a2" name="secondAttendee" type="hidden"/>
								<input id="a3" name="thirdAttendee" type="hidden"/>
								<input id="a4" name="fourthAttendee" type="hidden"/>
							</div>
							<div class="row">
								<button class="btn btn-primary" type="submit">Create</button>
							</div>
						</form>
					</div>
				</div>


			</div>
		</div>
	</section>



	<script type="text/javascript">
		var old1 = {
				value : undefined,
				text : undefined
		};
		var old2 = {
				value : undefined,
				text : undefined
		};
		var old3 = {
				value : undefined,
				text : undefined
		};
		var old4 = {
				value : undefined,
				text : undefined
		};
		
		$("#firstAttendee").select2({
			placeholder: "Select first team's attendee"
		});
		$("#secondAttendee").select2({
			placeholder: "Select second team's attendee",
			disabled: true
		});
		$("#thirdAttendee").select2({
			placeholder: "Select third team's attendee",
			disabled: true
		});
		$("#fourthAttendee").select2({
			placeholder: "Select fourth team's attendee",
			disabled: true
		});
		
		var $eventFirst = $("#firstAttendee");
		var $eventSecond = $("#secondAttendee");
		var $eventThird = $("#thirdAttendee");
		var $eventFourth = $("#fourthAttendee");

		$(document).ready(function() { 
			console.log("Initialize");
			$eventFirst.val(null);
			$eventSecond.val(null);
			$eventThird.val(null);
			$eventFourth.val(null);
		});
		
		$eventFirst.on("select2:select", function (e) {
			$eventSecond.prop("disabled", false);
			$("#secondAttendee option[value='"+e.params.data.id+"']").remove();
			$("#thirdAttendee option[value='"+e.params.data.id+"']").remove();
			$("#fourthAttendee option[value='"+e.params.data.id+"']").remove();
			if(old1.value){
				$("#secondAttendee").append('<option value="'+old1.value+'">'+old1.text+'</option>');
				$("#thirdAttendee").append('<option value="'+old1.value+'">'+old1.text+'</option>');
				$("#fourthAttendee").append('<option value="'+old1.value+'">'+old1.text+'</option>');
			}
			old1.value=e.params.data.id;
			old1.text=e.params.data.text;
			document.getElementById('a1').value = e.params.data.id;
		});
		
		$eventSecond.on("select2:select", function (e) {
			$eventThird.prop("disabled", false);
			$("#firstAttendee option[value='"+e.params.data.id+"']").remove();
			$("#thirdAttendee option[value='"+e.params.data.id+"']").remove();
			$("#fourthAttendee option[value='"+e.params.data.id+"']").remove();
			if(old2.value){
				$("#firstAttendee").append('<option value="'+old2.value+'">'+old2.text+'</option>');
				$("#thirdAttendee").append('<option value="'+old2.value+'">'+old2.text+'</option>');
				$("#fourthAttendee").append('<option value="'+old2.value+'">'+old2.text+'</option>');
			}
			old2.value=e.params.data.id;
			old2.text=e.params.data.text;
			document.getElementById('a2').value = e.params.data.id;
		});
		
		$eventThird.on("select2:select", function (e) {
			$eventFourth.prop("disabled", false);
			$("#firstAttendee option[value='"+e.params.data.id+"']").remove();
			$("#secondAttendee option[value='"+e.params.data.id+"']").remove();
			$("#fourthAttendee option[value='"+e.params.data.id+"']").remove();
			if(old3.value){
				$("#firstAttendee").append('<option value="'+old3.value+'">'+old3.text+'</option>');
				$("#secondAttendee").append('<option value="'+old3.value+'">'+old3.text+'</option>');
				$("#fourthAttendee").append('<option value="'+old3.value+'">'+old3.text+'</option>');
			}
			old3.value=e.params.data.id;
			old3.text=e.params.data.text;
			document.getElementById('a3').value = e.params.data.id;
		});
		
		$eventFourth.on("select2:select", function (e) {
			$("#firstAttendee option[value='"+e.params.data.id+"']").remove();
			$("#secondAttendee option[value='"+e.params.data.id+"']").remove();
			$("#thirdAttendee option[value='"+e.params.data.id+"']").remove();
			if(old4.value){
				$("#firstAttendee").append('<option value="'+old4.value+'">'+old4.text+'</option>');
				$("#secondAttendee").append('<option value="'+old4.value+'">'+old4.text+'</option>');
				$("#thirdAttendee").append('<option value="'+old4.value+'">'+old4.text+'</option>');
			}
			old4.value=e.params.data.id;
			old4.text=e.params.data.text;
			document.getElementById('a4').value = e.params.data.id;
			document.getElementById('sendForm').style.display = 'block';
		});
		
		
	</script>
</body>
</html>