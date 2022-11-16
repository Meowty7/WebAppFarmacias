<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FarMap - Inicio</title>
    <!-- icono superior -->
    <link rel="shortcut icon" href="recursos/imagenes/UbiFarma-2.png">
    <!-- Estilos -->
    <link rel="stylesheet" href="recursos/estilos/estilo-general.css">
    <link rel="stylesheet" href="recursos/estilos/inicio.css">
    <!-- Estilos de fuente -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;700&display=swap" rel="stylesheet">
    <script>

    </script>
</head>
<body>

<jsp:include page="common/header.jsp"/>

<main>
    <%
        if (session.getAttribute("username") == null) {
            response.sendRedirect("/login");
        }
    %>
    <div class="main-content">
        <picture>
            <img src="recursos/imagenes/UbiFarma-2.png" alt="farmap-logo">
        </picture>

        <div class="main-info">
            <h2>Bienvenido Administrador</h2>
            <p>Desde este programa podras manipular el inventario de medicamentos y farmacias.</p>
        </div>
    </div>
</main>


<jsp:include page="common/footer.jsp"/>

</body>

</html>
<script src="recursos/scripts/script.js"></script>

