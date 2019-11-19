<%-- 
    Document   : paginaInicio
    Created on : 2/11/2019, 05:41:32 PM
    Author     : astridmc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href = "<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include  file= "headerAdministracion.jsp"%>
                 
        <div style="padding-top: 320px; padding-left: 16%;">
            <h1>BIENVENIDO ADMINISTRADOR</h1>
            <div class="card" style="width: 90%;">
                <img  src="<%=request.getContextPath()%>/DocumentosWeb/imagenes/portadaFarmacia3.jpg" class="card-img-top" alt="..." style="width: 100%;">
                <div class="card-body">
                    <h5 class="card-title">BIENVENIDO Farmaceuta!!</h5>
                    <p class="card-text"></p>
                    <br>
                    <br>
                </div>
            </div>
        </div>
    </body>
</html>
