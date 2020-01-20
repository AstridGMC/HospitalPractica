<%-- 
    Document   : ReporteVentasPorFarmaceuta
    Created on : 18/01/2020, 03:50:05 AM
    Author     : astridmc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href = "<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
        <title>Ventas por Empleado</title>
    </head>
    <body>
        <%@include  file= "headerAdministracion.jsp"%>
        <div style="padding-top: 320px; align-content: center;">
            <form method="POST" action="<%=request.getContextPath()%>/ReportesAdmin">
                <div style="padding-left: 100px; padding-top: 50px; padding-bottom:50px; font-size: 30px;">
                    Buscar por cui Empleado:
                    <input type="number" oninput="maxLengthCheck(this)" name="cuiF" style="width: 400px; font-size: 30px;">
                    <input type="submit" name="reporte"  class="btn btn-success"  value="Buscar por Empleado">
                </div>
            </form>
            <div style= "display: inline; padding: 10px; float: left; width: 100%; text-align: justify;">
                <embed src="<%=request.getContextPath()%>/ReportesAdmin?pm1=<%=request.getAttribute("cuiF")%>" type="application/pdf" width="100%" height=750px" />
            </div>

        </div>
    <script>
        function maxLengthCheck(object)
        {
            if (object.value.length > 13)
                object.value = object.value.slice(0, object.maxLength);
        }

    </script>
    </body>
</html>
