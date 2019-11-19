<%-- 
    Document   : nuevoCliente
    Created on : 5/11/2019, 11:15:27 AM
    Author     : astridmc
--%>

<%@page import="java.io.PrintWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href = "<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
        <link href= "<%=request.getContextPath()%>/bootstrap/css/estiloNuevoCliente.css" rel="stylesheet" type ="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar Nuevo Cliente</title>
    </head>
    <body>
        <%@include  file= "headerRecepcionista.jsp"%>
        <%  if (session.getAttribute("Guardado") != null) {
                String strExpired = (String) session.getAttribute("Guardado");
                System.out.println(strExpired);
                if (strExpired.equals("Guardado")) {
                    session.setAttribute("Guardado", null);
        %>
        <script>

            alert("el Cliente se Ha Registrado Con Exito");

        </script>
        <% } else if (strExpired.equals("noGuardado")) {
            session.setAttribute("Guardado", null);%>
        <script>

            alert("el Cliente no ha podido Registrarse");

        </script>
        <% }}%>
        <div  style="padding-top: 320px; padding-left: 16%; align-content: center;">
            <h1 id='titulo'>Nuevo Paciente</h1>
            <div  id="inferior" style="padding-top: 50px; padding-left: 10%;">
                <form class="form-horizontal" action="<%=request.getContextPath()%>/ManejadorPaciente" method="POST">
                    <div class="form-group" id="div0">
                        <div class="col-sm-10">
                            <label >Nombres</label>
                            <input id="nombres" type="text" name='nombres'>
                        </div>
                    </div>
                    <div class="form-group" id="div0">
                        <div class="col-sm-10">
                            <label >Apellidos </label>
                            <input  id="apellidos"   type='text' name ='apellidos'>
                        </div>
                    </div>
                    <div  class = "form__field" id="div1">
                        <div class="col-sm-10">
                            <label >CUI</label>
                            <input class="form__input" type="number"  oninput="maxLengthCheck(this)"
                                   name="cui" id="cui" placeholder ="cui (13 digitos)" required>
                        </div>
                    </div>
                    <div class="form-group" id="div3">
                        <div class="col-sm-10">
                            <label >Telefono </label>
                            <input type='number' oninput="maxLengthCheck2(this)"
                                   name="telefono" id="telefono" placeholder ="telefono (8 digitos)" required>
                        </div>
                    </div>   

                    <div class="form-group" id="div4">
                        <div class="col-sm-10">
                            <label>Correo Electronico </label>
                            <input type='email' name='correo' id='correo'>
                        </div>
                    </div>   
                    <input type="submit" name='boton' id="boton" class="btn btn-success"     value="Guardar">

                </form>
            </div>
        </div>
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
    </body>
</html>
