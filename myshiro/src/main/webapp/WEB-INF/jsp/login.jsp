<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Login Page</h2>
	<form action="" method="POST">
		username:<input type="text" name="j_username">
		<br><br>
		password:<input type="password" name="j_password">
		<br><br>
		vaildataCode:<input name="j_random" id="j_random" type="text" >
				<img style='cursor:pointer;' onclick='changeImg(this)' border=0 src='${pageContext.request.contextPath}/randomImage' title='点击切换验证码'>
		<br><br>
		remember Me<input type="checkbox" name="rememberMe">
		<input type="submit" value="submit"> <label style="color: red">${error}</label>
	</form>
</body>
<script  type="text/javascript">
	function changeImg(img){
		img.src = "${pageContext.request.contextPath}/randomImage?" + Math.random() * 10000; 
	}
</script>
</html>