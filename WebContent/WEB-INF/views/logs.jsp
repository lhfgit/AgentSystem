<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/inc/head.jsp" %>
<div class="mbxnav">
	<a href="${pageContext.request.contextPath }/main">代理商管理</a> \ <a href="${pageContext.request.contextPath }/mylogs">查看操作日志</a>
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
		  <th>用户名称</th>
		  <th>操作信息</th>
		  <th>操作时间</th>
	  </tr>
  </thead>   
  <tbody>
	<c:if test="${logs.page.items!=null }">
	<c:forEach items="${logs.page.items }" var="a" varStatus="i">
		
		<tr>
		<td>
			<c:choose>
		<c:when test="${1>=accountDetail.page.page }">
		${i.index+1 }
		</c:when>
		<c:otherwise>
		${(accountDetail.page.page-1)*accountDetail.page.pageSize+i.index+1 }
		</c:otherwise>
		</c:choose>
			</td>
			<td>${a.userName }</td>
			<td>${a.operateInfo }</td>
			<td class="center"><fmt:formatDate value="${a.operateDatetime }" pattern="yyyy-MM-dd  HH:mm:ss"/> </td>
		</tr>
		</c:forEach>
	</c:if>
</tbody>
</table>

<div class="pagination pagination-centered">
						  <ul>
							<li><a href="${pageContext.request.contextPath }/mylogspage.page=1" title="首页">首页</a></li>
							<c:if test="${logs.page.prevPages!=null }">
							<c:forEach items="${logs.page.prevPages}" var="num">
								
								
									<li><a
										href="${pageContext.request.contextPath }/mylogs?page.page=${num}"
										class="number" title="${num }">${num } </a></li>
								</c:forEach>
							</c:if>
							<li class="active">
							  <a href="#"title="${logs.page.page }">${logs.page.page } </a>
							</li>
							<c:if test="${logs.page.nextPages!=null }">
							<c:forEach items="${logs.page.nextPages}" var="num">
								
									<li><a href="${pageContext.request.contextPath }/mylogs?page.page=${num }" title="${num }">
									${num } </a></li>
								</c:forEach>
							</c:if>
							<li><a href="${pageContext.request.contextPath }/mylogs?page.page=${logs.page.pageCount}" title="尾页">尾页</a></li>
						  </ul>
						</div>
</div>
<link id='theme' rel='stylesheet' href='${pageContext.request.contextPath }/css/logs.css'/>
<jsp:include page="/inc/foot.jsp"></jsp:include>
</body>
</html>