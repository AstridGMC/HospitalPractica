<%-- 
    Document   : cobrarServicios
    Created on : 18/11/2019, 12:43:35 AM
    Author     : astridmc
--%>

<%@page import="hospitalPractica.Backend.Paciente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cobro Servicios</title>
    </head>
    <body>
        <h1>Cobrar Servicios</h1>
        <%@include  file= "headerRecepcionista.jsp"%>
        <div  style="padding-top: 320px; padding-left: 16%;">
            
            <div class="col-sm-10" id="div2">
                <h1>Registrar Nueva Consulta</h1>
                <div id="div5">
                    <form action='<%=request.getContextPath()%>/ManejadorPaciente' method='POST'>
                        <label>CUI Paciente</label>
                        <input class="form__input" type="number"  oninput="maxLengthCheck(this)"
                               name="cuiPaciente" id="cui" placeholder ="cui (13 digitos)" required>
                        <input type="submit" name="boton" id="botonBuscar" value="buscarCliente">
                    </form>
                </div>
                <%Paciente paciente = (Paciente) request.getAttribute("paciente");
                    if (request.getAttribute("encontrado") != null) {
                        if ((boolean) request.getAttribute("encontrado")) {
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
        </div>
            <div>
                
            </div>
    </body>
</html>
