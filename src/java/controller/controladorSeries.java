/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import config.conexionBD;
import entidades.Series;
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
public class controladorSeries {

    //Instancia para conexion
    conexionBD con = new conexionBD();
    JdbcTemplate jdbc = new JdbcTemplate(con.Conectar());
    ModelAndView mav = new ModelAndView(); //Importar la que dice SERVLET

    //Metodo para las vistas - METODO PARA OBTENER VISTAS
    @RequestMapping(value = "altaSerie.htm", method = RequestMethod.GET)
    public ModelAndView VistaAgregar() {
        //Productora
        String sql = "SELECT * from Productora ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql);
        mav.addObject("ListaProductora", datos);

        // Genero
        String sql1 = "SELECT * from Genero ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql1);
        mav.addObject("ListaGenero", datos);

        //Director
        String sql3 = "SELECT * from Director ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql3);
        mav.addObject("ListaDirector", datos);

        //Distribuidora
        String sql4 = "SELECT * FROM Distribuidora ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql4);
        mav.addObject("ListaDistribuidora", datos);

        mav.addObject(new Series());
        mav.setViewName("altaSerie");
        return mav;
    }

    String nombre;

    //METODO PARA AGREGAR
    @RequestMapping(value = "altaSerie.htm", method = RequestMethod.POST)
    public ModelAndView Agregar(Series s, HttpServletRequest request) {
        nombre = request.getParameter("nombre");
        String temporada = "SELECT * FROM Series WHERE Nombre LIKE '%" + nombre + "%'";
        datos = this.jdbc.queryForList(temporada);

        if (datos.isEmpty()) {
            String sql = "INSERT INTO Series(Nombre, OtrosNombres, Genero, idProductora, idDistribuidora, idDirector, Portada) VALUES (?,?,?,?,?,?,?)";
            this.jdbc.update(sql, s.getNombre(), s.getOtrosNombres(), s.getGenero(), s.getIdProductora(), s.getIdDistribuidora(), s.getIdDirector(), s.getPortada());
            return new ModelAndView("redirect:/altaSerie.htm");
        }
        return new ModelAndView("redirect:/listaSerie.htm");
    }

    //METODO PARA LISTA
    int idSerie;
    List datos;

    @RequestMapping("listaSerie.htm")
    public ModelAndView Listar() {
        /*String sql = "SELECT\n"
                + "Series.idSerie,\n"
                + "Series.Nombre,\n"
                + "Series.OtrosNombres,\n"
                + "COUNT(Temporadas_Series.idTemporada) AS Temporadas,\n"
                + "SUM(Temporadas_Series.Capitulos) AS Capitulos,\n"
                + "DATE_FORMAT(MIN(Temporadas_Series.FechaInicio), \"%d / %b / %Y\") As FechaInicio,\n"
                + "DATE_FORMAT(MAX(Temporadas_Series.FechaFin), \"%d / %b / %Y\") As FechaFin,\n"
                + "IF(AVG(Temporadas_Series.Calificacion) = 10, FORMAT(AVG(Temporadas_Series.Calificacion), 0),FORMAT(AVG(Temporadas_Series.Calificacion), 2)) AS Promedio,\n"
                + "Genero.Nombre AS Genero,\n"
                + "Productora.Nombre AS Productora,\n"
                + "Distribuidora.Nombre AS Distribuidora,\n"
                + "Director.NombreArtistico AS Director,\n"
                + "Series.Portada\n"
                + "FROM Series\n"
                + "LEFT JOIN Genero\n"
                + "ON Genero.idGenero = Series.Genero\n"
                + "LEFT JOIN Productora\n"
                + "ON Series.idProductora = Productora.idProductora\n"
                + "LEFT JOIN Distribuidora\n"
                + "ON Series.idDistribuidora = Distribuidora.idDistribuidora\n"
                + "LEFT JOIN Director\n"
                + "ON Series.idDirector = Director.idDirector\n"
                + "LEFT JOIN Temporadas_Series\n"
                + "ON Temporadas_Series.idSerie = Series.idSerie\n"
                + "GROUP BY (Series.idSerie)\n"
                + "ORDER BY Series.Nombre";*/

        String Series = "SELECT * FROM Vista_Series";
        datos = this.jdbc.queryForList(Series);
        mav.addObject("Lista", datos);

        mav.setViewName("listaSerie");
        return mav;
    }

