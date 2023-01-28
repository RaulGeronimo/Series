/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import config.conexionBD;
import entidades.Caricatura;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Raúl
 */
@Controller
public class controladorCaricatura {

    //Instancia para conexion
    conexionBD con = new conexionBD();
    JdbcTemplate jdbc = new JdbcTemplate(con.Conectar());
    ModelAndView mav = new ModelAndView(); //Importar la que dice SERVLET

    //Metodo para las vistas - METODO PARA OBTENER VISTAS
    @RequestMapping(value = "altaCaricatura.htm", method = RequestMethod.GET)
    public ModelAndView VistaAgregar() {
        //Productora
        String sql = "SELECT * from Productora ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql);
        mav.addObject("ListaProductora", datos);

        //Distribuidora
        String sq = "SELECT * from Distribuidora ORDER BY Nombre";
        datos = this.jdbc.queryForList(sq);
        mav.addObject("ListaDistribuidora", datos);

        // Genero
        String sql1 = "SELECT * from Genero ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql1);
        mav.addObject("ListaGenero", datos);

        //Director
        String sql3 = "SELECT * from Director ORDER BY NombreArtistico";
        datos = this.jdbc.queryForList(sql3);
        mav.addObject("ListaDirector", datos);

        mav.addObject(new Caricatura());
        mav.setViewName("altaCaricatura");
        return mav;
    }

    String nombre;

    //METODO PARA AGREGAR
    @RequestMapping(value = "altaCaricatura.htm", method = RequestMethod.POST)
    public ModelAndView Agregar(Caricatura c, HttpServletRequest request) {
        /*nombre = request.getParameter("nombre");
        String caricatura = "SELECT * FROM Caricatura WHERE Nombre LIKE ''" + nombre + "''";
        datos = this.jdbc.queryForList(caricatura);

        if (datos.isEmpty()) {
            String sql = "INSERT INTO Caricatura(Nombre, OtrosNombres, Genero, idProductora, idDistribuidora, idDirector, Portada) VALUES (?,?,?,?,?,?,?)";
            this.jdbc.update(sql, c.getNombre(), c.getOtrosNombres(), c.getGenero(), c.getIdProductora(), c.getIdDistribuidora(), c.getIdDirector(), c.getPortada());
            return new ModelAndView("redirect:/altaCaricatura.htm");
        }
        return new ModelAndView("redirect:/listaCaricatura.htm");*/
        String sql = "INSERT INTO Caricatura(Nombre, OtrosNombres, Genero, idProductora, idDistribuidora, idDirector, Portada) VALUES (?,?,?,?,?,?,?)";
        this.jdbc.update(sql, c.getNombre(), c.getOtrosNombres(), c.getGenero(), c.getIdProductora(), c.getIdDistribuidora(), c.getIdDirector(), c.getPortada());
        return new ModelAndView("redirect:/altaCaricatura.htm");
    }

    //METODO PARA LISTA
    int idCaricatura;
    List datos;

