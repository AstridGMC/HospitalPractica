<%-- 
    Document   : nuevoServicioHospital
    Author     : astridmc
--%>

<%@page import="hospitalPractica.Backend.Administracion.AreaHospital"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href = "<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
        <title>Agregar Servicio</title>
    </head>
    <body>
            <%@include  file= "headerAdministracion.jsp"%>
        <div style="padding-top: 320px;  font-size: 25px;">
            <h1 style="padding-left: 20%">Area del Hospital Nueva</h1>
            <form action='<%=request.getContextPath()%>/Administracion' method="POST">
                 <div style="  margin-left: 25%; margin-right: auto; font-size: 25px;">
                <div style="padding-top: 50px;">
                    <div class="col-sm-10">
                        <label style="font-size: 30px;"  >Nombre del nuevo Servicio</label>
                        <input   style="font-size: 30px;" type='text' name="nombreServicio" id="nombreArea" placeholder ="nombre" required>
                    </div>
                    <br>
                </div>
                 <select name = "miArea">
                        <%ArrayList<AreaHospital> areas = (ArrayList) request.getAttribute("areas");
                            int i;
                            for (i = 0; i < areas.size(); i++) {

                                AreaHospital area = areas.get(i);
                        %>
                        <option name="miRango" value="<%=area.getIdAreaHospital()%>"><%=area.getNombreArea()%></option>
                        <%}
                        %>
                    </select> 
                <input type="submit" name="boton" id="btnGuardar" class="btn btn-success" value="Guardar Servicio">
                 </div>
            </form>
        </div>
    </body>
</html>
