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
                            </tr>
                        </thead>
                        <%  ArrayList<Servicio> servicios = (ArrayList) request.getAttribute("servicio");
                            for (int i = 0; i < servicios.size(); i++) {
                                Servicio servicio = servicios.get(i);
                                System.out.println(servicio.getNombreServicio());
                        %>

                        <tr> 
                        <form action='<%=request.getContextPath()%>/Administracion' method='POST'>
                            <td id="11" value="<%=servicio.getNombreServicio()%>"><%=servicio.getNombreServicio()%> </td>
                            <td name='precio' type="text" id ="22"   value="<%=servicio.getAreaHospital()%>"></td>
                            <td> <div><input name='costo' style="width: 100px;" id="33" type="number"  value="<%=servicio.getPrecioServicio()%>"> </div> </td>
                            <td>

                                <div class="alert alert-success alert-dismissable">
                                    <input onclick="capturar()" type="submit"  name="boton"  value="Modificar Servicio"></div>
                            </td>
                            <%}%>
                        </form>
                        </tr>
                    </table>
                </div>
            
                        <br>
        <div><h1>Modificar Servicios Hospital</h1>
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
                        <form action='<%=request.getContextPath()%>/Administracion' method='POST'>
                            <td id="11" value="<%=servicioE.getNombreServicio()%>"></td>
                            <td> <div><input name='precio' type="text" id ="22"   value="<%=servicioE.getCosto()%>"></div> </td>
                            <td> <div><input name='costo' style="width: 100px;" id="33" type="number"  value="<%=servicioE.getPagoEspecialista()%>"> </div> </td>
                            <td> <div><input name='costo' style="width: 100px;" id="33" type="number"  value="<%=servicioE.getPrecio()%>"> </div> </td>
                            <td>
                                 
                                <div class="alert alert-success alert-dismissable">
                                    <input onclick="capturar()" type="submit"  name="boton"  value="Modificar Servicio"></div>
                            </td>
                             <%}%>
                        </form>
                        </tr>
                    </table>
                </div>
            </div>
                        </div>
    </body>
</html>
