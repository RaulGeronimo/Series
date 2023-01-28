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
public class controladorAuditoriaTemporadasEmision {
    //Instancia para conexion
    conexionBD con = new conexionBD();
    JdbcTemplate jdbc = new JdbcTemplate(con.Conectar());
    ModelAndView mav = new ModelAndView(); //Importar la que dice SERVLET

    //METODO PARA LISTA
    List datos;

    @RequestMapping("listaAuditoriaTemporadasEmision.htm")
    public ModelAndView Listar() {
        /*String sql = "SELECT \n"
                + "id,\n"
                + "Nombre,\n"
                + "DATE_FORMAT(FechaInicio, \"%d / %b / %Y\") AS FechaInicio,\n"
                + "DATE_FORMAT(FechaFin, \"%d / %b / %Y\") AS FechaFin,\n"
                + "Usuario,\n"
                + "DATE_FORMAT(Modificado, \"%d / %b / %Y - %r\") AS Modificado,\n"
                + "Proceso,\n"
                + "idTemporada\n"
                + "FROM Auditoria_Temporadas_Emision";*/
        String cambios = "SELECT * FROM Vista_Cambios_Temporadas_Emision";
        datos = this.jdbc.queryForList(cambios);
        mav.addObject("Lista", datos);

        mav.setViewName("listaAuditoriaTemporadasEmision");
        return mav;
    }
}
