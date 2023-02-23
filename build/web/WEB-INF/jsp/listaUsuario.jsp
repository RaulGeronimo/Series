<%-- 
    Document   : listaUsuario
    Created on : 10/07/2022, 02:21:54 PM
    Author     : Raúl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="img/Icono.png" type="image/x-icon" />
    <title>Lista Usuario</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;500;600;700&display=swap" rel="stylesheet">

    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="lib/animate/animate.min.css" rel="stylesheet">
    <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="lib/lightbox/css/lightbox.min.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Datatables -->
    <!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous"> -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fomantic-ui/2.8.8/semantic.min.css"
        type="text/css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/dataTables.semanticui.min.css" type="text/css">
    <link rel="stylesheet" href="https://cdn.datatables.net/select/1.3.4/css/select.dataTables.min.css">
    <link rel="stylesheet" href="css/datatables.css" type="text/css">
    <link rel="stylesheet" href="css/buttons.dataTables.min.css" type="text/css">

    <!-- Template Stylesheet -->
    <link href="css/style.css" rel="stylesheet">
    <link href="css/estilos.css" rel="stylesheet">
</head>

<body>
    <!-- Navbar Start -->
    <nav class="navbar navbar-expand-lg bg-primary navbar-dark sticky-top py-lg-0 px-lg-5 wow fadeIn"
        data-wow-delay="0.1s">
        <a href="index.htm" class="navbar-brand ms-3 d-lg-none">SERIES</a>
        <button type="button" class="navbar-toggler me-3" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <div class="navbar-nav me-auto p-3 p-lg-0">
                <a href="index.htm" class="nav-item nav-link">Inicio</a>
                <a href="Acerca de.htm" class="nav-item nav-link">Acerca de</a>
                <a href="Contenido.htm" class="nav-item nav-link">Contenido</a>
                <a href="Temas.htm" class="nav-item nav-link">Temas</a>
            </div>
            <div class="navbar-nav me-auto p-3 p-lg-0">
                <a href="login.htm" class="nav-item nav-link">Cerrar Sesión</a>
            </div>
        </div>
    </nav>
    <!-- Navbar End -->

    <!-- Page Header Start -->
    <div class="container-fluid page-header py-5 mb-3 wow fadeIn" data-wow-delay="0.1s">
        <div class="container text-center py-5">
            <h1 class="display-4 text-white animated slideInDown mb-3">Usuarios</h1>
            <nav aria-label="breadcrumb animated slideInDown">
                <ol class="breadcrumb justify-content-center mb-0">
                    <li class="breadcrumb-item"><a class="text-white" href="Anime.htm">Anime</a></li>
                    <li class="breadcrumb-item"><a class="text-white" href="Caricaturas.htm">Caricaturas</a></li>
                    <li class="breadcrumb-item"><a class="text-white" href="Peliculas.htm">Peliculas</a></li>
                    <li class="breadcrumb-item"><a class="text-white" href="Series.htm">Series</a></li>
                    <li class="breadcrumb-item text-primary active" aria-current="page">Usuarios</li>
                </ol>
            </nav>
        </div>
    </div>
    <!-- Page Header End -->

    <!-- Tabla Inicio -->
    <div class="card text-white text-center">
        <div class="card-header bg-success">
            <h4 class="text-white">Lista General Usuario</h4>
            <div style="float: right">
                <a class="btn btn-outline-light" href="listaAuditoriaUsuario.htm">Vista de Cambios</a>
            </div>
            <div style="float: left">
                <a class="btn btn-outline-light" href="altaUsuario.htm">Agregar Registro</a>
            </div>

        </div>
        <div class="card-body bg-dark scrollmenu">
            <table id="tabla" class="table table-dark">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Nombre</th>
                        <th>Apellido Paterno</th>
                        <th>Apellido Materno</th>
                        <th>Fecha Nacimiento</th>
                        <th>Edad</th>
                        <th>Dias para Cumpleaños</th>
                        <th>Celular</th>
                        <th>Sexo</th>
                        <th>Correo</th>
                        <th>Usuario</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dato" items="${Lista}">
                        <tr>
                            <!--Se coloca igual que la BD-->
                            <td>${dato.idUsuario}</td>
                            <td>${dato.Nombre}</td>
                            <td>${dato.Paterno}</td>
                            <td>${dato.Materno}</td>
                            <td>${dato.FechaNacimiento}</td>
                            <td>${dato.Edad}</td>
                            <td>${dato.Cumple}</td>
                            <td>${dato.Celular}</td>
                            <td>${dato.Sexo}</td>
                            <td>${dato.Correo}</td>
                            <td>${dato.Username}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <a href="Contenido.htm" class="btn btn-outline-success btn-lg">Regresar</a>
        </div>
    </div>
    <!-- Tabla Fin -->

    <!-- Footer Start -->
    <div class="container-fluid bg-dark text-body footer mt-5 wow fadeIn" data-wow-delay="0.1s">
        <div class="container-fluid copyright">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 text-center text-md-start mb-3 mb-md-0">
                        &copy; <a href="index.htm" target="_blank">Series</a>, CU UAEM Valle de Chalco.
                    </div>
                    <div class="col-md-6 text-center text-md-end">
                        Designed By: Raúl Gerónimo </a>
                        <br>Proyecto de Vacaciones
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Footer End -->
    
    <!-- Back to Top -->
    <a href="#" class="btn btn-lg btn-primary btn-lg-square rounded-circle back-to-top"><i
            class="bi bi-arrow-up"></i></a>
</body>

<!-- JavaScript Libraries -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="lib/wow/wow.min.js"></script>
<script src="lib/easing/easing.min.js"></script>
<script src="lib/waypoints/waypoints.min.js"></script>
<script src="lib/counterup/counterup.min.js"></script>
<script src="lib/owlcarousel/owl.carousel.min.js"></script>
<script src="lib/lightbox/js/lightbox.min.js"></script>

<!-- Template Javascript -->
<script src="js/main.js"></script>

<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/buttons/2.2.2/js/dataTables.buttons.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
<script src="https://cdn.datatables.net/buttons/2.2.2/js/buttons.html5.min.js"></script>
<script src="https://cdn.datatables.net/buttons/2.2.2/js/buttons.print.min.js"></script>

<script src="js/Exportar/Usuario.js"></script>

</html>