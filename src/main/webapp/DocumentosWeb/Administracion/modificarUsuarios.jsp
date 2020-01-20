<%-- 
    Document   : modificarUsuarios
    Created on : 16/01/2020, 09:53:17 PM
    Author     : astridmc
--%>

<%@page import="hospitalPractica.Backend.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href = "<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
        <title>Modificar Usuario</title>
    </head>
    <body>
                  <%  if (session.getAttribute("Guardado") != null) {
                String strExpired = (String) session.getAttribute("Guardado");
                System.out.println(strExpired);
                if (strExpired.equals("Guardado")) {
                    session.setAttribute("Guardado", null);
        %>
        <script>

            alert("la info del Usuario se ha actualizado Con Exito");

        </script>
        <% } else if (strExpired.equals("noGuardado")) {
            session.setAttribute("Guardado", null);%>
        <script>

            alert("la info del usuario no ha podido actualizarse");

        </script>
        <% }else if (strExpired.equals("eliminado")) {
                session.setAttribute("Guardado", null);%>
        <script>

            alert("el usuario ha sido eliminado de la base de datos");

        </script>
        <%} else if (strExpired.equals("noEliminado")) {
            session.setAttribute("Guardado", null);%>
        %>
        <script>

            alert("el usuario no ha sido eliminado debido a que aun se encuentra activo en el sistema");

        </script>
        <%}
        session.setAttribute("Guardado", null);
    }
        %>
    
    <%@include  file= "headerAdministracion.jsp"%>
    <div  style="padding-top: 320px; padding-left: 14%; padding-right: 300px;">
    <h1>Actualizar Clientes</h1>
            <div>
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Cui</th>
                            <th scope="col">NombreUsuario</th>
                            <th scope="col">Rango</th>
                            <th scope="col">Telefono</th>
                            <th scope="col">Estado</th>
                            <th scope="col">Actualizar</th>
                            <th scope="col">Eliminar</th>
                        </tr>
                    </thead>
                    <%  ArrayList<Usuario> usuarios = (ArrayList) request.getAttribute("usuarios");
                        System.out.println(usuarios.size() + "  tamanio arreglo usuarios");
                        for (int i = 0; i < usuarios.size(); i++) {
                           
                            Usuario usuario = usuarios.get(i);
                            System.out.println(usuario.getNombreUsuario());
                    %>

                    <tr> 
                    <form action='<%=request.getContextPath()%>/Empleados' method='POST'>
                        <td name="cui" id="11" value="<%=usuario.getCui()%>"><%=usuario.getCui()%></div></td>
                        <td oninput="capturar();" id ="22"   value= ' <%=usuario.getNombreUsuario()%>'><%=usuario.getNombreUsuario()%></td>
                        <td id ="22"   value= ' <%=usuario.getRango()%>'><%=usuario.getRango()%></td>
                        <td  name = "celular" style="width: 200px;" id="33"  value="<%=usuario.getEmpleado().getCelular()%>"> <%=usuario.getEmpleado().getCelular()%> </td> 
                        <%if (usuario.isEstado()) {%>
                            <td>
                                <select name="didponibilidad">
                                    <option value="Disponible" selected>Activo</option>
                                    <option value="No Disponible">Inactivo</option>
                                </select>
                            </td>
                            <%} else {%>
                            <td>
                                <select name="didponibilidad">
                                    <option value="Disponible">Activo</option>
                                    <option value="No Disponible" selected>Inactivo</option>
                                </select>
                            </td>
                            <%}%>
                        <td name = "suscribir" >
                            <div class="alert alert-success alert-dismissable">
                                <input name="pm1" value="F" style="display: none;">
                                <input name = "rango" type="text" id ="22"   value= '<%=usuario.getRango()%>' style="display: none;">
                                <input name = "cui" type="text" id ="22"   value= '<%=usuario.getCui()%>' style="display: none;">
                                <input name = "nombreUsuario" type="text"   value= '<%=usuario.getNombreUsuario()%>'style="display: none;">
                                <input name = "password" type="text"   value= '<%=usuario.getPassword()%>'style="display: none;">
                                <input type="submit"  name="boton"  value="Actualizar Usuario"></div>
                        </td>
                        <td><input type="submit" style="background-color: #efa2a9; color: black; margin-top: 20%;" name="boton" value="eliminar"></td>
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
    </body>
</html>
