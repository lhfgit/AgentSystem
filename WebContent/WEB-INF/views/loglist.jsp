<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<link href="${pageContext.request.contextPath }/css/public.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath }/css/main.css"
	rel="stylesheet" />
<link rel="stylesheet" id='skin' type="text/css"
	href="${pageContext.request.contextPath }/alertframe/skin/simple_gray/ymPrompt.css" />
<link id='theme' rel='stylesheet'
	href='${pageContext.request.contextPath }/humane/themes/original.css' />
<!-- jQuery -->
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/alertframe/ymPrompt.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/humane/humane.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/public.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/main.js" charset="UTF-8"></script>
</head>
<body>
	<!-- ${sessionScope.userSession.userName} -->
	<div class="mbxnav">账号：【${logs.userName}】LOG
		操作日志</div>
	<div class="container">
		<div class="searchuserdiv ope">
			<ul>
				<li>
					<form action="${pageContext.request.contextPath }/loglist"
						method="get">
						<input type="hidden" id="usercode" name="userCode"
							value="${logs.userName}"> <input type="hidden"
							id="userid" name="id" value="${logs.userId}"> 操作时间：
						<!-- <input class="Wdate" size="15" name="operateDatetime" value="<s:date name='logs.operateDatetime' format='yyyy-MM-dd'/>" readonly="readonly"  type="text" onClick="WdatePicker()" /> -->
						<input class="Wdate" size="15" name="operateDatetime"
							value='<fmt:formatDate value="${logs.operateDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>'
							readonly="readonly" type="text" onClick="WdatePicker()" />
						<!-- <input type="date" name="operateDatetime"> -->
						<input type="submit" value="查询" />
					</form>
				</li>
			</ul>
		</div>
		
		<table>
			<thead>
				<tr>
					<th>登陆账户</th>
					<th>操作信息</th>
					<th>操作时间</th>
				</tr>


			</thead>
			<tbody>
				<c:forEach items="${logs.page.items }" var="logs">
					<tr>
						<td>${logs.userName }</td>
						<td>${logs.operateInfo }</td>
						
						<td><fmt:formatDate value="${logs.operateDatetime}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<div class="pagination pagination-centered">
			<ul>
				<%-- <c:forEach begin="1" end="${page.pageCount }" varStatus="status"
					var="i">
		  ${i }
		  </c:forEach> --%>

				<li><a
					href="${pageContext.request.contextPath }/loglist?page.page=1&id=${logs.userId }&userCode=${logs.userName }&operateDatetime=<fmt:formatDate value="${logs.operateDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					title="首页">首页</a></li>

				<c:if test="${logs.page.prevPages!=null}">

					<c:forEach items="${logs.page.prevPages}" var="num">
						<li><a
							href="${pageContext.request.contextPath }/loglist?page.page=${num}&id=${logs.userId }&userCode=${logs.userName }&operateDatetime=<fmt:formatDate value="${logs.operateDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
							class="number" title="${num }">${num } </a></li>
					</c:forEach>

				</c:if>
				<li class="active"><a href="#" title="${logs.page.page}">${logs.page.page}
				</a></li>
				<c:if test="${logs.page.nextPages!=null}">
					<c:forEach items="${logs.page.nextPages}" var="num">

						<li><a
							href="${pageContext.request.contextPath }/loglist?page.page=${num }&id=${logs.userId }&userCode=${logs.userName }&operateDatetime=<fmt:formatDate value="${logs.operateDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>">
								${num }</a></li>

					</c:forEach>
				</c:if>
				<li><a
					href='${pageContext.request.contextPath }/loglist.action?page.page=${page.pageCount}&id=${logs.userId }&userCode=${logs.userName }&operateDatetime=<fmt:formatDate value="${logs.operateDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>'
					title="尾页">尾页</a></li>
			</ul>

		</div>
	</div>
	<link id='theme' rel='stylesheet'
		href='${pageContext.request.contextPath }/css/loglist.css' />
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/medire/WdatePicker.js"></script>
</body>
</html>