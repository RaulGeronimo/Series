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
public class controladorContenido {
    //Instancia para conexion
    conexionBD con = new conexionBD();
    JdbcTemplate jdbc = new JdbcTemplate(con.Conectar());
    ModelAndView mav = new ModelAndView(); //Importar la que dice SERVLET
    
    //METODO PARA LISTA
    List datos;

    @RequestMapping("Contenido.htm")
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
        //String sql4 = "SELECT COUNT(idPelicula) AS Pelicula FROM Peliculas";
        String sql4 = "SELECT SUM((SELECT COUNT(*) AS Peli FROM Peliculas)+(SELECT COUNT(*) AS Peli FROM PeliculaAnime)) AS Peliculas";
        String sq4 = "SELECT COUNT(idPelicula) AS Pelicula FROM Peliculas";
        datos = this.jdbc.queryForList(sq4);
        mav.addObject("ListaPelicula", datos);
        
        //Director
        String sql5 = "SELECT COUNT(idDirector) AS Director FROM Director";
        datos = this.jdbc.queryForList(sql5);
        mav.addObject("ListaDirector", datos);
        
        //Estudios
        String sql6 = "SELECT COUNT(idEstudio) AS Estudio FROM EstudioAnimacion";
        datos = this.jdbc.queryForList(sql6);
        mav.addObject("ListaEstudio", datos);
        
        //Productora
        String sql7 = "SELECT COUNT(idProductora) AS Productora FROM Productora";
        datos = this.jdbc.queryForList(sql7);
        mav.addObject("ListaProductora", datos);
        
        //Usuario
        String sql8 = "SELECT COUNT(IdUsuario) AS Usuario FROM Usuario";
        datos = this.jdbc.queryForList(sql8);
        mav.addObject("ListaUsuario", datos);
        
        //Clasificacion
        String sql9 = "SELECT COUNT(IdClasificacion) AS clasificacion FROM Clasificacion";
        datos = this.jdbc.queryForList(sql9);
        mav.addObject("ListaClasificacion", datos);
        
        //Genero
        String sql10 = "SELECT COUNT(idGenero) AS Genero FROM Genero";
        datos = this.jdbc.queryForList(sql10);
        mav.addObject("ListaGenero", datos);
        
        //Pais
        String sql11 = "SELECT COUNT(idPais) AS Pais FROM Pais";
        datos = this.jdbc.queryForList(sql11);
        mav.addObject("ListaPais", datos);

        mav.setViewName("Contenido");
        return mav;
    }
}