    @RequestMapping(value = "buscaTemporadaSerie.htm")
    public ModelAndView Buscar(HttpServletRequest request) {
        idSerie = Integer.parseInt(request.getParameter("idSerie"));
        /*String sql = "SELECT\n"
                + "Temporadas_Series.idTemporada,\n"
                + "Series.Nombre AS Serie,\n"
                + "Temporadas_Series.Nombre,\n"
                + "Temporadas_Series.Capitulos,\n"
                + "TIME_FORMAT(Duracion, \"%i\") AS Duracion,\n"
                + "IF(Calificacion = 10, FORMAT(Calificacion, 0),FORMAT(Calificacion, 1)) AS Calificacion,\n"
                + "DATE_FORMAT(FechaInicio, \"%d / %b / %Y\") AS FechaInicio,\n"
                + "DATE_FORMAT(FechaFin, \"%d / %b / %Y\") AS FechaFin\n"
                + "FROM Temporadas_Series\n"
                + "INNER JOIN Series\n"
                + "ON Temporadas_Series.idSerie = Series.idSerie\n"
                + "AND Series.idSerie = " + idSerie;*/
        
        String temporadas_series = "SELECT * FROM Vista_TemporadasSerie WHERE idSerie = " + idSerie + " ORDER BY Inicio";
        
        datos = this.jdbc.queryForList(temporadas_series);
        mav.addObject("Lista", datos);

        //Nombre de la Serie
        String sql2 = "SELECT idSerie, Nombre from Series WHERE idSerie = " + idSerie;
        datos = this.jdbc.queryForList(sql2);
        mav.addObject("Nombre", datos);

        mav.setViewName("buscaTemporadaSerie");
        return mav;
    }

