<%-- 
    Document   : buscaTemporadaAnime2
    Created on : 8/12/2022, 06:06:11 PM
    Author     : raul0
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="img/Icono.png" type="image/x-icon" />
    <title>Lista Temporadas Anime</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <!-- Datatables -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fomantic-ui/2.8.8/semantic.min.css"
        type="text/css">

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
            <h1 class="display-4 text-white animated slideInDown mb-3">Anime</h1>
            <nav aria-label="breadcrumb animated slideInDown">
                <ol class="breadcrumb justify-content-center mb-0">
                    <li class="breadcrumb-item text-primary active" aria-current="page"><a style="color: #0d6efd;"
                            href="Anime.htm">Anime</a></li>
                    <li class="breadcrumb-item"><a class="text-white" href="Caricaturas.htm">Caricaturas</a></li>
                    <li class="breadcrumb-item"><a class="text-white" href="Peliculas.htm">Peliculas</a></li>
                    <li class="breadcrumb-item"><a class="text-white" href="Series.htm">Series</a></li>
                    <li class="breadcrumb-item"><a class="text-white" href="listaUsuario.htm">Usuarios</a></li>
                </ol>
            </nav>
        </div>
    </div>
    <!-- Page Header End -->

    <!-- Card Inicio -->
    <div class="card text-white" style="margin-bottom: 1rem;">
        <div class="card-header bg-success">
            <c:forEach var="dato" items="${Nombre}">
                <h4 class="text-white">Temporadas de: ${dato.Nombre}</h4>
            </c:forEach>
            <div style="float: right">
                <a target="_blank" class="btn btn-outline-light" href="altaTemporadaAnime.htm">Agregar Registro</a>
                <a href="listaAnime.htm" class="btn btn-outline-light">Regresar</a>
                <a class="btn btn-outline-light" href="listaAuditoriaTemporadaAnime.htm">Ver Cambios</a>
                <a class="btn btn-outline-light" href="buscaTemporadaAnime.htm?idAnime=${Nombre[0].idAnime}">Otra
                    Vista</a>
            </div>
            <div style="float: left">
                <p>Temporadas: ${Conteo[0].Temporada},
                    <!-- Peliculas: ${Pelicula[0].Temporada}, --> Promedio: ${Conteo[0].Promedio}</p>
            </div>
        </div>
    </div>

    <div class="row">
        <c:forEach var="dato" items="${Lista}">
            <div class="col-md-4" style="margin-bottom: 1.5rem;">
                <div class="card text-center bg-dark scrollmenu">
                    <div class="card-header text-white d-flex justify-content-between align-items-center text-center">
                        ${dato.Nombre}
                        <a href="eliminarTemporadaAnime.htm?idTemporada=${dato.idTemporada}"
                            onclick="return confirm('¿Estás seguro que deseas eliminar el registro?')"
                            class="btn btn-danger"><i class="far fa-trash-alt"></i></a>
                    </div>
                    <img src="${dato.Portada}" style="margin-left: auto; margin-right: auto; height: 500px;"
                        alt="Imagen del Anime">
                    <div class="card-body text-white">
                        <p style="font-weight: bold;">Capitulos</p>
                        <p>${dato.Capitulos}</p>

                        <p style="font-weight: bold;">Duracion</p>
                        <p>${dato.Duracion}</p>

                        <p style="font-weight: bold;">Idioma</p>
                        <p>${dato.Idioma}</p>

                        <p style="font-weight: bold;">Temporada Emision</p>
                        <p>${dato.Emision}</p>

                        <p style="font-weight: bold;">Estudio</p>
                        <p>${dato.Estudio}</p>

                        <p style="font-weight: bold;">Calificacion</p>
                        <p>${dato.Calificacion}</p>

                        <a class="btn btn-outline-success" target="_blank"
                            href="editarTemporadaAnime.htm?idTemporada=${dato.idTemporada}">
                            Editar Temporada
                        </a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <!-- Card Fin -->

    <!-- Card Inicio -->
    <div class="card text-white" style="margin-bottom: 1rem;">
        <div class="card-header bg-success">
            <c:forEach var="dato" items="${Nombre}">
                <h4 class="text-white">Peliculas de: ${dato.Nombre}</h4>
            </c:forEach>
            <div style="float: right">
                <a class="btn btn-outline-light" href="altaTemporadaAnime.htm">Agregar Registro</a>
                <a href="listaAnime.htm" class="btn btn-outline-light">Regresar</a>
                <a class="btn btn-outline-light" href="listaAuditoriaTemporadaAnime.htm">Ver Cambios</a>
                <a class="btn btn-outline-light" href="buscaTemporadaAnime.htm?idAnime=${Nombre[0].idAnime}">Otra
                    Vista</a>
            </div>
            <div style="float: left">
                <p>Peliculas: ${Pelicula[0].Temporada}, Promedio: ${Pelicula[0].Promedio}</p>
            </div>
        </div>
    </div>

    <div class="row">
        <c:forEach var="dato" items="${Lista1}">
            <div class="col-md-4" style="margin-bottom: 1.5rem;">
                <div class="card text-center bg-dark scrollmenu">
                    <div class="card-header text-white d-flex justify-content-between align-items-center text-center">
                        ${dato.Nombre}
                        <a href="eliminarTemporadaAnime.htm?idTemporada=${dato.idTemporada}"
                            onclick="return confirm('¿Estás seguro que deseas eliminar el registro?')"
                            class="btn btn-danger"><i class="far fa-trash-alt"></i></a>
                    </div>
                    <img src="${dato.Portada}" style="margin-left: auto; margin-right: auto; height: 500px;"
                        alt="Imagen del Anime">
                    <div class="card-body text-white">
                        <p style="font-weight: bold;">Duracion</p>
                        <p>${dato.Duracion}</p>

                        <p style="font-weight: bold;">Estreno</p>
                        <p>${dato.FechaInicio}</p>

                        <p style="font-weight: bold;">Temporada Emision</p>
                        <p>${dato.Emision}</p>

                        <p style="font-weight: bold;">Idioma</p>
                        <p>${dato.Idioma}</p>

                        <p style="font-weight: bold;">Estudio</p>
                        <p>${dato.Estudio}</p>

                        <p style="font-weight: bold;">Calificacion</p>
                        <p>${dato.Calificacion}</p>

                        <a class="btn btn-outline-success"
                            href="editarTemporadaAnime.htm?idTemporada=${dato.idTemporada}">
                            Editar Temporada
                        </a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <!-- Card Fin -->

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


</html>