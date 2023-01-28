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
public class controladorAuditoriaSerie {

    //Instancia para conexion
    conexionBD con = new conexionBD();
    JdbcTemplate jdbc = new JdbcTemplate(con.Conectar());
    ModelAndView mav = new ModelAndView(); //Importar la que dice SERVLET

    //METODO PARA LISTA
    List datos;

    @RequestMapping("listaAuditoriaSerie.htm")
    public ModelAndView Listar() {
        /*String sql = "SELECT \n"
                + "Auditoria_Series.id,\n"
                + "Auditoria_Series.Nombre,\n"
                + "Auditoria_Series.OtrosNombres,\n"
                + "Genero.Nombre AS Genero,\n"
                + "Productora.Nombre AS Productora,\n"
                + "Distribuidora.Nombre AS Distribuidora,\n"
                + "Director.NombreArtistico AS Director,\n"
                + "Auditoria_Series.Portada,\n"
                + "Auditoria_Series.Usuario,\n"
                + "DATE_FORMAT(Modificado, \"%d / %b / %Y - %r\") AS Modificado,\n"
                + "Auditoria_Series.Proceso,\n"
                + "Auditoria_Series.idSerie\n"
                + "FROM Auditoria_Series\n"
                + "INNER JOIN Genero\n"
                + "ON Genero.idGenero = Auditoria_Series.Genero\n"
                + "INNER JOIN Productora\n"
                + "ON Auditoria_Series.idProductora = Productora.idProductora\n"
                + "INNER JOIN Productora AS Distribuidora\n"
                + "ON Auditoria_Series.idDistribuidora = Distribuidora.idProductora\n"
                + "INNER JOIN Director\n"
                + "ON Auditoria_Series.idDirector = Director.idDirector";*/
        String cambios = "SELECT * FROM Vista_Cambios_Serie";
        datos = this.jdbc.queryForList(cambios);
        mav.addObject("Lista", datos);

        mav.setViewName("listaAuditoriaSerie");
        return mav;
    }
}
