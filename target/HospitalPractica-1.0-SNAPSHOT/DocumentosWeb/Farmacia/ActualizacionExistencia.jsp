<%-- 
    Document   : Actualizacion de Existencia
    Created on : 1/11/2019, 10:53:03 PM
    Author     : astridmc
--%>

<%@page import="hospitalPractica.Backend.Farmacia.Medicina"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href = "<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Actualizar Existencia</title>
    </head>
    <body>
        <%@include  file= "headerFarmaceuta.jsp"%>
        <div  style="padding-top: 320px; padding-left: 16%;">
            <div>
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Nombre</th>
                            <th scope="col">Descripcion</th>
                            <th scope="col">Existencia Actual</th>
                            <th scope="col">Existencia Minima</th>
                            <th scope="col">Existencia Actualizada</th>
                        </tr>
                    </thead>

                    <%                    
                        ArrayList<Medicina> medicinas = (ArrayList) request.getAttribute("medicinasExistencia");
                        System.out.println(medicinas.size());
                        for (int i = 0; i < medicinas.size(); i++) {
                            Medicina medicina = medicinas.get(i);
                            String nombreMedicina = medicina.getNombre();
                            System.out.println(medicina.getNombre());
                    %>
                    <tr> 

                        <td name = "nombreProducto" > <%=medicina.getNombre()%> </td>
                        <td name = "descripcion" > <%=medicina.getDescripcion()%> </td>
                        <td name = "existencia" > <%=medicina.getExistencia()%> </td>
                        <%if(medicina.getExistencia()<= medicina.getExistenciaMinima()){%>
                        <td name = "existencia" style="background-color: #f1b0b7" > <%=medicina.getExistenciaMinima()%> </td>
                        <%}else{%>
                            <td name = "existencia" > <%=medicina.getExistenciaMinima()%> </td>
                         <%}%>
                        <td name = "suscribir" ><form action='<%=request.getContextPath()%>/ActualizarInventario' method='POST'><div class="alert alert-success alert-dismissable">
                                    <input name='medincinaNombre'  value='<%=medicina.getNombre()%>' style="display:none" >
                                    <input type="number"  name='existenciaActual'><input type="submit"  value="Guardar"></div></form></td>


                    </tr>
                    <%}%>
                </table>
            </div>

    </body>
</html>
