<%-- 
    Document   : headerRecepcionista
    Created on : 4/11/2019, 09:24:44 PM
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
    <div id="contenedor" style="background-image: url('<%=request.getContextPath()%>/DocumentosWeb/imagenes/portada1.jpeg')">
        <div id ="divIMG">
            <img src="<%=request.getContextPath()%>/Controler" alt="Mi imagen" width="200" name="imagen" height="220" value="IMG">
        </div>
        <div id ="div7" style="color:#10242B;">
            <h1>Recepcion</h1>
            <h1> <%=nombre%> </h1>
        </div>
        <br>
    </div>
        <div >
        <nav>
             <div class="navbar1">
                <a href="<%=request.getContextPath()%>/DocumentosWeb/Recepcion/paginaInicioR.jsp">Inicio</a>
                <a href="<%=request.getContextPath()%>/Empleados?pm=ModificarMiUsuario">Modificar Usuario</a>
                <div class="dropdown3">
                    <button class="dropbtn3">Clientes
                        <i class="fa fa-caret-down"></i>
                    </button>
                    <div class="dropdown-content1">
                        <a href="<%=request.getContextPath()%>/DocumentosWeb/Recepcion/nuevoCliente.jsp">Nuevo Cliente</a>
                        <a href="<%=request.getContextPath()%>/ManejadorPaciente?pm=ModificarPacientesR">Editar Datos </a>
                    </div>
                </div>
                <div class="dropdown3">
                    <button class="dropbtn3">Citas
                        <i class="fa fa-caret-down"></i>
                    </button>
                    <div class="dropdown-content1">
                        <a href="<%=request.getContextPath()%>/ManejadorPaciente?pm=NuevaConsulta">Agendar Nueva Cita</a>
                        <a href="#">Cobrar Cita</a>
                        <a href="#">Ver Citas</a>
                        <a href="#">Cambiar Citas</a>
                    </div>
                </div>
            </div>
        </nav>
    </div>
</header>