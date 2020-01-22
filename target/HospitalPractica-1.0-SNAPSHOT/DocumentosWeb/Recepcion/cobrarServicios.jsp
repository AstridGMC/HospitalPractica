<%-- 
    Document   : cobrarServicios
    Created on : 18/11/2019, 12:43:35 AM
    Author     : astridmc
--%>

<%@page import="hospitalPractica.Backend.ServiciosMedicos.Servicio"%>
<%@page import="java.util.ArrayList"%>
<%@page import="hospitalPractica.Backend.Paciente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href = "<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
        
        <title>Cobro Servicios</title>
    </head>
    <body>
        <h1>Cobrar Servicios</h1>
        <%@include  file= "headerRecepcionista.jsp"%>
        <div  style="padding-top: 320px; padding-left: 16%;">

            <div class="col-sm-10" id="div2">
                <h1>Servicios CLiente</h1>
                <div id="div5">
                    <form action='<%=request.getContextPath()%>/ManejadorPaciente' method='POST'>
                        <label>CUI Paciente</label>
                        <input class="form__input" type="number"  oninput="maxLengthCheck(this)"
                               name="cuiPaciente" id="cui" placeholder ="cui (13 digitos)" required>
                        <input type="submit" name="boton" id="botonBuscar" value="buscar Servicios Cliente">
                    </form>
                </div>
                <%Paciente paciente = (Paciente) request.getAttribute("paciente");
                    if (request.getAttribute("encontrado") != null) {
                        if ((boolean) request.getAttribute("encontrado")) {
                            request.setAttribute("cuiPaciente", paciente.getCui());
                            session.setAttribute("cuiPaciente", paciente.getCui());
                            System.out.println(paciente.getNombres());
                %>
                <div id="infoCliente" >
                    <label>Nombre:</label>
                    <label class="info"> <%=paciente.getNombres()%> <%=paciente.getApellidos()%></label>
                    <br>
                    <label>CUI:</label>
                    <label class="info"> <%=paciente.getCui()%> </label>
                </div>
                <%} else if ((boolean) request.getAttribute("encontrado") == false) {%>
                <div id="noEncontrado">
                    <h3>El Paciente  con cui = <%=request.getAttribute("cui")%> no se encuentra en la base de datos</h3>
                    <input type="button" id="boton" onClick= 'window.open("<%=request.getContextPath()%>/DocumentosWeb/Recepcion/nuevoCliente.jsp", "MsgWindow", "width=890, height=750, top=0,left=0");'  value="Registrar">

                </div>
                <%}
                    }%>
            </div>
            <%if (request.getAttribute("listar") != null) {%>
            <div id="table">
                <table class="table">
                    <thead style="background-color: #b9bbbe">
                        <tr>
                            <th scope="col">Nombre</th> 
                            <th scope="col">fecha</th> 
                            <th scope="col">precio</th> 
                            <th scope="col">cobrar </th>
                        </tr>
                    </thead>
                    <%
                            request.setAttribute("principio", null);
                            ArrayList<Servicio> servicios = (ArrayList) request.getAttribute("serviciosClientes");
                            for (int i = 0; i < servicios.size(); i++) {
                                Servicio servicio = servicios.get(i);
                    %>
                    <tr> 
                        <td> <%=servicio.getNombreServicio()%></td>
                        <td> <%=servicio.getFecha()%></td>
                        <td> <%=servicio.getPrecioServicio()%></td>
                        
                        <td><form action="<%=request.getContextPath()%>/ManejadorPaciente" method="POST">
                                <div class="form-group" id="div4">
                                    <div class="col-sm-10">
                                        <input type="text" name="cuiCliente" style="display: none;" value="<%=paciente.getCui()%>">
                                        <input type="text" name="precio" style="display: none;" value="<%=servicio.getPrecioServicio()%>">
                                        <input type="text" name="area" style="display: none;" value="<%=servicio.getAreaHospital()%>">
                                        <input type="text" name="nombre" style="display: none;" value="<%=servicio.getNombreServicio()%>">
                                        Fecha de Cobro  <input class="fechas" type="date" name="fecha" size="20" id="fechaActual" required>
                                        <input type="submit" name="boton"  id="botonFinalizar" value ="Cobrar Servicio">
                                    </div>
                                </div>
                            </form></td>
                    </tr>
                    <%}

                    %>
                </table>

            </div>
            <div  style="padding-left: 20%;">
                <form action="<%=request.getContextPath()%>/ManejadorPaciente" method="POST">
                    <div class="form-group" id="div4">
                        <div class="col-sm-10">
                            <% request.setAttribute("servicios", servicios);%>
                            Fecha de Cobro  <input class="fechas" type="date" name="fecha" size="20" id="fechaActual" required>
                            <input type="submit" name="boton"  id="botonFinalizar" value ="Cobrar Todos los Servicios">
                        </div>
                    </div>
                </form>
                <%}%>
            </div>

        </div>
        <div>

        </div>
    </body>
</html>
