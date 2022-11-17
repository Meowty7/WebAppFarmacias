<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Redireccionando...</title>
</head>
<body>
    <%
        session.removeAttribute("username");
        session.removeAttribute("password");
        session.invalidate();
        response.sendRedirect("/login");
    %>
</body>
</html>
