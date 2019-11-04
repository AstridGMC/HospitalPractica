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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Medina</title>
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
                            <th scope="col">Existencia Minima</th>
                            <th scope="col">Precio</th>
                            <th scope="col">Costo</th>
                            <th scope="col">Actualizar</th>
                        </tr>
                    </thead>

                    <%                    
                        ArrayList<Medicina> medicinas = (ArrayList) request.getAttribute("medicinasActualizar");
                        System.out.println(medicinas.size());
                        for (int i = 0; i < medicinas.size(); i++) {
                            Medicina medicina = medicinas.get(i);
                            String nombreMedicina = medicina.getNombre();
                            System.out.println(medicina.getNombre());
                    %>

                    <tr> 
                    
                        <td name = "nombreProducto" > <imput id="11"type ="text" value="<%=medicina.getNombre()%>"></imput></td>
                        <td name = "descripcion" > <imput id ="22" type ="text" value="<%=medicina.getDescripcion()%>"></imput></td>
                        <td name = "existenciaMin" > <imput id="33" type ="text" value="<%=medicina.getExistenciaMinima()%>"></imput> </td>
                        <td name = "Precio" > <imput id="44" type ="text" value="<%=medicina.getPrecio()%>"> </imput></td>
                        <td name = "Costo" > <imput id="55" type ="text" value="<%=medicina.getCosto()%> "> </imput></td>
                        <td name = "suscribir" >
                            <form action='<%=request.getContextPath()%>/#' method='POST'>
                                <div class="alert alert-success alert-dismissable">
                                <input name='medincinaNombre'  style="display:none" >
                                <input name='nombreActualizado' id="1"   style="display:none" >
                                <input name='descripcion'  id="2" style="display:none" >
                                <input name='existenciaMinima'  id="3" style="display:none" >
                                <input name='precio'  id="4" style="display:none" >
                                <input name='costo'  id="5" style="display:none" >
                                <input onclick="capturar()" type="submit"  name="boton"  value="actualizarMedicina"></div>
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
        
       var nombre = document.getElementById("11").value;
        var descripcion = document.getElementById("22").value;
        var existenciaMin = document.getElementById("33").value;
        var precio = document.getElementById("44").value;
        var costo=document.getElementById("55").value;
        
        document.getElementById("1").value = nombre;
        document.getElementById("2").value = descripcion;
        document.getElementsByName("3").value = existenciaMin;
        document.getElementsByTagName("4").value = precio;
        document.getElementsByTagName("5").value = costo;
    </script>
</html>
