<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/inc/head.jsp" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

<div class="mbxnav">
	<a href="javascript:void();">系统配置管理</a> \ 
	<c:if test="${configType == 1}">
		<a href="/caiwutype.action">账务类型</a>
	</c:if>
	<c:if test="${configType == 2}">
		<a href="/servicetype.action">服务类型</a>
	</c:if>
	<c:if test="${configType == 3}">
		<a href="/serviceyears.action">服务年限</a>
	</c:if>
	<c:if test="${configType == 4}">
		<a href="/appurl.action">APP地址</a>
	</c:if>
	<c:if test="${configType == 5}">
		<a href="/customtype.action">客户类型</a>
	</c:if>
	<c:if test="configType == 6">
		<a href="/cardtype.action">证件类型</a>
	</c:if>
	<c:if test="${configType == 7}">
		<a href="/youhuitype.action">优惠类型</a>
	</c:if>
	
</div>
<div class="container">
<c:if test="${configType == 1 || configType == 2 || configType == 5 || configType == 6 || configType == 7}">
<div id="modifySystemdiv" class="modifySystemdiv modifyback">
	<ul>
		<li class="lititle"><b>您正在进行修改操作</b></li>
		<li>
		<input type="hidden" id="sid"/>
		<input type="hidden" id="modifyConfigType" value="${configType }"/>
		类型名称：<input type="text" id="configName"/></li>
		<c:if test="${configType == 7 || configType == 2}">
			<li>类型数值：<input type="text" id="modifyConfigValue"/></li>
		</c:if>
		<c:if test="${configType == 7}">
			<li>实际数值：<input type="text" id="modifyConfigTypeValue"/></li>
		</c:if>
		<li>是否启用：<span id="isStart">
		<select id="modifyisStartSelect">
			<option value="1">启用</option>
			<option value="2">不启用</option>
		</select> 
		</span> 
		<input type="button" id="modifySystemConfigBtn" value="保存"/> 
		<input type="button" id="cancleModifySystemConfigBtn" value="取消"/> 
		</li>
	</ul>
</div>
<div id="addSystemdiv" class="modifySystemdiv addback">
	<ul>
		<li class="lititle"><b>您正在进行添加操作</b></li>
		<li>
		<input type="hidden" id="addConfigType" value="${configType }"/>
		类型名称：<input type="text" id="addConfigName"/></li>
		<c:if test="${configType == 7 || configType == 2}">
			<li>类型数值：<input type="text" id="addConfigValue"/></li>
		</c:if>
		<c:if test="${configType == 7}">
			<li>实际数值：<input type="text" id="addConfigTypeValue"/></li>
		</c:if>
		<li>是否启用：
		<select id="addisStartSelect">
			<option value="1">启用</option>
			<option value="2">不启用</option>
		</select> 
		<input type="button" id="addSystemConfigBtn" value="保存"/> 
		<input type="button" id="cancleAddSystemConfigBtn" value="取消"/> 
		</li>
	</ul>
