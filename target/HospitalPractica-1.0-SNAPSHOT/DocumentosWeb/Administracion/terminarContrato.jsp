<%-- 
    Document   : terminarContrato
    Created on : 14/11/2019, 11:15:22 AM
    Author     : astridmc
--%>

<%@page import="hospitalPractica.Backend.Administracion.Empleado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href= "<%=request.getContextPath()%>/bootstrap/css/estiloCambiarVacacionesEmpleado.css" rel="stylesheet" type ="text/css">
        <link href = "<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vaja Empleado</title>
    </head>
    <body>
        <%  if (session.getAttribute("Guardado") != null) {
                String strExpired = (String) session.getAttribute("Guardado");
                System.out.println(strExpired);
                if (strExpired.equals("Guardado")) {
                    session.setAttribute("Guardado", null);
        %>
        <script>

            alert("el Contrato del Empleado ha sido Finalizado");

        </script>
        <% } else if (strExpired.equals("noGuardado")) {
            session.setAttribute("Guardado", null);%>
        <script>

            alert("el Termino del contrato no ha podido Registrarse");

        </script>
        <% }
            }%>
        <%@include  file= "headerAdministracion.jsp"%>
        <div style="padding-top: 320px; padding-left: 16%; align-content: center;">
            <h1>Terminar Contrato</h1>
            <div class="col-sm-10" id="div2">
                <div>
                    <div id="div5">
                        <form action='<%=request.getContextPath()%>/Empleados' method='POST'>
                            <label>CUI Paciente</label>
                            <input class="form__input" type="number"  oninput="maxLengthCheck(this)"
                                   name="cuiEmpleado" id="cuiEmpleado" placeholder ="cui (13 digitos)" required>
                            <input type="submit" name="boton" id="botonBuscar" value="buscar info Contrato">
                        </form>
                    </div>

                    <%
                        if (request.getAttribute("encontrado") != null) {
                            Empleado empleado = (Empleado) request.getAttribute("empleado");
                            if ((boolean) request.getAttribute("encontrado")) {
                                System.out.println(empleado.getNombre());

                    %>

                    <div id="infoCliente" >
                        <label>Nombres del Empleado:</label>
                        <label class="info"> <%=empleado.getNombre()%> <%=empleado.getApellido()%></label>
                        <br>
                        <label>CUI:</label>
                        <label class="info"> <%=empleado.getCui()%> </label><br>
                        
                        <H5 style="font-weight: bolder;">CONTRATO</H5>
                        <label>Fecha de Contratacion :</label>
                        <label class="info"> <%=empleado.getContrato().getFechaInicio()%> </label>
                        <label>Rango Actual:</label> 
                        <label class="info"> <%=empleado.getRango()%> </label> <br>
                        <label>Salario:</label>
                        <label class="info"> <%=empleado.getSalario()%> </label>
                    </div>
                    <%} else if ((boolean) request.getAttribute("encontrado") == false) {%>
                    <div id="noEncontrado">
                        <h3>El Empleado  con cui = <%=request.getAttribute("cuiEempleado")%> no se encuentra en la base de datos</h3>
                        <%}
                            }%>
                    </div>
                </div>
            </div>
            <div>
                <form action='<%=request.getContextPath()%>/Empleado' method='POST'>
                    <h2 id="subtitulo">Fecha de Finalizacion del Contrato</h2>
                    <br>
                    <div class="form-group" id="div4">
                        <div class="form-group" id="div0">
                            <div class="col-sm-10" style="font-size:  30px;">
                                <label class="titulos" style="display: inline; padding-left: 10%;"  >ingrese la Fecha de FInalizacion del contrato </label>
                                <input style="display: inline; margin-left: 75px;" id="fechaFinalizacion" class="fechas" type="date" name="fechaFinalizacion" size="20" required>
                                <br>
                            </div>
                            <br>
                            <input style="display: none;" type="submit" name='boton' id="boton" class="btn btn-success" value="Finalizar Contrato">
                        </div>
                    </div>
                </form>
            </div>
            <script>


                window.onload = function () {
                    var fecha = new Date();
                    var mes = fecha.getMonth() + 1;
                    var dia = fecha.getDate();
                    var anio = fecha.getFullYear();
                    if (dia < 10)
                        dia = '0' + dia;
                    if (mes < 10)
                        mes = '0' + mes
                    document.getElementById('fechaFinalizacion').value = anio + "-" + mes + "-" + dia;
                }

                function maxLengthCheck(object)
                {
                    if (object.value.length > 13)
                        object.value = object.value.slice(0, object.maxLength);
                }

            </script>   
        </div>
    </body>
</html>
