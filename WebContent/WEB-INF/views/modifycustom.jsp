<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@include file="/inc/head.jsp"%>
<div class="mbxnav">
	<a href="${pageContext.request.contextPath }/main">代理商管理</a> \ <a
		href="${pageContext.request.contextPath }/customlist">代理商客户管理</a> \ <a
		href="${pageContext.request.contextPath }/modifycustom?custom.id=${custom.id}">修改客户信息</a>
</div>
<div class="container">
	<form id="cform"
		action="${pageContext.request.contextPath }/modifysavecustom"
		method="post">
		<input type="hidden" id="id" name="id" value="${custom.id}" /> <input
			type="hidden" name="cname" value="${custom.customName}" />
		<div class="subtitle">基本信息</div>

		<div class="info1">
			<ul>
				<li>企业名称： <input type="text" id="custom_name" name="customName"
					value="${custom.customName }" /> <span style="color: red;">*</span></li>
				<li>企业类型：<input id="customtypename" type="hidden"
					name="customTypeName" value="${custom.customTypeName}" /> <select
					id="selectcustomtype" name="customType">
						<option value="0" selected="selected">--请选择--</option>
						<c:if test="${customTypeList != null }">
							<c:forEach items="${customTypeList }" var="a">

								<option
									<c:if test="${custom.customType == a.configTypeValue }">
							selected = "selected"
							</c:if>
									value="${a.configTypeValue }">${a.configTypeName }</option>
							</c:forEach>
						</c:if>
				</select> <span style="color: red;">*</span></li>
				<li>企业主页：<input type="text" name="siteUrl"
					value="${custom.siteUrl }" /></li>
				<li>状态：<select name="customStatus">
						<c:if test="${custom.customStatus == 1 }">
							<option value="1" selected="selected">启用</option>
							<option value="0">停用</option>
						</c:if>
						<c:if test="${custom.customStatus == 0 }">
							<option value="0" selected="selected">停用</option>
							<option value="0">启用</option>
						</c:if>
				</select> <span style="color: red;">*</span></li>
			</ul>
			<div class="clear"></div>
		</div>
		<div class="subtitle">门户信息</div>
		<div class="info2">
			<ul>
				<li>法人代表：<input type="text" name="bossName"
					value="${custom.bossName }" /></li>
				<li>注册日期：<input class="Wdate" id="regdate" size="15"
					name="regDatetime"
					value='<fmt:formatDate value="${custom.regDatetime }" pattern="yyyy-MM-dd"/>'
					readonly="readonly" type="text" onClick="WdatePicker()" /></li>
				<li>证件类型：<input id="cardtypename" type="hidden"
					name="cardTypeName" value="${custom.cardTypeName }" /> <select
					id="selectcardtype" name="cardType">
						<option value="0" selected="selected">--请选择--</option>
						<c:if test="${cardTypeList != null}">
							<c:forEach items="${cardTypeList}" var="a">

								<option
									<c:if test="${custom.cardType == a.configTypeValue }">
							selected = "selected"
							</c:if>
									value="${configTypeValue}">${a.configTypeName}</option>
							</c:forEach>
						</c:if>
				</select></li>
				<li>证件号码：<input type="text" name="cardNum"
					value="${custom.cardNum}" /></li>
				<li>国家：<input type="text" name="country"
					value="${custom.country }" /></li>
				<li>省/地区：<select id="selectprovince" name="province">
						<c:if test="${custom.province== '0'}">
							<option value="0" selected="selected">--请选择--</option>
						</c:if>
						<c:if test="${provinceList != null}">
							<c:forEach items="${provinceList }" var="a">

								<c:if test="${custom.province == a.provinceID }">
									<option value="${a.provinceID }" selected="selected">${a.province}</option>
								</c:if>
								<c:if test="${custom.province != a.provinceID }">
									<option value="${a.provinceID }">${a.province}</option>
								</c:if>
							</c:forEach>
						</c:if>
				</select>
				</li>
				<li>公司传真：<input type="text" name="companyFax"
					value="${custom.companyFax }" /></li>

				<li>城市： <select id="selectcity" name="city">
						<c:if test="${custom.city == 0 }">
							<option value="0" selected="selected">--请选择--</option>
						</c:if>
						<c:if test="${cityList != null }">
							<c:forEach items="${cityList }" var="city">

								<c:if test="${custom.city eq city.cityID}">
									<option value="${city.cityID}" selected="selected">${city.city}</option>
								</c:if>
								<c:if test="${custom.city != city.cityID}">
									<option value="${city.cityID}">${city.city}</option>
								</c:if>
							</c:forEach>
						</c:if>
				</select>
				</li>
				<li>公司电话：<input type="text" name="companyTel"
					value="${custom.companyTel}" /></li>
				<li>区： <select id="selectarea" name="area">
						<c:if test="${custom.area == 0 }">
							<option value="0" selected="selected">--请选择--</option>
						</c:if>
						<c:if test="${areaList != null}">
							<c:forEach items="${areaList }" var="a">

								<c:if test="${custom.area == a.areaID}">
									<option value="${a.areaID}" selected="selected">${a.area}</option>
								</c:if>
								<c:if test="${custom.area != a.areaID }">
									<option value="${a.areaID}">${a.area}</option>
								</c:if>
							</c:forEach>
						</c:if>
				</select>
				</li>
				<li>公司地址：<input type="text" name="companyAddress"
					value="${custom.companyAddress}" /></li>
			</ul>
			<div class="clear"></div>
			<div>
				备注：
				<textarea rows="2" cols="50" name="memo">${custom.memo}</textarea>
			</div>
		</div>
		<div class="subtitle">
			<a href="javascript:void(0);" id="addcontact">添加一个联系人</a>
			<c:choose>
				<c:when test="${custom.constants ==null }">
					<input type="hidden" id="c_count" value="0" />
				</c:when>
				<c:otherwise>
					<input type="hidden" id="c_count" value="${fn:length(custom.constants)}" />
				</c:otherwise>
			</c:choose>
			<%-- <c:if test="${custom.constants ==null }">
				<input type="hidden" id="c_count" value="0" />
			</c:if>
			<c:if test="${custom.constants !=null }">
				<input type="hidden" id="c_count" value="${custom.constants.size }" />
			</c:if> --%>
		</div>
		<div class="info3">
			<table>
				<thead>
					<tr>
						<th>姓名</th>
						<th>电话</th>
						<th>传真</th>
						<th>邮箱</th>
						<th>职务</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="addtr">

					<c:if test="${custom.constants !=null }">
						<c:forEach items="${custom.constants }" var="a" varStatus="in">

							<tr>
								<td><input type="text"
									name="constants[${in.index }].contactName"
									value="${a.contactName}" /><span style="color: red;">*</span></td>
								<td><input type="text"
									name="constants[${in.index }].contactTel"
									value="${a.contactTel}" /><span style="color: red;">*</span></td>
								<td><input type="text"
									name="constants[${in.index }].contactFax"
									value="${a.contactFax}" /></td>
								<td><input type="text"
									name="constants[${in.index }].contactEmail"
									value="${a.contactEmail}" /></td>
								<td><input type="text"
									name="constants[${in.index }].contactRole"
									value="${a.contactRole}" /></td>
								<td onclick="getDel(this)"><a href='javascript:void(0);'>删除</a></td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${constants ==null }">没有联系人</c:if>
				</tbody>
			</table>
		</div>
		<div class="goback">
			<input type="button" value="保存" onclick="checksave();" /> <input
				type="button" onclick="window.history.back(-1);" value="返回" />
		</div>


	</form>


</div>
<link id='theme' rel='stylesheet'
	href='${pageContext.request.contextPath }/css/modifycustom.css' />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/modifycustom.js"
	charset="UTF-8"></script>
<jsp:include page="/inc/foot.jsp"></jsp:include>
</body>
</html>
