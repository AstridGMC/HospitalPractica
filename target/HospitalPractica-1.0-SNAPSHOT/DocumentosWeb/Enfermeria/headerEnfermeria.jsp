<%-- 
    Document   : headerEnfermera
    Created on : 29/10/2019, 06:46:45 PM
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
    <div class="container" id="contenedor" style="background-image: url('<%=request.getContextPath()%>/DocumentosWeb/imagenes/portadaAdmin.jpg')">
        <div id ="divIMG">
            <img src="<%=request.getContextPath()%>/Controler" alt="Mi imagen" width="200" name="imagen" height="220" value="IMG">
        </div>
        <div id ="div7">
            <h1>Enfermer@</h1>
            <h1> <%=nombre%> </h1>
        </div>
        <br>
    </div>
    <div >
        <nav>
            <div class="navbar1">
                <a href="#home">Inicio</a>
                <a href="<%=request.getContextPath()%>/Empleados?pm=ModificarMiUsuario">Modificar Usuario</a>
                <div class="dropdown3">
                    <button class="dropbtn3">Pacientes
                        <i class="fa fa-caret-down"></i>
                    </button>
                    <div class="dropdown-content">
                        <a href="#">Internar</a>
                        <a href="#">Dar de Alta</a>
                    </div>
                </div>
                <div class="dropdown3">
                    <button class="dropbtn3">Administracion
                        <i class="fa fa-caret-down"></i>
                    </button>
                    <div class="dropdown-content1">
                        <a href="#">Cambiar Dias de Vacaciones</a>
                        <a href="#">Agregar Nueva Area</a>
                        <a href="#">Agregar Habitacion nueva</a>
                    </div>
                </div>
                <div class="dropdown3">
                    <button class="dropbtn3">Cambios de Precios
                        <i class="fa fa-caret-down"></i>
                    </button>
                    <div class="dropdown-content1">
                        <a href="#">Medicina</a>
                        <a href="#">Mantenimiento de Habitaciones</a>
                        <a href="#">Habitaciones</a>
                    </div>
                </div>
            </div>
        </nav>
    </div>
</div>
</header>
