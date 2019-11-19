<%-- 
    Document   : cambiarNumeroDiasVacaciones
    Created on : 10/11/2019, 03:38:33 PM
    Author     : astridmc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href = "<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
        <title>Cambiar Nuemero de Dias</title>
    </head>
    <body>
        <%@include  file= "headerAdministracion.jsp"%>
        <div style="padding-top: 320px; padding-left: 16%; align-content: center;">
            <h1>Cambiar Numero De Dias De Vacaciones </h1>
            <h2 style="display: inline; text-align: center;">  Numero de dias de Vacaciones Actualmente:</h2>
            <label style="display: inline; font-size: 50px; color: red;"> <%=request.getAttribute("numeroDias")%>  Dias </label>
            <br>
            <br>
            <form action='<%=request.getContextPath()%>/Vacaciones'  method="POST" >
                <div class="form-group" id="div3" style="font-size: 30px;">
                    <div class="col-sm-10">
                        <label>Numero de Dias de Vacaciones Nuevo: </label>
                        <input type='number'  name="noDiasVacaciones" id="noDiasVacaciones" placeholder ="numero de Dias" required>
                    </div>
                    <input type="submit" name='boton' id="boton" class="btn btn-success" style="margin-top: 50px; margin-left:30%; margin-right: auto;"    value="Cambiar Numero De Dias">
                </div> 
            </form>
        </div>

    </body>
</html>
