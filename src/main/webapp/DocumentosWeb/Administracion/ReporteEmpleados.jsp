<%-- 
    Document   : ReporteEmpleados
    Created on : 20/01/2020, 12:36:21 AM
    Author     : astridmc
--%>

<%@page import="hospitalPractica.Backend.Administracion.AreaHospital"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href = "<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Empleados</title>
    </head>
    <body>
        <%@include  file= "headerAdministracion.jsp"%>
        <div style="padding-top: 320px; padding-left: 16%; align-content: center;font-size: 25px;">
            <form action='<%=request.getContextPath()%>/ReportesAdmin' method='POST' >

                <select onchange="pagoOnChange(this);" name="reporteTipo" >
                    <option value="todos los empleados">todos los empleados</option>
                    <option value="ver por fecha">ver por fecha</option>
                    <option value="ver por Area">ver por Area</option>
                </select>

                <div id="fechas" style="display: none">
                    <div class="col-sm-10" style="font-size:  30px;">
                        <label class="titulos" style="display: inline"  >DE </label>
                        <input style="display: inline; margin-left: 75px;" id="fechaInicio" class="fechas" type="date" name="fechaInicial" size="20" required>


                    </div>
                    <div class="col-sm-10" style="font-size:  30px;">
                        <label class="titulos" style="display: inline"  >A </label>
                        <input style="display: inline; margin-left: 75px;" id="fechaFinal" class="fechas" type="date" name="fechaFinal" size="20" required>
                        <br>
                    </div>
                    <input type="submit" name="reporte" value="Generar Reporte por Fechas">
                </div>
                </form>
                <div id="rangos" style="display: none">
                    <form method="POST"  action='<%=request.getContextPath()%>/ReportesAdmin'>
                    <label>Areas</label>
                    <select name = "miArea">
                        <%ArrayList<AreaHospital> areas = (ArrayList) request.getAttribute("areas");
                            int i;
                            for (i = 0; i < areas.size(); i++) {

                                String area = areas.get(i).getNombreArea();
                        %>
                        <option name="miRango" value="<%=area%>"><%=area%></option>
                        <%}
                        %>
                    </select> 
                    <input type="submit" name="reporte" value="Generar Reporte por Areas">
                    </form>
                </div>

            
            <div id="reporte">
                <%if (request.getAttribute("tipo") == null) {
                %>
                <div style= "display: inline; padding: 10px; float: left; width: 100%; text-align: justify;">
                    <embed src="<%=request.getContextPath()%>/ReportesAdmin?boton=Reportes EmpleadosSF" type="application/pdf" width="100%" height=750px" />
                </div>
                <%} else if (request.getAttribute("tipo").equals("fechas")) {
                    request.setAttribute("tipo", null);
                    System.out.println(request.getAttribute("FechaInicial") + "ffffinicio");
                %>
                <div style= "display: inline; padding: 10px; float: left; width: 100%; text-align: justify;">
                    <embed src="<%=request.getContextPath()%>/ReportesAdmin?boton=fecha&FechaInicial=<%=request.getAttribute("FechaInicial")%>&fechaFinal=<%=request.getAttribute("fechaFinal")%>" type="application/pdf" width="100%" height=750px" />
                </div>
                <%} else if (request.getAttribute("tipo").equals("areas")) {
                    request.setAttribute("tipo", null);%>
                <div style= "display: inline; padding: 10px; float: left; width: 100%; text-align: justify;">
                    <embed src="<%=request.getContextPath()%>/ReportesAdmin?boton=area&area=<%=request.getAttribute("area")%>" type="application/pdf" width="100%" height=750px" />
                </div>
                <%}%>
            </div>
        </div>
    </body>
    <script>


        function pagoOnChange(sel) {
            if (sel.value === "todos los empleados") {
                divC = document.getElementById("fechas");
                divC.style.display = "none";
                divT = document.getElementById("rangos");
                divT.style.display = "none";

            } else if (sel.value === "ver por fecha") {
                divC = document.getElementById("fechas");
                divC.style.display = 'block';
                divT = document.getElementById("rangos");
                divT.style.display = "none";
            } else if (sel.value === "ver por Area") {
                divC = document.getElementById("fechas");
                divC.style.display = 'none';
                divT = document.getElementById("rangos");
                divT.style.display = "block";
            }
        }

        window.onload = function () {
            var fecha = new Date();
            var mes = fecha.getMonth() + 1;
            var dia = fecha.getDate();
            var anio = fecha.getFullYear();
            if (dia < 10)
                dia = '0' + dia;
            if (mes < 10)
                mes = '0' + mes
            document.getElementById('fechaContratacion').value = anio + "-" + mes + "-" + dia;
        }
    </script>
</html>
