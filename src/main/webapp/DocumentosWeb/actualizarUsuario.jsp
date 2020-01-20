<%-- 
    Document   : actualizarUsuario
    Created on : 17/01/2020, 01:12:39 AM
    Author     : astridmc
--%>

<%@page import="hospitalPractica.Backend.Usuario"%>
<%@page import="hospitalPractica.Backend.Administracion.Empleado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href= "<%=request.getContextPath()%>/bootstrap/css/estiloNuevoContrato.css" rel="stylesheet" type ="text/css">
        <link href = "<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ActualizarMiUsuario</title>
    </head>
    <body>
        <%Empleado empleado = (Empleado)request.getAttribute("miEmpleado");
            Usuario usuario =(Usuario)request.getAttribute("miUsuario");
            if (session.getAttribute("rango").equals("Administrador")) {%>
        <%@include  file= "../DocumentosWeb/Administracion/headerAdministracion.jsp"%>
        <%} else if (session.getAttribute("rango").equals("Enfermero")) {%>
        <%@include  file= "../DocumentosWeb/Enfermeria/headerEnfermeria.jsp"%>
        <%} else if (session.getAttribute("rango").equals("Farmaceuta")) {%>
        <%@include  file= "../DocumentosWeb/Farmacia/headerFarmaceuta.jsp"%>
        <%} else if (session.getAttribute("rango").equals("Recepcionista")) {%>
        <%@include  file= "../DocumentosWeb/Recepcion/headerRecepcionista.jsp"%>
        <%}%>
        <div  style="padding-top: 320px; padding-left: 14%; padding-right: 300px;">
            <div style="align-content: center; padding-left: 10%;">
                <h1 id="titulo">MI USUARIO</h1>
            </div>
            <div id="divPrincipal">
                <form class="form-horizontal" action="<%=request.getContextPath()%>/Empleados" method="POST">
                    
                    <div  class = "form__field" id="div1">
                        <div class="col-sm-10">
                            <label style="font-weight: bolder;">CUI del Empleado:</label>
                            <label style="padding-left: 5%;" ><%=usuario.getCui()%></label>
                            <input class="form__input" type="text"  oninput="maxLengthCheck(this)"
                                   name="cui" value="<%=usuario.getCui()%>" style="display: none;">
                        </div>
                    </div>
                    <div class="form-group" id="div0">
                        <div class="col-sm-10">
                            <label style="font-weight: bolder;">Nombre:</label>
                             <label > <%=empleado.getNombre()%></label>
                        </div>
                    </div>
                    <div class="form-group" id="div0">
                        <div class="col-sm-10">
                            <label style="font-weight: bolder;">Apellidos:</label>
                            <label > <%=empleado.getApellido()%></label>
                        </div>
                    </div>
                    <div class="form-group" id="div0">
                        <div class="col-sm-10">
                            <label style="font-weight: bolder;">Nombre De usuario:</label><br>
                            <input id="nombres" type="text" name='nombreUsuario' value="<%=usuario.getNombreUsuario()%>">
                            con este nombre iniciaras sesion en el sistema
                            
                        </div>
                    </div>
                    
                    <div class="form-group" id="div3">
                        <div class="col-sm-10">
                            <label style="font-weight: bolder;">Cambiar Contraseña: </label>
                            <input  type='password' name="password" id="password" value="<%=usuario.getPassword()%>" style="display: inline">
                            <input  type='text' name="estado" value="<%=usuario.isEstado()%>" style="display: none">
                             <input  type='text' name="rango" value="<%=usuario.getRango()%>" style="display: none">
                            <label 
                                 name="password" id="password1" value="<%=usuario.getPassword()%>" style="display: none;"><%=usuario.getPassword()%></label>
                             <input type="button" value="Mostrar"  ondblclick="mostrar();" onclick="ocultar();" style="display: inline" >
                           
                        </div>
                    </div>
            </div>
            <input type="submit" name='boton' id="boton" class="btn btn-success" value="Actualizar Mi Usuario">
            </form>
        </div>
    </div>
</body>
<script>
    var i =0;
        function mostrar(){
        document.getElementById('password').style.display= 'none';
        document.getElementById('password1').style.display= 'inline';
    }
    
    function ocultar(){
        document.getElementById('password1').style.display= 'none';
        document.getElementById('password').style.display= 'inline';
    }
    </script>
    
    <script>
    
    </script>
</html>
