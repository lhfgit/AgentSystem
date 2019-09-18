<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/inc/iframehead.jsp" %>
<div class="addAppTitle">
	<h2><font size="2">为${keywords.keywords }续费 </font>
<c:if test="${account != null }"><font color="red" size="2"> 
【当前账户余额：￥ <span id="accountspan">${account.money }</span>】</font></c:if>
</h2>
</div>
<div class="message">

</div>
<div class="formdiv">
	<ul>
		<li><a>客户名称：</a><input id="customname" class="customname" value="${keywords.customName }" type="text" readonly="readonly"/></li>
		<li><a>关键词：</a><input id="keyword" class="keyword" type="text" value="${keywords.keywords }" readonly="readonly"/> <span id="keywordtip" class="keywordtip"></span></li>
		<li><a>服务类别：</a>
			<select id="servicetype">
			<c:forEach items="${serviceType }" var="a">
			
					<option value="${a.id }" <c:if test="${keywords.productType == a.id }">selected="selected"</c:if>>${a.configTypeName }</option>
			</c:forEach>
				<%-- <s:iterator value="serviceType">
					<option value="<s:property value='id'/>" <s:if test="keywords.productType == id">selected="selected"</s:if>><s:property value="configTypeName"/></option>
				</s:iterator> --%>
			</select>
		</li>
		<li><a>服务年限：</a><input type="hidden" id="kid" value="${keywords.id }"/>
		<select id="serviceyears">
			<option value="0" selected="selected">--请选择--</option>
			<option value="1" >1年</option>
			<option value="2" >2年</option>
			<option value="3" >3年</option>
			<option value="4" >4年</option>
			<option value="5" >5年</option>
			<option value="6" >6年</option>
			<option value="7" >7年</option>
			<option value="8" >8年</option>
			<option value="9" >9年</option>
			<option value="10">10年</option>
			</select>
		</li>
		<li><a>价格：</a><input class="price" id="price" type="text" readonly="readonly"/></li>
		<li><input id="submitkeyword" type="button" value="续费提交"/></li>
	</ul>
</div>
<link id='theme' rel='stylesheet' href='${pageContext.request.contextPath }/css/xufei.css'/>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/xufei.js" charset="UTF-8"></script> 
</body></html>