/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import config.conexionBD;
import entidades.Anime;
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
public class controladorAnime {

    //Instancia para conexion
    conexionBD con = new conexionBD();
    JdbcTemplate jdbc = new JdbcTemplate(con.Conectar());
    ModelAndView mav = new ModelAndView(); //Importar la que dice SERVLET

    //Metodo para las vistas - METODO PARA OBTENER VISTAS
    @RequestMapping(value = "altaAnime.htm", method = RequestMethod.GET)
    public ModelAndView VistaAgregar() {

        mav.addObject(new Anime());
        mav.setViewName("altaAnime");
        return mav;
    }

    String nombre;

    //METODO PARA AGREGAR
    @RequestMapping(value = "altaAnime.htm", method = RequestMethod.POST)
    public ModelAndView Agregar(Anime a, HttpServletRequest request) {
        nombre = request.getParameter("nombre");
        String anime = "SELECT * FROM Anime WHERE Nombre LIKE '" + nombre + "'";
        datos = this.jdbc.queryForList(anime);

        if (datos.isEmpty()) {
            String sql = "INSERT INTO Anime(Nombre, OtrosNombres, Genero) VALUES (?,?,?)";
            this.jdbc.update(sql, a.getNombre(), a.getOtroNombre(), a.getGenero());
            return new ModelAndView("redirect:/altaAnime.htm");
        }
        return new ModelAndView("redirect:/listaAnime.htm");

    }
    //METODO PARA LISTA
    int idAnime;
    List datos;

    @RequestMapping("listaAnime.htm")
    public ModelAndView Listar() {
        /*String sql = "SELECT\n"
                + "Anime.idAnime,\n"
                + "TRIM(Anime.Nombre) AS Nombre,\n"
                + "TRIM(Anime.OtrosNombres) AS OtrosNombres,\n"
                + "Anime.Genero, \n"
                + "Temporadas_Anime.Portada,\n"
                + "COUNT((Temporadas_Anime.idTemporada)) AS Temporadas,\n"
                + "SUM(Temporadas_Anime.Capitulos) AS Capitulos,\n"
                + "DATE_FORMAT((MIN(Temporadas_Anime.FechaInicio)), \"%d / %b / %Y\") As FechaInicio,\n"
                + "IF(Temporadas_Anime.FechaFin IS NULL AND Temporadas_Anime.FechaInicio IS NULL,' ' ,IF(MAX(Temporadas_Anime.FechaInicio > NOW()), DATE_FORMAT(MAX(Temporadas_Anime.FechaFin), \"%d / %b / %Y\"), (IF (MIN(Temporadas_Anime.FechaFin IS NOT NULL), (DATE_FORMAT((MAX(Temporadas_Anime.FechaFin)), \"%d / %b / %Y\")), (IF(NOW() < Temporadas_Anime.FechaInicio, \" \", DATE_FORMAT(NOW(), \"%d / %b / %Y\") )))))) As FechaFin,\n"
                + "IF(Temporadas_Anime.FechaFin IS NULL AND Temporadas_Anime.FechaInicio IS NULL, ' ', IF(MAX(Temporadas_Anime.FechaInicio > NOW()), \"Estreno\", IF (MIN(Temporadas_Anime.FechaFin IS NOT NULL), \"Finalizado\", \"Emision\"))) AS Estado,\n"
                + "IFNULL((IF((AVG(Temporadas_Anime.Calificacion)) = 10, FORMAT((AVG(Temporadas_Anime.Calificacion)), 0),FORMAT((AVG(Temporadas_Anime.Calificacion)), 2))), \" \") AS Promedio\n"
                + "FROM Anime \n"
                + "LEFT JOIN Temporadas_Anime\n"
                + "ON Temporadas_Anime.idAnime = Anime.idAnime\n"
                + "AND Temporadas_Anime.Duracion <= '00:59:00'\n"
                + "GROUP BY (Anime.idAnime)";*/
        
        String anime = "SELECT * FROM Vista_Animes";
        datos = this.jdbc.queryForList(anime);
        mav.addObject("Lista", datos);

        mav.setViewName("listaAnime");
        return mav;
    }

