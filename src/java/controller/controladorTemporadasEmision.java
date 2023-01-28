/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import config.conexionBD;
import entidades.TemporadasEmision;
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
public class controladorTemporadasEmision {

    //Instancia para conexion
    conexionBD con = new conexionBD();
    JdbcTemplate jdbc = new JdbcTemplate(con.Conectar());
    ModelAndView mav = new ModelAndView(); //Importar la que dice SERVLET

    //Metodo para las vistas - METODO PARA OBTENER VISTAS
    @RequestMapping(value = "altaTemporadasEmision.htm", method = RequestMethod.GET)
    public ModelAndView VistaAgregar() {
        mav.addObject(new TemporadasEmision());
        mav.setViewName("altaTemporadasEmision");
        return mav;
    }

    String nombre;

    //METODO PARA AGREGAR
    @RequestMapping(value = "altaTemporadasEmision.htm", method = RequestMethod.POST)
    public ModelAndView Agregar(TemporadasEmision t, HttpServletRequest request) {
        nombre = request.getParameter("nombre");
        String temporada = "SELECT * FROM Temporadas_Emision WHERE Nombre LIKE '" + nombre + "'";
        datos = this.jdbc.queryForList(temporada);

        if (datos.isEmpty()) {
            String sql = "INSERT INTO Temporadas_Emision(Nombre, FechaInicio, FechaFin) VALUES (?,?,?)";
            this.jdbc.update(sql, t.getNombre(), t.getFechaInicio(), t.getFechaFin());
            return new ModelAndView("redirect:/altaTemporadasEmision.htm");
        }
        return new ModelAndView("redirect:/listaTemporadasEmision.htm");
    }

    //METODO PARA LISTA
    int idTemporada;
    List datos;

    @RequestMapping("listaTemporadasEmision.htm")
    public ModelAndView Listar() {
        /*String sql = "SELECT\n"
                + "Temporadas_Emision.idTemporada,\n"
                + "REPLACE(Temporadas_Emision.Nombre, \"Otonio\", \"Otoño\") AS Nombre,\n"
                + "DATE_FORMAT(Temporadas_Emision.FechaInicio, \"%d / %b / %Y\") AS FechaInicio,\n"
                + "DATE_FORMAT(Temporadas_Emision.FechaFin, \"%d / %b / %Y\") AS FechaFin,\n"
                + "COUNT((Temporadas_Anime.idTemporadaEmision)) AS Animes,\n"
                + "IFNULL((IF((AVG(Temporadas_Anime.Calificacion)) = 10, FORMAT((AVG(Temporadas_Anime.Calificacion)), 0),FORMAT((AVG(Temporadas_Anime.Calificacion)), 2))), \" \") AS Promedio\n"
                + "FROM Temporadas_Emision\n"
                + "INNER JOIN Temporadas_Anime\n"
                + "ON Temporadas_Anime.idTemporadaEmision = Temporadas_Emision.idTemporada\n"
                + "GROUP BY (Temporadas_Emision.idTemporada)\n"
                + "ORDER BY (Temporadas_Emision.Nombre)";*/
        String anime = "SELECT * FROM Vista_TemporadasEmision";
        datos = this.jdbc.queryForList(anime);
        mav.addObject("Lista", datos);

        mav.setViewName("listaTemporadasEmision");
        return mav;
    }

    @RequestMapping(value = "buscarAnimesTemporada.htm", method = RequestMethod.GET)
    public ModelAndView Buscar(HttpServletRequest request) {
        idTemporada = Integer.parseInt(request.getParameter("idTemporada"));
        /*String sql = "SELECT\n"
                + "Temporadas_Anime.idTemporada,\n"
                + "TRIM(Anime.Nombre) AS Anime,\n"
                + "TRIM(Temporadas_Anime.Nombre) AS Nombre,\n"
                + "TRIM(Temporadas_Anime.OtrosNombres) AS OtrosNombres,\n"
                + "Temporadas_Anime.Capitulos,\n"
                + "TIME_FORMAT(Temporadas_Anime.Duracion, \"%i\") AS Duracion,\n"
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
                + "AND Temporadas_Emision.idTemporada = " + idTemporada;*/
        
        String anime = "SELECT * FROM Vista_TemporadasAnime WHERE idTemporadaEmision = " + idTemporada + " ORDER BY Inicio";
        datos = this.jdbc.queryForList(anime);
        mav.addObject("Lista", datos);

        //Nombre de la Temporada
        String sql2 = "SELECT \n"
                + "idTemporada, \n"
                + "REPLACE(Nombre, \"Otonio\", \"Otoño\") AS Emision \n"
                + "FROM Temporadas_Emision \n"
                + "WHERE idTemporada = " + idTemporada;
        datos = this.jdbc.queryForList(sql2);
        mav.addObject("Nombre", datos);

        //CONTEO
        String conteo = "SELECT \n"
                + "COUNT(idTemporadaEmision) AS Conteo \n"
                + "FROM Temporadas_Anime \n"
                + "WHERE idTemporadaEmision = " + idTemporada;
        datos = this.jdbc.queryForList(conteo);
        mav.addObject("Conteo", datos);

        mav.setViewName("buscarAnimesTemporada");
        return mav;
    }

