/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import config.conexionBD;
import entidades.Estudio;
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
public class controladorEstudio {

    //Instancia para conexion
    conexionBD con = new conexionBD();
    JdbcTemplate jdbc = new JdbcTemplate(con.Conectar());
    ModelAndView mav = new ModelAndView(); //Importar la que dice SERVLET

    //Metodo para las vistas - METODO PARA OBTENER VISTAS
    @RequestMapping(value = "altaEstudio.htm", method = RequestMethod.GET)
    public ModelAndView VistaAgregar() {
        mav.addObject(new Estudio());
        mav.setViewName("altaEstudio");
        return mav;
    }

    String nombre;

    //METODO PARA AGREGAR
    @RequestMapping(value = "altaEstudio.htm", method = RequestMethod.POST)
    public ModelAndView Agregar(Estudio e, HttpServletRequest request) {
        nombre = request.getParameter("nombre");
        String estudio = "SELECT * FROM EstudioAnimacion WHERE Nombre LIKE '" + nombre + "'";
        datos = this.jdbc.queryForList(estudio);

        if (datos.isEmpty()) {
            String sql = "INSERT INTO EstudioAnimacion (Nombre, Fundacion, Imagen) values (?,?,?)";
            this.jdbc.update(sql, e.getNombre(), e.getFundacion(), e.getImagen());
            return new ModelAndView("redirect:/altaEstudio.htm");
        }
        return new ModelAndView("redirect:/listaEstudio.htm");

    }

    //METODO PARA LISTA
    List datos;
    int idEstudio;

    @RequestMapping("listaEstudio.htm")
    public ModelAndView Listar() {
        /*String sql = "SELECT\n"
                + "EstudioAnimacion.idEstudio,\n"
                + "TRIM(EstudioAnimacion.Nombre) AS Nombre,\n"
                + "COUNT((Temporadas_Anime.idTemporada)) AS Animes,\n"
                + "DATE_FORMAT(EstudioAnimacion.Fundacion, \"%d / %b / %Y\") AS Fundacion,\n"
                + "Imagen\n"
                + "FROM EstudioAnimacion\n"
                + "INNER JOIN Temporadas_Anime\n"
                + "ON Temporadas_Anime.idEstudio = EstudioAnimacion.idEstudio\n"
                + "GROUP BY (EstudioAnimacion.idEstudio)\n"
                + "ORDER BY EstudioAnimacion.Nombre";

        String sq = "SELECT\n"
                + "idEstudio,\n"
                + "Nombre,\n"
                + "DATE_FORMAT(EstudioAnimacion.Fundacion, \"%d / %M / %Y\") AS Fundacion,\n"
                + "Imagen\n"
                + "FROM EstudioAnimacion "
                + "ORDER BY EstudioAnimacion.Nombre";*/
        String estudio = "SELECT * FROM Vista_Estudio\n";
        datos = this.jdbc.queryForList(estudio);
        mav.addObject("Lista", datos);

        //Estudios
        String sql6 = "SELECT COUNT(idEstudio) AS Estudio FROM EstudioAnimacion";
        datos = this.jdbc.queryForList(sql6);
        mav.addObject("ListaEstudio", datos);

        mav.setViewName("listaEstudio");
        return mav;
    }

    //Vista 2
    @RequestMapping("listaEstudio2.htm")
    public ModelAndView Listar1() {
        /*String sql = "SELECT\n"
                + "EstudioAnimacion.idEstudio,\n"
                + "TRIM(EstudioAnimacion.Nombre) AS Nombre,\n"
                + "COUNT((Temporadas_Anime.idTemporada)) AS Animes,\n"
                + "DATE_FORMAT(EstudioAnimacion.Fundacion, \"%d / %b / %Y\") AS Fundacion,\n"
                + "Imagen\n"
                + "FROM EstudioAnimacion\n"
                + "INNER JOIN Temporadas_Anime\n"
                + "ON Temporadas_Anime.idEstudio = EstudioAnimacion.idEstudio\n"
                + "GROUP BY (EstudioAnimacion.idEstudio)\n"
                + "ORDER BY EstudioAnimacion.Nombre";*/

        String estudio = "SELECT * FROM Vista_Estudio\n";
        datos = this.jdbc.queryForList(estudio);
        mav.addObject("Lista", datos);

        mav.setViewName("listaEstudio2");
        return mav;
    }

