<%-- 
    Document   : actualizarClientes
    Created on : 15/01/2020, 07:52:43 PM
    Author     : astridmc
--%>

<%@page import="hospitalPractica.Backend.Paciente"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link href = "<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
        <title>Actualizar clientes</title>
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
        <% }else if (strExpired.equals("eliminado")) {
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
        <%@include  file= "headerRecepcionista.jsp"%>
        <div  style="padding-top: 320px; padding-left: 10%; padding-right: 100px;">
            <h1>Actualizar Clientes</h1>
            <div>
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Cui</th>
                            <th scope="col">Nombre</th>
                            <th scope="col">Apellidos</th>
                            <th scope="col">Correo</th>
                            <th scope="col">Telefono</th>
                            <th scope="col">Actualizar</th>
                            <th scope="col">Eliminar</th>
                        </tr>
                    </thead>
                     <%  ArrayList<Paciente> pacientes = (ArrayList) request.getAttribute("pacientes");
                        System.out.println(pacientes.size() + "  tamanio arreglo pacientes");
                        for (int i = 0; i < pacientes.size(); i++) {
                            Paciente paciente = pacientes.get(i);
                            System.out.println(paciente.getNombres()+ " "+ paciente.getApellidos());
                    %>

                    <tr> 
                    <form action='<%=request.getContextPath()%>/ManejadorPaciente' method='POST'>
                        <td name = "cui" id="11" value="<%=paciente.getCui()%>"><%=paciente.getCui()%></div></td>
                        <td ><div> <input  name = "nombres" oninput="capturar();" type="text" id ="22"   value= ' <%=paciente.getNombres()%>'></div></td>
                        <td ><div> <input  name = "apellidos" type="text" id ="22"   value= ' <%=paciente.getApellidos()%>'></div></td>
                        <td  > <div><input name = "correo" style="width: 200px;" id="33" type="number"  value="<%=paciente.getCorreoElectronico()%>"> </div> </td>
                        <td  > <div ><input  name = "telefono" style="width: 100px;"id="44" type="number" oninput="maxLengthCheck2(this)" value="<%=paciente.getTelfono()%>"> </div></td>
                        <td name = "suscribir" >
                            <div class="alert alert-success alert-dismissable">
                                <input name="pm1" value="R" style="display: none;">    
                                <input type="submit"  name="boton"  value="Actualizar Paciente"></div>
                                <input name="cui" value="<%=paciente.getCui()%>" style="display: none;">
                        </td>
                        <td><input type="submit" style="background-color: #efa2a9; color: black; margin-top: 20%;" name="boton" value="eliminar"></td>
                    </form>
                    </tr>
                    <%}%>
                </table>
                </table>
            </div>
        </div>
         <script>
        function maxLengthCheck2(object)
        {
            if (object.value.length > 8)
                object.value = object.value.slice(0, object.maxLength);
        }
    </script>
    </body>
</html>
