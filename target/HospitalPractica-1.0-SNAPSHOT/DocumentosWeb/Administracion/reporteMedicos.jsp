<%-- 
    Document   : reporteMedicos
    Author     : astridmc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Medicos</title>
    </head>
    <body>
                <%

            System.out.println("mi rango es " + session.getAttribute("rango"));
            if ("Editor".equals(session.getAttribute("rango"))) {
                System.out.println("se incluye rango " + session.getAttribute("rango"));%>
        <%@include  file= "/DocumentosWeb/Farmacia/headerFarmaceuta.jsp"%>
        <% } else if ("Suscriptor".equals(session.getAttribute("rango"))) {
            System.out.println("se incluye rango " + session.getAttribute("rango"));
        %>
        <%@include  file= "/DocumentosWeb/Enfermeria/headerEnfermeria.jsp"%>
        <% } else if (session.getAttribute("rango").equals("Administrador")) {%>
        <%@include  file= "/DocumentosWeb/Administracion/headerAdministracion.jsp"%>
        <%}%>
        <div style="padding-top: 320px; padding-left: 16%; align-content: center;font-size: 25px;">
            <form action='<%=request.getContextPath()%>/ReportesAdmin' method='POST' >

                <select onchange="pagoOnChange(this);" name="reporteTipo" >
                    <option value="todos los Medicos">todos los empleados</option>
                    <option value="Medicos Asignados A Pacientes">Medicos Asignados A Pacientes</option>
                    <option value="Medicos No Asignados A Pacientes">Medicos No Asignados A Pacientes</option>
                </select>
                
                <input type="submit" name="reporte" value="Generar Reporte Medicos">
            </form>
             <div id="reporte">
                <%if (request.getAttribute("tipo") == null) {
                %>
                <div style= "display: inline; padding: 10px; float: left; width: 100%; text-align: justify;">
                    <embed src="<%=request.getContextPath()%>/ReportesAdmin?boton=medicos" type="application/pdf" width="100%" height=750px" />
                </div>
                <%} else if (request.getAttribute("tipo").equals("medicosAsignados")) {
                    request.setAttribute("tipo", null);
                    System.out.println(request.getAttribute("FechaInicial") + "ffffinicio");
                %>
                <div style= "display: inline; padding: 10px; float: left; width: 100%; text-align: justify;">
                    <embed src="<%=request.getContextPath()%>/ReportesAdmin?boton=medicosAsignados" type="application/pdf" width="100%" height=750px" />
                </div>
                <%} else if (request.getAttribute("tipo").equals("medicosNoAsignados")) {
                    request.setAttribute("tipo", null);%>
                <div style= "display: inline; padding: 10px; float: left; width: 100%; text-align: justify;">
                    <embed src="<%=request.getContextPath()%>/ReportesAdmin?boton=medicosNoAsignados" type="application/pdf" width="100%" height=750px" />
                </div>
                <%}%>
            </div>
        </div>
        </div>
    </body>
</html>
