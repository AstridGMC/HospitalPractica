<%-- 
    Document   : ingresarNuevaConsulta
    Created on : 4/11/2019, 09:19:14 PM
    Author     : astridmc
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="hospitalPractica.Backend.Paciente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href = "<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
        <link href= "<%=request.getContextPath()%>/bootstrap/css/estiloRegistroCita.css" rel="stylesheet" type ="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ingresoNuevaConsulta</title>
    </head>
    <body>
        <%@include  file= "headerRecepcionista.jsp"%>
        <%  
            if (session.getAttribute("Guardado") != null) {
                String strExpired = (String) session.getAttribute("Guardado");
                System.out.println(strExpired);
                if (strExpired.equals("Guardado")) {
                    session.setAttribute("Guardado", null);
                %>
                <script>
                    alert("Servicio Registrado");
                </script>
                <% } else if (strExpired.equals("noGuardado")) {
                    session.setAttribute("Guardado", null);%>
                <script>
                    alert(" el servicio  no ha podido registrarse");
                </script>
                <% }
            }%>
        <div  style="padding-top: 320px; padding-left: 16%;">
            <h1>Registrar Servicio</h1>
            <div class="col-sm-10" id="div2">
                <div id="div5">
                    <form action='<%=request.getContextPath()%>/ManejadorPaciente' method='POST'>
                        <label>CUI Paciente</label>
                        <input class="form__input" type="number"  oninput="maxLengthCheck(this)"
                               name="cuiPaciente" id="cui" placeholder ="cui (13 digitos)" required>
                        <input type="submit" name="boton" id="botonBuscar" value="buscarCliente">
                    </form>
                </div>
           <form action='<%=request.getContextPath()%>/ManejadorPaciente' method='POST'>
                <%Paciente paciente = (Paciente) request.getAttribute("paciente");
                    if (request.getAttribute("encontrado") != null) {
                        if ((boolean) request.getAttribute("encontrado")) {
                            System.out.println(paciente.getNombres());
                %>
                <input type="text" style="display: none" name="cuiCliente" value="<%=paciente.getCui()%>">
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
            <div id="datosConsulta">
               
                    
                    
                    <div class="form-group" id="div4">
                        <br>
                        <div style="font-size:30px;">
                            <label>Tipo Consulta</label> 
                            <select name = "categoriaElegida">
                                <c:forEach var="element" items="${servicios}">
                                    <option  value="${element.getNombreServicio()}"> ${element.getNombreServicio()}  Precio: Q. ${element.getPrecioServicio()}   </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group" id="div1" style="font-size:30px;">
                            <div class="col-sm-10">
                                Fecha Consulta <input class="fechas" type="date" name="fecha" size="20" required>
                            </div>
                            <div>
                                <input name="rango" value="Recepcionista" style="display: none;">
                                Hora: <input type="time" name="hora" min="08:00" max="18:00" step="3600">
                            </div>
                        </div>
                        <input type="submit" name='boton' id="boton" class="btn btn-success"     value="Guardar Servicio Cliente">
                    </div>
                </form>
            </div>
            <div id="terminarIngresoConsulta">

            </div>
        </div>
        <script>
            function maxLengthCheck(object)
            {
                if (object.value.length > 13)
                    object.value = object.value.slice(0, object.maxLength)
            }
        </script>
    </body>
</html>
