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
        <div  style="padding-top: 320px; padding-left: 16%;">
            <h1>Registrar Nueva Consulta</h1>
            <div class="col-sm-10" id="div2">
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
            <div id="datosConsulta">
                <form action='<%=request.getContextPath()%>/ManejadorPaciente' method='POST'>

                    <div class="form-group" id="div4">
                        <br>
                        <label>Tipo Consulta</label> 
                        <select name = "categoriaElegida">
                            <c:forEach var="element.getNombre()" items="${Servicios}">
                                <option value="${element.getNombre()}">${element.getNombre()}</option>
                            </c:forEach>
                        </select>
                        <div class="form-group" id="div1">
                            <label class="titulos" id="as">Fecha Consulta </label>
                            <div class="col-sm-10">
                                <input class="fechas" type="date" name="fecha" size="20" required>
                            </div>
                            <div>
                                <input type="time" name="hora" min="08:00" max="18:00" step="3600">
                            </div>
                        </div>
                        <input type="submit" name='boton' id="boton" class="btn btn-success"     value="Guardar">
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
