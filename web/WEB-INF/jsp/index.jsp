<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="img/Icono.png" type="image/x-icon" />
    <title>Series</title>
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

    <!-- Template Stylesheet -->
    <link href="css/style.css" rel="stylesheet">
</head>

<body>
    <!-- Barra Start -->
    <nav class="navbar navbar-expand-lg bg-primary navbar-dark sticky-top py-lg-0 px-lg-5 wow fadeIn"
        data-wow-delay="0.1s">
        <a href="index.htm" class="navbar-brand ms-3 d-lg-none">SERIES</a>
        <button type="button" class="navbar-toggler me-3" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <div class="navbar-nav me-auto p-3 p-lg-0">
                <a href="index.htm" class="nav-item nav-link active">Inicio</a>
                <a href="Acerca de.htm" class="nav-item nav-link">Acerca de</a>
                <a href="Contenido.htm" class="nav-item nav-link">Contenido</a>
                <a href="Temas.htm" class="nav-item nav-link">Temas</a>
            </div>
            <div class="navbar-nav me-auto p-3 p-lg-0">
                <a href="login.htm" class="nav-item nav-link">Cerrar Sesión</a>
            </div>
        </div>
    </nav>
    <!-- Barra End -->

    <!-- Encabezado Start -->
    <div class="container-fluid p-0 mb-5 wow fadeIn" data-wow-delay="0.1s">
        <div id="header-carousel" class="carousel slide" data-bs-ride="carousel">
            <div class="carousel-indicators">
                <button type="button" data-bs-target="#header-carousel" data-bs-slide-to="0" class="active"
                    aria-current="true" aria-label="Slide 1">
                    <img class="img-fluid" src="img/Encabezado.jpg" alt="Image">
                </button>
                <button type="button" data-bs-target="#header-carousel" data-bs-slide-to="1" aria-label="Slide 2">
                    <img class="img-fluid" src="img/Encabezado2.jpg" alt="Image">
                </button>
                <button type="button" data-bs-target="#header-carousel" data-bs-slide-to="2" aria-label="Slide 3">
                    <img class="img-fluid" src="img/Encabezado3.jpg" alt="Image">
                </button>
            </div>
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img class="w-100" src="img/Encabezado.jpg" alt="Image">
                    <div class="carousel-caption">
                        <div class="p-3" style="max-width: 900px;">
                            <h4 class="text-white text-uppercase mb-4 animated zoomIn">Proyecto de Vacaciones 8vo
                                Semestre</h4>
                            <h1 class="display-1 text-white mb-0 animated zoomIn">Series con Java Spring
                            </h1>
                        </div>
                    </div>
                </div>
                <div class="carousel-item">
                    <img class="w-100" src="img/Encabezado2.jpg" alt="Image">
                    <div class="carousel-caption">
                        <div class="p-3" style="max-width: 900px;">
                            <h4 class="text-white text-uppercase mb-4 animated zoomIn">Utilizando MariaDB</h4>
                            <h1 class="display-1 text-white mb-0 animated zoomIn">CRUD de Bases de Datos
                            </h1>
                        </div>
                    </div>
                </div>
                <div class="carousel-item">
                    <img class="w-100" src="img/Encabezado3.jpg" alt="Image">
                    <div class="carousel-caption">
                        <div class="p-3" style="max-width: 900px;">
                            <h4 class="text-white text-uppercase mb-4 animated zoomIn">Realizado por</h4>
                            <h1 class="display-1 text-white mb-0 animated zoomIn">Raúl Gabriel Gerónimo Herrera
                            </h1>
                        </div>
                    </div>
                </div>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#header-carousel" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#header-carousel" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>
    </div>
    <!-- Encabezado End -->

    <!-- Servicios Start -->
    <div class="container-xxl py-5">
        <div class="container">
            <div class="text-center mx-auto mb-5 wow fadeInUp" data-wow-delay="0.1s" style="max-width: 600px;">
                <h6 class="section-title bg-white text-center text-primary px-3">Servicios</h6>
                <h1 class="display-6 mb-4">Este sistema cuenta con un total de</h1>
            </div>
            <div class="row g-4">
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
            </div>
        </div>
    </div>
    <!-- Servicios End -->

    <!-- Acerca de Start -->
    <div class="container-xxl py-5">
        <div class="container">
            <div class="row g-5">
                <div class="col-lg-6 wow fadeInUp" data-wow-delay="0.1s">
                    <div class="img-border">
                        <img class="img-fluid" src="img/Base de Datos.jpg" alt="">
                    </div>
                </div>
                <div class="col-lg-6 wow fadeInUp" data-wow-delay="0.5s">
                    <div class="h-100">
                        <h6 class="section-title bg-white text-start text-primary pe-3">Acerca de</h6>
                        <h1 class="display-6 mb-4">Este sistema de Base de Datos <span class="text-primary">CRUD</span>
                        </h1>
                        <p>Consistira en una pagina con un CRUD de Bases de Datos con la tematica de las
                            series que se han visto desde los inicios de la pandemia hasta la fecha actual, en donde
                            estaran los conocimientos de Java, SQL asi como de diseño web.
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Acerca de End -->

    <!-- Contenido Start -->
    <div class="container-xxl py-5">
        <div class="container">
            <div class="text-center mx-auto mb-5 wow fadeInUp" data-wow-delay="0.1s" style="max-width: 600px;">
                <h6 class="section-title bg-white text-center text-primary px-3">Contenido</h6>
                <h1 class="display-6 mb-4">Cuenta con</h1>
            </div>
            <div class="row g-4">
                <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.1s">
                    <a class="service-item d-block rounded text-center h-100 p-4" title="Usuarios"
                        href="listaUsuario.htm">
                        <img class="img-fluid rounded mb-4" src="img/Usuarios.jpg" alt="">
                        <h4 class="mb-0">Usuarios</h4>
                    </a>
                </div>
                <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.3s">
                    <a class="service-item d-block rounded text-center h-100 p-4" title="Celular" href="Navegacion.htm">
                        <img class="img-fluid rounded mb-4" src="img/Celular.jpg" alt="">
                        <h4 class="mb-0">Navegacion por Celular</h4>
                    </a>
                </div>
                <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.5s">
                    <a class="service-item d-block rounded text-center h-100 p-4" title="Peliculas"
                        href="Peliculas.htm">
                        <img class="img-fluid rounded mb-4" src="img/Peliculas.jpg" alt="">
                        <h4 class="mb-0">Peliculas</h4>
                    </a>
                </div>
                <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.1s">
                    <a class="service-item d-block rounded text-center h-100 p-4" title="Series" href="Series.htm">
                        <img class="img-fluid rounded mb-4" src="img/Series.jpg" alt="">
                        <h4 class="mb-0">Series</h4>
                    </a>
                </div>
                <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.3s">
                    <a class="service-item d-block rounded text-center h-100 p-4" title="Caricaturas"
                        href="Caricaturas.htm">
                        <img class="img-fluid rounded mb-4" src="img/Caricaturas.jpg" alt="">
                        <h4 class="mb-0">Caricaturas</h4>
                    </a>
                </div>
                <div class="col-lg-4 col-md-6 wow fadeInUp" data-wow-delay="0.5s">
                    <a class="service-item d-block rounded text-center h-100 p-4" title="Anime" href="Anime.htm">
                        <img class="img-fluid rounded mb-4" src="img/Anime.jpg" alt="">
                        <h4 class="mb-0">Anime</h4>
                    </a>
                </div>
            </div>
        </div>
    </div>
    <!-- Contenido End -->

    <!-- Acciones Start -->
    <div class="container-xxl py-5">
        <div class="container">
            <div class="text-center mx-auto mb-5 wow fadeInUp" data-wow-delay="0.1s" style="max-width: 600px;">
                <h6 class="section-title bg-white text-center text-primary px-3">Acciones</h6>
                <h1 class="display-6 mb-4">En cada una de las tablas se podra realizar.</h1>
            </div>
            <div class="owl-carousel project-carousel wow fadeInUp" data-wow-delay="0.1s">
                <div class="project-item border rounded h-100 p-4" data-dot="01">
                    <h6>Insersion de Datos</h6>
                    <span>Cada una de las tablas podra insertar nuevos registros, estos se guardaran en una Base de
                        Datos.</span>
                </div>

                <div class="project-item border rounded h-100 p-4" data-dot="02">
                    <h6>Actualizacion de Datos</h6>
                    <span>En cada tabla se podran actualizar los registros en dado caso que necesite alguna
                        modificacion.</span>
                </div>

                <div class="project-item border rounded h-100 p-4" data-dot="03">
                    <h6>Eliminacion de Registros</h6>
                    <span>Si ya no se quiere contener algun registro, se eliminara para que ya no aparezca en el Sistema
                        de Bases de Datos.</span>
                </div>

                <div class="project-item border rounded h-100 p-4" data-dot="04">
                    <h6>Busqueda</h6>
                    <span>Al contener varios registros, en cada una de las tablas se tendra una barra de busqueda para
                        que asi
                        se pueda buscar el registro.</span>
                </div>

                <div class="project-item border rounded h-100 p-4" data-dot="05">
                    <h6>Navegacion desde el Celular</h6>
                    <span>Desde el celular se podra navegar en la pagina, asi como insertar registros, para esto se
                        usara un codigo QR.</span>
                </div>

                <div class="project-item border rounded h-100 p-4" data-dot="06">
                    <h6>Exportacion</h6>
                    <span>Se podra realizar una exportacion de los registros tanto en PDF como en Excel y en XML.</span>
                </div>

                <div class="project-item border rounded h-100 p-4" data-dot="07">
                    <h6>Vista de Cambios</h6>
                    <span>Cada tabla contara con una vista de cambios, en esta tabla se vera reflejado cada modificacion
                        de registros.</span>
                </div>

                <div class="project-item border rounded h-100 p-4" data-dot="08">
                    <h6>Usuarios</h6>
                    <span>Para el uso del sistema, se debera contar con un usuario para el loggeo, asimismo podra verse
                        los datos del usuario.</span>
                </div>
            </div>
        </div>
    </div>
    <!-- Acciones End -->

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

</html>