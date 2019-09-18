<%@ page language="java" import="java.util.*,org.agent.pojo.*"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8">
<title>代理商管理系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
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
</head>
<body>
	<input type="hidden" value="${pageContext.request.contextPath }"
		id="path">
	<div class="head">
		<ul>
			<li><h2>
					<!--  <img src="/imgs/logo.png" width="130px"/> -->
				</h2></li>
			<li class="headfunc">
				<ul>
					<li>欢迎您：${sessionScope.userSession.userName} <a
						class="modifypwd" id="modifypwdbtna"> 修改密码 </a> <a
						href="${pageContext.request.contextPath }/exit">退出</a>
					</li>
				</ul>
			</li>
		</ul>
	</div>
	<!-- 主菜单 -->
	<div id="menu" class="menu">
		<ul>
			<c:forEach items="${roleFuntionslist }" var="roleFuntions"
				varStatus="index">

				<li class="m_line"><img alt=""
					src="${pageContext.request.contextPath }/imgs/line1.gif"></li>
				<li id="m_${index.index+1 }" class="m_li"
					onmouseover="mover(${index.index+1})"><a
					href="${pageContext.request.contextPath }${roleFuntions.mainFunction.funcUrl }">${roleFuntions.mainFunction.functionName}</a>

				</li>
			</c:forEach>

		</ul>

	</div>


	<%-- <div id="menu" class="menu">
	
		<ul >
		
		 <li class="m_line"><img alt="" src="${pageContext.request.contextPath }/imgs/line1.gif"></li> 
		<li id="m_1" class="m_li" onmouseover ="mover(1)"><a href="#">代理商管理</a></li>
		 <li class="m_line"><img alt="" src="${pageContext.request.contextPath }/imgs/line1.gif"></li> 
		<li id="m_1" class="m_li" onmouseover ="mover(2)"><a href="#">门户管理</a></li>
		 <li class="m_line"><img alt="" src="${pageContext.request.contextPath }/imgs/line1.gif"></li> 
		<li id="m_1" class="m_li" onmouseover ="mover(3)"><a href="#">报表管理</a></li>
		 <li class="m_line"><img alt="" src="${pageContext.request.contextPath }/imgs/line1.gif"></li> 
		<li  id="m_4" class="m_li" onmouseover ="mover(4)"><a href="${pageContext.request.contextPath }/#">系统管理</a></li>
		 <li class="m_line"><img alt="" src="${pageContext.request.contextPath }/imgs/line1.gif"></li> 
		<li  id="m_5" class="m_li" onmouseover ="mover(5)"><a href="#">系统配置管理</a></li>
		</ul>
	</div>  --%>

	<!-- 子菜单 -->
	<%-- <div class="subbox">
		<ul class="smenu">
			<!-- 代理商管理 -->
			<li id="s_1" class="s_li"><a href="/keyword.action">关键词申请</a> <a
				href="/customlist.action">代理商客户管理</a> <a href="/yfk.action">代理商预付款</a>
				<a href="/keywordmanage.action">关键词申请管理</a> <a href="/mylogs.action">操作日志</a>
			</li>
			<li id="s_4" class="s_li"><a
				href="${pageContext.request.contextPath }/caiwu">财务管理</a> <a
				href="${pageContext.request.contextPath }/rolelist">角色管理</a> <a
				href="${pageContext.request.contextPath }/premission">角色权限配置</a> <a
				href="${pageContext.request.contextPath }/userlist">用户管理</a> <a
				href="${pageContext.request.contextPath }/checkkeyword.action">关键字审核</a>
			</li>
			<li id="s_5" class="s_li"><a
				href="${pageContext.request.contextPath }/config/1">财务类型</a> <a
				href="${pageContext.request.contextPath }/config/2">服务类型</a> <a
				href="${pageContext.request.contextPath }/config/3">服务年限</a> <a
				href="${pageContext.request.contextPath }/config/4">APP地址</a> <a
				href="${pageContext.request.contextPath }/config/5">客户类型</a> <a
				href="${pageContext.request.contextPath }/config/6">证件类型</a> <a
				href="${pageContext.request.contextPath }/config/7">优惠类型</a></li>


		</ul>
	</div> --%>
	<!-- ******* -->
	<div class="subbox">
		<ul class="smenu">
			<c:forEach var="roleFunctions" items="${roleFuntionslist}"
				varStatus="in">
				<c:if
					test="${roleFunctions.subFuntions != null && roleFunctions.subFuntions.size()>0 }">
					<c:choose>
						<c:when test="${in.index==0 }">
							<li style="padding-left: 10px;" id="s_1" class='s_li_a'><c:forEach
									items="${roleFunctions.subFuntions }" var="subFunctions">
									<a href="${pageContext.request.contextPath }${subFunctions.funcUrl }">${subFunctions.functionName }</a>
								</c:forEach>
						</c:when>
						<c:when test="${in.index==1 }">
							<li style="padding-left: 10px;" id="s_2" class='s_li'><c:forEach
									items="roleFunctions.subFuntions" var="subFunctions">
									<a href="${pageContext.request.contextPath }${subFuntions.funcUrl }">${subFuntions.functionName }</a>
								</c:forEach>
						</c:when>
						<c:when test="${in.index==2 }">
							<li style="padding-left: 10px;" id="s_3" class='s_li'><c:forEach
									items="${roleFunctions.subFuntions }" var="subFuntions">
									<a href="${pageContext.request.contextPath }${subFuntions.funcUrl }">${subFuntions.functionName }</a>
								</c:forEach>
						</c:when>
						<c:when test="${in.index==3 }">
							<li style="padding-left: 10px;" id="s_4" class='s_li'><c:forEach
									items="${roleFunctions.subFuntions }" var="subFuntions">
									<a href="${pageContext.request.contextPath }${subFuntions.funcUrl }">${subFuntions.functionName }</a>
								</c:forEach>
						</c:when>
						<c:when test="${in.index==4 }">
							<li style="padding-left: 10px;" id="s_5" class='s_li'><c:forEach
									items="${roleFunctions.subFuntions }" var="subFuntions">
									<a href="${pageContext.request.contextPath }${subFuntions.funcUrl }">${subFuntions.functionName }</a>
								</c:forEach>
						</c:when>
					</c:choose>
				</c:if>
				<c:if test="${roleFunctions.subFuntions == null}">
					<c:choose>
						<c:when test="${in.index==0 }">
							<li style="padding-left: 10px;" id="s_1" class='s_li_a'>
						</c:when>
						<c:when test="${in.index==1 }">
							<li style="padding-left: 131px;" id="s_2" class='s_li'
								onmouseover='mover(2);'>
						</c:when>
						<c:when test="${in.index==2 }">
							<li style="padding-left: 243px;" id="s_3" class='s_li'
								onmouseover='mover(3);'>
						</c:when>
						<c:when test="${in.index==3 }">
							<li style="padding-left: 360px;" id="s_4" class='s_li'
								onmouseover='mover(4);'>
						</c:when>
						<c:when test="${in.index==4 }">
							<li style="padding-left: 220px;" id="s_5" class='s_li'
								onmouseover='mover(5);'>
						</c:when>
						<c:otherwise>
							<a href="/main.action" title="无权限">无权限</a>
						</c:otherwise>

					</c:choose>
					</li>
				</c:if>
			</c:forEach>
		</ul>
	</div>
	<%-- <div class="subbox">
		<ul class="smenu">
		
			<s:iterator value="roleFunctions" status="sta">
				<s:if test="subFunctions != null && subFunctions.size()>0">
					<s:if test="#sta.index == 0">
					<li style="padding-left:10px;" id="s_1" class='s_li_a'>
					<s:iterator value="subFunctions">
						<a href="<s:property value="funcUrl"/>"><s:property value="functionName"/></a>
					</s:iterator>
					</li>
					</s:if><s:elseif test="#sta.index == 1">
					<li style="padding-left:131px;" id="s_2" class='s_li' onmouseover='mover(2);'>
						<s:iterator value="subFunctions">
						<a href="<s:property value="funcUrl"/>"><s:property value="functionName"/></a>
						</s:iterator>
					</li>
					</s:elseif><s:elseif test="#sta.index == 2">
					<li style="padding-left:243px;" id="s_3" class='s_li' onmouseover='mover(3);'>
						<s:iterator value="subFunctions">
						<a href="<s:property value="funcUrl"/>"><s:property value="functionName"/></a>
						</s:iterator>
					</li>
					</s:elseif><s:elseif test="#sta.index == 3">
					<li style="padding-left:140px;" id="s_4" class='s_li' onmouseover='mover(4);'>
						<s:iterator value="subFunctions">
						<a href="<s:property value="funcUrl"/>"><s:property value="functionName"/></a>
						</s:iterator>
					</li>
					</s:elseif><s:elseif test="#sta.index == 4">
					<li style="padding-left:220px;" id="s_5" class='s_li' onmouseover='mover(5);'>
						<s:iterator value="subFunctions">
						<a href="<s:property value="funcUrl"/>"><s:property value="functionName"/></a>
						</s:iterator>
					</li>
					</s:elseif>
				</s:if><s:else>
					<s:if test="#sta.index == 0">
						<li style="padding-left:10px;" id="s_1" class='s_li_a'>
					</s:if><s:elseif test="#sta.index == 1">
						<li style="padding-left:131px;" id="s_2" class='s_li' onmouseover='mover(2);'>
					</s:elseif><s:elseif test="#sta.index == 2">
					<li style="padding-left:243px;" id="s_3" class='s_li' onmouseover='mover(3);'>
					</s:elseif><s:elseif test="#sta.index == 3">
					<li style="padding-left:360px;" id="s_4" class='s_li' onmouseover='mover(4);'>
					</s:elseif><s:elseif test="#sta.index == 4">
					<li style="padding-left:460px;" id="s_5" class='s_li' onmouseover='mover(5);'>
					</s:elseif>
						<a href="/main.action"  title="无权限">无权限</a>
					</li>
				</s:else>
			</s:iterator>	
</ul>
	</div>  --%>
</body>

</html>

