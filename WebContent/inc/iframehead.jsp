<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="cn">
<head>
	<meta charset="utf-8">
	<title>后台管理系统</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="${pageContext.request.contextPath }/css/public.css" rel="stylesheet"/>
	<link href="${pageContext.request.contextPath }/css/main.css" rel="stylesheet"/>
	<link rel="stylesheet" id='skin' type="text/css" href="${pageContext.request.contextPath }/alertframe/skin/simple_gray/ymPrompt.css" />
	<link id='theme' rel='stylesheet' href='${pageContext.request.contextPath }/humane/themes/jackedup.css'/>
	<!-- jQuery -->
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.7.2.min.js"></script> 
	<script type="text/javascript" src="${pageContext.request.contextPath }/alertframe/ymPrompt.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/humane/humane.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/public.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/main.js" charset="UTF-8"></script> 
    <style type="text/css">
    	body{background: #fff;}
    </style>
</head>
<body>
<input type="hidden" value="${pageContext.request.contextPath }" id="path">