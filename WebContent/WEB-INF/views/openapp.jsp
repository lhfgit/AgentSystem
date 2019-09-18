<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/inc/iframehead.jsp"%>
<div class="addAppTitle">
	<h2>开通APP</h2>
</div>
<div class="message">
	<c:choose>
		<c:when test="${message=='恭喜开通App成功！' }">
			<font color="green">${message }</font>
		</c:when>
		<c:otherwise>
			<font color="red">${message }</font>
		</c:otherwise>
	</c:choose>
</div>
<div class="formdiv">
	<form action="${pageContext.request.contextPath }/modifyapp">
		<input type="hidden" name="id" value="${keywords.id }" /> <input
			type="hidden" name="openApp" value="1" /> <input type="hidden"
			id="a_keywords" name="keywords" value="${keywords.keywords }" /> <input
			type="hidden" id="a_customname" name="customName"
			value="${keywords.customName }" /> <input type="hidden" id="a_price"
			name="price" value="${keywords.price }" /> <input type="hidden"
			id="a_type" name="configTypeName"
			value="${systemconfig.configTypeName }" />
		<div>
			<ul>
				<li>登录账号：<input type="text" name="appUserName"
					value="${keywords.appUserName }" /></li>
				<li>登录密码：<input type="text" name="appPassword"
					value="${keywords.appPassword }" /></li>
				<li>关键词：<span id="s_key"></span></li>
				<li>企业名称：<span id="s_cn"></span></li>
				<li>价格：￥<span id="s_price"></span></li>
				<li>服务类型：<span id="s_type"></span></li>
			</ul>
		</div>
		<div class="clear"></div>
		<div class="submitdiv">
			<input type="submit" value="开通APP" />
		</div>
	</form>

</div>
<link id='theme' rel='stylesheet'
	href='${pageContext.request.contextPath }/css/openapp.css' />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/openapp.js" charset="UTF-8"></script>
</body>
</html>