    @RequestMapping(value = "buscarEstudio.htm", method = RequestMethod.GET)
    public ModelAndView Buscar(HttpServletRequest request) {
        idEstudio = Integer.parseInt(request.getParameter("idEstudio"));
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
                + "IFNULL((DATE_FORMAT(Temporadas_Anime.FechaFin, \"%d / %b / %Y\")), (DATE_FORMAT(NOW(), \"%d / %b / %Y\"))) AS FechaFin,\n"
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
                + "AND EstudioAnimacion.idEstudio = " + idEstudio + "\n"
                + "ORDER BY Temporadas_Anime.Nombre";*/
        
        String anime = "SELECT * FROM Vista_TemporadasAnime WHERE idEstudio = " + idEstudio + " ORDER BY Inicio";
        datos = this.jdbc.queryForList(anime);
        mav.addObject("Lista", datos);

        //Temporada
        String estudio = "SELECT Nombre AS Estudio FROM EstudioAnimacion WHERE idEstudio = " + idEstudio;
        datos = this.jdbc.queryForList(estudio);
        mav.addObject("Estudio", datos);

        //Conteo
        String conteo = "SELECT idEstudio, COUNT(idTemporada) AS Conteo FROM Temporadas_Anime WHERE idEstudio = " + idEstudio;
        datos = this.jdbc.queryForList(conteo);
        mav.addObject("Conteo", datos);

        mav.setViewName("buscarEstudio");
        return mav;
    }

    @RequestMapping(value = "buscarEstudio2.htm", method = RequestMethod.GET)
    public ModelAndView Buscar1(HttpServletRequest request) {
        idEstudio = Integer.parseInt(request.getParameter("idEstudio"));
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
                + "AND EstudioAnimacion.idEstudio = " + idEstudio + "\n"
                + "AND Temporadas_Anime.Duracion <= '00:59:00'\n"
                + "ORDER BY (Temporadas_Anime.FechaInicio)";*/
        String anime = "SELECT * FROM Vista_TemporadasAnime WHERE idEstudio = " + idEstudio + " AND Duration <= '00:59:00' ORDER BY Inicio";
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
                + "AND EstudioAnimacion.idEstudio = " + idEstudio + "\n"
                + "AND Temporadas_Anime.Duracion > '00:59:00'\n"
                + "ORDER BY (Temporadas_Anime.FechaInicio)";*/
        String panime = "SELECT * FROM Vista_TemporadasAnime WHERE idEstudio = " + idEstudio + " AND Duration > '00:59:00' ORDER BY Inicio";
        datos = this.jdbc.queryForList(panime);
        mav.addObject("Lista1", datos);

        //Nombre Estudio
        String estudio = "SELECT idEstudio, Nombre AS Estudio FROM EstudioAnimacion WHERE idEstudio = " + idEstudio;
        datos = this.jdbc.queryForList(estudio);
        mav.addObject("Estudio", datos);

        //Conteo Animes
        String conteo = "SELECT COUNT(idTemporada) AS Conteo FROM Temporadas_Anime WHERE Duracion <= '00:59:00' AND idEstudio = " + idEstudio;
        datos = this.jdbc.queryForList(conteo);
        mav.addObject("Conteo", datos);
        
        //Conteo Animes
        String Pelicula = "SELECT COUNT(idTemporada) AS Conteo FROM Temporadas_Anime WHERE Duracion > '00:59:00' AND idEstudio = " + idEstudio;
        datos = this.jdbc.queryForList(Pelicula);
        mav.addObject("Pelicula", datos);

        mav.setViewName("buscarEstudio2");
        return mav;
    }

    //Editar
    @RequestMapping(value = "editarEstudio.htm", method = RequestMethod.GET)
    public ModelAndView vistaEditar(HttpServletRequest request) {
        idEstudio = Integer.parseInt(request.getParameter("idEstudio"));
        String sql = "SELECT * FROM EstudioAnimacion WHERE idEstudio = " + idEstudio;
        datos = this.jdbc.queryForList(sql);
        mav.addObject("Lista", datos);
        mav.setViewName("editarEstudio");
        return mav;
    }

    //METODO PARA EDITAR
    @RequestMapping(value = "editarEstudio.htm", method = RequestMethod.POST)
    public ModelAndView Editar(Estudio e) {
        String sql = "UPDATE EstudioAnimacion SET Nombre=?, Fundacion=?, Imagen=? WHERE idEstudio=?";
        this.jdbc.update(sql, e.getNombre(), e.getFundacion(), e.getImagen(), idEstudio);
        return new ModelAndView("redirect:/listaEstudio.htm");
    }

    //METODO PARA ELIMINAR
    @RequestMapping(value = "eliminarEstudio.htm")
    public ModelAndView Eliminar(HttpServletRequest request) {
        idEstudio = Integer.parseInt(request.getParameter("idEstudio"));
        String sql = "DELETE FROM EstudioAnimacion WHERE idEstudio=" + idEstudio;
        this.jdbc.update(sql);
        return new ModelAndView("redirect:/listaEstudio.htm");
    }
}