    @RequestMapping("listaCaricatura.htm")
    public ModelAndView Listar() {
        /*String sql = "SELECT\n"
                + "Caricatura.idCaricatura,\n"
                + "Caricatura.Nombre,\n"
                + "REPLACE(Caricatura.OtrosNombres, 'Ã±', 'ñ') AS NombreSecundario,\n"
                + "COUNT((Temporadas_Caricatura.idTemporada)) AS Temporadas,\n"
                + "SUM(Temporadas_Caricatura.Capitulos) AS Capitulos,\n"
                + "DATE_FORMAT((MIN(Temporadas_Caricatura.FechaInicio)), \"%d / %b / %Y\") As FechaInicio,\n"
                + "DATE_FORMAT((MAX(Temporadas_Caricatura.FechaFin)), \"%d / %b / %Y\") As FechaFin,\n"
                + "IF((AVG(Temporadas_Caricatura.Calificacion)) = 10, FORMAT((AVG(Temporadas_Caricatura.Calificacion)), 0),FORMAT((AVG(Temporadas_Caricatura.Calificacion)), 2)) AS Promedio,\n"
                + "Genero.Nombre AS Genero,\n"
                + "Productora.Nombre AS Productora,\n"
                + "Distribuidora.Nombre AS Distribuidora,\n"
                + "Director.NombreArtistico AS Director,\n"
                + "Caricatura.Portada\n"
                + "FROM Caricatura\n"
                + "LEFT JOIN Genero\n"
                + "ON Caricatura.Genero = Genero.idGenero\n"
                + "LEFT JOIN Productora\n"
                + "ON Caricatura.idProductora = Productora.idProductora\n"
                + "LEFT JOIN Productora AS Distribuidora\n"
                + "ON Caricatura.idDistribuidora = Distribuidora.idProductora\n"
                + "LEFT JOIN Director\n"
                + "ON Caricatura.idDirector = Director.idDirector\n"
                + "LEFT JOIN Temporadas_Caricatura\n"
                + "ON Temporadas_Caricatura.idCaricatura = Caricatura.idCaricatura\n"
                + "GROUP BY (Caricatura.idCaricatura)\n"
                + "ORDER BY Caricatura.Nombre";*/
        String caricaturas = "SELECT * FROM Vista_Caricaturas";
        datos = this.jdbc.queryForList(caricaturas);
        mav.addObject("Lista", datos);

        mav.setViewName("listaCaricatura");
        return mav;
    }

    @RequestMapping("buscaPeliculaCaricatura.htm")
    public ModelAndView Buscar() {
        /*String sql = "SELECT\n"
                + "Peliculas.idPelicula,\n"
                + "Peliculas.Nombre,\n"
                + "Peliculas.OtrosNombres AS NombreSecundario,\n"
                + "Productora.Nombre AS Productora,\n"
                + "Distribuidora.Nombre AS Distribuidora,\n"
                + "DATE_FORMAT(Peliculas.Duracion, \"%Hh %im\") AS Duracion,\n"
                + "Genero.Nombre AS Genero,\n"
                + "Peliculas.Tipo,\n"
                + "Clasificacion.Nombre AS Clasificacion,\n"
                + "DATE_FORMAT(Peliculas.Estreno, \"%d / %b / %Y\") AS Estreno,\n"
                + "DATE_FORMAT(Peliculas.EstrenoMexico, \"%d / %b / %Y\") AS EstrenoMexico,\n"
                + "IF(Peliculas.Calificacion = 10, FORMAT(Peliculas.Calificacion, 0),FORMAT(Peliculas.Calificacion, 1)) AS Calificacion,\n"
                + "IF(Peliculas.Estreno IS NULL, 'Proximamente', TIMESTAMPDIFF(Year, Peliculas.Estreno, NOW())) AS Anios,\n"
                + "Director.NombreArtistico AS Director,\n"
                + "Peliculas.Portada\n"
                + "FROM Peliculas\n"
                + "INNER JOIN Productora\n"
                + "ON Productora.idProductora = Peliculas.idProductora\n"
                + "INNER JOIN Distribuidora\n"
                + "ON Distribuidora.idDistribuidora = Peliculas.idDistribuidora\n"
                + "INNER JOIN Genero\n"
                + "ON Genero.idGenero = Peliculas.Genero\n"
                + "INNER JOIN Clasificacion\n"
                + "ON Clasificacion.idClasificacion = Peliculas.Clasificacion\n"
                + "INNER JOIN Director\n"
                + "ON Peliculas.idDirector = Director.idDirector\n"
                + "AND Peliculas.Tipo = 'Caricatura'\n"
                + "ORDER BY Peliculas.Nombre";*/
        String caricaturas = "SELECT * FROM Vista_Peliculas WHERE tipo = 'Caricatura'";
        datos = this.jdbc.queryForList(caricaturas);
        mav.addObject("Lista", datos);

        mav.setViewName("buscaPeliculaCaricatura");
        return mav;
    }