</div>
</c:if>

	<c:if test="${configType == 1}">
	
		<div>
		<div class="addSystemConfig">
			<input type="button" id="addsystemconfig" value="添加账务类型"/>
		</div>
			<table>
			  <thead>
				  <tr>
					  <th>序号</th>
					  <th>配置类型</th>
					  <th>是否启用</th>
					  <th colspan="2">操作</th>                                          
				  </tr>
			  </thead>   
			  <tbody>
			 <c:forEach items="${systemConfigs}" var="systemconfig" begin="0"  varStatus="index">
			  	<tr>
					 <td>${index.index+1}</td>
					<td>${systemconfig.configTypeName }</td>
					<td><c:if test="${systemconfig.isStart == 1}"><font color="green">启用</font></c:if>
					<c:if test="${ systemconfig.isStart == 2}"><font color="red">未启用</font></c:if>  </td>
					<td colspan="2">
						<span class="modifySystemBtn" sid="${systemconfig.id }" configtype="${systemconfig.configType }/>" sname="${systemconfig.configTypeName }" isStart="${systemconfig.isStart }"><a>修改</a></span> | 
						<span class="deleteSystemBtn" sname="${systemconfig.configTypeName }" sid="${systemconfig.id }"><a>删除</a></span>
					</td>
				<tr>
				</c:forEach>
			</table>
		</div>		

	</c:if>
	<c:if test="${configType == 2}">
		<div>
		<div class="addSystemConfig">
			<input type="button" id="addsystemconfig" value="添加服务类型"/>
		</div>
			<table>
			  <thead>
				  <tr>
					  <th>序号</th>
					  <th>配置类型</th>
					  <th>配置数值</th>
					  <th>是否启用</th>
					  <th colspan="2">操作</th>                                          
				  </tr>
			  </thead>   
			  <tbody>
			 
			   <c:forEach items="${systemConfigs}" var="systemconfig" begin="0"  varStatus="index">
			  	<tr>
					<td>${index.index+1}</td>
					<td>${systemconfig.configTypeName}</td>
					<td><c:if test="${ systemconfig.isStart == 1}"><font color="green">启用</font></c:if>
					<c:if test="${ systemconfig.isStart == 2}"><font color="red">未启用</font></c:if>  </td>
					<td colspan="2">
						<span class="modifySystemBtn" sid="${systemconfig.id}" configtype="${configType}" sname="${systemconfig.configTypeName}" isStart="${systemconfig.isStart}"><a>修改</a></span> | 
						<c:if test="${systemconfig.id!=12 &&systemconfig.id!=13}">
						<span class="deleteSystemBtn" sname="${systemconfig.configTypeName}" sid="${systemconfig.id}"><a>删除</a></span>
						</c:if>
					</td>
				<tr>
				</c:forEach>
			</table>
		</div>		
	</c:if>
	<c:if test="${configType == 3}">
		<div class="simpleconfig">
		<H3>服务年限管理-填写最大的服务年限</H3>
			<input type="hidden" id="configType" value="${configType}"/>
			<ul>
				<li>配置名称：<input type="text" id="simpTypeName" value="${systemconfig.configTypeName}" disabled="disabled"/></li>
				<li>配置数值：<input type="text" id="simpConfigValue" value="${systemconfig.configValue}"/></li>
				<li>
				<input type="hidden" id="simpleId" value="${systemconfig.id}"/>
				<input type="button" id="simpleBtn" value="保存"/></li>
			</ul>
		</div>
	</c:if>
	<c:if test="${configType == 4}">
		<div class="simpleconfig">
		<H3>APP URL管理-填写制作APP的系统的URL地址</H3>
			<input type="hidden" id="configType" value="${configType}"/>
			<ul>
				<li>配置名称：<input type="text" id="simpTypeName" value="${systemconfig.configTypeName}"/></li>
				<li>配置数值：<input type="text" id="simpConfigValue" value="${systemconfig.configValue}"/></li>
				<li>
				<input type="hidden" id="simpleId" value="${systemconfig.id}"/>
				<input type="button" id="simpleBtn" value="保存"/></li>
			</ul>
		</div>
	</c:if>
	<c:if test="${configType == 5}">
		<div>
		<div class="addSystemConfig">
			<input type="button" id="addsystemconfig" value="添加客户类型"/>
		</div>
			<table>
			  <thead>
				  <tr>
					  <th>序号</th>
					  <th>配置类型</th>
					  <th>是否启用</th>
					  <th colspan="2">操作</th>                                          
				  </tr>
			  </thead>   
			  <tbody>
			  
			  <c:forEach items="${systemConfigs}" var="systemconfig" begin="0"  varStatus="index">
			  	<tr>
					<td>${index.index+1}</td>
					<td>${systemconfig.configTypeName }</td>
					<td><c:if test="${systemconfig.isStart == 1}"><font color="green">启用</font></c:if>
					<c:if test="${systemconfig.isStart == 2}"><font color="red">未启用</font></c:if>  </td>
					<td colspan="2">
						<span class="modifySystemBtn" sid="${systemconfig.id}" configtype="${systemconfig.configType}" sname="${systemconfig.configTypeName}" isStart="${systemconfig.isStart}"><a>修改</a></span> | 
						<c:if test="${systemconfig.id !=12 &&systemconfig.id !=13}">
						<span class="deleteSystemBtn" sname="${systemconfig.configTypeName}" sid="${systemconfig.id}"><a>删除</a></span>
						</c:if>
					</td>
				</tr>
				</c:forEach>
			</table>
		</div>		
	</c:if>
	<c:if test="${configType == 6 }">
		<div>
		<div class="addSystemConfig">
			<input type="button" id="addsystemconfig" value="添加证件类型"/>
		</div>
			<table>
			  <thead>
				  <tr>
					  <th>序号</th>
					  <th>配置类型</th>
					  <th>是否启用</th>
					  <th colspan="2">操作</th>                                          
				  </tr>
			  </thead>   
			  <tbody>
			  
			   <c:forEach items="${systemConfigs}" var="systemconfig" begin="0"  varStatus="index">
			  	<tr>
					<td>${index.index+1}</td>
					<td>${systemconfig.configTypeName }</td>
					<td><c:if test="${systemconfig.isStart == 1}"><font color="green">启用</font></c:if>
					<c:if test="${systemconfig.isStart == 2}"><font color="red">未启用</font></c:if>  </td>
					<td colspan="2">
						<span class="modifySystemBtn" sid="${systemconfig.id}" configtype="${systemconfig.configType}" sname="${systemconfig.configTypeName}" isStart="${systemconfig.isStart}"><a>修改</a></span> | 
						<c:if test="${systemconfig.id !=12 &&systemconfig.id !=13}">
						<span class="deleteSystemBtn" sname="${systemconfig.configTypeName}" sid="${systemconfig.id}"><a>删除</a></span>
						</c:if>
					</td>
				</tr>
				</c:forEach>
			</table>
		</div>		
	</c:if>
	<c:if test="${configType == 7 }"> 
		<div>
		<div class="addSystemConfig">
			<input type="button" id="addsystemconfig" value="添加优惠类型"/>
		</div>
			<table>
			  <thead>
				  <tr>
					  <th>序号</th>
					  <th>配置类型</th>
					  <th>配置数值</th>
					  <th>实际数值</th>
					  <th>是否启用</th>
					  <th colspan="2">操作</th>                                          
				  </tr>
			  </thead>   
			  <tbody>
			<c:forEach items="${systemConfigs}" var="systemconfig" begin="0"  varStatus="index">
			  	<tr>
					<td>${index.index+1}</td>
					<td>${systemconfig.configTypeName}</td>
					<td>${systemconfig.configValue}</td>
					<td>${systemconfig.configTypeValue}</td>
					<td><c:if test="${systemconfig.isStart == 1}"><font color="green">启用</font></c:if>
					<c:if test="${systemconfig.isStart == 2}"><font color="red">未启用</font></c:if>  </td>
					<td colspan="2">
						<span class="modifySystemBtn" sid="${systemconfig.id}" configtype="${systemconfig.configType}" sname="${systemconfig.configTypeName}" isStart="${systemconfig.isStart}"><a>修改</a></span> | 
						<c:if test="${systemconfig.id !=12 &&systemconfig.id !=13}">
						<span class="deleteSystemBtn" sname="${systemconfig.configTypeName}" sid="${systemconfig.id}"><a>删除</a></span>
						</c:if>
					</td>
				</tr>
				</c:forEach>
			</table>
		</div>		
	</c:if>

</div>
<link id='theme' rel='stylesheet' href='${pageContext.request.contextPath }/css/systemconfig.css'/>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/systemconfig.js" charset="UTF-8"></script> 
<jsp:include page="/inc/foot.jsp"></jsp:include>
</body>
</html>