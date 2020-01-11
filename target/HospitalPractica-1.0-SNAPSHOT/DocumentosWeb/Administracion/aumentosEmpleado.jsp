<%-- 
    Document   : aumentosEmpleado
    Created on : 15/11/2019, 12:36:08 PM
    Author     : astridmc
--%>

<%@page import="hospitalPractica.Backend.Administracion.Empleado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href = "<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar Aumento</title>
    </head>
    <body>
        <%if (session.getAttribute("Guardado") != null) {
                String strExpired = (String) session.getAttribute("Guardado");
                System.out.println(strExpired);
                if (strExpired.equals("Guardado")) {
                    session.setAttribute("Guardado", null);
        %>
        <script>
            alert("las vacaciones han sido marcadas como Finalizadas");
        </script>
        <%} else if (strExpired.equals("noGuardado")) {
            session.setAttribute("Guardado", null);%>
        <script>
            alert("las vacaciones del empleado no han podido ser marcadas como Finalizadas");
        </script>
        <% }
            }%>
        <%@include  file= "headerAdministracion.jsp"%>
        <div style="padding-top: 320px; padding-left: 16%; align-content: center;">
            <h1>Aumentos</h1>
            <div>
                <div class="col-sm-10" id="div2">
                    <div id="div5">
                        <form action='<%=request.getContextPath()%>/Vacaciones' method='POST'>
                            <label>CUI Paciente</label>
                            <input class="form__input" type="number"  oninput="maxLengthCheck(this)"
                                   name="cuiEmpleado" id="cuiEmpleado" placeholder ="cui (13 digitos)" required>
                            <input type="submit" name="boton" id="botonBuscar" value="Buscar Info Vacaciones">
                        </form>
                    </div>

                    <%
                        if (request.getAttribute("encontrado") != null) {
                            Empleado empleado = (Empleado) request.getAttribute("empleado");
                            if ((boolean) request.getAttribute("encontrado")) {
                                System.out.println(empleado.getNombre());
                                if ((boolean) request.getAttribute("tomadas") == false) {
                    %>

                    <div id="infoCliente" >
                        <label>Nombre:</label>
                        <label class="info"> <%=empleado.getNombre()%> <%=empleado.getApellido()%></label>
                        <br>
                        <label>CUI:</label>
                        <label class="info"> <%=empleado.getCui()%> </label><br>
                        <%if (empleado.getVacaciones().getFechaVacacionesInicio() != null) {%>
                        <H5>VACACIONES:</H5>
                        <label>Fecha Inicio:</label>
                        <label class="info"> <%=empleado.getVacaciones().getFechaVacacionesInicio()%> </label>
                        <label>Fecha Final:</label> 
                        <label class="info"> <%=empleado.getVacaciones().getFechaVacacionesFinal()%> </label> <br>
                        <%}%>
                    </div>
                    <%} else {%>

                    <h3>El Empleado  con cui = <%=request.getAttribute("cuiEempleado")%> ya ha recibido sus vacaciones</h3>

                    <%}
                } else if ((boolean) request.getAttribute("encontrado") == false) {%>
                    <div id="noEncontrado">
                        <h3>El Empleado  con cui = <%=request.getAttribute("cuiEempleado")%> no se encuentra en la base de datos</h3>
                        <%}
                        }%>
                    </div>
                </div>
            </div>
                    <div>
                        <form action='<%=request.getContextPath()%>/Vacaciones'  method="POST">
                    <div class="form-group" id="div3">
                        <div class="col-sm-10">
                            <label >Nuevo Salario del Empleado:</label>
                            <input  type='number' name="aumento" id="aumento" placeholder ="aumento" required>
                        </div>
                         <input type="submit" name="boton" id="nuevaHabitacion" value="Guardar Aumento">
                    </div>
                </form>
                    </div>
        </div>
    </body>
</html>
