<%-- 
    Document   : reporteEmpleados
    Created on : 19/11/2019, 12:03:59 AM
    Author     : astridmc
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.io.*" %>
<%@page import="java.util.*" %>
<%@page import="net.sf.jasperreports.engine.*" %>
<%@page import="net.sf.jasperreports.view.JasperViewer.*" %>
<%@page import="javax.servlet.ServletResponse" %>
<!DOCTYPE html>
<html>
    <head>
        <link href = "<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       
        <%@include  file= "headerAdministracion.jsp"%>
        <div style="padding-top: 320px; padding-left: 16%; align-content: center;font-size: 25px;">
                <form action='<%=request.getContextPath()%>/ReportesAdmin' method='GET' >
                    
                    <select onchange="pagoOnChange(this);" name="tipoReporte" >
                        <option value="todos los empleados">todos los empleados</option>
                        <option value="ver por fecha">ver por fecha</option>
                        <option value="ver por Area">ver por Area</option>
                    </select>
                    
                    <div id="fechas" style="display: none">
                        <div class="col-sm-10" style="font-size:  30px;">
                            <label class="titulos" style="display: inline"  >DE </label>
                            <input style="display: inline; margin-left: 75px;" id="fechaInicio" class="fechas" type="date" name="fechaInicial" size="20" required>
                            <br>

                        </div>
                        <div class="col-sm-10" style="font-size:  30px;">
                            <label class="titulos" style="display: inline"  >A </label>
                            <input style="display: inline; margin-left: 75px;" id="fechaFinal" class="fechas" type="date" name="fechaFinal" size="20" required>
                            <br>
                        </div>
                        <input type="submit" name="pm" value="Generar Reporte por Fechas">
                    </div>

                    <div id="rangos" style="display: none">
                        <label>Rango</label>
                        <select name = "miRango">
                            <c:forEach var="element" items="${rangos1}">
                                <option name="miRango" value="${element}">${element}</option>
                            </c:forEach>
                        </select> 
                        <input type="submit" name="pm" value="Generar Reporte por Areas">
                    </div>


                </form>
            </div>
            <div id="reporte">
                <%if (request.getAttribute("tipo") != null) {
                        if (request.getAttribute("tipo").equals("reporteTodos")) {
                            %>
                <div style= "display: inline; padding: 10px; float: left; width: 100%; text-align: justify;">
                    <embed src="<%=request.getContextPath()%>/ReportesAdmin?pm=reportesEmpleados" type="application/pdf" width="100%" height=750px" />
                </div>
                <%}%>
                <%if (request.getAttribute("tipo").equals("reporteFechas")) {
                    request.setAttribute("tipo", null);%>
                <div style= "display: inline; padding: 10px; float: left; width: 100%; text-align: justify;">
                    <embed src="<%=request.getContextPath()%>/ReportesAdmin" type="application/pdf" width="100%" height=750px" />
                </div>
                <%}%>
                <%if (request.getAttribute("tipo").equals("reporteAreas")) {
                    request.setAttribute("tipo", null);%>
                <div style= "display: inline; padding: 10px; float: left; width: 100%; text-align: justify;">
                    <embed src="<%=request.getContextPath()%>/ReportesAdmin?lectura" type="application/pdf" width="100%" height=750px" />
                </div>
                <%}
                }%>
            </div>

    </body>
 <script>
        
     
    function pagoOnChange(sel) {
            if (sel.value === "todos los empleados"){
                divC = document.getElementById("fechas");
                divC.style.display = "none";
                divT = document.getElementById("rangos");
                divT.style.display = none;
                
        }else if (sel.value === "ver por fecha"){
                divC = document.getElementById("fechas");
                divC.style.display='block';
                divT = document.getElementById("rangos");
                divT.style.display = "none";
            }
            else if (sel.value === "ver por Area"){
                divC = document.getElementById("fechas");
                divC.style.display='none';
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
