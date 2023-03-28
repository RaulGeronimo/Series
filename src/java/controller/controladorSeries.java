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
        String Series = "SELECT * FROM Vista_Series";
        datos = this.jdbc.queryForList(Series);
        mav.addObject("Lista", datos);

        mav.setViewName("listaSerie");
        return mav;
    }

    @RequestMapping(value = "buscaTemporadaSerie.htm")
    public ModelAndView Buscar(HttpServletRequest request) {
        idSerie = Integer.parseInt(request.getParameter("idSerie"));
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
        String temporadas_series = "SELECT * FROM Vista_TemporadasSerie WHERE idSerie = " + idSerie + " ORDER BY Inicio";

        datos = this.jdbc.queryForList(temporadas_series);
        mav.addObject("Lista", datos);

        //Nombre de la Serie
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
