<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/inc/head.jsp"%>
<div class="mbxnav">
	<a href="/main.action">系统管理</a> \ <a
		href="${pageContext.request.contextPath }/premission">角色权限管理</a>
</div>
<div class="container">
	<ul>
		<!-- 角色列表 -->
		<li class="jslist">
			<ul>
				<c:forEach items="${roleList }" var="role">
				<li>
				<div id="sidebar">
				<a target="functionlist" href="${pageContext.request.contextPath }/functionlist/${role.id}">${role.roleName }</a>
				</div>
				</li>
				</c:forEach>
			</ul>
		</li>
		<!-- 功能类表 -->
		<li class="iframeli">
		<iframe id="functionlist" name="functionlist" width="100%"
				height="99%" src=""></iframe></li>
	</ul>



</div>
<jsp:include page="/inc/foot.jsp"></jsp:include>
<link id='theme' rel='stylesheet' href='${pageContext.request.contextPath }/css/premission.css'/>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/premission1.js"></script>
</body>
</html>