/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import config.conexionBD;
import entidades.TemporadasSeries;
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
public class controladorTemporadasSeries {

    //Instancia para conexion
    conexionBD con = new conexionBD();
    JdbcTemplate jdbc = new JdbcTemplate(con.Conectar());
    ModelAndView mav = new ModelAndView(); //Importar la que dice SERVLET

    
    /////////////FINALIZADO
    //Metodo para las vistas - METODO PARA OBTENER VISTAS
    @RequestMapping(value = "altaTemporadaSerie.htm", method = RequestMethod.GET)
    public ModelAndView VistaAgregar() {
        //Serie
        String sql = "SELECT * from Series ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql);
        mav.addObject("ListaSerie", datos);

        mav.addObject(new TemporadasSeries());
        mav.setViewName("altaTemporadaSerie");
        return mav;
    }

    //METODO PARA AGREGAR
    @RequestMapping(value = "altaTemporadaSerie.htm", method = RequestMethod.POST)
    public ModelAndView Agregar(TemporadasSeries t) {
        String sql = "INSERT INTO Temporadas_Series(idSerie, Nombre, Capitulos, Duracion, Calificacion, FechaInicio, FechaFin) VALUES (?,?,?,?,?,?,?)";
        this.jdbc.update(sql, t.getIdSerie(), t.getNombre(), t.getCapitulos(), t.getDuracion(), t.getCalificacion(), t.getFechaInicio(), t.getFechaFin());
        return new ModelAndView("redirect:/altaTemporadaSerie.htm");
    }
    
    /////////////EMISION
    //Metodo para las vistas - METODO PARA OBTENER VISTAS
    @RequestMapping(value = "altaTemporadaSerieEmision.htm", method = RequestMethod.GET)
    public ModelAndView VistaAgregar1() {
        //Serie
        String sql = "SELECT * from Series ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql);
        mav.addObject("ListaSerie", datos);

        mav.addObject(new TemporadasSeries());
        mav.setViewName("altaTemporadaSerieEmision");
        return mav;
    }

    //METODO PARA AGREGAR
    @RequestMapping(value = "altaTemporadaSerieEmision.htm", method = RequestMethod.POST)
    public ModelAndView Agregar1(TemporadasSeries t) {
        String sql = "INSERT INTO Temporadas_Series(idSerie, Nombre, Capitulos, Duracion, FechaInicio) VALUES (?,?,?,?,?)";
        this.jdbc.update(sql, t.getIdSerie(), t.getNombre(), t.getCapitulos(), t.getDuracion(), t.getFechaInicio());
        return new ModelAndView("redirect:/altaTemporadaSerie.htm");
    }

    //METODO PARA LISTA
    int idTemporada;
    List datos;

    @RequestMapping("listaTemporadaSerie.htm")
    public ModelAndView Listar() {
        String temporadas_series = "SELECT * FROM Vista_TemporadasSerie";
        datos = this.jdbc.queryForList(temporadas_series);
        mav.addObject("Lista", datos);

        mav.setViewName("listaTemporadaSerie");
        return mav;
    }

    /////Finalizado
    @RequestMapping(value = "editarTemporadaSerie.htm", method = RequestMethod.GET)
    public ModelAndView vistaEditar(HttpServletRequest request) {
        idTemporada = Integer.parseInt(request.getParameter("idTemporada"));
        String sql = "SELECT * from Temporadas_Series WHERE idTemporada=" + idTemporada;
        datos = this.jdbc.queryForList(sql);
        mav.addObject("Lista", datos);

        //Serie
        String sql2 = "SELECT * from Series ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql2);
        mav.addObject("ListaSerie", datos);

        //Select
        String sql3 = "SELECT\n"
                + "Temporadas_Series.idTemporada,\n"
                + "CONCAT_WS(' - ', Series.Nombre, Series.OtrosNombres) AS Serie\n"
                + "FROM Temporadas_Series\n"
                + "INNER JOIN Series\n"
                + "ON Temporadas_Series.idSerie = Series.idSerie\n"
                + "AND idTemporada = " + idTemporada;
        datos = this.jdbc.queryForList(sql3);
        mav.addObject("ListaTemporada", datos);

        mav.setViewName("editarTemporadaSerie");
        return mav;
    }

    //METODO PARA EDITAR
    @RequestMapping(value = "editarTemporadaSerie.htm", method = RequestMethod.POST)
    public ModelAndView Editar(TemporadasSeries t) {
        String sql = "UPDATE Temporadas_Series SET idSerie=?, Nombre=?, Capitulos=?, Duracion=?, Calificacion=?, FechaInicio=?, FechaFin=? WHERE idTemporada=?";
        this.jdbc.update(sql, t.getIdSerie(), t.getNombre(), t.getCapitulos(), t.getDuracion(), t.getCalificacion(), t.getFechaInicio(), t.getFechaFin(), idTemporada);
        return new ModelAndView("redirect:/listaTemporadaSerie.htm");
    }
    
    /////Emision
    @RequestMapping(value = "editarTemporadaSerieEmision.htm", method = RequestMethod.GET)
    public ModelAndView vistaEditar1(HttpServletRequest request) {
        idTemporada = Integer.parseInt(request.getParameter("idTemporada"));
        String sql = "SELECT * from Temporadas_Series WHERE idTemporada=" + idTemporada;
        datos = this.jdbc.queryForList(sql);
        mav.addObject("Lista", datos);

        //Serie
        String sql2 = "SELECT * from Series ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql2);
        mav.addObject("ListaSerie", datos);

        //Select
        String sql3 = "SELECT\n"
                + "Temporadas_Series.idTemporada,\n"
                + "CONCAT_WS(' - ', Series.Nombre, Series.OtrosNombres) AS Serie\n"
                + "FROM Temporadas_Series\n"
                + "INNER JOIN Series\n"
                + "ON Temporadas_Series.idSerie = Series.idSerie\n"
                + "AND idTemporada = " + idTemporada;
        datos = this.jdbc.queryForList(sql3);
        mav.addObject("ListaTemporada", datos);

        mav.setViewName("editarTemporadaSerieEmision");
        return mav;
    }

    //METODO PARA EDITAR
    @RequestMapping(value = "editarTemporadaSerieEmision.htm", method = RequestMethod.POST)
    public ModelAndView Editar1(TemporadasSeries t) {
        String sql = "UPDATE Temporadas_Series SET idSerie=?, Nombre=?, Capitulos=?, Duracion=?, FechaInicio=? WHERE idTemporada=?";
        this.jdbc.update(sql, t.getIdSerie(), t.getNombre(), t.getCapitulos(), t.getDuracion(), t.getFechaInicio(), idTemporada);
        return new ModelAndView("redirect:/listaTemporadaSerie.htm");
    }

    //METODO PARA ELIMINAR
    @RequestMapping(value = "eliminarTemporadaSerie.htm")
    public ModelAndView Eliminar(HttpServletRequest request) {
        idTemporada = Integer.parseInt(request.getParameter("idTemporada"));
        String sql = "DELETE from Temporadas_Series WHERE idTemporada=" + idTemporada;
        this.jdbc.update(sql);
        return new ModelAndView("redirect:/listaTemporadaSerie.htm");
    }
}
