<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>代理商管理系统</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/default.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/public.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/login.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
<!-- 页头 -->

<div class="head">
<h1>AgentSystem|代理商管理系统<span>v1.0</span></h1></div>
<!-- content -->
<div class="container">

<form action="login" method="post" onsubmit="return validateLoginUserFunc();">
	<h1>登录系统|Sing in</h1>
	<ul class="loginul">
	<li>登录账号:<input type="text" id="usercode" name="userCode"></li>
	<li>登录密码:<input type="password" id="userpassword" name="userPassword"></li>
	<li><input type="submit"  value="提交" /> </li>
	</ul>
</form>
</div>
<!-- 页尾 -->
 <jsp:include page="/inc/foot.jsp"></jsp:include> 
<div></div>
</body>
</html>