<%-- 
    Document   : registrarContrto
    Created on : 10/11/2019, 10:19:15 AM
    Author     : astridmc
--%>
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
        <%@include  file= "headerAdministracion.jsp"%>
        <div style="padding-top: 320px; padding-left: 16%; align-content: center;">
            <div style="align-content: center; padding-left: 10%;">
                <h1 id="titulo">Nuevo Contrato</h1>
            </div>
            <div id="divPrincipal">
                <form class="form-horizontal" action="<%=request.getContextPath()%>/#" method="POST">
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
                                   name="cui" id="cui" placeholder ="cui (13 digitos)"  required>
                        </div>
                    </div>
                    <div class="form-group" id="div3">
                        <div class="col-sm-10">
                            <label >Telefono del Empleado</label>
                            <input  type='number' oninput="maxLengthCheck2(this)"
                                   name="telefono" id="telefono" placeholder ="telefono (8digitos)" required>
                        </div>
                    </div>
                    <div class="form-group" id="divSegundo">
                        <select class="input" name = "rangos">
                                <option value="66">PRUEBA</option>
                                <option value="66">PRUEBA 22</option>
                        </select>
                        <%-- <select name = "rangos">
                            <c:forEach var="element.getNombre()" items="${Rangos}">
                                <option value="${element.getNombre()}">${element}</option>
                            </c:forEach>
                        </select>--%>
                        <label><input class="imput" type="checkbox" id="IGSS" name="IGSS" value="IGSS"> IGSS</label>
                        <label><input class="imput" type="checkbox" id="IRTRA" name="IRTRA" value="IRTRA"> IRTRA</label>
                    </div>
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
        </script>
</html>
