<%-- 
    Document   : modificarMedicinas
    Created on : 17/11/2019, 10:34:07 AM
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
        <title>modificar Medicinas</title>
    </head>
    <body>
        <%@include  file= "headerAdministracion.jsp"%>
        <div>
            <div  style="padding-top: 320px; padding-left: 14%; padding-right: 300px;">
                <h1>Modificar Medicina</h1>
                <div>
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">Nombre</th>
                                <th scope="col">Precio</th>
                                <th scope="col">Costo</th>
                                <th scope="col">Descripcion</th>
                                <th scope="col">Existencia Minima</th>
                                <th scope="col">Modificar</th>
                            </tr>
                        </thead>
                        <%  ArrayList<Medicina> medicinas = (ArrayList) request.getAttribute("medicinas");
                            for (int i = 0; i < medicinas.size(); i++) {
                                Medicina medicina = medicinas.get(i);
                                System.out.println(medicina.getNombre());
                        %>

                        <tr> 
                        <form action='<%=request.getContextPath()%>/Administracion' method='POST'>
                            <td id="11" value="<%=medicina.getNombre()%>"><%=medicina.getNombre()%> </td>
                            <td><div> <input name='precio' type="text" id ="22"   value="<%=medicina.getPrecio()%>"></div></td>
                            <td> <div><input name='costo' style="width: 100px;" id="33" type="number"  value="<%=medicina.getCosto()%>"> </div> </td>
                            <td> <div ><input name='descripcion' style="width: 200px;"id="44" type="text"  value="<%=medicina.getDescripcion()%>"> </div></td>
                            <td id="55" ame='existenciaMinima' value="<%=medicina.getExistenciaMinima()%>"><%=medicina.getExistenciaMinima()%> </td>
                                <input name='medincinaNombre'  value='<%=medicina.getNombre()%>' style="display:none" >
                                 <input name='existenciaMinima'  value='<%=medicina.getExistenciaMinima()%>' style="display:none" >
                                  <input name='existencia'  value='<%=medicina.getExistencia()%>' style="display:none" >
                            <td>
                                 
                                <div class="alert alert-success alert-dismissable">
                                    <input onclick="capturar()" type="submit"  name="boton"  value="ModificarMedicina"></div>
                            </td>
                             <%}%>
                        </form>
                        </tr>
                    </table>
                </div>
            </div>
    </body>
</div>
</body>
</html>
