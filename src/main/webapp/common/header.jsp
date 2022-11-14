<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<header>
    <div class="logo-box">
        <picture class="logo">
            <img class="logo-img" src="../recursos/imagenes/UbiFarma.png" alt="LOGO">
        </picture>
        <div class="logo-description">
            <h1>FarMap</h1>
            <span>|</span>
            <div>
                <p>Farmacias a</p>
                <p>tu alcance</p>
            </div>
        </div>
    </div>
    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/home">Inicio</a></li>
            <li><a class="admin-tab" href="#">Administrar tablas <img src="../recursos/iconos/down-arrow.png" alt="opciones-desplegables"></a></li>
            <li><a href="logout">Cerrar Sesión</a></li>
        </ul>
    </nav>
    <img class="menu-icon" src="../recursos/iconos/menu-icon.png" alt="menu-icon">

    <div class="menu no-ver" id="show-this">
        <ul>
            <li><a href="${pageContext.request.contextPath}/farmacias">Farmacias</a></li>
            <li><a href="${pageContext.request.contextPath}/medicamentos">Medicamentos</a></li>
        </ul>
    </div>
</header>
