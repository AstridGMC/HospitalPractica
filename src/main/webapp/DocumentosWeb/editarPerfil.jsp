<%-- 
    Document   : editarPerfil
    Created on : 29/10/2019, 11:08:08 PM
    Author     : astridmc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%

            System.out.println("mi rango es " + session.getAttribute("rango"));
            if ("Editor".equals(session.getAttribute("rango"))) {
                System.out.println("se incluye rango " + session.getAttribute("rango"));%>
        <%@include  file= "headerFarmaceuta.jsp"%>
        <% } else if ("Suscriptor".equals(session.getAttribute("rango"))) {
            System.out.println("se incluye rango " + session.getAttribute("rango"));
        %>
        <%@include  file= "headerEnfermeria.jsp"%>
        <% } else if (session.getAttribute("rango").equals("Administrador")) {%>
        <%@include  file= "headerAdministracion.jsp"%>
        <%} } else if (session.getAttribute("rango").equals("Recepcionista")) {%>
        <%@include  file= "headerRecepcionista.jsp"%>
        <%}%>

        <div id="seccion" class="contenido">
            <h4>Puedes editar tu perfil las veces que lo desees</h4>
        </div>
        <div class="perfil">
            <form action="<%=request.getContextPath()%>/editarMiPerfil" method="POST" enctype="multipart/form-data" id="formulario">
                <div class="form-group" id="div1">
                    <label class="titulos" id="as">Ingresar Foto </label>
                    <div>
                        <input id="fotobtn" type="file" name="fileFoto" accept="image/*"  >
                    </div>
                    <div id="file-preview-zone">
                    </div>
                    <input type="submit" name="accion" id="btnAgregaFoto" value="GUARDAR">
                    <input type="button" name="canclear" id="canclear" value="Cancelar">
                    <%
                        String error = (String) session.getAttribute("error");
                        if (error != null) {
                    %>
                    <div style="border-color: red; color: black;"> 
                        <%=error%>
                    </div>
                    <%
                        }
                        session.setAttribute("error", null);
                    %>
                </div>
            </form>
            <form action="<%=request.getContextPath()%>/editarMiPerfil" method="POST" enctype="multipart/form-data">
                <br>
                <div class="form-group" id="div1">
                    <label class="titulos" id="as">Gustos </label>
                    <div>
                        <textarea readonly class="form-control" rows="3"name="gustos" id="datos1" placeholder ="cuentanos tus gustos personales"><%= request.getAttribute("misGustos")%></textarea>
                    </div>
                </div>

                <br>
                <br>
                <input type="button" name="accion" id="editando" value="EDITAR" >
                <input type="submit" name="accion" style="display:none" id="guardar" value="GUARDAR PERFIL" >
                <br>
                <br>

            </form>
            <footer></footer>
        </div>




    </body>

    <script>
        function habilitar(value)
        {
            if (value == true)
            {
                // habilitamos
                document.getElementById("entrada").disabled = false;
            } else if (value == false) {
                // deshabilitamos
                document.getElementById("entrada").disabled = true;
            }
        }
    </script>

    <script>
        function readFile(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();
                reader.onload = function (e) {
                    var filePreview = document.createElement('img');
                    filePreview.id = 'file-preview';
                    filePreview.src = null;
                    filePreview.src = e.target.result;
                    var previewZone = document.getElementById('file-preview-zone');
                    previewZone.removeChild(previewZone.lastChild);
                    previewZone.appendChild(filePreview);
                }
                reader.readAsDataURL(input.files[0]);
            }
        }
        var fileUpload = document.getElementById('fotobtn');
        fileUpload.onchange = function (e) {
            readFile(e.srcElement);
        }
        var cancelar = document.getElementById('canclear');
        cancelar.onclick = function (e) {
            var previewZone = document.getElementById('file-preview-zone');
            previewZone.removeChild(previewZone.lastChild);
        }
        var cancelar = document.getElementById('editando');
        cancelar.onclick = function (e) {
            document.getElementById('datos1').removeAttribute("readonly");
            document.getElementById('datos2').removeAttribute("readonly");
            document.getElementById('datos3').removeAttribute("readonly");
            document.getElementById('datos4').removeAttribute("readonly");
            document.getElementById('guardar').style.display = 'inline';
            document.getElementById('editando').style.display = 'none';
        }

    </script>
</html>
