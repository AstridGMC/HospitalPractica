<%-- 
    Document   : paginaInicioR
    Created on : 5/11/2019, 08:22:54 PM
    Author     : astridmc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <%@include  file= "headerRecepcionista.jsp"%>
                 
        <div style="padding-top: 320px; padding-left: 16%;">
            <h1>BIENVENIDO RECEPCIONISTA</h1>
            <div class="card" style="width: 90%;">
                <img  src="<%=request.getContextPath()%>/DocumentosWeb/imagenes/recepcionista.jpg" class="card-img-top" alt="..." style="width: 100%;">
                <div class="card-body">
                    <h5 class="card-title">BIENVENIDO Recepcionista!!</h5>
                    <p class="card-text"></p>
                    <br>
                    <br>
                </div>
            </div>
        </div>
    </body>
</html>
