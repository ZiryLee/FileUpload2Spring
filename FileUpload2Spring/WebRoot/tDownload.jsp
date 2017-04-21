<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>文件下载测试案例</title>
  </head>
  
  <body>
    <form action="<%=basePath%>tDownload.html" enctype="multipart/form-data" method="post">
                 地址： <input type="text" name="url">
        <input type="submit" value="获取">  
    </form>
    
    <c:if test="${photo!=null }">
                 原图：<img src="${photo}">
    </c:if>
    <br><br>
    <c:if test="${photoSmall!=null }">
                 缩略图：<img src="${photoSmall}">
    </c:if>
  </body>
</html>
