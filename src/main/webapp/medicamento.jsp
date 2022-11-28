<%@ page import="ds.proyecto.webappfarmacias.database.Conexion" %>
<jsp:useBean id="objeto" class="ds.proyecto.webappfarmacias.database.Operaciones" />
<%@ page import="ds.proyecto.webappfarmacias.database.Medicamentos" %>
<%@ page import="java.util.LinkedList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FarMap - Medicamentos</title>
    <!-- icono superior -->
    <link rel="shortcut icon" href="recursos/imagenes/UbiFarma-2.png">
    <!-- Estilos -->
    <link rel="stylesheet" href="recursos/estilos/estilo-general.css">
    <link rel="stylesheet" href="recursos/forms/css/form.css">
    <link rel="stylesheet" href="recursos/forms/css/table.css">
    <link rel="stylesheet" href="recursos/estilos/popdel.css">
    <!-- Estilos de fuente -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;700&display=swap" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script src="recursos/scripts/script.js" defer></script>
    <script>
        //Evita el submit y consultas a la base de datos al recargar la página
        if ( window.history.replaceState ) {
            window.history.replaceState( null, null, window.location.href );
        }
    </script>
</head>
<body>

<jsp:include page="common/header.jsp"/>

<%
    if (session.getAttribute("username") == null) {
        response.sendRedirect("/login");
    }
%>
<%!
    Conexion connexion = new Conexion("Farmacia", "" ,"");
    LinkedList<Medicamentos> medicamentos;
    int id = 0;
    float price = 0.00f;
    String genericName = "",  comercialName = "";
    int idn = 0;
    float precio_n = 0f;
    String comercial = "";
    String generic = "";
    String attribute = "";
    String value = "";
