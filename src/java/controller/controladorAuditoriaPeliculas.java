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
public class controladorAuditoriaPeliculas {

    //Instancia para conexion
    conexionBD con = new conexionBD();
    JdbcTemplate jdbc = new JdbcTemplate(con.Conectar());
    ModelAndView mav = new ModelAndView(); //Importar la que dice SERVLET

    //METODO PARA LISTA
    List datos;

    @RequestMapping("listaAuditoriaPelicula.htm")
    public ModelAndView Listar() {
        //String sql = "SELECT * from Album";
        /*String sql = "SELECT \n"
                + "Auditoria_Peliculas.id,\n"
                + "Auditoria_Peliculas.Nombre,\n"
                + "REPLACE(Auditoria_Peliculas.OtrosNombres, 'Ã±', 'ñ') AS NombreSecundario,\n"
                + "Productora.Nombre as Productora,\n"
                + "Distribuidora.Nombre as Distribuidora,\n"
                + "DATE_FORMAT(Auditoria_Peliculas.Duracion, \"%Hh %im\") AS Duracion,\n"
                + "Genero.Nombre AS Genero,\n"
                + "Auditoria_Peliculas.Tipo,\n"
                + "Clasificacion.Nombre AS Clasificacion,\n"
                + "DATE_FORMAT(Auditoria_Peliculas.Estreno, \"%d / %b / %Y\") AS Estreno,\n"
                + "DATE_FORMAT(Auditoria_Peliculas.EstrenoMexico, \"%d / %b / %Y\") AS EstrenoMexico,\n"
                + "IF(Auditoria_Peliculas.Calificacion = 10, FORMAT(Auditoria_Peliculas.Calificacion, 0),FORMAT(Auditoria_Peliculas.Calificacion, 1)) AS Calificacion,\n"
                + "Director.NombreArtistico AS Director,\n"
                + "Auditoria_Peliculas.Portada,\n"
                + "Auditoria_Peliculas.Usuario,\n"
                + "DATE_FORMAT(Auditoria_Peliculas.Modificado, \"%d / %b / %Y - %r\") AS Modificado,\n"
                + "Auditoria_Peliculas.Proceso,\n"
                + "Auditoria_Peliculas.idPelicula\n"
                + "FROM Auditoria_Peliculas\n"
                + "INNER JOIN Productora\n"
                + "ON Productora.idProductora = Auditoria_Peliculas.idProductora\n"
                + "INNER JOIN Distribuidora\n"
                + "ON Distribuidora.idDistribuidora = Auditoria_Peliculas.idDistribuidora\n"
                + "INNER JOIN Genero\n"
                + "ON Genero.idGenero = Auditoria_Peliculas.Genero\n"
                + "INNER JOIN Clasificacion\n"
                + "ON Clasificacion.idClasificacion = Auditoria_Peliculas.Clasificacion\n"
                + "INNER JOIN Director\n"
                + "ON Auditoria_Peliculas.idDirector = Director.idDirector\n"
                + "ORDER BY Auditoria_Peliculas.Nombre";*/
        String cambios = "SELECT * FROM Vista_Cambios_Pelicula";
        datos = this.jdbc.queryForList(cambios);
        mav.addObject("Lista", datos);

        mav.setViewName("listaAuditoriaPelicula");
        return mav;
    }
}
