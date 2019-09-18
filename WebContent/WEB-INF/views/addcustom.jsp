<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@include file="/inc/head.jsp"%>
<div class="mbxnav">
	<a href="/main">代理商管理</a> \ <a href="/customlist">代理商客户管理</a> \ <a
		href="/addcustom?custom.id=<s:property value='custom.id'/>">添加客户信息</a>
</div>
<div class="container">
	<form id="cform"
		action="${pageContext.request.contextPath }/addsavecustom"
		method="post">
		<div class="subtitle">基本信息</div>
		<div class="info1">
			<ul>
				<li>企业名称： <input type="text" name="customName" id="custom_name" />
					<span style="color: red;">*</span></li>
				<li>企业类型：<input id="customtypename" type="hidden"
					name="customTypeName" value="" /> <select id="selectcustomtype"
					name="customType">
						<option value="0" selected="selected">--请选择--</option>
						<c:if test="${customTypeList != null }">
							<c:forEach items="${customTypeList }" var="a">
								<option value="${a.configTypeValue }">${a.configTypeName }</option>
							</c:forEach>
						</c:if>
				</select> <span style="color: red;">*</span></li>
				<li>企业主页：<input type="text" name="siteUrl" /></li>
				<li>状态：<select name="customStatus">
						<option value="1" selected="selected">启用</option>
						<option value="0">停用</option>
				</select> <span style="color: red;">*</span></li>
			</ul>
			<div class="clear"></div>
		</div>
		<div class="subtitle">门户信息</div>
		<div class="info2">
			<ul>
				<li>法人代表：<input type="text" name="bossName" /></li>
				<li>注册日期：<input class="Wdate" id="regdate" size="15"
					name="regDatetime" value="" readonly="readonly" type="text"
					onClick="WdatePicker()" /></li>
				<li>证件类型：<input id="cardtypename" type="hidden"
					name="cardTypeName" value="" /> <select id="selectcardtype"
					name="cardType">
						<option value="0" selected="selected">--请选择--</option>
						<c:if test="${cardTypeList != null }">
							<c:forEach items="${cardTypeList }" var="c">
								<option value="${c.configTypeValue }">${c.configTypeName }</option>
							</c:forEach>
						</c:if>
				</select></li>
				<li>证件号码：<input id="card_num" type="text" name="cardNum" /></li>
				<li>国家：<input type="text" name="country" value="中国" /></li>
				<li>省/地区：<select id="selectprovince" name="province">
						<option value="0" selected="selected">--请选择--</option>
						<c:if test="${provinceList != null }">
							<c:forEach items="${provinceList}" var="p">
								<option value="${p.provinceID } ">${p.province }</option>
							</c:forEach>
						</c:if>
				</select>
				</li>
				<li>公司传真：<input type="text" name="companyFax" /></li>
				<li>城市： <select id="selectcity" name="city">
						<option value="0" selected="selected">--请选择--</option>
				</select>
				</li>
				<li>公司电话：<input type="text" name="companyTel" /></li>
				<li>区： <select id="selectarea" name="area">
						<option value="0" selected="selected">--请选择--</option>
				</select>
				<li>公司地址：<input type="text" name="companyAddress" /></li>
			</ul>
			<div class="clear"></div>
			<div>
				备注：
				<textarea rows="2" cols="50" name="memo"></textarea>
			</div>
		</div>
		<div class="subtitle">
			<a href="javascript:void(0);" id="addcontact">添加一个联系人</a>
			<c:choose>
				<c:when test="${contactList == null }">
					<input type="hidden" id="c_count" value="0" />
				</c:when>
				<c:otherwise>
					<input type="hidden" id="c_count" value="${contactList.size }" />
				</c:otherwise>
			</c:choose>
			<%--  <s:if test="contactList == null"><input type="hidden" id="c_count" value="0"/></s:if>
          <s:else><input type="hidden" id="c_count" value="<s:property value="contactList.size()"/>"/></s:else></div> --%>
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
					</tbody>
				</table>
			</div>
			<div class="goback">
				<input type="button" value="保存" onclick="checksave();" /> <input
					type="button" onclick="window.history.back(-1);" value="返回" />
			</div>
			</div>
	</form>
</div>
<link id='theme' rel='stylesheet'
	href='${pageContext.request.contextPath }/css/addcustom.css' />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/addcustom.js"
	charset="UTF-8"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/medire/WdatePicker.js"></script>
<jsp:include page="/inc/foot.jsp"></jsp:include>
</body>
</html>