    @RequestMapping(value = "buscaTemporadaCaricatura.htm", method = RequestMethod.GET)
    public ModelAndView Buscar(HttpServletRequest request) {
        idCaricatura = Integer.parseInt(request.getParameter("idCaricatura"));
        /*String sql = "SELECT\n"
                + "Temporadas_Caricatura.idTemporada,\n"
                + "Caricatura.Nombre AS Caricatura,\n"
                + "Temporadas_Caricatura.Nombre,\n"
                + "Temporadas_Caricatura.Capitulos,\n"
                + "TIME_FORMAT(Duracion, \"%i\") AS Duracion,\n"
                + "IF(Calificacion = 10, FORMAT(Calificacion, 0),FORMAT(Calificacion, 1)) AS Calificacion,\n"
                + "DATE_FORMAT(FechaInicio, \"%d / %b / %Y\") AS FechaInicio,\n"
                + "DATE_FORMAT(FechaFin, \"%d / %b / %Y\") AS FechaFin\n"
                + "FROM Temporadas_Caricatura\n"
                + "INNER JOIN Caricatura\n"
                + "ON Temporadas_Caricatura.idCaricatura = Caricatura.idCaricatura\n"
                + "AND Temporadas_Caricatura.idCaricatura = " + idCaricatura;*/
        String temporadas_caricaturas = "SELECT * FROM Vista_TemporadasCaricatura WHERE idCaricatura = " + idCaricatura;
        datos = this.jdbc.queryForList(temporadas_caricaturas);
        mav.addObject("Lista", datos);

        //Nombre de la Caricatura
        String sql2 = "SELECT idCaricatura, Nombre from Caricatura WHERE idCaricatura = " + idCaricatura;
        datos = this.jdbc.queryForList(sql2);
        mav.addObject("Nombre", datos);

        mav.setViewName("buscaTemporadaCaricatura");
        return mav;
    }

