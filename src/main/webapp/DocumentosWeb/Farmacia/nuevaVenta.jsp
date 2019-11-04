<%-- 
    Document   : nuevaVenta
    Created on : 1/11/2019, 09:51:01 AM
    Author     : astridmc
--%>

<%@page import="hospitalPractica.Backend.Farmacia.Medicina"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href = "<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
        <link href= "<%=request.getContextPath()%>/bootstrap/css/estiloBarra.css" rel="stylesheet" type ="text/css">
        <link href = "<%=request.getContextPath()%>/bootstrap/css/estiloNuevaVenta.css" rel="stylesheet" type ="text/css">
        <title>Nueva Venta</title>
    </head>
    <body>
        <%@include  file= "headerFarmaceuta.jsp"%>
        <%System.out.println(request.getContextPath());%>
        <div style="padding-top: 320px; padding-left: 16%;">
            
            <h1 >Generar Nueva venta</h1>
            <div>
                <form  action="<%=request.getContextPath()%>/RegistrarVentaNueva" method="POST">
                    <div class="form-group" id="div1">
                        <div class="col-sm-10">
                            Nombre Del Producto: <input type="text" class="form-control" name="producto" placeholder="Producto" id="nombreProducto" required>
                            Cantidad  <input id="cantidad" type="number" name="cantidad" pattern=".{13}" required >
                            <input  class="form-control" name="boton"  value="Agregar" type="submit" id="botonAgregar">
                        </div>
                        <%if (request.getAttribute("existe") != null) {%>
                        <h4 style="color: red;"><%=request.getAttribute("existe")%></h4>
                        <% System.out.println(request.getContextPath());
                            request.setAttribute("existe", null);
                            }
                            System.out.println(request.getAttribute("existe"));
                        %>
                    </div>
                </form>
            </div>
            <div id="table">
                <table class="table">
                    <thead style="background-color: #b9bbbe">
                        <tr>
                            <th scope="col">Nombre</th> 
                            <th scope="col">Descripcion</th> 
                            <th scope="col">Cantidad</th> 
                            <th scope="col">Precio</th> 
                        </tr>
                    </thead>
                    <%
                        if (request.getAttribute("principio") == "iniciado") {
                            request.setAttribute("principio", null);
                            ArrayList<Medicina> medicinas = (ArrayList) request.getAttribute("arregloMedicinaComprada");
                            if (!medicinas.isEmpty()) {

                                for (int i = 0; i < medicinas.size(); i++) {
                                    Medicina miMedicina = medicinas.get(i);
                    %>
                    <tr> 
                        <td> <%=miMedicina.getNombre()%></td>
                        <td> <%=miMedicina.getDescripcion()%></td>
                        <td> <%=miMedicina.getExistenciaMinima()%></td>
                        <td> <%=miMedicina.getPrecio()%></td>
                    </tr>
                    <%}
                            }
                        }%>
                </table>
            </div>
            <div  style="padding-left: 20%;">
                <form>
                    <div class="form-group" id="div4">
                        <div class="col-sm-10">
                            Fecha de Creacion  <input class="fechas" type="date" name="fecha" size="20" id="fechaActual" required>
                            <input type="button" class="form-control" name="boton"  id="botonFinalizar" value ="Finalizar Venta y Generar Factura">
                        </div>
                    </div>
                </form>  
            </div>
        </div>
    </body>
    <script>
        window.onload = function () {
            var fecha = new Date(); //Fecha actual
            var mes = fecha.getMonth() + 1; //obteniendo mes
            var dia = fecha.getDate(); //obteniendo dia
            var anio = fecha.getFullYear(); //obteniendo año
            if (dia < 10)
                dia = '0' + dia; //agrega cero si el menor de 10
            if (mes < 10)
                mes = '0' + mes //agrega cero si el menor de 10
            document.getElementById('fechaActual').value = anio + "-" + mes + "-" + dia;
        }
    </script>
</html>
