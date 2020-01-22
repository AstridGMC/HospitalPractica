<%-- 
    Document   : nuevaArea
    Created on : 15/11/2019, 03:10:47 PM
    Author     : astridmc
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href = "<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
        <title>Registro nueva Area</title>
    </head>
    <body>
        <%  if (session.getAttribute("Guardado") != null) {
                String strExpired = (String) session.getAttribute("Guardado");
                System.out.println(strExpired);
                if (strExpired.equals("Guardado")) {
                    session.setAttribute("Guardado", null);
        %>
        <script>
            alert("la nueva area del hospital se ha  Registrado Con Exito");
        </script>
        <% } else if (strExpired.equals("noGuardado")) {
            session.setAttribute("Guardado", null);%>
        <script>
            alert("la nueva area del hospital  no ha podido Registrarse");
        </script>
        <% }
            }%>
        <%@include  file= "headerAdministracion.jsp"%>
        <div style="padding-top: 320px;  font-size: 25px;">
            <h1 style="padding-left: 20%">Area del Hospital Nueva</h1>
            <form action='<%=request.getContextPath()%>/Administracion' method="POST">
                 <div style="  margin-left: 25%; margin-right: auto; font-size: 25px;">
                <div style="padding-top: 50px;">
                    <div class="col-sm-10">
                        <label style="font-size: 30px;"  >Nombre de la Nueva Area del Hospital:</label>
                        <input   style="font-size: 30px;" type='text' name="nombreArea" id="nombreArea" placeholder ="nombre" required>
                    </div>
                    <br>
                </div>
                     <div>
                    <h3>Rangos que perteneceran al Area</h3><br>
                    <c:forEach var="element" items="${rangos}">
                        <label> <input  type="checkbox" name="misRangos" value="${element}">${element}</label><br>
                        </c:forEach>
                    
                </div>
                <input type="submit" name="boton" id="btnGuardar" class="btn btn-success" value="Guardar Area">
                 </div>
            </form>
        </div>
        
    </body>
</html>
