/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import config.conexionBD;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Raúl
 */
@Controller
public class controladorIndex {
    //Instancia para conexion
    conexionBD con = new conexionBD();
    JdbcTemplate jdbc = new JdbcTemplate(con.Conectar());
    ModelAndView mav = new ModelAndView(); //Importar la que dice SERVLET
    
    //METODO PARA LISTA
    List datos;

    @RequestMapping("index.htm")
    public ModelAndView Listar() {
        //Animes
        String sql = "SELECT COUNT(idAnime) AS Anime FROM Anime";
        datos = this.jdbc.queryForList(sql);
        mav.addObject("ListaAnime", datos);
        
        //Series
        String sql2 = "SELECT COUNT(idSerie) AS Serie FROM Series";
        datos = this.jdbc.queryForList(sql2);
        mav.addObject("ListaSerie", datos);
        
        //Caricaturas
        String sql3 = "SELECT COUNT(idCaricatura) AS Caricatura FROM Caricatura";
        datos = this.jdbc.queryForList(sql3);
        mav.addObject("ListaCaricatura", datos);
        
        //Peliculas
        String sql4 = "SELECT COUNT(idPelicula) AS Pelicula FROM Peliculas";
        datos = this.jdbc.queryForList(sql4);
        mav.addObject("ListaPelicula", datos);

        mav.setViewName("index");
        return mav;
    }
}
