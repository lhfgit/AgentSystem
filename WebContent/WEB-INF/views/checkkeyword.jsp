<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@include file="/inc/head.jsp" %>
<div class="mbxnav">
	<a href="javascript:void();">系统管理</a> \ <a href="/checkkeyword">关键词审核</a>
</div>
<div class="container">

<h2>关键词审核</h2>

<div class="searchuserdiv ope">
	<ul>
		<li>
		<form action="${pageContext.request.contextPath }/checkkeyword" method="post">
		关键词：<input type="text" value="${keywords.keywords }" name="keywords"/>
		<input type="submit" value="查询"/> 
		<span class="okflow">审核流程：已申请（代理商申请） 》 审核中 》 通过 》 续费 </span> | 
		<span class="noflow">审核流程：已申请（代理商申请） 》 审核中 》 不通过</span>
		</form>
		</li>
	</ul>
</div>


 <table>
  <thead>
	  <tr>
		  <th>序号</th>
		  <th>关键词</th>
		  <th>客户名称</th>
		  <th>代理商</th>
		  <th>申请年限</th>
		  <th>申请日期</th>
		  <th>到期日期</th>
		  <th>申请到期状态</th>
		  <th>审核状态</th>
		  <th>使用状态</th>
		  <th>操作</th>
	  </tr>
  </thead>   
  <tbody>
	
			<c:if test="${keywords.page.items!=null }">
				<c:forEach items="${keywords.page.items}" var="a" varStatus="i">

					<tr class="tabletr">
						<td><c:choose>
								<c:when test="${1 >= keywords.page.page }">${i.index+1 }</c:when>
								<c:otherwise>${(keywords.page.page-1)*keywords.page.pageSize+i.index+1 }</c:otherwise>

							</c:choose></td>
						<td>${a.keywords }</td>
						<td>${a.customName }</td>
						<td>${a.agentName }</td>
						<td>${a.serviceYears }</td>
						<td><fmt:formatDate value="${a.regDatetime }"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td><fmt:formatDate value="${a.regPassDatetime }"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>

						<td><c:if test="${a.isPass == 0 }">
								<font color="green">未过期</font>
							</c:if> <c:if test="${a.isPass == 1 }">

								<font color="red">预注册过期</font>
							</c:if>
							<c:if test="${a.isPass == 2 }">
								<font color="red">过期</font>
							</c:if></td>
						<td><c:if test="${a.checkStatus == 0 }">

								<font color="green">已申请</font>
							</c:if>
							<c:if test="${a.checkStatus == 1 }">

								<font color="green">审核中</font>
							</c:if>
							<c:if test="${a.checkStatus == 2 }">

								<font color="green">已通过</font>
							</c:if>
							<c:if test="${a.checkStatus == 3 }">

								<font color="red">未通过</font>
							</c:if></td>
						<td><c:if test="${a.isUse == 0 }">
								<font color="red">未使用</font>
							</c:if>
							<c:if test="${a.isUse == 1 }">
								<font color="green">已使用</font>
							</c:if></td>
						<%-- <td><c:if test="${a.openApp != 1 }">
								<font color="red">未开通</font>
							</c:if>
							<c:if test="${a.openApp == 1 }">
								<font color="green">已开通</font>
							</c:if></td> --%>
			<td>
				<select class="checkselect" kid="${a.id }" keyword="${a.keywords }">
					<option value="0">--请选择--</option>
					<c:if test="${a.checkStatus == 0 && a.isUse == 1 }">
						<option value="1">审核中</option>
					</c:if>
					<c:if test="${a.checkStatus == 1 && a.isUse == 1 }">
						<option value="2">审核通过</option>
						<option value="3">不通过</option>
					</c:if>
					<c:if test="${a.checkStatus == 2 && a.isUse == 1 }">
						<option value="4">续费</option>
					</c:if>
					<c:if test="${a.isUse == 1 }">
						<option value="5">不使用</option>
					</c:if>
					<c:if test="${a.isUse == 0 }">
						<option value="6">使用</option>
					</c:if>
				</select>
			</td>
		</tr>
		</c:forEach>
	</c:if>
</tbody>
</table>
<div class="pagination pagination-centered">
		<ul>
			<li><a
				href="${pageContext.request.contextPath }/checkkeyword?page.page=1&keywords=${keywords.keywords }"
				title="首页">首页</a></li>
			<c:if test="${keywords.page.prevPages!=null }">
			<c:forEach var="num" items="${keywords.page.prevPages }">
				
					<li><a
						href="${pageContext.request.contextPath }/checkkeyword?page.page=${num}&keywords=${keywords.keywords }"
						class="number" title="${num}">${num}</a></li>
				</c:forEach>
			</c:if>
			<li class="active"><a href="#"
				title="${keywords.page.page }">${keywords.page.page } </a></li>
			<c:if test="page.nextPages!=null">
				<c:forEach var="num" items="${keywords.page.nextPages }">
					<li><a
						href="${pageContext.request.contextPath }/checkkeyword?page.page=${num}&keywords=${keywords.keywords }"
						title="${num }">${num } 
					</a></li>
				</c:forEach>
			</c:if>
			<li><a
				href="${pageContext.request.contextPath }/checkkeyword?page.page=${keywords.page.pageCount}&keywords=${keywords.keywords }"
				title="尾页">尾页</a></li>
		</ul>
	</div>
<%-- <div class="pagination pagination-centered">
						  <ul>
							<li><a href="/checkkeyword.action?page.page=1&keywords.keywords=<s:property value='keywords.keywords'/>" title="首页">首页</a></li>
							<s:if test="page.prevPages!=null">
								<s:iterator value="page.prevPages" var="num">
									<li><a
										href="/checkkeyword.action?page.page=${num}&keywords.keywords=<s:property value='keywords.keywords'/>"
										class="number" title="<s:property value='#num'/>"><s:property
											value="#num" /> </a></li>
								</s:iterator>
							</s:if>
							<li class="active">
							  <a href="#"title="<s:property value='page.page'/>"><s:property value='page.page' /> </a>
							</li>
							<s:if test="page.nextPages!=null">
								<s:iterator value="page.nextPages" var="num">
									<li><a href="/checkkeyword.action?page.page=${num}&keywords.keywords=<s:property value='keywords.keywords'/>" title="<s:property value='#num'/>">
									<s:property value="#num" /> </a></li>
								</s:iterator>
							</s:if>
							<li><a href="/checkkeyword.action?page.page=${page.pageCount}&keywords.keywords=<s:property value='keywords.keywords'/>" title="尾页">尾页</a></li>
						  </ul>
						</div> --%>
</div>
<link id='theme' rel='stylesheet' href='${pageContext.request.contextPath }/css/checkkeyword.css'/>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/checkkeyword.js" charset="UTF-8"></script> 
<jsp:include page="/inc/foot.jsp"></jsp:include>
</body>
</html>