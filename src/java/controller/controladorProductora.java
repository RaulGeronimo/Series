/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import config.conexionBD;
import entidades.Productora;
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
public class controladorProductora {

    //Instancia para conexion
    conexionBD con = new conexionBD();
    JdbcTemplate jdbc = new JdbcTemplate(con.Conectar());
    ModelAndView mav = new ModelAndView(); //Importar la que dice SERVLET

    //Metodo para las vistas - METODO PARA OBTENER VISTAS
    @RequestMapping(value = "altaProductora.htm", method = RequestMethod.GET)
    public ModelAndView VistaAgregar() {
        //Genero
        String sql = "SELECT * FROM Genero ORDER BY Nombre";
        datos = this.jdbc.queryForList(sql);
        mav.addObject("ListaGenero", datos);

        mav.addObject(new Productora());
        mav.setViewName("altaProductora");
        return mav;
    }

    String nombre;

    //METODO PARA AGREGAR
    @RequestMapping(value = "altaProductora.htm", method = RequestMethod.POST)
    public ModelAndView Agregar(Productora p, HttpServletRequest request) {
        nombre = request.getParameter("nombre");
        String caricatura = "SELECT * FROM Productora WHERE Nombre LIKE '" + nombre + "'";
        datos = this.jdbc.queryForList(caricatura);

        if (datos.isEmpty()) {
            String sql = "INSERT INTO Productora(Nombre, Genero, SitioWeb, Imagen) values (?,?,?,?)";
            this.jdbc.update(sql, p.getNombre(), p.getGenero(), p.getSitio(), p.getImagen());
            return new ModelAndView("redirect:/altaProductora.htm");
        }
        return new ModelAndView("redirect:/listaProductora.htm");
    }

    //METODO PARA LISTA
    int idProductora;
    List datos;

    @RequestMapping("listaProductora.htm")
    public ModelAndView Listar() {
        String sql = "SELECT\n"
                + "Productora.idProductora, \n"
                + "Productora.Nombre, \n"
                + "Genero.Nombre AS Genero, \n"
                + "Productora.SitioWeb, \n"
                + "Productora.Imagen\n"
                + "FROM Productora\n"
                + "INNER JOIN Genero\n"
                + "ON Productora.Genero = Genero.idGenero\n"
                + "ORDER BY (Productora.Nombre)";
        datos = this.jdbc.queryForList(sql);
        mav.addObject("Lista", datos);

        String sql1 = "SELECT COUNT(idProductora) AS Productora FROM Productora";
        datos = this.jdbc.queryForList(sql1);
        mav.addObject("Conteo", datos);

        mav.setViewName("listaProductora");
        return mav;
    }

    //Editar
    @RequestMapping(value = "editarProductora.htm", method = RequestMethod.GET)
    public ModelAndView vistaEditar(HttpServletRequest request) {
        idProductora = Integer.parseInt(request.getParameter("idProductora"));
        String sql = "SELECT * FROM Productora WHERE idProductora=" + idProductora;
        datos = this.jdbc.queryForList(sql);
        mav.addObject("Lista", datos);

        //Genero
        String sq2 = "SELECT * FROM Genero ORDER BY Nombre";
        datos = this.jdbc.queryForList(sq2);
        mav.addObject("ListaGenero", datos);

        //Select
        String sql3 = "SELECT\n"
                + "Productora.idProductora, \n"
                + "Productora.Nombre, \n"
                + "Genero.Nombre AS Genero, \n"
                + "Productora.SitioWeb, \n"
                + "Productora.Imagen\n"
                + "FROM Productora\n"
                + "INNER JOIN Genero\n"
                + "ON Productora.Genero = Genero.idGenero\n"
                + "AND Productora.idProductora = " + idProductora;
        datos = this.jdbc.queryForList(sql3);
        mav.addObject("ListaProductora", datos);

        mav.setViewName("editarProductora");
        return mav;
    }

    //METODO PARA EDITAR
    @RequestMapping(value = "editarProductora.htm", method = RequestMethod.POST)
    public ModelAndView Editar(Productora p) {
        String sql = "UPDATE Productora SET Nombre=?, Genero=?, SitioWeb=?, Imagen=? WHERE idProductora=?";
        this.jdbc.update(sql, p.getNombre(), p.getGenero(), p.getSitio(), p.getImagen(), idProductora);
        return new ModelAndView("redirect:/listaProductora.htm");
    }

    //METODO PARA ELIMINAR
    @RequestMapping(value = "eliminarProductora.htm")
    public ModelAndView Eliminar(HttpServletRequest request) {
        idProductora = Integer.parseInt(request.getParameter("idProductora"));
        String sql = "DELETE FROM Productora WHERE idProductora=" + idProductora;
        this.jdbc.update(sql);
        return new ModelAndView("redirect:/listaProductora.htm");
    }
}
