/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import config.conexionBD;
import entidades.PeliculaAnime;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author raul0
 */
@Controller
public class controladorPeliculaAnime {
    //Instancia para conexion
    conexionBD con = new conexionBD();
    JdbcTemplate jdbc = new JdbcTemplate(con.Conectar());
    ModelAndView mav = new ModelAndView(); //Importar la que dice SERVLET
    
    //Metodo para las vistas - METODO PARA OBTENER VISTAS
    @RequestMapping(value = "altaPeliculaAnime.htm", method = RequestMethod.GET)
    public ModelAndView VistaAgregar() {
        //Productora
        String sql = "SELECT * from Productora ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql);
        mav.addObject("ListaProductora", datos);

        // Genero
        String sql1 = "SELECT * from Genero ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql1);
        mav.addObject("ListaGenero", datos);
        
        //Distribuidora
        String sq = "SELECT * from Distribuidora ORDER BY Nombre";
        datos = this.jdbc.queryForList(sq);
        mav.addObject("ListaDistribuidora", datos);

        // Clasificacion
        String sql2 = "SELECT * from Clasificacion ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql2);
        mav.addObject("ListaClasificacion", datos);

        //Director
        String sql3 = "SELECT * from Director ORDER BY NombreArtistico";
        datos = this.jdbc.queryForList(sql3);
        mav.addObject("ListaDirector", datos);
        
        //Temporada Emision
        String sql7 = "SELECT *, REPLACE(Nombre, \"Otonio\", \"Otoño\") AS Emision from Temporadas_Emision ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql7);
        mav.addObject("ListaEmision", datos);
        
        //Estudio de Animacion
        String sql21 = "SELECT * from EstudioAnimacion ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql21);
        mav.addObject("ListaEstudio", datos);

        mav.addObject(new PeliculaAnime());
        mav.setViewName("altaPeliculaAnime");
        return mav;
    }

    //METODO PARA AGREGAR
    @RequestMapping(value = "altaPeliculaAnime.htm", method = RequestMethod.POST)
    public ModelAndView Agregar(PeliculaAnime p) {
        String sql = "INSERT INTO PeliculasAnime(Nombre, OtrosNombres, idProductora, idDistribuidora, Duracion, Genero, Tipo, Clasificacion, Estreno, EstrenoMexico, Calificacion, idDirector, Portada, idTemporadaEmision, idEstudio) VALUES (?,?,?,?,?,?,'Anime',?,?,?,?,?,?,?,?)";
        this.jdbc.update(sql, p.getNombre(), p.getOtrosNombres(), p.getIdProductora(), p.getIdDistribuidora(), p.getDuracion(), p.getGenero(), p.getClasificacion(), p.getEstreno(), p.getEstrenoMexico(), p.getCalificacion(), p.getIdDirector(), p.getPortada(), p.getIdTemporadaEmision(), p.getIdEstudio());
        return new ModelAndView("redirect:/altaPeliculaAnime.htm");
    }
    
    //METODO PARA LISTA
    int idPelicula;
    List datos;
}
