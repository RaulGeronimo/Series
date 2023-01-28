/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import config.conexionBD;
import entidades.Pais;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
public class controladorPais {
    //Instancia para conexion
    conexionBD con = new conexionBD();
    JdbcTemplate jdbc = new JdbcTemplate(con.Conectar());
    ModelAndView mav = new ModelAndView(); //Importar la que dice SERVLET
    
    //Metodo para las vistas - METODO PARA OBTENER VISTAS
    @RequestMapping(value = "altaPais.htm", method = RequestMethod.GET)
    public ModelAndView VistaAgregar(){
        mav.addObject(new Pais());
        mav.setViewName("altaPais");
        return mav;
    }
    
    //METODO PARA AGREGAR
    @RequestMapping(value = "altaPais.htm", method = RequestMethod.POST)
    public ModelAndView Agregar(Pais p) {
        String sql = "INSERT INTO Pais(Nombre, Nacionalidad, Continente, Bandera) values (?,?,?,?)";
        this.jdbc.update(sql, p.getNombre(), p.getNacionalidad(),p.getContinente(), p.getBandera());
        return new ModelAndView("redirect:/listaPais.htm");
    }
    
    //METODO PARA LISTA
    int idPais;
    List datos;

    @RequestMapping("listaPais.htm")
    public ModelAndView Listar() {
        String sql = "SELECT * FROM Pais ORDER BY (Nombre)";
        datos = this.jdbc.queryForList(sql);
        mav.addObject("Lista", datos);
        
        String sql2 = "SELECT COUNT(idPais) AS Paises FROM Pais";
        datos = this.jdbc.queryForList(sql2);
        mav.addObject("Conteo", datos);
        
        mav.setViewName("listaPais");
        return mav;
    }
    
    //Editar
    @RequestMapping(value = "editarPais.htm", method = RequestMethod.GET)
    public ModelAndView vistaEditar(HttpServletRequest request) {
        idPais = Integer.parseInt(request.getParameter("idPais"));
        String sql = "SELECT * FROM Pais WHERE idPais=" + idPais;
        datos = this.jdbc.queryForList(sql);
        mav.addObject("Lista", datos);
        mav.setViewName("editarPais");
        return mav;
    }

    //METODO PARA EDITAR
    @RequestMapping(value = "editarPais.htm", method = RequestMethod.POST)
    public ModelAndView Editar(Pais p) {
        String sql = "UPDATE Pais SET Nombre=?, Nacionalidad=?, Continente=?, Bandera=? WHERE idPais=?";
        this.jdbc.update(sql, p.getNombre(), p.getNacionalidad(), p.getContinente(), p.getBandera(), idPais);
        return new ModelAndView("redirect:/listaPais.htm");
    }

    //METODO PARA ELIMINAR
    @RequestMapping(value = "eliminarPais.htm")
    public ModelAndView Eliminar(HttpServletRequest request) {
        idPais = Integer.parseInt(request.getParameter("idPais"));
        String sql = "DELETE FROM Pais WHERE idPais=" + idPais;
        this.jdbc.update(sql);
        return new ModelAndView("redirect:/listaPais.htm");
    }
}