    @RequestMapping(value = "buscaTemporadaCaricatura2.htm", method = RequestMethod.GET)
    public ModelAndView Buscar2(HttpServletRequest request) {
        idCaricatura = Integer.parseInt(request.getParameter("idCaricatura"));
        /*String sql = "SELECT\n"
                + "Temporadas_Caricatura.idTemporada,\n"
                + "Caricatura.Nombre AS Caricatura,\n"
                + "Temporadas_Caricatura.Nombre,\n"
                + "Temporadas_Caricatura.Capitulos,\n"
                + "TIME_FORMAT(Duracion, \"%i min\") AS Duracion,\n"
                + "IF(Calificacion = 10, FORMAT(Calificacion, 0),FORMAT(Calificacion, 2)) AS Calificacion,\n"
                + "CASE\n"
                + "WHEN MONTH(FechaInicio) = 1 THEN REPLACE(DATE_FORMAT(FechaInicio, \"%d / %b / %Y\"), 'Jan', 'Enero')\n"
                + "WHEN MONTH(FechaInicio) = 2 THEN REPLACE(DATE_FORMAT(FechaInicio, \"%d / %b / %Y\"), 'Feb', 'Febrero')\n"
                + "WHEN MONTH(FechaInicio) = 3 THEN REPLACE(DATE_FORMAT(FechaInicio, \"%d / %b / %Y\"), 'Mar', 'Marzo')\n"
                + "WHEN MONTH(FechaInicio) = 4 THEN REPLACE(DATE_FORMAT(FechaInicio, \"%d / %b / %Y\"), 'Apr', 'Abril')\n"
                + "WHEN MONTH(FechaInicio) = 5 THEN REPLACE(DATE_FORMAT(FechaInicio, \"%d / %b / %Y\"), 'May', 'Mayo')\n"
                + "WHEN MONTH(FechaInicio) = 6 THEN REPLACE(DATE_FORMAT(FechaInicio, \"%d / %b / %Y\"), 'Jun', 'Junio')\n"
                + "WHEN MONTH(FechaInicio) = 7 THEN REPLACE(DATE_FORMAT(FechaInicio, \"%d / %b / %Y\"), 'Jul', 'Julio')\n"
                + "WHEN MONTH(FechaInicio) = 8 THEN REPLACE(DATE_FORMAT(FechaInicio, \"%d / %b / %Y\"), 'Aug', 'Agosto')\n"
                + "WHEN MONTH(FechaInicio) = 9 THEN REPLACE(DATE_FORMAT(FechaInicio, \"%d / %b / %Y\"), 'Sep', 'Septiembre')\n"
                + "WHEN MONTH(FechaInicio) = 10 THEN REPLACE(DATE_FORMAT(FechaInicio, \"%d / %b / %Y\"), 'Oct', 'Octubre')\n"
                + "WHEN MONTH(FechaInicio) = 11 THEN REPLACE(DATE_FORMAT(FechaInicio, \"%d / %b / %Y\"), 'Nov', 'Noviembre')\n"
                + "ELSE REPLACE(DATE_FORMAT(FechaInicio, \"%d / %b / %Y\"), 'Dec', 'Diciembre')\n"
                + "END AS FechaInicio,\n"
                + "CASE\n"
                + "WHEN MONTH(FechaFin) = 1 THEN REPLACE(DATE_FORMAT(FechaFin, \"%d / %b / %Y\"), 'Jan', 'Enero')\n"
                + "WHEN MONTH(FechaFin) = 2 THEN REPLACE(DATE_FORMAT(FechaFin, \"%d / %b / %Y\"), 'Feb', 'Febrero')\n"
                + "WHEN MONTH(FechaFin) = 3 THEN REPLACE(DATE_FORMAT(FechaFin, \"%d / %b / %Y\"), 'Mar', 'Marzo')\n"
                + "WHEN MONTH(FechaFin) = 4 THEN REPLACE(DATE_FORMAT(FechaFin, \"%d / %b / %Y\"), 'Apr', 'Abril')\n"
                + "WHEN MONTH(FechaFin) = 5 THEN REPLACE(DATE_FORMAT(FechaFin, \"%d / %b / %Y\"), 'May', 'Mayo')\n"
                + "WHEN MONTH(FechaFin) = 6 THEN REPLACE(DATE_FORMAT(FechaFin, \"%d / %b / %Y\"), 'Jun', 'Junio')\n"
                + "WHEN MONTH(FechaFin) = 7 THEN REPLACE(DATE_FORMAT(FechaFin, \"%d / %b / %Y\"), 'Jul', 'Julio')\n"
                + "WHEN MONTH(FechaFin) = 8 THEN REPLACE(DATE_FORMAT(FechaFin, \"%d / %b / %Y\"), 'Aug', 'Agosto')\n"
                + "WHEN MONTH(FechaFin) = 9 THEN REPLACE(DATE_FORMAT(FechaFin, \"%d / %b / %Y\"), 'Sep', 'Septiembre')\n"
                + "WHEN MONTH(FechaFin) = 10 THEN REPLACE(DATE_FORMAT(FechaFin, \"%d / %b / %Y\"), 'Oct', 'Octubre')\n"
                + "WHEN MONTH(FechaFin) = 11 THEN REPLACE(DATE_FORMAT(FechaFin, \"%d / %b / %Y\"), 'Nov', 'Noviembre')\n"
                + "ELSE REPLACE(DATE_FORMAT(FechaFin, \"%d / %b / %Y\"), 'Dec', 'Diciembre')\n"
                + "END AS FechaFin\n"
                + "FROM Temporadas_Caricatura\n"
                + "INNER JOIN Caricatura\n"
                + "ON Temporadas_Caricatura.idCaricatura = Caricatura.idCaricatura\n"
                + "AND Temporadas_Caricatura.idCaricatura = " + idCaricatura + "\n"
                + "ORDER BY (Temporadas_Caricatura.FechaInicio)";*/
        String temporadas_caricaturas = "SELECT * FROM Vista_TemporadasCaricatura WHERE idCaricatura = " + idCaricatura + " ORDER BY Inicio";
        datos = this.jdbc.queryForList(temporadas_caricaturas);
        mav.addObject("Lista", datos);

        //Nombre de la Caricatura
        /*String sql2 = "SELECT\n"
                + "Caricatura.idCaricatura,\n"
                + "Caricatura.Nombre,\n"
                + "REPLACE(Caricatura.OtrosNombres, 'Ã±', 'ñ') AS OtrosNombres,\n"
                + "COUNT((Temporadas_Caricatura.idTemporada)) AS Temporadas,\n"
                + "SUM(Temporadas_Caricatura.Capitulos) AS Capitulos,\n"
                + "CASE\n"
                + "WHEN MONTH(MIN(Temporadas_Caricatura.FechaInicio)) = 1 THEN REPLACE(DATE_FORMAT(MIN(Temporadas_Caricatura.FechaInicio), \"%d / %b / %Y\"), 'Jan', 'Enero')\n"
                + "WHEN MONTH(MIN(Temporadas_Caricatura.FechaInicio)) = 2 THEN REPLACE(DATE_FORMAT(MIN(Temporadas_Caricatura.FechaInicio), \"%d / %b / %Y\"), 'Feb', 'Febrero')\n"
                + "WHEN MONTH(MIN(Temporadas_Caricatura.FechaInicio)) = 3 THEN REPLACE(DATE_FORMAT(MIN(Temporadas_Caricatura.FechaInicio), \"%d / %b / %Y\"), 'Mar', 'Marzo')\n"
                + "WHEN MONTH(MIN(Temporadas_Caricatura.FechaInicio)) = 4 THEN REPLACE(DATE_FORMAT(MIN(Temporadas_Caricatura.FechaInicio), \"%d / %b / %Y\"), 'Apr', 'Abril')\n"
                + "WHEN MONTH(MIN(Temporadas_Caricatura.FechaInicio)) = 5 THEN REPLACE(DATE_FORMAT(MIN(Temporadas_Caricatura.FechaInicio), \"%d / %b / %Y\"), 'May', 'Mayo')\n"
                + "WHEN MONTH(MIN(Temporadas_Caricatura.FechaInicio)) = 6 THEN REPLACE(DATE_FORMAT(MIN(Temporadas_Caricatura.FechaInicio), \"%d / %b / %Y\"), 'Jun', 'Junio')\n"
                + "WHEN MONTH(MIN(Temporadas_Caricatura.FechaInicio)) = 7 THEN REPLACE(DATE_FORMAT(MIN(Temporadas_Caricatura.FechaInicio), \"%d / %b / %Y\"), 'Jul', 'Julio')\n"
                + "WHEN MONTH(MIN(Temporadas_Caricatura.FechaInicio)) = 8 THEN REPLACE(DATE_FORMAT(MIN(Temporadas_Caricatura.FechaInicio), \"%d / %b / %Y\"), 'Aug', 'Agosto')\n"
                + "WHEN MONTH(MIN(Temporadas_Caricatura.FechaInicio)) = 9 THEN REPLACE(DATE_FORMAT(MIN(Temporadas_Caricatura.FechaInicio), \"%d / %b / %Y\"), 'Sep', 'Septiembre')\n"
                + "WHEN MONTH(MIN(Temporadas_Caricatura.FechaInicio)) = 10 THEN REPLACE(DATE_FORMAT(MIN(Temporadas_Caricatura.FechaInicio), \"%d / %b / %Y\"), 'Oct', 'Octubre')\n"
                + "WHEN MONTH(MIN(Temporadas_Caricatura.FechaInicio)) = 11 THEN REPLACE(DATE_FORMAT(MIN(Temporadas_Caricatura.FechaInicio), \"%d / %b / %Y\"), 'Nov', 'Noviembre')\n"
                + "ELSE REPLACE(DATE_FORMAT(MIN(Temporadas_Caricatura.FechaInicio), \"%d / %b / %Y\"), 'Dec', 'Diciembre')\n"
                + "END AS FechaInicio,\n"
                + "CASE\n"
                + "WHEN MONTH(MAX(Temporadas_Caricatura.FechaFin)) = 1 THEN REPLACE(DATE_FORMAT(MAX(Temporadas_Caricatura.FechaFin), \"%d / %b / %Y\"), 'Jan', 'Enero')\n"
                + "WHEN MONTH(MAX(Temporadas_Caricatura.FechaFin)) = 2 THEN REPLACE(DATE_FORMAT(MAX(Temporadas_Caricatura.FechaFin), \"%d / %b / %Y\"), 'Feb', 'Febrero')\n"
                + "WHEN MONTH(MAX(Temporadas_Caricatura.FechaFin)) = 3 THEN REPLACE(DATE_FORMAT(MAX(Temporadas_Caricatura.FechaFin), \"%d / %b / %Y\"), 'Mar', 'Marzo')\n"
                + "WHEN MONTH(MAX(Temporadas_Caricatura.FechaFin)) = 4 THEN REPLACE(DATE_FORMAT(MAX(Temporadas_Caricatura.FechaFin), \"%d / %b / %Y\"), 'Apr', 'Abril')\n"
                + "WHEN MONTH(MAX(Temporadas_Caricatura.FechaFin)) = 5 THEN REPLACE(DATE_FORMAT(MAX(Temporadas_Caricatura.FechaFin), \"%d / %b / %Y\"), 'May', 'Mayo')\n"
                + "WHEN MONTH(MAX(Temporadas_Caricatura.FechaFin)) = 6 THEN REPLACE(DATE_FORMAT(MAX(Temporadas_Caricatura.FechaFin), \"%d / %b / %Y\"), 'Jun', 'Junio')\n"
                + "WHEN MONTH(MAX(Temporadas_Caricatura.FechaFin)) = 7 THEN REPLACE(DATE_FORMAT(MAX(Temporadas_Caricatura.FechaFin), \"%d / %b / %Y\"), 'Jul', 'Julio')\n"
                + "WHEN MONTH(MAX(Temporadas_Caricatura.FechaFin)) = 8 THEN REPLACE(DATE_FORMAT(MAX(Temporadas_Caricatura.FechaFin), \"%d / %b / %Y\"), 'Aug', 'Agosto')\n"
                + "WHEN MONTH(MAX(Temporadas_Caricatura.FechaFin)) = 9 THEN REPLACE(DATE_FORMAT(MAX(Temporadas_Caricatura.FechaFin), \"%d / %b / %Y\"), 'Sep', 'Septiembre')\n"
                + "WHEN MONTH(MAX(Temporadas_Caricatura.FechaFin)) = 10 THEN REPLACE(DATE_FORMAT(MAX(Temporadas_Caricatura.FechaFin), \"%d / %b / %Y\"), 'Oct', 'Octubre')\n"
                + "WHEN MONTH(MAX(Temporadas_Caricatura.FechaFin)) = 11 THEN REPLACE(DATE_FORMAT(MAX(Temporadas_Caricatura.FechaFin), \"%d / %b / %Y\"), 'Nov', 'Noviembre')\n"
                + "ELSE REPLACE(DATE_FORMAT(MAX(Temporadas_Caricatura.FechaFin), \"%d / %b / %Y\"), 'Dec', 'Diciembre')\n"
                + "END AS FechaFin,\n"
                + "IF((AVG(Temporadas_Caricatura.Calificacion)) = 10, FORMAT((AVG(Temporadas_Caricatura.Calificacion)), 0),FORMAT((AVG(Temporadas_Caricatura.Calificacion)), 2)) AS Promedio,\n"
                + "Genero.Nombre AS Genero,\n"
                + "Productora.Nombre AS Productora,\n"
                + "Distribuidora.Nombre AS Distribuidora,\n"
                + "Director.NombreArtistico AS Director,\n"
                + "Caricatura.Portada\n"
                + "FROM Caricatura\n"
                + "INNER JOIN Genero\n"
                + "ON Caricatura.Genero = Genero.idGenero\n"
                + "INNER JOIN Productora\n"
                + "ON Caricatura.idProductora = Productora.idProductora\n"
                + "INNER JOIN Productora AS Distribuidora\n"
                + "ON Caricatura.idDistribuidora = Distribuidora.idProductora\n"
                + "INNER JOIN Director\n"
                + "ON Caricatura.idDirector = Director.idDirector\n"
                + "INNER JOIN Temporadas_Caricatura\n"
                + "ON Temporadas_Caricatura.idCaricatura = Caricatura.idCaricatura\n"
                + "AND Caricatura.idCaricatura = " + idCaricatura;*/
        String caricaturas = "SELECT * FROM Vista_Caricaturas WHERE idCaricatura = " + idCaricatura;
        datos = this.jdbc.queryForList(caricaturas);
        mav.addObject("Nombre", datos);

        mav.setViewName("buscaTemporadaCaricatura2");
        return mav;
    }

