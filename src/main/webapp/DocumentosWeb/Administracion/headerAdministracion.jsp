<%-- 
    Document   : headerAdministrador
    Created on : 29/10/2019, 04:32:32 PM
    Author     : astridmc
--%>

<link href= "<%=request.getContextPath()%>/bootstrap/css/estiloBarra.css" rel="stylesheet" type ="text/css">
<link href = "bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String cui = (String) session.getAttribute("cui");
    String nombre = (String) session.getAttribute("nombreDelUsuario");
    request.setAttribute("imagen", "imagenPerfil");
%>
<header>
    <div class="container" id="contenedor" style="background-image: url('<%=request.getContextPath()%>/DocumentosWeb/imagenes/11.jpg')">
        <div id ="divIMG">
            <img src="<%=request.getContextPath()%>/Controler" alt="Mi imagen" width="200" name="imagen" height="220" value="IMG">
        </div>
        <div id ="div7">
            <h1>Administrador</h1>
            <h1> <%=nombre%> </h1>
        </div>
        <br>
    </div>
        <div >
        <nav>
            <div class="navbar">
                <a href="#home">Inicio</a>
                <a href="#news">Cambiar Imagen</a>
                <div class="dropdown">
                    <button class="dropbtn">Empleados
                        <i class="fa fa-caret-down"></i>
                    </button>
                    <div class="dropdown-content">
                        <a href="#">Contratos</a>
                        <a href="#">Despidos</a>
                        <a href="#">Cambio de Vacaciones</a>
                        <a href="#">Vacaciones Recividas</a>
                        <a href="#">Aumentos</a>
                    </div>
                </div>
                <div class="dropdown">
                    <button class="dropbtn">Administracion
                        <i class="fa fa-caret-down"></i>
                    </button>
                    <div class="dropdown-content">
                        <a href="#">Cambiar Dias de Vacaciones</a>
                        <a href="#">Agregar Nueva Area</a>
                        <a href="#">Agregar Habitacion nueva</a>
                    </div>
                </div>
                <div class="dropdown">
                    <button class="dropbtn">Cambios de Precios
                        <i class="fa fa-caret-down"></i>
                    </button>
                    <div class="dropdown-content">
                        <a href="#">Medicina</a>
                        <a href="#">Mantenimiento de Habitaciones</a>
                        <a href="#">Habitaciones</a>
                    </div>
                </div>
                 <div class="dropdown">
                    <button class="dropbtn">Reportes
                        <i class="fa fa-caret-down"></i>
                    </button>
                    <div class="dropdown-content">
                        <a href="#">Empleados</a>
                        <a href="#">Ganancias</a>
                        <a href="#">Habitaciones</a>
                    </div>
                </div>
            </div>
        </nav>
    </div>
</header>