<%@ page import="hello.servlet.domain.member.Member" %><%--
  Created by IntelliJ IDEA.
  User: yoosangmin
  Date: 2021/08/18
  Time: 7:45 오후
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
    <li>id=${member.id}</li>
    <li>id=${member.username}</li>
    <li>id=${member.age}</li>
</ul>
<a href="/index.html">메인</a>

</body>
</html>
