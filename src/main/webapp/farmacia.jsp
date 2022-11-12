<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FarMap - Farmacias</title>
    <!-- icono superior -->
    <link rel="shortcut icon" href="recursos/imagenes/UbiFarma-2.png">
    <!-- Estilos -->
    <link rel="stylesheet" href="recursos/estilos/estilo-general.css">
    <!-- Estilos de fuente -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;700&display=swap" rel="stylesheet">
</head>
<body>

<jsp:include page="common/header.jsp"/>

    <main>
        <%
            if (session.getAttribute("username") == null) {
                response.sendRedirect("/login");
            }
        %>
        <img width="100%" src="recursos/imagenes/construccion.png" alt="en-construcción">
    </main>

<jsp:include page="common/footer.jsp"/>

</body>
<script src="recursos/scripts/script.js"></script>
</html>

