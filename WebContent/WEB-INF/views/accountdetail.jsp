<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="/inc/head.jsp"%>
<div class="mbxnav">
	<a href="main">代理商管理</a> \ <a href="main">当前账户信息</a> \ <a
		href="accountdetail">查看账户明细</a>
</div>
<div class="container">
	<!-- 
private Integer detailType;
	private String  detailTypeName;
	private BigDecimal money;
	private BigDecimal accountMoney;
	private String memo;
	private Date detailDateTime;
 -->
	<table>
		<thead>
			<tr>
				<th>序号</th>
				<th>账务类型</th>
				<th>账务资金</th>
				<th>账户余额</th>
				<th>备注信息</th>
				<th>明细时间</th>
			</tr>
			<c:if test=""></c:if>
		</thead>
		<tbody>

			<%-- <c:if test="page.items!=null">
		
		<c:forEach items="page.items" var="accountDetail">
		<tr>
		<td>
			<c:if test="1 >= page.page"><s:property value='#adIndex.index+1' /></c:if>
			<s:else>
			<s:property value='(page.page-1) * page.pageSize+#adIndex.index+1' />
			</s:else>
			</td>
			<td><s:property value='#accountDetail.detailTypeName' /></td>
			<td>￥<s:property value='#accountDetail.money' /></td>
			<td>￥<s:property value='#accountDetail.accountMoney' /></td>
			<td><s:property value='#accountDetail.memo' /></td>
			<td class="center"><s:date name='#accountDetail.detailDateTime' format="yyyy-MM-dd HH:mm:ss" /></td>
		</tr>
		</c:forEach>
	</c:if> --%>
			 <c:if test="${accountDetail.page.items !=null }"> 
				<c:forEach items="${accountDetail.page.items}" var="accountDetail">
					<tr>
						<td>${accountDetail.id}</td>
						<td>${accountDetail.detailTypeName}</td>
						<td>${accountDetail.money}</td>
						<td>${accountDetail.accountMoney}</td>
						<td>${accountDetail.memo}</td>
						<td>${accountDetail.id}<fmt:formatDate
								value="${accountDetail.detailDateTime}"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
					</tr>
				</c:forEach>

			</c:if>
		</tbody>
	</table>

	<div class="pagination pagination-centered">
		<ul>
			<li><a href="accountdetail?page.page=1" title="首页">首页</a></li>
			<%-- <s:if test="page.prevPages!=null">
								<s:iterator value="page.prevPages" var="num">
									<li><a
										href="/accountdetail.action?page.page=${num}"
										class="number" title="<s:property value='#num'/>"><s:property
											value="#num" /> </a></li>
								</s:iterator>
							</s:if> --%>
			<c:if test="${accountDetail.page.prevPages!=null}">
				<c:forEach items="${accountDetail.page.prevPages }" var="num">
					<li><a href="/accountdetail?page.page=${num}" class="number"
						title="${num }">${num}</a></li>
				</c:forEach>
			</c:if>
			<li class="active"><a href="#" title="${accountDetail.page.page}">${accountDetail.page.page}
				</a></li>
			
			<c:if test="${accountDetail.page.nextPages!=null}">
				
				<c:forEach items="${accountDetail.page.nextPages}" var="num">

					<li><a
						href="${pageContext.request.contextPath }/accountdetail?page.page=${num }">
							${num }</a></li>

				</c:forEach>
			</c:if>
			<li><a href="${pageContext.request.contextPath }/accountdetail?page.page=${accountDetail.page.pageCount}"
				title="尾页">尾页</a></li>
		</ul>
	</div>
</div>
<link id='theme' rel='stylesheet' href='css/accountdetail.css' />
<jsp:include page="/inc/foot.jsp"></jsp:include>
</body>
</html>