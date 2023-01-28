<%-- 
    Document   : editarUsuario
    Created on : 8/07/2022, 05:06:29 PM
    Author     : Raúl
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Recuperar Contraseña</title>
    <link rel="shortcut icon" href="img/Icono.png" type="image/x-icon" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
        integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
        integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <link rel="stylesheet" href="./css/Login.css" type="text/css">
</head>

<body>
    <div class="container">
        <div class="d-flex justify-content-center h-100">
            <div class="card">
                <div class="card-header">
                    <h3 class="text-white text-center">Recuperar Contraseña</h3>
                </div>
                <div class="card-body">
                    <form method="POST">
                        <!-- Correo -->
                        <div class="input-group form-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fas fa-mail-bulk"></i></span>
                            </div>
                            <input type="email" name="correo" class="form-control" placeholder="Correo" required autofocus/>
                        </div>

                        <!-- Usuario -->
                        <div class="input-group form-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fas fa-users"></i></span>
                            </div>
                            <input name="usuario" type="text" class="form-control" placeholder="Usuario" required>
                        </div>

                        <!-- Celular -->
                        <div class="input-group form-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fas fa-phone"></i></span>
                            </div>
                            <input type="tel" name="celular" class="form-control" pattern="[0-9]{3}[0-9]{3}[0-9]{4}"
                                title="Un número de teléfono válido consiste en un área de código de 3 dígitos entre paréntesis, un espacio, los tres primeros dígitos del número, un espacio o hypen (-), y cuatro dígitos más."
                                placeholder="Numero de Telefono" required />
                        </div>

                        <!-- Contraseña -->
                        <div class="input-group form-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"><i class="fas fa-key"></i></span>
                            </div>
                            <input id="txtPassword" name="contrasena" type="password" class="form-control"
                                placeholder="password">
                            <div class="input-group-append">
                                <button id="show_password" class="btn btn-primary" type="button"
                                    onclick="mostrarPassword()"> <span class="fa fa-eye-slash icon"></span> </button>
                            </div>
                        </div>

                        <div class="form-group">
                            <input type="submit" value="Confirmar" class="btn float-left regreso_btn">
                            <a href="login.htm" class="btn float-right login_btn">Regresar</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="js/mostrarContra.js"></script>

</html>