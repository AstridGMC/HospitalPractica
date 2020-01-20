<%-- 
    Document   : reportesMedicina
    Created on : 17/01/2020, 06:14:51 PM
    Author     : astridmc
--%>

<%@page import="java.util.Map"%>
<%@page import="Servlet.InicioSesion"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reporte Medicina</title>
    </head>
    <body>
        <%@include  file= "headerFarmaceuta.jsp"%>
        <%System.out.println(request.getContextPath());%>
        <div style="padding-top: 320px;">
           <div style= "display: inline; padding: 10px; float: left; width: 100%; text-align: justify;">
            <embed src="<%=request.getContextPath()%>/RegistradorMedicina?boton=Generar Reporte" type="application/pdf" width="100%" height=750px" />
        </div>
</div>
    </body>
</html>
