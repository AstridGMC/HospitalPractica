<%-- 
    Document   : headerAdministrador
    Created on : 29/10/2019, 04:32:32 PM
    Author     : astridmc
--%>

<link href= "<%=request.getContextPath()%>/bootstrap/css/estiloBarra.css" rel="stylesheet" type ="text/css">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String cui = (String) session.getAttribute("cui");
    String nombre = (String) session.getAttribute("nombreDelUsuario");
    request.setAttribute("imagen", "imagenPerfil");
%>
<header>
    <div id="contenedor" style="background-image: url('<%=request.getContextPath()%>/DocumentosWeb/imagenes/11.jpg')">
        <div id ="divIMG">
            <img src="<%=request.getContextPath()%>/Controler" alt="Mi imagen" width="200" name="imagen" height="220" value="IMG">
        </div>
        <div id ="div7" style="color:white;">
            <h1>Administrador</h1>
            <h1> <%=nombre%> </h1>
        </div>
        <br>
    </div>
        <div >
        <nav>
            <div class="navbar1">
                <a href="<%=request.getContextPath()%>/DocumentosWeb/Administracion/paginaInicioA.jsp">Inicio</a>
                <a href="<%=request.getContextPath()%>/Empleados?pm=ModificarMiUsuario">Modificar Usuario</a>
                <div class="dropdown3">
                    <button class="dropbtn3">Empleados
                        <i class="fa fa-caret-down"></i>
                    </button>
                    <div class="dropdown-content1">
                        <a href="<%=request.getContextPath()%>/Empleados?pm=contratoNuevo">Contrato Nuevo</a>
                        <a href="<%=request.getContextPath()%>/DocumentosWeb/Administracion/terminarContrato.jsp">Fin de Contrato</a>
                        <a href="<%=request.getContextPath()%>/Vacaciones?pm=cambiarVacaciones">Cambio de Vacaciones</a>
                        <a href="<%=request.getContextPath()%>/DocumentosWeb/Administracion/registrarVaccionesRecividas.jsp">Vacaciones Recividas</a>
                        <a href="<%=request.getContextPath()%>/DocumentosWeb/Administracion/aumentosEmpleado.jsp">Aumentos</a>
                        <a href="<%=request.getContextPath()%>/Empleados?pm=ModificarEmpleado">Modificar Empleados</a>
                        <a href="<%=request.getContextPath()%>/Empleados?pm=ModificarUsuario">Eliminar Desactivar Usuarios</a>
                    </div>
                </div>
                <div class="dropdown3">
                    <button class="dropbtn3">Administracion
                        <i class="fa fa-caret-down"></i>
                    </button>
                    <div class="dropdown-content1">
                        <a href="<%=request.getContextPath()%>/Vacaciones?pm=Cambiar Numero De Dias">Cambiar Dias de Vacaciones</a>
                        <a href="<%=request.getContextPath()%>/Administracion?pm=nuevaArea">Agregar Nueva Area</a>
                        <a href="<%=request.getContextPath()%>/Administracion?pm=modificacionAreas">Modificar Area</a>
                        <a href="<%=request.getContextPath()%>/Administracion?pm=numeroHabitacion">Agregar Habitacion nueva</a>
                        <a href="<%=request.getContextPath()%>/ManejadorAtencion?pm=nuevoServicioHospital">Agregar Servicio Hospital</a>
                        <a href="<%=request.getContextPath()%>/DocumentosWeb/Administracion/nuevoServicioEspecial.jsp">Agregar Servicio Especial</a>
                    </div>
                </div>
                <div class="dropdown3">
                    <button class="dropbtn3">Cambios de Precios
                        <i class="fa fa-caret-down"></i>
                    </button>
                    <div class="dropdown-content1">
                        <a href="<%=request.getContextPath()%>/Administracion?pm=modificacionMedicina1">Medicina</a>
                        <a href="<%=request.getContextPath()%>/Administracion?pm=modificacionHabitaciones">Mantenimiento de Habitaciones</a>
                        <a href="<%=request.getContextPath()%>/ManejadorAtencion?pm=modificarTarifario">Modificar Tarifario</a>
                    </div>
                </div>
                 <div class="dropdown3">
                    <button class="dropbtn3">Reportes
                        <i class="fa fa-caret-down"></i>
                    </button>
                    <div class="dropdown-content1">
                        <a href="<%=request.getContextPath()%>/ReportesAdmin?boton=redirigir">Empleados Contratados</a>
                        <a href="<%=request.getContextPath()%>/ReportesAdmin?boton=redirigirRetirados">Empleados Retirados</a>
                        <a href="<%=request.getContextPath()%>/DocumentosWeb/Administracion/reporteMedicos.jsp">Medicos</a>
                        <a href="#">Ganancias</a>
                        <a href="<%=request.getContextPath()%>/DocumentosWeb/Administracion/ReporteVentasPorFarmaceuta.jsp">Ventas Por Farmaceuta</a>
                    </div>
                </div>
            </div>
        </nav>
    </div>
</header>
                    
                    