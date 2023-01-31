<%-- 
    Document   : altaPeliculaAnime
    Created on : 12/12/2022, 06:25:02 PM
    Author     : raul0
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="img/Icono.png" type="image/x-icon" />
    <title>Alta Pelicula</title>
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
            <h1 class="display-4 text-white animated slideInDown mb-3">Peliculas</h1>
            <nav aria-label="breadcrumb animated slideInDown">
                <ol class="breadcrumb justify-content-center mb-0">
                    <li class="breadcrumb-item"><a class="text-white" href="Anime.htm">Anime</a></li>
                    <li class="breadcrumb-item"><a class="text-white" href="Caricaturas.htm">Caricaturas</a></li>
                    <li class="breadcrumb-item text-primary active" aria-current="page"><a style="color: #0d6efd;"
                            href="Peliculas.htm">Peliculas</a></li>
                    <li class="breadcrumb-item"><a class="text-white" href="Series.htm">Series</a></li>
                    <li class="breadcrumb-item"><a class="text-white" href="listaUsuario.htm">Usuarios</a></li>
                </ol>
            </nav>
        </div>
    </div>
    <!-- Page Header End -->

    <!-- Form -->
    <div class="col-md-5 offset-md-3 text-white">
        <div class="card">
            <div class="card-header bg-success">
                <h3 class="text-white text-center">Nueva Pelicula</h3>
            </div>
            <div class="card-body bg-dark">
                <form method="POST">

                    <!-- Nombre -->
                    <div class="card-body">
                        <input type="text" name="nombre" placeholder="Nombre de la Pelicula:" class="form-control"
                            autofocus required>
                    </div>

                    <!-- Otros Nombres -->
                    <div class="card-body">
                        <input type="text" name="otrosNombres" placeholder="Otros Nombres de la Pelicula:"
                            class="form-control" required>
                    </div>

                    <!-- Productora -->
                    <div class="card-body">
                        <div class="input-group mb-3">
                            <label class="input-group-text" for="Productora"
                                style="color: #adb5bd; background-color: #444;">Productora</label>
                            <select name="idProductora" id="Productora" class="form-select"
                                aria-label="Default select example" required>
                                <option selected disabled value="">Selecciona una Productora</option>
                                <c:forEach var="dato" items="${ListaProductora}">
                                    <option value="${dato.idProductora}">${dato.Nombre}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <!-- Distribuidora -->
                    <div class="card-body">
                        <div class="input-group mb-3">
                            <label class="input-group-text" for="Distribuidora"
                                style="color: #adb5bd; background-color: #444;">Distribuidora</label>
                            <select name="idDistribuidora" id="Distribuidora" class="form-select"
                                aria-label="Default select example" required>
                                <option selected disabled value="">Selecciona una Distribuidora</option>
                                <c:forEach var="dato" items="${ListaDistribuidora}">
                                    <option value="${dato.idDistribuidora}">${dato.Nombre}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <!-- Duracion -->
                    <div class="card-body">
                        <div class="input-group mb-3">
                            <label class="input-group-text" for="Duracion"
                                style="color: #adb5bd; background-color: #444;">Duracion</label>
                            <input type="time" id="Duracion" name="duracion" class="form-control" required>
                        </div>
                    </div>

                    <!-- Genero -->
                    <div class="card-body">
                        <div class="input-group mb-3">
                            <label class="input-group-text" for="Genero"
                                style="color: #adb5bd; background-color: #444;">Genero de la Pelicula</label>
                            <select name="genero" id="Genero" class="form-select" aria-label="Default select example"
                                required>
                                <option selected disabled value="">Selecciona un Genero</option>
                                <c:forEach var="dato" items="${ListaGenero}">
                                    <option value="${dato.idGenero}">${dato.Nombre}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <!-- Clasificacion -->
                    <div class="card-body">
                        <div class="input-group mb-3">
                            <label class="input-group-text" for="Clasificacion"
                                style="color: #adb5bd; background-color: #444;">Clasificacion</label>
                            <select name="clasificacion" id="Clasificacion" class="form-select"
                                aria-label="Default select example" required>
                                <option selected disabled value="">Clasificacion de la Pelicula</option>
                                <c:forEach var="dato" items="${ListaClasificacion}">
                                    <option value="${dato.idClasificacion}">${dato.Nombre}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <!-- Estreno -->
                    <div class="card-body">
                        <div class="input-group mb-3">
                            <label class="input-group-text" for="Estreno"
                                style="color: #adb5bd; background-color: #444;">Fecha de Estreno</label>
                            <input type="date" id="Estreno" name="estreno" class="form-control" required />
                        </div>
                    </div>

                    <!-- Estreno Mexico -->
                    <div class="card-body">
                        <div class="input-group mb-3">
                            <label class="input-group-text" for="EstrenoMexico"
                                style="color: #adb5bd; background-color: #444;">Fecha de Estreno en Mexico</label>
                            <input type="date" id="EstrenoMexico" name="estrenoMexico" class="form-control" required />
                        </div>
                    </div>

                    <!-- Calificacion -->
                    <div class="card-body">
                        <input type="number" name="calificacion" class="form-control" placeholder="Calificacion"
                            step="0.01" min="0" max="10" required />
                    </div>

                    <!-- Director -->
                    <div class="card-body">
                        <div class="input-group mb-3">
                            <label class="input-group-text" for="Director"
                                style="color: #adb5bd; background-color: #444;">Director</label>
                            <select name="idDirector" id="Director" class="form-select"
                                aria-label="Default select example" required>
                                <option selected disabled value="">Selecciona un Director</option>
                                <c:forEach var="dato" items="${ListaDirector}">
                                    <option value="${dato.idDirector}">${dato.NombreArtistico}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <!-- Temporada Emision -->
                    <div class="card-body">
                        <div class="input-group mb-3">
                            <label class="input-group-text" for="TemporadaEmision"
                                style="color: #adb5bd; background-color: #444;">Emision</label>
                            <select name="idTemporadaEmision" id="TemporadaEmision" class="form-select"
                                aria-label="Default select example" required>
                                <option selected disabled value="">Selecciona la Temporada de Emision</option>
                                <c:forEach var="dato" items="${ListaEmision}">
                                    <option value="${dato.idTemporada}">${dato.Emision}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <!-- Estudio de Animacion -->
                    <div class="card-body">
                        <div class="input-group mb-3">
                            <label class="input-group-text" for="idEstudio"
                                style="color: #adb5bd; background-color: #444;">Estudio</label>
                            <select name="idEstudio" id="idEstudio" class="form-select"
                                aria-label="Default select example" required>
                                <option selected disabled value="">Selecciona el Estudio de Animacion</option>
                                <c:forEach var="dato" items="${ListaEstudio}">
                                    <option value="${dato.idEstudio}">${dato.Nombre}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <!-- Imagen -->
                    <div class="card-body">
                        <input type="url" name="Portada" placeholder="Imagen:" class="form-control" required>
                    </div>

                    <br><button type="submit" class="btn btn-outline-primary">Agregar</button>
                    <a href="buscarPelicula.htm" class="btn btn-outline-success">Regresar</a>
                </form>
            </div>
        </div>
    </div>
    <!-- Form -->

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