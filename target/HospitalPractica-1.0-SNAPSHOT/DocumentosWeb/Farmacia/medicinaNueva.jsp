<%-- 
    Document   : medicinaNueva
    Created on : 2/11/2019, 12:03:39 AM
    Author     : astridmc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href = "<%=request.getContextPath()%>/bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
        <link href = "<%=request.getContextPath()%>/bootstrap/css/estiloMedicinaNueva.css" rel="stylesheet" type ="text/css">
        <title>Registrar Medicina</title>
    </head>
    <body>
        <%@include  file= "headerFarmaceuta.jsp"%>
        <div style="padding-top: 320px; padding-left: 16%;">
            <h1 id="titulo">Registrar Nueva Medicina</h1>
            <div class="form-group" id="div1">
                <form action="<%=request.getContextPath()%>/RegistradorMedicina" method="POST">
                    <div class="col-sm-10" id="div2">
                        Nombre Del Producto: <input type="text" class="form-control" name="nombre" placeholder="Producto" id="nombreProducto" required>
                        Descripcion: <input type="text" class="form-control" name="descripcion" placeholder="Descripcion" id="descripcion" required>
                    </div>
                    <div class="col-sm-10">
                        Precio:  <input class="ingresoNumero" id="precio" type="number" name="precio" pattern=".{13}" required >
                        Costo:  <input class="ingresoNumero" id="costo" type="number" name="costo" pattern=".{13}" required >
                        Existencia Minima:  <input class="ingresoNumero"  id="existenciaMin" type="number" name="existenciaMin" required >
                        Existencia:  <input id="existencia" type="number" name="existencia" required >
                        <br>
                        <br>Fecha de Ingreso  <input class="fechas" type="date" name="fecha" size="20" id="fechaActual" required>
                    </div>

                    <input type="submit" class="form-control" name="boton"  id="botonAgregar" value ="nuevaMedicina">
                </form>
            </div>
        </div>
    </body>
</html>
