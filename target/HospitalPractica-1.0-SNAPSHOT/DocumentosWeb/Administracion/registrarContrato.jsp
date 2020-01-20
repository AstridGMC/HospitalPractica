<%-- 
    Document   : registrarContrato
    Created on : 10/11/2019, 10:19:15 AM
    Author     : astridmc
--%>
<%@page import="hospitalPractica.Backend.Administracion.Rango"%>
<%@page import="hospitalPractica.Backend.Administracion.AreaHospital"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href= "<%=request.getContextPath()%>/bootstrap/css/estiloNuevoContrato.css" rel="stylesheet" type ="text/css">
        <link href = "<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contrato Nuevo</title>
    </head>
    <body>
        <%  if (session.getAttribute("Guardado") != null) {
                String strExpired = (String) session.getAttribute("Guardado");
                System.out.println(strExpired);
                if (strExpired.equals("Guardado")) {
                    session.setAttribute("Guardado", null);
        %>
        <script>

            alert("el Empleado se Ha Registrado Con Exito");

        </script>
        <% } else if (strExpired.equals("noGuardado")) {
            session.setAttribute("Guardado", null);%>
        <script>

            alert("el Empleado no ha podido Registrarse");

        </script>
        <% }
            }%>
        <%@include  file= "headerAdministracion.jsp"%>
        <div style="padding-top: 320px; padding-left: 16%; align-content: center;">
            <div style="align-content: center; padding-left: 10%;">
                <h1 id="titulo">Nuevo Contrato</h1>
            </div>
            <div id="divPrincipal">
                <form class="form-horizontal" action="<%=request.getContextPath()%>/Empleados" method="POST">
                    <div class="form-group" id="div0">
                        <div class="col-sm-10">
                            <label >Nombres Del Empleado</label>
                            <input id="nombres" type="text" name='nombres'>
                        </div>
                    </div>
                    <div class="form-group" id="div0">
                        <div class="col-sm-10">
                            <label >Apellidos del Empleado</label>
                            <input   id="apellidos"   type='text' name ='apellidos'>
                        </div>
                    </div>
                    <div  class = "form__field" id="div1">
                        <div class="col-sm-10">
                            <label >CUI del Empleado</label>
                            <input class="form__input" type="number"  oninput="maxLengthCheck(this)"
                                   name="cuiEmpleado" id="cui" placeholder ="cui (13 digitos)"  required>
                        </div>
                    </div>
                    <div class="form-group" id="div3">
                        <div class="col-sm-10">
                            <label >Telefono del Empleado</label>
                            <input  type='number' oninput="maxLengthCheck2(this)"
                                    name="telefono" id="telefono" placeholder ="telefono (8digitos)" required>
                        </div>
                    </div>
                    <div class="form-group" id="div3">
                        <div class="col-sm-10">
                            <label >contraseña(en caso de ser necesario)</label>
                            <input  type='password' 
                                    name="password" id="password" placeholder ="contraseña">
                        </div>
                    </div>
                    <div class="form-group" id="divSegundo">
                         <label>Area </label>
                        <select name="rangoArea">
                            <option selected>...</option>
                            <%  ArrayList<AreaHospital> areas = (ArrayList) request.getAttribute("areasHospital");
                                System.out.println(areas.size() + "  tamanio arreglo areas");
                                for (int i = 0; i < areas.size(); i++) {
                                    System.out.println(i);
                                    AreaHospital area = areas.get(i);
                                    String nombreArea = area.getNombreArea();
                                    System.out.println(areas.get(i).getNombreArea()+ "  nombre Area");
                            %>
                            <optgroup label="<%=areas.get(i).getNombreArea()%>">
                            
                            <%
                               ArrayList<String> rangos = area.getRangos();
                                for (int j = 0; j < rangos.size(); j++) {
                                    String rango = rangos.get(j);
                                    System.out.println(rango);
                            %>
                            <option  value="<%=nombreArea%>-<%=rango%>"><%=rango%></option>
                            <%}%>
                            </optgroup>
                            <%}%>
                        </select>
                        <br>
                            <div class="form-group" id="div3">
                                <div class="col-sm-10">
                                    <label >Salario</label>
                                    <input  type='number' 
                                            name="salario" id="salario" required>
                                </div>
                            </div>

                            <div class="col-sm-10" style="font-size:  30px;">
                                <label class="titulos" style="display: inline"  >Fecha de Contratacion </label>
                                <input style="display: inline; margin-left: 75px;" id="fechaContratacion" class="fechas" type="date" name="fechaContratacion" size="20" required>
                                <br>
                            </div>
                            <label><input class="imput" type="checkbox" id="IGSS" name="IGSS" value="IGSS"> IGSS</label>
                            <label><input class="imput" type="checkbox" id="IRTRA" name="IRTRA" value="IRTRA"> IRTRA</label>
                    </div>
                    <input type="submit" name='boton' id="boton" class="btn btn-success" value="GuardarEmpelado">
                </form>
            </div>
        </div>
    </body>
    <script>
        function maxLengthCheck(object)
        {
            if (object.value.length > 13)
                object.value = object.value.slice(0, object.maxLength);
        }

        function maxLengthCheck2(object)
        {
            if (object.value.length > 8)
                object.value = object.value.slice(0, object.maxLength);
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