    @RequestMapping(value = "buscaTemporadaSerie2.htm")
    public ModelAndView Buscar2(HttpServletRequest request) {
        idSerie = Integer.parseInt(request.getParameter("idSerie"));
        /*String sql = "SELECT\n"
                + "Temporadas_Series.idTemporada,\n"
                + "Series.Nombre AS Serie,\n"
                + "Temporadas_Series.Nombre,\n"
                + "Temporadas_Series.Capitulos,\n"
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
                + "FROM Temporadas_Series\n"
                + "INNER JOIN Series\n"
                + "ON Temporadas_Series.idSerie = Series.idSerie\n"
                + "AND Series.idSerie = " + idSerie;*/
        
        String temporadas_series = "SELECT * FROM Vista_TemporadasSerie WHERE idSerie = " + idSerie + " ORDER BY Inicio";

        datos = this.jdbc.queryForList(temporadas_series);
        mav.addObject("Lista", datos);

        //Nombre de la Serie
        /*String sql2 = "SELECT\n"
                + "Series.idSerie,\n"
                + "Series.Nombre,\n"
                + "Series.OtrosNombres,\n"
                + "COUNT(Temporadas_Series.idTemporada) AS Temporadas,\n"
                + "SUM(Temporadas_Series.Capitulos) AS Capitulos,\n"
                + "CASE\n"
                + "WHEN MONTH(MIN(Temporadas_Series.FechaInicio)) = 1 THEN REPLACE(DATE_FORMAT(MIN(Temporadas_Series.FechaInicio), \"%d / %b / %Y\"), 'Jan', 'Enero')\n"
                + "WHEN MONTH(MIN(Temporadas_Series.FechaInicio)) = 2 THEN REPLACE(DATE_FORMAT(MIN(Temporadas_Series.FechaInicio), \"%d / %b / %Y\"), 'Feb', 'Febrero')\n"
                + "WHEN MONTH(MIN(Temporadas_Series.FechaInicio)) = 3 THEN REPLACE(DATE_FORMAT(MIN(Temporadas_Series.FechaInicio), \"%d / %b / %Y\"), 'Mar', 'Marzo')\n"
                + "WHEN MONTH(MIN(Temporadas_Series.FechaInicio)) = 4 THEN REPLACE(DATE_FORMAT(MIN(Temporadas_Series.FechaInicio), \"%d / %b / %Y\"), 'Apr', 'Abril')\n"
                + "WHEN MONTH(MIN(Temporadas_Series.FechaInicio)) = 5 THEN REPLACE(DATE_FORMAT(MIN(Temporadas_Series.FechaInicio), \"%d / %b / %Y\"), 'May', 'Mayo')\n"
                + "WHEN MONTH(MIN(Temporadas_Series.FechaInicio)) = 6 THEN REPLACE(DATE_FORMAT(MIN(Temporadas_Series.FechaInicio), \"%d / %b / %Y\"), 'Jun', 'Junio')\n"
                + "WHEN MONTH(MIN(Temporadas_Series.FechaInicio)) = 7 THEN REPLACE(DATE_FORMAT(MIN(Temporadas_Series.FechaInicio), \"%d / %b / %Y\"), 'Jul', 'Julio')\n"
                + "WHEN MONTH(MIN(Temporadas_Series.FechaInicio)) = 8 THEN REPLACE(DATE_FORMAT(MIN(Temporadas_Series.FechaInicio), \"%d / %b / %Y\"), 'Aug', 'Agosto')\n"
                + "WHEN MONTH(MIN(Temporadas_Series.FechaInicio)) = 9 THEN REPLACE(DATE_FORMAT(MIN(Temporadas_Series.FechaInicio), \"%d / %b / %Y\"), 'Sep', 'Septiembre')\n"
                + "WHEN MONTH(MIN(Temporadas_Series.FechaInicio)) = 10 THEN REPLACE(DATE_FORMAT(MIN(Temporadas_Series.FechaInicio), \"%d / %b / %Y\"), 'Oct', 'Octubre')\n"
                + "WHEN MONTH(MIN(Temporadas_Series.FechaInicio)) = 11 THEN REPLACE(DATE_FORMAT(MIN(Temporadas_Series.FechaInicio), \"%d / %b / %Y\"), 'Nov', 'Noviembre')\n"
                + "ELSE REPLACE(DATE_FORMAT(MIN(Temporadas_Series.FechaInicio), \"%d / %b / %Y\"), 'Dec', 'Diciembre')\n"
                + "END AS FechaInicio,\n"
                + "CASE\n"
                + "WHEN MONTH(MAX(Temporadas_Series.FechaFin)) = 1 THEN REPLACE(DATE_FORMAT(MAX(Temporadas_Series.FechaFin), \"%d / %b / %Y\"), 'Jan', 'Enero')\n"
                + "WHEN MONTH(MAX(Temporadas_Series.FechaFin)) = 2 THEN REPLACE(DATE_FORMAT(MAX(Temporadas_Series.FechaFin), \"%d / %b / %Y\"), 'Feb', 'Febrero')\n"
                + "WHEN MONTH(MAX(Temporadas_Series.FechaFin)) = 3 THEN REPLACE(DATE_FORMAT(MAX(Temporadas_Series.FechaFin), \"%d / %b / %Y\"), 'Mar', 'Marzo')\n"
                + "WHEN MONTH(MAX(Temporadas_Series.FechaFin)) = 4 THEN REPLACE(DATE_FORMAT(MAX(Temporadas_Series.FechaFin), \"%d / %b / %Y\"), 'Apr', 'Abril')\n"
                + "WHEN MONTH(MAX(Temporadas_Series.FechaFin)) = 5 THEN REPLACE(DATE_FORMAT(MAX(Temporadas_Series.FechaFin), \"%d / %b / %Y\"), 'May', 'Mayo')\n"
                + "WHEN MONTH(MAX(Temporadas_Series.FechaFin)) = 6 THEN REPLACE(DATE_FORMAT(MAX(Temporadas_Series.FechaFin), \"%d / %b / %Y\"), 'Jun', 'Junio')\n"
                + "WHEN MONTH(MAX(Temporadas_Series.FechaFin)) = 7 THEN REPLACE(DATE_FORMAT(MAX(Temporadas_Series.FechaFin), \"%d / %b / %Y\"), 'Jul', 'Julio')\n"
                + "WHEN MONTH(MAX(Temporadas_Series.FechaFin)) = 8 THEN REPLACE(DATE_FORMAT(MAX(Temporadas_Series.FechaFin), \"%d / %b / %Y\"), 'Aug', 'Agosto')\n"
                + "WHEN MONTH(MAX(Temporadas_Series.FechaFin)) = 9 THEN REPLACE(DATE_FORMAT(MAX(Temporadas_Series.FechaFin), \"%d / %b / %Y\"), 'Sep', 'Septiembre')\n"
                + "WHEN MONTH(MAX(Temporadas_Series.FechaFin)) = 10 THEN REPLACE(DATE_FORMAT(MAX(Temporadas_Series.FechaFin), \"%d / %b / %Y\"), 'Oct', 'Octubre')\n"
                + "WHEN MONTH(MAX(Temporadas_Series.FechaFin)) = 11 THEN REPLACE(DATE_FORMAT(MAX(Temporadas_Series.FechaFin), \"%d / %b / %Y\"), 'Nov', 'Noviembre')\n"
                + "ELSE REPLACE(DATE_FORMAT(MAX(Temporadas_Series.FechaFin), \"%d / %b / %Y\"), 'Dec', 'Diciembre')\n"
                + "END AS FechaFin,\n"
                + "IF(AVG(Temporadas_Series.Calificacion) = 10, FORMAT(AVG(Temporadas_Series.Calificacion), 0),FORMAT(AVG(Temporadas_Series.Calificacion), 2)) AS Promedio,\n"
                + "Genero.Nombre AS Genero,\n"
                + "Productora.Nombre AS Productora,\n"
                + "Distribuidora.Nombre AS Distribuidora,\n"
                + "Director.NombreArtistico AS Director,\n"
                + "Series.Portada\n"
                + "FROM Series\n"
                + "INNER JOIN Genero\n"
                + "ON Genero.idGenero = Series.Genero\n"
                + "INNER JOIN Productora\n"
                + "ON Series.idProductora = Productora.idProductora\n"
                + "INNER JOIN Distribuidora\n"
                + "ON Series.idDistribuidora = Distribuidora.idDistribuidora\n"
                + "INNER JOIN Director\n"
                + "ON Series.idDirector = Director.idDirector\n"
                + "INNER JOIN Temporadas_Series\n"
                + "ON Temporadas_Series.idSerie = Series.idSerie\n"
                + "AND Series.idSerie = " + idSerie;*/
        String Series = "SELECT * FROM Vista_Series WHERE idSerie = " + idSerie;
        datos = this.jdbc.queryForList(Series);
        mav.addObject("Nombre", datos);

        mav.setViewName("buscaTemporadaSerie2");
        return mav;
    }

