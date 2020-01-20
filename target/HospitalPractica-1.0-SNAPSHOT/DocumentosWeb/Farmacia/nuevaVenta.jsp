<%-- 
    Document   : nuevaVenta
    Created on : 1/11/2019, 09:51:01 AM
    Author     : astridmc
--%>

<%@page import="hospitalPractica.Backend.Paciente"%>
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
        <%  
            if (session.getAttribute("Guardado") != null) {
                String strExpired = (String) session.getAttribute("Guardado");
                System.out.println(strExpired);
                if (strExpired.equals("Guardado")) {
                    session.setAttribute("Guardado", null);
                %>
                <script>
                    alert("la venta ha sido Realizada con exito");
                </script>
                <% } else if (strExpired.equals("noGuardado")) {
                    session.setAttribute("Guardado", null);%>
                <script>
                    alert(" la venta  no ha podido registrarse");
                </script>
                <% }
            }%>
        <%@include  file= "headerFarmaceuta.jsp"%>
        <%System.out.println(request.getContextPath());%>
        <div style="padding-top: 320px; padding-left: 16%;">

            <h1 >Generar Nueva venta</h1>
                     <div class="col-sm-10" id="div2">
                <div id="div5">
                    <form action='<%=request.getContextPath()%>/RegistrarVentaNueva' method='POST'>
                        <label>CUI Paciente</label>
                        <input class="form__input" type="number"  oninput="maxLengthCheck(this)" style="margin-left: 50px; margin-right:  50px"
                               name="cuiPaciente" id="cui" placeholder ="cui (13 digitos)" required>
                        <input type="submit" name="boton" id="botonBuscar" value="buscarCliente">
                    </form>
                </div>
                <%
                    Paciente paciente = (Paciente) request.getAttribute("paciente");
                    if (request.getAttribute("encontrado") != null) {
                        if ((boolean) request.getAttribute("encontrado")) {
                            System.out.println(paciente.getNombres());
                            session.setAttribute("cuiPaciente", paciente.getCui());
                            
                %>

                <div id="infoCliente" style="background-color: #e2e6ea;">
                    <label>Nombre:</label>
                    <label class="info"> <%=paciente.getNombres()%> <%=paciente.getApellidos()%></label>
                    <br>
                    <label>CUI:</label>
                    <label class="info"> <%=paciente.getCui()%> </label>
                </div>
                <%} else if ((boolean) request.getAttribute("encontrado") == false) {%>
                <div id="noEncontrado">
                    <h3>El Paciente  con cui = <%=request.getAttribute("cui")%> no se encuentra en la base de datos</h3>
                    <input type="button" id="boton" onClick= 'window.open("<%=request.getContextPath()%>/DocumentosWeb/Recepcion/nuevoCliente.jsp", "MsgWindow", "width=890, height=750, top=0,left=0");'  value="Registrar">

                </div>
                <%}
                    }%>
            </div>
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
                            <th scope="col">quitarElemento</th>
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
                        <% int numero = i ; %>
                        <td><form action="<%=request.getContextPath()%>/RegistrarVentaNueva" method="POST">
                                <input type="text" name="indice" style="display: none;" value="<%=i%>">
                                <input  type="submit" name="boton" value="eliminar"> </form></td>
                    </tr>
                    <%}
                        }
                    %>
                </table>
                
            </div>
                <div  style="padding-left: 20%;">
                    <form action="<%=request.getContextPath()%>/RegistrarVentaNueva" method="POST">
                        <div class="form-group" id="div4">
                            <div class="col-sm-10">
                                <% request.setAttribute("arregloMed", medicinas);%>
                                Fecha de Creacion  <input class="fechas" type="date" name="fecha" size="20" id="fechaActual" required>
                                <input type="submit" class="form-control" name="boton"  id="botonFinalizar" value ="Finalizar Venta y Generar Factura">
                            </div>
                        </div>
                    </form>
                    <%}%>
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
        
            function maxLengthCheck(object)
            {
                if (object.value.length > 13)
                    object.value = object.value.slice(0, object.maxLength)
            }
        
    </script>
</html>
