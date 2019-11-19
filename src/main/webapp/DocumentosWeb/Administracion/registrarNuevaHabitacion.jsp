<%-- 
    Document   : registrarNuevaHabitacion
    Created on : 14/11/2019, 05:39:07 PM
    Author     : astridmc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <link href= "<%=request.getContextPath()%>/bootstrap/css/estiloNuevoContrato.css" rel="stylesheet" type ="text/css">
        <link href = "<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nueva Habitacion</title>
    </head>
    <body>
        <%  if (session.getAttribute("Guardado") != null) {
                String strExpired = (String) session.getAttribute("Guardado");
                System.out.println(strExpired);
                if (strExpired.equals("Guardado")) {
                    session.setAttribute("Guardado", null);
        %>
        <script>
            alert("la habitacion nueva se ha  Registrado Con Exito");
        </script>
        <% } else if (strExpired.equals("noGuardado")) {
            session.setAttribute("Guardado", null);%>
        <script>
            alert("la habitacion nueva se ha  no ha podido Registrarse");
        </script>
        <% }
            }%>
        <%@include  file= "headerAdministracion.jsp"%>
        <div style="padding-top: 320px; padding-left: 16%; align-content: center;">
            <h1 style="padding-left: 20%; font-size: 60px;">Registro Nueva Habitacion</h1>
            <div>
                <h2>Habitacion numero  <%=request.getAttribute("numeroHabitacion")%></h2>
                <form action='<%=request.getContextPath()%>/Vacaciones'  method="POST">
                    <div id="nuevaHab">
                        <div class="col-sm-10">
                            <label >Precio de la Habitacion:</label>
                            <input  type='number' name="precioHabitacion" id="precioHabitacion" placeholder ="precio Habitacion" required>
                        </div>
                        <br>
                        <div class="form-group" id="div3">
                            <div class="col-sm-10">
                                <label>Costo de Mantenimineto:</label>
                                    <input  type='number'name="costoHabitacion" id="costoHabitacion" placeholder ="costo de Habitacion" required>
                            </div>
                        </div>
                        <input type="submit" name="boton" id="nuevaHabitacion" value="Guardar Habitacion" style="margin-left: 20%;">
                    </div>
                </form>
            </div>
        </div>

    </body>
</html>
