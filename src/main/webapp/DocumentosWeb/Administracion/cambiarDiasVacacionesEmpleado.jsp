    <%-- 
    Document   : cambiarDiasVacacionesEmpleado
    Created on : 10/11/2019, 04:04:10 PM
    Author     : astridmc
--%>

<%@page import="hospitalPractica.Backend.Administracion.Empleado"%>
<%@page import="hospitalPractica.Backend.Paciente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href= "<%=request.getContextPath()%>/bootstrap/css/estiloCambiarVacacionesEmpleado.css" rel="stylesheet" type ="text/css">
        <link href = "<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%  if (session.getAttribute("Guardado") != null) {
                String strExpired = (String) session.getAttribute("Guardado");
                System.out.println(strExpired);
                if (strExpired.equals("Guardado")) {
                    session.setAttribute("Guardado", null);
        %>
        <script>
            alert("el las vacaciones del empleado se Han Registrado Con Exito");
        </script>
        <% } else if (strExpired.equals("noGuardado")) {
            session.setAttribute("Guardado", null);%>
        <script>
            alert("el las vacaciones del empleado no han podido Registrarse");
        </script>
        <% }}%>

        <%@include  file= "headerAdministracion.jsp"%>
        <div  style="padding-top: 320px; padding-left: 16%;">
            <h1>Cambio de Dias de Vacaciones</h1>
            <div class="col-sm-10" id="div2">
                <div id="div5">
                    <form action='<%=request.getContextPath()%>/Vacaciones' method='POST'>
                        <label>CUI Paciente</label>
                        <input class="form__input" type="number"  oninput="maxLengthCheck(this)"
                               name="cuiEmpleado" id="cuiEmpleado" placeholder ="cui (13 digitos)" required>
                        <input type="submit" name="boton" id="botonBuscar" value="Buscar Vacaciones">
                    </form>
                </div>

                <%
                    if (request.getAttribute("encontrado") != null) {
                        Empleado empleado = (Empleado) request.getAttribute("empleado");
                        if ((boolean) request.getAttribute("encontrado")) {
                            System.out.println(empleado.getNombre());
                            if ((boolean) request.getAttribute("tomadas")==false) {
                %>

                <div id="infoCliente" >
                    <label>Nombre:</label>
                    <label class="info"> <%=empleado.getNombre()%> <%=empleado.getApellido()%></label>
                    <br>
                    <label>CUI:</label>
                    <label class="info"> <%=empleado.getCui()%> </label><br>
                    <%if(empleado.getVacaciones().getFechaVacacionesInicio()!=null){%>
                    <H5>VACACIONES:</H5>
                    <label>Fecha Inicio:</label>
                    <label class="info"> <%=empleado.getVacaciones().getFechaVacacionesInicio()%> </label>
                    <label>Fecha Final:</label> 
                    <label class="info"> <%=empleado.getVacaciones().getFechaVacacionesFinal()%> </label> <br>
                    <label>Fecha Asignacion</label>
                    <label class="info"> <%=empleado.getVacaciones().getFechaAsignacion()%> </label>
                    <%}%>
                </div>
                <%} else {%>

                <h3>El Empleado  con cui = <%=request.getAttribute("cuiEempleado")%> ya ha recibido sus vacaciones</h3>

                <%} }else if ((boolean) request.getAttribute("encontrado") == false) {%>
                <div id="noEncontrado">
                    <h3>El Empleado  con cui = <%=request.getAttribute("cuiEempleado")%> no se encuentra en la base de datos</h3>
                    <%}%>
                        
                </div>
                <br>
                <br>
                <div id="diasVacaciones">
                    <form action='<%=request.getContextPath()%>/Vacaciones' method='POST'>
                        <input style="display: none;" name="cuiEmpleado" value="<%=request.getAttribute("cuiEmpleado")%>">
                        <h2 id="subtitulo">Nueva fecha de vacaciones</h2>
                        <br>
                        <h3>La fecha deve ser cambiada por lo menos con  7  dias de anticipacion</h3>
                        <div class="form-group" id="div4">
                            <div class="form-group" id="div0">
                                <div class="col-sm-10" style="font-size:  30px;">
                                    <label class="titulos" style="display: inline"  >Fecha de Asignacion </label>
                                    <input style="display: inline; margin-left: 75px;" id="fechaAsignacion" class="fechas" type="date" name="fechaAsignacion" size="20" required>
                                    <br>
                                </div>
                                <br>
                                <div class="col-sm-10" style="font-size:  30px;">
                                    <input id="noDias"  value="<%=request.getAttribute("numeroDias")%>" style="display: none;">
                                    <label class="titulos" style="display: inline"  >Fecha de Inicio </label>
                                    <input onchange="calcularFechaFinal();"  style="display: inline; margin-left: 150px;" id="fechaInicio" class="fechas" type="date" name="fechaInicio" size="20" required>
                                    <br>
                                    <div id="finales" style="display: none; font-size: 30px;">
                                        <br>
                                        <label class="titulos" id="as" style="display: inline; "  >Fecha Final </label>
                                        <input name="fechaFinal" id="fechaFinal" readonly="readonly" style="margin-left: 190px; width: 300px;">
                                    </div>
                                </div>
                                <br>
                                <br>
                                <label style="color:red; display: none;" id="ad">No se puede asignar fecha con menos de 10 dias de anticipacion</label>
                                <input  type="submit" name='boton' id="boton" class="btn btn-success" value="Guardar Vacaciones Empelado">
                            </div>
                        </div>
                    </form>
                                    <%}%>
                </div>
            </div>
            <script>

                function calcularFechaFinal() {
                    numeroDias = parseInt(document.getElementById("noDias").value, 10);
                    fechaInicial = document.getElementById("fechaInicio").value;
                    final = document.getElementById("fechaFinal");
                    console.log(document.getElementById("noDias").value);
                    console.log(fechaInicial);
                    var parts = fechaInicial.split('-');
                    mifecha = new Date(parts[0], parts[1]-1, parts[2]);
                    console.log(mifecha.getDate());
                    mifecha.setDate(mifecha.getDate() + numeroDias);
                    var anno = mifecha.getFullYear();
                    var mes = mifecha.getMonth()+1;
                    var dia = mifecha.getDate();
                    mes = (mes < 10) ? ("0" + mes) : mes;
                    dia = (dia < 10) ? ("0" + dia) : dia;
                    var fechaFinal =  anno+ '-' + mes + '-' +  dia;
                    mifechaFinal = new Date(anno, mes, dia);

                    final.value = fechaFinal;
                    document.getElementById('finales').style.display = 'block';
                    validarFecha(fechaInicial);
                }

                function validarFecha(fechaInicial) {
                    var date_1 = new Date(fechaInicial);
                    var date_2 = new Date(document.getElementById("fechaAsignacion").value);
                    console.log(date_2);
                    var day_as_milliseconds = 86400000;
                    var diff_in_millisenconds = date_1 - date_2;
                    console.log(diff_in_millisenconds);
                    var diff_in_days = diff_in_millisenconds / day_as_milliseconds;
                    if (diff_in_days >= 7) {
                        document.getElementById('ad').style.display = 'none';
                        document.getElementById('boton').style.display = 'block';
                    } else {
                        document.getElementById('ad').style.display = 'block';
                        document.getElementById('boton').style.display = 'none';
                    }
                    console.log(diff_in_days);
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
                    document.getElementById('fechaInicio').value = anio + "-" + mes + "-" + dia;
                    document.getElementById('fechaAsignacion').value = anio + "-" + mes + "-" + dia;
                }

                function maxLengthCheck(object)
                {
                    if (object.value.length > 13)
                        object.value = object.value.slice(0, object.maxLength);
                }

            </script>   
    </body>
</html>
