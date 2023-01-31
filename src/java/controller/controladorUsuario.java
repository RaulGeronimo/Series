/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import config.conexionBD;
import entidades.Usuario;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author raul0
 */
@Controller
public class controladorUsuario {
    
    //Instancia para conexion
    conexionBD con = new conexionBD();
    JdbcTemplate jdbc = new JdbcTemplate(con.Conectar());
    ModelAndView mav = new ModelAndView(); //Importar la que dice SERVLET

    //Metodo para las vistas - METODO PARA OBTENER VISTAS
    @RequestMapping(value = "altaUsuario.htm", method = RequestMethod.GET)
    public ModelAndView VistaAgregar() {
        mav.addObject(new Usuario());
        mav.setViewName("altaUsuario");
        return mav;
    }

    //METODO PARA AGREGAR
    @RequestMapping(value = "altaUsuario.htm", method = RequestMethod.POST)
    public ModelAndView Agregar(Usuario u, HttpServletRequest request) {
        username = request.getParameter("correo");
        String sql = "SELECT * FROM USUARIO WHERE correo='" + username + "'";
        datos = this.jdbc.queryForList(sql);
        
        if (datos.isEmpty()) {
            String sql2 = "INSERT INTO Usuario(Nombre, Paterno, Materno, fechaNacimiento, Celular, Sexo, Username, Correo, Password) values (?,?,?,?,?,?,?,?,md5(?))";
            this.jdbc.update(sql2, u.getNombre(), u.getPaterno(), u.getMaterno(), u.getFechaNacimiento(), u.getCelular(), u.getSexo(), u.getUsuario(), u.getCorreo(), u.getContrasena());
            return new ModelAndView("redirect:/login.htm");
        }
        return new ModelAndView("redirect:/altaUsuario.htm");

    }

    //METODO PARA LISTA
    List datos;

    @RequestMapping("listaUsuario.htm")
    public ModelAndView Listar() {
        String usuario = "SELECT * FROM Vista_Usuarios";
        datos = this.jdbc.queryForList(usuario);
        mav.addObject("Lista", datos);

        mav.setViewName("listaUsuario");
        return mav;
    }

    //Editar
    //Metodo para las vistas - METODO PARA OBTENER VISTAS
    @RequestMapping(value = "editarUsuario.htm", method = RequestMethod.GET)
    public ModelAndView VistaEditar() {
        mav.addObject(new Usuario());
        mav.setViewName("editarUsuario");
        return mav;
    }

    //METODO PARA ACTUALIZAR
    @RequestMapping(value = "editarUsuario.htm", method = RequestMethod.POST)
    public ModelAndView Editar(Usuario u) {
        String sql = "UPDATE Usuario SET Celular=?, Username=?, Password=md5(?) WHERE Correo=?";
        this.jdbc.update(sql, u.getCelular(), u.getUsuario(), u.getContrasena(), u.getCorreo());
        return new ModelAndView("redirect:/login.htm");
    }

    String username, password;

    //LOGIN
    @RequestMapping(value = "login.htm", method = RequestMethod.GET)
    public ModelAndView logear(HttpServletRequest request) {
        username = request.getParameter("username");
        password = request.getParameter("password");
        //String sql = "SELECT * FROM USUARIO WHERE correo='" + username + "' AND password='" + password + "'";
        String sql = "SELECT * FROM Usuario\n"
                + "WHERE (username='" + username + "' AND password=md5('" + password + "'))\n"
                + "OR (correo='" + username + "' AND password=md5('" + password + "'))";
        datos = this.jdbc.queryForList(sql);
        mav.addObject("Lista", datos);
        mav.setViewName("login");
        return mav;
    }

    @RequestMapping(value = "login.htm", method = RequestMethod.POST)
    public ModelAndView vista(HttpServletRequest request) {
        username = request.getParameter("Usuario");
        password = request.getParameter("Contra");
        //String sql = "SELECT * FROM USUARIO WHERE username='" + username + "' AND password='" + password + "'";
        String sql = "SELECT * FROM Usuario\n"
                + "WHERE (username='" + username + "' AND password=md5('" + password + "'))\n"
                + "OR (correo='" + username + "' AND password=md5('" + password + "'))";
        datos = this.jdbc.queryForList(sql);
        if (datos.isEmpty()) {
            return new ModelAndView("redirect:/login.htm");
        }
        return new ModelAndView("redirect:/index.htm");
    }
}
