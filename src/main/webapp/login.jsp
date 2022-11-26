<%@ page import="ds.proyecto.webappfarmacias.database.Conexion" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<jsp:useBean id="u_" class="ds.proyecto.webappfarmacias.database.Operaciones" />
<!DOCTYPE html>
<html lang="es">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FarMap - Log in</title>
    <!-- icono superior -->
    <link rel="shortcut icon" href="recursos/imagenes/UbiFarma-2.png">
    <!-- Estilos -->
    <link rel="stylesheet" href="recursos/estilos/estilo-general.css">
    <link rel="stylesheet" href="recursos/estilos/login.css">
    <!-- Estilos de fuente -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;700&display=swap" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <style>
       .password-container .user-input{
           width: 100%;
       }

       .togglePassword{
           position: absolute;
           cursor: pointer;
       }

       .form-wrp{
           width: 100%;
           padding: 31px 55px;
           background: white;
           border-radius: 5px;
           box-shadow: 0 2px 4px rgb(0 0 0 / 10%), 0 8px 16px rgb(0 0 0 / 10%);;
       }

       #error-span{
           color: red;
           font-size: 1.2rem;
           position: absolute;
       }
    </style>

</head>
<body>
    <header>
        <div class="logo-box">
            <picture class="logo">
                <img class="logo-img" src="recursos/imagenes/UbiFarma.png" alt="LOGO">
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
    </header>

    <main>
        <%! String username, password; %>
        <%
            session.setAttribute("invalid", "hidden");
            String attr_hide = (String) session.getAttribute("invalid");
            username = request.getParameter("username");
            password = request.getParameter("password");
            HttpSession sessionActive = request.getSession(false);
            if(sessionActive.getAttribute("authenticated")!=null
                    && sessionActive.getAttribute("authenticated").equals(true)) {
                response.sendRedirect("/home");
            }else{
                if (username != null && password != null) {
                    try {
                        boolean existe = u_.user_exists(username, password, new Conexion("Farmacia", "", ""));
                        if (existe) {
                            session.setAttribute("username", username);
                            session.setAttribute("password", password);
                            session.setAttribute("authenticated", true);
                            response.sendRedirect("/home");
                        } else {
                            attr_hide = "";
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
        %>
        <div class="form-wrp">
            <h2>Inicio de Sesión</h2>
            <span>Acceda a su cuenta</span>
            <form action="${pageContext.request.contextPath}/login" method="POST">
                <label for="username"><img src="recursos/iconos/user-icon.png" alt="user-icon" width="16px"> Usuario</label>
                <input required class="user-input" name="username" id="username" type="text" placeholder="Usuario" autocomplete="off">
                <label for="password"> <img src="recursos/iconos/password-icon.png" alt="password-icon" width="16px"> Contraseña</label>
                <div class="password-container">
                    <input required class="user-input"
                           name="password"
                           id="password"
                           type="password"
                           placeholder="*********"
                           autocomplete="off">
                    <img class="togglePassword" width="20" src="recursos/imagenes/notvisible-eye.svg" alt="eyelash-ico">
                    <div <%=attr_hide%> class="error-span-wrapper">
                        <span<%=attr_hide%> id="error-span"> Usuario o contraseña incorrectos. </span>
                    </div>
                </div>
                    <input class="input-button" type="submit" value="Acceder" name="login-button" id="">
            </form>
        </div>
        <%
            }
        %>
    </main>

<jsp:include page="common/footer.jsp"/>
    <script>
        const fieldPassword = document.getElementById('password');
        const tgg_pss = document.getElementsByClassName('togglePassword')
        tgg_pss[0].addEventListener('click', function (){
            if (fieldPassword.type === "password") {
                fieldPassword.type = "text";
                this.src = 'recursos/imagenes/visible-eye.svg'
            } else {
                fieldPassword.type = "password";
                this.src = 'recursos/imagenes/notvisible-eye.svg'
            }
        });
    </script>


</body>
</html>