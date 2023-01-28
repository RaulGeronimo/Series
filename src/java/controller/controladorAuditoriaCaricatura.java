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
public class controladorAuditoriaCaricatura {

    //Instancia para conexion
    conexionBD con = new conexionBD();
    JdbcTemplate jdbc = new JdbcTemplate(con.Conectar());
    ModelAndView mav = new ModelAndView(); //Importar la que dice SERVLET

    //METODO PARA LISTA
    List datos;

    @RequestMapping("listaAuditoriaCaricatura.htm")
    public ModelAndView Listar() {
        /*String sql = "SELECT\n"
                + "Auditoria_Caricatura.id,\n"
                + "Auditoria_Caricatura.Nombre,\n"
                + "REPLACE(Auditoria_Caricatura.OtrosNombres, 'AraÃ±a', 'Araña') AS NombreSecundario,\n"
                + "Genero.Nombre AS Genero,\n"
                + "Productora.Nombre AS Productora,\n"
                + "Distribuidora.Nombre AS Distribuidora,\n"
                + "Director.NombreArtistico AS Director,\n"
                + "Auditoria_Caricatura.Portada,\n"
                + "Auditoria_Caricatura.Usuario,\n"
                + "DATE_FORMAT(Modificado, \"%d / %b / %Y - %r\") AS Modificado,\n"
                + "Auditoria_Caricatura.Proceso,\n"
                + "Auditoria_Caricatura.idCaricatura\n"
                + "FROM Auditoria_Caricatura\n"
                + "INNER JOIN Genero\n"
                + "ON Auditoria_Caricatura.Genero = Genero.idGenero\n"
                + "INNER JOIN Productora\n"
                + "ON Auditoria_Caricatura.idProductora = Productora.idProductora\n"
                + "INNER JOIN Productora AS Distribuidora\n"
                + "ON Auditoria_Caricatura.idDistribuidora = Distribuidora.idProductora\n"
                + "INNER JOIN Director\n"
                + "ON Auditoria_Caricatura.idDirector = Director.idDirector";*/
        String cambios = "SELECT * FROM Vista_Cambios_Caricatura";
        datos = this.jdbc.queryForList(cambios);
        mav.addObject("Lista", datos);

        mav.setViewName("listaAuditoriaCaricatura");
        return mav;
    }
}
