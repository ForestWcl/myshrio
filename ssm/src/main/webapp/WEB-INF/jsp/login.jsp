<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!doctype html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>登录</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/styles.css">
</head>
<body>
<div class="htmleaf-container">
	<div class="wrapper">
		<div class="container">
			<h1>Welcome</h1>
			
			<form class="form" action="${pageContext.request.contextPath }/sys/loginCheck" method="POST">
				<input type="text" placeholder="Username" name="userName">
				<input type="password" placeholder="Password" name="passWord">
				<button type="submit" id="login-button">Login</button>
				<div>
				 <label style="color:red;font-family: 微软雅黑;font-size:small;">${ msg }</label>
				</div>
			</form>
		</div>
		
		<ul class="bg-bubbles">
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
		</ul>
	</div>
</div>

<script src="${pageContext.request.contextPath }/js/jquery-1.12.4.js" type="text/javascript"></script>
<script>
$('#login-buttonn').click(function (event) {
	event.preventDefault();
	$('form').fadeOut(500);
	$('.wrapper').addClass('form-success');

});
</script>

<div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';color:#000000">
<h1>数据管理系统</h1>
</div>
</body>
</html>