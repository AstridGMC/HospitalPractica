<%-- 
    Document   : factura
    Created on : 19/01/2020, 01:22:27 AM
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

        <%
            if (session.getAttribute("Guardado") != null) {
                String strExpired = (String) session.getAttribute("Guardado");
                System.out.println(strExpired);
                if (strExpired.equals("Guardado")) {
                    session.setAttribute("Guardado", null);
        %>
        <script>
            alert("la venta ha sido Realizada con exito");
        </script>
        <% } else if (strExpired.equals("noGuardado")) {
                    session.setAttribute("Guardado", null);%>
        <script>
            alert(" la venta  no ha podido registrarse");
        </script>
        <% }
                    }%>
        <%@include  file= "headerFarmaceuta.jsp"%>
        <div style="padding-top: 320px; align-content: center;">
            <div style= "display: inline; padding: 10px; float: left; width: 100%; text-align: justify;">
                <embed src="<%=request.getContextPath()%>/ReportesAdmin?pm1=factura" type="application/pdf" width="100%" height=750px" />
            </div>
        </div>
    </body>
</html>
