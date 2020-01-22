<%-- 
    Document   : modificarTarifario
    Author     : astridmc
--%>

<%@page import="hospitalPractica.Backend.ServiciosMedicos.ServicioEspecial"%>
<%@page import="hospitalPractica.Backend.ServiciosMedicos.Servicio"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href = "<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
        <title>Modificar Tarifario</title>
    </head>
    <body>
        <%@include  file= "headerAdministracion.jsp"%>
        <%  if (session.getAttribute("Guardado") != null) {
                    boolean strExpired =  Boolean.parseBoolean(session.getAttribute("Guardado").toString());
                    System.out.println(strExpired);
                    if (strExpired ) {
                        session.setAttribute("Guardado", null);
            %>
            <script>
                alert("se ha modificado el servicio especificado");
            </script>
            <% } else{
                session.setAttribute("Guardado", null);%>
            <script>
                alert(" el servicio no ha podido Modificarse");
            </script>
            <% }
                }%>
        <div>
            
            <div  style="padding-top: 320px; padding-left: 14%; padding-right: 300px;">
                <h1>Modificar Servicios Hospital</h1>
                <div>
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">Nombre</th>
                                <th scope="col">Area </th>
                                <th scope="col">Precio Servicio</th>
                                <th scope="col">Modificar Servicio</th>
                                <th scope="col">Eliminar Servicio</th>
                            </tr>
                        </thead>
                        <%  ArrayList<Servicio> servicios = (ArrayList) request.getAttribute("servicio");
                            for (int i = 0; i < servicios.size(); i++) {
                                Servicio servicio = servicios.get(i);
                                System.out.println(servicio.getNombreServicio());
                        %>

                        <tr> 
                        <form action='<%=request.getContextPath()%>/ManejadorAtencion' method='POST'>
                            <td id="11" value="<%=servicio.getNombreServicio()%>"><%=servicio.getNombreServicio()%> </td>
                            <td name='area' type="text" id ="22"   value="<%=servicio.getAreaHospital()%>"><%=servicio.getAreaHospital()%></td>
                            <td> <div><input name='precio' style="width: 100px;" id="33" type="number"  value="<%=servicio.getPrecioServicio()%>"> </div> </td>
                            <td>
                                <div class="alert alert-success alert-dismissable">
                                    <input name='nombreServicio' style="display: none;" value="<%=servicio.getNombreServicio()%>">
                                    <input onclick="capturar()" type="submit"  name="boton"  value="Modificar Servicio"></div>
                            </td>
                            <td>
                                <div class="alert alert-success alert-dismissable"  style="background-color: #f5c6cb">
                                    <input name='nombreServicio' style="display: none;" value="<%=servicio.getNombreServicio()%>">
                                    <input onclick="capturar()" type="submit" name="boton"  value="Eliminar Servicio"></div>
                            </td>
                        </form>

                        </tr>
                        <%}%>
                    </table>
                </div>
                <br>
                <div><h1>Modificar Servicios Especiales</h1>
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">Nombre</th>
                                <th scope="col">Costo Hospital </th>
                                <th scope="col">Pago Especialista </th>
                                <th scope="col">Precio Servicio</th>
                            </tr>
                        </thead>
                        <%  ArrayList<ServicioEspecial> serviciosEs = (ArrayList) request.getAttribute("servicioEspecial");
                            for (int i = 0; i < serviciosEs.size(); i++) {
                                ServicioEspecial servicioE = serviciosEs.get(i);
                                System.out.println(servicioE.getNombreServicio());
                        %>

                        <tr> 
                        <form action='<%=request.getContextPath()%>/ManejadorAtencion' method='POST'>
                            <td id="11" value="<%=servicioE.getNombreServicio()%>"></td>
                            <td> <div><input name='costo' type="text" id ="22"   value="<%=servicioE.getCosto()%>"></div> </td>
                            <td> <div><input name='pagoEspecialista' style="width: 100px;" id="33" type="number"  value="<%=servicioE.getPagoEspecialista()%>"> </div> </td>
                            <td> <div><input name='precio' style="width: 100px;" id="33" type="number"  value="<%=servicioE.getPrecio()%>"> </div> </td>
                            <td>

                                <div class="alert alert-success alert-dismissable">
                                    <input name="nombre" value="<%=servicioE.getNombreServicio()%>" style="display: none">
                                    <input type="submit"  name="boton"  value="Modificar Servicio Especial">
                                </div>
                            </td>
                        </form>
                        <%}%>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
