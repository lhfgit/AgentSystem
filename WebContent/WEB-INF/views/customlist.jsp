<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"  deferredSyntaxAllowedAsLiteral="true"%>
<%@include file="/inc/head.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="mbxnav">
	<a href="/main">代理商管理</a> \ <a href="${pageContext.request.contextPath }/customlist">代理商客户管理</a>
</div>
<div class="container">
	
		<form action="${pageContext.request.contextPath }/customlist" method="post" >
             <div>
	            <label>客户名称:</label>
	            <input type="text" id="cname" name="customName" value="${custom1.customName }"/>
	            <input type="submit" value="查询" /> 
              </div>
        </form>
        
        <div class="addCustomDiv">
        <input onclick="javascript:location.href='${pageContext.request.contextPath }/addcustom';" type="button" value="添加客户" />
        </div>
		<c:if test="${custom1.page.items != null }">
            <table>
            <thead>
            <tr>
	     	<th>序号</th>
	     	<th>客户名称</th>
	     	<th>法人代表</th>
	     	<th>注册时间</th>
	     	<th>类型</th>
	     	<th>状态</th>
	     	<th>操作</th>
  			</tr>
            </thead>
            <c:forEach items="${custom1.page.items }" var="custom" varStatus="in">
            	<tr class="tabletr">
            		<td>
            		<c:choose>
            		<c:when test="${1 >= custom.page.page }">${in.index+1 }</c:when>
            		<c:otherwise>
            		
            		</c:otherwise>
            		</c:choose>
            		${(custom.page.page-1)*custom.page.page.Size+in.index+1 }
					
            		</td>
                    <td>${custom.customName }</td>
                    <td>${custom.bossName }</td>
                    <td><fmt:formatDate value="${custom.regDatetime }" pattern="yyyy-MM-dd"/> </td>
                    <td>${custom.customTypeName }</td>
                    <td>
                    <span id="m_status${in.index+1 }">
                    <c:if test="${custom.customStatus==1 }"><font color="green">启用</font></c:if>
                     <c:if test="${custom.customStatus==0 }"><font color="red">停用</font></c:if>
                    
                    </span>
                    </td>
                    <td class="funcli">
                    	<ul>
                     		<li><a class="viewCustom" id="${custom.id}">查看</a></li>
                     		<li><a class="modifyCustom" id="${custom.id }" cname="${custom.customName }">修改</a></li>
                     		<li>
                     		 <c:if test="${custom.customStatus==1 }">
                     		 <a class="modifyCustomStatus" id="${custom.id }" mStatus="${in.index+1 }" customStatus="${custom.customStatus}" customName="${custom.customName}"><font color="red">停用</font></a></li>
                     		 </c:if>
                    		 <c:if test="${custom.customStatus==0 }">
							 <a class="modifyCustomStatus" id="${custom.id }" mStatus="${in.index+1 }" customStatus="${custom.customStatus}" customName="${custom.customName}"><font color="green">启用</font></a></li>
<%-- 							 <a class="modifyCustomStatus" id="<s:property value="id"/>"  mStatus="<s:property value="#sta.index+1"/>" customStatus="<s:property value="customStatus"/>" customName="<s:property value="customName"/>"><font color="green">启用</font></a></li> --%>
							</c:if>
                     		
                     	</ul>
					</td>
                 </tr>   
            
              </c:forEach>
              </table>
             <div class="pagination pagination-centered">
						  <ul>
							<li><a href="/customlist?page.page=1&customName=${custom1.customName }" title="首页">首页</a></li>
							<c:if test="${custom1.page.prevPages!=null }">
							<c:forEach items="${custom1.page.prevPages }" varStatus="num">
								
									<li><a
										href="/customlist?page.page=${num}customName=${custom1.customName }"
										class="number" title="${num }">${num } </a></li>
								</c:forEach>
							</c:if>
							<li class="active">
							  <a href="#"title="${custom1.page.page }">${custom1.page.page }</a>
							</li>
							<c:if test="${custom1.page.nextPages!=null }">
								
								<c:forEach items="${custom1.page.nextPages }" varStatus="num">
									<li><a href="/customlist?page.page=${num}&customName=${custom1.customName }" title="${num }">
									${num } </a></li>
								</c:forEach>
							</c:if>
							<li><a href="/customlist?page.page=${page.pageCount}&customName=${custom1.customName }" title="尾页">尾页</a></li>
						  </ul>
						</div> 
       </c:if>
	</div>
<link id='theme' rel='stylesheet' href='${pageContext.request.contextPath }/css/customlist.css'/>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/customlist.js" charset="UTF-8"></script> 
<jsp:include page="/inc/foot.jsp"></jsp:include>
</body>
</html>

