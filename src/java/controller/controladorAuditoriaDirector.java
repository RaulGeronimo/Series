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
public class controladorAuditoriaDirector {

    //Instancia para conexion
    conexionBD con = new conexionBD();
    JdbcTemplate jdbc = new JdbcTemplate(con.Conectar());
    ModelAndView mav = new ModelAndView(); //Importar la que dice SERVLET

    //METODO PARA LISTA
    List datos;

    @RequestMapping("listaAuditoriaDirector.htm")
    public ModelAndView Listar() {
        /*String sql = "SELECT\n"
                + "Auditoria_Director.id,\n"
                + "Auditoria_Director.Nombre,\n"
                + "Auditoria_Director.Apellidos,\n"
                + "Auditoria_Director.NombreArtistico,\n"
                + "DATE_FORMAT(Auditoria_Director.FechaNacimiento, \"%d / %b / %Y\") AS FechaNacimiento,\n"
                + "DATE_FORMAT(Auditoria_Director.FechaDefuncion, \"%d / %b / %Y\") AS FechaDefuncion,\n"
                + "Auditoria_Director.Sexo,\n"
                + "FORMAT(Auditoria_Director.Estatura, 2) AS Estatura,\n"
                + "Pais.Nombre AS Pais,\n"
                + "Auditoria_Director.Usuario,\n"
                + "DATE_FORMAT(Auditoria_Director.Modificado, \"%d / %b / %Y - %r\") AS Modificado,\n"
                + "Auditoria_Director.Proceso,\n"
                + "Auditoria_Director.idDirector\n"
                + "FROM Auditoria_Director\n"
                + "INNER JOIN Pais\n"
                + "ON Auditoria_Director.Nacionalidad = Pais.idPais";*/
        String cambios = "SELECT * FROM Vista_Cambios_Director";
        datos = this.jdbc.queryForList(cambios);
        mav.addObject("Lista", datos);

        mav.setViewName("listaAuditoriaDirector");
        return mav;
    }
}
