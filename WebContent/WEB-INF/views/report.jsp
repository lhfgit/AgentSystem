<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/inc/head.jsp"%>
<div class="mbxnav">
	<a href="${pageContext.request.contextPath }/report">报表管理</a> \ <a
		href="${pageContext.request.contextPath }/report">报表管理</a>
</div>
<div class="container">
	<div class="searchuserdiv ope">
		<ul>
			<li>
				<form action="${pageContext.request.contextPath }/report"
					method="post" onsubmit="return searchReportFunc();">

					操作类型： <select name="reportType" id="reporttype">
						<option value="999"
							style="background: #333333; padding: 3px; color: #fff;">财务报表</option>
						<option value="1"
							<c:if test="${reportType == 1 }">selected="selected"</c:if>
							style="padding: 3px;">&nbsp;&nbsp;&nbsp;&nbsp;代理商账户余额报表</option>
						<option value="2"
							<c:if test="${reportType == 2 }">selected="selected"</c:if>
							style="padding: 3px;">&nbsp;&nbsp;&nbsp;&nbsp;预付款流水报表</option>
						<option value="3"
							<c:if test="${reportType == 3 }">selected="selected"</c:if>
							style="padding: 3px;">&nbsp;&nbsp;&nbsp;&nbsp;代理商流水报表</option>
						<option value="998"
							style="background: #333333; padding: 3px; color: #fff;">产品报表</option>
						<option value="4"
							<c:if test="${reportType == 4 }">selected="selected"</c:if>
							style="padding: 3px;">
							&nbsp;&nbsp;&nbsp;&nbsp;产品分类数量/金额汇总</option>
						<option value="997"
							style="background: #333333; padding: 3px; color: #fff;">消费报表</option>
						<option value="6"
							<c:if test="${reportType == 5 }">selected="selected"</c:if>
							style="padding: 3px;">
							&nbsp;&nbsp;&nbsp;&nbsp;客户消费汇总(暂无)</option>
					</select>
					<div id="opertime" style="margin-top: -20px; margin-left: 280px;">
						操作时间： <input class="Wdate" size="15" name="startTime"
							id="starttime"
							value='<fmt:formatDate value="${startTime }" pattern="yyyy-MM-dd"/> '
							readonly="readonly" type="text" onClick="WdatePicker()" /> 至 <input
							class="Wdate" size="15" name="endTime" id="endtime"
							value="<fmt:formatDate value="${endTime }" pattern="yyyy-MM-dd"/>"
							readonly="readonly" type="text" onClick="WdatePicker()" />
					</div>
					<div style="margin-top: -20px; margin-left: 580px;">
						<input type="submit" value="查询" />
					</div>
				</form>
			</li>
		</ul>
	</div>

	<c:if test="${reportType == 1 }">
		<%-- <div class="downloadfile">
			<ul>
				<li><img src="${pageContext.request.contextPath }/imgs/pdf.png" /><a
					href="${pageContext.request.contextPath }/reportaccount">PDF下载</a></li>
				<li><img
					src="${pageContext.request.contextPath }/imgs/excel.png" /><a
					href="${pageContext.request.contextPath }/reportaccounte">Excel下载</a></li>
			</ul>
		</div> --%>
		<h1>代理商余额报表</h1>
		<table>
			<thead>
				<tr>
					<th>序号</th>
					<th>代理商名称</th>
					<th>账户余额</th>
				</tr>
			</thead>
			<tbody>
			
			
					<c:forEach items="${accountList }" var="account" varStatus="i">

						<tr>
							<td>${i.index+1 }</td>
							<td>${account.userName}</td>
							<td>￥${account.money}</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>

	<c:if test="${reportType == 2 }">
		<h1>预付款流水报表</h1>
		<table>
			<thead>
				<tr>
					<th>序号</th>
					<th>代理商名称</th>
					<th>预付款</th>
					<th>账户余额</th>
					<th>备注信息</th>
					<th>时间</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${accountDetailList !=null }">
					<c:forEach items="${accountDetailList }" var="a" varStatus="i">
						<tr>
							<td>${i.index+1 }</td>
							<td>${a.userName }</td>
							<td>${a.money}</td>
							<td>${a.accountMoney }</td>
							<td>${a.memo}</td>
							<td><fmt:formatDate value="${a.detailDateTime }"
									pattern="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</c:if>
	<c:if test="${reportType == 3 }">
		<h1>预付款流水报表</h1>
		<table>
			<thead>
				<tr>
					<th>序号</th>
					<th>代理商名称</th>
					<th>预付款</th>
					<th>账户余额</th>
					<th>备注信息</th>
					<th>时间</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${accountDetailList !=null }">
					<c:forEach items="${accountDetailList }" var="a" varStatus="i">
						<tr>
							<td>${i.index+1 }</td>
							<td>${a.userName }</td>
							<td>${a.money}</td>
							<td>${a.accountMoney }</td>
							<td>${a.memo}</td>
							<td><fmt:formatDate value="${a.detailDateTime }"
									pattern="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</c:if>
	<c:if test="${reportType == 4 }">
		<h1>产品分类数量/金额汇总</h1>
		<table>
			<thead>
				<tr>
					<th>序号</th>
					<th>产品分类名称</th>
					<th>数量</th>
					<th>价格</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${reportProductList!=null }">
					<c:forEach items="${reportProductList }" var="a" varStatus="i">
						<tr>
							<td>${i.index+1 }</td>
							<td>${a.configTypeName }</td>
							<td>${a.number }</td>
							<td>${a.price }</td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	</c:if>







</div>
<link id='theme' rel='stylesheet'
	href='${pageContext.request.contextPath }/css/report.css' />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/report.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/medire/WdatePicker.js"></script>
<jsp:include page="/inc/foot.jsp"></jsp:include>
</body>
</html>