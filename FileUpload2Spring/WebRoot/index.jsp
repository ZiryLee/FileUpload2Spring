<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>文件上传测试案例</title>
  </head>
  
  <body>
    <a href="<%=basePath%>tUplode.html">测试文件上传</a>
    <br><br>
    <a href="<%=basePath%>tDownload.html">测试文件下载</a>
  </body>
</html>