    @RequestMapping(value = "buscaTemporadaAnime.htm", method = RequestMethod.GET)
    public ModelAndView Buscar(HttpServletRequest request) {
        idAnime = Integer.parseInt(request.getParameter("idAnime"));
        /*String sql = "SELECT\n"
                + "Temporadas_Anime.idTemporada,\n"
                + "TRIM(Anime.Nombre) AS Anime,\n"
                + "TRIM(Temporadas_Anime.Nombre) AS Nombre,\n"
                + "TRIM(Temporadas_Anime.OtrosNombres) AS OtrosNombres,\n"
                + "Temporadas_Anime.Capitulos,\n"
                + "IF(DATE_FORMAT(Duracion, \"%H\") = '00', DATE_FORMAT(Temporadas_Anime.Duracion, \"%i\"), DATE_FORMAT(Temporadas_Anime.Duracion, \"%Hh %im\")) AS Duracion,\n"
                + "Temporadas_Anime.Idioma,\n"
                + "REPLACE(Temporadas_Emision.Nombre, \"Otonio\", \"Otoño\") AS Emision,\n"
                + "CASE\n"
                + "WHEN DAYNAME(Temporadas_Anime.FechaInicio) = 'Monday' THEN 'Lunes'\n"
                + "WHEN DAYNAME(Temporadas_Anime.FechaInicio) = 'Tuesday' THEN 'Martes'\n"
                + "WHEN DAYNAME(Temporadas_Anime.FechaInicio) = 'Wednesday' THEN 'Miercoles'\n"
                + "WHEN DAYNAME(Temporadas_Anime.FechaInicio) = 'Thursday' THEN 'Jueves'\n"
                + "WHEN DAYNAME(Temporadas_Anime.FechaInicio) = 'Friday' THEN 'Viernes'\n"
                + "WHEN DAYNAME(Temporadas_Anime.FechaInicio) = 'Saturday' THEN 'Sabado'\n"
                + "ELSE 'Domingo'\n"
                + "END AS DiaEmision,\n"
                + "DATE_FORMAT(Temporadas_Anime.FechaInicio, \"%d / %b / %Y\") AS FechaInicio,\n"
                + "IF(Temporadas_Anime.FechaInicio > NOW(), \"\", IFNULL((DATE_FORMAT(Temporadas_Anime.FechaFin, \"%d / %b / %Y\")), (DATE_FORMAT(NOW(), \"%d / %b / %Y\")))) AS FechaFin,\n"
                + "IFNULL((TIMESTAMPDIFF(WEEK,Temporadas_Anime.FechaInicio, Temporadas_Anime.FechaFin)), TIMESTAMPDIFF(WEEK,Temporadas_Anime.FechaInicio, NOW())) AS Semanas,\n"
                + "TIMESTAMPDIFF(YEAR, Temporadas_Anime.FechaInicio, NOW()) AS Años,\n"
                + "EstudioAnimacion.Nombre AS Estudio,\n"
                + "IF(Calificacion = 10, FORMAT(Calificacion, 0),FORMAT(Calificacion, 2)) AS Calificacion,\n"
                + "Temporadas_Anime.Portada\n"
                + "FROM Temporadas_Anime\n"
                + "INNER JOIN Anime\n"
                + "ON Temporadas_Anime.idAnime = Anime.idAnime\n"
                + "INNER JOIN Temporadas_Emision\n"
                + "ON Temporadas_Anime.idTemporadaEmision = Temporadas_Emision.idTemporada\n"
                + "INNER JOIN EstudioAnimacion\n"
                + "ON Temporadas_Anime.idEstudio = EstudioAnimacion.idEstudio\n"
                + "AND Anime.idAnime = " + idAnime;*/
        
        String anime = "SELECT * FROM Vista_TemporadasAnime WHERE idAnime = " + idAnime + " ORDER BY Inicio";
        datos = this.jdbc.queryForList(anime);
        mav.addObject("Lista", datos);

        //Nombre del Anime
        String sql2 = "SELECT * FROM Vista_Anime WHERE idAnime = " + idAnime;
        datos = this.jdbc.queryForList(sql2);
        mav.addObject("Nombre", datos);

        mav.setViewName("buscaTemporadaAnime");
        return mav;
    }