    @RequestMapping(value = "editarCaricatura.htm", method = RequestMethod.GET)
    public ModelAndView vistaEditar(HttpServletRequest request) {
        idCaricatura = Integer.parseInt(request.getParameter("idCaricatura"));
        String sql = "SELECT * from Caricatura WHERE idCaricatura=" + idCaricatura;
        datos = this.jdbc.queryForList(sql);
        mav.addObject("Lista", datos);

        //Productora
        String sq = "SELECT * from Productora ORDER BY Nombre";
        datos = this.jdbc.queryForList(sq);
        mav.addObject("ListaProductora", datos);

        //Distribuidora
        String s = "SELECT * from Distribuidora ORDER BY Nombre";
        datos = this.jdbc.queryForList(s);
        mav.addObject("ListaDistribuidora", datos);

        //Director
        String sql4 = "SELECT * from Director ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql4);
        mav.addObject("ListaDirector", datos);

        // Genero
        String sql1 = "SELECT * from Genero ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql1);
        mav.addObject("ListaGenero", datos);

        //Select
        String sql3 = "SELECT\n"
                + "Caricatura.idCaricatura,\n"
                + "Caricatura.OtrosNombres AS NombreSecundario,\n"
                + "Genero.Nombre AS Genero,\n"
                + "Productora.Nombre AS Productora,\n"
                + "Distribuidora.Nombre AS Distribuidora,\n"
                + "Director.NombreArtistico AS Director\n"
                + "FROM Caricatura\n"
                + "INNER JOIN Genero\n"
                + "ON Caricatura.Genero = Genero.idGenero\n"
                + "INNER JOIN Productora\n"
                + "ON Caricatura.idProductora = Productora.idProductora\n"
                + "INNER JOIN Productora AS Distribuidora\n"
                + "ON Caricatura.idDistribuidora = Distribuidora.idProductora\n"
                + "INNER JOIN Director\n"
                + "ON Caricatura.idDirector = Director.idDirector\n"
                + "AND Caricatura.idCaricatura = " + idCaricatura;
        datos = this.jdbc.queryForList(sql3);
        mav.addObject("ListaCaricatura", datos);

        mav.setViewName("editarCaricatura");
        return mav;
    }

    //METODO PARA EDITAR
    @RequestMapping(value = "editarCaricatura.htm", method = RequestMethod.POST)
    public ModelAndView Editar(Caricatura c) {
        String sql = "UPDATE Caricatura SET Nombre=?, OtrosNombres=?, Genero=?, idProductora=?, idDistribuidora=?, idDirector=?, Portada=? WHERE idCaricatura=?";
        this.jdbc.update(sql, c.getNombre(), c.getOtrosNombres(), c.getGenero(), c.getIdProductora(), c.getIdDistribuidora(), c.getIdDirector(), c.getPortada(), idCaricatura);
        return new ModelAndView("redirect:/listaCaricatura.htm");
    }

    //METODO PARA ELIMINAR
    @RequestMapping(value = "eliminarCaricatura.htm")
    public ModelAndView Eliminar(HttpServletRequest request) {
        idCaricatura = Integer.parseInt(request.getParameter("idCaricatura"));
        String sql = "DELETE from Caricatura WHERE idCaricatura=" + idCaricatura;
        this.jdbc.update(sql);
        return new ModelAndView("redirect:/listaCaricatura.htm");
    }
}
