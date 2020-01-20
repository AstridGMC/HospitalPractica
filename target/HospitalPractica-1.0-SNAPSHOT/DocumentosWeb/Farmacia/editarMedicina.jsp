<%-- 
    Document   : editarMedicina
    Created on : 3/11/2019, 11:49:21 PM
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
        <title>Editar Medina</title>
    </head>
    <body>
        <%  if (session.getAttribute("Guardado") != null) {
                String strExpired = (String) session.getAttribute("Guardado");
                System.out.println(strExpired);
                if (strExpired.equals("Guardado")) {
                    session.setAttribute("Guardado", null);
        %>
        <script>

            alert("la medicina se ha actualizado Con Exito");

        </script>
        <% } else if (strExpired.equals("noGuardado")) {
            session.setAttribute("Guardado", null);%>
        <script>

            alert("la medicina no ha podido actualizarse");

        </script>
        <% }else if (strExpired.equals("eliminado")) {
                session.setAttribute("Guardado", null);%>
        <script>

            alert("La medicina ha sido eliminada de la base de datos");

        </script>
        <%} else if (strExpired.equals("noEliminado")) {
            session.setAttribute("Guardado", null);%>
        %>
        <script>

            alert("La medicina no ha sido eliminada debido a que aun se encuentran existencias disponibles");

        </script>
        <%}
        session.setAttribute("Guardado", null);
    }%>
        <%@include  file= "headerFarmaceuta.jsp"%>
        <div  style="padding-top: 320px; padding-left: 14%; padding-right: 300px;">
            <div>
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Nombre</th>
                            <th scope="col">Descripcion</th>
                            <th scope="col">Existencia Minima</th>
                            <th scope="col">Precio</th>
                            <th scope="col">Costo</th>
                            <th scope="col">Actualizar</th>
                        </tr>
                    </thead>

                    <%  ArrayList<Medicina> medicinas = (ArrayList) request.getAttribute("medicinasActualizar");
                        System.out.println(medicinas.size() + "  tamanio arreglo medicina");
                        for (int i = 0; i < medicinas.size(); i++) {
                            Medicina medicina = medicinas.get(i);
                            System.out.println(medicina.getNombre());
                    %>

                    <tr> 
                    <form action='<%=request.getContextPath()%>/RegistradorMedicina' method='POST'>
                        <td name = "nombreProducto" id="11" value="<%=medicina.getNombre()%>"><%=medicina.getNombre()%></div></td>
                        <td ><div> <input  name = "descripcion" oninput="capturar();" type="text" id ="22"   value= ' <%=medicina.getDescripcion()%>'></div></td>
                        <td  > <div><input name = "existenciaMinima" style="width: 100px;" id="33" type="number"  value="<%=medicina.getExistenciaMinima()%>"> </div> </td>
                        <td  > <div ><input  name = "precio" style="width: 100px;"id="44" type="number"  value="<%=medicina.getPrecio()%>"> </div></td>
                        <td  > <div><input  name = "costo" style="width: 100px;" id="55" type ="number" value="<%=medicina.getCosto()%>"> </div> </td>
                        <td name = "suscribir" >
                            <input name="existencia" value="<%=medicina.getExistencia()%>" style="display: none;">
                            <input name="nombre" value="<%=medicina.getNombre()%>" style="display: none;">
                            <div class="alert alert-success alert-dismissable">
                                <input type="submit"  name="boton"  value="actualizarMedicina"></div>
                        </td>
                        <td><input type="submit" style="background-color: #efa2a9; color: black; margin-top: 20%;" name="boton" value="eliminar"></td>
                    </form>
                    </tr>
                    <%}%>
                </table>
            </div>
        </div>
    </body>
    <script>
        function capturar() {
            console.log("naded desasddfaf")
            var nombre = document.getElementById("11").value;
            var descripcion = document.getElementById("22").value;
            var existenciaMin = document.getElementById("33").value;
            var precio = document.getElementById("44").value;
            var costo = document.getElementById("55").value;

            document.getElementById("1").value = nombre;
            document.getElementById("2").value = descripcion;
            document.getElementsByName("3").value = existenciaMin;
            document.getElementsByTagName("4").value = precio;
            document.getElementsByTagName("5").value = costo;

    </script>
</html>
