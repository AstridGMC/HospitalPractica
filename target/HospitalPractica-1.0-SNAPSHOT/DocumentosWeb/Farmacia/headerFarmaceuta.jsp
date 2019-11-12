<%-- 
    Document   : headerFarmaceuta
    Created on : 29/10/2019, 08:16:58 PM
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
    <div   id="contenedor" style="background-image: url('<%=request.getContextPath()%>/DocumentosWeb/imagenes/portadaFarmacia1.jpg')">
        <div id ="divIMG">
            <img src="<%=request.getContextPath()%>/Controler" alt="Mi imagen" width="200" name="imagen" height="220" value="IMG">
        </div>
        <div id ="div7" >
            <h1>Farmaceuta</h1>
            <h1> <%=nombre%> </h1>
        </div>
        <br>
    </div>
    <div >
        <nav>
            <div class="navbar1">
                <a href="<%=request.getContextPath()%>/DocumentosWeb/Farmacia/paginaInicioF.jsp">Inicio</a>
                <a href="<%=request.getContextPath()%>/EditarPerfil">Cambiar Imagen</a>
                <a href="<%=request.getContextPath()%>/RegistrarVentaNueva">Nueva Venta</a>
                <a href="<%=request.getContextPath()%>/DocumentosWeb/Farmacia/medicinaNueva.jsp">Ingresar Medicina Nueva</a>
                <div class="dropdown3">
                    <button class="dropbtn3">Actualizar
                        <i class="fa fa-caret-down"></i>
                    </button>
                    <div class="dropdown-content1">
                        <a href="<%=request.getContextPath()%>/ActualizarInventario">Inventario</a>
                        <a href="<%=request.getContextPath()%>/RegistradorMedicina?pagina=ActualizarMedicina">Medicina</a>
                    </div>
                </div>
                <div class="dropdown3">
                    <button class="dropbtn3">Reportes
                        <i class="fa fa-caret-down"></i>
                    </button>
                    <div class="dropdown-content1">
                        <a href="#">Inventario</a>
                        <a href="#">Ganancias</a>
                    </div>
                </div>
            </div>
        </nav>
    </div>
</div>
</header>
