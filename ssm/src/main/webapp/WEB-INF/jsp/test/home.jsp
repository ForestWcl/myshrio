<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.4.js"></script>
<link href="${pageContext.request.contextPath }/css/login.css" rel="StyleSheet" type="text/css">
<title>Insert title here</title>
</head>
<body>
	<img src="${pageContext.request.contextPath }/ss/image/qq.png">
<h2>HOME</h2>
	<div>
		填入参数：<input type="text" id="in">
		<button>测试AJAX请求</button>
	</div>
	<table>
		<tr>
			<td>编号</td>
			<td>用户名</td>
			<td>密码</td>
			<td>年龄</td>
		</tr>
		<tr id="tr">
			
		</tr>
	</table>
</body>
<script type="text/javascript">
		$(function(){
			$("button").click(function(){
				var para = $("#in").val();
				if(para!='1'&& para!='2'){
					alert("只能填入1或2")
					return ;
				}
				$.post("/ssm/user/getUser",
						{
						id:$("#in").val(),
						},
						function(data){
							var p = data.info.data[0];
							$("#tr").html("<td>"+p.id+"</td><td>"+p.userName+
											"</td><td>"+p.passWord+"</td><td>"+p.age+"</td>");
						}
				)
			})
		})
	</script>
</html>