%>
<main class="medicamentos-main">
    <div class="tr-form-wrapper">
        <div class="th-form-wrapper">
            <form method="POST" id="add-form" action="${pageContext.request.contextPath}/medicamentos" class="add-med">
                <input aria-label="" id="lola" hidden value="true" name="new-m" type="text">
                <button value="insertar" id="op-button-insertar" name="op-button-insertar" type="submit" class="form-button insertar">Añadir Medicamento</button>
            </form>
        </div>
    </div>
    <section id="table-container">
        <table id="tableDemo">
            <thead>
            <tr>
                <th>Código Medicamento</th>
                <th>Nombre Genérico</th>
                <th>Nombre Comercial</th>
                <th>Precio</th>
                <th></th>
            </tr>
            </thead>

            <tbody>
            <%
                try {
                    medicamentos = objeto.consulta_registro(connexion);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                for (Medicamentos medicamento: medicamentos) {
            %>
            <tr class="cols">
                <td class="cells first-col"><%out.print(medicamento.getIdMedicamento());%></td>
                <td class="cells"><%out.print(medicamento.getNombreGeneric());%></td>
                <td class="cells"><%out.print(medicamento.getNombreComercial());%></td>
                <td class="cells"><%out.print(medicamento.getPrecioFabricante());%></td>
                <td class="form-cell">
                    <form name="myForm" action="${pageContext.request.contextPath}/medicamentos" id="myForm" method="POST">
                        <input aria-label="" hidden id="id" type="text" name="id" value="<%out.print(medicamento.getIdMedicamento());%>">
                        <input aria-label="" hidden type="text" id="generico" name="generico" value="<%out.print(medicamento.getNombreGeneric());%>">
                        <input aria-label="" hidden type="text" name="comercial" value="<%out.print(medicamento.getNombreComercial());%>">
                        <input aria-label="" hidden type="text" name="precio" value="<%out.print(medicamento.getPrecioFabricante());%>">
                        <button id="edit-btn" type="submit"> <img width="20" src="recursos/forms/img/edit-input.svg" alt="editar icono"> </button>
                    </form>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>

    </section>

    <%

        if( request.getParameter("id")!=null && request.getParameter("op-button")==null
                || request.getParameter("new-m")!=null) {

            if(request.getParameter("op-button-insertar")==null) {
                try {
                    attribute = "readonly";
                    value = "guardar";
                    id = Integer.parseInt(request.getParameter("id"));
                    genericName = request.getParameter("generico");
                    comercialName = request.getParameter("comercial");
                    price = Float.parseFloat(request.getParameter("precio"));
                    pageContext.setAttribute("val1", id);
                    pageContext.setAttribute("val2", genericName);
                    pageContext.setAttribute("val3", comercialName);
                    pageContext.setAttribute("val4", price);
                } catch (NumberFormatException NFE) {
                    out.print("Error de conversion numérica: " + NFE);
                } catch (Exception e) {
                    out.print("Ocurrió un error, " + e);
                }
            }else{
                attribute = "required";
                value = "insertar";
            }

    %>
    <section id="operations-container">
        <div>
            <form onsubmit="reloadChanges()" name="popupContact" autocomplete="off" id="popupContact" action="${pageContext.request.contextPath}/medicamentos" method="POST">
                <div class="field-container A">
                    <label for="id-medicamento-n">Código Medicamento</label>
                    <input <%=attribute%> autocomplete="off" class="input-formEdit" type="text" name="id-medicamento-n" id="id-medicamento-n" value="${val1}">
                </div>

                <div class="field-container B">
                    <label for="nombre-generic-n">Nombre Genérico</label>
                    <input class="input-formEdit" id="nombre-generic-n" type="text" name="nombre-generic-n" value="${val2}" required>
                </div>

                <div class="field-container C">
                    <label for="nombre-comercial-n">Nombre Comercial</label>
                    <input class="input-formEdit" id="nombre-comercial-n" type="text" name="nombre-comercial-n" value="${val3}" required>
                </div>

                <div class="field-container D">
                    <label for="precio-n">Precio</label>
                    <input class="input-formEdit" type="text" name="precio-n" id="precio-n" value="${val4}" required autocomplete="off">
                </div>

                <div id="button-container">
                    <button id="save" class="form-button save" name="op-button" value="<%=value%>" type="submit">Guardar</button>
                    <button class="form-button delete" id="delete" name="op-button" type="button" >Eliminar</button>
                    <button id="cancel" class="form-button cancel" name="op-button" value="cancelar" type="button">Cancelar</button>
                </div>
            </form>
        </div>

        <div class="popup-delete_wrap">
            <div id="popup-delete">
                <div>
                    <img width="35" src="recursos/imagenes/delete_forever_black_24dp.svg" alt="dico">
                </div>
                <h3>¿Está seguro?</h3>
                <span> ¿Realmente desea eliminar este registro? <br>
                    Esta acción no se puede deshacer
                </span>
                <form onsubmit="reloadChanges()" action="${pageContext.request.contextPath}/medicamentos">
                    <div id="buttons-delete">
                        <div class="wrapper-bd-pop">
                            <button id="bd-pop-cancelar" type="button">CANCELAR</button>
                        </div>
                        <div class="wrapper-bd-pop">
                            <input hidden type="text" name="id-medicamento-n" value="${val1}">
                            <button name="bd-pop-eliminar" value="eliminar" id="bd-pop-eliminar" type="submit">ELIMINAR</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

    </section>


    <%
        }else
            if(request.getParameter("op-button")!=null
                &&
                request.getParameter("id-medicamento-n")!=null
                ||
                request.getParameter("bd-pop-eliminar")!=null ){

            try
            {
                idn = Integer.parseInt(request.getParameter("id-medicamento-n"));
                precio_n = Float.parseFloat(request.getParameter("precio-n"));
                comercial = request.getParameter("nombre-comercial-n");
                generic = request.getParameter("nombre-generic-n");
            } catch (NumberFormatException NFE) {
                out.print("Error de conversion numérica: " + NFE);
            } catch (Exception e) {
                out.print("Ocurrió un error, " + e);
            }

            if (("guardar").equals(request.getParameter("op-button"))) {
                try
                {
                    objeto.modificar(idn, generic, comercial, precio_n, connexion);
                } catch (NumberFormatException NFE) {
                    out.print("Error de conversion numérica: " + NFE);
                } catch (Exception e) {
                    out.print("Ocurrió un error, " + e);
                }
            }else
                if (("eliminar").equals(request.getParameter("bd-pop-eliminar"))) {
                    try
                    {
                        objeto.eliminar(idn, connexion);
                    } catch (Exception e) {
                        out.print("Ocurrió un error, " + e);
                    }
            }else if(("cancelar").equals(request.getParameter("op-button"))){
              //hacer nada
            }else
                if("insertar".equals(request.getParameter("op-button"))){
                    try
                    {
                        objeto.insertar(idn, generic, comercial, precio_n, connexion);
                    } catch (NumberFormatException NFE) {
                        out.print("Error de conversion numérica: " + NFE);
                    } catch (Exception e) {
                        out.print("Ocurrió un error, " + e);
                    }
            }
        }

    %>

</main>

<jsp:include page="common/footer.jsp"/>

</body>

</html>

