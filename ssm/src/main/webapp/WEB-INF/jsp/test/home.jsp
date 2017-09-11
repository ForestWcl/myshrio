<%@ page language="java" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.7.2.min.js " type="text/javascript"></script>
<title>Insert title here</title>
</head>
<body>
<h2>HOME</h2>
	<div>
		<button>go</button>
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
				$.post("/ssm/user/getUser",
						{
						id:"1",
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