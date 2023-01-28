/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import config.conexionBD;
import entidades.Clasificacion;
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
public class controladorClasificacion {
    //Instancia para conexion
    conexionBD con = new conexionBD();
    JdbcTemplate jdbc = new JdbcTemplate(con.Conectar());
    ModelAndView mav = new ModelAndView(); //Importar la que dice SERVLET
    
    //Metodo para las vistas - METODO PARA OBTENER VISTAS
    @RequestMapping(value = "altaClasificacion.htm", method = RequestMethod.GET)
    public ModelAndView VistaAgregar(){
        mav.addObject(new Clasificacion());
        mav.setViewName("altaClasificacion");
        return mav;
    }
    
    //METODO PARA AGREGAR
    @RequestMapping(value = "altaClasificacion.htm", method = RequestMethod.POST)
    public ModelAndView Agregar(Clasificacion c) {
        String sql = "INSERT INTO Clasificacion(Nombre, Descripcion) values (?,?)";
        this.jdbc.update(sql, c.getNombre(), c.getDescripcion());
        return new ModelAndView("redirect:/altaClasificacion.htm");
    }
    
    //METODO PARA LISTA
    List datos;

    @RequestMapping("listaClasificacion.htm")
    public ModelAndView Listar() {
        String sql = "SELECT * FROM Clasificacion ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql);
        mav.addObject("Lista", datos);
        mav.setViewName("listaClasificacion");
        return mav;
    }
}
