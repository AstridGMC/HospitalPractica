<%-- 
    Document   : modificacionCuartosHospital
    Created on : 17/11/2019, 01:00:08 AM
    Author     : astridmc
--%>

<%@page import="hospitalPractica.Backend.Administracion.CuartoHospital"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href = "<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cambiar Precios</title>
    </head>
    <body>
        <div>
            <%  if (session.getAttribute("Guardado") != null) {
                String strExpired = (String) session.getAttribute("Guardado");
                System.out.println(strExpired);
                if (strExpired.equals("Guardado")) {
                    session.setAttribute("Guardado", null);
                %>
                <script>
                    alert("se ha modificado el cuarto de hospital especificado");
                </script>
                <% } else if (strExpired.equals("noGuardado")) {
                    session.setAttribute("Guardado", null);%>
                <script>
                    alert(" el cuarto de hospital especificado  no ha podido Modificarse");
                </script>
                <% }
            }%>
        <%@include  file= "headerAdministracion.jsp"%>
            <div  style="padding-top: 320px; padding-left: 14%; padding-right: 300px;">
                <h1>Cambiar Precios Habitaciones de Hospital</h1>
                <div>
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">Numero de Cuarto</th>
                                <th scope="col">Precio Habitacion</th>
                                <th scope="col">Costo Mantenimiento</th>
                                <th scope="col">Disponibilidad</th>
                                <th scope="col">Modificar</th>
                            </tr>
                        </thead>

                        <%
                            ArrayList<CuartoHospital> habitaciones = (ArrayList) request.getAttribute("habitaciones");
                            System.out.println(habitaciones.size() + "  tamanio arreglo habitaciones");
                            for (int i = 0; i < habitaciones.size(); i++) {
                                CuartoHospital habitacion = habitaciones.get(i);
                                System.out.println(habitacion.getNoCuarto());
                        %>

                        <tr> 

                            <td id="11" value="<%=habitacion.getNoCuarto()%>"><%=habitacion.getNoCuarto()%> </td>
                            <td><div> <input type="text" id ="22"   value="<%=habitacion.getPrecio()%>"></imput></div></td>
                            <td> <div><input style="width: 100px;" id="33" type="number"  value="<%=habitacion.getCostoMantenimiento().toString()%>"> </div> </td>
                            <%if(habitacion.isDisponivilidad()){%>
                                <td> <div ><input style="width: 100px;"id="44" type="number"  value="Disponible"> </div></td>
                            <%}else{%>
                                <td id="44"value="<%=true%>"> No Disponible</td>
                            <%}%>
                            <td>
                                <form action='<%=request.getContextPath()%>/Administracion' method='POST'>
                                    <div class="alert alert-success alert-dismissable">
                                        <input name='numeroDeCuarto' id="1"   style="display:none" >
                                        <input name='precioHabitacion'  id="2" style="display:none" >
                                        <input name='costoMantenimiento'  id="3" style="display:none" >
                                        <input name='didponibilidad'  id="4" style="display:none" >
                                        <input onclick="capturar()" type="submit"  name="boton"  value="ModificarCuarto"></div>
                                </form>
                            </td>
                        </tr>
                        <%}%>
                    </table>
                </div>
            </div>
    </body>
    <script>
        function capturar()
        {

            var numeroCuarto = document.getElementById("11").value;
            var precio = document.getElementById("22").value;
            var costo = document.getElementById("33").value;
            var disponivilidad = document.getElementById("44").value;

            document.getElementById("1").value = numeroCuarto;
            document.getElementById("2").value = precio;
            document.getElementsByName("3").value = costo;
            document.getElementsByTagName("4").value = disponivilidad;
    </script>
</div>
</body>
</html>