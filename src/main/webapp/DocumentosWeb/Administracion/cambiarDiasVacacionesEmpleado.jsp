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

        <%@include  file= "headerAdministracion.jsp"%>
        <div  style="padding-top: 320px; padding-left: 16%;">
            <h1>Cambio de Dias de Vacaciones</h1>
            <div class="col-sm-10" id="div2">
                <div id="div5">
                    <form action='<%=request.getContextPath()%>/ManejadorPaciente' method='POST'>
                        <label>CUI Paciente</label>
                        <input class="form__input" type="number"  oninput="maxLengthCheck(this)"
                               name="cuiPaciente" id="cui" placeholder ="cui (13 digitos)" required>
                        <input type="submit" name="boton" id="botonBuscar" value="buscarCliente">
                    </form>
                </div>
                <%Empleado empleado = (Empleado) request.getAttribute("paciente");
                    if (request.getAttribute("encontrado") != null) {
                        if ((boolean) request.getAttribute("encontrado")) {
                            System.out.println(empleado.getNombre());
                %>

                <div id="infoCliente" >
                    <label>Nombre:</label>
                    <label class="info"> <%=empleado.getNombre()%> <%=empleado.getApellido()%></label>
                    <br>
                    <label>CUI:</label>
                    <label class="info"> <%=empleado.getCui()%> </label>
                    <label>Fecha Inicio:</label>
                    <label class="info"> <%=empleado.getFechaVacacionesInicio()%> </label>
                    <label>Fecha Final:</label>
                    <label class="info"> <%=empleado.getFechaVacacionesFinal()%> </label>
                </div>
                <%} else if ((boolean) request.getAttribute("encontrado") == false) {%>
                <div id="noEncontrado">
                    <h3>El Empoleadp  con cui = <%=request.getAttribute("cui")%> no se encuentra en la base de datos</h3>
                    <%}
                        }%>
                </div>
                <br>
                <br>
                <div id="diasVacaciones">
                    <form action='<%=request.getContextPath()%>/#' method='POST'>
                        <h2 id="subtitulo">Nueva fecha de vacaciones</h2>
                        <br> <%=request.getAttribute("numeroDias")%>
                        <h3>La fecha deve ser cambiada por lo menos con  <%=request.getAttribute("numeroDias")%>  dias de anticipacion</h3>
                        <div class="form-group" id="div4">
                            <div class="form-group" id="div0">
                                <div class="col-sm-10" style="font-size:  30px;">
                                    <input id="noDias"  value="<%=request.getAttribute("numeroDias")%>" style="display: none;">
                                    <label class="titulos" style="display: inline"  >Fecha de Inicio </label>
                                    <input onchange="calcularFechaFinal();"  style="display: inline; margin-left: 80px;" id="fechaInicio" class="fechas" type="date" name="fechaInicio" size="20" required>
                                </div>
                                <div id="finales" style="display: none; font-size: 30px;">
                                    <br>
                                    
                                    <label class="titulos" id="as" style="display: inline"  >Fecha Final </label>
                                    <input id="fechaFinal" readonly="readonly">
                                </div>
                            </div>
                            <br>
                            <br>
                            <input type="submit" name='boton' id="boton" class="btn btn-success"     value="Guardar">
                        </div>
                    </form>
                </div>
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
                mifecha = new Date(parts[0], parts[1], parts[2]);
                console.log(mifecha.getDate());
                mifecha.setDate(mifecha.getDate() + numeroDias);
                var anno = mifecha.getFullYear();
                var mes = mifecha.getMonth()+1;
                var dia = mifecha.getDate();
                mes = (mes < 10) ? ("0" + mes) : mes;
                dia = (dia < 10) ? ("0" + dia) : dia;
                var fechaFinal = dia + '-' + mes + '-' + anno;
                
                final.value = fechaFinal;
                document.getElementById('finales').style.display = 'block';
            }

            window.onload = function () {
                var fecha = new Date(); //Fecha actual
                var mes = fecha.getMonth() + 1; //obteniendo mes
                var dia = fecha.getDate(); //obteniendo dia
                var anio = fecha.getFullYear(); //obteniendo año
                if (dia < 10)
                    dia = '0' + dia; //agrega cero si el menor de 10
                if (mes < 10)
                    mes = '0' + mes //agrega cero si el menor de 10
                document.getElementById('fechaInicio').value = anio + "-" + mes + "-" + dia;
            }



        </script>   
    </body>
</html>