    //Vista Carta
    @RequestMapping(value = "buscaTemporadaAnime2.htm", method = RequestMethod.GET)
    public ModelAndView Buscar2(HttpServletRequest request) {
        idAnime = Integer.parseInt(request.getParameter("idAnime"));
        /*String sql = "SELECT\n"
                + "Temporadas_Anime.idTemporada,\n"
                + "TRIM(Anime.Nombre) AS Anime,\n"
                + "TRIM(Temporadas_Anime.Nombre) AS Nombre,\n"
                + "TRIM(Temporadas_Anime.OtrosNombres) AS OtrosNombres,\n"
                + "Temporadas_Anime.Capitulos,\n"
                + "IF(DATE_FORMAT(Duracion, \"%H\") = '00', DATE_FORMAT(Temporadas_Anime.Duracion, \"%i min\"), DATE_FORMAT(Temporadas_Anime.Duracion, \"%Hh %im\")) AS Duracion,\n"
                + "Temporadas_Anime.Idioma,\n"
                + "REPLACE(Temporadas_Emision.Nombre, \"Otonio\", \"Otoño\") AS Emision,\n"
                + "CASE\n"
                + "WHEN DAYNAME(Temporadas_Anime.FechaInicio) = 'Monday' THEN 'Lunes'\n"
                + "WHEN DAYNAME(Temporadas_Anime.FechaInicio) = 'Tuesday' THEN 'Martes'\n"
                + "WHEN DAYNAME(Temporadas_Anime.FechaInicio) = 'Wednesday' THEN 'Miercoles'\n"
                + "WHEN DAYNAME(Temporadas_Anime.FechaInicio) = 'Thursday' THEN 'Jueves'\n"
                + "WHEN DAYNAME(Temporadas_Anime.FechaInicio) = 'Friday' THEN 'Viernes'\n"
                + "WHEN DAYNAME(Temporadas_Anime.FechaInicio) = 'Saturday' THEN 'Sabado'\n"
                + "ELSE 'Domingo'\n"
                + "END AS DiaEmision,\n"
                + "DATE_FORMAT(Temporadas_Anime.FechaInicio, \"%d / %b / %Y\") AS FechaInicio,\n"
                + "IF(Temporadas_Anime.FechaInicio > NOW(), \"\", IFNULL((DATE_FORMAT(Temporadas_Anime.FechaFin, \"%d / %b / %Y\")), (DATE_FORMAT(NOW(), \"%d / %b / %Y\")))) AS FechaFin,\n"
                + "IFNULL((TIMESTAMPDIFF(WEEK,Temporadas_Anime.FechaInicio, Temporadas_Anime.FechaFin)), TIMESTAMPDIFF(WEEK,Temporadas_Anime.FechaInicio, NOW())) AS Semanas,\n"
                + "TIMESTAMPDIFF(YEAR, Temporadas_Anime.FechaInicio, NOW()) AS Años,\n"
                + "EstudioAnimacion.Nombre AS Estudio,\n"
                + "IF(Calificacion = 10, FORMAT(Calificacion, 0),FORMAT(Calificacion, 2)) AS Calificacion,\n"
                + "Temporadas_Anime.Portada\n"
                + "FROM Temporadas_Anime\n"
                + "INNER JOIN Anime\n"
                + "ON Temporadas_Anime.idAnime = Anime.idAnime\n"
                + "INNER JOIN Temporadas_Emision\n"
                + "ON Temporadas_Anime.idTemporadaEmision = Temporadas_Emision.idTemporada\n"
                + "INNER JOIN EstudioAnimacion\n"
                + "ON Temporadas_Anime.idEstudio = EstudioAnimacion.idEstudio\n"
                + "AND Anime.idAnime = " + idAnime + "\n"
                + "AND Temporadas_Anime.Duracion <= '00:59:00'\n"
                + "ORDER BY (Temporadas_Anime.FechaInicio)";*/
        
        String anime = "SELECT * FROM Vista_TemporadasAnime WHERE idAnime = " + idAnime + " AND Duration <= '00:59:00' ORDER BY Inicio";
        datos = this.jdbc.queryForList(anime);
        mav.addObject("Lista", datos);

        //Peliculas
        /*String peli = "SELECT\n"
                + "Temporadas_Anime.idTemporada,\n"
                + "TRIM(Anime.Nombre) AS Anime,\n"
                + "TRIM(Temporadas_Anime.Nombre) AS Nombre,\n"
                + "TRIM(Temporadas_Anime.OtrosNombres) AS OtrosNombres,\n"
                + "Temporadas_Anime.Capitulos,\n"
                + "IF(DATE_FORMAT(Duracion, \"%H\") = '00', DATE_FORMAT(Temporadas_Anime.Duracion, \"%i min\"), DATE_FORMAT(Temporadas_Anime.Duracion, \"%Hh %im\")) AS Duracion,\n"
                + "Temporadas_Anime.Idioma,\n"
                + "REPLACE(Temporadas_Emision.Nombre, \"Otonio\", \"Otoño\") AS Emision,\n"
                + "CASE\n"
                + "WHEN DAYNAME(Temporadas_Anime.FechaInicio) = 'Monday' THEN 'Lunes'\n"
                + "WHEN DAYNAME(Temporadas_Anime.FechaInicio) = 'Tuesday' THEN 'Martes'\n"
                + "WHEN DAYNAME(Temporadas_Anime.FechaInicio) = 'Wednesday' THEN 'Miercoles'\n"
                + "WHEN DAYNAME(Temporadas_Anime.FechaInicio) = 'Thursday' THEN 'Jueves'\n"
                + "WHEN DAYNAME(Temporadas_Anime.FechaInicio) = 'Friday' THEN 'Viernes'\n"
                + "WHEN DAYNAME(Temporadas_Anime.FechaInicio) = 'Saturday' THEN 'Sabado'\n"
                + "ELSE 'Domingo'\n"
                + "END AS DiaEmision,\n"
                + "DATE_FORMAT(Temporadas_Anime.FechaInicio, \"%d / %b / %Y\") AS FechaInicio,\n"
                + "IF(Temporadas_Anime.FechaInicio > NOW(), \"\", IFNULL((DATE_FORMAT(Temporadas_Anime.FechaFin, \"%d / %b / %Y\")), (DATE_FORMAT(NOW(), \"%d / %b / %Y\")))) AS FechaFin,\n"
                + "IFNULL((TIMESTAMPDIFF(WEEK,Temporadas_Anime.FechaInicio, Temporadas_Anime.FechaFin)), TIMESTAMPDIFF(WEEK,Temporadas_Anime.FechaInicio, NOW())) AS Semanas,\n"
                + "TIMESTAMPDIFF(YEAR, Temporadas_Anime.FechaInicio, NOW()) AS Años,\n"
                + "EstudioAnimacion.Nombre AS Estudio,\n"
                + "IF(Calificacion = 10, FORMAT(Calificacion, 0),FORMAT(Calificacion, 2)) AS Calificacion,\n"
                + "Temporadas_Anime.Portada\n"
                + "FROM Temporadas_Anime\n"
                + "INNER JOIN Anime\n"
                + "ON Temporadas_Anime.idAnime = Anime.idAnime\n"
                + "INNER JOIN Temporadas_Emision\n"
                + "ON Temporadas_Anime.idTemporadaEmision = Temporadas_Emision.idTemporada\n"
                + "INNER JOIN EstudioAnimacion\n"
                + "ON Temporadas_Anime.idEstudio = EstudioAnimacion.idEstudio\n"
                + "AND Anime.idAnime = " + idAnime + "\n"
                + "AND Temporadas_Anime.Duracion > '00:59:00'\n"
                + "ORDER BY (Temporadas_Anime.FechaInicio)";*/
        
        String panime = "SELECT * FROM Vista_TemporadasAnime WHERE idAnime = " + idAnime + " AND Duration > '00:59:00' ORDER BY Inicio";
        datos = this.jdbc.queryForList(panime);
        mav.addObject("Lista1", datos);

        //Nombre del Anime
        String sql2 = "SELECT idAnime, Nombre from Anime WHERE idAnime = " + idAnime;
        datos = this.jdbc.queryForList(sql2);
        mav.addObject("Nombre", datos);

        //Temporadas
        String sql1 = "SELECT\n"
                + "COUNT(idTemporada) AS Temporada,\n"
                + "IFNULL((IF((AVG(Calificacion)) = 10, FORMAT((AVG(Calificacion)), 0),FORMAT((AVG(Calificacion)), 2))), \" \") AS Promedio\n"
                + "FROM Temporadas_Anime\n"
                + "WHERE Duracion <= '00:59:00'\n"
                + "AND idAnime = " + idAnime;
        datos = this.jdbc.queryForList(sql1);
        mav.addObject("Conteo", datos);

        //Peliculas
        String peliculas = "SELECT\n"
                + "COUNT(idTemporada) AS Temporada,\n"
                + "IFNULL((IF((AVG(Calificacion)) = 10, FORMAT((AVG(Calificacion)), 0),FORMAT((AVG(Calificacion)), 2))), \" \") AS Promedio\n"
                + "FROM Temporadas_Anime\n"
                + "WHERE Duracion > '00:59:00'\n"
                + "AND idAnime = " + idAnime;
        datos = this.jdbc.queryForList(peliculas);
        mav.addObject("Pelicula", datos);

        mav.setViewName("buscaTemporadaAnime2");
        return mav;
    }

