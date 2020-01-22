<%-- 
    Document   : nuevoServicioEspecial
    Created on : 20/01/2020, 10:19:50 PM
    Author     : astridmc
--%>

<%@page import="hospitalPractica.Backend.ServiciosMedicos.ServicioEspecial"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href = "<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include  file= "headerAdministracion.jsp"%>
        <div style="padding-top: 320px;  font-size: 25px;">
            <h1 style="padding-left: 20%">Area del Hospital Nueva</h1>
            <form action='<%=request.getContextPath()%>/ManejadorAtencion' method="POST">
                 <div style="  margin-left: 25%; margin-right: auto; font-size: 25px;">
                <div style="padding-top: 50px;">
                    <div class="col-sm-10">
                        <label style="font-size: 30px;"  >Nombre del nuevo Servicio</label>
                        <input   style="font-size: 30px;" type='text' name="nombreServicio" id="nombreArea" placeholder ="nombre" required>
                    </div><br>
                    <div class="col-sm-10">
                        Pago Especialista:  <input class="ingresoNumero" id="precio" type="number" name="pagoEspecialista" required >
                        <br>
                    </div>
                    <br>
                    <div class="col-sm-10">
                        Costo:  <input class="ingresoNumero" id="precio" type="number" name="costo" required >
                        <br>
                    </div>
                    <br>
                    <div class="col-sm-10">
                        Precio:  <input class="ingresoNumero" id="precio" type="number" name="precio" required >
                        <br>
                    </div>
                    <br>
                </div>
                <input type="submit" name="boton" id="btnGuardar" class="btn btn-success" value="Guardar Servicio Especial">
                <br><br>
                 </div>
            </form>
        </div>
    </body>
</html>
