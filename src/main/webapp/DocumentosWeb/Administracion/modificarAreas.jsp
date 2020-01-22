<%-- 
    Document   : modificarAreas
    Created on : 21/01/2020, 12:36:30 AM
    Author     : astridmc
--%>

<%@page import="hospitalPractica.Backend.Administracion.Rango"%>
<%@page import="hospitalPractica.Backend.Administracion.AreaHospital"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href = "<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
        <title>Areas</title>
    </head>
    <body>
        <%@include  file= "headerAdministracion.jsp"%>
        <div  style="padding-top: 320px; padding-left: 14%; padding-right: 300px;">
                <h1>Modificar Areas Hospital</h1>
                <div>
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">idArea</th>
                                <th scope="col">Area </th>
                                <th scope="col">rangos </th>
                                <th scope="col">Actualizar</th>
                                <th scope="col">Eliminar Area</th>
                            </tr>
                        </thead>
                        
                        <
                    <%  ArrayList<AreaHospital> areas = (ArrayList) request.getAttribute("areas");
                        System.out.println(areas.size() + "  tamanio arreglo");
                        for (int i = 0; i < areas.size(); i++) {
                            AreaHospital area = areas.get(i);
                            System.out.println(area.getNombreArea());
                    %>

                    <tr> 
                    <form action='<%=request.getContextPath()%>/Administracion' method='POST'>
                        <td name = "idArea" id="11" value="<%=area.getIdAreaHospital()%>"><%=area.getIdAreaHospital()%></div></td>
                        <td ><div> <input  name = "nombreArea" type="text" id ="22"   value= ' <%=area.getNombreArea()%>'></div></td>
                        <td>
                        <%ArrayList rangos = area.getRangos();
                        ArrayList misRangos = (ArrayList) request.getAttribute("misRangos");
                            for (int j = 0; j < misRangos.size(); j++) { 
                            if(rangos.contains(misRangos.get(j))){
                        %>
                        <label><input style="color:#0056b3; font-weight: bolder;" type="checkbox" name="rangosTodos" value='<%=misRangos.get(j)%>' checked > <%=misRangos.get(j)%></label>
                            <%} else{%>
                        <label><input type="checkbox" value='<%=misRangos.get(j)%>' name="rangosTodos"><%=misRangos.get(j)%></label>
                        
                        <%}}%></td>
                        <td name = "suscribir" >
                            <input name="idArea" value="<%=area.getIdAreaHospital()%>" style="display: none;">
                            <div class="alert alert-success alert-dismissable">
                            <input type="submit"  name="boton"  value="Actualizar Area"></div>
                        </td>
                        <td><input type="submit" style="background-color: #efa2a9; color: black; margin-top: 20%;" name="boton" value="Eliminar Area"></td>
                    </form>
                    </tr>
                    <%}%>
                </table>
                    </table>
                </div>
        </div>
    </body>
</html>
