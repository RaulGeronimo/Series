<%-- 
    Document   : Temas
    Created on : 11/07/2022, 08:34:03 PM
    Author     : Raúl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="img/Icono.png" type="image/x-icon" />
    <title>Temas</title>

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
    <!-- Barra Navegacion Start -->
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
                <a href="Temas.htm" class="nav-item nav-link active">Temas</a>
            </div>
            <div class="navbar-nav me-auto p-3 p-lg-0">
                <a href="login.htm" class="nav-item nav-link">Cerrar Sesión</a>
            </div>
        </div>
    </nav>
    <!-- Barra Navegacion End -->

    <!-- Encabezado Start -->
    <div class="container-fluid page-header py-5 mb-5 wow fadeIn" data-wow-delay="0.1s">
        <div class="container text-center py-5">
            <h1 class="display-4 text-white animated slideInDown mb-3">Temas</h1>
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
    <!-- Encabezado End -->

    <!-- Apartados Start -->
    <div class="container-xxl py-5">
        <div class="container">
            <div class="text-center mx-auto mb-5 wow fadeInUp" data-wow-delay="0.1s" style="max-width: 600px;">
                <h6 class="section-title bg-white text-center text-primary px-3">Apartados</h6>
                <h1 class="display-6 mb-4">Seleccione lo que quieras ingresar.</h1>
            </div>
            <div class="owl-carousel project-carousel wow fadeInUp" data-wow-delay="0.1s">
                <!-- Anime -->
                <div class="project-item border rounded h-100 p-4" data-dot="01">
                    <div class="position-relative mb-4">
                        <img class="img-fluid rounded" src="img/Anime/Animes.jpg" alt="">
                        <a href="listaAnime.htm"><i class="fa fa-eye fa-2x"></i></a>
                    </div>
                    <h6>Anime</h6>
                    <span>Adaptacion de un Manga en formato animado.</span>
                </div>
                <!-- Caricaturas -->
                <div class="project-item border rounded h-100 p-4" data-dot="02">
                    <div class="position-relative mb-4">
                        <img class="img-fluid rounded" src="img/Caricaturas/Caricatura.jpg" alt="">
                        <a href="listaCaricatura.htm"><i class="fa fa-eye fa-2x"></i></a>
                    </div>
                    <h6>Caricaturas</h6>
                    <span>Serie animada realizada para entreteneral publico.</span>
                </div>
                <!-- Clasificacion -->
                <div class="project-item border rounded h-100 p-4" data-dot="03">
                    <div class="position-relative mb-4">
                        <img class="img-fluid rounded" src="img/Peliculas/Clasificacion.jpg" alt="">
                        <a href="listaClasificacion.htm"><i class="fa fa-eye fa-2x"></i></a>
                    </div>
                    <h6>Clasificacion</h6>
                    <span>El sistema de restriccion para cierto publico en las peliculas.</span>
                </div>
                <!-- Director -->
                <div class="project-item border rounded h-100 p-4" data-dot="04">
                    <div class="position-relative mb-4">
                        <img class="img-fluid rounded" src="img/Peliculas/Director.jpg" alt="">
                        <a href="listaDirector.htm"><i class="fa fa-eye fa-2x"></i></a>
                    </div>
                    <h6>Director</h6>
                    <span>El responsable de la producción de una pelicula.</span>
                </div>
                <!-- Estudios de Animacion -->
                <div class="project-item border rounded h-100 p-4" data-dot="05">
                    <div class="position-relative mb-4">
                        <img class="img-fluid rounded" src="img/Anime/Estudios.jpg" alt="">
                        <a href="listaEstudio.htm"><i class="fa fa-eye fa-2x"></i></a>
                    </div>
                    <h6>Estudios de Animacion</h6>
                    <span>Estudio encargado de la animacion de un anime.</span>
                </div>
                <!-- Genero Peliculas -->
                <div class="project-item border rounded h-100 p-4" data-dot="06">
                    <div class="position-relative mb-4">
                        <img class="img-fluid rounded" src="img/Peliculas/Genero.jpg" alt="">
                        <a href="listaGenero.htm"><i class="fa fa-eye fa-2x"></i></a>
                    </div>
                    <h6>Genero</h6>
                    <span>El tema general de una película que sirve para su clasificación.</span>
                </div>
                <!-- Pais -->
                <div class="project-item border rounded h-100 p-4" data-dot="07">
                    <div class="position-relative mb-4">
                        <img class="img-fluid rounded" src="img/Peliculas/Nacionalidad.jpg" alt="">
                        <a href="listaPais.htm"><i class="fa fa-eye fa-2x"></i></a>
                    </div>
                    <h6>Nacionalidad</h6>
                    <span>Es el lugar de origen de los directores de las peliculas.</span>
                </div>
                <!-- Peliculas -->
                <div class="project-item border rounded h-100 p-4" data-dot="08">
                    <div class="position-relative mb-4">
                        <img class="img-fluid rounded" src="img/Peliculas/Pelicula.jpg" alt="">
                        <a href="listaPelicula.htm"><i class="fa fa-eye fa-2x"></i></a>
                    </div>
                    <h6>Pelicula</h6>
                    <span>Es el filme en donde se narra una historia, se utilizan Actores.</span>
                </div>
                <!-- Productora -->
                <div class="project-item border rounded h-100 p-4" data-dot="09">
                    <div class="position-relative mb-4">
                        <img class="img-fluid rounded" src="img/Series/Productora.jpg" alt="">
                        <a href="listaProductora.htm"><i class="fa fa-eye fa-2x"></i></a>
                    </div>
                    <h6>Productora / Distribuidora</h6>
                    <span>Es el canal en donde se transmite la caricatura.</span>
                </div>
                <!-- Series -->
                <div class="project-item border rounded h-100 p-4" data-dot="10">
                    <div class="position-relative mb-4">
                        <img class="img-fluid rounded" src="img/Series/Series.jpg" alt="">
                        <a href="listaSerie.htm"><i class="fa fa-eye fa-2x"></i></a>
                    </div>
                    <h6>Series</h6>
                    <span>Programa de television que involucra una narrativa.</span>
                </div>
                <!-- Temporadas Anime -->
                <div class="project-item border rounded h-100 p-4" data-dot="11">
                    <div class="position-relative mb-4">
                        <img class="img-fluid rounded" src="img/Anime/TemporadaAnime.jpg" alt="">
                        <a href="listaTemporadaAnime.htm"><i class="fa fa-eye fa-2x"></i></a>
                    </div>
                    <h6>Temporadas Anime</h6>
                    <span>Es la division de un Anime con una cierta cantidad de Episodios.</span>
                </div>
                <!-- Temporadas Caricaturas -->
                <div class="project-item border rounded h-100 p-4" data-dot="12">
                    <div class="position-relative mb-4">
                        <img class="img-fluid rounded" src="img/Caricaturas/Temporadas.jpg" alt="">
                        <a href="listaTemporadaCaricatura.htm"><i class="fa fa-eye fa-2x"></i></a>
                    </div>
                    <h6>Temporadas Caricaturas</h6>
                    <span>Es la division de una caricatura con una cierta cantidad de Episodios.</span>
                </div>
                <!-- Temporadas de Emision -->
                <div class="project-item border rounded h-100 p-4" data-dot="13">
                    <div class="position-relative mb-4">
                        <img class="img-fluid rounded" src="img/Anime/TemporadaEmision.jpg" alt="">
                        <a href="listaTemporadasEmision.htm"><i class="fa fa-eye fa-2x"></i></a>
                    </div>
                    <h6>Temporadas de Emision</h6>
                    <span>Es la temporada del año en la que se emiten ciertos animes.</span>
                </div>
                <!-- Temporadas Series -->
                <div class="project-item border rounded h-100 p-4" data-dot="14">
                    <div class="position-relative mb-4">
                        <img class="img-fluid rounded" src="img/Series/TemporadasSeries.jpg" alt="">
                        <a href="listaTemporadaSerie.htm"><i class="fa fa-eye fa-2x"></i></a>
                    </div>
                    <h6>Temporadas Series</h6>
                    <span>Es la division de una serie con una cierta cantidad de Episodios.</span>
                </div>
                <!-- Usuarios -->
                <div class="project-item border rounded h-100 p-4" data-dot="15">
                    <div class="position-relative mb-4">
                        <img class="img-fluid rounded" src="img/Usuario.jpg" alt="">
                        <a href="listaUsuario.htm"><i class="fa fa-eye fa-2x"></i></a>
                    </div>
                    <h6>Usuarios</h6>
                    <span>Son las personas registradas en el sistema.</span>
                </div>
            </div>
        </div>
    </div>
    <!-- Apartados End -->

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