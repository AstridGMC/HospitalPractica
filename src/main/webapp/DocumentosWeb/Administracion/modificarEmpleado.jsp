<%-- 
    Document   : modificarEmpleado
    Created on : 16/01/2020, 11:48:04 AM
    Author     : astridmc
--%>

<%@page import="hospitalPractica.Backend.Administracion.Empleado"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href = "<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
        <title>Modificar Empleado</title>
    </head>
    <body>
        <%  if (session.getAttribute("Guardado") != null) {
                String strExpired = (String) session.getAttribute("Guardado");
                System.out.println(strExpired);
                if (strExpired.equals("Guardado")) {
                    session.setAttribute("Guardado", null);
        %>
        <script>

            alert("la info del paciente se ha actualizado Con Exito");

        </script>
        <% } else if (strExpired.equals("noGuardado")) {
            session.setAttribute("Guardado", null);%>
        <script>

            alert("la info del paciente no ha podido actualizarse");

        </script>
        <% } else if (strExpired.equals("eliminado")) {
            session.setAttribute("Guardado", null);%>
        <script>

            alert("el paciente ha sido eliminado de la base de datos");

        </script>
        <%} else if (strExpired.equals("noEliminado")) {
            session.setAttribute("Guardado", null);%>
        %>
        <script>

            alert("el paciente no ha sido eliminado debido a que cuenta con informacion importate para el sistema");

        </script>
        <%}
                session.setAttribute("Guardado", null);
            }%>
        <%@include  file= "headerAdministracion.jsp"%>
        <div  style="padding-top: 320px; padding-left: 10%; padding-right: 100px;">
            <h1>Actualizar Clientes</h1>
            <div>
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Cui</th>
                            <th scope="col">Nombre</th>
                            <th scope="col">Apellidos</th>
                            <th scope="col">Telefono</th>
                            <th scope="col">Salario</th>
                            <th scope="col">Actualizar</th>
                        </tr>
                    </thead>
                    <%  ArrayList<Empleado> empleados = (ArrayList) request.getAttribute("empleados");
                        System.out.println(empleados.size() + "  tamanio arreglo pacientes");
                        for (int i = 0; i < empleados.size(); i++) {
                            Empleado empleado = empleados.get(i);
                            System.out.println(empleado.getNombre() + " " + empleado.getApellido()+" "+ empleado.getCui());
                    %>

                    <tr> 
                    <form action='<%=request.getContextPath()%>/Empleados' method='POST'>
                        <td name="cui" id="11" value="<%=empleado.getCui()%>"><%=empleado.getCui()%></div></td>
                        <td ><div> <input  name = "nombres" oninput="capturar();" type="text" id ="22"   value= ' <%=empleado.getNombre()%>'></div></td>
                        <td ><div> <input  name = "apellidos" type="text" id ="22"   value= ' <%=empleado.getApellido()%>'></div></td>
                        <td  > <div ><input  name = "telefono" style="width: 100px;"id="44" type="number"  oninput="maxLengthCheck2(this)"  value="<%=empleado.getCelular()%>"> </div></td>
                        <td  name = "salario" style="width: 100px;" id="33" value="<%=empleado.getSalario()%>"> <%=empleado.getSalario()%></td>
                        <td name = "suscribir" >
                            <div class="alert alert-success alert-dismissable">
                                <input name="cui" value="<%=empleado.getCui()%>" style="display: none;">
                                <input name="salario" value="<%=empleado.getSalario()%>" style="display: none;">
                                <input type="submit"  name="boton"  value="Modificar Empleado"></div>
                        </td>
                        
                    </form>
                    </tr>
                    <%}%>
                </table>
                </table>
            </div>
        </div>
    </body>
        <script>
        function maxLengthCheck2(object)
        {
            if (object.value.length > 8)
                object.value = object.value.slice(0, object.maxLength);
        }
    </script>
</html>
