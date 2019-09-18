<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="/inc/head.jsp"%>
<div class="mbxnav">
	<a href="javascript:void();">系统管理</a> \ <a
		href="${pageContext.request.contextPath }/userlist.action">用户管理</a>
</div>
<div class="container">


	<div class="searchuserdiv ope">
		<ul>
			<li>
				<form action="${pageContext.request.contextPath }/userlist"
					method="post">
					用户名称: <input type="text" id="uname" name="userName"
						value="${user.userName }" /> 角色： <select name="roleId">
						<option value="" selected="selected">--请选择--</option>
						<c:if test="${roleList != null }">
							<c:forEach items="${roleList }" var="role">

								<option
									<c:if test="${user.roleId == role.id }">selected = "selected"</c:if>
									value="${role.id }">${role.roleName}</option>
							</c:forEach>
						</c:if>
					</select> 是否启用： <select name="isStart">
						<option value="" selected="selected">--请选择--</option>
						<c:if test="${user.isStart == 1 }">
							<option value="1" selected="selected">启用</option>
							<option value="0">未启用</option>
						</c:if>
						<c:if test="${user.isStart == 0 }">
							<option value="0" selected="selected">未启用</option>
							<option value="1">启用</option>
						</c:if>
						<c:if test="${user.isStart == null }">
							<option value="0">未启用</option>
							<option value="1">启用</option>
						</c:if>
					</select> <input type="submit" value="查询" />
				</form>
			</li>
		</ul>
	</div>
	<div id="addUserDiv" class="addUserDivClass addback">
		<ul>
			<li class="lititle"><b>添加代理商用户信息</b></li>
			<li>登录账号：<input id="a_userCode" type="text" name="user.userCode" />
				<span>*</span></li>
			<li>用户名称：<input id="a_userName" type="text" name="user.userName" />
				<span>*</span></li>
			<li>登录密码：<input id="a_userPassword" type="password"
				name="user.userPassword" value="123456" /> <span>*默认初始密码123456</span></li>
			<li>角&nbsp;&nbsp;&nbsp;&nbsp;色： <select id="a_roleId"
				name="user.roleId">
					<option value="0" selected="selected">--请选择--</option>
					<c:if test="${roleList != null }">
						<c:forEach items="${roleList }" var="role">
							<option value="${role.id }">${role.roleName}</option>
						</c:forEach>
						<%-- <s:iterator value="roleList">
 						<option value="<s:property value='id'/>"><s:property value="roleName"/></option>
 					</s:iterator> --%>
					</c:if>
			</select> <span>*</span>
			</li>
			<li>是否启用： <select id="a_isStart" name="user.isStart">
					<option value="1" selected="selected">启用</option>
					<option value="0">不启用</option>
			</select> <span>*</span> <input id="addUserSubmit" type="button" value="保存" />
				<input id="addcancel" type="reset" value="取消" /></li>
		</ul>
	</div>
	<div id="modifyUserDiv" class="addUserDivClass modifyback">
		<ul>
			<li class="lititle"><input id="m_userId" type="hidden"
				name="user.id" /> <b>修改代理商用户信息</b></li>
			<li>登录账号：<input id="m_userCode" type="text" name="user.userCode" />
				<span>*</span></li>
			<li>用户名称：<input id="m_userName" type="text" name="user.userName" />
				<span>*</span></li>
			<li>登录密码：<input id="m_userPassword" type="password"
				name="user.userPassword" /> <span>*</span></li>
			<li>角&nbsp;&nbsp;&nbsp;&nbsp;色： <span id=m_SelectRole></span> <span>*</span>
			</li>
			<li>是否启用： <span id="m_Select"></span> <span>*</span> <input
				id="modifyUserSubmit" type="submit" value="保存" /> <input
				id="modifycancel" type="button" value="取消" /></li>
		</ul>
	</div>

	<div class="addUserDiv">
		<input id="addUser" type="button" value="新增" />
	</div>

	<table>
		<thead>
			<tr>
				<th>登录账号</th>
				<th>用户名称</th>
				<th>角色</th>
				<th>创建时间</th>
				<th>是否启用</th>
				<th colspan="3">操作</th>
			</tr>
		</thead>
		<tbody>

			<c:if test="${user.page.items !=null}">
				<c:forEach items="${user.page.items}" var="a">
					<tr>
						<td>${a.userCode }</td>
						<td>${a.userName }</td>
						<td>${a.role.roleName }</td>
						<td><fmt:formatDate value="${a.creationTime }"
								pattern="yyyy-MM-dd HH:mm:ss" /></td>
						<td><c:if test="${a.isStart == 1 }">启用</c:if> <c:if
								test="${a.isStart == 0 }">未启用</c:if></td>
						<td colspan="3"><span class="modifyUser" userid="${a.id}"
							usercode="${a.userCode}" username="${a.userName}"
							userpassword="${a.userPassword}" isstart="${a.isStart}"
							roleid="${a.roleId }" rolename="${a.role.roleName}"><a>修改</a></span>
							| <span class="deleteUser" usercode="${a.userCode }"
							userid="${a.id }"
							currentUserRoleId="${sessionScope.userSession.roleId}"
							roleid="${a.roleId }"><a>删除</a></span> | <a
							href="javascript:ymPrompt.win('${pageContext.request.contextPath }/yfklist?id=${a.id }&userCode=${a.userCode }',1000,500,' 预付款',null,null,null,true);">预付款</a>
							| <a
							href="javascript:ymPrompt.win('${pageContext.request.contextPath }/loglist?id=${a.id }&userCode=${a.userCode }',1015,540,' LOG日志',null,null,null,true);">LOG日志</a>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>

	<div class="pagination pagination-centered">
		<ul>
			<li><a
				href="${pageContext.request.contextPath }/userlist?page.page=1&userName=${user.userName }&roleId=${user.roleId}&isStart=${user.isStart}"
				title="首页">首页</a></li>
			<c:if test="${user.page.prevPages!=null }">

				<c:forEach items="${user.page.prevPages}" var="num">
					<li><a
						href="${pageContext.request.contextPath }/userlist?page.page=${num}&userName=${user.userName }&roleId=${user.roleId}&isStart=${user.isStart}"
						class="number" title="${num }">${num } </a></li>
				</c:forEach>
			</c:if>
			<li class="active"><a href="#" title="${user.page.page }">${user.page.page }
			</a></li>
			<c:if test="${user.page.nextPages!=null }">

				<c:forEach items="${user.page.nextPages}" var="num">
					<li><a
						href="${pageContext.request.contextPath }/userlist?page.page=${num}&userName=${user.userName }&roleId=${user.roleId}&isStart=${user.isStart}">
							${num} </a></li>
				</c:forEach>
			</c:if>
			<li><a
				href="${pageContext.request.contextPath }/userlist?page.page=${page.pageCount}&userName=${user.userName }&roleId=${user.roleId}&isStart=${user.isStart}"
				title="尾页">尾页</a></li>
		</ul>
	</div>
</div>

<script type="text/javascript">
	var roleListJson = [
			/* <s:iterator value="roleList"> */
			<c:forEach items="${roleList }" var="role">
			{"id":"${role.id}","roleName":"${role.roleName}"},
			/* </s:iterator> */
			</c:forEach>{"id":"over","roleName":"over"}];
</script>
<link id='theme' rel='stylesheet'
	href='${pageContext.request.contextPath }/css/userlist.css' />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/js/userlist.js"
	charset="UTF-8"></script>
<jsp:include page="/inc/foot.jsp"></jsp:include>
</body>
</html>
