<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/inc/head.jsp"%>
<div class="mbxnav">
	<a href="/main.action">代理商管理</a> \ <a href="/customlist">代理商客户管理</a> \
	<a href="/viewcustom?id=${custom.id}">查看客户信息</a>
</div>
<div class="container">

	<div class="subtitle">基本信息</div>
	<div class="info1">
		<ul>
			<li>企业名称：${custom.customName}</li>
			<li>状态：<c:if test="${custom.customStatus == 1}">
					<font color="green">启用</font>
				</c:if> <c:if test="${custom.customStatus == 0}">
					<font color="red">停用</font>
				</c:if>
			</li>
			<li>企业类型：${custom.customTypeName}</li>
			<li>企业主页：${custom.siteUrl}</li>
		</ul>
		<div class="clear"></div>
	</div>
	<div class="subtitle">门户信息</div>
	<div class="info2">
		<ul>
			<li>法人代表：${custom.bossName}</li>
			<li>注册日期：<fmt:formatDate value="${custom.regDatetime }"
					pattern="yyyy-MM-dd" /></li>
			<li>证件类型：${custom.cardTypeName}</li>
			<li>证件号码：${custom.cardNum}</li>
			<li>国家：${custom.country}</li>
			<li>省/地区： <c:if test="${provinceList !=null }">
					<c:forEach items="${provinceList}" var="a">

						<c:if test="${custom.province == a.provinceID }">
		        	 	    ${a.province }
		        	 	  </c:if>
					</c:forEach>
				</c:if>
			</li>
			<li>区： <c:if test="${areaList !=null}">
					<c:forEach items="${areaList}" var="b">
						<c:if test="${custom.area == b.areaID }">
		        	 	    ${b.area}
		        	 	  </c:if>
					</c:forEach>
				</c:if>
			</li>
			<li>城市： <c:if test="${cityList != null }">
					<c:forEach items="${cityList}" var="c">

						<c:if test="${custom.city == c.cityID }">
		        	 	    ${c.city }
		        	 	  </c:if>
					</c:forEach>
				</c:if>
			</li>
			<li>公司电话：${custom.companyTel }</li>
			<li>公司传真：${custom.companyFax}</li>
			<li>公司地址：${custom.companyAddress}</li>
			<li>备注：${custom.memo}</li>
		</ul>
		<div class="clear"></div>
	</div>

	<div class="subtitle">联系人信息</div>
	<div class="info3">
		<ul>
			<c:if test="${custom.constants !=null}">
				<c:forEach items="${custom.constants}" var="a">
            	
            		<li>
						<div>姓名：${a.contactName}</div>
						<div>电话：${a.contactTel}</div>
						<div>传真：${a.contactFax}</div>
						<div>邮箱：${a.contactEmail}</div>
						<div>职务：${a.contactRole}</div>
					</li>
				</c:forEach>
			</c:if>
			<c:if test="${custom.constants ==null }">
				<li>没有任何联系人</li>
			</c:if>
		</ul>
		<div class="clear"></div>
	</div>
	<div class="goback">
		<input type="button" onclick="window.history.back(-1);" value="返回" />
	</div>
</div>
<link id='theme' rel='stylesheet'
	href='${pageContext.request.contextPath }/css/viewcustom.css' />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/viewcustom.js"
	charset="UTF-8"></script>
<jsp:include page="/inc/foot.jsp"></jsp:include>
</body>
</html>
