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
public class controladorAuditoriaTemporadaSerie {

    //Instancia para conexion
    conexionBD con = new conexionBD();
    JdbcTemplate jdbc = new JdbcTemplate(con.Conectar());
    ModelAndView mav = new ModelAndView(); //Importar la que dice SERVLET

    //METODO PARA LISTA
    List datos;

    @RequestMapping("listaAuditoriaTemporadaSerie.htm")
    public ModelAndView Listar() {
        /*String sql = "SELECT \n"
                + "Auditoria_Temporadas_Series.id,\n"
                + "Series.Nombre AS Serie,\n"
                + "Auditoria_Temporadas_Series.Nombre,\n"
                + "Auditoria_Temporadas_Series.Capitulos,\n"
                + "Auditoria_Temporadas_Series.Duracion,\n"
                + "IF(Calificacion = 10, FORMAT(Calificacion, 0),FORMAT(Calificacion, 1)) AS Calificacion,\n"
                + "DATE_FORMAT(FechaInicio, \"%d / %b / %Y\") AS FechaInicio,\n"
                + "DATE_FORMAT(FechaFin, \"%d / %b / %Y\") AS FechaFin,\n"
                + "Auditoria_Temporadas_Series.Usuario,\n"
                + "DATE_FORMAT(Modificado, \"%d / %b / %Y - %r\") AS Modificado,\n"
                + "Auditoria_Temporadas_Series.Proceso,\n"
                + "Auditoria_Temporadas_Series.idTemporada\n"
                + "FROM Auditoria_Temporadas_Series\n"
                + "INNER JOIN Series\n"
                + "ON Auditoria_Temporadas_Series.idSerie = Series.idSerie";*/
        String cambios = "SELECT * FROM Vista_Cambios_Temporadas_Serie";
        datos = this.jdbc.queryForList(cambios);
        mav.addObject("Lista", datos);

        mav.setViewName("listaAuditoriaTemporadaSerie");
        return mav;
    }
}