    //Editar
    @RequestMapping(value = "editarAnime.htm", method = RequestMethod.GET)
    public ModelAndView vistaEditar(HttpServletRequest request) {
        idAnime = Integer.parseInt(request.getParameter("idAnime"));
        String sql = "SELECT * from Anime WHERE idAnime=" + idAnime;
        datos = this.jdbc.queryForList(sql);
        mav.addObject("Lista", datos);

        mav.setViewName("editarAnime");
        return mav;
    }

    //METODO PARA EDITAR
    @RequestMapping(value = "editarAnime.htm", method = RequestMethod.POST)
    public ModelAndView Editar(Anime a) {
        String sql = "UPDATE Anime SET Nombre=?, OtrosNombres=?, Genero=? WHERE idAnime=?";
        this.jdbc.update(sql, a.getNombre(), a.getOtroNombre(), a.getGenero(), idAnime);
        return new ModelAndView("redirect:/listaAnime.htm");
    }

    //METODO PARA ELIMINAR
    @RequestMapping(value = "eliminarAnime.htm")
    public ModelAndView Eliminar(HttpServletRequest request) {
        idAnime = Integer.parseInt(request.getParameter("idAnime"));
        String sql = "DELETE from Anime WHERE idAnime=" + idAnime;
        this.jdbc.update(sql);
        return new ModelAndView("redirect:/listaAnime.htm");
    }
}
