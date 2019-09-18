<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/inc/iframehead.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<div class="container">
	<h3>功能列表</h3>
	<div class="btndiv">
		<input type="hidden" id="roleid" value="${roleId }"/>
		<input id="saverolefunc" type="button" value="保存" />
		<input id="cancel" type="button" value="取消" />
	</div>
		
		<c:if test="${funcList != null }">
            <table>
            <thead>
            <tr>
	     	<th><input type="checkbox" id="cball"/>全选/全不选</th>
	     	<th>功能名称</th>
	     	<th>功能URL</th>
	     	<th>创建时间</th>
	     	<th>是否启用</th>
  			</tr>
            </thead>
            <tbody>
          <c:forEach items="${funcList }" var="function">  
            	<tr>
                    <td>
                   <c:if test="${function.check }">
                    	<input class="cb" type="checkbox" checked="checked" value="${function.id}"/>
                    </c:if>
                   <c:if test="${function.check==false }">
                    	<input class="cb" type="checkbox" value="${function.id}"/>
                    </c:if> 
                   <%--  <input class="cb" type="checkbox" value="${function.id}"/> --%>
                    </td>
                    <td>${function.functionName}</td>
                    <td>${function.funcUrl}</td>
                   <!--  <td><s:date name="creationTime" format="yyyy-MM-dd HH:mm:ss"/></td> -->
                   <td><fmt:formatDate value="${function.creationTime}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
                    <td>
                    <c:if test="${function.isStart == 1 }">启用</c:if>
                    <c:if test="${function.isStart == 0 }">未启用</c:if>
                   
                    </td>
                 </tr>   
              
              </c:forEach>
              </tbody>
            </table>
       </c:if>
       
</div>
<link id='theme' rel='stylesheet' href='${pageContext.request.contextPath }/css/functionlist.css'/>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/functionlist.js" charset="UTF-8"></script> 
</body>
</html>