    @RequestMapping(value = "editarSerie.htm", method = RequestMethod.GET)
    public ModelAndView vistaEditar(HttpServletRequest request) {
        idSerie = Integer.parseInt(request.getParameter("idSerie"));
        String sq = "SELECT * from Series WHERE idSerie=" + idSerie;
        datos = this.jdbc.queryForList(sq);
        mav.addObject("Lista", datos);

        //Productora
        String sql = "SELECT * from Productora ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql);
        mav.addObject("ListaProductora", datos);

        // Genero
        String sql1 = "SELECT * from Genero ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql1);
        mav.addObject("ListaGenero", datos);

        //Director
        String sql3 = "SELECT * from Director ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql3);
        mav.addObject("ListaDirector", datos);

        //Distribuidora
        String sql4 = "SELECT * FROM Distribuidora ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql4);
        mav.addObject("ListaDistribuidora", datos);

        //Select
        String sql5 = "SELECT\n"
                + "Series.idSerie,\n"
                + "Genero.Nombre AS Genero,\n"
                + "Productora.Nombre AS Productora,\n"
                + "Distribuidora.Nombre AS Distribuidora,\n"
                + "Director.NombreArtistico AS Director\n"
                + "FROM Series\n"
                + "INNER JOIN Genero\n"
                + "ON Genero.idGenero = Series.Genero\n"
                + "INNER JOIN Productora\n"
                + "ON Series.idProductora = Productora.idProductora\n"
                + "INNER JOIN Distribuidora\n"
                + "ON Series.idDistribuidora = Distribuidora.idDistribuidora\n"
                + "INNER JOIN Director\n"
                + "ON Series.idDirector = Director.idDirector\n"
                + "AND Series.idSerie = " + idSerie;
        datos = this.jdbc.queryForList(sql5);
        mav.addObject("ListaSerie", datos);

        mav.setViewName("editarSerie");
        return mav;
    }

    //METODO PARA EDITAR
    @RequestMapping(value = "editarSerie.htm", method = RequestMethod.POST)
    public ModelAndView Editar(Series s) {
        String sql = "UPDATE Series SET Nombre=?, OtrosNombres=?, Genero=?, idProductora=?, idDistribuidora=?, idDirector=?, Portada=? WHERE idSerie=?";
        this.jdbc.update(sql, s.getNombre(), s.getOtrosNombres(), s.getGenero(), s.getIdProductora(), s.getIdDistribuidora(), s.getIdDirector(), s.getPortada(), idSerie);
        return new ModelAndView("redirect:/listaSerie.htm");
    }

    //METODO PARA ELIMINAR
    @RequestMapping(value = "eliminarSerie.htm")
    public ModelAndView Eliminar(HttpServletRequest request) {
        idSerie = Integer.parseInt(request.getParameter("idSerie"));
        String sql = "DELETE from Series WHERE idSerie=" + idSerie;
        this.jdbc.update(sql);
        return new ModelAndView("redirect:/listaSerie.htm");
    }
}