    @RequestMapping(value = "buscarAnimesTemporada2.htm", method = RequestMethod.GET)
    public ModelAndView Buscar2(HttpServletRequest request) {
        idTemporada = Integer.parseInt(request.getParameter("idTemporada"));
        /*String sql = "SELECT\n"
                + "Temporadas_Anime.idTemporada,\n"
                + "TRIM(Anime.Nombre) AS Anime,\n"
                + "TRIM(Temporadas_Anime.Nombre) AS Nombre,\n"
                + "TRIM(Temporadas_Anime.OtrosNombres) AS OtrosNombres,\n"
                + "Temporadas_Anime.Capitulos,\n"
                + "TIME_FORMAT(Temporadas_Anime.Duracion, \"%i\") AS Duracion,\n"
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
                + "AND Temporadas_Emision.idTemporada = " + idTemporada + "\n"
                + "AND Temporadas_Anime.Duracion <= '00:59:00'\n"
                + "ORDER BY (Temporadas_Anime.FechaInicio)";*/
        
        String anime = "SELECT * FROM Vista_TemporadasAnime WHERE idTemporadaEmision = " + idTemporada + " AND Duration <= '00:59:00' ORDER BY Inicio";
        datos = this.jdbc.queryForList(anime);
        mav.addObject("Lista", datos);
        
        //Pelicula
       /* String peli = "SELECT\n"
                + "Temporadas_Anime.idTemporada,\n"
                + "TRIM(Anime.Nombre) AS Anime,\n"
                + "TRIM(Temporadas_Anime.Nombre) AS Nombre,\n"
                + "TRIM(Temporadas_Anime.OtrosNombres) AS OtrosNombres,\n"
                + "Temporadas_Anime.Capitulos,\n"
                + "TIME_FORMAT(Temporadas_Anime.Duracion, \"%i\") AS Duracion,\n"
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
                + "AND Temporadas_Emision.idTemporada = " + idTemporada + "\n"
                + "AND Temporadas_Anime.Duracion > '00:59:00'\n"
                + "ORDER BY (Temporadas_Anime.FechaInicio)";*/
        String panime = "SELECT * FROM Vista_TemporadasAnime WHERE idTemporadaEmision = " + idTemporada + " AND Duration > '00:59:00' ORDER BY Inicio";
        datos = this.jdbc.queryForList(panime);
        mav.addObject("Lista1", datos);

        //Nombre de la Temporada
        String sql2 = "SELECT \n"
                + "idTemporada, \n"
                + "REPLACE(Nombre, \"Otonio\", \"Otoño\") AS Emision\n"
                + "FROM Temporadas_Emision \n"
                + "WHERE idTemporada = " + idTemporada;
        datos = this.jdbc.queryForList(sql2);
        mav.addObject("Nombre", datos);

        //CONTEO ANIME
        String conteo = "SELECT \n"
                + "COUNT(idTemporadaEmision) AS Conteo\n,"
                + "IFNULL((IF((AVG(Calificacion)) = 10, FORMAT((AVG(Calificacion)), 0),FORMAT((AVG(Calificacion)), 2))), \" \") AS Promedio\n"
                + "FROM Temporadas_Anime \n"
                + "WHERE Duracion <= '00:59:00'\n"
                + "AND idTemporadaEmision = " + idTemporada;
        datos = this.jdbc.queryForList(conteo);
        mav.addObject("Conteo", datos);
        
        //CONTEO PELICULA
        String Pelicula = "SELECT \n"
                + "COUNT(idTemporadaEmision) AS Conteo\n,"
                + "IFNULL((IF((AVG(Calificacion)) = 10, FORMAT((AVG(Calificacion)), 0),FORMAT((AVG(Calificacion)), 2))), \" \") AS Promedio\n"
                + "FROM Temporadas_Anime \n"
                + "WHERE Duracion > '00:59:00'\n"
                + "AND idTemporadaEmision = " + idTemporada;
        datos = this.jdbc.queryForList(Pelicula);
        mav.addObject("Pelicula", datos);

        mav.setViewName("buscarAnimesTemporada2");
        return mav;
    }

    @RequestMapping(value = "editarTemporadasEmision.htm", method = RequestMethod.GET)
    public ModelAndView vistaEditar(HttpServletRequest request) {
        idTemporada = Integer.parseInt(request.getParameter("idTemporada"));
        String sql = "SELECT * from Temporadas_Emision WHERE idTemporada=" + idTemporada;
        datos = this.jdbc.queryForList(sql);
        mav.addObject("Lista", datos);

        mav.setViewName("editarTemporadasEmision");
        return mav;
    }

    //METODO PARA EDITAR
    @RequestMapping(value = "editarTemporadasEmision.htm", method = RequestMethod.POST)
    public ModelAndView Editar(TemporadasEmision t) {
        String sql = "UPDATE Temporadas_Emision SET Nombre=?, FechaInicio=?, FechaFin=? WHERE idTemporada=?";
        this.jdbc.update(sql, t.getNombre(), t.getFechaInicio(), t.getFechaFin(), idTemporada);
        return new ModelAndView("redirect:/listaTemporadasEmision.htm");
    }

    //METODO PARA ELIMINAR
    @RequestMapping(value = "eliminarTemporadasEmision.htm")
    public ModelAndView Eliminar(HttpServletRequest request) {
        idTemporada = Integer.parseInt(request.getParameter("idTemporada"));
        String sql = "DELETE from Temporadas_Emision WHERE idTemporada=" + idTemporada;
        this.jdbc.update(sql);
        return new ModelAndView("redirect:/listaTemporadasEmision.htm");
    }
}
