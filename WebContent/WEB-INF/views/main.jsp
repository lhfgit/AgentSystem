<%@ page language="java" import="java.util.*,org.agent.pojo.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="/inc/head.jsp" %>
<div class="mbxnav">
	<a href="${pageContext.request.contextPath }/main">代理商管理</a> \ <a href="${pageContext.request.contextPath }/main">当前账户信息</a>
</div>
<div class="container">

<div class="userInfo">
	<ul>
		<li><b>您好，${sessionScope.userSession.userName}!</b> 您上次登录时间 <fmt:formatDate value="${sessionScope.userSession.lastLoginTime}" pattern="yyyy-MM-dd HH:mm:ss "/> </li>
		<li>
		您.当前账户余额：
	<c:if test="${account == null }">对不起，您还没有开启账户，或者您的账户的资金出现了问题，请联系系统管理员进行处理。</c:if>
	
	<c:if test="${account != null }">￥${account.money }</c:if>
	<a href="${pageContext.request.contextPath }/accountdetail">查看账户明细</a>
	</li>
	</ul>
	
</div>

</div>
<jsp:include page="/inc/foot.jsp"></jsp:include>
</body>
</html>