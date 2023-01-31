<%-- 
    Document   : Contenido
    Created on : 11/07/2022, 08:33:51 PM
    Author     : Raúl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="img/Icono.png" type="image/x-icon" />
    <title>Contenido</title>

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
                <a href="Contenido.htm" class="nav-item nav-link active">Contenido</a>
                <a href="Temas.htm" class="nav-item nav-link">Temas</a>
            </div>
            <div class="navbar-nav me-auto p-3 p-lg-0">
                <a href="login.htm" class="nav-item nav-link">Cerrar Sesión</a>
            </div>
        </div>
    </nav>
    <!-- Navbar End -->

    <!-- Page Header Start -->
    <div class="container-fluid page-header py-5 mb-5 wow fadeIn" data-wow-delay="0.1s">
        <div class="container text-center py-5">
            <h1 class="display-4 text-white animated slideInDown mb-3">Contenido</h1>
            <nav aria-label="breadcrumb animated slideInDown">
                <ol class="breadcrumb justify-content-center mb-0">
                    <li class="breadcrumb-item"><a class="text-white" href="Anime.htm">Anime</a></li>
                    <li class="breadcrumb-item"><a class="text-white" href="Caricaturas.htm">Caricaturas</a></li>
                    <li class="breadcrumb-item"><a class="text-white" href="Peliculas.htm">Peliculas</a></li>
                    <li class="breadcrumb-item"><a class="text-white" href="Series.htm">Series</a></li>
                    <li class="breadcrumb-item"><a class="text-white" href="listaUsuario.htm">Usuarios</a></li>
                </ol>
            </nav>
        </div>
    </div>
    <!-- Page Header End -->

    <!-- Servicios Start -->
    <div class="container-xxl py-5">
        <div class="container">
            <div class="row g-4">
                <div class="text-center mx-auto mb-5 wow fadeInUp" data-wow-delay="0.1s" style="max-width: 600px;">
                    <h6 class="section-title bg-white text-center text-primary px-3">Servicios</h6>
                    <h1 class="display-6 mb-4">Este sistema cuenta con un total de</h1>
                </div>
                <div class="row g-4">
                    <!-- Animes -->
                    <div class="col-lg-3 col-md-6 wow fadeInUp" data-wow-delay="0.1s">
                        <div class="fact-item bg-light rounded text-center h-100 p-5">
                            <i class="fa bi-laptop fa-4x text-primary mb-4"></i>
                            <a href="listaAnime.htm">
                                <h5 class="mb-3">Animes</h5>
                            </a>
                            <c:forEach var="dato" items="${ListaAnime}">
                                <h1 class="display-5 mb-0" data-toggle="counter-up">${dato.Anime}</h1>
                            </c:forEach>
                        </div>
                    </div>
                    <!-- Caricaturas -->
                    <div class="col-lg-3 col-md-6 wow fadeInUp" data-wow-delay="0.3s">
                        <div class="fact-item bg-light rounded text-center h-100 p-5">
                            <i class="fa fa-users fa-4x text-primary mb-4"></i>
                            <a href="listaCaricatura.htm">
                                <h5 class="mb-3">Caricaturas</h5>
                            </a>
                            <c:forEach var="dato" items="${ListaCaricatura}">
                                <h1 class="display-5 mb-0" data-toggle="counter-up">${dato.Caricatura}</h1>
                            </c:forEach>
                        </div>
                    </div>
                    <!-- Clasificacion -->
                    <div class="col-lg-3 col-md-6 wow fadeInUp" data-wow-delay="0.3s">
                        <div class="fact-item bg-light rounded text-center h-100 p-5">
                            <!-- <i class="fa fa-users fa-4x text-primary mb-4"></i> -->
                            <i class="fas fa-broom fa-4x text-primary mb-4"></i>
                            <a href="listaClasificacion.htm">
                                <h5 class="mb-3">Clasificación</h5>
                            </a>
                            <c:forEach var="dato" items="${ListaClasificacion}">
                                <h1 class="display-5 mb-0" data-toggle="counter-up">${dato.Clasificacion}</h1>
                            </c:forEach>
                        </div>
                    </div>
                    <!-- Director -->
                    <div class="col-lg-3 col-md-6 wow fadeInUp" data-wow-delay="0.3s">
                        <div class="fact-item bg-light rounded text-center h-100 p-5">
                            <i class="fa fa-id-card fa-4x text-primary mb-4"></i>
                            <a href="listaDirector.htm">
                                <h5 class="mb-3">Directores</h5>
                            </a>
                            <c:forEach var="dato" items="${ListaDirector}">
                                <h1 class="display-5 mb-0" data-toggle="counter-up">${dato.Director}</h1>
                            </c:forEach>
                        </div>
                    </div>
                    <!-- Distribuidora -->
                    <div class="col-lg-3 col-md-6 wow fadeInUp" data-wow-delay="0.3s">
                        <div class="fact-item bg-light rounded text-center h-100 p-5">
                            <i class="fa fa-check fa-4x text-primary mb-4"></i>
                            <a href="listaProductora.htm">
                                <h5 class="mb-3">Distribuidora</h5>
                            </a>
                            <c:forEach var="dato" items="${ListaProductora}">
                                <h1 class="display-5 mb-0" data-toggle="counter-up">${dato.Productora}</h1>
                            </c:forEach>
                        </div>
                    </div>
                    <!-- Estudios Animacion -->
                    <div class="col-lg-3 col-md-6 wow fadeInUp" data-wow-delay="0.3s">
                        <div class="fact-item bg-light rounded text-center h-100 p-5">
                            <i class="fas fa-chalkboard fa-4x text-primary mb-4"></i>
                            <a href="listaEstudio.htm">
                                <h5 class="mb-3">Estudios Animacion</h5>
                            </a>
                            <c:forEach var="dato" items="${ListaEstudio}">
                                <h1 class="display-5 mb-0" data-toggle="counter-up">${dato.Estudio}</h1>
                            </c:forEach>
                        </div>
                    </div>
                    <!-- Genero -->
                    <div class="col-lg-3 col-md-6 wow fadeInUp" data-wow-delay="0.3s">
                        <div class="fact-item bg-light rounded text-center h-100 p-5">
                            <i class="fas fa-city fa-4x text-primary mb-4"></i>
                            <a href="listaGenero.htm">
                                <h5 class="mb-3">Genero de Peliculas</h5>
                            </a>
                            <c:forEach var="dato" items="${ListaGenero}">
                                <h1 class="display-5 mb-0" data-toggle="counter-up">${dato.Genero}</h1>
                            </c:forEach>
                        </div>
                    </div>
                    <!-- Peliculas -->
                    <div class="col-lg-3 col-md-6 wow fadeInUp" data-wow-delay="0.5s">
                        <div class="fact-item bg-light rounded text-center h-100 p-5">
                            <i class="fa fa-film fa-4x text-primary mb-4"></i>
                            <a href="listaPelicula.htm">
                                <h5 class="mb-3">Peliculas</h5>
                            </a>
                            <c:forEach var="dato" items="${ListaPelicula}">
                                <h1 class="display-5 mb-0" data-toggle="counter-up">${dato.Pelicula}</h1>
                            </c:forEach>
                        </div>
                    </div>
                    <!-- Pais -->
                    <div class="col-lg-3 col-md-6 wow fadeInUp" data-wow-delay="0.5s">
                        <div class="fact-item bg-light rounded text-center h-100 p-5">
                            <i class="fa fa-globe-asia fa-4x text-primary mb-4"></i>
                            <a href="listaPais.htm">
                                <h5 class="mb-3">Pais</h5>
                            </a>
                            <c:forEach var="dato" items="${ListaPais}">
                                <h1 class="display-5 mb-0" data-toggle="counter-up">${dato.Pais}</h1>
                            </c:forEach>
                        </div>
                    </div>
                    <!-- Productora -->
                    <div class="col-lg-3 col-md-6 wow fadeInUp" data-wow-delay="0.3s">
                        <div class="fact-item bg-light rounded text-center h-100 p-5">
                            <i class="fab fa-studiovinari fa-4x text-primary mb-4"></i>
                            <a href="listaProductora.htm">
                                <h5 class="mb-3">Productoras</h5>
                            </a>
                            <c:forEach var="dato" items="${ListaProductora}">
                                <h1 class="display-5 mb-0" data-toggle="counter-up">${dato.Productora}</h1>
                            </c:forEach>
                        </div>
                    </div>
                    <!-- Series -->
                    <div class="col-lg-3 col-md-6 wow fadeInUp" data-wow-delay="0.7s">
                        <div class="fact-item bg-light rounded text-center h-100 p-5">
                            <i class="fa fa-tv fa-4x text-primary mb-4"></i>
                            <a href="listaSerie.htm">
                                <h5 class="mb-3">Series</h5>
                            </a>
                            <c:forEach var="dato" items="${ListaSerie}">
                                <h1 class="display-5 mb-0" data-toggle="counter-up">${dato.Serie}</h1>
                            </c:forEach>
                        </div>
                    </div>
                    <!-- Usuarios -->
                    <div class="col-lg-3 col-md-6 wow fadeInUp" data-wow-delay="0.7s">
                        <div class="fact-item bg-light rounded text-center h-100 p-5">
                            <i class="fa fa-users-cog fa-4x text-primary mb-4"></i>
                            <a href="listaUsuario.htm">
                                <h5 class="mb-3">Usuarios</h5>
                            </a>
                            <c:forEach var="dato" items="${ListaUsuario}">
                                <h1 class="display-5 mb-0" data-toggle="counter-up">${dato.Usuario}</h1>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Servicios End -->

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


    <!-- JavaScript Libraries -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="lib/wow/wow.min.js"></script>
    <script src="lib/easing/easing.min.js"></script>
    <script src="lib/waypoints/waypoints.min.js"></script>
    <script src="lib/counterup/counterup.min.js"></script>
    <script src="lib/owlcarousel/owl.carousel.min.js"></script>
    <script src="lib/lightbox/js/lightbox.min.js"></script>

    <!-- Template Javascript -->
    <script src="js/main.js"></script>
</body>

</html>