<%-- 
    Document   : index
    Created on : 25/10/2019, 07:55:54 PM
    Author     : astridmc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href = "bootstrap/css/bootstrap.min.css" rel="stylesheet" type ="text/css">
        <link href = "bootstrap/css/estiloLogIn.css" rel="stylesheet" type ="text/css">
        <title>Hospital: Inicio de Sesion </title>
    </head>
    <body background = "DocumentosWeb/imagenes/1.jpg">
        <% HttpSession sesion = request.getSession(); %>
        <div id="cuadro">
            <div id="superior">
                <h1>Iniciar Secion</h1>
            </div>
            <div id="inferior">
                <form method ="POST" action ="<%=request.getContextPath()%>/inicioSesion"  class="form-horizontal">
                    <div class="form-group" id="div1">
                        <label class="col-sm-2 control-label">Usuario</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="usuario"  placeholder="Usuario" id="nombre" value ="">
                        </div>
                    </div>
                    <div class="form-group" id="div2">
                        <label class="col-sm-2 control-label">Password</label>
                        <div class="col-sm-10" >
                            <input type="password" name ="password" class="form-control" id="url" placeholder="Password">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="botonClass">
                            <button type="submit" name="acceder" class="btn btn-success pull-right" id="acceder">Acceder</button>
                        </div>
                    </div>
                </form>
            </div>
            <div id="wrapperbottom"></div>
            <%
                String error = (String) sesion.getAttribute("error");
                if (error != null) {
            %>
            <center>
                <div style="font-size: 20px; color: black;"> 
                    <%=error%>
                </div>
            </center>
                <%
                        session.setAttribute("error", null);
                    }
                %>
        </div>
    </body>
</html>
