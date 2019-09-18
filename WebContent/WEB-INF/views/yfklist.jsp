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
<%-- <link id='theme' rel='stylesheet'href='${pageContext.request.contextPath }/css/yfk.css' /> --%>
</head>
<body>
	<div class="mbxnav">账号：【${user.userCode}】代理商预付款</div>
	<div class="container">
		<div class="searchuserdiv ope">
			<ul>
				<li>
					<form action="${pageContext.request.contextPath }/yfklist"
						method="post" onsubmit="return searchyfklistFunc();">
						<input type="hidden" id="usercode" name="userCode"
							value="${user.userCode }"> <input type="hidden"
							id="userid" name="userId" value="${user.id }"> 操作类型： <select
							name="detailType">
							<option value="">---请选择---</option>
							<option
								<c:if test="${accountDetail.detailType == 9999}">
					selected = "selected"
				</c:if>
								value="9999">系统自动-关键词申请扣款</option>
							<option
								<c:if test="${accountDetail.detailType == 9998 }">
					selected = "selected"
				</c:if>
								value="9998">系统自动-返回预注册冻结资金</option>
							<option
								<c:if test="${accountDetail.detailType == 9997 }">
					selected = "selected"
				</c:if>
								value="9997">系统自动-扣除申请关键词的所有资金</option>
							<option
								<c:if test="${accountDetail.detailType == 9996 }">
					selected = "selected"
				</c:if>
								value="9996">系统自动-扣除关键词续费资金</option>
							<c:forEach items="${accountConfigList }" var="a">

								<option
									<c:if test="${accountDetail.detailType == a.id }">
					selected = "selected"
				</c:if>
									value="${a.id }">${a.configTypeName }</option>
							</c:forEach>
						</select> 操作时间： <input class="Wdate" size="15" name="startTime"
							id="starttime"
							value='<fmt:formatDate value="${accountDetail.startTime }" pattern="yyyy-MM-dd"/>'
							readonly="readonly" type="text" onClick="WdatePicker()" /> 至 <input
							class="Wdate" size="15" name="endTime" id="endtime"
							value='<fmt:formatDate value="${accountDetail.endTime }" pattern="yyyy-MM-dd"/>'
							readonly="readonly" type="text" onClick="WdatePicker()" /> <input
							type="submit" value="查询" />
					</form>
				</li>
			</ul>
		</div>


		<table>
			<thead>
				<tr>
					<th>账务类型</th>
					<th>账务资金</th>
					<th>账户余额</th>
					<th>备注信息</th>
					<th>明细时间</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${accountDetail.page.items!=null }">
					<c:forEach items="${accountDetail.page.items }" var="a"
						varStatus="i">

						<tr>
							<td><c:choose>
									<c:when test="${1>=accountDetail.page.page }">${i.index+1 }</c:when>
									<c:otherwise>${(accountDetail.page.page-1)*accountDetail.page.pageSize+i.index+1 }</c:otherwise>
								</c:choose></td>
							<td>${a.detailTypeName }</td>
							<td>￥${a.money }</td>
							<td>￥${a.accountMoney }</td>
							<td>${a.memo }</td>
							<td class="center"><fmt:formatDate
									value="${a.detailDateTime }" pattern="yyy-MM-dd" /></td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>

		<div class="pagination pagination-centered">
			<ul>
				<li><a
					href="${pageContext.request.contextPath }/yfklist?page.page=1&id=${user.id }&userCode=${user.userCode }&detailType=${accountDetail.detailType }&startTime=<fmt:formatDate value="${accountDetail.startTime }" pattern="yyyy-MM-dd"/>&endTime=<fmt:formatDate value="${accountDetail.endTime }" pattern="yyyy-MM-dd"/>"
					title="首页">首页</a></li>
				<c:if test="${accountDetail.page.prevPages!=null }">
					<c:forEach var="num" items="${accountDetail.page.prevPages }">
						<li><a
							href="${pageContext.request.contextPath }/yfklist?page.page=${num}&id=${user.id }&userCode=${user.userCode }&detailType=${accountDetail.detailType }&startTime=<fmt:formatDate value="${accountDetail.startTime }" pattern="yyyy-MM-dd"/>&endTime=<fmt:formatDate value="${accountDetail.endTime }" pattern="yyyy-MM-dd"/>">
								class="number" title="${num }">${num }</a></li>


					</c:forEach>
				</c:if>
				<li class="active"><a href="#"
					title="${accountDetail.page.page }">${accountDetail.page.page }
				</a></li>
				<c:if test="${accountDetail.page.nextPages!=null }">
					<c:forEach var="num" items="${accountDetail.page.nextPages }">
						<li><a
							href="${pageContext.request.contextPath }/yfklist?page.page=${num}&id=${user.id }&userCode=${user.userCode }&detailType=${accountDetail.detailType }&startTime=<fmt:formatDate value="${accountDetail.startTime }" pattern="yyyy-MM-dd"/>&endTime=<fmt:formatDate value="${accountDetail.endTime }" pattern="yyyy-MM-dd"/>"
							title="${num }"> ${num } </a></li>
					</c:forEach>
				</c:if>
				<li><a
					href="${pageContext.request.contextPath }/yfklist?page.page=${page.pageCount}&id=${user.id }&userCode=${user.userCode }&detailType=${accountDetail.detailType }&startTime=<fmt:formatDate value="${accountDetail.startTime }" pattern="yyyy-MM-dd"/>&endTime=<fmt:formatDate value="${accountDetail.endTime }" pattern="yyyy-MM-dd"/>"
					title="尾页">尾页</a></li>
			</ul>
		</div>
	</div>

	 <link id='theme' rel='stylesheet'
		href='${pageContext.request.contextPath }/css/yfk.css' /> 

	<script type="text/javascript"
		src="${pageContext.request.contextPath }/medire/WdatePicker.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/js/yfklist.js"></script>

</body>
</html>