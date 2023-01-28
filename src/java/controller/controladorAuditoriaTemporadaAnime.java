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
public class controladorAuditoriaTemporadaAnime {

    //Instancia para conexion
    conexionBD con = new conexionBD();
    JdbcTemplate jdbc = new JdbcTemplate(con.Conectar());
    ModelAndView mav = new ModelAndView(); //Importar la que dice SERVLET

    //METODO PARA LISTA
    List datos;

    @RequestMapping("listaAuditoriaTemporadaAnime.htm")
    public ModelAndView Listar() {
        /*String sql = "SELECT\n"
                + "Auditoria_Temporadas_Anime.id,\n"
                + "Anime.Nombre AS Anime,\n"
                + "Auditoria_Temporadas_Anime.Nombre,\n"
                + "Auditoria_Temporadas_Anime.OtrosNombres,\n"
                + "Auditoria_Temporadas_Anime.Capitulos,\n"
                + "Auditoria_Temporadas_Anime.Duracion,\n"
                + "Auditoria_Temporadas_Anime.Idioma,\n"
                + "Temporadas_Emision.Nombre AS Emision,\n"
                + "DATE_FORMAT(Auditoria_Temporadas_Anime.FechaInicio, \"%d / %b / %Y\") AS FechaInicio,\n"
                + "DATE_FORMAT(Auditoria_Temporadas_Anime.FechaFin, \"%d / %b / %Y\") AS FechaFin,\n"
                + "EstudioAnimacion.Nombre AS Estudio,\n"
                + "IF(Calificacion = 10, FORMAT(Calificacion, 0),FORMAT(Calificacion, 1)) AS Calificacion,\n"
                + "Auditoria_Temporadas_Anime.Portada,\n"
                + "Auditoria_Temporadas_Anime.Usuario,\n"
                + "DATE_FORMAT(Modificado, \"%d / %b / %Y - %r\") AS Modificado,\n"
                + "Auditoria_Temporadas_Anime.Proceso,\n"
                + "Auditoria_Temporadas_Anime.idTemporada\n"
                + "FROM Auditoria_Temporadas_Anime\n"
                + "INNER JOIN Anime\n"
                + "ON Auditoria_Temporadas_Anime.idAnime = Anime.idAnime\n"
                + "INNER JOIN Temporadas_Emision\n"
                + "ON Auditoria_Temporadas_Anime.idTemporadaEmision = Temporadas_Emision.idTemporada\n"
                + "INNER JOIN EstudioAnimacion\n"
                + "ON Auditoria_Temporadas_Anime.idEstudio = EstudioAnimacion.idEstudio";*/
        String cambios = "SELECT * FROM Vista_Cambios_Temporadas_Anime";
        datos = this.jdbc.queryForList(cambios);
        mav.addObject("Lista", datos);

        mav.setViewName("listaAuditoriaTemporadaAnime");
        return mav;
    }
}
