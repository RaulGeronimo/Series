/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import config.conexionBD;
import entidades.Genero;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Raúl
 */
@Controller
public class controladorGenero {
    //Instancia para conexion
    conexionBD con = new conexionBD();
    JdbcTemplate jdbc = new JdbcTemplate(con.Conectar());
    ModelAndView mav = new ModelAndView(); //Importar la que dice SERVLET
    
    //Metodo para las vistas - METODO PARA OBTENER VISTAS
    @RequestMapping(value = "altaGenero.htm", method = RequestMethod.GET)
    public ModelAndView VistaAgregar(){
        mav.addObject(new Genero());
        mav.setViewName("altaGenero");
        return mav;
    }
    
    //METODO PARA AGREGAR
    @RequestMapping(value = "altaGenero.htm", method = RequestMethod.POST)
    public ModelAndView Agregar(Genero g) {
        String sql = "INSERT INTO Genero(Nombre, Descripcion) values (?,?)";
        this.jdbc.update(sql, g.getNombre(), g.getDescripcion());
        return new ModelAndView("redirect:/altaGenero.htm");
    }
    
    //METODO PARA LISTA
    int idArtista;
    List datos;

    @RequestMapping("listaGenero.htm")
    public ModelAndView Listar() {
        String sql = "SELECT * FROM Genero";
        datos = this.jdbc.queryForList(sql);
        mav.addObject("Lista", datos);
        mav.setViewName("listaGenero");
        return mav;
    }
}
