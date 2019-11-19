<%-- 
    Document   : registrarVaccionesRecividas
    Created on : 15/11/2019, 09:47:13 AM
    Author     : astridmc
--%>

<%@page import="hospitalPractica.Backend.Administracion.Empleado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href= "<%=request.getContextPath()%>/bootstrap/css/estiloCambiarVacacionesEmpleado.css" rel="stylesheet" type ="text/css">
        <link href = "<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar Vacacionmes</title>
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
            <h1>Registrar Vacaciones Recibidas</h1>
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
                            if ((boolean) request.getAttribute("tomadas")==false) {
                %>

                <div id="infoCliente" >
                    <label>Nombre:</label>
                    <label class="info"> <%=empleado.getNombre()%> <%=empleado.getApellido()%></label>
                    <br>
                    <label>CUI:</label>
                    <label class="info"> <%=empleado.getCui()%> </label><br>
                    <%if(empleado.getVacaciones().getFechaVacacionesInicio()!=null){%>
                    <H5>VACACIONES:</H5>
                    <label>Fecha Inicio:</label>
                    <label class="info"> <%=empleado.getVacaciones().getFechaVacacionesInicio()%> </label>
                    <label>Fecha Final:</label> 
                    <label class="info"> <%=empleado.getVacaciones().getFechaVacacionesFinal()%> </label> <br>
                    <%}%>
                </div>
                <div>
                <form action='<%=request.getContextPath()%>/Vacaciones' method="POST">
                    <input name="cuiEmpleado" style="display:none;" value="<%=request.getAttribute("cuiEmpleado")%>" > 
                    <input style="margin-top: 40px;" type="submit" name='boton' id="boton" class="btn btn-success" value="Marcar Como Finalizadas">  
                </form>
                    </div>
                <%} else {%>
                
                <h3>El Empleado  con cui = <%=request.getAttribute("cuiEmpleado")%> ya ha recibido sus vacaciones</h3>

                <%} }else if ((boolean) request.getAttribute("encontrado") == false) {%>
                <div id="noEncontrado">
                    <h3>El Empleado  con cui = <%=request.getAttribute("cuiEmpleado")%> no se encuentra en la base de datos</h3>
                    <%}%>
                        
                </div>
            </div>
            
                <%}%>
            
        </div>
    </body>
</html>
